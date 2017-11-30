package com.djcps.wms.loadingtable.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.loadingtable.model.AddLoadingTableBO;
import com.djcps.wms.loadingtable.model.DeleteLoadingTableBO;
import com.djcps.wms.loadingtable.model.IsUseLoadingTableBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByIdBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByAttributeBO;
import com.djcps.wms.loadingtable.model.UpdateLoadingTableBO;
import com.djcps.wms.loadingtable.request.WmsServerForLoadingTableHttpRequest;
import com.djcps.wms.provider.request.WmsServerForProviderHttpRequest;
import com.djcps.wms.provider.service.ProviderServiceImpl;
import com.google.gson.Gson;

import rpc.plugin.http.HTTPResponse;

/**
 * @title:装车台服务
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月23日
 */
@Component
public class LoadingTableServer {
	
	private static final Logger logger = LoggerFactory.getLogger(LoadingTableServer.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private WmsServerForLoadingTableHttpRequest loadingTableHttpRequest;

	public HttpResult add(AddLoadingTableBO loadingTable)throws Exception{
        //将请求参数转化为requestbody格式
        String json = gson.toJson(loadingTable);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = loadingTableHttpRequest.add(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }
	
	public HttpResult modify(UpdateLoadingTableBO loadingTable)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(loadingTable);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = loadingTableHttpRequest.modify(rb);
		//校验请求是否成功
		return verifyHttpResult(http);
	}
	
	public HttpResult delete(DeleteLoadingTableBO loadingTable)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(loadingTable);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = loadingTableHttpRequest.delete(rb);
		//校验请求是否成功
		return verifyHttpResult(http);
	}
	
	public HttpResult getAllList(BaseListParam baseListParam)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(baseListParam);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = loadingTableHttpRequest.getAllList(rb);
		//校验请求是否成功
		return verifyHttpResult(http);
	}
	
	public HttpResult getLoadingTableByAttribute(SelectLoadingTableByAttributeBO loadingTable)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(loadingTable);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = loadingTableHttpRequest.getLoadingTableByAttribute(rb);
		//校验请求是否成功
		return verifyHttpResult(http);
	}
	
	public HttpResult getLoadingTableById(SelectLoadingTableByIdBO loadingTable)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(loadingTable);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = loadingTableHttpRequest.getLoadingTableById(rb);
		//校验请求是否成功
		return verifyHttpResult(http);
	}
	
	public HttpResult enable(IsUseLoadingTableBO loadingTable)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(loadingTable);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = loadingTableHttpRequest.enable(rb);
		//校验请求是否成功
		return verifyHttpResult(http);
	}
	
	public HttpResult disable(IsUseLoadingTableBO loadingTable)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(loadingTable);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = loadingTableHttpRequest.disable(rb);
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
