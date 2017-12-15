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
	
}
