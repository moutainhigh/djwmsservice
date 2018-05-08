package com.djcps.wms.address.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.address.model.ProvinceCityAreaCodeBO;
import com.djcps.wms.address.server.AddressServer;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;

/**
 * 地址业务层实现类
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressServer addressServer;

	@Override
	public Map<String, Object> getProvinceAllList(ProvinceCityAreaCodeBO param) {
		HttpResult result = addressServer.getProvinceAllList(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getCityListByProvince(ProvinceCityAreaCodeBO param) {
		HttpResult result = addressServer.getCityListByProvince(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAreaListByCity(ProvinceCityAreaCodeBO param) {
		HttpResult result = addressServer.getAreaListByCity(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getStreeListByArea(ProvinceCityAreaCodeBO param) {
		HttpResult result = addressServer.getStreeListByArea(param);
		return MsgTemplate.customMsg(result);
	}
	

}
