package com.djcps.wms.provider.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.base.BaseListPartnerIdBO;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.fluentvalidator.ValidateNotNullInteger;
import com.djcps.wms.commons.fluentvalidator.ValidateNullInteger;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtable.enums.LoadingTableMsgEnum;
import com.djcps.wms.provider.model.AddProviderBO;
import com.djcps.wms.provider.model.DeleteProviderBO;
import com.djcps.wms.provider.model.SelectProviderByAttributeBO;
import com.djcps.wms.provider.model.UpdateProviderBO;
import com.djcps.wms.provider.service.ProviderService;
import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;
import java.util.Map;

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
	
	private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(ProviderController.class);
	
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
			LOGGER.debug("json : " + json);
			AddProviderBO param = gson.fromJson(json, AddProviderBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			LOGGER.debug("AddProviderBO : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<AddProviderBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					//名称30个字
					.on(param.getName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,30))
					//简称10个字
					.on(param.getShortName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					//备注50个字
					.on(param.getRemark(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,50))
					//网站地址50个字
					.on(param.getWebUrl(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,50))
					//联系人10个字
					.on(param.getContacts(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					//固定电话15个字
					.on(param.getTel(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,15))
					//手机11个字
					.on(param.getPhone(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,11))
					//传真15个字
					.on(param.getFax(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,15))
					//邮箱30个字
					.on(param.getEmail(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,30))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return providerService.add(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
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
			LOGGER.debug("json : " + json);
			UpdateProviderBO param = gson.fromJson(json, UpdateProviderBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			LOGGER.debug("UpdateProviderVO : " + param.toString());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<UpdateProviderBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					//名称30个字
					.on(param.getName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,30))
					//简称10个字
					.on(param.getShortName().length(),new ValidateNotNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					//备注50个字
					.on(param.getRemark(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,50))
					//网站地址50个字
					.on(param.getWebUrl(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,50))
					//联系人10个字ss
					.on(param.getContacts(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,10))
					//固定电话15个字
					.on(param.getTel(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,15))
					//手机11个字
					.on(param.getPhone(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,11))
					//传真15个字
					.on(param.getFax(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,15))
					//邮箱30个字
					.on(param.getEmail(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,30))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return providerService.modify(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
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
			LOGGER.debug("json : " + json);
			DeleteProviderBO param = gson.fromJson(json, DeleteProviderBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			LOGGER.debug("DeleteProviderBO : " + param.toString());
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
			LOGGER.error(e.getMessage());
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
			LOGGER.debug("json : " + json);
			BaseListPartnerIdBO param = gson.fromJson(json, BaseListPartnerIdBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			return providerService.getAllList(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
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
			LOGGER.debug("json : " + json);
			SelectProviderByAttributeBO param = gson.fromJson(json, SelectProviderByAttributeBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBean,param);
			return providerService.getProviderByAttribute(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
}
