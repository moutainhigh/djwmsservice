package com.djcps.wms.outLocation.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.outLocation.model.OrderIdBO;
import com.djcps.wms.outLocation.request.OrderDetailHttpRequest;
import com.djcps.wms.outLocation.request.WmsForOutLocationHttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rpc.plugin.http.HTTPResponse;

@Component
public class OutLocationServer {
	@Autowired
	private WmsForOutLocationHttpRequest wmsForOutLocationHttp;
	@Autowired
	private OrderDetailHttpRequest orderDetailHttp;
	
	private static Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
	
	private static final Logger logger = LoggerFactory.getLogger(OutLocationServer.class);
	
	public HttpResult getOrderDetailByOrderId(OrderIdBO param){
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param,OrderIdBO.class);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = orderDetailHttp.getOrderDetailByOrderId(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}
	
	private HttpResult verifyHttpResult(HTTPResponse http){
		HttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), HttpResult.class);
		}
		if(ObjectUtils.isEmpty(result)){
			System.err.println("Http请求出错,HttpResult结果为null");
			logger.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}
}
