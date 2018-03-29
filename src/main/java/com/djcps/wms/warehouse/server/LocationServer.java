package com.djcps.wms.warehouse.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.wms.commons.model.GetCodeBO;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.model.GetCodeBO;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.request.GetCodeRequest;
import com.djcps.wms.warehouse.model.location.AddLocationBO;
import com.djcps.wms.warehouse.model.location.DeleteLocationBO;
import com.djcps.wms.warehouse.model.location.SelectAllLocationListBO;
import com.djcps.wms.warehouse.model.location.SelectLocationByAttributeBO;
import com.djcps.wms.warehouse.model.location.UpdateLocationBO;
import com.djcps.wms.warehouse.request.WmsForLocationHttpRequest;
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
	
	private static final DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(LocationServer.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private WmsForLocationHttpRequest locationHttpRequest;

	@Autowired
	private GetCodeRequest getCodeRequest;

	public HttpResult addLocation(AddLocationBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = locationHttpRequest.addLocation(rb);
		return verifyHttpResult(http);
	}

	public HttpResult modifyLocation(UpdateLocationBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = locationHttpRequest.modifyLocation(rb);
		return verifyHttpResult(http);
	}

	public HttpResult deleteLocation(DeleteLocationBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = locationHttpRequest.deleteLocation(rb);
		return verifyHttpResult(http);
	}

	public HttpResult getLocationAllList(SelectAllLocationListBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = locationHttpRequest.getLocationAllList(rb);
		return verifyHttpResult(http);
	}

	public HttpResult getLocationByAttribute(SelectLocationByAttributeBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = locationHttpRequest.getLocationByAttribute(rb);
		return verifyHttpResult(http);
	}

	/**
	 * @title  获取库位编码
	 * @author  wzy
	 * @create  2017/12/21 17:04
	 **/
	public HttpResult getLocationCode(GetCodeBO getCodeBO){
		//将请求参数转化为requestbody格式
		String json = gson.toJson(getCodeBO);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = getCodeRequest.getCode(rb);
		return verifyHttpResult(http);
	}

	public HttpResult verifyCode(AddLocationBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = locationHttpRequest.verifyCode(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}

	public HttpResult deleteCode(DeleteLocationBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = locationHttpRequest.deleteCode(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}

	public HttpResult getLocationByCode(SelectLocationByAttributeBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
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
			LOGGER.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}
}
