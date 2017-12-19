package com.djcps.wms.commons.request;

import com.djcps.wms.commons.config.ParamsConfig;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 高德地图api httpRequest
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月18日
 */
@RPCClientFields(urlfield = "MAP_SERVER", urlbean = ParamsConfig.class)
public interface MapHttpRequest {

	/**
	 * 根据经纬度获取地理编码
	 * @param key
	 * @param location
	 * @param output
	 * @return
	 * @author:zdx
	 * @date:2017年12月18日
	 */
	@GET("geocode/regeo?")
	HTTPResponse getRecommendLoca(@Query("key")String key,@Query("location")String location,@Query("output")String output);

}
