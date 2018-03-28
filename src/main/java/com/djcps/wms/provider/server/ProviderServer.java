package com.djcps.wms.provider.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.base.BaseListBO;
import com.djcps.wms.commons.base.BaseListPartnerIdBO;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.provider.model.AddProviderBO;
import com.djcps.wms.provider.model.DeleteProviderBO;
import com.djcps.wms.provider.model.SelectProviderByAttributeBO;
import com.djcps.wms.provider.model.UpdateProviderBO;
import com.djcps.wms.provider.request.WmsForProviderHttpRequest;
import com.google.gson.Gson;

import rpc.plugin.http.HTTPResponse;

/**
 * @title:供应商服务
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月23日
 */
@Component
public class ProviderServer {
	
	private static final DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(ProviderServer.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private WmsForProviderHttpRequest providerHttpRequest;
	
	public HttpResult add(AddProviderBO addBean){
        //将请求参数转化为requestbody格式
        String json = gson.toJson(addBean);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = providerHttpRequest.add(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }
	
	public HttpResult modify(UpdateProviderBO updateBean){
		//将请求参数转化为requestbody格式
		String json = gson.toJson(updateBean);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = providerHttpRequest.modify(rb);
		//校验请求是否成功
		return verifyHttpResult(http);
	}
	
	public HttpResult delete(DeleteProviderBO deleteBean){
		//将请求参数转化为requestbody格式
		String json = gson.toJson(deleteBean);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = providerHttpRequest.delete(rb);
		//校验请求是否成功
		return verifyHttpResult(http);
	}
	
	public HttpResult getAllList(BaseListPartnerIdBO baseListParam){
		//将请求参数转化为requestbody格式
		String json = gson.toJson(baseListParam);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = providerHttpRequest.getAllList(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getProviderByAttribute(SelectProviderByAttributeBO selectVagueBean){
		//将请求参数转化为requestbody格式
		String json = gson.toJson(selectVagueBean);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = providerHttpRequest.getProviderByAttribute(rb);
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
			LOGGER.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}

}
