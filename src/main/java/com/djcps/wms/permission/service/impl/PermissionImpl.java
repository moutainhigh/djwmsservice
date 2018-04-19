package com.djcps.wms.permission.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSONObject;
import com.djcps.wms.commons.base.BaseListPO;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.permission.model.BaseOrgBO;
import com.djcps.wms.permission.model.DeletePermissionBO;
import com.djcps.wms.permission.model.GetPermissionBO;
import com.djcps.wms.permission.model.GetPermissionChooseBO;
import com.djcps.wms.permission.model.GetUserByPermissionIdBO;
import com.djcps.wms.permission.model.GetWmsPermissionBO;
import com.djcps.wms.permission.model.InsertOrUpdatePermissionBO;
import com.djcps.wms.permission.model.PO.ChangeOnePerPO;
import com.djcps.wms.permission.model.PO.ChangePerPackagePO;
import com.djcps.wms.permission.model.PO.ChangeWmsPerPO;
import com.djcps.wms.permission.model.PO.GetOnePermissionPO;
import com.djcps.wms.permission.model.PO.GetPermissionPackagePO;
import com.djcps.wms.permission.model.PO.GetWmsPerPO;
import com.djcps.wms.permission.model.PO.UserInfoPO;
import com.djcps.wms.permission.server.PermissionServer;
import com.djcps.wms.permission.service.PermissionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.http.client.domain.UserInfo;

import retrofit2.http.QueryMap;

/**
 * @author zhq
 * 权限服务层
 * 2018年4月13日
 */
@Service
public class PermissionImpl implements PermissionService{
	
	@Autowired
	private PermissionServer permissionServer;
	
	private Gson gson = new Gson();
	
	/**
	 * 得到组合权限的数据
	 */
	public Map<String, Object> getPermissionList(GetPermissionBO param,GetPermissionBO param_count) {
		//@QueryMap这个注解，如果值为null会报错
		if(ObjectUtils.isEmpty(param.getKeyWord())) {
			param.setKeyWord("");
			param_count.setKeyWord("");
		}else {
			param_count.setKeyWord(param.getKeyWord());
		}
		//得到请求的数据
		HttpResult result =permissionServer.getPermissionList(param);
		String data = JSONObject.toJSONString(result.getData());
		ArrayList<GetPermissionPackagePO> list_user = gson.fromJson(data,new TypeToken<List<GetPermissionPackagePO>>() {}.getType());
		//规范返回字段
		List list_user_change=list_user.stream().map(x->new ChangePerPackagePO() {{
				setBussion(x.getPbussion());
				setDescribe(x.getPdes());
				setId(x.getId());
				setPerList(x.getPerlist());
				setTitle(x.getPtitle());
		}}).collect(Collectors.toList());
		//查出total，供前端分页使用
		HttpResult result_count =permissionServer.getPermissionList(param_count);
		String data_count = JSONObject.toJSONString(result_count.getData());
		ArrayList<GetPermissionPackagePO> list_count = gson.fromJson(data_count,new TypeToken<List<GetPermissionPackagePO>>() {}.getType());
		int count=list_count.size();
		//组织返回数据
		BaseListPO info=new BaseListPO() {{
			setList(list_user_change);
			setTotal(count);
		}};
		return MsgTemplate.successMsg(info);
	}

	/**
	 * 得到WMS权限
	 */
	public Map<String, Object> getWmsPermission(GetWmsPermissionBO param) {
		//得到wms权限数据
		HttpResult result =permissionServer.getWmsPermission(param);
		String data = JSONObject.toJSONString(result.getData());
		ArrayList<GetWmsPerPO> list_user = gson.fromJson(data,new TypeToken<List<GetWmsPerPO>>() {}.getType());
		//规范返回字段
		List list_user_change=list_user.stream().map(x->new ChangeWmsPerPO() {{
				setId(x.getId());
				setTitle(x.getPtitle());
				setLayer(x.getPolayer());
				setFatherId(x.getPfather());
				setFirstId(x.getPfirst());
				setIsParent(x.getIsParent());
				setMark(x.getPmark());
				setIcon(x.getIcon());
				setInterfaceInfo(x.getPinterface());
		}}).collect(Collectors.toList());
		return MsgTemplate.successMsg(list_user_change);
	}

