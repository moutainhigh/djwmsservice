package com.djcps.wms.loadingtask.controller;

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
import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtask.model.AddOrderApplicationListBO;
import com.djcps.wms.loadingtask.model.AdditionalOrderBO;
import com.djcps.wms.loadingtask.model.ConfirmBO;
import com.djcps.wms.loadingtask.model.FinishLoadingBO;
import com.djcps.wms.loadingtask.model.LoadingBO;
import com.djcps.wms.loadingtask.model.LoadingPersonBO;
import com.djcps.wms.loadingtask.model.RejectRequestBO;
import com.djcps.wms.loadingtask.model.RemoveLoadingPersonBO;
import com.djcps.wms.loadingtask.service.LoadingTaskService;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * @title:装车任务控制层
 * @description:
 * @author:wyb
 * @company:djwms
 * @create:2018/3/19
 **/
@RestController
@RequestMapping(value = "/loadingTask")
public class LoadingTaskController {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(LoadingTaskController.class);

    @Autowired
    private LoadingTaskService loadingTaskService;

    /**
     * 装车员列表
     * 
     * @autuor wyb
     * @since 2018/3/19
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "装车员列表", value = "/loadingPersonList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> loadingPersonList(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            LoadingPersonBO param = gson.fromJson(json, LoadingPersonBO.class);
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBo, param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<LoadingPersonBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return loadingTaskService.loadingPersonList(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 更新装车员状态并获取装车任务列表
     * 
     * @autuor wyb
     * @since 2018/3/19
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "更新装车员状态并获取装车任务列表", value = "/confirm", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> confirm(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            ConfirmBO param = gson.fromJson(json, ConfirmBO.class);
            PartnerInfoBO partnerInfoBO = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBO, param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<ConfirmBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return loadingTaskService.confirm(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }

    }

    /**
     * 移除装车员
     * 
     * @autuor wyb
     * @since 2018/3/19
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "移除装车员", value = "/removeLoadingPerson", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> removeLoadingPerson(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            RemoveLoadingPersonBO param = gson.fromJson(json, RemoveLoadingPersonBO.class);
            PartnerInfoBO partnerInfoBO = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBO, param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<RemoveLoadingPersonBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return loadingTaskService.removeLoadingPerson(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 装车
     * 
     * @autuor wyb
     * @since 2018/3/19
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "装车", value = "/loading", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> loading(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            LoadingBO param = gson.fromJson(json, LoadingBO.class);
            PartnerInfoBO partnerInfoBO = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBO, param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<LoadingBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return loadingTaskService.loading(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 完成装车
     * 
     * @autuor wyb
     * @since 2018/3/21
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "完成装车", value = "/finishLoading", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> finishLoading(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            FinishLoadingBO param = gson.fromJson(json, FinishLoadingBO.class);
            PartnerInfoBO partnerInfoBO = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBO, param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<FinishLoadingBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return loadingTaskService.finishLoading(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 追加订单FOR PDA
     * 
     * @autuor wyb
     * @since 2018/3/21
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "追加订单", value = "/additionalOrder", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> additionalOrder(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            AdditionalOrderBO param = gson.fromJson(json, AdditionalOrderBO.class);
            PartnerInfoBO partnerInfoBO = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBO, param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<AdditionalOrderBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return loadingTaskService.additionalOrder(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 驳回申请web
     * 
     * @autuor wyb
     * @since 2018/3/21
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "驳回申请", value = "/rejectRequest", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> rejectRequest(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            RejectRequestBO param = gson.fromJson(json, RejectRequestBO.class);
            PartnerInfoBO partnerInfoBO = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBO, param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<RejectRequestBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            param.setAppSystem(AppConstant.WMS);
            return loadingTaskService.rejectRequest(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * 追加订单申请列表web
     * 
     * @autuor wyb
     * @since 2018/3/21
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "追加订单申请列表", value = "/list", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> addOrderApplicationList(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            AddOrderApplicationListBO param = gson.fromJson(json, AddOrderApplicationListBO.class);
            PartnerInfoBO partnerInfoBO = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBO, param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<AddOrderApplicationListBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return loadingTaskService.addOrderApplicationList(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    
    /**
     * 根据用户登录id获取装车台id
     * @param json
     * @param request
     * @return
     * @author:zdx
     * @date:2018年4月4日
     */
    @RequestMapping(name = "根据用户登录id获取装车台id", value = "/getLoadingTableIdByUserId", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getLoadingTableIdByUserId(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            //伪代码
            PartnerInfoBO partnerInfoBO = (PartnerInfoBO) request.getAttribute("partnerInfo");
            return loadingTaskService.getLoadingTableIdByUserId(partnerInfoBO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
}
