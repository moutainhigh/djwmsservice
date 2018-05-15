package com.djcps.wms.permission.request;


import com.djcps.wms.commons.config.ParamsConfig;
import retrofit2.http.*;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

import java.util.Map;

/**
 * @author zhq
 * 向or调取服务
 * 2018年4月12日
 */
@RPCClientFields(urlfield="ORG_SERVER",urlbean=ParamsConfig.class)
public interface DjorForPermissionHttpRequest {
	/**
	 * 获取组合权限的数据
	 * @param map
	 * @return
	 */
	@GET("getPerManageList.org")
	HTTPResponse getPermissionList(@QueryMap Map<String,Object> map);
	
	/**
	 * 获取WMS权限
	 * @param map
	 * @return
	 */
	@GET("getPerBase.org")
	HTTPResponse getWmsPermission(@QueryMap Map<String,Object> map);
	
	/**
	 * 新增权限包
	 * @param map
	 * @return
	 */
	@FormUrlEncoded
	@POST("addPostPerManage.org")
	HTTPResponse insertPermission(@FieldMap Map<String,Object> map);
	
	/**
	 * 修改权限包
	 * @param map
	 * @return
	 */
	@FormUrlEncoded
	@POST("updatePostPerManage.org")
	HTTPResponse updatePermission(@FieldMap Map<String,Object> map);
	
	/**
	 * 删除权限包
	 * @param map
	 * @return
	 */
	@FormUrlEncoded
	@POST("delPerManage.org")
	HTTPResponse deletePermission(@FieldMap Map<String,Object> map);
	
	/**
	 * 根据权限id获取用户信息
	 * @param map
	 * @return
	 */
	@GET("getPerToUser.org")
	HTTPResponse getUserByPermissionId(@QueryMap Map<String,Object> map);
	
	/**
	 * 根据组合权限id和公司id，获取获取组合权限集合
	 * @param map
	 * @return
	 */
	@GET("getPerChoose.org")
	HTTPResponse getPerChoose(@QueryMap Map<String,Object> map);

	/**
	 * 根据用户id获取用户所有权限列表
	 * @author Chengw
	 * @since 2018/4/23  14:27
	 * @param map
	 * @return
	 */
	@GET("getUserToPer.org")
	HTTPResponse getUserPermission(@QueryMap Map<String,Object> map);
}
