package com.djcps.wms.stock.server;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.model.MapAddressComponentPO;
import com.djcps.wms.commons.request.MapHttpRequest;
import com.djcps.wms.stock.dao.StockDao;
import com.djcps.wms.stock.model.AddStockBO;
import com.djcps.wms.stock.model.MapLocationPO;
import com.djcps.wms.stock.model.MoveStockBO;
import com.djcps.wms.stock.model.RecommendLocaBO;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.djcps.wms.stock.model.SelectSavedStockAmountBO;
import com.djcps.wms.stock.request.WmsForStockHttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import rpc.plugin.http.HTTPResponse;

/**
 * @title:入库移库服务
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月23日
 */
@Component
public class StockServer {
	
	private static final Logger logger = LoggerFactory.getLogger(StockServer.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	StockDao stockDao;
	
	@Autowired
	private MapHttpRequest mapHttpRequest;
	
	@Autowired
	WmsForStockHttpRequest wmsForStockHttpRequest;
	
	public MapLocationPO getStreetCode(String key, String newLocation, String output) {
		MapLocationPO allLocation = stockDao.getLocationByLocation(newLocation);
		if(allLocation!=null){
			//非空直接返回
			return allLocation;
		}else{
			//为空
			//调用接口获取信息
			HTTPResponse http = mapHttpRequest.getStreetCode(key, newLocation,output);
			String bodyString = http.getBodyString();
			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parse(bodyString).getAsJsonObject().get("regeocode");
			JsonElement jsonElement2 = jsonElement.getAsJsonObject().get("addressComponent");
			MapAddressComponentPO fromJson = gson.fromJson(jsonElement2, MapAddressComponentPO.class);
			MapLocationPO mapLocaTion = new MapLocationPO();
			mapLocaTion.setId(UUID.randomUUID().toString());
			mapLocaTion.setStreetCode(fromJson.getTowncode().substring(0,9));
			mapLocaTion.setStreetName(fromJson.getTownship());
			mapLocaTion.setCountyCode(fromJson.getAdcode());
			mapLocaTion.setCountyName(fromJson.getDistrict());
			mapLocaTion.setLnglat(newLocation);
			stockDao.insertLocation(mapLocaTion);
			return mapLocaTion;
		}
	}
	
	public HttpResult getRecommendLoca(RecommendLocaBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = wmsForStockHttpRequest.getRecommendLoca(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}
	
	public HttpResult getOperationRecord(String param) {
		//将请求参数转化为requestbody格式
        System.out.println("---http请求参数转化为json格式---:"+param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),param);
        //调用借口获取信息
        HTTPResponse http = wmsForStockHttpRequest.getOperationRecord(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}
	
	public HttpResult addStock(AddStockBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = wmsForStockHttpRequest.addStock(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}
	
	public HttpResult moveStock(MoveStockBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = wmsForStockHttpRequest.moveStock(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}
	
	public HttpResult getSavedStockAmount(SelectSavedStockAmountBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = wmsForStockHttpRequest.getSavedStockAmount(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}
	
	public HttpResult getAreaByOrderId(SelectAreaByOrderIdBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = wmsForStockHttpRequest.getAreaByOrderId(rb);
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
		if(ObjectUtils.isEmpty(result)){
			System.err.println("Http请求出错,HttpResult结果为null");
			logger.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}

}
