package com.djcps.wms.inneruser.service.impl;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.inneruser.constant.UserConstant;
import com.djcps.wms.inneruser.enums.UserMsgEnum;
import com.djcps.wms.inneruser.model.userparam.*;
import com.djcps.wms.inneruser.server.UserServer;
import com.djcps.wms.inneruser.service.UserService;
import com.djcps.wms.stocktaking.model.orderresult.OrderResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户模块逻辑层
 * @author:wzy
 * @date:2018/4/20
 **/
@Service
public class UserServiceImpl implements UserService {
    private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserServer userServer;

    Gson gson=new Gson();

    /**
     * org根据用户批量id获取用户信息列表
     * @author  wzy
     * @param orgGetUserInfo
     * @return  map
     * @date  2018/4/12 14:53
     **/
    @Override
    public Map<String, Object> getUserList(OrgGetUserInfoByIds orgGetUserInfo) {
        //TODO 批量从org获取用户信息
        List<OrgUserInfoPO> list=userServer.getUserListByOrg(orgGetUserInfo);
        return null;
    }


    /**
     * 从org获取一个用户的所有信息
     * @author  wzy
     * @param orgGetUserInfo
     * @return  map
     * @date  2018/4/12 14:53
     **/
    @Override
    public Map<String, Object> getUser(OrgGetUserInfoById orgGetUserInfo) {
        //org获取用户信息
        OrgUserInfoPO orgUserInfoPO=userServer.getUserByOrg(orgGetUserInfo);
        //wms获取用户仓库信息
        DeleteUserBO deleteUserBO=new DeleteUserBO();
        BeanUtils.copyProperties(orgUserInfoPO,deleteUserBO);
       List<String> warehouseIdList=userServer.getUserWarehouse(deleteUserBO);
        UserInfoPO userInfoPO=new UserInfoPO();
        if (!ObjectUtils.isEmpty(warehouseIdList)){
            userInfoPO.setWarehouseIdList(warehouseIdList);
        }
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
        if(!ObjectUtils.isEmpty(userInfoPO)){
            return MsgTemplate.successMsg(userInfoPO);
        }
        return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIET);
    }

    /**
     * wms获取单条用户关联信息
     * @author  wzy
     * @param deleteUserBO
     * @return map
     * @date  2018/4/13 10:23
     **/
    @Override
    public Map<String, Object> getUserRelevance(DeleteUserBO deleteUserBO) {
        UserRelevanceBO userRelevanceBO = userServer.getUserRelevance(deleteUserBO);
        if(ObjectUtils.isEmpty(userRelevanceBO)){
            return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIET);
        }
        return MsgTemplate.successMsg(userRelevanceBO);
    }

    /**
     * wms修改用户工作状态和仓库等信息
     * @author  wzy
     * @param updateUserStatusBO
     * @return map
     * @date  2018/4/13 9:40
     **/
    @Override
    public Map<String, Object> updateUserStatus(UpdateUserStatusBO updateUserStatusBO) {
        HttpResult result = userServer.updateUserStatus(updateUserStatusBO);
        return MsgTemplate.customMsg(result);
    }

    /**
     * wms删除用户关联信息
     * @author  wzy
     * @param deleteUserBO
     * @return map
     * @date  2018/4/13 11:16
     **/
    @Override
    public Map<String, Object> deleteUserRelevance(DeleteUserBO deleteUserBO) {
        HttpResult result = userServer.deleteUserRelevance(deleteUserBO);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 删除用户信息，包括org
     * @author  wzy
     * @param
     * @return
     * @date  2018/4/13 11:22
     **/
    @Override
    public Map<String, Object> deleteUser(OrgDeleteUserBO orgDeleteUserBO) {
        //TODO 获取用户关联信息，判断是否在工作中
        DeleteUserBO deleteUserBO=new DeleteUserBO();
        deleteUserBO.setUserId(orgDeleteUserBO.getUserId());
        deleteUserBO.setPartnerId(orgDeleteUserBO.getPartnerId());
        UserRelevanceBO userRelevanceBO = userServer.getUserRelevance(deleteUserBO);
        if(!ObjectUtils.isEmpty(userRelevanceBO)){
            //不在忙碌状态,可以删除
            int status=userRelevanceBO.getWorkStatus();
            if(!UserConstant.BUSY.equals(status)){
                //TODO org禁用用户
                orgDeleteUserBO.setStatus(UserConstant.ORG_DEL);
                Boolean delOpenUser=userServer.updateCloseOpenUser(orgDeleteUserBO);
                if(delOpenUser){
                    //TODO 删除用户关联信息
                    HttpResult delResult = userServer.deleteUserRelevance(deleteUserBO);
                    if(delResult.isSuccess()){
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
     * @author  wzy
     * @param pageGetUserBO
     * @return map
     * @date  2018/4/13 15:20
     **/
    @Override
    public Map<String, Object> pageGetUserRelevance(PageGetUserBO pageGetUserBO) {
        //获取WMS缓存的用户列表
        OrderResult userRelevanceResult=userServer.pageGetUserRelevance(pageGetUserBO);
        if(userRelevanceResult.isSuccess()) {
            if (!ObjectUtils.isEmpty(userRelevanceResult.getData())){
                //TODO 根据用户id去org获取用户信息
                if(!ObjectUtils.isEmpty(userRelevanceResult.getData().getResult())){
                    String userResult = gson.toJson(userRelevanceResult.getData().getResult());
                    //查出的用户信息列表
                    List<UserRelevanceBO> userRelevancePOList = gson.fromJson(userResult, new TypeToken<ArrayList<UserRelevanceBO>>(){}.getType());
                    if (!ObjectUtils.isEmpty(userRelevancePOList)) {
                        OrgGetUserInfoByIds orgGetUserInfoByIds = new OrgGetUserInfoByIds();
                        BeanUtils.copyProperties(pageGetUserBO, orgGetUserInfoByIds);
                        StringBuffer ids = new StringBuffer();
                        //拼接用户id
                        userRelevancePOList.stream().forEach(userRelevanceBO -> {
                            ids.append(userRelevanceBO.getUserId()+",");
                        });
                        orgGetUserInfoByIds.setUserids(ids.toString());

                        // 从org获取用户数据
                        List<OrgUserInfoPO> orgUserInfoPOS = userServer.getUserListByOrg(orgGetUserInfoByIds);
                        if (!ObjectUtils.isEmpty(orgUserInfoPOS)) {
                            userRelevancePOList.stream().forEach(userRelevanceBO -> {
                                orgUserInfoPOS.stream().forEach(orgUserInfoPO -> {
                                    if (userRelevanceBO.getUserId().equals(orgUserInfoPO.getId())) {
                                        //赋值
                                        userRelevanceBO.setDepartment(orgUserInfoPO.getUfdepartment());
                                        userRelevanceBO.setContactWay(orgUserInfoPO.getUphone());
                                        //获取所属仓库参数
                                        StringBuffer warehouseNameStr=new StringBuffer();
                                        GetWarehouseListBO listBOList=new GetWarehouseListBO();
                                        listBOList.setPartnerId(pageGetUserBO.getPartnerId());
                                        //拆分仓库id字符串
                                        String warehouses=userRelevanceBO.getWarehouseId();
                                        if(!StringUtils.isEmpty(warehouses)){
                                            List<String> warehouseList=new ArrayList<>();
                                            //分割出来的字符数组
                                            String[] warehouse = warehouses.split(",");
                                            AddUserWarehouseBO addUserWarehouseBO=null;
                                            for (int i = 0; i < warehouse.length; i++) {
                                                warehouseList.add(warehouse[i]);
                                            }
                                            listBOList.setList(warehouseList);
                                            List<WarehouseListPO> warehouseListPOList=userServer.getUserWarehouseList(listBOList);
                                            if(!ObjectUtils.isEmpty(warehouseListPOList)){
                                                warehouseListPOList.stream().forEach(warehouseListPO -> {
                                                    warehouseNameStr.append(warehouseListPO.getWarehouseName()+",");
                                                });
                                            }
                                        }
                                        userRelevanceBO.setWarehouseName(warehouseNameStr.toString());
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
            return MsgTemplate.successMsg();
        }
        return MsgTemplate.failureMsg(UserMsgEnum.GET_USER_FAIL);
    }

    /**
     * 保存用户信息 包括新增/修改
     * @author  wzy
     * @param
     * @return
     * @date  2018/4/16 11:21
     **/
    @Override
    public Map<String, Object> saveUser(WmsSaveUserBO wmssaveUserBO) {
        // 参数拷贝
        SaveUserBO saveUserBO=new SaveUserBO();
        if(!ObjectUtils.isEmpty(wmssaveUserBO)){
            List<WmsSaveUserBO> list=new ArrayList<>();
            list.add(wmssaveUserBO);
            List saveUserList=list.stream().map(x->new SaveUserBO() {
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
                }
            }).collect(Collectors.toList());
            SaveUserBO saveUserBOOne=(SaveUserBO) saveUserList.get(0);
            BeanUtils.copyProperties(saveUserBOOne,saveUserBO);
        }
        // 无用户id，表示新增
        if(StringUtils.isEmpty(saveUserBO.getId())){
            // org新增用户
            SaveUserBO orgsaveUserBO=userServer.addPostUserInfo(saveUserBO);
            if(!ObjectUtils.isEmpty(orgsaveUserBO)){
                DeleteUserBO deleteUserBO=new DeleteUserBO();
                deleteUserBO.setPartnerId(wmssaveUserBO.getPartnerId());
                deleteUserBO.setUserId(wmssaveUserBO.getUserId());
                UserRelevanceBO relevanceBO = userServer.getUserRelevance(deleteUserBO);
                //用户不存在，新增用户关联信息
                if(ObjectUtils.isEmpty(relevanceBO)){
                    UserRelevanceBO userRelevanceBO= new UserRelevanceBO();
                    userRelevanceBO.setUserId(orgsaveUserBO.getId());
                    userRelevanceBO.setWarehouseId(StringUtils.isEmpty(saveUserBO.getWarehouseId())?null:saveUserBO.getWarehouseId());
                    userRelevanceBO.setUserName(StringUtils.isEmpty(saveUserBO.getUname())?null:saveUserBO.getUname());
                    userRelevanceBO.setRoleType(StringUtils.isEmpty(saveUserBO.getRoleids())?null:saveUserBO.getRoleids());
                    userRelevanceBO.setPartnerId(wmssaveUserBO.getPartnerId());
                    userRelevanceBO.setWorkStatus(UserConstant.FREE);
                    // 新增用户关联信息
                    HttpResult addUserRelevance=userServer.insertUserRelevance(userRelevanceBO);
                    if(addUserRelevance.isSuccess()){
                        if(!StringUtils.isEmpty(userRelevanceBO.getWarehouseId())){
                            //仓库id字符串
                            String warehouses=userRelevanceBO.getWarehouseId();
                            List<AddUserWarehouseBO> warehouseList=new ArrayList<>();
                            //分割出来的字符数组
//                            String[] warehouse = warehouses.split(",");
                            AddUserWarehouseBO addUserWarehouseBO=null;
//                            for (int i = 0; i < warehouse.length; i++) {
                                addUserWarehouseBO=new AddUserWarehouseBO();
                                addUserWarehouseBO.setUserId(userRelevanceBO.getUserId());
                                addUserWarehouseBO.setPartnerId(userRelevanceBO.getPartnerId());
                                addUserWarehouseBO.setWarehouseId(warehouses);
                                warehouseList.add(addUserWarehouseBO);
//                            }
                            // 新增用户关联仓库
                            HttpResult addwarehouse=userServer.insertUserWarehouseList(warehouseList);
                        }
                    }
                    return MsgTemplate.successMsg();
                }else{
                    //用户已存在，修改用户关联信息
                    if(updateUserRelevance(wmssaveUserBO,saveUserBO)){
                        return MsgTemplate.successMsg();
                    }
                }

            }
        }else{
            // org修改用户信息
            Boolean flag=userServer.updateUserManage(saveUserBO);
            if(flag){
                // 修改WMS用户关联信息
                if(updateUserRelevance(wmssaveUserBO,saveUserBO)){
                    return MsgTemplate.successMsg();
                }
            }
        }
        return MsgTemplate.failureMsg(UserMsgEnum.SAVE_USER_FAIL);
    }


    /**
     * 修改用户关联信息
     * @author  wzy
     * @param
     * @return
     * @date  2018/4/19 10:02
     **/
    public Boolean updateUserRelevance(WmsSaveUserBO wmssaveUserBO,SaveUserBO saveUserBO){
        //TODO 修改WMS用户关联信息
        UpdateUserStatusBO updateUserStatusBO=new UpdateUserStatusBO();
        updateUserStatusBO.setPartnerId(wmssaveUserBO.getPartnerId());
        updateUserStatusBO.setUserId(saveUserBO.getId());
        updateUserStatusBO.setWarehouseId(StringUtils.isEmpty(saveUserBO.getWarehouseId())?null:saveUserBO.getWarehouseId());
        updateUserStatusBO.setUserName(StringUtils.isEmpty(saveUserBO.getUname())?null:saveUserBO.getUname());
        updateUserStatusBO.setRoleType(StringUtils.isEmpty(saveUserBO.getRoleType())?null:saveUserBO.getRoleType());
        HttpResult updateResult=userServer.updateUserStatus(updateUserStatusBO);
        if(updateResult.isSuccess()){
           return true;
        }
        return false;
    }

    /**
     * 获取公司所有部门
     * @author  wzy
     * @param getDepartmentBO
     * @return  map
     * @date  2018/4/16 11:43
     **/
    @Override
    public Map<String, Object> getAllDepartment(GetDepartmentBO getDepartmentBO) {
        OrgGetDepartmentBO orgGetDepartmentBO=new OrgGetDepartmentBO();
        BeanUtils.copyProperties(getDepartmentBO,orgGetDepartmentBO);
        orgGetDepartmentBO.setCompanyID(getDepartmentBO.getCompanyId());
        List<OrgDepartmentPO> orgDepartmentBOList=userServer.getDepartment(orgGetDepartmentBO);
        List<DepartmentPO> departmentPOList=new ArrayList<>();
        DepartmentPO departmentPO=null;
        if(!ObjectUtils.isEmpty(orgDepartmentBOList)){
            for (OrgDepartmentPO orgDepartmentPO:orgDepartmentBOList){
                departmentPO=new DepartmentPO();
                departmentPO.setDepartmentId(orgDepartmentPO.getId());
                departmentPO.setDepartmentName(orgDepartmentPO.getOname());
                departmentPOList.add(departmentPO);
            }
        }
        return MsgTemplate.successMsg(departmentPOList);
    }

    /**
     * 获取公司所有职务
     * @author  wzy
     * @param getJobBO
     * @return map
     * @date  2018/4/17 9:40
     **/
    @Override
    public Map<String, Object> getUjob(GetJobBO getJobBO) {
        OrgGetDepartmentBO orgGetDepartmentBO=new OrgGetDepartmentBO();
        BeanUtils.copyProperties(getJobBO,orgGetDepartmentBO);
        orgGetDepartmentBO.setCompanyID(getJobBO.getCompanyId());
        List<OrgUjobPO> orgUjobPOList=userServer.getUjob(orgGetDepartmentBO);
        List<JobPO> jobPOList=new ArrayList<>();
        JobPO jobPO=null;
        if(!ObjectUtils.isEmpty(orgUjobPOList)){
            for (OrgUjobPO jobPO1:orgUjobPOList){
                jobPO=new JobPO();
                jobPO.setJobId(jobPO1.getId());
                jobPO.setJobName(jobPO1.getUjob_name());
                jobPOList.add(jobPO);
            }
        }
        return MsgTemplate.successMsg(jobPOList);
    }

    /**
     * 公司所有职位
     * @author  wzy
     * @param orgGetPositionBO
     * @return map
     * @date  2018/4/17 9:40
     **/
    @Override
    public Map<String, Object> getPosition(OrgGetPositionBO orgGetPositionBO) {
        List<OrgPositionPO> orgPositionPOList=userServer.getPosition(orgGetPositionBO);
        List<PositionPO> positionPOList=new ArrayList<>();
        PositionPO positionPO=null;
        if(!ObjectUtils.isEmpty(orgPositionPOList)){
            for (OrgPositionPO orgPositionPO:orgPositionPOList){
                positionPO=new PositionPO();
                positionPO.setPositionId(orgPositionPO.getId());
                positionPO.setPositionName(orgPositionPO.getUposition_name());
                positionPOList.add(positionPO);
            }
        }
        return MsgTemplate.successMsg(positionPOList);
    }

    /**
     * 获取用户的部门职位信息
     * @author  wzy
     * @param
     * @return
     * @date  2018/4/17 9:59
     **/
    @Override
    public Map<String, Object> getDepartAndJob(OrgGetUserInfoById orgGetUserInfoById) {
        OrgUserInfoPO orgUserInfoPO=userServer.getUserByOrg(orgGetUserInfoById);
        UserDepartAndJobBO userDepartAndJobBO=new UserDepartAndJobBO();
        if(!ObjectUtils.isEmpty(orgUserInfoPO)){
            userDepartAndJobBO.setDepartmentId(orgUserInfoPO.getUdepartment_id());
            userDepartAndJobBO.setDepartment(orgUserInfoPO.getUdepartment());
            userDepartAndJobBO.setJobName(orgUserInfoPO.getUjob());
            userDepartAndJobBO.setPositionId(orgUserInfoPO.getCmp_user_position__id());
            userDepartAndJobBO.setPositionName(orgUserInfoPO.getUposition_name());
            OrgGetDepartmentBO getDepartmentBO=new OrgGetDepartmentBO();
            OrgGetPositionBO orgGetPositionBO=new OrgGetPositionBO();
            BeanUtils.copyProperties(orgGetUserInfoById,getDepartmentBO);
            getDepartmentBO.setCompanyID(orgUserInfoPO.getCompany_id());
            getDepartmentBO.setType("1");
            BeanUtils.copyProperties(orgGetUserInfoById,orgGetPositionBO);
            orgGetPositionBO.setDepartmentId(orgUserInfoPO.getUdepartment_id());
            //TODO 获取所有部门列表
            List<OrgDepartmentPO> orgDepartmentBOList=userServer.getDepartment(getDepartmentBO);
            List<DepartmentPO> departmentPOList=new ArrayList<>();
            DepartmentPO departmentPO=null;
            for (OrgDepartmentPO orgDepartmentPO:orgDepartmentBOList){
                departmentPO=new DepartmentPO();
                departmentPO.setDepartmentId(orgDepartmentPO.getId());
                departmentPO.setDepartmentName(orgDepartmentPO.getOname());
                departmentPOList.add(departmentPO);
            }
            if(!ObjectUtils.isEmpty(departmentPOList)){
                userDepartAndJobBO.setAllDepartmentList(departmentPOList);
            }
            // 获取所有职位列表
            List<OrgUjobPO> orgUjobPOList=userServer.getUjob(getDepartmentBO);
            List<JobPO> jobPOList=new ArrayList<>();
            JobPO jobPO=null;
            for (OrgUjobPO orgUjobPO:orgUjobPOList){
                jobPO=new JobPO();
                jobPO.setJobId(orgUjobPO.getId());
                jobPO.setJobName(orgUjobPO.getUjob_name());
                jobPOList.add(jobPO);
            }
            if(!ObjectUtils.isEmpty(jobPOList)){
                userDepartAndJobBO.setAllJobList(jobPOList);
            }
            // 获取所有职务列表
            List<OrgPositionPO> orgPositionPOList=userServer.getPosition(orgGetPositionBO);
            List<PositionPO> positionPOList=new ArrayList<>();
            PositionPO positionPO1=null;
            for (OrgPositionPO positionPO:orgPositionPOList){
                positionPO1=new PositionPO();
                positionPO1.setPositionId(positionPO.getId());
                positionPO1.setPositionName(positionPO.getUposition_name());
                positionPOList.add(positionPO1);
            }
            if(!ObjectUtils.isEmpty(positionPOList)){
                userDepartAndJobBO.setAllPositionList(positionPOList);
            }
            return MsgTemplate.successMsg(userDepartAndJobBO);
        }
        return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIET);
    }

    /**
     * 获取角色列表，根据id获取或者获取全部
     * @author  wzy
     * @param roleTypeBO
     * @return  map
     * @date  2018/4/16 16:26
     **/
    @Override
    public Map<String, Object> roleList(RoleTypeBO roleTypeBO) {
        RoleTypeBO getRoleTypeBO=new RoleTypeBO();
        getRoleTypeBO.setPageNo(0);
        getRoleTypeBO.setPageSize(10000);
        getRoleTypeBO.setPartnerId(roleTypeBO.getPartnerId());
        //TODO 获取全部角色列表
        List<GetRoleTypePO> getRoleTypePOList=userServer.roleList(getRoleTypeBO);
        if(!ObjectUtils.isEmpty(getRoleTypePOList)){
            return MsgTemplate.failureMsg(UserMsgEnum.ROLETYPE_NULL);
        }

        List<GetRoleTypePO> getRoleList=new ArrayList<>();

        //TODO 有用户id，获取某用户的角色信息
        if(!StringUtils.isEmpty(roleTypeBO.getUserId())){
            DeleteUserBO deleteUserBO=new DeleteUserBO();
            deleteUserBO.setPartnerId(roleTypeBO.getPartnerId());
            deleteUserBO.setUserId(roleTypeBO.getUserId());
            UserRelevanceBO userRelevanceBO=userServer.getUserRelevance(deleteUserBO);
            if(!ObjectUtils.isEmpty(userRelevanceBO)){
                //TODO 有角色类型编号字段
                String roletypes=userRelevanceBO.getRoleType();
                List<String> roletypeList=new ArrayList<>();
                //分割出来的字符数组
                String[] roletype = roletypes.split(",");
                for (int i = 0; i < roletype.length; i++) {
                    roletypeList.add(roletype[i]);
                }
                RoleTypeBO getRoleTypeBO1=new RoleTypeBO();
                getRoleTypeBO1.setList(roletypeList);
                getRoleTypeBO1.setPageNo(0);
                getRoleTypeBO1.setPageSize(10000);
                getRoleTypeBO1.setPartnerId(roleTypeBO.getPartnerId());
                //TODO 获取当前角色列表
                getRoleList=userServer.roleList(getRoleTypeBO1);
            }
        }
        //组织好的角色信息
        UserRoleListBO userRoleListBO=new UserRoleListBO();
        userRoleListBO.setAllRoleList(getRoleTypePOList);
        if(!ObjectUtils.isEmpty(getRoleList)){
            userRoleListBO.setPersonRoleList(getRoleList);
        }
        return MsgTemplate.successMsg(userRoleListBO);
    }

    /**
     * 获取用户关联仓库信息
     * @author  wzy
     * @param getUserWarehouse
     * @return  \
     * @date  2018/4/17 11:09
     **/
    @Override
    public Map<String, Object> getUserWarehouse(DeleteUserBO getUserWarehouse) {
        List<String> list=userServer.getUserWarehouse(getUserWarehouse);
        if(ObjectUtils.isEmpty(list)){
            return MsgTemplate.failureMsg(UserMsgEnum.NULL_RESULT);
        }
        return MsgTemplate.successMsg(list);
    }
}
