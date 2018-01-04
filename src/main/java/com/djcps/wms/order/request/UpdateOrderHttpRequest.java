package com.djcps.wms.order.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;


/**
 * 修改订单临时http请求
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
@RPCClientFields(urlfield = "UPDATE_ORDER_SERVER", urlbean = ParamsConfig.class)
public interface UpdateOrderHttpRequest {
	
	/**
	 * 修改订单状态
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月29日
	 */
	@Headers("content-type:application/json")
	@POST("order/changeOrderStatus.do")
	public HTTPResponse updateOrderStatus(@Body RequestBody rb);
	
}
