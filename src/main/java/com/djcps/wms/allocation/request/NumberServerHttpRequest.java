package com.djcps.wms.allocation.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 编号服务
 * @company:djwms
 * @author:zdx
 * @date:2018年1月22日
 */
@RPCClientFields(urlfield = "NUMBER_SERVER", urlbean = ParamsConfig.class)
public interface NumberServerHttpRequest {
	/**
	 * 获取订单类型枚举
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	@Headers("content-type:application/x-www-form-urlencoded")
	@POST("getnumber.do")
	public HTTPResponse getNumber(@Body RequestBody rb);
}
