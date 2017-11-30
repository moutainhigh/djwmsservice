package com.djcps.wms.provider.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsServerForProviderHttpRequest {
	/**
	 * @title:供应商新增
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("provider/save.do")
	public HTTPResponse add(@Body RequestBody json);
	
	/**
	 * @title:供应商修改
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
    @POST("provider/modify.do")
	public HTTPResponse modify(@Body RequestBody json);
	
	/**
	 * @title:供应商删除
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("provider/delete.do")
	public HTTPResponse delete(@Body RequestBody json);
	
	/**
	 * @title:获取所有供应商信息(带分页)
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("provider/list.do")
	public HTTPResponse getAllList(@Body RequestBody json);
	
	/**
	 * @title:根据供应商属性模糊查询获取供应商信息(带分页)
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("provider/search.do")
	public HTTPResponse getProviderByAttribute(@Body RequestBody json);
}
