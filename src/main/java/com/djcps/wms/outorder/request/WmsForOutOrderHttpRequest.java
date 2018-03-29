package com.djcps.wms.outorder.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;
/**
 * 向wms服务端获取信息
 * @author ldh
 *
 */
@RPCClientFields(urlfield="WMS_SERVER",urlbean=ParamsConfig.class)
public interface WmsForOutOrderHttpRequest {
	/**
	 * 根据出库单号获取订单编号
	 * @param rb
	 * @return
	 */
	@Headers("content-type:application/json")
	@POST("outOrder/getOrderList.do")
	HTTPResponse getOrderIdsByOutOrderId(@Body RequestBody rb);
	
	/**
	 * 获取出库单中的所有数据
	 * @param rb
	 * @return
	 */
	@Headers("content-type:application/json")
	@POST("outOrder/selectOutOrder.do")
	HTTPResponse getAllOutOrder(@Body RequestBody rb);
	
	/**
	 * 根据出库单编号获取一条出库单数据
	 * @param rb
	 * @return
	 */
	@Headers("content-type:application/json")
	@POST("")
	HTTPResponse getOutOrderByOutOrderId(@Body RequestBody rb);
	
	/**
	 * 根据出库单编号更新出库单状态和打印次数
	 * @param rb
	 * @return
	 */
	@Headers("content-type:application/json")
	@POST("outOrder/updateOutOrder.do")
	HTTPResponse updateOutOrderByOutOrderId(@Body RequestBody rb);
	
}
