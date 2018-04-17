package com.djcps.wms.role.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * @title:用户角色http请求
 * @author:wyb
 * @company:djwms
 * @create:2018/4/12
 **/
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface RoleHttpRequest {
    /**
     * 获取角色关联信息列表接口
     *
     * @param json
     * @return
     * @author wyb
     * @create 2018/4/12
     **/
    @Headers("content-type:application/json")
    @POST("role/list.do")
    HTTPResponse list(@Body RequestBody json);
    /**
     * 更新角色关联信息列表接口
     *
     * @param json
     * @return
     * @author wyb
     * @create 2018/4/12
     **/
    @Headers("content-type:application/json")
    @POST("role/update.do")
    HTTPResponse update(@Body RequestBody json);
    /**
     * 删除角色关联信息接口
     *
     * @param json
     * @return
     * @author wyb
     * @create 2018/4/12
     **/
    @Headers("content-type:application/json")
    @POST("role/delete.do")
    HTTPResponse delete(@Body RequestBody json);
    /**
     * 新增角色关联信息接口
     *
     * @param json
     * @return
     * @author wyb
     * @create 2018/4/12
     **/
    @Headers("content-type:application/json")
    @POST("role/save.do")
    HTTPResponse save(@Body RequestBody json);
    /**
     * 根据角色类型批量获取用户状态等信息
     *
     * @param json
     * @return
     * @author wyb
     * @create 2018/4/12
     **/
    @Headers("content-type:application/json")
    @POST("user/getUserStatusList")
    HTTPResponse getUserStatusList(@Body RequestBody json);
    
}
