package com.djcps.wms.inneruser.server;

import com.alibaba.fastjson.JSONArray;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.base.BaseVO;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.inneruser.model.userparam.*;
import com.djcps.wms.inneruser.request.UserRequest;
import com.djcps.wms.inneruser.request.WmsForUserRequest;
import com.djcps.wms.stocktaking.model.orderresult.OrderResult;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import rpc.plugin.http.HTTPResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * @author:wzy
 * @date:2018/4/12
 **/
@Repository
public class UserServer {
    private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(UserServer.class);

    @Autowired
    private UserRequest userRequest;

    @Autowired
    private WmsForUserRequest wmsForUserRequest;

    Gson gson=new Gson();

    /**
     * 从org批量根据用户id获取用户基本信息
     * @author  wzy
     * @param
     * @return 
     * @date  2018/4/12 14:06
     **/
    public List<OrgUserInfoPO>  getUserListByOrg(OrgGetUserInfoByIds orgGetUserInfo){
        String json = gson.toJson(orgGetUserInfo);
        Map<String,Object> map=gson.fromJson(json,Map.class);
        HTTPResponse httpResponse = userRequest.getUserInfoList(map);
        List<OrgUserInfoPO> list=null;
        HttpResult result = null;
        if (httpResponse.isSuccessful()){
            result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if(!ObjectUtils.isEmpty(result)){
                String data = gson.toJson(result.getData());
                list=new ArrayList<>();
                list=JSONArray.parseArray(data,OrgUserInfoPO.class);
            }
        }
        return list;
    }

    /**
     * 从org获取该用户的全部信息
     * @author  wzy
     * @param
     * @return
     * @date  2018/4/12 14:39
     **/
    public OrgUserInfoPO  getUserByOrg(OrgGetUserInfoById orgGetUserInfo){
        String json = gson.toJson(orgGetUserInfo);
        Map<String,Object> map=gson.fromJson(json,Map.class);
        HTTPResponse httpResponse = userRequest.getUserInfo(map);
        HttpResult result = null;
        OrgUserInfoPO orgUserInfoPO=null;
        List<OrgUserInfoPO> list=null;
        if (httpResponse.isSuccessful()){
            result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if(!ObjectUtils.isEmpty(result)){
                String data = gson.toJson(result.getData());
                orgUserInfoPO=new OrgUserInfoPO();
                list= JSONArray.parseArray(data,OrgUserInfoPO.class);
                orgUserInfoPO=list.get(0);
            }
        }
        return orgUserInfoPO;
    }

