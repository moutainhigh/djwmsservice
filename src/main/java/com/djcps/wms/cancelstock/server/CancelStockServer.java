package com.djcps.wms.cancelstock.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.address.model.ProvinceCityAreaCodeBO;
import com.djcps.wms.address.request.AddressServerHttpRequest;
import com.djcps.wms.cancelstock.model.param.AddCancelStockBO;
import com.djcps.wms.cancelstock.model.param.AddStockBO;
import com.djcps.wms.cancelstock.model.param.CancelOrderIdBO;
import com.djcps.wms.cancelstock.model.param.PickerIdBO;
import com.djcps.wms.cancelstock.request.CancelStockServerHttpRequest;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.google.gson.Gson;

import rpc.plugin.http.HTTPResponse;

/**
 * @title:地址服务
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月23日
 */
@Component
public class CancelStockServer {
	
	private static final DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(CancelStockServer.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private CancelStockServerHttpRequest cancelStockServerHttpRequest;
	
	public HttpResult getOrderByOrderId(CancelOrderIdBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = cancelStockServerHttpRequest.getOrderByOrderId(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult addStock(AddStockBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = cancelStockServerHttpRequest.addStock(rb);
		return verifyHttpResult(http);
	}

	public HttpResult addCancelStock(AddCancelStockBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = cancelStockServerHttpRequest.addCancelStock(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getCancelStockByPickerId(PickerIdBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = cancelStockServerHttpRequest.getCancelStockByPickerId(rb);
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
			LOGGER.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}

}
