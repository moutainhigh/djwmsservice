package com.djcps.wms.abnormal.controller;


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
import com.djcps.wms.abnormal.service.AbnormalService;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.google.gson.Gson;
import com.djcps.wms.abnormal.model.AddAbnormal;
import com.djcps.wms.abnormal.model.GetOrderByAttributeBO;
import com.djcps.wms.abnormal.model.OrderIdBO;
import com.djcps.wms.abnormal.model.OrderIdListBO;
import com.djcps.wms.abnormal.model.UpdateAbnormalBO;

/**
 * @title: 异常订单请求相关接口
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2018年3月7日
 */
@RestController
@RequestMapping(value = "/abnormalOrder")
public class AbnormalController {
	
	private static final Logger logger = LoggerFactory.getLogger(AbnormalController.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	private AbnormalService abnormalService;
	
	/**
	 * 异常订单查询(包含查询条件)
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	@RequestMapping(name="异常订单查询(包含查询条件)",value = "/getOrderByAttribute", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getOrderByAttributeBO(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
            logger.debug("json : " + json);
            GetOrderByAttributeBO param = gson.fromJson(json, GetOrderByAttributeBO.class);
            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<GetOrderByAttributeBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return abnormalService.getOrderByAttributeBO(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }
	}
	
	/**
	 * 根据拆分的订单号获取订单信息
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	@RequestMapping(name="根据拆分的订单号获取订单信息",value = "/getSplitOrderByOrderId", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getSplitOrderByOrderId(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
            logger.debug("json : " + json);
            OrderIdBO param = gson.fromJson(json, OrderIdBO.class);
            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<OrderIdBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return abnormalService.getSplitOrderByOrderId(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }
	}
	
	/**
	 * 修改异常订单
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	@RequestMapping(name="修改异常订单",value = "/updateAbnormal", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> updateAbnormal(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
            logger.debug("json : " + json);
            UpdateAbnormalBO param = gson.fromJson(json, UpdateAbnormalBO.class);
            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<UpdateAbnormalBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return abnormalService.updateAbnormal(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }
	}
	
	/**
	 * 新增异常订单
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	@RequestMapping(name="新增异常订单",value = "/addAbnormal", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> addAbnormal(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
            logger.debug("json : " + json);
            AddAbnormal param = gson.fromJson(json, AddAbnormal.class);
            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<AddAbnormal>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return abnormalService.addAbnormal(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }
	}
	
	/**
	 * 根据订单号获取异常订单信息(批量)
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	@RequestMapping(name="根据订单号获取异常订单信息(批量)",value = "/getOrderByOrderIdList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getOrderByOrderIdList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
            logger.debug("json : " + json);
            OrderIdListBO param = gson.fromJson(json, OrderIdListBO.class);
            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<OrderIdListBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return abnormalService.getOrderByOrderIdList(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }
	}
}
