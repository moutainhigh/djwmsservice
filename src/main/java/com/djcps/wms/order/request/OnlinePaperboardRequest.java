package com.djcps.wms.order.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 线上纸板订单
 * @company:djwms
 * @author:zdx
 * @date:2018年4月12日
 */
@RPCClientFields(urlfield = "ONLINE_PAPERBOARD_SERVER", urlbean = ParamsConfig.class)
public interface OnlinePaperboardRequest {
	
	/**
	 * 获取线上纸板订单
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年4月13日
	 */
	@Headers("content-type:application/json")
	@POST("oms/loadOnlinePaperBoard.do")
	public HTTPResponse getOnlinePaperboardList(@Body RequestBody rb);
	
	/**
	 * 批量修改子单状态
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年4月13日
	 */
	@Headers("content-type:application/json")
	@POST("oms/updateOnlineBoardsStatus.do")
	public HTTPResponse updateOrderInfo(@Body RequestBody rb);
	
	/**
	 * 批量修改拆单数据信息
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年4月19日
	 */
	@Headers("content-type:application/json")
	@POST("oms/updateSplitOrdersInfo.do")
	public HTTPResponse updateSplitOrderInfo(@Body RequestBody rb);

	/**
	 * 根据订单号获取订单详情（支持批量/包含拆单）
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年4月13日
	 */
	@Headers("content-type:application/json")
	@POST("oms/orderInfoByIds.do")
	public HTTPResponse getOrderDeatilByIdList(@Body RequestBody rb);

	/**
	 * 根据子单号获取拆单信息
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年4月20日
	 */
	@Headers("content-type:application/json")
	@POST("oms/loadSplitOrderByOrderIds.do")
	public HTTPResponse getSplitOrderDeatilByI(@Body RequestBody rb);
	
	/**
	 * 拆分订单接口
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年4月25日
	 */
	@Headers("content-type:application/json")
	@POST("oms/updateSplitOrderStatus.do")
	public HTTPResponse splitOrder(@Body RequestBody rb);
	
}
