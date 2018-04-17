package com.djcps.wms.permission.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSONObject;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.permission.model.BaseOrgBO;
import com.djcps.wms.permission.model.DeletePermissionBO;
import com.djcps.wms.permission.model.GetPermissionBO;
import com.djcps.wms.permission.model.GetPermissionChooseBO;
import com.djcps.wms.permission.model.GetUserByPermissionIdBO;
import com.djcps.wms.permission.model.InsertOrUpdatePermissionBO;
import com.djcps.wms.permission.model.UserIdBO;
import com.djcps.wms.permission.model.UserInfoPO;
import com.djcps.wms.permission.model.UserRelevancePO;
import com.djcps.wms.permission.server.PermissionServer;
import com.djcps.wms.permission.service.PermissionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.http.client.domain.UserInfo;

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
	public Map<String, Object> getPermissionList(GetPermissionBO param) {
		//获得组合权限的请求列表
		HttpResult result =permissionServer.getPermissionList(param);
		return MsgTemplate.customMsg(result);
	}

	/**
	 * 得到WMS权限
	 */
	public Map<String, Object> getWmsPermission(BaseOrgBO param) {
		HttpResult result =permissionServer.getWmsPermission(param);
		return MsgTemplate.customMsg(result);
	}

	/**
	 * 新增/修改权限
	 * id为空则为新增，id不为空为修改
	 */
	public Map<String, Object> insertOrUpdatePermission(InsertOrUpdatePermissionBO param) {
		HttpResult result =permissionServer.insertOrUpdatePermission(param);
		return MsgTemplate.customMsg(result);
	}

	/**
	 * 删除权限包
	 * 如果有用户关联权限包且不处于空闲状态，则不能删除
	 * 代码效率有待测试
	 */
	public Map<String, Object> deletePermission(DeletePermissionBO param,String partnerId) {
		//组织参数,获取关联了该权限的用户
		GetUserByPermissionIdBO getUser=new GetUserByPermissionIdBO();
		BeanUtils.copyProperties(param, getUser);
		HttpResult result_user =permissionServer.getUserByPermissionId(getUser);
		//如果有用户关联该权限，则进行后续判断
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
		}
		//如果没有用户关联或者都处于空闲，则可以删除
		HttpResult result =permissionServer.deletePermission(param);
		return MsgTemplate.customMsg(result);
	}

	/**
	 * 根据组合权限id和公司id，获取获取组合权限集合
	 */
	public Map<String, Object> getPerChoose(GetPermissionChooseBO param) {
		HttpResult result =permissionServer.getPerChoose(param);
		return MsgTemplate.customMsg(result);
	}

}
