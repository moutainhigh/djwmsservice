package com.djcps.wms.loadingtable.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
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
import com.djcps.wms.commons.fluentvalidator.ValidateInteger;
import com.djcps.wms.commons.model.PartnerInfoBean;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtable.enums.LoadingTableMsgEmum;
import com.djcps.wms.loadingtable.model.AddLoadingTableBO;
import com.djcps.wms.loadingtable.model.DeleteLoadingTableBO;
import com.djcps.wms.loadingtable.model.IsUseLoadingTableBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByIdBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByAttributeBO;
import com.djcps.wms.loadingtable.model.UpdateLoadingTableBO;
import com.djcps.wms.loadingtable.service.LoadingTableService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * @title:装车台控制层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
@RestController
@RequestMapping(value = "/loadingTable")
public class LoadingTableController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoadingTableController.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	private LoadingTableService loadingTableService;
	
	/**
	 * @title:新增装车台
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> add(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			AddLoadingTableBO loadingTable = gson.fromJson(json, AddLoadingTableBO.class);
			PartnerInfoBean partnerInfoBean = new PartnerInfoBean();
			BeanUtils.copyProperties(partnerInfoBean,loadingTable);
			logger.debug("loadingTable : " + loadingTable.toString());
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(loadingTable,
							new HibernateSupportedValidator<AddLoadingTableBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.on(loadingTable.getName().length(),
							new ValidateInteger(LoadingTableMsgEmum.LENGTH_BEYOND,10))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return loadingTableService.add(loadingTable);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:修改装车台
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> modify(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			UpdateLoadingTableBO loadingTable  = gson.fromJson(json, UpdateLoadingTableBO.class);
			PartnerInfoBean partnerInfoBean = new PartnerInfoBean();
			BeanUtils.copyProperties(partnerInfoBean,loadingTable);
			logger.debug("loadingTable : " + loadingTable.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(loadingTable,
							new HibernateSupportedValidator<UpdateLoadingTableBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.on(loadingTable.getName().length(),
							new ValidateInteger(LoadingTableMsgEmum.LENGTH_BEYOND,10))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return loadingTableService.modify(loadingTable);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:删除装车台
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> delete(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			DeleteLoadingTableBO loadingTable  = gson.fromJson(json, DeleteLoadingTableBO.class);
			PartnerInfoBean partnerInfoBean = new PartnerInfoBean();
			BeanUtils.copyProperties(partnerInfoBean,loadingTable);
			logger.debug("loadingTable : " + loadingTable.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(loadingTable,
							new HibernateSupportedValidator<DeleteLoadingTableBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return loadingTableService.delete(loadingTable);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:获取所有装车台
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(value = "/getAllList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAllList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			BaseListParam baseListParam  = gson.fromJson(json, BaseListParam.class);
			return loadingTableService.getAllList(baseListParam);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:根据装车台编号获取指定装车台
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(value = "/getLoadingTableById", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getLoadingTableById(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			SelectLoadingTableByIdBO loadingTable  = gson.fromJson(json, SelectLoadingTableByIdBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(loadingTable,
							new HibernateSupportedValidator<SelectLoadingTableByIdBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return loadingTableService.getLoadingTableById(loadingTable);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:根据装车台属性模糊查询获得装车台信息
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(value = "/getLoadingTableByAttribute", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getLoadingTableByAttribute(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			SelectLoadingTableByAttributeBO loadingTable  = gson.fromJson(json, SelectLoadingTableByAttributeBO.class);
			return loadingTableService.getLoadingTableByAttribute(loadingTable);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:启用装车台
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(value = "/enable", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> enable(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			IsUseLoadingTableBO loadingTable  = gson.fromJson(json, IsUseLoadingTableBO.class);
			PartnerInfoBean partnerInfoBean = new PartnerInfoBean();
			BeanUtils.copyProperties(partnerInfoBean,loadingTable);
			logger.debug("loadingTable : " + loadingTable.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(loadingTable,
							new HibernateSupportedValidator<IsUseLoadingTableBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return loadingTableService.enable(loadingTable);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:禁用装车台
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(value = "/disable", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> disable(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			IsUseLoadingTableBO loadingTable  = gson.fromJson(json, IsUseLoadingTableBO.class);
			PartnerInfoBean partnerInfoBean = new PartnerInfoBean();
			BeanUtils.copyProperties(partnerInfoBean,loadingTable);
			logger.debug("loadingTable : " + loadingTable.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(loadingTable,
							new HibernateSupportedValidator<IsUseLoadingTableBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return loadingTableService.disable(loadingTable);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
}
