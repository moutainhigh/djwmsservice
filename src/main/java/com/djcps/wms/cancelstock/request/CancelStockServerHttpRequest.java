package com.djcps.wms.cancelstock.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 退库服务http请求接口
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface CancelStockServerHttpRequest {

	/**
	 * 根据订单号和退库人获取订单详情信息
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年3月20日
	 */
	@Headers("content-type:application/json")
	@POST("cancelStock/getCancelStockAttribute.do")
	public HTTPResponse getOrderByOrderId(@Body RequestBody rb);

	/**
	 * 新增库存信息
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年3月20日
	 */
	@Headers("content-type:application/json")
	@POST("cancelStock/addStock.do")
	public HTTPResponse addStock(@Body RequestBody rb);

	/**
	 * 新增退库任务
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年3月20日
	 */
	@Headers("content-type:application/json")
	@POST("cancelStock/addCancelStock.do")
	public HTTPResponse addCancelStock(@Body RequestBody rb);

	/**
	 * 根据退库人id获取退库任务
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年3月20日
	 */
	@Headers("content-type:application/json")
	@POST("cancelStock/getCancelStockByPickerId.do")
	public HTTPResponse getCancelStockByPickerId(@Body RequestBody rb);
}
