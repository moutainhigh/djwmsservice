package com.djcps.wms.outorder.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.outorder.model.OrderBO;
import com.djcps.wms.outorder.model.OutOrderBO;
import com.djcps.wms.outorder.model.SelectOutOrderBO;
import com.djcps.wms.outorder.request.OrderDetailHttpRequest;
import com.djcps.wms.outorder.request.WmsForOutOrderHttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rpc.plugin.http.HTTPResponse;
/**
 * 
 * @author ldh
 *
 */
@Component
public class OutOrderServer {
	@Autowired
	private OrderDetailHttpRequest orderDetailHttpRequest;
	
	@Autowired
	private WmsForOutOrderHttpRequest wmsForOutOrderHttpRequest;
	
	private Gson gson = new GsonBuilder().serializeNulls().create();
	
	private static final Logger logger = LoggerFactory.getLogger(OutOrderServer.class);
	
	public HttpResult getOrderDetailByOrderId(OrderBO param){
		String json = gson.toJson(param);
		logger.debug("---http请求参数转化成json---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
		HTTPResponse http = orderDetailHttpRequest.getOrderDetailByOrderId(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getOrderIdsByOutOrderId(OutOrderBO param){
		String json = gson.toJson(param);
		logger.debug("---http请求参数转化成json---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
		HTTPResponse http = wmsForOutOrderHttpRequest.getOrderIdsByOutOrderId(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getAllOutOrder(SelectOutOrderBO param){
		String json = gson.toJson(param);
		logger.debug("---http请求参数转化成json---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
		HTTPResponse http = wmsForOutOrderHttpRequest.getAllOutOrder(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getOutOrderByOutOrderId(OutOrderBO param){
		String json = gson.toJson(param);
		logger.debug("---http请求参数转化成json---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
		HTTPResponse http = wmsForOutOrderHttpRequest.getOutOrderByOutOrderId(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult updateOutOrderByOutOrderId(OutOrderBO param){
		String json = gson.toJson(param);
		logger.debug("---http请求参数转化成json---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
		HTTPResponse http = wmsForOutOrderHttpRequest.updateOutOrderByOutOrderId(rb);
		return verifyHttpResult(http);
	}
	
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
