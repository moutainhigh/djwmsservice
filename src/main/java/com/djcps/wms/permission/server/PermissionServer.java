package com.djcps.wms.permission.server;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.httpclient.OtherHttpResult;
import com.djcps.wms.outorder.model.SelectOutOrderBO;
import com.djcps.wms.outorder.server.OutOrderServer;
import com.djcps.wms.permission.model.bo.BaseOrgBO;
import com.djcps.wms.permission.model.bo.DeletePermissionBO;
import com.djcps.wms.permission.model.bo.GetPermissionBO;
import com.djcps.wms.permission.model.bo.GetPermissionChooseBO;
import com.djcps.wms.permission.model.bo.GetUserByPermissionIdBO;
import com.djcps.wms.permission.model.bo.InsertOrUpdatePermissionBO;
import com.djcps.wms.permission.request.DjorForPermissionHttpRequest;
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

	private Gson gson = new GsonBuilder().serializeNulls().create();

	private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(PermissionServer.class);
	
	/**
	 * 得到组合权限包
	 * @param param
	 * @return
	 */
	public OtherHttpResult getPermissionList(GetPermissionBO param) {
		String json = gson.toJson(param);
		LOGGER.debug("---http请求参数转化成json---:"+json);
		Map<String,Object> map=gson.fromJson(json,Map.class);
		HTTPResponse http =djorForPermissionHttpRequest.getPermissionList(map);
		return verifyOtherHttpResult(http);
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
	 * 新增权限
	 * @param param
	 * @return
	 */
	public HttpResult insertPermission(InsertOrUpdatePermissionBO param) {
		String json = gson.toJson(param);
		LOGGER.debug("---http请求参数转化成json---:"+json);
		Map<String,Object> map=gson.fromJson(json,Map.class);
		HTTPResponse http = djorForPermissionHttpRequest.insertPermission(map);
		return verifyHttpResult(http);
	}
	
	/**
	 * 修改权限
	 * @param param
	 * @return
	 */
	public HttpResult updatePermission(InsertOrUpdatePermissionBO param) {
		String json = gson.toJson(param);
		LOGGER.debug("---http请求参数转化成json---:"+json);
		Map<String,Object> map=gson.fromJson(json,Map.class);
		HTTPResponse http = djorForPermissionHttpRequest.updatePermission(map);
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
	
	private OtherHttpResult verifyOtherHttpResult(HTTPResponse http){
		OtherHttpResult result=null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), OtherHttpResult.class);
		}
		if(result == null){
			LOGGER.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}
}
