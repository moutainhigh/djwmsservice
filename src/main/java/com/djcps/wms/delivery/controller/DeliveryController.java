package com.djcps.wms.delivery.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.wms.commons.aop.inneruser.annotation.InnerUser;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.delivery.model.*;
import com.djcps.wms.delivery.service.DeliveryService;
import com.djcps.wms.inneruser.model.result.UserInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validation;
import java.util.Map;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * 提货
 * 
 * @author Chengw
 * @since 2018/1/31 07:49.
 */
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    @Autowired
    private DeliveryService deliveryService;

    /**
     * 获取提货单列表
     * 
     * @autuor Chengw
     * @since 2018/1/31 08:35
     * @param json
     * @return
     */
    @RequestMapping(name = "提货单列表", value = "/list", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> list(@RequestBody(required = false) String json) {
        try {
            logger.debug("json : " + json);
            ListDeliveryBO param = gson.fromJson(json, ListDeliveryBO.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<ListDeliveryBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return deliveryService.list(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }
    }

    /**
     * 获取提货单订单列表
     * 
     * @autuor Chengw
     * @since 2018/1/31 08:35
     * @param json
     * @return
     */
    @RequestMapping(name = "提货单订单列表", value = "/listOrder", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> listOrder(@RequestBody(required = false) String json) {
        try {
            logger.debug("json : " + json);
            ListDeliveryOrderBO param = gson.fromJson(json, ListDeliveryOrderBO.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<ListDeliveryOrderBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return deliveryService.listOrder(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }
    }

    /**
     * 打印
     * 
     * @autuor Chengw
     * @since 2018/1/31 08:35
     * @param json
     * @return
     */
    @RequestMapping(name = "打印", value = "/print", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> print(@RequestBody(required = false) String json) {
        try {
            logger.debug("json : " + json);
            PrintDeliveryBO param = gson.fromJson(json, PrintDeliveryBO.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<PrintDeliveryBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return deliveryService.print(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }
    }

    /**
     * 完成单条提货订单
     * 
     * @autuor Chengw
     * @since 2018/2/1 14:15
     * @param json
     * @param userInfoVO
     * @return
     */
    @RequestMapping(name = "完成单条提货订单", value = "/completeOrder", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> completeOrder(@RequestBody(required = false) String json,
            @InnerUser UserInfoVO userInfoVO) {
        try {
            logger.debug("json : " + json);
            SaveDeliveryBO param = gson.fromJson(json, SaveDeliveryBO.class);
            param.setPartnerId(userInfoVO.getUcompany());
            param.setOperator(userInfoVO.getUname());
            param.setOperatorId(String.valueOf(userInfoVO.getId()));
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<SaveDeliveryBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return deliveryService.completeOrder(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }
    }

    /**
     * 获取提货信息以及订单信息 -PDA
     * 
     * @autuor Chengw
     * @since 2018/2/1 14:15
     * @param json
     * @return
     */
    @RequestMapping(name = "获取提货信息以及订单信息 -PDA", value = "/getDeliveryForPDA", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getDeliveryForPDA(@RequestBody(required = false) String json,
            @InnerUser UserInfoVO userInfoVO) {
        try {
            logger.debug("json : " + json);
            DeliveryOrderBO param = gson.fromJson(json, DeliveryOrderBO.class);
            param.setPartnerId(userInfoVO.getUcompany());
            param.setPickerId(String.valueOf(userInfoVO.getId()));
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<DeliveryOrderBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return deliveryService.getDeliveryForPDA(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }
    }

    /**
     * 获取订单信息 -PDA
     * 
     * @autuor Chengw
     * @since 2018/2/1 14:15
     * @param json
     * @param userInfoVO
     * @return
     */
    @RequestMapping(name = "获取订单信息列表 -PDA", value = "/listOrderForPDA", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> listOrderForPDA(@RequestBody(required = false) String json,
            @InnerUser UserInfoVO userInfoVO) {
        try {
            logger.debug("json : " + json);
            DeliveryOrderBO param = gson.fromJson(json, DeliveryOrderBO.class);
            param.setPartnerId(userInfoVO.getUcompany());
            param.setPickerId(String.valueOf(userInfoVO.getId()));
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<DeliveryOrderBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return deliveryService.listOrderForPDA(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }
    }

    /**
     * 获取订单信息
     * 
     * @param json
     * @return
     */
    @RequestMapping(name = "获取订单详细信息", value = "/getOrderDetail", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getOrderDetail(@RequestBody(required = false) String json,
            @InnerUser UserInfoVO userInfoVO) {
        try {
            logger.debug("json : " + json);
            DeliveryOrderDetailBO param = gson.fromJson(json, DeliveryOrderDetailBO.class);
            param.setPartnerId(userInfoVO.getUcompany());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<DeliveryOrderDetailBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return deliveryService.getOrderDetail(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }
    }

    /**
     * 删除订单信息
     * 
     * @param json
     * @return
     */
    @RequestMapping(name = "删除订单信息", value = "/delOrderInfo", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> delOrderInfo(@RequestBody(required = false) String json,
            @InnerUser UserInfoVO userInfoVO) {
        try {
            logger.debug("json : " + json);
            DeleteOrderInfoBO param = gson.fromJson(json, DeleteOrderInfoBO.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<DeleteOrderInfoBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return deliveryService.delOrderInfo(param);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }
    }
}
