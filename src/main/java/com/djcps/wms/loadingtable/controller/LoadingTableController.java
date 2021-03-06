package com.djcps.wms.loadingtable.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.base.BaseListPartnerIdBO;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.fluentvalidator.ValidateNotNullInteger;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.loadingtable.model.AddLoadingTableBO;
import com.djcps.wms.loadingtable.model.DeleteLoadingTableBO;
import com.djcps.wms.loadingtable.model.GetUserListBO;
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
	
	private static DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(LoadingTableController.class);
	
	private Gson gson = GsonUtils.gson;
	
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
			LOGGER.debug("json : " + json);
			AddLoadingTableBO loadingTable = gson.fromJson(json, AddLoadingTableBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,loadingTable);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(loadingTable,
							new HibernateSupportedValidator<AddLoadingTableBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.on(loadingTable.getName().length(),
							new ValidateNotNullInteger(SysMsgEnum.LENGTH_BEYOND,10))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return loadingTableService.add(loadingTable);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
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
			LOGGER.debug("json : " + json);
			UpdateLoadingTableBO loadingTable  = gson.fromJson(json, UpdateLoadingTableBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,loadingTable);
			ComplexResult ret = null;
			if(!StringUtils.isEmpty(loadingTable.getName())){
				ret = FluentValidator.checkAll().failFast()
						.on(loadingTable,
								new HibernateSupportedValidator<UpdateLoadingTableBO>()
								.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
						.on(loadingTable.getName().length(),
								new ValidateNotNullInteger(SysMsgEnum.LENGTH_BEYOND,10))
						.doValidate().result(ResultCollectors.toComplex());
			}else{
				ret = FluentValidator.checkAll().failFast()
						.on(loadingTable,
								new HibernateSupportedValidator<UpdateLoadingTableBO>()
								.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
						.doValidate().result(ResultCollectors.toComplex());
			}
			
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return loadingTableService.modify(loadingTable);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
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
			LOGGER.debug("json : " + json);
			DeleteLoadingTableBO loadingTable  = gson.fromJson(json, DeleteLoadingTableBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,loadingTable);
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
			LOGGER.error(e.getMessage());
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
			LOGGER.debug("json : " + json);
			BaseListPartnerIdBO param  = gson.fromJson(json, BaseListPartnerIdBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			return loadingTableService.getAllList(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
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
			LOGGER.debug("json : " + json);
			SelectLoadingTableByIdBO param  = gson.fromJson(json, SelectLoadingTableByIdBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<SelectLoadingTableByIdBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return loadingTableService.getLoadingTableById(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
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
			LOGGER.debug("json : " + json);
			SelectLoadingTableByAttributeBO param  = gson.fromJson(json, SelectLoadingTableByAttributeBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			return loadingTableService.getLoadingTableByAttribute(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
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
			LOGGER.debug("json : " + json);
			IsUseLoadingTableBO loadingTable  = gson.fromJson(json, IsUseLoadingTableBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,loadingTable);
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
			LOGGER.error(e.getMessage());
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
			LOGGER.debug("json : " + json);
			IsUseLoadingTableBO loadingTable  = gson.fromJson(json, IsUseLoadingTableBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,loadingTable);
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
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}

	/**
	 * @title 随机获取1个编码
	 * @author  wzy
	 * @create  2017/12/21 10:43
	 **/
	@RequestMapping(name = "获取随机编号",value = "/getnumber")
	public Map<String, Object> getNumber(@RequestBody(required = false) String json, HttpServletRequest request){
		try {
			return loadingTableService.getnumber(1);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 获取所有的装车台账号列表
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年3月22日
	 */
	@RequestMapping(name="获取所有的装车台账号列表",value = "/getUserList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getUserList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			GetUserListBO param  = gson.fromJson(json, GetUserListBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<GetUserListBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return loadingTableService.getUserList(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	@RequestMapping(name="装车台账号解除绑定",value = "/deleteBindingUserId", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> deleteBindingUserId(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			UpdateLoadingTableBO param  = gson.fromJson(json, UpdateLoadingTableBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<UpdateLoadingTableBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return loadingTableService.deleteBindingUserId(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
}
