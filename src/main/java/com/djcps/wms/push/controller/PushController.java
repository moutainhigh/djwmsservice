package com.djcps.wms.push.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.wms.commons.aop.inneruser.annotation.InnerUser;
import com.djcps.wms.commons.aop.inneruser.annotation.InnerUserToken;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.inneruser.model.result.UserInfoVo;
import com.djcps.wms.push.model.PushAppBO;
import com.djcps.wms.push.model.PushMsgBO;
import com.djcps.wms.push.service.PushService;
import com.djcps.wms.stocktaking.model.AddStocktakingBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Validation;
import java.util.Map;
import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * 消息推送 控制层
 * @author Chengw
 * @since 2018/1/23 15:24.
 */
@RestController
@RequestMapping(value = "/push")
public class PushController {

    private static Logger logger = LoggerFactory.getLogger(PushController.class);

    @Resource
    private PushService pushService;


    /**
     * 设备注册
     * @param json
     * @param innerUser
     * @return
     */
    @RequestMapping(name="设备注册",value = "/register", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> register(@RequestBody(required = false) String json, @InnerUser UserInfoVo innerUser){
        try {
            PushAppBO param= gson.fromJson(json,PushAppBO.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<PushAppBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            param.setUserid(innerUser.getUids());
            param.setAppsystem(AppConstant.WMS);
            return pushService.registerMsg(param);
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }


    /**
     * 设备注销
     * @param json
     * @param innerUser
     * @return
     */
    @RequestMapping(name="设备注销",value = "/logout", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> logout(@RequestBody(required = false) String json, @InnerUser UserInfoVo innerUser){
        try {
            PushAppBO param= gson.fromJson(json,PushAppBO.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<PushAppBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            param.setUserid(innerUser.getUids());
            param.setAppsystem(AppConstant.WMS);
            return pushService.logoutMsg(param);
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 消息推送
     * @param json
     * @return
     */
    @RequestMapping(name="发送消息",value = "/send", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> send(@RequestBody(required = false) String json){
        try {
            PushMsgBO param= gson.fromJson(json,PushMsgBO.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<PushMsgBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            param.setAppSystem(AppConstant.WMS);
            return pushService.sendAppMsg(param);
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
}
