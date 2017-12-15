package com.djcps.wms.warehouse.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.provider.server.ProviderServer;
import com.djcps.wms.warehouse.model.location.AddLocationBO;
import com.djcps.wms.warehouse.model.location.DeleteLocationBO;
import com.djcps.wms.warehouse.model.location.SelectAllLocationList;
import com.djcps.wms.warehouse.model.location.SelectLocationByAttributeBO;
import com.djcps.wms.warehouse.model.location.UpdateLocationBO;
import com.djcps.wms.warehouse.model.warehouse.AddWarehouseBO;
import com.djcps.wms.warehouse.model.warehouse.DeleteWarehouseBO;
import com.djcps.wms.warehouse.model.warehouse.IsUseWarehouseBO;
import com.djcps.wms.warehouse.model.warehouse.SelectWarehouseByAttributeBO;
import com.djcps.wms.warehouse.model.warehouse.SelectWarehouseByIdBO;
import com.djcps.wms.warehouse.model.warehouse.UpdateWarehouseBO;
import com.djcps.wms.warehouse.request.WmsForLocationHttpRequest;
import com.djcps.wms.warehouse.request.WmsForWarehouseHttpRequest;
import com.google.gson.Gson;

import rpc.plugin.http.HTTPResponse;

/**
 * @title:库位调用http服务
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月23日
 */
@Component
public class LocationServer {
	
	private static final Logger logger = LoggerFactory.getLogger(LocationServer.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private WmsForLocationHttpRequest locationHttpRequest;

	public HttpResult addLocation(AddLocationBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = locationHttpRequest.addLocation(rb);
		return verifyHttpResult(http);
	}

	public HttpResult modifyLocation(UpdateLocationBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = locationHttpRequest.modifyLocation(rb);
		return verifyHttpResult(http);
	}

	public HttpResult deleteLocation(DeleteLocationBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = locationHttpRequest.deleteLocation(rb);
		return verifyHttpResult(http);
	}

	public HttpResult getLocationAllList(SelectAllLocationList param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = locationHttpRequest.getLocationAllList(rb);
		return verifyHttpResult(http);
	}

	public HttpResult getLocationByAttribute(SelectLocationByAttributeBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = locationHttpRequest.getLocationByAttribute(rb);
		return verifyHttpResult(http);
	}
	
	/**
	 * @title:校验HTTPResponse结果是否成功
	 * @description:
	 * @param http
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	private HttpResult verifyHttpResult(HTTPResponse http){
		HttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), HttpResult.class);
		}
		if(result == null){
			System.err.println("Http请求出错,HttpResult结果为null");
			logger.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}
}
