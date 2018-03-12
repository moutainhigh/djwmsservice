package com.djcps.wms.outLocation.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

@RPCClientFields(urlfield="ORDER_SERVER", urlbean=ParamsConfig.class)
public interface OrderDetailHttpRequest {
	/**
	 * 获取订单明细
	 * @param rb
	 * @return
	 */
	@Headers("content-type:application/json")
	@POST("order/getInfoByChildId.do")
	public HTTPResponse getOrderDetailByOrderId(@Body RequestBody rb);
}
