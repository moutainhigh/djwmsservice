package com.djcps.wms.warehouse.request;

import java.util.Map;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsServerForWarehouseHttpRequest {
	
	/**
	 * @title:仓库管理新增
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/save.do")
	public HTTPResponse add(@Body RequestBody json);
	
	/**
	 * @title:仓库管理修改
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
    @POST("warehouse/modify.do")
	public HTTPResponse modify(@Body RequestBody json);
	
	/**
	 * @title:仓库管理删除
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/delete.do")
	public HTTPResponse delete(@Body RequestBody json);
	
	/**
	 * @title:仓库管理获取所有仓库(带分页)
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/list.do")
	public HTTPResponse getAllList(@Body RequestBody json);
	
	/**
	 * @title:根据仓库属性模糊查询获取仓库
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/search.do")
	public HTTPResponse getWarehouseByAttribute(@Body RequestBody json);
	
	/**
	 * @title:根据仓库唯一id查询获取仓库
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/getById.do")
	public HTTPResponse getWarehouseById(@Body RequestBody json);
	
	/**
	 * @title:启用仓库
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/enable.do")
	public HTTPResponse enable(@Body RequestBody json);
	
	/**
	 * @title:禁用仓库
	 * @description:
	 * @param json
	 * @return
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Headers("content-type:application/json")
	@POST("warehouse/disable.do")
	public HTTPResponse disable(@Body RequestBody json);
	
}
