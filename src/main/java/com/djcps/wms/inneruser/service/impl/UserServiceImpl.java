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
import com.djcps.wms.inneruser.model.userparam.*;
import com.djcps.wms.inneruser.server.UserServer;
import com.djcps.wms.inneruser.service.UserService;
import com.djcps.wms.permission.redis.PermissionRedisDao;
import com.djcps.wms.role.constant.RoleConstant;
import com.djcps.wms.role.model.OrgUserRoleBO;
import com.djcps.wms.role.model.OrgUserRoleVO;
import com.djcps.wms.role.model.request.WmsRoleInfoPO;
import com.djcps.wms.role.request.RoleHttpRequest;
import com.djcps.wms.role.server.OrgRoleHttpServer;
import com.djcps.wms.role.server.RoleHttpServer;
import com.djcps.wms.stocktaking.model.orderresult.OrderResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户模块逻辑层
 *
 * @author:wzy
 * @date:2018/4/20
 **/
@Service
public class UserServiceImpl implements UserService {

    private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserServer userServer;

    @Autowired
    private OrgRoleHttpServer orgRoleHttpServer;

    @Autowired
    private RoleHttpServer roleHttpServer;
    
    @Autowired
    private PermissionRedisDao permissionRedisDao;

    Gson gson = new Gson();

    /**
     * org根据用户批量id获取用户信息列表
     *
     * @param orgGetUserInfo
     * @return map
     * @author wzy
     * @date 2018/4/12 14:53
     **/
    @Override
    public Map<String, Object> getUserList(BatchOrgUserInfoBO orgGetUserInfo) {
        //TODO 批量从org获取用户信息
        List<OrgUserInfoPO> list = userServer.getUserListByOrg(orgGetUserInfo);
        return null;
    }


