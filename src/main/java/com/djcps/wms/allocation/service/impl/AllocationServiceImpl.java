package com.djcps.wms.allocation.service.impl;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.address.model.ProvinceCityAreaCodeBo;
import com.djcps.wms.allocation.model.AddAllocation;
import com.djcps.wms.allocation.server.AllocationServer;
import com.djcps.wms.allocation.service.AllocationService;
import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.base.BaseParam;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.provider.controller.ProviderController;
import com.djcps.wms.provider.model.AddProviderBO;
import com.djcps.wms.provider.model.DeleteProviderBO;
import com.djcps.wms.provider.model.SelectProviderByAttributeBO;
import com.djcps.wms.provider.model.UpdateProviderVO;
import com.djcps.wms.provider.server.ProviderServer;
import com.djcps.wms.provider.service.ProviderService;
import com.google.gson.Gson;

/**
 * 混合配货业务层实现类
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
@Service
public class AllocationServiceImpl implements AllocationService {
	
	private static final Logger logger = LoggerFactory.getLogger(AllocationServiceImpl.class);	
	
	private Gson gson = new Gson();

	@Autowired
	private AllocationServer allocationServer;

	@Override
	public Map<String, Object> getOrderType(BaseParam baseParam){
		HttpResult result = allocationServer.getOrderType(baseParam);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getChooseAllocation() {
		HttpResult result = allocationServer.getChooseAllocation();
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> saveAllocation(AddAllocation allocation) {
		HttpResult result = allocationServer.saveAllocation(allocation);
		return MsgTemplate.customMsg(result);
	}
}