    /**
     * org开启/禁用用户
     * @author  wzy
     * @param orgDeleteUserBO
     * @return bool
     * @date  2018/4/13 13:36
     **/
    public Boolean updateCloseOpenUser(OrgDeleteUserBO orgDeleteUserBO){
        String json = gson.toJson(orgDeleteUserBO);
        Map<String,Object> map=gson.fromJson(json,Map.class);
        HTTPResponse httpResponse = userRequest.updateCloseOpenUser(map);
        HttpResult result = null;
        List<OrgUserInfoPO> list=null;
        if (httpResponse.isSuccessful()){
            String body = httpResponse.getBodyString();
            if (StringUtils.isNotBlank(body)) {
                HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                if (baseResult.isSuccess()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * wms获取单条用户关联信息
     * @author  wzy
     * @param
     * @return
     * @date  2018/4/13 10:32
     **/
    public UserRelevanceBO  getUserRelevance(DeleteUserBO deleteUserBO){
        String paramJson = gson.toJson(deleteUserBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.getUserRelevance(requestBody);
        UserRelevanceBO userRelevanceBO=null;
        HttpResult result = null;
        if (httpResponse.isSuccessful()){
            result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if(!ObjectUtils.isEmpty(result)){
                String data = gson.toJson(result.getData());
                userRelevanceBO=new UserRelevanceBO();
                userRelevanceBO=gson.fromJson(data,UserRelevanceBO.class);
            }
        }
        return userRelevanceBO;
    }

    /**
     * wms修改用户工作状态等信息
     * @author  wzy
     * @param updateUserStatusBO
     * @return HTTPResponse
     * @date  2018/4/13 9:31
     **/
    public HttpResult  updateUserStatus(UpdateUserStatusBO updateUserStatusBO){
        String paramJson = gson.toJson(updateUserStatusBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.updateUserStatus(requestBody);
        return returnResult(httpResponse);
    }

    /**
     * wms删除用户关联信息
     * @author  wzy
     * @param deleteUserBO
     * @return result
     * @date  2018/4/13 11:18
     **/
    public HttpResult  deleteUserRelevance(DeleteUserBO deleteUserBO){
        String paramJson = gson.toJson(deleteUserBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.deleteUserRelevance(requestBody);
        return returnResult(httpResponse);
    }

    /**
     * wms新增用户关联信息
     * @author  wzy
     * @param userRelevanceBO
     * @return http
     * @date  2018/4/16 10:15
     **/
    public HttpResult  insertUserRelevance(UserRelevanceBO userRelevanceBO){
        String paramJson = gson.toJson(userRelevanceBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.insertUserRelevance(requestBody);
        return returnResult(httpResponse);
    }

    /**
     * wms新增用户关联仓库信息
     * @author  wzy
     * @param userRelevanceBO
     * @return http
     * @date  2018/4/16 11:40
     **/
    public HttpResult  insertUserWarehouse(UserRelevanceBO userRelevanceBO){
        String paramJson = gson.toJson(userRelevanceBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.insertUserWarehouse(requestBody);
        return returnResult(httpResponse);
    }

    /**
     * WMS条件获取用户列表
     * @author  wzy
     * @param pageGetUserBO
     * @return OrderResult
     * @date  2018/4/13 15:17
     **/
    public OrderResult pageGetUserRelevance(PageGetUserBO pageGetUserBO){
        String paramJson = gson.toJson(pageGetUserBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.pageGetUserRelevance(requestBody);
        UserRelevanceBO userRelevanceBO=null;
        OrderResult result = null;
        if (httpResponse.isSuccessful()){
            result = gson.fromJson(httpResponse.getBodyString(), OrderResult.class);
        }
        return result;
    }

    /**
     * 从org获取该公司的所有部门信息
     * @author  wzy
     * @param orgGetDepartmentBO
     * @return OrgDepartmentPO
     * @date  2018/4/12 14:39
     **/
    public List<OrgDepartmentPO>  getDepartment(OrgGetDepartmentBO orgGetDepartmentBO){
        String json = gson.toJson(orgGetDepartmentBO);
        Map<String,Object> map=gson.fromJson(json,Map.class);
        HTTPResponse httpResponse = userRequest.getDepartList(map);
        HttpResult result = null;
        List<OrgDepartmentPO> list=null;
        if (httpResponse.isSuccessful()){
            result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if(!ObjectUtils.isEmpty(result)){
                String data = gson.toJson(result.getData());
                list= JSONArray.parseArray(data,OrgDepartmentPO.class);
            }
        }
        return list;
    }

    /**
     * 从org获取该公司的所有职务信息
     * @author  wzy
     * @param
     * @return
     * @date  2018/4/17 9:25
     **/
    public List<OrgUjobPO>  getUjob(OrgGetDepartmentBO orgGetDepartmentBO){
        String json = gson.toJson(orgGetDepartmentBO);
        Map<String,Object> map=gson.fromJson(json,Map.class);
        HTTPResponse httpResponse = userRequest.getUjob(map);
        HttpResult result = null;
        //OrgDepartmentPO orgDepartmentBO=null;
        List<OrgUjobPO> list=null;
        if (httpResponse.isSuccessful()){
            result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if(!ObjectUtils.isEmpty(result)){
                String data = gson.toJson(result.getData());
                list= JSONArray.parseArray(data,OrgUjobPO.class);
            }
        }
        return list;
    }

    public List<OrgPositionPO>  getPosition(OrgGetPositionBO orgGetPositionBO){
        String json = gson.toJson(orgGetPositionBO);
        Map<String,Object> map=gson.fromJson(json,Map.class);
        HTTPResponse httpResponse = userRequest.queryPosition(map);
        HttpResult result = null;
        List<OrgPositionPO> list=null;
        if (httpResponse.isSuccessful()){
            result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if(!ObjectUtils.isEmpty(result)){
                String data = gson.toJson(result.getData());
                list= JSONArray.parseArray(data,OrgPositionPO.class);
            }
        }
        return list;
    }


    /**
     * org修改保存用户信息
     * @author  wz y
     * @param saveUserBO
     * @return  bool
     * @date  2018/4/16 9:50
     **/
    public Boolean updateUserManage(SaveUserBO saveUserBO){
        String json = gson.toJson(saveUserBO);
        Map<String,Object> map=gson.fromJson(json,Map.class);
        HTTPResponse httpResponse = userRequest.updateUserManage(map);
        if (httpResponse.isSuccessful()){
            String body = httpResponse.getBodyString();
            if (StringUtils.isNotBlank(body)) {
                HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                if (baseResult.isSuccess()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * org新增用户并返回用户信息
     * @author  wzy
     * @param saveUserBO
     * @return saveUserBO
     * @date  2018/4/16 9:58
     **/
    public SaveUserBO  addPostUserInfo(SaveUserBO saveUserBO){
        String json = gson.toJson(saveUserBO);
        Map<String,Object> map=gson.fromJson(json,Map.class);
        HTTPResponse httpResponse = userRequest.addPostUserInfo(map);
        HttpResult result = null;
        SaveUserBO saveUserBO1=null;
        List<SaveUserBO> list=null;
        if (httpResponse.isSuccessful()){
            result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if(!ObjectUtils.isEmpty(result)){
                String data = gson.toJson(result.getData());
                saveUserBO1=new SaveUserBO();
                list= JSONArray.parseArray(data,SaveUserBO.class);
                saveUserBO1=list.get(0);
            }
        }
        return saveUserBO1;
    }

    /**
     * 根绝角色类型编码获取角色列表
     * @author  wzy
     * @param
     * @return
     * @date  2018/4/16 15:54
     **/
    public List<GetRoleTypePO> roleList(RoleTypeBO getRoleTypeBO){
        String json = gson.toJson(getRoleTypeBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        HTTPResponse httpResponse = wmsForUserRequest.roleList(requestBody);
        BaseVO baseVO = null;
        List<GetRoleTypePO> roleTypePOList=null;
        HttpResult result=null;
        if (httpResponse.isSuccessful()){
            result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if(!ObjectUtils.isEmpty(result)){
                String data = gson.toJson(result.getData());
                if(!ObjectUtils.isEmpty(data)){
                    baseVO=gson.fromJson(data,BaseVO.class);
                    if(!ObjectUtils.isEmpty(baseVO)){
                         roleTypePOList=baseVO.getResult();
                        return roleTypePOList;
                    }
                }
            }
        }
        return roleTypePOList;
    }

    /**
     * 获取用户关联仓库信息
     * @author  wzy
     * @param getUserWarehouse
     * @return /
     * @date  2018/4/17 11:08
     **/
    public  List<String> getUserWarehouse(DeleteUserBO getUserWarehouse){
        String paramJson = gson.toJson(getUserWarehouse);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.getUserWarehouse(requestBody);
        List<String> warehouseList=null;
        HttpResult result=null;
        if (httpResponse.isSuccessful()){
            result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            String json=gson.toJson(result.getData());
            warehouseList= JSONArray.parseArray(json,String.class);
        }
        return warehouseList;
    }


    /**
     * 公共返回
     *
     * @param httpResponse
     * @return
     */
    private HttpResult returnResult(HTTPResponse httpResponse) {
        if (httpResponse.isSuccessful()) {
            try {
                String body = httpResponse.getBodyString();
                if (StringUtils.isNotBlank(body)) {
                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    return baseResult;
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }
}
