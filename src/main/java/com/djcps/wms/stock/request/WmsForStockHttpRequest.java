package com.djcps.wms.stock.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 入库移库http请求接口
 * @company:djwms
 * @author:zdx
 * @date:2017年12月20日
 */
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsForStockHttpRequest {

	/**
	 * 获取推荐库位
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月20日
	 */
	@Headers("content-type:application/json")
	@POST("entry/recommendWarehouseArea.do")
	HTTPResponse getRecommendLoca(@Body RequestBody rb);

	/**
	 * 获取操作记录
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月20日
	 */
	@Headers("content-type:application/json")
	@POST("entry/operationRecordList.do")
	HTTPResponse getOperationRecord(@Body RequestBody rb);

	/**
	 * 入库
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月20日
	 */
	@Headers("content-type:application/json")
	@POST("entry/entryWarehouse.do")
	HTTPResponse addStock(@Body RequestBody rb);

	/**
	 * 移库
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月20日
	 */
	@Headers("content-type:application/json")
	@POST("entry/removeWarehouse.do")
	HTTPResponse moveStock(@Body RequestBody rb);

	/**
	 * 获取已入库的订单数量
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月20日
	 */
	@Headers("content-type:application/json")
	@POST("entry/getOrderAmountSaved.do")
	HTTPResponse getSavedStockAmount(@Body RequestBody rb);

	/**
	 * 根据订单获取库位信息
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月20日
	 */
	@Headers("content-type:application/json")
	@POST("entry/orderWarehouseLocInfo.do")
	HTTPResponse getAreaByOrderId(@Body RequestBody rb);

	/**
	 * 入库部分,根据订单号获取部分入库的拆分订单号
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年4月23日
	 */
	@Headers("content-type:application/json")
	@POST("entry/getLessStockOrderId.do")
	HTTPResponse getLessStockOrderId(@Body RequestBody rb);
}
