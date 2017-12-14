package com.djcps.wms.warehouse.request;


import com.djcps.wms.commons.config.ParamsConfig;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 库位管理http请求接口
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsForLocationHttpRequest {
	
	
	/**
	 * 新增库位
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	@Headers("content-type:application/json")
	@POST("warehouseLoc/save.do")
	public HTTPResponse addLocation(@Body RequestBody rb);
	
	/**
	 * 修改库位
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	@Headers("content-type:application/json")
	@POST("warehouseLoc/modify.do")
	public HTTPResponse modifyLocation(@Body RequestBody rb);
	
	/**
	 * 删除库位
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	@Headers("content-type:application/json")
	@POST("warehouseLoc/delete.do")
	public HTTPResponse deleteLocation(@Body RequestBody rb);
	
	/**
	 * 获取所有库位
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	@Headers("content-type:application/json")
	@POST("warehouseLoc/list.do")
	public HTTPResponse getLocationAllList(@Body RequestBody rb);
	
	/**
	 * 根据库位属性模糊查询
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	@Headers("content-type:application/json")
	@POST("warehouseLoc/search.do")
	public HTTPResponse getLocationByAttribute(@Body RequestBody rb);
	
}
