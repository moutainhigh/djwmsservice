package com.djcps.wms.provider.controller;

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
import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.fluentvalidator.ValidateNotNullInteger;
import com.djcps.wms.commons.fluentvalidator.ValidateNullInteger;
import com.djcps.wms.commons.model.PartnerInfoBean;
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
 * @title:供应商控制层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月23日
 */
@RestController
@RequestMapping(value = "/provider")
public class ProviderController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProviderController.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	private ProviderService providerService;
	
	/**
	 * @title:新增供应商档案
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(name="新增供应商档案",value = "/add", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> add(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			AddProviderBO param = gson.fromJson(json, AddProviderBO.class);
			PartnerInfoBean partnerInfoBean = new PartnerInfoBean();
			BeanUtils.copyProperties(partnerInfoBean,param);
			logger.debug("AddProviderBO : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<AddProviderBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					//名称30个字
					.on(param.getName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,30))
					//简称10个字
					.on(param.getShortName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					//备注50个字
					.on(param.getRemark().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,50))
					//网站地址50个字
					.on(param.getWebUrl().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,50))
					//联系人10个字
					.on(param.getContacts().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					//固定电话15个字
					.on(param.getTel().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,15))
					//手机11个字
					.on(param.getPhone().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,11))
					//传真15个字
					.on(param.getFax().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,15))
					//邮箱30个字
					.on(param.getEmail().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,30))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return providerService.add(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:修改供应商档案
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(name="修改供应商档案",value = "/modify", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> modify(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			UpdateProviderVO param = gson.fromJson(json, UpdateProviderVO.class);
			PartnerInfoBean partnerInfoBean = new PartnerInfoBean();
			BeanUtils.copyProperties(partnerInfoBean,param);
			logger.debug("UpdateProviderVO : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<UpdateProviderVO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					//名称30个字
					.on(param.getName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,30))
					//简称10个字
					.on(param.getShortName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					//备注50个字
					.on(param.getRemark().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,50))
					//网站地址50个字
					.on(param.getWebUrl().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,50))
					//联系人10个字
					.on(param.getContacts().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					//固定电话15个字
					.on(param.getTel().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,15))
					//手机11个字
					.on(param.getPhone().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,11))
					//传真15个字
					.on(param.getFax().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,15))
					//邮箱30个字
					.on(param.getEmail().length(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,30))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return providerService.modify(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:删除供应商档案
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(name="删除供应商档案",value = "/delete", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> delete(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			DeleteProviderBO param = gson.fromJson(json, DeleteProviderBO.class);
			PartnerInfoBean partnerInfoBean = new PartnerInfoBean();
			BeanUtils.copyProperties(partnerInfoBean,param);
			logger.debug("DeleteProviderBO : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<DeleteProviderBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return providerService.delete(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:获取所有供应商档案列表
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(name="获取所有供应商档案列表",value = "/getAllList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAllList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			BaseListParam param = gson.fromJson(json, BaseListParam.class);
			return providerService.getAllList(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * @title:根据供应商属性模糊查询
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(name="根据供应商属性模糊查询",value = "/getByProviderAttribute", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getByProviderAttribute(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			SelectProviderByAttributeBO param = gson.fromJson(json, SelectProviderByAttributeBO.class);
			return providerService.getProviderByAttribute(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
}
