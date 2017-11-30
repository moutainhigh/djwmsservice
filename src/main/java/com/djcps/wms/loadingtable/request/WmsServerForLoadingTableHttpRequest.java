package com.djcps.wms.loadingtable.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsServerForLoadingTableHttpRequest {
	/**
	 * @title:装车台新增
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("loadingTable/save.do")
	public HTTPResponse add(@Body RequestBody json);
	
	/**
	 * @title:装车台修改
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
    @POST("loadingTable/modify.do")
	public HTTPResponse modify(@Body RequestBody json);
	
	/**
	 * @title:装车台删除
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("loadingTable/delete.do")
	public HTTPResponse delete(@Body RequestBody json);
	
	/**
	 * @title:获取所有装车台信息(带分页)
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("loadingTable/list.do")
	public HTTPResponse getAllList(@Body RequestBody json);
	
	/**
	 * @title:根据装车台属性模糊查询获取装车台信息(带分页)
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("loadingTable/search.do")
	public HTTPResponse getLoadingTableByAttribute(@Body RequestBody json);
	
	/**
	 * @title:根据装车台编号获取装车台信息
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("loadingTable/getById.do")
	public HTTPResponse getLoadingTableById(@Body RequestBody json);
	
	/**
	 * @title:启用装车台
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("loadingTable/enable.do")
	public HTTPResponse enable(@Body RequestBody json);
	
	/**
	 * @title:禁用装车台
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("loadingTable/disable.do")
	public HTTPResponse disable(@Body RequestBody json);
}
