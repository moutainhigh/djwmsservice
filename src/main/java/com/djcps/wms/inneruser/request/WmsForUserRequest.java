package com.djcps.wms.inneruser.request;

import com.djcps.wms.commons.config.ParamsConfig;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * WMS用户管理request请求
 * @author wzy
 * @date 2018/4/13
 **/
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsForUserRequest {

    /**
     * 修改用户工作状态等信息
     * @author  wzy
     * @param json RequestBody
     * @return HTTPResponse
     * @date  2018/4/13 9:31
     **/
    @Headers("content-type:application/json")
    @POST("user/updateUserRelevance.do")
    HTTPResponse updateUserRelevance(@Body RequestBody json);

    /**
     * 获取单条用户关联信息
     * @author  wzy
     * @param json RequestBody
     * @return HTTPResponse
     * @date  2018/4/13 10:18
     **/
    @Headers("content-type:application/json")
    @POST("user/getUserRelevance.do")
    HTTPResponse getUserRelevance(@Body RequestBody json);

    /**
     * WMS删除用户关联系信息
     * @author  wzy
     * @param json RequestBody
     * @return http
     * @date  2018/4/13 14:51
     **/
    @Headers("content-type:application/json")
    @POST("user/deleteUser.do")
    HTTPResponse deleteUserRelevance(@Body RequestBody json);

    /**
     * WMS条件获取用户相关信息分页
     * @author  wzy
     * @param json RequestBody
     * @return http
     * @date  2018/4/13 14:53
     **/
    @Headers("content-type:application/json")
    @POST("user/pageUserRelevance.do")
    HTTPResponse pageUserRelevance(@Body RequestBody json);

    /**
     * WMS新增用户关联信息
     * @author  wzy
     * @param json RequestBody
     * @return http
     * @date  2018/4/16 10:11
     **/
    @Headers("content-type:application/json")
    @POST("user/saveUserRelevance.do")
    HTTPResponse saveUserRelevance(@Body RequestBody json);

    /**
     * 批量获取用户关联仓库
     * @author  wzy
     * @param json RequestBody
     * @return  http
     * @date  2018/4/17 11:03
     **/
    @Headers("content-type:application/json")
    @POST("user/listUserWarehouse.do")
    HTTPResponse listUserWarehouse(@Body RequestBody json);

    /**
     * 根据角色类型 获取用户信息
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("user/listUserByRoleCode.do")
    HTTPResponse listUserByRoleCode(@Body RequestBody json);

}
