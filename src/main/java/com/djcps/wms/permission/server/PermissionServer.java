package com.djcps.wms.permission.server;

import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.httpclient.OtherHttpResult;
import com.djcps.wms.permission.constants.PermissionConstants;
import com.djcps.wms.permission.model.bo.*;
import com.djcps.wms.permission.model.po.UserPermissionPO;
import com.djcps.wms.permission.model.vo.UserPermissionVO;
import com.djcps.wms.permission.request.DjorForPermissionHttpRequest;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rpc.plugin.http.HTTPResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * @author zhq 
 * 权限服务 
 * 2018年4月12日
 */
@Component
public class PermissionServer {
	@Autowired
	private DjorForPermissionHttpRequest djorForPermissionHttpRequest;

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
	public HttpResult getWmsPermission(WmsPermissionBO param) {
		String json = gson.toJson(param);
		LOGGER.debug("---http请求参数转化成json---:"+json);
		Map<String,Object> map = gson.fromJson(json,Map.class);
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
	public HttpResult deletePermission(DeletePerParamBO param) {
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
	
	/**
	 * 得到wms基本权限项
	 * @param param
	 * @return
	 */
	public List<UserPermissionVO> listBasicPermission(BaseOrgBO param) {
		String json = gson.toJson(param);
		Map<String,Object> map=gson.fromJson(json,Map.class);
		HTTPResponse http = djorForPermissionHttpRequest.getWmsPermission(map);
		HttpResult result = verifyHttpResult(http);
		String data = JSONObject.toJSONString(result.getData());
		List<UserPermissionPO> userPermissionPOList = gson.fromJson(data,new TypeToken<List<UserPermissionPO>>() {}.getType());
		return toUserPermissionVO(userPermissionPOList);
	}

	/**
	 * 获取用户拥有的所有权限
	 * @param param
	 * @watch business default: 30
	 * @return
	 */
	public List<UserPermissionVO> getUserPermission(UserPermissionBO param) {
		param.setpBusiness(PermissionConstants.BUSINESS_ID);
		String json = gson.toJson(param);
		Map<String,Object> map = gson.fromJson(json,Map.class);
		HTTPResponse http = djorForPermissionHttpRequest.getUserPermission(map);
		HttpResult result = verifyHttpResult(http);
		String data = JSONObject.toJSONString(result.getData());
		List<UserPermissionPO> userPermissionPOList = gson.fromJson(data,new TypeToken<List<UserPermissionPO>>() {}.getType());
		return userPermissionToTree(toUserPermissionVO(userPermissionPOList),PermissionConstants.BUSINESS_ID);
	}

	/**
	 * 获取数据权限项
	 * @autuor Chengw
	 * @since 2018/4/23  20:47
	 * @param param
	 * @return
	 */
	public List<UserPermissionVO> listUserPermission(UserPermissionBO param) {
		param.setpBusiness(PermissionConstants.BUSINESS_ID);
		String json = gson.toJson(param);
		Map<String,Object> map = gson.fromJson(json,Map.class);
		HTTPResponse http = djorForPermissionHttpRequest.getUserPermission(map);
		HttpResult result = verifyHttpResult(http);
		String data = JSONObject.toJSONString(result.getData());
		List<UserPermissionPO> userPermissionPOList = gson.fromJson(data,new TypeToken<List<UserPermissionPO>>() {}.getType());
		return toUserPermissionVO(userPermissionPOList);
	}

	/**
	 * 转换实体类
	 * @param userPermissionPOList
	 * @return
	 */
	private List<UserPermissionVO> toUserPermissionVO(List<UserPermissionPO> userPermissionPOList){
		 List<UserPermissionVO> userPermissionVOList = userPermissionPOList.stream().map(u -> new UserPermissionVO(){{
			setId(u.getId());
			setTitle(u.getPtitle());
			setBusiness(u.getPbusiness());
			setFather(u.getPfather());
			setIco(u.getPico());
			setInterfaceName(u.getPinterface());
			setLayer(u.getPolayer());
			setHtmlId(u.getPhtmlid());
			setType(u.getPtype());
		}}).collect(Collectors.toList());
		return userPermissionVOList;
	}

	/**
	 * 将权限项递归生成树
	 * @param param
	 * @param rootId
	 * @return
	 */
	private List<UserPermissionVO> userPermissionToTree(List<UserPermissionVO> param,String rootId){
		List<UserPermissionVO> result = param.stream().filter(u -> rootId.equals(u.getFather())).collect(Collectors.toList());
		result.stream().forEach( u -> {
			u.setChildren(userPermissionToTree(param,u.getId()));
		});
		return result;
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
