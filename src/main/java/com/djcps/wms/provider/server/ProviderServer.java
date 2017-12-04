package com.djcps.wms.provider.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.provider.model.AddProviderBO;
import com.djcps.wms.provider.model.DeleteProviderBO;
import com.djcps.wms.provider.model.ProvinceCityAreaCodeBo;
import com.djcps.wms.provider.model.SelectProviderByAttributeBO;
import com.djcps.wms.provider.model.UpdateProviderVO;
import com.djcps.wms.provider.request.AddressServerHttpRequest;
import com.djcps.wms.provider.request.WmsServerForProviderHttpRequest;
import com.djcps.wms.provider.service.ProviderServiceImpl;
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
	
	private static final Logger logger = LoggerFactory.getLogger(ProviderServer.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private WmsServerForProviderHttpRequest providerHttpRequest;
	
	@Autowired
	private AddressServerHttpRequest addressServerHttpRequest;

	public HttpResult add(AddProviderBO addBean)throws Exception{
        //将请求参数转化为requestbody格式
        String json = gson.toJson(addBean);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = providerHttpRequest.add(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }
	
	public HttpResult modify(UpdateProviderVO updateBean)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(updateBean);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = providerHttpRequest.modify(rb);
		//校验请求是否成功
		return verifyHttpResult(http);
	}
	
	public HttpResult delete(DeleteProviderBO deleteBean)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(deleteBean);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = providerHttpRequest.delete(rb);
		//校验请求是否成功
		return verifyHttpResult(http);
	}
	
	public HttpResult getAllList(BaseListParam baseListParam)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(baseListParam);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = providerHttpRequest.getAllList(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getProviderByAttribute(SelectProviderByAttributeBO selectVagueBean)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(selectVagueBean);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = providerHttpRequest.getProviderByAttribute(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getProvinceAllList(ProvinceCityAreaCodeBo param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = addressServerHttpRequest.getProvinceAllList(rb);
		return verifyHttpResult(http);
	}

	public HttpResult getCityListByProvince(ProvinceCityAreaCodeBo param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = addressServerHttpRequest.getCityListByProvince(rb);
		return verifyHttpResult(http);
	}

	public HttpResult getAreaListByCity(ProvinceCityAreaCodeBo param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = addressServerHttpRequest.getAreaListByCity(rb);
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
