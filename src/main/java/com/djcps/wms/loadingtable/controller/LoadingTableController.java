package com.djcps.wms.loadingtable.controller;

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
import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.fluentvalidator.ValidateNotNullInteger;
import com.djcps.wms.commons.model.PartnerInfoBo;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtable.enums.LoadingTableMsgEnum;
import com.djcps.wms.loadingtable.model.AddLoadingTableBO;
import com.djcps.wms.loadingtable.model.DeleteLoadingTableBO;
import com.djcps.wms.loadingtable.model.IsUseLoadingTableBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByIdBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByAttributeBO;
import com.djcps.wms.loadingtable.model.UpdateLoadingTableBO;
import com.djcps.wms.loadingtable.service.LoadingTableService;
import com.google.gson.Gson;

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
	@RequestMapping(name="新增装车台",value = "/add", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> add(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			AddLoadingTableBO loadingTable = gson.fromJson(json, AddLoadingTableBO.class);
			PartnerInfoBo partnerInfoBean = new PartnerInfoBo();
			BeanUtils.copyProperties(partnerInfoBean,loadingTable);
			logger.debug("loadingTable : " + loadingTable.toString());
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(loadingTable,
							new HibernateSupportedValidator<AddLoadingTableBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.on(loadingTable.getName().length(),
							new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
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
	@RequestMapping(name="修改装车台",value = "/modify", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> modify(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			UpdateLoadingTableBO loadingTable  = gson.fromJson(json, UpdateLoadingTableBO.class);
			PartnerInfoBo partnerInfoBean = new PartnerInfoBo();
			BeanUtils.copyProperties(partnerInfoBean,loadingTable);
			logger.debug("loadingTable : " + loadingTable.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(loadingTable,
							new HibernateSupportedValidator<UpdateLoadingTableBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.on(loadingTable.getName().length(),
							new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
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
	@RequestMapping(name="删除装车台",value = "/delete", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> delete(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			DeleteLoadingTableBO loadingTable  = gson.fromJson(json, DeleteLoadingTableBO.class);
			PartnerInfoBo partnerInfoBean = new PartnerInfoBo();
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
	@RequestMapping(name="获取所有装车台",value = "/getAllList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAllList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			PartnerInfoBo attribute =(PartnerInfoBo)request.getAttribute("partnerInfo");
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
	 * @title:根据id获取指定装车台
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(name="根据id获取指定装车台",value = "/getLoadingTableById", method = RequestMethod.POST, produces = "application/json")
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
	 * @title:根据装车台属性模糊查询
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(name="根据装车台属性模糊查询",value = "/getLoadingTableByAttribute", method = RequestMethod.POST, produces = "application/json")
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
	@RequestMapping(name="启用装车台",value = "/enable", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> enable(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			IsUseLoadingTableBO loadingTable  = gson.fromJson(json, IsUseLoadingTableBO.class);
			PartnerInfoBo partnerInfoBean = new PartnerInfoBo();
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
	@RequestMapping(name="禁用装车台",value = "/disable", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> disable(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			IsUseLoadingTableBO loadingTable  = gson.fromJson(json, IsUseLoadingTableBO.class);
			PartnerInfoBo partnerInfoBean = new PartnerInfoBo();
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
