package com.djcps.wms.order.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;


/**
 * 订单http请求
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
@RPCClientFields(urlfield = "ORDER_SERVER", urlbean = ParamsConfig.class)
public interface WmsForOrderHttpRequest {
	
	/**
	 * 获取订单详情
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月29日
	 */
	@Headers("content-type:application/json")
	@POST("order/load.do")
	public HTTPResponse getAllOrderList(@Body RequestBody rb);

	/**
	 * 根据订单号获取订单详情
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月29日
	 */
	@Headers("content-type:application/json")
	@POST("order/getInfoByChildId.do")
	public HTTPResponse getOrderByOrderId(@Body RequestBody rb);
	
}
