package com.djcps.wms.warehouse.request;


import com.djcps.wms.commons.config.ParamsConfig;
import com.sun.org.apache.regexp.internal.RE;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 仓库管理http请求接口
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsForWarehouseHttpRequest {
	
	/**
	 * 仓库管理新增http请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/save.do")
	public HTTPResponse add(@Body RequestBody json);
	
	/**
	 * 仓库管理修改http请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
    @POST("warehouse/modify.do")
	public HTTPResponse modify(@Body RequestBody json);
	
	/**
	 * 仓库管理删除http请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/delete.do")
	public HTTPResponse delete(@Body RequestBody json);
	
	/**
	 * 获取所有仓库http请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/list.do")
	public HTTPResponse getAllList(@Body RequestBody json);
	
	/**
	 * 根据仓库属性模糊查询获取仓库http请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/search.do")
	public HTTPResponse getWarehouseByAttribute(@Body RequestBody json);
	
	/**
	 * 根据仓库唯一id查询获取仓库http请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/getById.do")
	public HTTPResponse getWarehouseById(@Body RequestBody json);
	
	/**
	 * 启用仓库
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/enable.do")
	public HTTPResponse enable(@Body RequestBody json);
	
	/**
	 * 禁用仓库
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/disable.do")
	public HTTPResponse disable(@Body RequestBody json);

	/**
	 * 获取仓库类型
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/builtType.do")
	public HTTPResponse getWarehouseType(@Body RequestBody json);

	/**
	 * 获取所有仓库名称
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月12日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/warehouseName.do")
	public HTTPResponse getAllWarehouseName(@Body RequestBody rb);

	/**
	 * @title 获取仓库编码
	 * @author  wzy
	 * @create  2017/12/20 10:15
	 **/
	@Headers("content-type:application/json")
	@POST("code/getCode.do")
	public HTTPResponse getWarehouseCode(@Body RequestBody rb);

	
}
