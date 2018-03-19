package com.djcps.wms.warehouse.request;


import com.djcps.wms.commons.config.ParamsConfig;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 仓库库区http请求接口
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsForAreaHttpRequest {
	
	/**
	 * 仓库库区新增http请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	@Headers("content-type:application/json")
	@POST("warehouseArea/save.do")
	HTTPResponse addArea(@Body RequestBody json);
	
	/**
	 * 仓库库区修改http请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	@Headers("content-type:application/json")
	@POST("warehouseArea/modify.do")
	HTTPResponse modifyArea(@Body RequestBody json);
	
	/**
	 * 仓库库区删除http请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	@Headers("content-type:application/json")
	@POST("warehouseArea/delete.do")
	HTTPResponse deleteArea(@Body RequestBody json);
	
	/**
	 * 获取所有仓库库区请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	@Headers("content-type:application/json")
	@POST("warehouseArea/list.do")
	HTTPResponse getAreaAllList(@Body RequestBody json);
	
	/**
	 * 根据id获取仓库库区请求接口
	 * @param json
	 * @return HTTPResponse
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	@Headers("content-type:application/json")
	@POST("warehouseArea/getById.do")
	HTTPResponse getAreaById(@Body RequestBody json);

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
	
	/**
	 * 获取库区编码
	 * @title
	 * @author  wzy
	 * @create  2017/12/20 13:11
	 * @param rb
	 * @return
	 **/
	@Headers("content-type:application/json")
	@POST("code/getCode.do")
	HTTPResponse getAreaCode(@Body RequestBody rb);

	/**
	 * 新增库区校验是否已存在街道
	 * @param rb
	 * @return
	 * @author:zdx
	 * @date:2018年1月2日
	 */
	@Headers("content-type:application/json")
	@POST("warehouseArea/isUsedStreet.do")
	HTTPResponse isUsedStreet(@Body RequestBody rb);
}
