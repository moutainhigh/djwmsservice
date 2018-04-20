package com.djcps.wms.workrecords.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.record.controller.OperationRecordController;
import com.djcps.wms.record.model.param.EntryRecordListBO;
import com.djcps.wms.workrecords.model.WorkRecordsBO;
import com.djcps.wms.workrecords.model.param.WorkRecordsParam;
import com.djcps.wms.workrecords.service.WorkRecordService;
import com.google.gson.Gson;

/**
 * @title 工作记录控制层
 * @author panyang
 * @version 创建时间：2018年4月17日 下午3:23:29
 */
@RestController
@RequestMapping("/workrecord")

public class WorkRecordController {
	private static final Logger logger = LoggerFactory.getLogger(OperationRecordController.class);

	@Autowired
	private WorkRecordService workRecordService;

	private Gson gson = new Gson();

	/**
	 * 入库和装车 通过操作类型获取工作记录相关信息
	 * 
	 * @author py
	 */
	@RequestMapping(name = "通过操作类型获取工作记录信息接口", value = "/getRecordListByOperationType", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getRecordListByOperationType(@RequestBody(required = false) String json,
			HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			WorkRecordsBO param = gson.fromJson(json, WorkRecordsBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.getAllRecordListByOperationType(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}

	/**
	 * 获取提货工作记录信息
	 * 
	 * @param json
	 * @param request
	 * @return
	 */

	@RequestMapping(name = "提货工作记录信息接口", value = "/getDeliveryRecordList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getDeliveryRecordList(@RequestBody(required = false) String json,
			HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			WorkRecordsBO param = gson.fromJson(json, WorkRecordsBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.getDeliveryRecordList(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}

	/**
	 * 获取入库装车工作记录详情
	 * 
	 * @param json
	 * @param request
	 * @return
	 */

	@RequestMapping(name = "获取工作记录详情列表", value = "/getWorkRecordsDetail", method = RequestMethod.POST)
	public Map<String, Object> getWorkRecordsDetail(@RequestBody String json) {
		try {
			WorkRecordsParam param = gson.fromJson(json, WorkRecordsParam.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsParam>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.getWorkRecordsDetail(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}

	}

	/**
	 * 获取提货工作记录详情
	 * @param json
	 * @param request
	 * @return
	 */

	@RequestMapping(name = "获取提货工作记录详情列表", value = "/getDeliveryWorkRecordsDetail", method = RequestMethod.POST)
	public Map<String, Object> getDeliveryWorkRecordsDetail(@RequestBody String json) {
		try {
			WorkRecordsParam param = gson.fromJson(json, WorkRecordsParam.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsParam>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.getDeliveryWorkRecordsDetail(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}

	}

}