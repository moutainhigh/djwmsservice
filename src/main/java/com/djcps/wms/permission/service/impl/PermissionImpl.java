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
import com.djcps.wms.commons.httpclient.OtherHttpResult;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.permission.constants.ParamConstants;
import com.djcps.wms.permission.model.bo.BaseOrgBO;
import com.djcps.wms.permission.model.bo.DeletePermissionBO;
import com.djcps.wms.permission.model.bo.GetPermissionBO;
import com.djcps.wms.permission.model.bo.GetPermissionChooseBO;
import com.djcps.wms.permission.model.bo.GetUserByPermissionIdBO;
import com.djcps.wms.permission.model.bo.GetWmsPermissionBO;
import com.djcps.wms.permission.model.bo.InsertOrUpdatePermissionBO;
import com.djcps.wms.permission.model.bo.PermissionBO;
import com.djcps.wms.permission.model.bo.PermissionChooseBO;
import com.djcps.wms.permission.model.bo.UpdatePermissionBO;
import com.djcps.wms.permission.model.po.GetOnePermissionPO;
import com.djcps.wms.permission.model.po.GetPermissionPackagePO;
import com.djcps.wms.permission.model.po.GetWmsPerPO;
import com.djcps.wms.permission.model.vo.ChangeOnePerVO;
import com.djcps.wms.permission.model.vo.ChangePerPackageVO;
import com.djcps.wms.permission.model.vo.ChangeWmsPerVO;
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
	@Override
	public Map<String, Object> getPermissionList(PermissionBO param) {
			/*if(ObjectUtils.isEmpty(param.getKeyWord())) {
				param.setKeyWord("");
			}*/
			GetPermissionBO getPermissonBO=new GetPermissionBO();
			BeanUtils.copyProperties(param, getPermissonBO);
			getPermissonBO.setCompanyID(param.getCompanyId());
			//得到请求的数据
			OtherHttpResult result =permissionServer.getPermissionList(getPermissonBO);
			String data = JSONObject.toJSONString(result.getData());
			String countData = JSONObject.toJSONString(result.getTotal());
			Integer count =Integer.parseInt(countData);
			ArrayList<GetPermissionPackagePO> listUser = gson.fromJson(data,new TypeToken<List<GetPermissionPackagePO>>() {}.getType());
			//规范返回字段
			List listUserChange=listUser.stream().map(x->new ChangePerPackageVO() {{
					setBussion(x.getPbussion());
					setDescribe(x.getPdes());
					setId(x.getId());
					setPerList(x.getPerlist());
					setTitle(x.getPtitle());
			}}).collect(Collectors.toList());
			//组织返回数据
			BaseListPO info=new BaseListPO() {{
				setList(listUserChange);
				setTotal(count);
			}};
			return MsgTemplate.successMsg(info);
	}
	

	/**
	 * @author zhq
	 * 得到WMS权限
	 */
	@Override
	public Map<String, Object> getWmsPermission(GetWmsPermissionBO param) {
		//得到wms权限数据
		HttpResult result =permissionServer.getWmsPermission(param);
		String data = JSONObject.toJSONString(result.getData());
		ArrayList<GetWmsPerPO> listUser = gson.fromJson(data,new TypeToken<List<GetWmsPerPO>>() {}.getType());
		//规范返回字段
		List listUserChange=listUser.stream().map(x->new ChangeWmsPerVO() {{
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
		return MsgTemplate.successMsg(listUserChange);
	}

	/**
	 * 新增权限
	 * @author zhq
	 */
	@Override
	public Map<String, Object> insertPermission(UpdatePermissionBO param,PartnerInfoBO partnerInfoBo) {
		if(ObjectUtils.isEmpty(param.getId())) {
			param.setId("");
		}
		InsertOrUpdatePermissionBO insertOrUpdate = new InsertOrUpdatePermissionBO();
		BeanUtils.copyProperties(param, insertOrUpdate);
		insertOrUpdate.setCompanyID(param.getCompanyId());
		insertOrUpdate.setPdes(param.getDescribe());
		insertOrUpdate.setPtitle(param.getTitle());
		//insertOrUpdate.setCompanyID("100");
		insertOrUpdate.setUserid(partnerInfoBo.getOperatorId());
		insertOrUpdate.setPbussion(ParamConstants.BUSSION_ID);
		HttpResult result =permissionServer.insertPermission(insertOrUpdate);
		return MsgTemplate.customMsg(result);
	}

	/**
	 * @author zhq
	 * 删除权限包
	 * 如果有用户关联权限包，则不能删除
	 */
	@Override
	public Map<String, Object> deletePermission(DeletePermissionBO param) {
		//组织参数,获取关联了该权限的用户
		GetUserByPermissionIdBO getUser=new GetUserByPermissionIdBO();
		BeanUtils.copyProperties(param, getUser);
		HttpResult resultUser =permissionServer.getUserByPermissionId(getUser);
		//如果有用户关联则删除失败，否则删除
		if(!ObjectUtils.isEmpty(resultUser.getData())) {
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
	 * @author zhq
	 * 根据组合权限id和公司id，获取获取组合权限信息
	 */
	@Override
	public Map<String, Object> getPerChoose(PermissionChooseBO param) {
		GetPermissionChooseBO getPerChoose=new GetPermissionChooseBO();
		BeanUtils.copyProperties(param, getPerChoose);
		getPerChoose.setCompanyID(param.getCompanyId());
		//得到查询权限信息
		HttpResult result =permissionServer.getPerChoose(getPerChoose);
		String data = JSONObject.toJSONString(result.getData());
		ArrayList<GetOnePermissionPO> list = gson.fromJson(data,new TypeToken<List<GetOnePermissionPO>>() {}.getType());
		//规范返回字段
		List listChange=list.stream().map(x->new ChangeOnePerVO() {{
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
		return MsgTemplate.successMsg(listChange);
	}

	/**
	 * @author zhq
	 * 修改权限包
	 */
	@Override
	public Map<String, Object> updatePermission(UpdatePermissionBO param,PartnerInfoBO partnerInfoBO) {
		InsertOrUpdatePermissionBO insertOrUpdate = new InsertOrUpdatePermissionBO();
		BeanUtils.copyProperties(param, insertOrUpdate);
		insertOrUpdate.setCompanyID(param.getCompanyId());
		insertOrUpdate.setPdes(param.getDescribe());
		insertOrUpdate.setPtitle(param.getTitle());
		insertOrUpdate.setUserid(partnerInfoBO.getOperatorId());
		insertOrUpdate.setPbussion(ParamConstants.BUSSION_ID);
		HttpResult result =permissionServer.updatePermission(insertOrUpdate);
		return MsgTemplate.customMsg(result);
	}

	

}
