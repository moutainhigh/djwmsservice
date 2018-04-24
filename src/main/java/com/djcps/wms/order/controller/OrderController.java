package com.djcps.wms.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
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
import com.djcps.wms.allocation.model.AddAllocationOrderBO;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.order.model.OrderParamBO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderIdListBO;
import com.djcps.wms.order.model.onlinepaperboard.QueryObjectBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitSonOrderBO;
import com.djcps.wms.order.service.OrderService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 订单控制层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {
	
	private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(OrderController.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * @title: 获取所有订单
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(name="获取所有订单",value = "/getAllOrderList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAllOrderList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			//仓库类型,1,2,3,4,5(纸板，纸箱，积分商城仓库，物料仓库，退货仓库)
			LOGGER.debug("json : " + json);
			QueryObjectBO param = gson.fromJson(json, QueryObjectBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<QueryObjectBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return orderService.getOnlinePaperboardList(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	@RequestMapping(name="根据订单获取订单",value = "/getOrderByOrderId", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getOrderByOrderId(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			BatchOrderIdListBO param = gson.fromJson(json, BatchOrderIdListBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			param.setKeyArea(partnerInfoBean.getPartnerArea());
			BeanUtils.copyProperties(partnerInfoBean, param);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<BatchOrderIdListBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return orderService.getOnlinePaperboardByOrderId(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 获取线上纸板订单
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年4月12日
	 */
	@RequestMapping(name="线上纸板订单列表接口",value = "/getOnlinePaperboardList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getOnlinePaperboardList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			QueryObjectBO param = gson.fromJson(json, QueryObjectBO.class);
//			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
//			BeanUtils.copyProperties(partnerInfoBean,param);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<QueryObjectBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return orderService.getOnlinePaperboardList(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}

}
