package com.djcps.wms.workrecords.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.record.controller.OperationRecordController;

import com.djcps.wms.record.service.workRecordService;
import com.djcps.wms.workrecords.model.WorkRecordsBO;
import com.djcps.wms.workrecords.model.WorkRecordsInfoPO;
import com.djcps.wms.workrecords.model.param.WorkRecordsParam;
import com.djcps.wms.workrecords.service.WorkRecordService;
import com.google.gson.Gson;

/**
 * @author panyang
 * @version 创建时间：2018年4月17日 下午3:23:29 类说明
 */
@RestController
@RequestMapping("/workrecord")

public class WorkRecordController {
	private static final Logger logger = LoggerFactory.getLogger(OperationRecordController.class);

	private WorkRecordService workRecordService ;
	
	private Gson gson = new Gson();

	/**
	 * 获取入库工作记录相关信息
	 * @author py
	 */

	 @RequestMapping(name = "获取入库工作记录接口", value = "/entryRecordList", method = RequestMethod.POST, produces = "application/json")
	    public Map<String, Object> getAllRecordList(@RequestBody(required = false) String json,
	            HttpServletRequest request) {
	        try {
	           WorkRecordsBO param = gson.fromJson(json,  WorkRecordsBO .class);
	            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
	            BeanUtils.copyProperties(partnerInfoBean, param);
	            ComplexResult ret = FluentValidator.checkAll().failFast()
	                    .on(param,
	                            new HibernateSupportedValidator<WorkRecordsBO >()
	                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
	                    .doValidate().result(ResultCollectors.toComplex());
	            if (!ret.isSuccess()) {
	                return MsgTemplate.failureMsg(ret);
	            }
	            return workRecordService.getAllRecordList(param);
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(e.getMessage());
	            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
	        }
	    }
	 
	 /**
	  * 获取装车工作记录相关信息
	  * @param json
	  * @param request
	  * @return
	  */
	 
	 
	 @RequestMapping(name = "获取装车工作记录接口", value = "/loadingTableRecordList", method = RequestMethod.POST, produces = "application/json")
	    public Map<String, Object> getloadingTableWordRecord(@RequestBody(required = false) String json,
	            HttpServletRequest request) {
	        try {
	           WorkRecordsParam fromJson = gson.fromJson(json,  WorkRecordsParam .class);
	            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
	            BeanUtils.copyProperties(partnerInfoBean, fromJson);
	            ComplexResult ret = FluentValidator.checkAll().failFast()
	                    .on(fromJson,
	                            new HibernateSupportedValidator<  WorkRecordsParam >()
	                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
	                    .doValidate().result(ResultCollectors.toComplex());
	            if (!ret.isSuccess()) {
	                return MsgTemplate.failureMsg(ret);
	            }
	            return workRecordService.loadingTableRecordList(fromJson);
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(e.getMessage());
	            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
	        }
	    }
	 
	 @RequestMapping(name = "获取提货工作记录接口", value = "/deliveryRecordList", method = RequestMethod.POST, produces = "application/json")
	    public Map<String, Object> getdeliveryWordRecord(@RequestBody(required = false) String json,
	            HttpServletRequest request) {
	        try {
	           WorkRecordsParam fromJson = gson.fromJson(json,  WorkRecordsParam .class);
	            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
	            BeanUtils.copyProperties(partnerInfoBean, fromJson);
	            ComplexResult ret = FluentValidator.checkAll().failFast()
	                    .on(fromJson,
	                            new HibernateSupportedValidator<  WorkRecordsParam >()
	                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
	                    .doValidate().result(ResultCollectors.toComplex());
	            if (!ret.isSuccess()) {
	                return MsgTemplate.failureMsg(ret);
	            }
	            return workRecordService.deliveryRecordList(fromJson);
	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(e.getMessage());
	            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
	        }
	    }
	 
	 
	 
	 
}