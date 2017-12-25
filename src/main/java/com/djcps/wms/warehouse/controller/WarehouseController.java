package com.djcps.wms.warehouse.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;

import com.djcps.wms.commons.model.GetCodeBO;
import com.djcps.wms.warehouse.model.warehouse.*;
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
import com.djcps.wms.warehouse.service.WarehouseService;
import com.google.gson.Gson;

/**
 * @title:仓库管理控制层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月22日
 */
@RestController
@RequestMapping(value = "/warehouse")
public class WarehouseController {
	private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	private WarehouseService warehouseService;

	/**
	 * @title:新增仓库
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="新增仓库",value = "/add", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> add(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			AddWarehouseBO param = gson.fromJson(json, AddWarehouseBO.class);
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			logger.debug("AddWarehouseBO : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<AddWarehouseBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.on(param.getName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					//联系人10个字符
					.on(param.getContacts().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					//备注50个字符
					.on(param.getRemark().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,50))
					//手机以1开头的11位数字
					.on(param.getPhone().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,11))
					//固定电话最多15个字，只可输入数字或-
					.on(param.getTel().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,15))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return warehouseService.add(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:修改仓库
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="修改仓库",value = "/modify", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> modify(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			UpdateWarehouseBO param = gson.fromJson(json, UpdateWarehouseBO.class);
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			logger.debug("PartnerInfoBean : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<UpdateWarehouseBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.on(param.getName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					//联系人10个字符
					.on(param.getContacts().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					//备注50个字符
					.on(param.getRemark().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,50))
					//手机以1开头的11位数字
					.on(param.getPhone().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,11))
					//固定电话最多15个字，只可输入数字或-
					.on(param.getTel().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,15))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return warehouseService.modify(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:删除仓库
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="删除仓库",value = "/delete", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> delete(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			DeleteWarehouseBO param = gson.fromJson(json, DeleteWarehouseBO.class);
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			logger.debug("DeleteWarehouseBO : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<DeleteWarehouseBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return warehouseService.delete(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
		
	/**
	 * @title:启用仓库
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="启用仓库",value = "/enable", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> enable(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			IsUseWarehouseBO param = gson.fromJson(json, IsUseWarehouseBO.class);
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			logger.debug("IsUseWarehouseBO : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<IsUseWarehouseBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return warehouseService.enable(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:禁用仓库
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="禁用仓库",value = "/disable", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> disable(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			IsUseWarehouseBO param = gson.fromJson(json, IsUseWarehouseBO.class);
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			logger.debug("IsUseWarehouseBO : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<IsUseWarehouseBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return warehouseService.disable(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:获取所有仓库列表
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="获取所有仓库列表",value = "/getAllList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAllList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			BaseListParam param = gson.fromJson(json, BaseListParam.class);
			return warehouseService.getAllList(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:根据id获取仓库
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="根据id获取仓库",value = "/getWarehouseById", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getWarehouseById(@RequestBody(required = false) String json, HttpServletRequest request) {
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
			return warehouseService.getWarehouseById(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:根据仓库属性模糊查询
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月22日
	 */
	@RequestMapping(name="根据仓库属性模糊查询",value = "/getWarehouseByAttribute", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getWarehouseByAttribute(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			SelectWarehouseByAttributeBO param = gson.fromJson(json, SelectWarehouseByAttributeBO.class);
			return warehouseService.getWarehouseByAttribute(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 获取所有仓库类型
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年12月1日
	 */
	@RequestMapping(name="获取所有仓库类型",value = "/getWarehouseType", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getWarehouseType(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			String str = partnerInfoBean.getPartnerId();
			//该方法查询只需要传合作方id即可
			String partnerId = "{\"partnerId\":"+str+"}";
			return warehouseService.getWarehouseType(partnerId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 获取所有的仓库名称
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年12月12日
	 */
	@RequestMapping(name="获取所有的仓库名称",value = "/getAllWarehouseName", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAllWarehouseName(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			String str = partnerInfoBean.getPartnerId();
			//该方法查询只需要传合作方id即可
			String partnerId = "{\"partnerId\":"+str+"}";
			return warehouseService.getAllWarehouseName(partnerId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 获取仓库编码
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年12月19日
	 */
	@RequestMapping(name="获取仓库编码",value = "/getWarehouseCode", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getWarehouseCode(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			GetCodeBO getCodeBO=gson.fromJson(json,GetCodeBO.class);
			PartnerInfoBo partnerInfoBean = (PartnerInfoBo) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,getCodeBO);
			return  warehouseService.getWarehouseCode(getCodeBO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
}
