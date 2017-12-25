package com.djcps.wms.loadingtable.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.commons.base.BaseListBO;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtable.model.AddLoadingTableBO;
import com.djcps.wms.loadingtable.model.DeleteLoadingTableBO;
import com.djcps.wms.loadingtable.model.IsUseLoadingTableBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByIdBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByAttributeBO;
import com.djcps.wms.loadingtable.model.UpdateLoadingTableBO;
import com.djcps.wms.loadingtable.server.LoadingTableServer;
import com.djcps.wms.loadingtable.service.LoadingTableService;
import com.google.gson.Gson;


/**
 * @title:装车台service层实现类
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
@Service
public class LoadingTableServiceImpl implements LoadingTableService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoadingTableServiceImpl.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private LoadingTableServer loadingTableServer;

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
	public Map<String, Object> getAllList(BaseListBO baseListParam){
		HttpResult result = loadingTableServer.getAllList(baseListParam);
		return MsgTemplate.customMsg(result);
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
}
