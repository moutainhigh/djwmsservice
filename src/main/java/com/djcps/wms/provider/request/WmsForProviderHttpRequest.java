package com.djcps.wms.provider.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 供应商http请求接口
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsForProviderHttpRequest {
	/**
	 * 供应商新增http接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("provider/save.do")
	public HTTPResponse add(@Body RequestBody json);
	
	/**
	 * 供应商修改http接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
    @POST("provider/modify.do")
	public HTTPResponse modify(@Body RequestBody json);
	
	/**
	 * 供应商删除http接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("provider/delete.do")
	public HTTPResponse delete(@Body RequestBody json);
	
	/**
	 * 获取所有供应商信息http接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("provider/list.do")
	public HTTPResponse getAllList(@Body RequestBody json);
	
	/**
	 * 根据供应商属性模糊查询获取供应商信息http接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("provider/search.do")
	public HTTPResponse getProviderByAttribute(@Body RequestBody json);
	
	
	/**
	 * 获取所有城市的列表
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月4日
	 */
	@Headers("content-type:application/json")
	@POST("provider/search.do")
	public HTTPResponse getProvinceAllList(@Body RequestBody rb);
	
	/**
	 * 根据省份获取所有的城市列表
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月4日
	 */
	@Headers("content-type:application/json")
	@POST("provider/search.do")
	public HTTPResponse getCityListByProvince(@Body RequestBody rb);
	
	/**
	 * 根据城市获取所有区的列表
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月4日
	 */
	@Headers("content-type:application/json")
	@POST("provider/search.do")
	public HTTPResponse getAreaListByCity(@Body RequestBody rb);

}
