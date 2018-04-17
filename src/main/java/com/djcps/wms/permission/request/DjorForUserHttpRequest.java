package com.djcps.wms.permission.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * @author zhq
 * 调取用户信息请求
 * 2018年4月16日
 */
@RPCClientFields(urlfield="WMS_SERVER",urlbean=ParamsConfig.class)
public interface DjorForUserHttpRequest {
	/**
	 * 查询用户是否处于工作状态
	 * @param rb
	 * @return
	 */
	@Headers("content-type:application/json")
	@POST("user/getUserRelevance.do")
	HTTPResponse getUserInfo(@Body RequestBody rb);
}
