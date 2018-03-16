package com.djcps.wms.outorder.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;
/**
 * 向订单服务获取订单明细
 * @author ldh
 *
 */
@RPCClientFields(urlfield="ORDER_SERVER",urlbean=ParamsConfig.class)
public interface OrderDetailHttpRequest {
	/**
	 * 根据订单编号获取订单明细列表
	 * @param rb
	 * @return
	 */
	@Headers("content-type:application/json")
	@POST("order/getInfoByChildId.do")
	public HTTPResponse getOrderDetailByOrderId(@Body RequestBody rb);
}
