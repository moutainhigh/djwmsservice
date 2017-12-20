package com.djcps.wms.address.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.wms.address.model.ProvinceCityAreaCodeBo;
import com.djcps.wms.address.service.AddressService;
import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.fluentvalidator.ValidateNotNullInteger;
import com.djcps.wms.commons.fluentvalidator.ValidateNullInteger;
import com.djcps.wms.commons.model.PartnerInfoBo;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtable.enums.LoadingTableMsgEnum;
import com.djcps.wms.provider.model.AddProviderBO;
import com.djcps.wms.provider.model.DeleteProviderBO;
import com.djcps.wms.provider.model.SelectProviderByAttributeBO;
import com.djcps.wms.provider.model.UpdateProviderVO;
import com.djcps.wms.provider.service.ProviderService;
import com.djcps.wms.warehouse.controller.WarehouseController;
import com.google.gson.Gson;

/**
 * @title:地址控制层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月23日
 */
@RestController
@RequestMapping(value = "/address")
public class AddressController {
	
	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	private AddressService addressService;
	
	/**
	 * 获取所有的省份列表
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年12月4日
	 */
	@RequestMapping(name="获取所有的省份列表",value = "/getProvinceAllList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getProvinceAllList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			ProvinceCityAreaCodeBo param = new ProvinceCityAreaCodeBo();
			param.setCode("0");
			return addressService.getProvinceAllList(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 根据省份获取所有的城市列表
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年12月4日
	 */
	@RequestMapping(name="根据省份获取所有的城市列表", value = "/getCityListByProvince", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getCityListByProvince(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			ProvinceCityAreaCodeBo param = gson.fromJson(json, ProvinceCityAreaCodeBo.class);
			return addressService.getCityListByProvince(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 根据城市获取所有的区(县)列表
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年12月4日
	 */
	@RequestMapping(name="根据城市获取所有的区(县)列表",value = "/getAreaListByCity", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAreaListByCity(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			ProvinceCityAreaCodeBo param = gson.fromJson(json, ProvinceCityAreaCodeBo.class);
			return addressService.getAreaListByCity(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 根据区(县)获取所有的街道列表
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年12月13日
	 */
	@RequestMapping(name="根据区(县)获取所有的街道列表",value = "/getStreeListByArea", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getStreeListByArea(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			ProvinceCityAreaCodeBo param = gson.fromJson(json, ProvinceCityAreaCodeBo.class);
			return addressService.getStreeListByArea(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
}
