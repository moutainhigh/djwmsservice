package com.djcps.wms.warehouse.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;

import com.djcps.wms.commons.model.GetCodeBO;
import com.djcps.wms.warehouse.model.area.AreaCode;
import com.djcps.wms.warehouse.model.location.*;
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
import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.fluentvalidator.ValidateNotNullInteger;
import com.djcps.wms.commons.fluentvalidator.ValidateNullInteger;
import com.djcps.wms.commons.model.PartnerInfoBo;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtable.enums.LoadingTableMsgEnum;
import com.djcps.wms.warehouse.model.warehouse.AddWarehouseBO;
import com.djcps.wms.warehouse.model.warehouse.DeleteWarehouseBO;
import com.djcps.wms.warehouse.model.warehouse.IsUseWarehouseBO;
import com.djcps.wms.warehouse.model.warehouse.SelectWarehouseByAttributeBO;
import com.djcps.wms.warehouse.model.warehouse.SelectWarehouseByIdBO;
import com.djcps.wms.warehouse.model.warehouse.UpdateWarehouseBO;
import com.djcps.wms.warehouse.server.LocationServer;
import com.djcps.wms.warehouse.service.LocationService;
import com.djcps.wms.warehouse.service.WarehouseService;
import com.google.gson.Gson;

/**
 * @title:库位管理控制层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月22日
 */
@RestController
@RequestMapping(value = "/warehouse")
public class LocationController {
	private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	private LocationService locationService;
	
	/**
	 * @title:新增库位
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="新增库位",value = "/addLocation", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> addLocation(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			AddLocationBO param = gson.fromJson(json, AddLocationBO.class);
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			logger.debug("AddWarehouseBO : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<AddLocationBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					//库区名称
					.on(param.getWarehouseAreaName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return locationService.addLocation(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:修改库位
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="修改库位",value = "/modifyLocation", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> modifyLocation(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			UpdateLocationBO param = gson.fromJson(json, UpdateLocationBO.class);
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			logger.debug("PartnerInfoBean : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<UpdateLocationBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return locationService.modifyLocation(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:删除库位
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="删除库位",value = "/deleteLocation", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> deleteLocation(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			DeleteLocationBO param = gson.fromJson(json, DeleteLocationBO.class);
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			logger.debug("DeleteWarehouseBO : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<DeleteLocationBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return locationService.deleteLocation(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
		
	
	/**
	 * @title:获取所有库位
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="获取所有库位",value = "/getLocationAllList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getLocationAllList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			SelectAllLocationList param = gson.fromJson(json, SelectAllLocationList.class);
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			return locationService.getLocationAllList(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:根据库位属性模糊查询
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="根据库位属性模糊查询",value = "/getLocationByAttribute", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getLocationByAttribute(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			SelectLocationByAttributeBO param = gson.fromJson(json, SelectLocationByAttributeBO.class);
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			return locationService.getLocationByAttribute(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 获取库位编码
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @update:wzy
	 * @date:2017年12月19日
	 */
	@RequestMapping(name="获取库位编码",value = "/getLocationCode", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getLocationCode(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			LocationBo param=gson.fromJson(json,LocationBo.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<LocationBo>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}

			GetCodeBO getCodeBO=new GetCodeBO();
			getCodeBO.setCodeType("3");
			getCodeBO.setPartnerId(partnerInfoBean.getPartnerId());
			getCodeBO.setVersion(partnerInfoBean.getVersion());
			getCodeBO.setWarehouseId(param.getWarehouseId());
			getCodeBO.setWarehouseAreaId(param.getWarehouseAreaId());
			return locationService.getLocationCode(partnerInfoBean,param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
}
