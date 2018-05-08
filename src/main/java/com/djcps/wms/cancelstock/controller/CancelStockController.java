package com.djcps.wms.cancelstock.controller;

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
import com.djcps.wms.cancelstock.model.param.AddStockBO;
import com.djcps.wms.cancelstock.model.param.CancelOrderIdBO;
import com.djcps.wms.cancelstock.model.param.PickerIdBO;
import com.djcps.wms.cancelstock.service.CancelStockService;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.GsonUtils;
import com.google.gson.Gson;

/**
 * 退库控制层
 * @company:djwms
 * @author:zdx
 * @date:2018年3月19日
 */
@RestController
@RequestMapping(value = "/cancelStock")
public class CancelStockController {
	
	private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(CancelStockController.class);
	
	private Gson gson = GsonUtils.gson;
	
	@Autowired
	private CancelStockService cancelStockService;
	
    /**
     * 根据订单号和退库人获取订单详情信息
     * @param json
     * @param request
     * @return
     * @author:zdx
     * @date:2018年3月20日
     */
    @RequestMapping(name="根据订单号和退库人获取订单详情信息",value = "/getOrderByOrderId", method = RequestMethod.POST)
    public Map<String, Object> getOrderByOrderId(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
        	LOGGER.debug("json : " + json);
            // 解析参数
        	CancelOrderIdBO param = gson.fromJson(json, CancelOrderIdBO.class);
        	PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
            // 参数校验
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,new HibernateSupportedValidator<CancelOrderIdBO>()
                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            // 返回结果
            return cancelStockService.getOrderByOrderId(param);
        } catch (Exception e) {
        	e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 根据退库人id获取退库任务
     * @param json
     * @return
     * @author:zdx
     * @date:2018年3月19日
     */
    @RequestMapping(name="根据退库人id获取退库任务",value = "/getCancelStockByPickerId", method = RequestMethod.POST)
    public Map<String, Object> getCancelStockByPickerId(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
        	LOGGER.debug("json : " + json);
            // 解析参数
        	PickerIdBO param = gson.fromJson(json, PickerIdBO.class);
        	PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
            // 参数校验
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,new HibernateSupportedValidator<PickerIdBO>()
                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            // 返回结果
            return cancelStockService.getCancelStockByPickerId(param);
        } catch (Exception e) {
        	e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
 
    /**
     * 新增库存信息
     * @param json
     * @return
     * @author:zdx
     * @date:2018年3月20日
     */
    @RequestMapping(name="新增库存信息",value = "/addStock", method = RequestMethod.POST)
    public Map<String, Object> addStock(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
        	LOGGER.debug("json : " + json);
            // 解析参数
        	AddStockBO param = gson.fromJson(json, AddStockBO.class);
        	PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
            // 参数校验
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,new HibernateSupportedValidator<AddStockBO>()
                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            // 返回结果
            return cancelStockService.addStock(param);
        } catch (Exception e) {
        	e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
}
