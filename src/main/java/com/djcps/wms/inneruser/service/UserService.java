package com.djcps.wms.inneruser.service;

import com.djcps.wms.inneruser.model.userparam.*;

import java.util.Map;

/**
 * 用户模块
 * @author:wzy
 * @date:2018/4/12
 **/
public interface UserService {

    /**
     * org根据用户批量id获取用户信息列表
     * @param orgGetUserInfo
     * @return map
     * @author:wzy
     * @date:2018/4/12
     **/
    Map<String,Object> getUserList(OrgGetUserInfoByIds orgGetUserInfo);

    /**
     * 从org获取一个用户的信息
     * @author:wzy
     * @date:2018/4/12
     **/
    Map<String,Object> getUser(OrgGetUserInfoById orgGetUserInfo);

    /**
     * wms获取单条用户关联信息
     * @param deleteUserBO
     * @return map
     * @author:wzy
     * @date:2018/4/13
     **/
    Map<String,Object> getUserRelevance(DeleteUserBO deleteUserBO);

    /**
     * 修改用户工作状态和仓库等信息
     * @param updateUserStatusBO
     * @return map
     * @author:wzy
     * @date:2018/4/13
     **/
    Map<String,Object> updateUserStatus(UpdateUserStatusBO updateUserStatusBO);

    /**
     * 删除用户关联信息
     * @param deleteUserBO
     * @return map
     * @author:wzy
     * @date:2018/4/13
     **/
    Map<String,Object> deleteUserRelevance(DeleteUserBO deleteUserBO);

    /**
     * 删除用户信息，包括org
     * @param orgDeleteUserBO
     * @return map
     * @author:wzy
     * @date:2018/4/13
     **/
    Map<String,Object> deleteUser(OrgDeleteUserBO orgDeleteUserBO);

    /**
     * 分页获取用户列表
     * @author  wzy
     * @param
     * @return 
     * @date  2018/4/13 15:20
     **/
    Map<String,Object> pageGetUserRelevance(PageGetUserBO pageGetUserBO);

    /**
     * 保存用户信息新增/修改
     * @author  wzy
     * @param
     * @return
     * @date  2018/4/16 9:38
     **/
    Map<String,Object> saveUser(SaveUserBO saveUserBO);

    /**
     * 获取公司所有部门信息
     * @author  wzy
     * @param orgGetDepartmentBO
     * @return  map
     * @date  2018/4/16 11:43
     **/
    Map<String,Object> getAllDepartment(OrgGetDepartmentBO orgGetDepartmentBO);

    /**
     * 获取公司所有职务信息
     * @author  wzy
     * @param orgGetDepartmentBO
     * @return map
     * @date  2018/4/17 9:37
     **/
    Map<String,Object> getUjob(OrgGetDepartmentBO orgGetDepartmentBO);

    /**
     * 获取公司所有职位信息
     * @author  wzy
     * @param orgGetPositionBO
     * @return map
     * @date  2018/4/17 9:38
     **/
    Map<String,Object> getPosition(OrgGetPositionBO orgGetPositionBO);

    /**
     * 获取个人部门职位信息和全部信息
     * @author  wzy
     * @param OrgGetUserInfoById
     * @return map
     * @date  2018/4/17 9:58
     **/
    Map<String,Object> getDepartAndJob(OrgGetUserInfoById OrgGetUserInfoById);

    /**
     * 获取角色列表，根据id获取或者获取全部
     * @author  wzy
     * @param roleTypeBO
     * @return  map
     * @date  2018/4/16 16:20
     **/
    Map<String,Object> roleList(RoleTypeBO roleTypeBO);

    /**
     * 获取用户关联仓库信息
     * @author  wzy
     * @param getUserWarehouse
     * @return  \
     * @date  2018/4/17 11:09
     **/
    Map<String,Object> getUserWarehouse(DeleteUserBO getUserWarehouse);
}
