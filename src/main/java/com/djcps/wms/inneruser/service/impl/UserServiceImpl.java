package com.djcps.wms.inneruser.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.base.BaseVO;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.inneruser.constant.UserConstant;
import com.djcps.wms.inneruser.enums.UserMsgEnum;
import com.djcps.wms.inneruser.model.param.*;
import com.djcps.wms.inneruser.model.result.*;
import com.djcps.wms.inneruser.server.UserServer;
import com.djcps.wms.inneruser.service.UserService;
import com.djcps.wms.permission.redis.PermissionRedisDao;
import com.djcps.wms.role.constant.RoleConstant;
import com.djcps.wms.role.model.OrgUserRoleBO;
import com.djcps.wms.role.model.OrgUserRoleVO;
import com.djcps.wms.role.model.request.WmsRoleInfoPO;
import com.djcps.wms.role.server.OrgRoleHttpServer;
import com.djcps.wms.role.server.RoleHttpServer;
import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户模块逻辑层
 *
 * @author wzy
 * @date 2018/4/20
 **/
@Service
public class UserServiceImpl implements UserService {

    private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserServer userServer;

    @Resource
    private OrgRoleHttpServer orgRoleHttpServer;

    @Resource
    private RoleHttpServer roleHttpServer;

    @Resource
    private PermissionRedisDao permissionRedisDao;

    private Gson gson = new Gson();

    /**
     * org根据用户批量id获取用户信息列表
     *
     * @param orgGetUserInfo BatchOrgUserInfoBO
     * @return map
     * @author wzy
     * @date 2018/4/12 14:53
     **/
    @Override
    public Map<String, Object> getUserList(BatchOrgUserInfoBO orgGetUserInfo) {
        // 批量从org获取用户信息
        List<UserInfoPO> list = userServer.getUserListByOrg(orgGetUserInfo);
        return MsgTemplate.successMsg(list);
    }


