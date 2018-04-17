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
import com.djcps.wms.record.model.param.EntryRecordListBO;
import com.djcps.wms.record.model.param.SaveOperationRecordBO;
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
    private Gson gson = new Gson();

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
     * @return
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
}
