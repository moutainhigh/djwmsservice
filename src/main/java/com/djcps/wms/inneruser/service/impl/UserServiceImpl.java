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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        OrgUserInfoPO orgUserInfoPO=userServer.getUserByOrg(orgGetUserInfo);
        if(!ObjectUtils.isEmpty(orgUserInfoPO)){
            return MsgTemplate.successMsg(orgUserInfoPO);
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
        deleteUserBO.setUserId(orgDeleteUserBO.getId());
        deleteUserBO.setPartnerId(orgDeleteUserBO.getOperator());
        UserRelevanceBO userRelevanceBO = userServer.getUserRelevance(deleteUserBO);
        if(!ObjectUtils.isEmpty(userRelevanceBO)){
            //不在忙碌状态,可以删除
            if(!UserConstant.BUSY.equals(userRelevanceBO.getWorkStatus())){
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
        OrderResult UserRelevanceResult=userServer.pageGetUserRelevance(pageGetUserBO);
        if(UserRelevanceResult.isSuccess()) {
            if (!ObjectUtils.isEmpty(UserRelevanceResult.getData())){
                //TODO 根据用户id去org获取用户信息
                String userResult = gson.toJson(UserRelevanceResult.getData().getResult());
                //查出的用户信息
                List<UserRelevanceBO> userRelevancePOList = gson.fromJson(userResult, List.class);

                if (!ObjectUtils.isEmpty(userRelevancePOList)) {
                    OrgGetUserInfoByIds orgGetUserInfoByIds = new OrgGetUserInfoByIds();
                    BeanUtils.copyProperties(pageGetUserBO, orgGetUserInfoByIds);
                    StringBuffer ids = new StringBuffer();
                    //拼接用户id
                    userRelevancePOList.stream().forEach(userRelevanceBO -> {
                        ids.append(userRelevanceBO.getUserId());
                    });
                    orgGetUserInfoByIds.setUserids(ids.toString());
                    //TODO 从org获取用户数据
                    List<OrgUserInfoPO> orgUserInfoPOS = userServer.getUserListByOrg(orgGetUserInfoByIds);
                    if (!ObjectUtils.isEmpty(orgUserInfoPOS)) {
                        userRelevancePOList.stream().forEach(userRelevanceBO -> {
                            orgUserInfoPOS.stream().forEach(orgUserInfoPO -> {
                                if (userRelevanceBO.getUserId().equals(orgUserInfoPO.getUids())) {
                                    //赋值
                                    userRelevanceBO.setDepartment(orgUserInfoPO.getUdepartment());
                                    userRelevanceBO.setContactWay(orgUserInfoPO.getUphone());
                                }
                            });
                        });
                        //返回数据
                        // UserRelevanceResult.getData().setResult(userRelevancePOList);
                        return MsgTemplate.successMsg(userRelevancePOList);
                    } else {
                        return MsgTemplate.failureMsg(UserMsgEnum.USER_NOT_EXIET);
                    }
                }
            }
        }
        return MsgTemplate.successMsg();
    }

    /**
     * 保存用户信息 包括新增/修改
     * @author  wzy
     * @param
     * @return
     * @date  2018/4/16 11:21
     **/
    @Override
    public Map<String, Object> saveUser(SaveUserBO saveUserBO) {
        //TODO 无用户id，表示新增
        if(StringUtils.isEmpty(saveUserBO.getId())){
            //TODO org新增用户
            SaveUserBO orgsaveUserBO=userServer.addPostUserInfo(saveUserBO);
            if(!ObjectUtils.isEmpty(orgsaveUserBO)){
                UserRelevanceBO userRelevanceBO=new UserRelevanceBO();
                userRelevanceBO.setUserId(orgsaveUserBO.getId());
                userRelevanceBO.setWarehouseId(saveUserBO.getWarehouseId());
                userRelevanceBO.setUserName(saveUserBO.getUname());
                userRelevanceBO.setRoleType(saveUserBO.getRoleType());
                userRelevanceBO.setPartnerId(saveUserBO.getOperator());
                userRelevanceBO.setWorkStatus(UserConstant.FREE);
                //TODO 新增用户关联信息
                HttpResult addUserRelevance=userServer.insertUserRelevance(userRelevanceBO);
                if(addUserRelevance.isSuccess()){
                    //TODO 新增用户关联仓库
                    HttpResult addwarehouse=userServer.insertUserWarehouse(userRelevanceBO);
                    if(addwarehouse.isSuccess()){
                        return MsgTemplate.successMsg();
                    }
                }
            }
        }else{
            //TODO org修改用户信息
            Boolean flag=userServer.updateUserManage(saveUserBO);
            if(flag){
                //TODO 修改用户关联信息
                UpdateUserStatusBO updateUserStatusBO=new UpdateUserStatusBO();
                updateUserStatusBO.setPartnerId(saveUserBO.getOperator());
                updateUserStatusBO.setUserId(saveUserBO.getId());
                updateUserStatusBO.setWarehouseId(saveUserBO.getWarehouseId());
                updateUserStatusBO.setUserName(saveUserBO.getUname());
                updateUserStatusBO.setRoleType(saveUserBO.getRoleType());
                HttpResult updateResult=userServer.updateUserStatus(updateUserStatusBO);
                if(updateResult.isSuccess()){
                    return MsgTemplate.successMsg();
                }
            }
        }
        return MsgTemplate.failureMsg(UserMsgEnum.SAVE_USER_FAIL);
    }

    /**
     * 获取公司所有部门
     * @author  wzy
     * @param orgGetDepartmentBO
     * @return  map
     * @date  2018/4/16 11:43
     **/
    @Override
    public Map<String, Object> getAllDepartment(OrgGetDepartmentBO orgGetDepartmentBO) {
        List<OrgDepartmentPO> orgDepartmentBOList=userServer.getDepartment(orgGetDepartmentBO);
        return MsgTemplate.successMsg(orgDepartmentBOList);
    }

    /**
     * 获取公司所有职务
     * @author  wzy
     * @param orgGetDepartmentBO
     * @return map
     * @date  2018/4/17 9:40
     **/
    @Override
    public Map<String, Object> getUjob(OrgGetDepartmentBO orgGetDepartmentBO) {
        List<OrgUjobPO> orgUjobPOList=userServer.getUjob(orgGetDepartmentBO);
        return MsgTemplate.successMsg(orgUjobPOList);
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
        return MsgTemplate.successMsg(orgPositionPOList);
    }

    /**
     * 获取用户的部门职位信息
     * @author  wzy
     * @param
     * @return
     * @date  2018/4/17 9:59
     **/
    @Override
    public Map<String, Object> getDepartAndJob(OrgGetUserInfoById OrgGetUserInfoById) {
        OrgUserInfoPO orgUserInfoPO=userServer.getUserByOrg(OrgGetUserInfoById);
        UserDepartAndJobBO userDepartAndJobBO=new UserDepartAndJobBO();
        if(!ObjectUtils.isEmpty(orgUserInfoPO)){
            userDepartAndJobBO.setDepartmentId(orgUserInfoPO.getUdepartment_id());
            userDepartAndJobBO.setDepartment(orgUserInfoPO.getUdepartment());
            userDepartAndJobBO.setJobName(orgUserInfoPO.getUjob());
            userDepartAndJobBO.setPositionId(orgUserInfoPO.getCmp_user_position__id());
            userDepartAndJobBO.setPositionName(orgUserInfoPO.getUposition_name());
            OrgGetDepartmentBO getDepartmentBO=new OrgGetDepartmentBO();
            OrgGetPositionBO orgGetPositionBO=new OrgGetPositionBO();
            BeanUtils.copyProperties(OrgGetUserInfoById,getDepartmentBO);
            getDepartmentBO.setCompanyID(orgUserInfoPO.getCompany_id());
            getDepartmentBO.setType("1");
            BeanUtils.copyProperties(OrgGetUserInfoById,orgGetPositionBO);
            orgGetPositionBO.setDepartmentId(orgUserInfoPO.getUdepartment_id());
            //TODO 获取所有部门列表
            List<OrgDepartmentPO> orgDepartmentBOList=userServer.getDepartment(getDepartmentBO);
            if(!ObjectUtils.isEmpty(orgDepartmentBOList)){
                userDepartAndJobBO.setAllDepartmentList(orgDepartmentBOList);
            }
            //TODO 获取所有职位列表
            List<OrgUjobPO> orgUjobPOList=userServer.getUjob(getDepartmentBO);
            if(!ObjectUtils.isEmpty(orgDepartmentBOList)){
                userDepartAndJobBO.setAllJobList(orgUjobPOList);
            }
            //TODO 获取所有职务列表
            List<OrgPositionPO> orgPositionPOList=userServer.getPosition(orgGetPositionBO);
            if(!ObjectUtils.isEmpty(orgDepartmentBOList)){
                userDepartAndJobBO.setAllPositionList(orgPositionPOList);
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
                RoleTypeBO getRoleTypeBO=new RoleTypeBO();
                getRoleTypeBO.setList(roletypeList);
                getRoleTypeBO.setPageNo(0);
                getRoleTypeBO.setPageSize(10000);
                getRoleTypeBO.setPartnerId(roleTypeBO.getPartnerId());
                //TODO 获取角色列表
                List<GetRoleTypePO> getRoleTypePOList=userServer.roleList(getRoleTypeBO);
                if(!ObjectUtils.isEmpty(getRoleTypePOList)){
                    return MsgTemplate.successMsg(getRoleTypePOList);
                }
            }
        }else {
            //TODO 无用户id，获取全部角色信息信息
            RoleTypeBO getRoleTypeBO=new RoleTypeBO();
            getRoleTypeBO.setPageNo(0);
            getRoleTypeBO.setPageSize(10000);
            getRoleTypeBO.setPartnerId(roleTypeBO.getPartnerId());
            //TODO 获取角色列表
            List<GetRoleTypePO> getRoleTypePOList=userServer.roleList(getRoleTypeBO);
            if(!ObjectUtils.isEmpty(getRoleTypePOList)){
                return MsgTemplate.successMsg(getRoleTypePOList);
            }
        }
        return null;
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
        return MsgTemplate.successMsg(list);
    }
}
