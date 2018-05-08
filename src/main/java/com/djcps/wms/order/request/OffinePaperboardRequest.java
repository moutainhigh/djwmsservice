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
	
}
