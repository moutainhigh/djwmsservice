package com.djcps.wms.record.controller;

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
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.record.model.OrderOperationRecordPO;
import com.djcps.wms.record.model.TaskOperationRecordPO;
import com.djcps.wms.record.model.param.DeleteOrderRecordBO;
import com.djcps.wms.record.model.param.DeleteTaskRecordBO;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.record.model.param.EntryRecordListBO;
import com.djcps.wms.record.model.param.RelativeIdBO;
import com.djcps.wms.record.model.param.SaveOperationRecordBO;
import com.djcps.wms.record.model.param.SelectOrderRecordBO;
import com.djcps.wms.record.model.param.SelectTaskRecordBO;
import com.djcps.wms.record.model.param.StocktakingRecordListBO;
import com.djcps.wms.record.service.OperationRecordService;
import com.google.gson.Gson;

/**
 * @title:操作记录控制层
 * @description:
 * @author:wyb
 * @company:djwms
 * @create:2018/3/6
 **/
@RestController
@RequestMapping("/operationRecord")
public class OperationRecordController {
    private static final Logger logger = LoggerFactory.getLogger(OperationRecordController.class);
    @Autowired
    private OperationRecordService operationRecordService;
    private Gson gson = GsonUtils.gson;

    /**
     * 获取盘点操作记录
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/6
     **/
    @RequestMapping(name = " 获取盘点操作记录", value = "/stocktakingList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> stocktakingRecordList(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
        StocktakingRecordListBO stocktakingRecordListBO = gson.fromJson(json, StocktakingRecordListBO.class);
        BeanUtils.copyProperties(partnerInfoBo, stocktakingRecordListBO);
        return operationRecordService.stocktakingRecordList(stocktakingRecordListBO);
    }

    /**
     * 获取入库移库操作记录
     * 
     * @author wyb
     * @param
     * @return`				 
     * @create 2018/3/6
     **/
    @RequestMapping(name = "获取入库移库操作记录接口", value = "/entryList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getOperationRecord(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            logger.debug("json : " + json);
            EntryRecordListBO fromJson = gson.fromJson(json, EntryRecordListBO.class);
            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBean, fromJson);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(fromJson,
                            new HibernateSupportedValidator<EntryRecordListBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return operationRecordService.getEntryRecordList(fromJson);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * 保存操作记录
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/6
     **/
    @RequestMapping(name = "保存操作记录接口", value = "/saveRecord", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> saveOperationRecord(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            logger.debug("json : " + json);
            SaveOperationRecordBO fromJson = gson.fromJson(json, SaveOperationRecordBO.class);
            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBean, fromJson);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(fromJson,
                            new HibernateSupportedValidator<SaveOperationRecordBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return operationRecordService.saveOperationRecord(fromJson);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * @title:根据关联id获取操作记录信息
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    @RequestMapping(name = "根据关联id获取操作记录信息", value = "/getRecordByRrelativeId", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getRecordByRrelativeId(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            logger.debug("json : " + json);
            RelativeIdBO fromJson = gson.fromJson(json, RelativeIdBO.class);
            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBean, fromJson);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(fromJson,
                            new HibernateSupportedValidator<RelativeIdBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return operationRecordService.getRecordByRrelativeId(fromJson);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * @title:查询任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    @RequestMapping(name = "查询任务 操作记录", value = "/selectTaskRecord", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> selectTaskRecord(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            logger.debug("json : " + json);
            SelectTaskRecordBO fromJson = gson.fromJson(json, SelectTaskRecordBO.class);
            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBean, fromJson);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(fromJson,
                            new HibernateSupportedValidator<SelectTaskRecordBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return operationRecordService.selectTaskRecord(fromJson);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * @title:保存任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    @RequestMapping(name = "保存任务 操作记录", value = "/saveTaskRecord", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> saveTaskRecord(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            logger.debug("json : " + json);
            TaskOperationRecordPO fromJson = gson.fromJson(json, TaskOperationRecordPO.class);
            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBean, fromJson);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(fromJson,
                            new HibernateSupportedValidator<TaskOperationRecordPO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return operationRecordService.saveTaskRecord(fromJson);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * @title:删除任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    @RequestMapping(name = "删除任务 操作记录", value = "/deleteTaskRecord", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> deleteTaskRecord(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            logger.debug("json : " + json);
            DeleteTaskRecordBO fromJson = gson.fromJson(json, DeleteTaskRecordBO.class);
            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBean, fromJson);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(fromJson,
                            new HibernateSupportedValidator<DeleteTaskRecordBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return operationRecordService.deleteTaskRecord(fromJson);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * @title:查询任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    @RequestMapping(name = "查询任务 操作记录", value = "/selectOrderRecord", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> selectOrderRecord(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            logger.debug("json : " + json);
            SelectOrderRecordBO fromJson = gson.fromJson(json, SelectOrderRecordBO.class);
            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBean, fromJson);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(fromJson,
                            new HibernateSupportedValidator<SelectOrderRecordBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return operationRecordService.selectOrderRecord(fromJson);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * @title:保存任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    @RequestMapping(name = "保存任务 操作记录", value = "/saveOrderRecord", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> saveOrderRecord(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            logger.debug("json : " + json);
            OrderOperationRecordPO fromJson = gson.fromJson(json, OrderOperationRecordPO.class);
            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBean, fromJson);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(fromJson,
                            new HibernateSupportedValidator<OrderOperationRecordPO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return operationRecordService.saveOrderRecord(fromJson);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * @title:删除任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    @RequestMapping(name = "删除任务 操作记录", value = "/deleteOrderRecord", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> deleteOrderRecord(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            logger.debug("json : " + json);
            DeleteOrderRecordBO fromJson = gson.fromJson(json, DeleteOrderRecordBO.class);
            PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBean, fromJson);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(fromJson,
                            new HibernateSupportedValidator<DeleteOrderRecordBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return operationRecordService.deleteOrderRecord(fromJson);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
}
