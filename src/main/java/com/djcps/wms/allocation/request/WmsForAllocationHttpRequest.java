package com.djcps.wms.allocation.request;



import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 混合配货http请求接口
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsForAllocationHttpRequest {
	
	/**
	 * 获取订单类型枚举
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/list.do")
	public HTTPResponse getOrderType(@Body RequestBody rb);
	
	/**
	 * 获取已选择的混合配货
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/information.do")
	public HTTPResponse getChooseAllocation(@Body RequestBody rb);
	
	/**
	 * 保存已混合配货
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/save.do")
	public HTTPResponse saveAllocation(@Body RequestBody rb);

	/**
	 * 根据仓库编码获取库存中的订单号
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/getOrderIdByWarsehouseId.do")
	public HTTPResponse getOrderIdByWarsehouseId(@Body RequestBody rb);
	
	/**
	 * 根据订单号获取订单详情
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/getOrderInfoByOrderId.do")
	public HTTPResponse getOrderInfoByOrderId(@Body RequestBody rb);

	/**
	 * 根据提货单号获取运单信息
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/getOrderInfoByOrderId.do")
	public HTTPResponse getWaybillByDeliveryId(@Body RequestBody rb);

	/**
	 * 根据提货单号,effect等于2,查询提货单和关联的订单信息
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/getIntelligentAllocaList.do")
	public HTTPResponse getIntelligentAllocaList(@Body RequestBody rb);

	/**
	 * 智能配货接口
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/zhinengpeihuo.do")
	public HTTPResponse zhinengpeihuo(@Body RequestBody rb);

	/**
	 * 确认配货
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/verifyAllocation.do")
	public HTTPResponse verifyAllocation(@Body RequestBody rb);

	/**
	 * 移除订单
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/moveOrder.do")
	public HTTPResponse moveOrder(@Body RequestBody rb);

	/**
	 * 确认追加订单
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/verifyAddOrder.do")
	public HTTPResponse verifyAddOrder(@Body RequestBody rb);

	/**
	 * 配货管理查询
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/getAllocationManageList.do")
	public HTTPResponse getAllocationManageList(@Body RequestBody rb);

	/** 
	 * 装车优化
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/getExcellentLoding.do")
	public HTTPResponse getExcellentLoding(@Body RequestBody rb);

	/**
	 * 装车优化确认追加订单
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/againVerifyAddOrder.do")
	public HTTPResponse againVerifyAddOrder(@Body RequestBody rb);

	/**
	 * 装车优化再次确认配货
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/againVerifyAllocation.do")
	public HTTPResponse againVerifyAllocation(@Body RequestBody rb);

	/**
	 * 车辆确认更换
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/changeCarInfo.do")
	public HTTPResponse changeCarInfo(@Body RequestBody rb);

	/**
	 * 取消配货
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/cancelAllocation.do")
	public HTTPResponse cancelAllocation(@Body RequestBody rb);

	/**
	 * 获取所有的车辆信息
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@Headers("content-type:application/json")
	@POST("allocation/getAllUserCarInfo.do")
	public HTTPResponse getAllUserCarInfo();

	/**
	 * 根据运单号获取提货单详情
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@Headers("content-type:application/json")
	@POST("delivery/getDeliveryByWaybillId.do")
	public HTTPResponse getDeliveryByWaybillId(@Body RequestBody rb);

}
