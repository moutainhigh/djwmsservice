	package com.djcps.wms.order.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 线下纸板订单
 * @company:djwms
 * @author:zdx
 * @date:2018年4月12日
 */
@RPCClientFields(urlfield = "OFFLINE_PAPERBOARD_SERVER", urlbean = ParamsConfig.class)
public interface OffinePaperboardRequest {
	
	/**
	 * 查询线下纸板订单
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年5月8日
	 */
	@Headers("content-type:application/json")
	@POST("offline/findOfflineCBPage.do")
	public HTTPResponse getOffinePaperboardList(@Body RequestBody rb);

	
	/**
	 *	查询线下纸箱订单
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年5月9日
	 */
	@Headers("content-type:application/json")
	@POST("offline/findOfflineCTPage.do")
	public HTTPResponse getOffineBoxOrderList(@Body RequestBody rb);


	/**
	 * 批量修改子单信息
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年5月9日
	 */
	@Headers("content-type:application/json")
	@POST("offline/updateOfflineOrderStatus.do")
	public HTTPResponse updateOrderInfo(@Body RequestBody rb);

	/**
	 * 修改拆单信息接口
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年5月9日
	 */
	@Headers("content-type:application/json")
	@POST("offline/updateSplitOrderInfo.do")
	public HTTPResponse updateSplitOrderInfo(@Body RequestBody rb);


	/**
	 * 拆分订单
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年5月9日
	 */
	@Headers("content-type:application/json")
	@POST("offline/updateSplitOrderStatus.do")
	public HTTPResponse splitOrder(@Body RequestBody rb);

	/**
	 * 获取线下纸板订单(订单号和拆单号混合批量查询)
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年5月9日
	 */
	@Headers("content-type:application/json")
	@POST("offline/orderCardboardInfoByIds.do")
	public HTTPResponse getOfflinePaperboardByIdList(@Body RequestBody rb);
	
	/**
	 * 获取线下纸板纸箱订单 (订单号和拆单号混合批量查询)
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年5月9日
	 */
	@Headers("content-type:application/json")
	@POST("offline/orderCartonInfoByIds.do")
	public HTTPResponse getOfflineBoxOrderByIdList(@Body RequestBody rb);

	/**
	 * 根据订单号获取拆单详情
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年5月9日
	 */
	@Headers("content-type:application/json")
	@POST("offline/loadSplitOrderByBoxOrderIds.do")
	public HTTPResponse getSplitOrderDeatilByI(@Body RequestBody rb);
	
}
