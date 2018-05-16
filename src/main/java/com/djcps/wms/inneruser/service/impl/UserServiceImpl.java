package com.djcps.wms.inneruser.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
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
import com.djcps.wms.stocktaking.model.orderresult.OrderResult;
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
        List<OrgUserInfoPO> list = userServer.getUserListByOrg(orgGetUserInfo);
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
        OrgUserInfoPO orgUserInfoPO = userServer.getUserByOrg(orgGetUserInfo);
        // wms获取用户仓库信息
        DeleteUserBO deleteUserBO = new DeleteUserBO();
        BeanUtils.copyProperties(orgGetUserInfo, deleteUserBO);
        List<String> warehouseIdList = userServer.getUserWarehouse(deleteUserBO);
        UserInfoPO userInfoPO = new UserInfoPO();
        if (!ObjectUtils.isEmpty(warehouseIdList)) {
            userInfoPO.setWarehouseIdList(warehouseIdList);
        }
        if (!ObjectUtils.isEmpty(orgGetUserInfo)) {
            userInfoPO.setUserName(orgUserInfoPO.getUname());
            userInfoPO.setIdCard(orgUserInfoPO.getUid_card());
            userInfoPO.setBirthday(orgUserInfoPO.getUbirthday());
            userInfoPO.setDepartmentId(orgUserInfoPO.getUfdepartment_id());
            userInfoPO.setDepartmentName(orgUserInfoPO.getUfdepartment());
            userInfoPO.setEducation(orgUserInfoPO.getUeducation());
            userInfoPO.setEmail(orgUserInfoPO.getUemail());
            userInfoPO.setGraduateSchool(orgUserInfoPO.getUgraduate_school());
            userInfoPO.setHomeAddress(orgUserInfoPO.getUhome_address());
            userInfoPO.setInduction(orgUserInfoPO.getUinduction());
            userInfoPO.setJob(orgUserInfoPO.getUjob());
            userInfoPO.setMaritalStatus(orgUserInfoPO.getUmarital_status());
            userInfoPO.setNation(orgUserInfoPO.getUnation());
            userInfoPO.setPhone(orgUserInfoPO.getUphone());
            userInfoPO.setPassword(orgUserInfoPO.getUpassword());
            userInfoPO.setPlaceOrigin(orgUserInfoPO.getUplace_origin());
            userInfoPO.setPoliticalOutlook(orgUserInfoPO.getUpolitical_outlook());
            userInfoPO.setSex(orgUserInfoPO.getUsex());
            userInfoPO.setShortPhone(orgUserInfoPO.getUshort_phone());
            userInfoPO.setPositionName(orgUserInfoPO.getUposition_name());
            userInfoPO.setUserStatus(orgUserInfoPO.getUserstatus());
            userInfoPO.setRegisteredResidence(orgUserInfoPO.getUregistered_residence());
        }
        if (!ObjectUtils.isEmpty(userInfoPO)) {
            return MsgTemplate.successMsg(userInfoPO);
        }
        return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIST);
    }

    /**
     * wms获取单条用户关联信息
     *
     * @param deleteUserBO DeleteUserBO
     * @return map
     * @author wzy
     * @date 2018/4/13 10:23
     **/
    @Override
    public Map<String, Object> getUserRelevance(DeleteUserBO deleteUserBO) {
        UserRelevanceBO userRelevanceBO = userServer.getUserRelevance(deleteUserBO);
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
    public Map<String, Object> updateUserStatus(UpdateUserBO updateUserBO) {
        HttpResult result = userServer.updateUserStatus(updateUserBO);
        return MsgTemplate.customMsg(result);
    }

    /**
     * wms删除用户关联信息
     *
     * @param deleteUserBO DeleteUserBO
     * @return Map<String ,   Object>
     * @author wzy
     * @date 2018/4/13 11:16
     **/
    @Override
    public Map<String, Object> deleteUserRelevance(DeleteUserBO deleteUserBO) {
        HttpResult result = userServer.deleteUserRelevance(deleteUserBO);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 删除用户信息，包括org
     *
     * @param orgDeleteUserBO OrgDeleteUserBO
     * @return Map<String ,   Object>
     * @author wzy
     * @date 2018/4/13 11:22
     **/
    @Override
    public Map<String, Object> deleteUser(OrgDeleteUserBO orgDeleteUserBO) {
        // 获取用户关联信息，判断是否在工作中
        DeleteUserBO deleteUserBO = new DeleteUserBO() {{
            setUserId(orgDeleteUserBO.getUserId());
            setPartnerId(orgDeleteUserBO.getPartnerId());
        }};
        orgDeleteUserBO.setId(orgDeleteUserBO.getUserId());
        UserRelevanceBO userRelevanceBO = userServer.getUserRelevance(deleteUserBO);
        if (!ObjectUtils.isEmpty(userRelevanceBO)) {
            //不在忙碌状态,可以删除
            int status = userRelevanceBO.getWorkStatus();
            if (!UserConstant.BUSY.equals(status)) {
                // org禁用用户
                orgDeleteUserBO.setStatus(UserConstant.ORG_DEL);
                Boolean delOpenUser = userServer.updateCloseOpenUser(orgDeleteUserBO);
                if (delOpenUser) {
                    // 删除用户关联信息
                    HttpResult delResult = userServer.deleteUserRelevance(deleteUserBO);
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
     * @return Map<String ,   Object>
     * @author wzy
     * @date 2018/4/13 15:20
     **/
    @Override
    public Map<String, Object> pageGetUserRelevance(PageUserInfoBO pageUserInfoBO) {
        // 获取WMS缓存的用户列表
        pageUserInfoBO.setRoleType(pageUserInfoBO.getRoleTypeCode());
        OrderResult userRelevanceResult = userServer.pageGetUserRelevance(pageUserInfoBO);
        if (userRelevanceResult.isSuccess()) {
            if (!ObjectUtils.isEmpty(userRelevanceResult.getData())) {
                // 根据用户id去org获取用户信息
                if (!ObjectUtils.isEmpty(userRelevanceResult.getData().getResult())) {
                    String userResult = gson.toJson(userRelevanceResult.getData().getResult());
                    // 查出的用户信息列表
                    List<UserRelevanceBO> userRelevancePOList = JSONObject.parseArray(userResult, UserRelevanceBO.class);
                    if (!ObjectUtils.isEmpty(userRelevancePOList)) {
                        BatchOrgUserInfoBO batchOrgUserInfoBO = new BatchOrgUserInfoBO();
                        BeanUtils.copyProperties(pageUserInfoBO, batchOrgUserInfoBO);
                        StringBuffer ids = new StringBuffer();
                        // 拼接用户id
                        userRelevancePOList.forEach(userRelevanceBO -> ids.append(userRelevanceBO.getUserId()).append(","));
                        batchOrgUserInfoBO.setUserids(ids.toString());

                        // 从org获取用户数据
                        List<OrgUserInfoPO> orgUserInfoPOS = userServer.getUserListByOrg(batchOrgUserInfoBO);
                        if (!ObjectUtils.isEmpty(orgUserInfoPOS)) {
                            userRelevancePOList.forEach(userRelevanceBO -> orgUserInfoPOS.forEach(orgUserInfoPO -> {
                                if (userRelevanceBO.getUserId().equals(orgUserInfoPO.getId())) {
                                    //赋值
                                    userRelevanceBO.setDepartment(orgUserInfoPO.getUfdepartment());
                                    userRelevanceBO.setContactWay(orgUserInfoPO.getUphone());
                                    //获取所属仓库参数
                                    StringBuffer warehouseNameStr = new StringBuffer();
                                    GetWarehouseListBO listBOList = new GetWarehouseListBO();
                                    listBOList.setPartnerId(pageUserInfoBO.getPartnerId());
                                    //拆分仓库id字符串
                                    String warehouses = userRelevanceBO.getWarehouseId();
                                    if (!StringUtils.isEmpty(warehouses)) {

                                        //分割出来的字符数组
                                        String[] warehouse = warehouses.split(",");
                                        List<String> warehouseList = Arrays.asList(warehouse);
                                        listBOList.setList(warehouseList);
                                        List<WarehouseListPO> warehouseListPOList = userServer.getUserWarehouseList(listBOList);
                                        if (!ObjectUtils.isEmpty(warehouseListPOList)) {
                                            warehouseListPOList.forEach(warehouseListPO -> warehouseNameStr.append(warehouseListPO.getWarehouseName()).append(","));
                                        }
                                    }
                                    String warehouseNameSub = null;
                                    if (!ObjectUtils.isEmpty(warehouseNameStr)) {
                                        warehouseNameSub = warehouseNameStr.toString();
                                        warehouseNameSub = warehouseNameSub.substring(0, warehouseNameSub.length() - 1);
                                    }
                                    userRelevanceBO.setWarehouseName(warehouseNameSub);

                                    StringBuffer roleTypeName = new StringBuffer();
                                    // wms的获取角色类型id
                                    String roleTypes = userRelevanceBO.getRoleType();
                                    // 分割出来的字符数组
                                    String[] roleType = roleTypes.split(",");
                                    List<String> roleTypeList = Arrays.asList(roleType);
                                    // 获取角色类型id
                                    RoleTypeBO getRoleTypeBO1 = new RoleTypeBO();
                                    getRoleTypeBO1.setTypeCodeList(roleTypeList);
                                    getRoleTypeBO1.setPartnerId(pageUserInfoBO.getPartnerId());
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
                                    String roleTypeSub = null;
                                    if (!ObjectUtils.isEmpty(roleTypeName)) {
                                        roleTypeSub = roleTypeName.toString();
                                        roleTypeSub = roleTypeSub.substring(0, roleTypeSub.length() - 1);
                                    }
                                    userRelevanceBO.setRoleType(roleTypeSub);
                                }
                            }));
                            // 返回数据
                            userRelevanceResult.getData().setResult(userRelevancePOList);
                            return MsgTemplate.successMsg(userRelevanceResult.getData());
                        } else {
                            return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIST);
                        }
                    }
                }
            }
            return MsgTemplate.successMsg(userRelevanceResult.getData());
        }
        return MsgTemplate.failureMsg(UserMsgEnum.GET_USER_FAIL);
    }

    /**
     * 保存用户信息 包括新增/修改
     *
     * @param wmsSaveUserBO WmsSaveUserBO
     * @return Map<String ,   Object>
     * @author wzy
     * @date 2018/4/16 11:21
     **/
    @Override
    public Map<String, Object> saveUser(WmsSaveUserBO wmsSaveUserBO) {
        // 参数拷贝
        SaveUserBO saveUserBO = new SaveUserBO();
        if (!ObjectUtils.isEmpty(wmsSaveUserBO)) {
            SaveUserBO saveUserBOOne =  new SaveUserBO() {
                {
                    setBusiness(wmsSaveUserBO.getBusiness());
                    setOperator(wmsSaveUserBO.getOperator());
                    setIp(wmsSaveUserBO.getIp());
                    setWarehouseId(wmsSaveUserBO.getWarehouseId());
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
                }
            };
            BeanUtils.copyProperties(saveUserBOOne, saveUserBO);
        }
        // 无用户id，表示新增
        if (StringUtils.isEmpty(saveUserBO.getId())) {
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
                DeleteUserBO deleteUserBO = new DeleteUserBO();
                deleteUserBO.setPartnerId(wmsSaveUserBO.getPartnerId());
                deleteUserBO.setUserId(wmsSaveUserBO.getUserId());
                UserRelevanceBO relevanceBO = userServer.getUserRelevance(deleteUserBO);
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
                    UserRelevanceBO userRelevanceBO = new UserRelevanceBO();
                    userRelevanceBO.setUserId(orgSaveUserBO.getId());
                    userRelevanceBO.setWarehouseId(saveUserBO.getWarehouseId());
                    userRelevanceBO.setUserName(saveUserBO.getUname());
                    userRelevanceBO.setRoleType(getRoleTypeCode(roleInfoPOList));
                    userRelevanceBO.setPartnerId(wmsSaveUserBO.getPartnerId());
                    userRelevanceBO.setWorkStatus(UserConstant.FREE);
                    // 新增用户关联信息
                    HttpResult addUserRelevance = userServer.insertUserRelevance(userRelevanceBO);
                    if (addUserRelevance.isSuccess()) {
                        if (!StringUtils.isEmpty(userRelevanceBO.getWarehouseId())) {
                            //仓库id字符串
                            String warehouses = userRelevanceBO.getWarehouseId();
                            List<AddUserWarehouseBO> warehouseList = new ArrayList<>();
                            AddUserWarehouseBO addUserWarehouseBO = new AddUserWarehouseBO();
                            addUserWarehouseBO.setUserId(userRelevanceBO.getUserId());
                            addUserWarehouseBO.setPartnerId(userRelevanceBO.getPartnerId());
                            addUserWarehouseBO.setWarehouseId(warehouses);
                            warehouseList.add(addUserWarehouseBO);
                            // 新增用户关联仓库
                            userServer.insertUserWarehouseList(warehouseList);
                        }
                    }
                    return MsgTemplate.successMsg();
                } else {
                    //用户已存在，修改用户关联信息
                    if (updateUserRelevance(wmsSaveUserBO, saveUserBO)) {
                        return MsgTemplate.successMsg();
                    }
                }

            }
        } else {
            // org修改用户信息
            Boolean flag = userServer.updateUserManage(saveUserBO);
            if (flag) {
                // 修改WMS用户关联信息
                if (updateUserRelevance(wmsSaveUserBO, saveUserBO)) {
                    // 用户信息修改,情况权限redis缓存信息
                    permissionRedisDao.delPermission(saveUserBO.getId());
                    return MsgTemplate.successMsg();
                }
            }
        }
        return MsgTemplate.failureMsg(UserMsgEnum.SAVE_USER_FAIL);
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
        String roleTypes = saveUserBO.getRoleids();
        //分割出来的字符数组
        String[] roleType = roleTypes.split(",");
        List<String> roleTypeList = Arrays.asList(roleType);
        //获取角色类型id
        RoleTypeBO getRoleTypeBO1 = new RoleTypeBO();
        getRoleTypeBO1.setList(roleTypeList);
        getRoleTypeBO1.setPartnerId(wmsSaveUserBO.getPartnerId());
        List<WmsRoleInfoPO> roleInfoPOList = roleHttpServer.listRole(getRoleTypeBO1);


        // 修改WMS用户关联信息
        UpdateUserBO updateUserBO = new UpdateUserBO();
        updateUserBO.setPartnerId(wmsSaveUserBO.getPartnerId());
        updateUserBO.setUserId(saveUserBO.getId());
        updateUserBO.setWarehouseId(saveUserBO.getWarehouseId());
        updateUserBO.setUserName(saveUserBO.getUname());
        updateUserBO.setRoleType(getRoleTypeCode(roleInfoPOList));
        HttpResult updateResult = userServer.updateUserStatus(updateUserBO);
        if (!ObjectUtils.isEmpty(updateResult)) {
            return updateResult.isSuccess();
        }
        return false;
    }

    private String getRoleTypeCode(List<WmsRoleInfoPO> roleInfoPOList) {
        StringBuffer roleTypeCode = new StringBuffer();
        List<String> distinctList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(roleInfoPOList)) {
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
     * @return Map<String ,   Object>
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
     * @return Map<String ,   Object>
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
     * @return Map<String ,   Object>
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
     * @return Map<String ,   Object>
     * @author wzy
     * @date 2018/4/17 9:59
     **/
    @Override
    public Map<String, Object> getDepartAndJob(GetOrgUserInfoBO getOrgUserInfoBO) {
        getOrgUserInfoBO.setId(getOrgUserInfoBO.getUserId());
        OrgUserInfoPO orgUserInfoPO = userServer.getUserByOrg(getOrgUserInfoBO);
        UserDepartAndJobBO userDepartAndJobBO = new UserDepartAndJobBO();
        if (!ObjectUtils.isEmpty(orgUserInfoPO)) {
            userDepartAndJobBO.setDepartmentId(orgUserInfoPO.getUdepartment_id());
            userDepartAndJobBO.setDepartment(orgUserInfoPO.getUdepartment());
            userDepartAndJobBO.setJobName(orgUserInfoPO.getUjob());
            userDepartAndJobBO.setPositionId(orgUserInfoPO.getCmp_user_position__id());
            userDepartAndJobBO.setPositionName(orgUserInfoPO.getUposition_name());
            OrgGetDepartmentBO getDepartmentBO = new OrgGetDepartmentBO();
            OrgPositionBO orgPositionBO = new OrgPositionBO();
            BeanUtils.copyProperties(getOrgUserInfoBO, getDepartmentBO);
            getDepartmentBO.setCompanyID(orgUserInfoPO.getUcompany_id());
            getDepartmentBO.setType("2");
            BeanUtils.copyProperties(getOrgUserInfoBO, orgPositionBO);
            orgPositionBO.setDepartmentId(orgUserInfoPO.getUdepartment_id());
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
     * @return Map<String, Object>
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
     * @param getUserWarehouse DeleteUserBO
     * @return Map<String ,   Object>
     * @author wzy
     * @date 2018/4/17 11:09
     **/
    @Override
    public Map<String, Object> getUserWarehouse(DeleteUserBO getUserWarehouse) {
        List<String> list = userServer.getUserWarehouse(getUserWarehouse);
        if (ObjectUtils.isEmpty(list)) {
            return MsgTemplate.failureMsg(UserMsgEnum.NULL_RESULT);
        }
        return MsgTemplate.successMsg(list);
    }

    @Override
    public Map<String, Object> listUserByType(UserInfoBO userInfoBO) {
        RoleTypeBO roleTypeBO = new RoleTypeBO(){{
            setRoleTypeCode(userInfoBO.getTypeCode());
            setPartnerId(userInfoBO.getPartnerId());
        }};
        List<WmsRoleInfoPO> roleList = roleHttpServer.listRole(roleTypeBO);
        List<OrgUserInfoVO>  userInfoPOList = listUser(roleList,userInfoBO.getPartnerId());
        return MsgTemplate.successMsg(userInfoPOList);
    }


    /***
     * 将查询到的用户信息 统计到一个集合中
     * @param roleList
     * @return
     */
    private List<OrgUserInfoVO> listUser(List<WmsRoleInfoPO> roleList,String companyId){
        if (!ObjectUtils.isEmpty(roleList)){
            List<OrgUserInfoVO> userInfoPOList = new ArrayList<>();
            roleList.forEach(x -> {
                List<OrgUserInfoVO> userInfoList = userServer.listUserByRoleId(new com.djcps.wms.inneruser.model.param.OrgUserRoleBO(){{
                    setRoleId(x.getRoleId());
                    setBusiness(AppConstant.WMS);
                    setCompanyID(companyId);
                    setIp("");
                    setOperator("-1");
                }});
                if(!ObjectUtils.isEmpty(userInfoList)){
                    userInfoList.forEach(u -> {
                        Boolean match = userInfoPOList.stream().noneMatch(o -> o.getId().equals(u.getId()));
                        if(match){
                            userInfoPOList.add(u);
                        }
                    });

                }
            });
            return userInfoPOList;
        }
        return null;
    }
}
