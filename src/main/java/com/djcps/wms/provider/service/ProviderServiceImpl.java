package com.djcps.wms.provider.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.provider.controller.ProviderController;
import com.djcps.wms.provider.model.AddProviderBO;
import com.djcps.wms.provider.model.DeleteProviderBO;
import com.djcps.wms.provider.model.ProvinceCityAreaCodeBo;
import com.djcps.wms.provider.model.SelectProviderByAttributeBO;
import com.djcps.wms.provider.model.UpdateProviderVO;
import com.djcps.wms.provider.server.ProviderServer;
import com.google.gson.Gson;

/**
 * 供应商业务层实现类
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
@Service
public class ProviderServiceImpl implements ProviderService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProviderServiceImpl.class);	
	
	private Gson gson = new Gson();

	@Autowired
	private ProviderServer providerServer;

	@Override
	public Map<String, Object> add(AddProviderBO addBean) throws Exception {
		HttpResult result = providerServer.add(addBean);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> modify(UpdateProviderVO updateBean) throws Exception{
		HttpResult result = providerServer.modify(updateBean);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> delete(DeleteProviderBO deleteBean) throws Exception{
		HttpResult result = providerServer.delete(deleteBean);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAllList(BaseListParam baseListParam) throws Exception{
		HttpResult result = providerServer.getAllList(baseListParam);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getProviderByAttribute(SelectProviderByAttributeBO selectVagueBean) throws Exception{
		HttpResult result = providerServer.getProviderByAttribute(selectVagueBean);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getProvinceAllList(ProvinceCityAreaCodeBo param) {
		HttpResult result = providerServer.getProvinceAllList(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getCityListByProvince(ProvinceCityAreaCodeBo param) {
		HttpResult result = providerServer.getCityListByProvince(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAreaListByCity(ProvinceCityAreaCodeBo param) {
		HttpResult result = providerServer.getAreaListByCity(param);
		return MsgTemplate.customMsg(result);
	}
	

}
