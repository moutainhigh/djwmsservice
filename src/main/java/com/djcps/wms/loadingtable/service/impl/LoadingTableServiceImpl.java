package com.djcps.wms.loadingtable.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.base.BaseListPartnerIdBO;
import com.djcps.wms.commons.base.BaseVO;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.inneruser.model.param.UserInfoBO;
import com.djcps.wms.inneruser.model.result.UserRelevancePO;
import com.djcps.wms.inneruser.server.UserServer;
import com.djcps.wms.loadingtable.model.AddLoadingTableBO;
import com.djcps.wms.loadingtable.model.DeleteLoadingTableBO;
import com.djcps.wms.loadingtable.model.GetUserListBO;
import com.djcps.wms.loadingtable.model.IsUseLoadingTableBO;
import com.djcps.wms.loadingtable.model.LoadingTablePO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByIdBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByAttributeBO;
import com.djcps.wms.loadingtable.model.UpdateLoadingTableBO;
import com.djcps.wms.loadingtable.model.UserPO;
import com.djcps.wms.loadingtable.server.LoadingTableServer;
import com.djcps.wms.loadingtable.service.LoadingTableService;
import com.djcps.wms.role.enums.RoleTypeEnum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


/**
 * @title:装车台service层实现类
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
@Service
public class LoadingTableServiceImpl implements LoadingTableService {
	
	private Gson gson = GsonUtils.gson;
	
	@Autowired
	private LoadingTableServer loadingTableServer;
	@Autowired
	private UserServer userServer;

	@Override
	public Map<String, Object> add(AddLoadingTableBO loadingTable) {
		HttpResult result = loadingTableServer.add(loadingTable);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> modify(UpdateLoadingTableBO loadingTable){
		HttpResult result = loadingTableServer.modify(loadingTable);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> delete(DeleteLoadingTableBO loadingTable){
		HttpResult result = loadingTableServer.delete(loadingTable);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAllList(BaseListPartnerIdBO baseListParam){
		HttpResult result = loadingTableServer.getAllList(baseListParam);
		BaseVO baseVO = null;
		if(!ObjectUtils.isEmpty(result.getData())){
			baseVO = gson.fromJson(gson.toJson(result.getData()), BaseVO.class);
		}else{
			baseVO = new BaseVO();
			baseVO.setResult(null);
			baseVO.setTotal(0);
		}
		return MsgTemplate.successMsg(baseVO);
	}

	@Override
	public Map<String, Object> getLoadingTableByAttribute(SelectLoadingTableByAttributeBO loadingTable){
		HttpResult result = loadingTableServer.getLoadingTableByAttribute(loadingTable);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getLoadingTableById(SelectLoadingTableByIdBO loadingTable){
		HttpResult result = loadingTableServer.getLoadingTableById(loadingTable);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> enable(IsUseLoadingTableBO loadingTable){
		HttpResult result = loadingTableServer.enable(loadingTable);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> disable(IsUseLoadingTableBO loadingTable){
		HttpResult result = loadingTableServer.disable(loadingTable);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getnumber(int count) {
		HttpResult result=loadingTableServer.getNumber(count);
		return  MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getUserList(GetUserListBO param) {
		UserInfoBO userInfoBO = new UserInfoBO();
		userInfoBO.setRoleTypeCode(Arrays.asList(RoleTypeEnum.ROLE_TYPE_7.getValue()));
		userInfoBO.setPartnerId(param.getPartnerId());
		List<UserRelevancePO> userRelevancePOList = userServer.listUserByRoleCode(userInfoBO);
        return MsgTemplate.successMsg(userRelevancePOList);
	}

}
