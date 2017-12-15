package com.djcps.wms.loadingtable.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 装车台http请求接口
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsForLoadingTableHttpRequest {
	
	/**
	 * 装车台新增http接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("loadingTable/save.do")
	public HTTPResponse add(@Body RequestBody json);
	
	/**
	 * 装车台修改http接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
    @POST("loadingTable/modify.do")
	public HTTPResponse modify(@Body RequestBody json);
	
	/**
	 * 装车台删除http接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("loadingTable/delete.do")
	public HTTPResponse delete(@Body RequestBody json);
	
	/**
	 * 获取所有装车台http接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("loadingTable/list.do")
	public HTTPResponse getAllList(@Body RequestBody json);
	
	/**
	 * 根据装车台属性模糊查询http接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("loadingTable/search.do")
	public HTTPResponse getLoadingTableByAttribute(@Body RequestBody json);
	
	/**
	 * 根据装车台编号获取装车台信息http接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("loadingTable/getById.do")
	public HTTPResponse getLoadingTableById(@Body RequestBody json);
	
	/**
	 * 启用装车台http接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("loadingTable/enable.do")
	public HTTPResponse enable(@Body RequestBody json);
	
	/**
	 * 禁用装车台http接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("loadingTable/disable.do")
	public HTTPResponse disable(@Body RequestBody json);
}
