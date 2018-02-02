package com.djcps.wms.order.server;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.djcps.wms.order.model.ChildOrderBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.httpclient.OrderHttpResult;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.order.model.OrderParamBO;
import com.djcps.wms.order.request.UpdateOrderHttpRequest;
import com.djcps.wms.order.request.WmsForOrderHttpRequest;
import com.djcps.wms.stock.model.AddOrderRedundantBO;
import com.google.gson.Gson;

import rpc.plugin.http.HTTPResponse;


/**
 * 订单服务层
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
@Component
public class OrderServer {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServer.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private WmsForOrderHttpRequest orderHttpRequest;
	
	@Autowired
	private UpdateOrderHttpRequest  updateOrderHttpRequest;
	
	public OrderHttpResult getAllOrderList(OrderParamBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = orderHttpRequest.getAllOrderList(rb);
        //校验请求是否成功
        OrderHttpResult result = null;
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), OrderHttpResult.class);
		}
		if(result == null){
			System.err.println("Http请求出错,HttpResult结果为null");
			logger.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}
	
	public HttpResult getOrderByOrderId(OrderIdBO param){
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = orderHttpRequest.getOrderByOrderId(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }
	
	public HttpResult getOrderByOrderIds(OrderIdsBO param){
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = updateOrderHttpRequest.getOrderByOrderIds(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }
	
	public HttpResult updateOrderStatus(OrderIdBO param){
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = updateOrderHttpRequest.updateOrderStatus(rb);
        //校验请求是否成功
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

	/**
	 * 批量获取订单详细信息
	 * @param param
	 * @return
	 */
	public List<ChildOrderBO> getChildOrderList(OrderIdsBO param){
		List<ChildOrderBO> childOrderBOList = new ArrayList<>();
		HttpResult httpResult = getOrderByOrderIds(param);
		if(httpResult.isSuccess()){
			String data = gson.toJson(httpResult.getData());
			childOrderBOList = JSONArray.parseArray(data,ChildOrderBO.class);
		}
		return  childOrderBOList;
	}
}
