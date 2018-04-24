package com.djcps.wms.inneruser.request;

import com.djcps.wms.commons.config.ParamsConfig;
import retrofit2.http.*;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

import java.util.Map;

/**
 * 用户管理ORGrequest请求
 * @author:wzy
 * @date:2018/4/12
 **/
@RPCClientFields(urlfield = "ORG_SERVER", urlbean = ParamsConfig.class)
public interface UserRequest {
    /**
     * org根据批量用户id获取其用户基本信息(删除用户也可查询出来)
     * @author  wzy
     * @param
     * @return
     * @date  2018/4/12 11:26
     **/
    @GET("userInfoFromUserIDS.org")
    HTTPResponse getUserInfoList(@QueryMap Map<String, Object> map);

    /**
     * org获取该用户的信息(包括角色组织用户全部信息)
     * @author  wzy
     * @param
     * @return
     * @date  2018/4/12 14:36
     **/
    @GET("useridAll.org")
    HTTPResponse getUserInfo(@QueryMap Map<String, Object> map);

    /**
     * org打开/禁用用户
     * @author  wzy
     * @param
     * @return
     * @date  2018/4/13 13:31
     **/
    @FormUrlEncoded
    @POST("updateCloseOpenUser.org")
    HTTPResponse updateCloseOpenUser(@FieldMap Map<String, Object> map);

    /**
     * org获取公司所有的部门和职务
     * @author  wzy
     * @param map
     * @return HTTPResponse
     * @date  2018/4/13 19:47
     **/
    @GET("getAllDepAndJob.org")
    HTTPResponse getDepartment(@QueryMap Map<String, Object> map);

    /**
     * 获取公司部门列表
     * @author  wzy
     * @param map
     * @return HTTPResponse
     * @date  2018/4/16 17:22
     **/
    @GET("getDepartList.org")
    HTTPResponse getDepartList(@QueryMap Map<String, Object> map);

    /**
     *获取公司职务列表
     * @author  wzy
     * @param map
     * @return http
     * @date  2018/4/16 17:25
     **/
    @GET("getUjob.org")
    HTTPResponse getUjob(@QueryMap Map<String, Object> map);

   /**
    * 获取公司职位列表
    * @author  wzy
    * @param map
    * @return http
    * @date  2018/4/17 9:30
    **/
    @GET("queryPosition.org")
    HTTPResponse queryPosition(@QueryMap Map<String, Object> map);

    /**
     * org新增用户信息
     * @author  wzy
     * @param map
     * @return HTTPResponse
     * @date  2018/4/13 20:01
     **/
    @FormUrlEncoded
    @POST("addPostUserInfo.org")
    HTTPResponse addPostUserInfo(@FieldMap Map<String, Object> map);

   /**
    * org修改保存用户信息
    * @author  wzy
    * @param
    * @return
    * @date  2018/4/16 9:46
    **/
    @FormUrlEncoded
    @POST("updateUserManage.org")
    HTTPResponse updateUserManage(@FieldMap Map<String, Object> map);

}
