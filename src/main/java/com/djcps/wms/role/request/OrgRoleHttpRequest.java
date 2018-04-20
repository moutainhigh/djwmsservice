package com.djcps.wms.role.request;

import java.util.Map;

import com.djcps.wms.commons.config.ParamsConfig;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * @title:OR用户角色http请求
 * @author:wyb
 * @company:djwms
 * @create:2018/4/12
 **/
@RPCClientFields(urlfield = "ORG_SERVER", urlbean = ParamsConfig.class)
public interface OrgRoleHttpRequest {
    /**
     * 获取角色列表接口
     *
     * @param json
     * @return
     * @author wyb
     * @create 2018/4/12
     **/
    @GET("getRolePerInfo.org")
    HTTPResponse getRolePerInfo(@QueryMap Map<String,Object> map);
    /**
     * 修改角色信息
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("updatePostRoleManage.org") 
    HTTPResponse updatePostRoleManage(@FieldMap Map<String,Object> map);
    /**
     * 新增角色信息
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("addPostRoleManage.org") 
    HTTPResponse addPostRoleManage(@FieldMap Map<String,Object> map);
    /**
     * 删除角色信息
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("delRoleManage.org")
    HTTPResponse delRoleManage(@FieldMap Map<String,Object> map);
}
