package com.djcps.wms.permission.server;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.outorder.model.SelectOutOrderBO;
import com.djcps.wms.outorder.server.OutOrderServer;
import com.djcps.wms.permission.model.BaseOrgBO;
import com.djcps.wms.permission.model.DeletePermissionBO;
import com.djcps.wms.permission.model.GetPermissionBO;
import com.djcps.wms.permission.model.GetPermissionChooseBO;
import com.djcps.wms.permission.model.GetUserByPermissionIdBO;
import com.djcps.wms.permission.model.UserIdBO;
import com.djcps.wms.permission.request.DjorForPermissionHttpRequest;
import com.djcps.wms.permission.request.DjorForUserHttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rpc.plugin.http.HTTPResponse;

/**
 * @author zhq 
 * 权限服务 
 * 2018年4月12日
 */
@Component
public class PermissionServer {
	@Autowired
	private DjorForPermissionHttpRequest djorForPermissionHttpRequest;
	
	@Autowired
	private DjorForUserHttpRequest djorForUserHttpRequest;

	private Gson gson = new GsonBuilder().serializeNulls().create();

	private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(PermissionServer.class);
	
	/**
	 * 得到组合权限包
	 * @param param
	 * @return
	 */
	public HttpResult getPermissionList(GetPermissionBO param) {
		String json = gson.toJson(param);
		LOGGER.debug("---http请求参数转化成json---:"+json);
		Map<String,Object> map=gson.fromJson(json,Map.class);
		HTTPResponse http =djorForPermissionHttpRequest.getPermissionList(map);
		//okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), json);
		//HTTPResponse http = djorForPermissionHttpRequest.getPermissionList(rb);
		return verifyHttpResult(http);
	}
	
	/**
	 * 得到wms权限
	 * @param param
	 * @return
	 */
	public HttpResult getWmsPermission(BaseOrgBO param) {
		String json = gson.toJson(param);
		LOGGER.debug("---http请求参数转化成json---:"+json);
		Map<String,Object> map=gson.fromJson(json,Map.class);		
		//okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
		HTTPResponse http = djorForPermissionHttpRequest.getWmsPermission(map);
		return verifyHttpResult(http);
	}
	
	/**
	 * 新增/修改权限
	 * @param param
	 * @return
	 */
	public HttpResult insertOrUpdatePermission(BaseOrgBO param) {
		String json = gson.toJson(param);
		LOGGER.debug("---http请求参数转化成json---:"+json);
		Map<String,Object> map=gson.fromJson(json,Map.class);
		//okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
		HTTPResponse http = djorForPermissionHttpRequest.insertOrUpdatePermission(map);
		return verifyHttpResult(http);
	}

	/**
	 * 删除权限
	 * @param param
	 * @return
	 */
	public HttpResult deletePermission(DeletePermissionBO param) {
		String json = gson.toJson(param);
		LOGGER.debug("---http请求参数转化成json---:"+json);
		Map<String,Object> map=gson.fromJson(json,Map.class);
		//okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
		HTTPResponse http = djorForPermissionHttpRequest.deletePermission(map);
		return verifyHttpResult(http);
	}
	
	/**
	 * 根据权限获取关联用户
	 * @param param
	 * @return
	 */
	public HttpResult getUserByPermissionId(GetUserByPermissionIdBO param) {
		String json = gson.toJson(param);
		LOGGER.debug("---http请求参数转化成json---:"+json);
		Map<String,Object> map=gson.fromJson(json,Map.class);
		HTTPResponse http = djorForPermissionHttpRequest.getUserByPermissionId(map);
		return verifyHttpResult(http);
	}
	
	/**
	 * 根据用户id获取用户信息
	 * @param param
	 * @return
	 */
	public HttpResult getUserInfo(String json){
		LOGGER.debug("---http请求参数转化成json---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
		HTTPResponse http = djorForUserHttpRequest.getUserInfo(rb);
		return verifyHttpResult(http);
	}
	
	/**
	 * 根据组合权限id和公司id，获取获取组合权限集合
	 * @param param
	 * @return
	 */
	public HttpResult getPerChoose(GetPermissionChooseBO param) {
		String json = gson.toJson(param);
		LOGGER.debug("---http请求参数转化成json---:"+json);
		Map<String,Object> map=gson.fromJson(json,Map.class);
		HTTPResponse http = djorForPermissionHttpRequest.getPerChoose(map);
		return verifyHttpResult(http);
	}
	
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