    /**
     * 从org获取一个用户的所有信息
     *
     * @param orgGetUserInfo GetOrgUserInfoBO
     * @return map
     * @author wzy
     * @date 2018/4/12 14:53
     **/
    @Override
    public Map<String, Object> getUser(GetOrgUserInfoBO orgGetUserInfo) {
        // org获取用户信息
        UserInfoPO userInfoPO = userServer.getUserByOrg(orgGetUserInfo);
        // wms获取用户仓库信息
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(orgGetUserInfo, userBO);
        ListWarehouseBO listWarehouseBO = new ListWarehouseBO() {{
            setUserIds(Arrays.asList(orgGetUserInfo.getUserId()));
            setPartnerId(orgGetUserInfo.getPartnerId());
        }};
        List<WarehouseListPO> warehouseList = userServer.listUserWarehouse(listWarehouseBO);
        if (!ObjectUtils.isEmpty(warehouseList)) {
            List<String> warehouseIds = warehouseList.stream().map(u -> u.getWarehouseId())
                    .collect(Collectors.toList());
            userInfoPO.setWarehouseIds(warehouseIds);
            userInfoPO.setWarehouseList(warehouseList);
        }
        if (!ObjectUtils.isEmpty(userInfoPO)) {
            return MsgTemplate.successMsg(userInfoPO);
        }
        return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIST);
    }

    /**
     * wms获取单条用户关联信息
     *
     * @param userBO UserBO
     * @return map
     * @author wzy
     * @date 2018/4/13 10:23
     **/
    @Override
    public Map<String, Object> getUserRelevance(UserBO userBO) {
        UserRelevancePO userRelevanceBO = userServer.getUserRelevance(userBO);
        if (ObjectUtils.isEmpty(userRelevanceBO)) {
            return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIST);
        }
        return MsgTemplate.successMsg(userRelevanceBO);
    }

    /**
     * wms修改用户工作状态和仓库等信息
     *
     * @param updateUserBO UpdateUserBO
     * @return map
     * @author wzy
     * @date 2018/4/13 9:40
     **/
    @Override
    public Map<String, Object> updateUserRelevance(UpdateUserBO updateUserBO) {
        HttpResult result = userServer.updateUserRelevance(updateUserBO);
        return MsgTemplate.customMsg(result);
    }

    /**
     * wms删除用户关联信息
     *
     * @param userBO UserBO
     * @return Map
     * @author wzy
     * @date 2018/4/13 11:16
     **/
    @Override
    public Map<String, Object> deleteUserRelevance(UserBO userBO) {
        HttpResult result = userServer.deleteUserRelevance(userBO);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 删除用户信息，包括org
     *
     * @param orgDeleteUserBO OrgDeleteUserBO
     * @return Map
     * @author wzy
     * @date 2018/4/13 11:22
     **/
    @Override
    public Map<String, Object> deleteUser(OrgDeleteUserBO orgDeleteUserBO) {
        // 获取用户关联信息，判断是否在工作中
        UserBO userBO = new UserBO() {{
            setUserId(orgDeleteUserBO.getUserId());
            setPartnerId(orgDeleteUserBO.getPartnerId());
        }};
        orgDeleteUserBO.setId(orgDeleteUserBO.getUserId());
        UserRelevancePO userRelevanceBO = userServer.getUserRelevance(userBO);
        if (!ObjectUtils.isEmpty(userRelevanceBO)) {
            //不在忙碌状态,可以删除
            int status = userRelevanceBO.getWorkStatus();
            if (!UserConstant.BUSY.equals(status)) {
                // org禁用用户
                orgDeleteUserBO.setStatus(UserConstant.ORG_DEL);
                Boolean delOpenUser = userServer.updateCloseOpenUser(orgDeleteUserBO);
                if (delOpenUser) {
                    // 删除用户关联信息
                    HttpResult delResult = userServer.deleteUserRelevance(userBO);
                    if (delResult.isSuccess()) {
                        return MsgTemplate.successMsg();
                    }
                }
                return MsgTemplate.failureMsg(UserMsgEnum.DEL_USER_FAIL);
            }
            return MsgTemplate.failureMsg(UserMsgEnum.USER_BUSY);
        }
        return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIST);

    }


    /**
     * 分页获取用户列表
     *
     * @param pageUserInfoBO PageUserInfoBO
     * @return Map
     * @author wzy
     * @date 2018/4/13 15:20
     **/
    @Override
    public Map<String, Object> pageUserRelevance(PageUserInfoBO pageUserInfoBO) {
        // 获取WMS缓存的用户列表
        pageUserInfoBO.setRoleType(pageUserInfoBO.getRoleTypeCode());
        BaseVO baseVO = userServer.pageUserRelevance(pageUserInfoBO);
        if (!ObjectUtils.isEmpty(baseVO)) {
            // 根据用户id去org获取用户信息
            if (!ObjectUtils.isEmpty(baseVO.getResult())) {
                String userResult = gson.toJson(baseVO.getResult());
                // 查出的用户信息列表
                List<UserRelevancePO> userRelevancePOList = JSONObject.parseArray(userResult, UserRelevancePO.class);
                if (!ObjectUtils.isEmpty(userRelevancePOList)) {
                    BatchOrgUserInfoBO batchOrgUserInfoBO = new BatchOrgUserInfoBO();
                    BeanUtils.copyProperties(pageUserInfoBO, batchOrgUserInfoBO);
                    StringBuffer ids = new StringBuffer();
                    // 拼接用户id
                    userRelevancePOList.forEach(userRelevanceBO -> ids.append(userRelevanceBO.getUserId()).append(","));
                    batchOrgUserInfoBO.setUserids(ids.toString());

                    // 从org获取用户数据
                    List<UserInfoPO> userInfoPOList = userServer.getUserListByOrg(batchOrgUserInfoBO);
                    if (!ObjectUtils.isEmpty(userInfoPOList)) {
                        userRelevancePOList.forEach(userRelevanceBO -> userInfoPOList.forEach(userInfoPO -> {
                            if (userRelevanceBO.getUserId().equals(userInfoPO.getId())) {
                                //赋值
                                userRelevanceBO.setDepartment(userInfoPO.getDepartmentName());
                                userRelevanceBO.setContactWay(userInfoPO.getPhone());

                                userRelevanceBO.setPartnerId(pageUserInfoBO.getPartnerId());
                                //TODO 拆分仓库id字符串
                                if (!ObjectUtils.isEmpty(userRelevanceBO.getWarehouseIds())) {
                                    ListWarehouseBO listWarehouseBO = new ListWarehouseBO() {{
                                        setUserIds(Arrays.asList(userRelevanceBO.getUserId()));
                                        setPartnerId(userRelevanceBO.getPartnerId());
                                    }};
                                    List<WarehouseListPO> warehouseListPOList = userServer.listUserWarehouse(listWarehouseBO);
                                    if (!ObjectUtils.isEmpty(warehouseListPOList)) {
                                        StringBuilder warehouseNameStr = new StringBuilder();
                                        warehouseListPOList.forEach(warehouseListPO -> warehouseNameStr.append(warehouseListPO.getWarehouseName()).append(","));
                                        String warehouseName = warehouseNameStr.toString();
                                        if (!ObjectUtils.isEmpty(warehouseName)) {
                                            warehouseName = warehouseName.substring(0, warehouseName.length() - 1);
                                            userRelevanceBO.setWarehouseName(warehouseName);
                                        }
                                    }
                                }

                                StringBuffer roleTypeName = new StringBuffer();
                                // wms的获取角色类型id
                                String roleTypes = userRelevanceBO.getRoleType();
                                // 分割出来的字符数组
                                String[] roleType = roleTypes.split(",");
                                List<String> roleTypeList = Arrays.asList(roleType);
                                // 获取角色类型id
                                RoleTypeBO getRoleTypeBO1 = new RoleTypeBO(){{
                                    setTypeCodeList(roleTypeList);
                                    setPartnerId(pageUserInfoBO.getPartnerId());
                                }};
                                List<WmsRoleInfoPO> roleInfoPOList = roleHttpServer.listRole(getRoleTypeBO1);
                                List<String> distinctList = new ArrayList<>();
                                if (!ObjectUtils.isEmpty(roleInfoPOList)) {
                                    roleInfoPOList.forEach(roleInfoPO -> {
                                        if (!distinctList.contains(roleInfoPO.getRoleTypeCode())) {
                                            distinctList.add(roleInfoPO.getRoleTypeCode());
                                            roleTypeName.append(roleInfoPO.getRoleTypeName()).append(",");
                                        }
                                    });
                                }
                                // 去掉最后一个逗号

                                if (!ObjectUtils.isEmpty(roleTypeName)) {
                                    String name = roleTypeName.toString();
                                    name = name.substring(0, name.length() - 1);
                                    userRelevanceBO.setRoleType(name);
                                }

                            }
                        }));
                        // 返回数据
                        baseVO.setResult(userRelevancePOList);
                        return MsgTemplate.successMsg(baseVO);
                    } else {
                        return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIST);
                    }
                }
            }
            return MsgTemplate.successMsg(baseVO);
        }
        return MsgTemplate.failureMsg(UserMsgEnum.GET_USER_FAIL);
    }

    /**
     * 保存用户信息 包括新增/修改
     *
     * @param wmsSaveUserBO WmsSaveUserBO
     * @return Map
     * @author wzy
     * @date 2018/4/16 11:21
     **/
    @Override
    public Map<String, Object> saveUser(WmsSaveUserBO wmsSaveUserBO) {
        // 参数拷贝
        if (StringUtils.isEmpty(wmsSaveUserBO.getUserId())) {
            SaveUserBO saveUserBO = saveUserBO(wmsSaveUserBO);
            // org新增用户
            HttpResult result = userServer.addPostUserInfo(saveUserBO);
            if (ObjectUtils.isEmpty(result)) {
                return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
            }
            SaveUserBO orgSaveUserBO;
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                List<SaveUserBO> list = JSONArray.parseArray(data, SaveUserBO.class);
                orgSaveUserBO = list.get(0);
            } else {
                return MsgTemplate.customMsg(result);
            }
            if (!ObjectUtils.isEmpty(orgSaveUserBO)) {
                UserBO userBO = new UserBO(){{
                    setPartnerId(wmsSaveUserBO.getPartnerId());
                    setUserId(wmsSaveUserBO.getUserId());
                }};
                UserRelevancePO relevanceBO = userServer.getUserRelevance(userBO);
                //用户不存在，新增用户关联信息
                if (ObjectUtils.isEmpty(relevanceBO)) {
                    //获取角色类型id
                    String roleTypes = saveUserBO.getRoleids();
                    //分割出来的字符数组
                    String[] roleType = roleTypes.split(",");
                    List<String> roleTypeList = Arrays.asList(roleType);
                    //获取角色类型id
                    RoleTypeBO getRoleTypeBO1 = new RoleTypeBO();
                    getRoleTypeBO1.setList(roleTypeList);
                    getRoleTypeBO1.setPartnerId(wmsSaveUserBO.getPartnerId());
                    List<WmsRoleInfoPO> roleInfoPOList = roleHttpServer.listRole(getRoleTypeBO1);
                    UserRelevanceBO userRelevanceBO = new UserRelevanceBO(){{
                        setUserId(orgSaveUserBO.getId());
                        setWarehouseIds(wmsSaveUserBO.getWarehouseIds());
                        setUserName(saveUserBO.getUname());
                        setRoleType(getRoleTypeCode(roleInfoPOList));
                        setPartnerId(wmsSaveUserBO.getPartnerId());
                        setWorkStatus(UserConstant.FREE);
                    }};

                    // 新增用户关联信息
                    HttpResult httpResult = userServer.saveUserRelevance(userRelevanceBO);
                    if (!ObjectUtils.isEmpty(httpResult)) {
                        if (httpResult.isSuccess()) {
                            return MsgTemplate.successMsg();
                        }
                    }
                } else {
                    //用户已存在，修改用户关联信息
                    if (updateUserRelevance(wmsSaveUserBO, saveUserBO)) {
                        return MsgTemplate.successMsg();
                    }
                }

            }
        } else {
            SaveUserBO saveUserBO = saveUserBO(wmsSaveUserBO);
            // org修改用户信息
            Boolean flag = userServer.updateUserManage(saveUserBO);
            if (flag) {
                // 修改WMS用户关联信息
                if (updateUserRelevance(wmsSaveUserBO, saveUserBO)) {
                    // 用户信息修改,情况权限redis缓存信息
                    permissionRedisDao.delPermission(saveUserBO.getId());
                    permissionRedisDao.delPermissionTree(saveUserBO.getId());
                    return MsgTemplate.successMsg();
                }
            }
        }
        return MsgTemplate.failureMsg(UserMsgEnum.SAVE_USER_FAIL);
    }

    private SaveUserBO saveUserBO(WmsSaveUserBO wmsSaveUserBO){
        return  new SaveUserBO() {{
                setId(wmsSaveUserBO.getUserId());
                setBusiness(wmsSaveUserBO.getBusiness());
                setOperator(wmsSaveUserBO.getOperator());
                setIp(wmsSaveUserBO.getIp());
                setUphone(wmsSaveUserBO.getPhone());
                setRoleType(wmsSaveUserBO.getRoleType());
                setUname(wmsSaveUserBO.getUserName());
                setId(wmsSaveUserBO.getUserId());
                setFdepartment_id(wmsSaveUserBO.getDepartmentId());
                setFdepartment(wmsSaveUserBO.getDepartmentName());
                setUposition_name(wmsSaveUserBO.getPositionName());
                setUjob(wmsSaveUserBO.getJob());
                setUshort_phone(wmsSaveUserBO.getShortPhone());
                setUid_card(wmsSaveUserBO.getIdCard());
                setUnation(wmsSaveUserBO.getNation());
                setUemail(wmsSaveUserBO.getEmail());
                setUeducation(wmsSaveUserBO.getEducation());
                setUmarital_status(wmsSaveUserBO.getMaritalStatus());
                setUpolitical_outlook(wmsSaveUserBO.getPoliticalOutlook());
                setUserStatus(wmsSaveUserBO.getUserStatus());
                setPositionId(wmsSaveUserBO.getPositionId());
                setUinduction(wmsSaveUserBO.getInduction());
                setUgraduate_school(wmsSaveUserBO.getGraduatSchool());
                setCompanyID(wmsSaveUserBO.getCompanyId());
                setRoleids(wmsSaveUserBO.getRoleIds());
                setOnlineUserId(wmsSaveUserBO.getOnlineUserId());
                setUhome_address(wmsSaveUserBO.getHomeAddress());
                setUregistered_residence(wmsSaveUserBO.getRegisteredResidence());
            }};
    }


    /**
     * 修改用户关联信息
     *
     * @param wmsSaveUserBO WmsSaveUserBO
     * @param saveUserBO    SaveUserBO
     * @return Boolean
     * @author wzy
     * @date 2018/4/19 10:02
     **/
    private Boolean updateUserRelevance(WmsSaveUserBO wmsSaveUserBO, SaveUserBO saveUserBO) {
        //获取角色类型id
        String roleTypes = wmsSaveUserBO.getRoleIds();
        //分割出来的字符数组
        String[] roleType = roleTypes.split(",");
        List<String> roleTypeList = Arrays.asList(roleType);
        //获取角色类型id
        RoleTypeBO getRoleTypeBO1 = new RoleTypeBO(){{
            setList(roleTypeList);
            setPartnerId(wmsSaveUserBO.getPartnerId());
        }};
        List<WmsRoleInfoPO> roleInfoPOList = roleHttpServer.listRole(getRoleTypeBO1);

        // 修改WMS用户关联信息
        UpdateUserBO updateUserBO = new UpdateUserBO();
        updateUserBO.setPartnerId(wmsSaveUserBO.getPartnerId());
        updateUserBO.setUserId(wmsSaveUserBO.getUserId());
        updateUserBO.setWarehouseIds(wmsSaveUserBO.getWarehouseIds());
        updateUserBO.setUserName(wmsSaveUserBO.getUserName());
        updateUserBO.setRoleType(getRoleTypeCode(roleInfoPOList));
        HttpResult updateResult = userServer.updateUserRelevance(updateUserBO);
        if (!ObjectUtils.isEmpty(updateResult)) {
            return updateResult.isSuccess();
        }
        return false;
    }

    /**
     * 角色类型代码去重处理
     * @param roleInfoPOList
     * @return String
     */
    private String getRoleTypeCode(List<WmsRoleInfoPO> roleInfoPOList) {
        StringBuffer roleTypeCode = new StringBuffer();
        if (!ObjectUtils.isEmpty(roleInfoPOList)) {
            List<String> distinctList = new ArrayList<>();
            roleInfoPOList.forEach(roleInfoPO -> {
                if (!distinctList.contains(roleInfoPO.getRoleTypeCode())) {
                    distinctList.add(roleInfoPO.getRoleTypeCode());
                    roleTypeCode.append(roleInfoPO.getRoleTypeCode()).append(",");
                }
            });
        }
        return roleTypeCode.toString();
    }

    /**
     * 获取公司所有部门
     *
     * @param getDepartmentBO GetDepartmentBO
     * @return Map
     * @author wzy
     * @date 2018/4/16 11:43
     **/
    @Override
    public Map<String, Object> getAllDepartment(GetDepartmentBO getDepartmentBO) {
        OrgGetDepartmentBO orgGetDepartmentBO = new OrgGetDepartmentBO();
        BeanUtils.copyProperties(getDepartmentBO, orgGetDepartmentBO);
        orgGetDepartmentBO.setCompanyID(getDepartmentBO.getCompanyId());
        List<DepartmentVO> departmentVOList = userServer.getDepartment(orgGetDepartmentBO);
        return MsgTemplate.successMsg(departmentVOList);
    }

    /**
     * 获取公司所有职务
     *
     * @param getJobBO GetJobBO
     * @return Map
     * @author wzy
     * @date 2018/4/17 9:40
     **/
    @Override
    public Map<String, Object> getUjob(GetJobBO getJobBO) {
        OrgGetDepartmentBO orgGetDepartmentBO = new OrgGetDepartmentBO();
        BeanUtils.copyProperties(getJobBO, orgGetDepartmentBO);
        orgGetDepartmentBO.setCompanyID(getJobBO.getCompanyId());
        orgGetDepartmentBO.setType("");
        List<JobVO> jobVOList = userServer.getUserJob(orgGetDepartmentBO);
        return MsgTemplate.successMsg(jobVOList);
    }

    /**
     * 公司所有职位
     *
     * @param orgPositionBO OrgPositionBO
     * @return Map
     * @author wzy
     * @date 2018/4/17 9:40
     **/
    @Override
    public Map<String, Object> getPosition(OrgPositionBO orgPositionBO) {
        List<PositionVO> positionVOList = userServer.getPosition(orgPositionBO);
        return MsgTemplate.successMsg(positionVOList);
    }

    /**
     * 获取用户的部门职位信息
     *
     * @param getOrgUserInfoBO GetOrgUserInfoBO
     * @return Map
     * @author wzy
     * @date 2018/4/17 9:59
     **/
    @Override
    public Map<String, Object> getDepartAndJob(GetOrgUserInfoBO getOrgUserInfoBO) {
        getOrgUserInfoBO.setId(getOrgUserInfoBO.getUserId());
        UserInfoPO userInfoPO = userServer.getUserByOrg(getOrgUserInfoBO);
        UserDepartAndJobBO userDepartAndJobBO = new UserDepartAndJobBO();
        if (!ObjectUtils.isEmpty(userInfoPO)) {
            userDepartAndJobBO.setDepartmentId(userInfoPO.getDepartmentId());
            userDepartAndJobBO.setDepartment(userInfoPO.getDepartmentName());
            userDepartAndJobBO.setJobName(userInfoPO.getJob());
            userDepartAndJobBO.setPositionId(userInfoPO.getPositionId());
            userDepartAndJobBO.setPositionName(userInfoPO.getPositionName());
            OrgGetDepartmentBO getDepartmentBO = new OrgGetDepartmentBO();
            OrgPositionBO orgPositionBO = new OrgPositionBO();
            BeanUtils.copyProperties(getOrgUserInfoBO, getDepartmentBO);
            getDepartmentBO.setCompanyID(userInfoPO.getCompanyId());
            getDepartmentBO.setType("2");
            BeanUtils.copyProperties(getOrgUserInfoBO, orgPositionBO);
            orgPositionBO.setDepartmentId(userInfoPO.getDepartmentId());
            // 获取所有部门列表
            List<DepartmentVO> departmentVOList = userServer.getDepartment(getDepartmentBO);
            if (!ObjectUtils.isEmpty(departmentVOList)) {
                userDepartAndJobBO.setAllDepartmentList(departmentVOList);
            }
            // 获取所有职位列表
            List<JobVO> jobVOList = userServer.getUserJob(getDepartmentBO);
            if (!ObjectUtils.isEmpty(jobVOList)) {
                userDepartAndJobBO.setAllJobList(jobVOList);
            }
            // 获取所有职务列表
            List<PositionVO> positionVOList = userServer.getPosition(orgPositionBO);
            if (!ObjectUtils.isEmpty(positionVOList)) {
                userDepartAndJobBO.setAllPositionList(positionVOList);
            }
            return MsgTemplate.successMsg(userDepartAndJobBO);
        }
        return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIST);
    }

    /**
     * 获取角色列表，根据id获取或者获取全部
     *
     * @param roleTypeBO RoleTypeBO
     * @return Map
     * @author wzy
     * @date 2018/4/16 16:26
     **/
    @Override
    public Map<String, Object> roleList(RoleTypeBO roleTypeBO) {
        RoleTypeBO getRoleTypeBO = new RoleTypeBO();
        getRoleTypeBO.setPartnerId(roleTypeBO.getPartnerId());
        // 获取全部角色列表
        List<WmsRoleInfoPO> roleInfoPOList = roleHttpServer.listRole(getRoleTypeBO);
        if (ObjectUtils.isEmpty(roleInfoPOList)) {
            return MsgTemplate.failureMsg(UserMsgEnum.ROLE_TYPE_NULL);
        }
        List<WmsRoleInfoPO> getRoleList = new ArrayList<>();
        // 有用户id，获取某用户的角色信息
        if (!StringUtils.isEmpty(roleTypeBO.getUserId())) {
            OrgUserRoleBO orgUserRoleBO = new OrgUserRoleBO() {{
                setBusiness(AppConstant.WMS);
                setOperator(roleTypeBO.getUserId());
                setUserid(roleTypeBO.getUserId());
                setIp("");
                setRtype(RoleConstant.SYSTEM);
            }};
            List<OrgUserRoleVO> orgUserRolePOList = orgRoleHttpServer.getUserRole(orgUserRoleBO);
            if (!ObjectUtils.isEmpty(orgUserRolePOList)) {
                List<String> ids = new ArrayList<>();
                orgUserRolePOList.forEach(u -> ids.add(u.getRoleId()));
                RoleTypeBO getRoleTypeBO1 = new RoleTypeBO() {{
                    setList(ids);
                    setPartnerId(roleTypeBO.getPartnerId());
                }};
                getRoleList = roleHttpServer.listRole(getRoleTypeBO1);
            }

        }
        // 组织好的角色信息
        UserRoleListBO userRoleListBO = new UserRoleListBO();
        userRoleListBO.setAllRoleList(roleInfoPOList);
        if (!ObjectUtils.isEmpty(getRoleList)) {
            userRoleListBO.setPersonRoleList(getRoleList);
        }
        return MsgTemplate.successMsg(userRoleListBO);
    }

    /**
     * 获取用户关联仓库信息
     *
     * @param getUserWarehouse UserBO
     * @return Map
     * @author wzy
     * @date 2018/4/17 11:09
     **/
    @Override
    public Map<String, Object> getUserWarehouse(UserBO getUserWarehouse) {
        ListWarehouseBO listWarehouseBO = new ListWarehouseBO() {{
            setPartnerId(getUserWarehouse.getPartnerId());
            setUserIds(Arrays.asList(getUserWarehouse.getUserId()));
        }};
        List<WarehouseListPO> list = userServer.listUserWarehouse(listWarehouseBO);
        if (ObjectUtils.isEmpty(list)) {
            return MsgTemplate.failureMsg(UserMsgEnum.NULL_RESULT);
        }
        return MsgTemplate.successMsg(list);
    }

    @Override
    public Map<String, Object> listUserByType(UserInfoBO userInfoBO) {
        List<UserRelevancePO> userRelevancePOList = userServer.listUserByRoleCode(userInfoBO);
        return MsgTemplate.successMsg(userRelevancePOList);
    }


}
