package com.djcps.wms.warehouse.request;


import com.djcps.wms.commons.config.ParamsConfig;
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
	HTTPResponse add(@Body RequestBody json);
	
	/**
	 * 仓库管理修改http请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
    @POST("warehouse/modify.do")
	HTTPResponse modify(@Body RequestBody json);
	
	/**
	 * 仓库管理删除http请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/delete.do")
	HTTPResponse delete(@Body RequestBody json);
	
	/**
	 * 获取所有仓库http请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/list.do")
	HTTPResponse getAllList(@Body RequestBody json);
	
	/**
	 * 根据仓库属性模糊查询获取仓库http请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/search.do")
	HTTPResponse getWarehouseByAttribute(@Body RequestBody json);
	
	/**
	 * 根据仓库唯一id查询获取仓库http请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/getById.do")
	HTTPResponse getWarehouseById(@Body RequestBody json);
	
	/**
	 * 启用仓库
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/enable.do")
	HTTPResponse enable(@Body RequestBody json);
	
	/**
	 * 禁用仓库
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/disable.do")
	HTTPResponse disable(@Body RequestBody json);

	/**
	 * 获取仓库类型
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/builtType.do")
	HTTPResponse getWarehouseType(@Body RequestBody json);

	/**
	 * 获取所有仓库名称
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月12日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/warehouseName.do")
	HTTPResponse getAllWarehouseName(@Body RequestBody rb);

	/**
	 * 获取仓库编码
	 * @author  wzy
	 * @param rb
	 * @return
	 * @create  2017/12/20 10:15
	 **/
	@Headers("content-type:application/json")
	@POST("code/getCode.do")
	HTTPResponse getWarehouseCode(@Body RequestBody rb);

	
	/**
	 * 编码确认
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月22日
	 */
	@Headers("content-type:application/json")
	@POST("code/verifyCode.do")
	HTTPResponse verifyCode(@Body RequestBody rb);

	/**
	 * 删除编码
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2017年12月22日
	 */
	@Headers("content-type:application/json")
	@POST("code/delCode.do")
	HTTPResponse deleteCode(@Body RequestBody rb);
}