    /**
     * 从org获取一个用户的所有信息
     *
     * @param orgGetUserInfo
     * @return map
     * @author wzy
     * @date 2018/4/12 14:53
     **/
    @Override
    public Map<String, Object> getUser(GetOrgUserInfoBO orgGetUserInfo) {
        //org获取用户信息
        OrgUserInfoPO orgUserInfoPO = userServer.getUserByOrg(orgGetUserInfo);
        //wms获取用户仓库信息
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
        return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIET);
    }

    /**
     * wms获取单条用户关联信息
     *
     * @param deleteUserBO
     * @return map
     * @author wzy
     * @date 2018/4/13 10:23
     **/
    @Override
    public Map<String, Object> getUserRelevance(DeleteUserBO deleteUserBO) {
        UserRelevanceBO userRelevanceBO = userServer.getUserRelevance(deleteUserBO);
        if (ObjectUtils.isEmpty(userRelevanceBO)) {
            return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIET);
        }
        return MsgTemplate.successMsg(userRelevanceBO);
    }

    /**
     * wms修改用户工作状态和仓库等信息
     *
     * @param updateUserStatusBO
     * @return map
     * @author wzy
     * @date 2018/4/13 9:40
     **/
    @Override
    public Map<String, Object> updateUserStatus(UpdateUserStatusBO updateUserStatusBO) {
        HttpResult result = userServer.updateUserStatus(updateUserStatusBO);
        return MsgTemplate.customMsg(result);
    }

    /**
     * wms删除用户关联信息
     *
     * @param deleteUserBO
     * @return map
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
     * @param
     * @return
     * @author wzy
     * @date 2018/4/13 11:22
     **/
    @Override
    public Map<String, Object> deleteUser(OrgDeleteUserBO orgDeleteUserBO) {
        //TODO 获取用户关联信息，判断是否在工作中
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
                //TODO org禁用用户
                orgDeleteUserBO.setStatus(UserConstant.ORG_DEL);
                Boolean delOpenUser = userServer.updateCloseOpenUser(orgDeleteUserBO);
                if (delOpenUser) {
                    //TODO 删除用户关联信息
                    HttpResult delResult = userServer.deleteUserRelevance(deleteUserBO);
                    if (delResult.isSuccess()) {
                        return MsgTemplate.successMsg();
                    }
                }
                return MsgTemplate.failureMsg(UserMsgEnum.DEL_USER_FAIL);
            }
            return MsgTemplate.failureMsg(UserMsgEnum.USER_BUSY);
        }
        return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIET);

    }


    /**
     * 分页获取用户列表
     *
     * @param pageGetUserBO
     * @return map
     * @author wzy
     * @date 2018/4/13 15:20
     **/
    @Override
    public Map<String, Object> pageGetUserRelevance(PageGetUserBO pageGetUserBO) {
        //获取WMS缓存的用户列表
        pageGetUserBO.setRoleType(pageGetUserBO.getRoleTypeCode());
        OrderResult userRelevanceResult = userServer.pageGetUserRelevance(pageGetUserBO);
        if (userRelevanceResult.isSuccess()) {
            if (!ObjectUtils.isEmpty(userRelevanceResult.getData())) {
                //根据用户id去org获取用户信息
                if (!ObjectUtils.isEmpty(userRelevanceResult.getData().getResult())) {
                    String userResult = gson.toJson(userRelevanceResult.getData().getResult());
                    //查出的用户信息列表
                    List<UserRelevanceBO> userRelevancePOList = JSONObject.parseArray(userResult, UserRelevanceBO.class);
                    if (!ObjectUtils.isEmpty(userRelevancePOList)) {
                        BatchOrgUserInfoBO batchOrgUserInfoBO = new BatchOrgUserInfoBO();
                        BeanUtils.copyProperties(pageGetUserBO, batchOrgUserInfoBO);
                        StringBuffer ids = new StringBuffer();
                        //拼接用户id
                        userRelevancePOList.stream().forEach(userRelevanceBO -> {
                            ids.append(userRelevanceBO.getUserId() + ",");
                        });
                        batchOrgUserInfoBO.setUserids(ids.toString());

                        //TODO 从org获取用户数据
                        List<OrgUserInfoPO> orgUserInfoPOS = userServer.getUserListByOrg(batchOrgUserInfoBO);
                        if (!ObjectUtils.isEmpty(orgUserInfoPOS)) {
                            userRelevancePOList.stream().forEach(userRelevanceBO -> {
                                orgUserInfoPOS.stream().forEach(orgUserInfoPO -> {
                                    if (userRelevanceBO.getUserId().equals(orgUserInfoPO.getId())) {
                                        //赋值
                                        userRelevanceBO.setDepartment(orgUserInfoPO.getUfdepartment());
                                        userRelevanceBO.setContactWay(orgUserInfoPO.getUphone());
                                        //获取所属仓库参数
                                        StringBuffer warehouseNameStr = new StringBuffer();
                                        GetWarehouseListBO listBOList = new GetWarehouseListBO();
                                        listBOList.setPartnerId(pageGetUserBO.getPartnerId());
                                        //拆分仓库id字符串
                                        String warehouses = userRelevanceBO.getWarehouseId();
                                        if (!StringUtils.isEmpty(warehouses)) {

                                            //分割出来的字符数组
                                            String[] warehouse = warehouses.split(",");
                                            List<String> warehouseList = Arrays.asList(warehouse);
                                            AddUserWarehouseBO addUserWarehouseBO = null;
                                            listBOList.setList(warehouseList);
                                            List<WarehouseListPO> warehouseListPOList = userServer.getUserWarehouseList(listBOList);
                                            if (!ObjectUtils.isEmpty(warehouseListPOList)) {
                                                warehouseListPOList.stream().forEach(warehouseListPO -> {
                                                    warehouseNameStr.append(warehouseListPO.getWarehouseName() + ",");
                                                });
                                            }
                                        }
                                        String warehouseNameSub = null;
                                        if (!ObjectUtils.isEmpty(warehouseNameStr)) {
                                            warehouseNameSub = warehouseNameStr.toString();
                                            warehouseNameSub = warehouseNameSub.substring(0, warehouseNameSub.length() - 1);
                                        }
                                        userRelevanceBO.setWarehouseName(warehouseNameSub);

                                        List<String> roleTypeList = new ArrayList<>();
                                        StringBuffer roleTypeName = new StringBuffer();
                                        //wms的获取角色类型id
                                        String roleTypes = userRelevanceBO.getRoleType();
                                        //分割出来的字符数组
                                        String[] roleType = roleTypes.split(",");
                                        roleTypeList = Arrays.asList(roleType);
                                        //获取角色类型id
                                        RoleTypeBO getRoleTypeBO1 = new RoleTypeBO();
                                        getRoleTypeBO1.setTypeCodeList(roleTypeList);
                                        getRoleTypeBO1.setPartnerId(pageGetUserBO.getPartnerId());
                                        List<WmsRoleInfoPO> roleInfoPOList = roleHttpServer.listRole(getRoleTypeBO1);
                                        List<String> distinctList = new ArrayList<>();
                                        if (!ObjectUtils.isEmpty(roleInfoPOList)) {
                                            roleInfoPOList.stream().forEach(roleInfoPO -> {
                                                if (!distinctList.contains(roleInfoPO.getRoleTypeCode())) {
                                                    distinctList.add(roleInfoPO.getRoleTypeCode());
                                                    roleTypeName.append(roleInfoPO.getRoleTypeName() + ",");
                                                }
                                            });
                                        }
                                        //去掉最后一个逗号
                                        String roleTypeSub = null;
                                        if (!ObjectUtils.isEmpty(roleTypeName)) {
                                            roleTypeSub = roleTypeName.toString();
                                            roleTypeSub = roleTypeSub.substring(0, roleTypeSub.length() - 1);
                                        }
                                        userRelevanceBO.setRoleType(roleTypeSub);
                                    }
                                });
                            });
                            //返回数据
                            userRelevanceResult.getData().setResult(userRelevancePOList);
                            return MsgTemplate.successMsg(userRelevanceResult.getData());
                        } else {
                            return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIET);
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
     * @param
     * @return
     * @author wzy
     * @date 2018/4/16 11:21
     **/
    @Override
    public Map<String, Object> saveUser(WmsSaveUserBO wmssaveUserBO) {
        // 参数拷贝
        SaveUserBO saveUserBO = new SaveUserBO();
        if (!ObjectUtils.isEmpty(wmssaveUserBO)) {
            List<WmsSaveUserBO> list = new ArrayList<>();
            list.add(wmssaveUserBO);
            List saveUserList = list.stream().map(x -> new SaveUserBO() {
                {
                    setBusiness(x.getBusiness());
                    setOperator(x.getOperator());
                    setIp(x.getIp());
                    setWarehouseId(x.getWarehouseId());
                    setUphone(x.getPhone());
                    setRoleType(x.getRoleType());
                    setUname(x.getUserName());
                    setId(x.getUserId());
                    setFdepartment_id(x.getDepartmentId());
                    setFdepartment(x.getDepartmentName());
                    setUposition_name(x.getPositionName());
                    setUjob(x.getJob());
                    setUshort_phone(x.getShortPhone());
                    setUid_card(x.getIdCard());
                    setUnation(x.getNation());
                    setUemail(x.getEmail());
                    setUeducation(x.getEducation());
                    setUmarital_status(x.getMaritalStatus());
                    setUpolitical_outlook(x.getPoliticalOutlook());
                    setUserStatus(x.getUserStatus());
                    setPositionId(x.getPositionId());
                    setUinduction(x.getInduction());
                    setUgraduate_school(x.getGraduatSchool());
                    setCompanyID(x.getCompanyId());
                    setRoleids(x.getRoleIds());
                    setOnlineUserId(x.getOnlineUserId());
                    setUhome_address(x.getHomeAddress());
                    setUregistered_residence(x.getRegisteredResidence());
                }
            }).collect(Collectors.toList());
            SaveUserBO saveUserBOOne = (SaveUserBO) saveUserList.get(0);
            BeanUtils.copyProperties(saveUserBOOne, saveUserBO);
        }
        // 无用户id，表示新增
        if (StringUtils.isEmpty(saveUserBO.getId())) {
            // org新增用户
            //SaveUserBO orgsaveUserBO=userServer.addPostUserInfo(saveUserBO);
            HttpResult result = userServer.addPostUserInfo(saveUserBO);
            if (ObjectUtils.isEmpty(result)) {
                return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
            }
            SaveUserBO orgSaveUserBO = null;
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                List<SaveUserBO> list = new ArrayList<>();
                list = JSONArray.parseArray(data, SaveUserBO.class);
                orgSaveUserBO = list.get(0);
            } else {
                return MsgTemplate.customMsg(result);
            }
            if (!ObjectUtils.isEmpty(orgSaveUserBO)) {
                DeleteUserBO deleteUserBO = new DeleteUserBO();
                deleteUserBO.setPartnerId(wmssaveUserBO.getPartnerId());
                deleteUserBO.setUserId(wmssaveUserBO.getUserId());
                UserRelevanceBO relevanceBO = userServer.getUserRelevance(deleteUserBO);
                //用户不存在，新增用户关联信息
                if (ObjectUtils.isEmpty(relevanceBO)) {
                    //StringBuffer roleTypeName=new StringBuffer();
                    StringBuffer roleTypeCode = new StringBuffer();
                    //获取角色类型id
                    String roleTypes = saveUserBO.getRoleids();
                    //分割出来的字符数组
                    String[] roleType = roleTypes.split(",");
                    List<String> roleTypeList = Arrays.asList(roleType);
                    //获取角色类型id
                    RoleTypeBO getRoleTypeBO1 = new RoleTypeBO();
                    getRoleTypeBO1.setList(roleTypeList);
                    getRoleTypeBO1.setPartnerId(wmssaveUserBO.getPartnerId());
                    List<WmsRoleInfoPO> roleInfoPOList = roleHttpServer.listRole(getRoleTypeBO1);
                    List<String> distinctList = new ArrayList<>();
                    if (!ObjectUtils.isEmpty(roleInfoPOList)) {
                        roleInfoPOList.stream().forEach(roleInfoPO -> {
                            //角色类型去重
                            if (!distinctList.contains(roleInfoPO.getRoleTypeCode())) {
                                distinctList.add(roleInfoPO.getRoleTypeCode());
                                roleTypeCode.append(roleInfoPO.getRoleTypeCode() + ",");
                            }
                        });
                    }
                    UserRelevanceBO userRelevanceBO = new UserRelevanceBO();
                    userRelevanceBO.setUserId(orgSaveUserBO.getId());
                    userRelevanceBO.setWarehouseId(saveUserBO.getWarehouseId());
                    userRelevanceBO.setUserName(saveUserBO.getUname());
                    // userRelevanceBO.setRoleType(saveUserBO.getRoleids());
                    userRelevanceBO.setRoleType(roleTypeCode.toString());
                    userRelevanceBO.setPartnerId(wmssaveUserBO.getPartnerId());
                    userRelevanceBO.setWorkStatus(UserConstant.FREE);
                    // 新增用户关联信息
                    HttpResult addUserRelevance = userServer.insertUserRelevance(userRelevanceBO);
                    if (addUserRelevance.isSuccess()) {
                        if (!StringUtils.isEmpty(userRelevanceBO.getWarehouseId())) {
                            //仓库id字符串
                            String warehouses = userRelevanceBO.getWarehouseId();
                            List<AddUserWarehouseBO> warehouseList = new ArrayList<>();
                            //分割出来的字符数组
//                            String[] warehouse = warehouses.split(",");
                            AddUserWarehouseBO addUserWarehouseBO = null;
//                            for (int i = 0; i < warehouse.length; i++) {
                            addUserWarehouseBO = new AddUserWarehouseBO();
                            addUserWarehouseBO.setUserId(userRelevanceBO.getUserId());
                            addUserWarehouseBO.setPartnerId(userRelevanceBO.getPartnerId());
                            addUserWarehouseBO.setWarehouseId(warehouses);
                            warehouseList.add(addUserWarehouseBO);
//                            }
                            // 新增用户关联仓库
                            HttpResult addwarehouse = userServer.insertUserWarehouseList(warehouseList);
                        }
                    }
                    return MsgTemplate.successMsg();
                } else {
                    //用户已存在，修改用户关联信息
                    if (updateUserRelevance(wmssaveUserBO, saveUserBO)) {
                        return MsgTemplate.successMsg();
                    }
                }

            }
        } else {
            // org修改用户信息
            Boolean flag = userServer.updateUserManage(saveUserBO);
            if (flag) {
                // 修改WMS用户关联信息
                if (updateUserRelevance(wmssaveUserBO, saveUserBO)) {
                    //用户信息修改,情况权限redis缓存信息
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
     * @param
     * @return
     * @author wzy
     * @date 2018/4/19 10:02
     **/
    public Boolean updateUserRelevance(WmsSaveUserBO wmssaveUserBO, SaveUserBO saveUserBO) {
        List<String> roleTypeList = new ArrayList<>();
        //StringBuffer roleTypeName=new StringBuffer();
        StringBuffer roleTypeCode = new StringBuffer();
        //获取角色类型id
        String roleTypes = saveUserBO.getRoleids();
        //分割出来的字符数组
        String[] roleType = roleTypes.split(",");
        roleTypeList = Arrays.asList(roleType);
        //获取角色类型id
        RoleTypeBO getRoleTypeBO1 = new RoleTypeBO();
        getRoleTypeBO1.setList(roleTypeList);
        getRoleTypeBO1.setPartnerId(wmssaveUserBO.getPartnerId());
        List<WmsRoleInfoPO> roleInfoPOList = roleHttpServer.listRole(getRoleTypeBO1);

        List<String> distinctList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(roleInfoPOList)) {
            roleInfoPOList.stream().forEach(roleInfoPO -> {
                if (!distinctList.contains(roleInfoPO.getRoleTypeCode())) {
                    distinctList.add(roleInfoPO.getRoleTypeCode());
                    roleTypeCode.append(roleInfoPO.getRoleTypeCode() + ",");
                }
            });
        }
        //TODO 修改WMS用户关联信息
        UpdateUserStatusBO updateUserStatusBO = new UpdateUserStatusBO();
        updateUserStatusBO.setPartnerId(wmssaveUserBO.getPartnerId());
        updateUserStatusBO.setUserId(saveUserBO.getId());
        updateUserStatusBO.setWarehouseId(saveUserBO.getWarehouseId());
        updateUserStatusBO.setUserName(saveUserBO.getUname());
        updateUserStatusBO.setRoleType(roleTypeCode.toString());
        HttpResult updateResult = userServer.updateUserStatus(updateUserStatusBO);
        if (updateResult.isSuccess()) {
            return true;
        }
        return false;
    }

    /**
     * 获取公司所有部门
     *
     * @param getDepartmentBO
     * @return map
     * @author wzy
     * @date 2018/4/16 11:43
     **/
    @Override
    public Map<String, Object> getAllDepartment(GetDepartmentBO getDepartmentBO) {
        OrgGetDepartmentBO orgGetDepartmentBO = new OrgGetDepartmentBO();
        BeanUtils.copyProperties(getDepartmentBO, orgGetDepartmentBO);
        orgGetDepartmentBO.setCompanyID(getDepartmentBO.getCompanyId());
        List<OrgDepartmentPO> orgDepartmentBOList = userServer.getDepartment(orgGetDepartmentBO);
        List<DepartmentPO> departmentPOList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(orgDepartmentBOList)) {
            departmentPOList = orgDepartmentBOList.stream().map(x -> new DepartmentPO() {{
                setDepartmentId(x.getId());
                setDepartmentName(x.getOname());
            }}).collect(Collectors.toList());
        }
        return MsgTemplate.successMsg(departmentPOList);
    }

    /**
     * 获取公司所有职务
     *
     * @param getJobBO
     * @return map
     * @author wzy
     * @date 2018/4/17 9:40
     **/
    @Override
    public Map<String, Object> getUjob(GetJobBO getJobBO) {
        OrgGetDepartmentBO orgGetDepartmentBO = new OrgGetDepartmentBO();
        BeanUtils.copyProperties(getJobBO, orgGetDepartmentBO);
        orgGetDepartmentBO.setCompanyID(getJobBO.getCompanyId());
        orgGetDepartmentBO.setType("");
        List<OrgUjobPO> orgJobPOList = userServer.getUjob(orgGetDepartmentBO);
        List<JobPO> jobPOList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(orgJobPOList)) {
            jobPOList = orgJobPOList.stream().map(x -> new JobPO() {{
                setJobId(x.getId());
                setJobName(x.getUjob_name());
            }}).collect(Collectors.toList());
        }
        return MsgTemplate.successMsg(jobPOList);
    }

    /**
     * 公司所有职位
     *
     * @param orgGetPositionBO
     * @return map
     * @author wzy
     * @date 2018/4/17 9:40
     **/
    @Override
    public Map<String, Object> getPosition(OrgGetPositionBO orgGetPositionBO) {
        List<OrgPositionPO> orgPositionPOList = userServer.getPosition(orgGetPositionBO);
        List<PositionPO> positionPOList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(orgPositionPOList)) {
            positionPOList = orgPositionPOList.stream().map(x -> new PositionPO() {{
                setPositionId(x.getId());
                setPositionName(x.getUposition_name());
            }}).collect(Collectors.toList());
        }
        return MsgTemplate.successMsg(positionPOList);
    }

    /**
     * 获取用户的部门职位信息
     *
     * @param
     * @return
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
            OrgGetPositionBO orgGetPositionBO = new OrgGetPositionBO();
            BeanUtils.copyProperties(getOrgUserInfoBO, getDepartmentBO);
            //getDepartmentBO.setCompanyID(orgUserInfoPO.getCompany_id());
            getDepartmentBO.setCompanyID(orgUserInfoPO.getUcompany_id());
            getDepartmentBO.setType("2");
            BeanUtils.copyProperties(getOrgUserInfoBO, orgGetPositionBO);
            orgGetPositionBO.setDepartmentId(orgUserInfoPO.getUdepartment_id());
            //TODO 获取所有部门列表
            List<OrgDepartmentPO> orgDepartmentBOList = userServer.getDepartment(getDepartmentBO);
            List<DepartmentPO> departmentPOList = new ArrayList<>();
            DepartmentPO departmentPO = null;
            for (OrgDepartmentPO orgDepartmentPO : orgDepartmentBOList) {
                departmentPO = new DepartmentPO();
                departmentPO.setDepartmentId(orgDepartmentPO.getId());
                departmentPO.setDepartmentName(orgDepartmentPO.getOname());
                departmentPOList.add(departmentPO);
            }
            if (!ObjectUtils.isEmpty(departmentPOList)) {
                userDepartAndJobBO.setAllDepartmentList(departmentPOList);
            }
            // 获取所有职位列表
            List<OrgUjobPO> orgUjobPOList = userServer.getUjob(getDepartmentBO);
            List<JobPO> jobPOList = new ArrayList<>();
            JobPO jobPO = null;
            for (OrgUjobPO orgUjobPO : orgUjobPOList) {
                jobPO = new JobPO();
                jobPO.setJobId(orgUjobPO.getId());
                jobPO.setJobName(orgUjobPO.getUjob_name());
                jobPOList.add(jobPO);
            }
            if (!ObjectUtils.isEmpty(jobPOList)) {
                userDepartAndJobBO.setAllJobList(jobPOList);
            }
            // 获取所有职务列表
            List<OrgPositionPO> orgPositionPOList = userServer.getPosition(orgGetPositionBO);
            List<PositionPO> positionPOList = new ArrayList<>();
            PositionPO positionPO1 = null;
            for (OrgPositionPO positionPO : orgPositionPOList) {
                positionPO1 = new PositionPO();
                positionPO1.setPositionId(positionPO.getId());
                positionPO1.setPositionName(positionPO.getUposition_name());
                positionPOList.add(positionPO1);
            }
            if (!ObjectUtils.isEmpty(positionPOList)) {
                userDepartAndJobBO.setAllPositionList(positionPOList);
            }
            return MsgTemplate.successMsg(userDepartAndJobBO);
        }
        return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIET);
    }

    /**
     * 获取角色列表，根据id获取或者获取全部
     *
     * @param roleTypeBO
     * @return map
     * @author wzy
     * @date 2018/4/16 16:26
     **/
    @Override
    public Map<String, Object> roleList(RoleTypeBO roleTypeBO) {
        RoleTypeBO getRoleTypeBO = new RoleTypeBO();
        getRoleTypeBO.setPartnerId(roleTypeBO.getPartnerId());
        //TODO 获取全部角色列表
        List<WmsRoleInfoPO> roleInfoPOList = roleHttpServer.listRole(getRoleTypeBO);
        if (ObjectUtils.isEmpty(roleInfoPOList)) {
            return MsgTemplate.failureMsg(UserMsgEnum.ROLETYPE_NULL);
        }
        List<WmsRoleInfoPO> getRoleList = new ArrayList<>();
        //TODO 有用户id，获取某用户的角色信息
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
                orgUserRolePOList.forEach(u ->{
                    ids.add(u.getRoleId());
                });
                RoleTypeBO getRoleTypeBO1 = new RoleTypeBO(){{
                    setList(ids);
                    setPartnerId(roleTypeBO.getPartnerId());
                }};
                getRoleList = roleHttpServer.listRole(getRoleTypeBO1);
            }

        }
        //组织好的角色信息
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
     * @param getUserWarehouse
     * @return
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
}
