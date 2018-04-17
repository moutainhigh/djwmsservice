package com.djcps.wms.permission.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;

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
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.outorder.controller.OutOrderController;
import com.djcps.wms.outorder.model.OutOrderBO;
import com.djcps.wms.permission.model.BaseOrgBO;
import com.djcps.wms.permission.model.DeletePermissionBO;
import com.djcps.wms.permission.model.GetPermissionBO;
import com.djcps.wms.permission.model.GetPermissionChooseBO;
import com.djcps.wms.permission.model.GetUserByPermissionIdBO;
import com.djcps.wms.permission.model.InsertOrUpdatePermissionBO;
import com.djcps.wms.permission.service.PermissionService;
import com.google.gson.Gson;


/**
 * @author zhq
 * 权限控制层
 * 2018年4月12日
 */
@RestController
@RequestMapping("/permission")
public class PersmissionController {
	@Autowired
	private PermissionService permissionService;
	
	private Gson gson = new Gson();
	
	private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(PersmissionController.class);
	
	/**
	 * 得到组合权限列表
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> getPermissionAll(@RequestBody(required = false) String json){
		try {
			LOGGER.debug("json:"+json);
			GetPermissionBO getPermissonBO=gson.fromJson(json, GetPermissionBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(getPermissonBO,new HibernateSupportedValidator<GetPermissionBO>()
					.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return permissionService.getPermissionList(getPermissonBO);
		}catch(Exception e) {
			e.printStackTrace();
			LOGGER.error("获取组合权限异常:{}"+e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 得到wms权限
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/getWmsPermission", method = RequestMethod.POST)
	public Map<String, Object> getWmsPermission(@RequestBody(required = false) String json){
		try {
			LOGGER.debug("json:"+json);
			BaseOrgBO baseOrgBO=gson.fromJson(json, BaseOrgBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(baseOrgBO,new HibernateSupportedValidator<BaseOrgBO>()
					.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return permissionService.getWmsPermission(baseOrgBO);
		}catch(Exception e) {
			e.printStackTrace();
			LOGGER.error("获取WMS权限异常:{}"+e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 新增权限包,id为空则为新增，id不为空为修改
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateWmsPermission", method = RequestMethod.POST)
	public Map<String, Object> insertWmsPermission(@RequestBody(required = false) String json){
		try {
			LOGGER.debug("json:"+json);
			InsertOrUpdatePermissionBO insertOrUpdate=gson.fromJson(json, InsertOrUpdatePermissionBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(insertOrUpdate,new HibernateSupportedValidator<InsertOrUpdatePermissionBO>()
					.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return permissionService.insertOrUpdatePermission(insertOrUpdate);
		}catch(Exception e) {
			e.printStackTrace();
			LOGGER.error("新增/修改权限异常:{}"+e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 删除权限包
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/deletePermission", method = RequestMethod.POST)
	public Map<String, Object> deletePermission(@RequestBody(required = false) String json,HttpServletRequest request){
		try {
			PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
			LOGGER.debug("json:"+json);
			DeletePermissionBO deleteBO=gson.fromJson(json, DeletePermissionBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(deleteBO,new HibernateSupportedValidator<DeletePermissionBO>()
					.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return permissionService.deletePermission(deleteBO,partnerInfoBo.getPartnerId());
		}catch(Exception e) {
			e.printStackTrace();
			LOGGER.error("删除权限异常:{}"+e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 根据组合权限id和公司id，获取获取组合权限集合
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/getPermissionChoose", method = RequestMethod.POST)
	public Map<String, Object> getPermissionList(@RequestBody(required = false) String json){
		try {
			LOGGER.debug("json:"+json);
			GetPermissionChooseBO getPerChoose=gson.fromJson(json, GetPermissionChooseBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
				.on(getPerChoose,new HibernateSupportedValidator<GetPermissionChooseBO>()
				.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
				.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}		
			return permissionService.getPerChoose(getPerChoose);
		}catch(Exception e) {
			e.printStackTrace();
			LOGGER.error("批量查询异常:{}"+e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
}
