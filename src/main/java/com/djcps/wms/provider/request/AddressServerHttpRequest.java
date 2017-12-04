package com.djcps.wms.provider.request;

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
@RPCClientFields(urlfield = "ADDRESS_SERVER", urlbean = ParamsConfig.class)
public interface AddressServerHttpRequest {
	
	/**
	 * 获取所有城市的列表
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月4日
	 */
	@Headers("content-type:application/json")
	@POST("allArea/findAllProvince.do")
	public HTTPResponse getProvinceAllList(@Body RequestBody rb);
	
	/**
	 * 根据省份获取所有的城市列表
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月4日
	 */
	@Headers("content-type:application/json")
	@POST("allArea/findAllCity.do")
	public HTTPResponse getCityListByProvince(@Body RequestBody rb);
	
	/**
	 * 根据城市获取所有区的列表
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月4日
	 */
	@Headers("content-type:application/json")
	@POST("allArea/findAllArea.do")
	public HTTPResponse getAreaListByCity(@Body RequestBody rb);
}