	/**
	 * 新增权限
	 * 
	 */
	public Map<String, Object> insertPermission(InsertOrUpdatePermissionBO param) {
		if(ObjectUtils.isEmpty(param.getId())) {
			param.setId("");
		}
		HttpResult result =permissionServer.insertPermission(param);
		return MsgTemplate.customMsg(result);
	}

	/**
	 * 删除权限包
	 * 如果有用户关联权限包，则不能删除
	 */
	public Map<String, Object> deletePermission(DeletePermissionBO param) {
		//组织参数,获取关联了该权限的用户
		GetUserByPermissionIdBO getUser=new GetUserByPermissionIdBO();
		BeanUtils.copyProperties(param, getUser);
		HttpResult result_user =permissionServer.getUserByPermissionId(getUser);
		//如果有用户关联则删除失败，否则删除
		if(!ObjectUtils.isEmpty(result_user.getData())) {
			return MsgTemplate.failureMsg("有用户存在关联，删除失败");
		}
		HttpResult result =permissionServer.deletePermission(param);
		return MsgTemplate.customMsg(result);
	}
		/*//如果有用户关联该权限，则进行后续判断
		if(!ObjectUtils.isEmpty(result_user.getData())){
			//得到了关联权限的用户list
			String data = JSONObject.toJSONString(result_user.getData());
			ArrayList<UserInfoPO> list_user = gson.fromJson(data,new TypeToken<List<UserInfoPO>>() {}.getType());
			//将用户id遍历查询出用户信息
			HttpResult result=new HttpResult();
			for (UserInfoPO user : list_user) {
				//组织参数
				UserIdBO uid=new UserIdBO() {{
					setUserId(user.getId());
					setPartnerId(partnerId);
				}};
				String data_userInfo = JSONObject.toJSONString(uid);
				result =permissionServer.getUserInfo(data_userInfo);
				if(!ObjectUtils.isEmpty(result.getData())) {
					String user_data = JSONObject.toJSONString(result.getData());
					UserRelevancePO userRelevance=gson.fromJson(user_data, UserRelevancePO.class);
					//如果有任意一个关联用户不处于空闲状态，则不能删除
					if(userRelevance.getWorkStatus()!=1) {
						return MsgTemplate.failureMsg("删除失败，有关联用户不处于空闲状态");
					}
				}
			}
		}*/
		

	/**
	 * 根据组合权限id和公司id，获取获取组合权限信息
	 */
	public Map<String, Object> getPerChoose(GetPermissionChooseBO param) {
		//得到查询权限信息
		HttpResult result =permissionServer.getPerChoose(param);
		String data = JSONObject.toJSONString(result.getData());
		ArrayList<GetOnePermissionPO> list = gson.fromJson(data,new TypeToken<List<GetOnePermissionPO>>() {}.getType());
		//规范返回字段
		List list_change=list.stream().map(x->new ChangeOnePerPO() {{
				setTitle(x.getPtitle());
				setDescribe(x.getPdes());
				setCompanyId(x.getPcompany());
				setUserId(x.getPuserid());
				setPerList(x.getPperlist());
				setBussion(x.getPbussion());
				setId(x.getId());
				setIsDel(x.getIsdel());
				setDeletePerson(x.getIsdel_per());
				setCreateTime(x.getCreate_time());
				setUpdateTime(x.getUpdate_time());
		}}).collect(Collectors.toList());
		return MsgTemplate.successMsg(list_change);
	}

	/**
	 * 修改权限包
	 */
	public Map<String, Object> updatePermission(InsertOrUpdatePermissionBO param) {
		HttpResult result =permissionServer.updatePermission(param);
		return MsgTemplate.customMsg(result);
	}

}
