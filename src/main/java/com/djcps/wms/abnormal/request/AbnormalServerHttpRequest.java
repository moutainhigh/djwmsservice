package com.djcps.wms.abnormal.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 地址服务http请求接口
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface AbnormalServerHttpRequest {
	
	/**
	 * 根据订单号批量查询获取异常订单信息
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	@Headers("content-type:application/json")
	@POST("abnormalOrder/batchSelectOrder.do")
	public HTTPResponse getOrderByOrderIdList(@Body RequestBody rb);

	/**
	 * 新增异常订单表
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	@Headers("content-type:application/json")
	@POST("abnormalOrder/saveAbnormal.do")
	public HTTPResponse addAbnormal(@Body RequestBody rb);

	/**
	 * 异常订单查询(包含查询条件)
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	@Headers("content-type:application/json")
	@POST("abnormalOrder/selectOrderByCondition.do")
	public HTTPResponse getOrderByAttributeBO(@Body RequestBody rb);

	/**
	 * 修改异常订单信息
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	@Headers("content-type:application/json")
	@POST("abnormalOrder/updateOrder.do")
	public HTTPResponse updateAbnormal(@Body RequestBody rb);
	
	/**
	 * 根据订单号获取拆分订单信息
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	@Headers("content-type:application/json")
	@POST("abnormalOrder/selectFissplitOrder.do")
	public HTTPResponse getSplitOrderByOrderId(@Body RequestBody rb);
	
}
