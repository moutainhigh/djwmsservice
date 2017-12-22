package com.djcps.wms.warehouse.controller;

import java.util.ArrayList;
import java.util.List;
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
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.fluentvalidator.ValidateNotNullInteger;
import com.djcps.wms.commons.model.PartnerInfoBo;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtable.enums.LoadingTableMsgEnum;
import com.djcps.wms.warehouse.model.area.AddAreaBO;
import com.djcps.wms.warehouse.model.area.AddAreaDetailBO;
import com.djcps.wms.warehouse.model.area.AreaCode;
import com.djcps.wms.warehouse.model.area.CountyBo;
import com.djcps.wms.warehouse.model.area.DeleteAreaBO;
import com.djcps.wms.warehouse.model.area.ProvinceCityBo;
import com.djcps.wms.warehouse.model.area.SelectAllAreaList;
import com.djcps.wms.warehouse.model.area.StreetBo;
import com.djcps.wms.warehouse.model.area.UpdateAreaBO;
import com.djcps.wms.warehouse.model.area.UpdateAreaDetailBO;
import com.djcps.wms.warehouse.model.warehouse.SelectWarehouseByIdBO;
import com.djcps.wms.warehouse.service.AreaService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @title:仓库库区管理控制层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月22日
 */
@RestController
@RequestMapping(value = "/warehouse")
public class AreaController {
	private static final Logger logger = LoggerFactory.getLogger(AreaController.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	private AreaService areaService;
	
	/**
	 * @title:新增库区
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="新增库区",value = "/addArea", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> addArea(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			//数据校验
			ProvinceCityBo param = gson.fromJson(json, ProvinceCityBo.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<ProvinceCityBo>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					//库区名称
					.on(param.getName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			//组织数据
			AddAreaBO addArea = new AddAreaBO();
			List<AddAreaDetailBO> addDetailList = new ArrayList<AddAreaDetailBO>();
			//用户对象属性赋值
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(param,addArea);
			BeanUtils.copyProperties(partnerInfoBean,addArea);
			
			String areaDetailListJson = gson.toJson(param.getCountyList());
			List<CountyBo> retList = gson.fromJson(areaDetailListJson,new TypeToken<List<CountyBo>>(){}.getType()); 
			for (CountyBo areaBo : retList) {
				String streetListJson = gson.toJson(areaBo.getStreetList());
				List<StreetBo> streetList = gson.fromJson(streetListJson,new TypeToken<List<StreetBo>>(){}.getType());
				for (StreetBo street : streetList) {
					AddAreaDetailBO areaDetai = new AddAreaDetailBO();
					//把仓库编号,库区编号,合作方id赋值
					BeanUtils.copyProperties(param,areaDetai);
					BeanUtils.copyProperties(addArea,areaDetai);
					BeanUtils.copyProperties(areaBo,areaDetai);
					BeanUtils.copyProperties(street,areaDetai);
					addDetailList.add(areaDetai);
				}
			}
			addArea.setCountyList(addDetailList);
			
			//数据组织完成之后对组织的数据进行校验
			ComplexResult otherRet = FluentValidator.checkAll().failFast()
					.on(addArea,
							new HibernateSupportedValidator<AddAreaBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					//库区名称
					.on(addArea.getName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(otherRet);
			}
			return areaService.addArea(addArea);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:修改库区
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="修改库区",value = "/modifyArea", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> modifyArea(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			//数据校验
			ProvinceCityBo param = gson.fromJson(json, ProvinceCityBo.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<ProvinceCityBo>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					//库区名称
					.on(param.getName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			//组织数据
			UpdateAreaBO updateArea = new UpdateAreaBO();
			List<UpdateAreaDetailBO> updateDetailList = new ArrayList<UpdateAreaDetailBO>();
			//用户对象属性赋值
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(param,updateArea);
			BeanUtils.copyProperties(partnerInfoBean,updateArea);
			
			String areaDetailListJson = gson.toJson(param.getCountyList());
			List<CountyBo> retList = gson.fromJson(areaDetailListJson,new TypeToken<List<CountyBo>>(){}.getType()); 
			for (CountyBo areaBo : retList) {
				String streetListJson = gson.toJson(areaBo.getStreetList());
				List<StreetBo> streetList = gson.fromJson(streetListJson,new TypeToken<List<StreetBo>>(){}.getType());
				for (StreetBo street : streetList) {
					UpdateAreaDetailBO areaDetai = new UpdateAreaDetailBO();
					//把仓库编号,库区编号,合作方id赋值
					BeanUtils.copyProperties(param,areaDetai);
					BeanUtils.copyProperties(updateArea,areaDetai);
					BeanUtils.copyProperties(areaBo,areaDetai);
					BeanUtils.copyProperties(street,areaDetai);
					updateDetailList.add(areaDetai);
				}
			}
			updateArea.setCountyList(updateDetailList);
			
			//数据组织完成之后对组织的数据进行校验
			ComplexResult otherRet = FluentValidator.checkAll().failFast()
					.on(updateArea,
							new HibernateSupportedValidator<UpdateAreaBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					//库区名称
					.on(updateArea.getName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(otherRet);
			}
			return areaService.modifyArea(updateArea);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:删除库区
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="删除库区",value = "/deleteArea", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> deleteArea(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			DeleteAreaBO param = gson.fromJson(json, DeleteAreaBO.class);
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			logger.debug("DeleteWarehouseBO : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<DeleteAreaBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return areaService.deleteArea(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:获取所有库区列表
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="获取所有库区列表",value = "/getAreaAllList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAreaAllList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			SelectAllAreaList param = gson.fromJson(json, SelectAllAreaList.class);
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			return areaService.getAreaAllList(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:根据库区id获取库区
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="根据库区id获取库区",value = "/getAreaById", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAreaById(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			SelectWarehouseByIdBO param = gson.fromJson(json, SelectWarehouseByIdBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<SelectWarehouseByIdBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return areaService.getAreaById(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 获取库区编码
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年12月18日
	 */
	@RequestMapping(name="获取库区编码",value = "/getAreaCode",method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAreaCode(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			PartnerInfoBo partnerInfoBo=(PartnerInfoBo) request.getAttribute("partnerInfo");
			AreaCode param=gson.fromJson(json,AreaCode.class);
			logger.debug("AreaCode : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<AreaCode>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return areaService.getAreaCode(partnerInfoBo,param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
}
