package com.djcps.wms.workrecords.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.aop.inneruser.annotation.InnerUser;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.inneruser.model.result.UserInfoVO;
import com.djcps.wms.record.controller.OperationRecordController;
import com.djcps.wms.workrecords.model.*;
import com.djcps.wms.workrecords.service.WorkRecordService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;
import java.util.Map;

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

	/**
	 * 获取pda端工作记录(入库、退库、移库)
	 * @author Chengw
	 * @since 2018/4/25  16:32
	 * @param json
	 * @return
	 */
	@RequestMapping(name = "获取pda工作记录", value = "/listPdaInfo", method = RequestMethod.POST)
	public Map<String, Object> listPdaInfo(@RequestBody String json,@InnerUser UserInfoVO userInfoVO) {
		try {
			WorkRecordsListBO param = gson.fromJson(json, WorkRecordsListBO.class);
			param.setOperatorId(userInfoVO.getId());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsListBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.listPdaInfo(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}

	/**
	 * 获取pda装车的工作记录 
	 * @author Chengw
	 * @since 2018/4/25  16:32
	 * @param json
	 * @return
	 */
	@RequestMapping(name = "获取pda装车的工作记录", value = "/listPdaLoadingTask", method = RequestMethod.POST)
	public Map<String, Object> listPdaLoadingTask(@RequestBody String json,@InnerUser UserInfoVO userInfoVO) {
		try {
			WorkRecordsListBO param = gson.fromJson(json, WorkRecordsListBO.class);
			param.setOperatorId(userInfoVO.getId());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsListBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.listPdaLoadingTask(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}

	/**
	 * 获取pda提货的工作记录
	 * @author Chengw
	 * @since 2018/4/25  16:32
	 * @param json
	 * @return
	 */
	@RequestMapping(name = "获取pda提货的工作记录", value = "/listPdaDelivery", method = RequestMethod.POST)
	public Map<String, Object> listPdaDelivery(@RequestBody String json,@InnerUser UserInfoVO userInfoVO) {
		try {
			WorkRecordsListBO param = gson.fromJson(json, WorkRecordsListBO.class);
			param.setOperatorId(userInfoVO.getId());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsListBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.listPdaDelivery(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}

	/**
	 * 获取pda盘点的工作记录
	 * @author Chengw
	 * @since 2018/4/25  16:31
	 * @param json
	 * @return
	 */
	@RequestMapping(name = "获取pda盘点的工作记录", value = "/listPdaStockTaking", method = RequestMethod.POST)
	public Map<String, Object> listPdaStockTaking(@RequestBody String json,@InnerUser UserInfoVO userInfoVO) {
		try {
			WorkRecordsListBO param = gson.fromJson(json, WorkRecordsListBO.class);
			param.setOperatorId(userInfoVO.getId());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsListBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.listPdaStockTaking(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}

	/**
	 * 获取pda运单明细 
	 * @author Chengw
	 * @since 2018/4/25  16:31
	 * @param json
	 * @return
	 */
	@RequestMapping(name = "获取pda运单明细", value = "/listPdaLoadingTaskInfo", method = RequestMethod.POST)
	public Map<String, Object> listPdaLoadingTaskInfo(@RequestBody String json,@InnerUser UserInfoVO userInfoVO) {
		try {
			WorkRecordsTaskBO param = gson.fromJson(json, WorkRecordsTaskBO.class);
			param.setOperatorId(userInfoVO.getId());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsTaskBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.listPdaLoadingTaskInfo(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}

	/**
	 * 获取pda提货单明细
	 * @author Chengw
	 * @since 2018/4/25  16:31
	 * @param json
	 * @return
	 */
	@RequestMapping(name = "获取pda提货单明细", value = "/listPdaDeliveryInfo", method = RequestMethod.POST)
	public Map<String, Object> listPdaDeliveryInfo(@RequestBody String json,@InnerUser UserInfoVO userInfoVO) {
		try {
			WorkRecordsTaskBO param = gson.fromJson(json, WorkRecordsTaskBO.class);
			param.setOperatorId(userInfoVO.getId());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsTaskBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.listPdaDeliveryInfo(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}

	/**
	 * 获取pda盘点单明细 
	 * @author Chengw
	 * @since 2018/4/25  16:31
	 * @param json
	 * @return
	 */
	@RequestMapping(name = "获取pda盘点单明细", value = "/listPdaStockTakingInfo", method = RequestMethod.POST)
	public Map<String, Object> listPdaStockTakingInfo(@RequestBody String json,@InnerUser UserInfoVO userInfoVO) {
		try {
			WorkRecordsTaskBO param = gson.fromJson(json, WorkRecordsTaskBO.class);
			param.setOperatorId(userInfoVO.getId());
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsTaskBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.listPdaStockTakingInfo(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}

	/**
	 * 获取操作订单信息
	 * @author Chengw
	 * @since 2018/4/25  18:42
	 * @param json
	 * @return
	 */
	@RequestMapping(name = "获取订单明细", value = "/getOrderDetail", method = RequestMethod.POST)
	public Map<String, Object> getOrderDetail(@RequestBody String json, HttpServletRequest request) {
		try {
			WorkRecordsOrderBO param = gson.fromJson(json, WorkRecordsOrderBO.class);
			PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBo, param);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<WorkRecordsOrderBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return workRecordService.getOrderDetail(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}


}