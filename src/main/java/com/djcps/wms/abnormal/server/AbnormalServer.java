package com.djcps.wms.abnormal.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.abnormal.model.AddAbnormal;
import com.djcps.wms.abnormal.model.GetOrderByAttributeBO;
import com.djcps.wms.abnormal.model.OrderIdBO;
import com.djcps.wms.abnormal.model.OrderIdListBO;
import com.djcps.wms.abnormal.model.UpdateAbnormalBO;
import com.djcps.wms.abnormal.request.AbnormalServerHttpRequest;
import com.djcps.wms.address.model.ProvinceCityAreaCodeBO;
import com.djcps.wms.address.request.AddressServerHttpRequest;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.google.gson.Gson;

import rpc.plugin.http.HTTPResponse;

/**
 * @title:异常订单服务层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2018年3月7日
 */
@Component
public class AbnormalServer {
	
	private static final DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(AbnormalServer.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private AbnormalServerHttpRequest abnormalServerHttpRequest;

	public HttpResult getOrderByOrderIdList(OrderIdListBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = abnormalServerHttpRequest.getOrderByOrderIdList(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult addAbnormal(AddAbnormal param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = abnormalServerHttpRequest.addAbnormal(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getOrderByAttributeBO(GetOrderByAttributeBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = abnormalServerHttpRequest.getOrderByAttributeBO(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult updateAbnormal(UpdateAbnormalBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = abnormalServerHttpRequest.updateAbnormal(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getSplitOrderByOrderId(OrderIdBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = abnormalServerHttpRequest.getSplitOrderByOrderId(rb);
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
