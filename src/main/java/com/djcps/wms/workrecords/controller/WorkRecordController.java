package com.djcps.wms.workrecords.controller;

import java.util.Map;

import javax.validation.Validation;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.workrecords.model.WorkRecordsDetailBO;
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
import com.djcps.wms.workrecords.model.WorkRecordsBO;
import com.djcps.wms.workrecords.service.WorkRecordService;
import com.google.gson.Gson;
import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * @title 工作记录控制层
 * @author panyang
 * @version 创建时间：2018年4月17日 下午3:23:29
 */
@RestController
@RequestMapping("/workrecords")
public class WorkRecordController {

	private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(OperationRecordController.class);

	@Autowired
	private WorkRecordService workRecordService;

	/**
	 * 入库和装车 通过操作类型获取工作记录相关信息
	 * @param json
	 * @author py
	 */
	@RequestMapping(name = "通过操作类型获取工作记录信息接口", value = "/listByOperationType", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> listByOperationType(@RequestBody(required = false) String json) {
		try {
            LOGGER.debug("json : " + json);
			WorkRecordsBO param = gson.fromJson(json, WorkRecordsBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.listByOperationType(param);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}

	/**
	 * 获取提货工作记录信息
	 * 
	 * @param json
	 * @return
	 */

	@RequestMapping(name = "提货工作记录信息接口", value = "/listDeliveryRecord", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> listDeliveryRecord(@RequestBody(required = false) String json) {
		try {
            LOGGER.debug("json : " + json);
			WorkRecordsBO param = gson.fromJson(json, WorkRecordsBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.listDeliveryRecord(param);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}

	/**
	 * 获取入库装车工作记录详情
	 * 
	 * @param json
	 * @return
	 */

	@RequestMapping(name = "获取工作记录详情列表", value = "/getDetail", method = RequestMethod.POST)
	public Map<String, Object> getDetail(@RequestBody String json) {
		try {
			WorkRecordsDetailBO param = gson.fromJson(json, WorkRecordsDetailBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsDetailBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.getWorkRecordsDetail(param);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}

	}

	/**
	 * 获取提货工作记录详情
	 * @param json
	 * @return
	 */

	@RequestMapping(name = "获取提货工作记录详情列表", value = "/getDeliveryDetail", method = RequestMethod.POST)
	public Map<String, Object> getDeliveryDetail(@RequestBody String json) {
		try {
			WorkRecordsDetailBO param = gson.fromJson(json, WorkRecordsDetailBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsDetailBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.getDeliveryDetail(param);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}

	}

}