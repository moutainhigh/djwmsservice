package com.djcps.wms.inneruser.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.aop.inneruser.annotation.InnerUserToken;
import com.djcps.wms.commons.aop.inneruser.annotation.OperatorAnnotation;
import com.djcps.wms.commons.aop.log.AddLog;
import com.djcps.wms.commons.config.ParamsConfig;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.OperatorInfoBO;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.CookiesUtil;
import com.djcps.wms.inneruser.enums.InnerUserMsgEnum;
import com.djcps.wms.inneruser.model.param.*;
import com.djcps.wms.inneruser.model.result.UserExchangeTokenVO;
import com.djcps.wms.inneruser.model.result.UserInfoVO;
import com.djcps.wms.inneruser.model.result.UserLogoutVO;
import com.djcps.wms.inneruser.service.InnerUserService;
import com.djcps.wms.permission.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 	
import javax.validation.Validation;
import java.util.Map;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toComplex;
import static com.djcps.wms.commons.utils.GsonUtils.gson;
/**
 * @author Chengw
 * @since 2017/12/4 11:12.
 */
@RestController
@RequestMapping("/innerUser")
@ResponseBody
public class InnerUserController {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(InnerUserController.class);

    @Autowired
    private InnerUserService innerUserService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(name = "test", value = "/test", method = {RequestMethod.GET,RequestMethod.POST})
    public Map<String, Object> login(@RequestBody(required = false) String json, @OperatorAnnotation OperatorInfoBO operatorInfoBO) {
        return MsgTemplate.successMsg(operatorInfoBO);
    }

    /**
     * APP登录页面
     * @param json
     * @return
     */
    @RequestMapping(name = "APP登录页面",value = "/login", method = {RequestMethod.POST})
    public Map<String, Object> login(@RequestBody(required = false) String json) {
        try {
          
            InnerUserLoginBO innerUserLoginBO = gson.fromJson(json,InnerUserLoginBO.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(innerUserLoginBO, new HibernateSupportedValidator<InnerUserLoginBO>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate()
                    .result(toComplex());
            if(ret.isSuccess()){
                Map<String, Object> result = innerUserService.loginTokenWithApp(innerUserLoginBO);
                return result;
            }
        }catch (Exception e){
            LOGGER.error("app登录异常：{} ",e.getMessage());
            e.printStackTrace();
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * APP登录页面-手机验证码
     * @param json
     * @return
     */
    @RequestMapping(name = "APP登录页面-手机验证码",value = "/loginWithPhone", method = {RequestMethod.POST})
    public Map<String, Object> loginWithPhone(@RequestBody(required = false) String json) {
        try {
            InnerUserLoginPhoneBO param = gson.fromJson(json,InnerUserLoginPhoneBO.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param, new HibernateSupportedValidator<InnerUserLoginPhoneBO>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate()
                    .result(toComplex());
            if(ret.isSuccess()){
                Map<String, Object> result = innerUserService.loginTokenWithPhone(param);
                return result;
            }
        }catch (Exception e){
            LOGGER.error("app登录异常：{} ",e.getMessage());
            e.printStackTrace();
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 发送登录手机验证码
     * @param json
     * @return
     */
    @RequestMapping(name = "发送手机验证码",value = "/sendLoginCode", method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String, Object> sendLoginCode(@RequestBody(required = false) String json) {
        try {
            InnerUserLoginPhoneBO param = gson.fromJson(json,InnerUserLoginPhoneBO.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param, new HibernateSupportedValidator<InnerUserLoginPhoneBO>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate()
                    .result(toComplex());
            if(ret.isSuccess()){
                Map<String, Object> result = innerUserService.sendLoginCode(param);
                return result;
            }
        }catch (Exception e){
            LOGGER.error("app登录异常：{} ",e.getMessage());
            e.printStackTrace();
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 该接口用于获取用户信息
     * @param token token
     * @return json数据 ，包含用户信息
     */
    @RequestMapping(name = "该接口用于获取用户信息",value = "/info",method = {RequestMethod.GET,RequestMethod.POST})
    @AddLog(module = "内部用户",value = "该接口用于获取用户信息")
    public Map<String, Object> getInfo(@InnerUserToken String token) {
        UserInfoVO userInfoVO = innerUserService.getInnerUserInfoFromRedis(token);
        return MsgTemplate.successMsg(userInfoVO);
    }

    /**
     * 该接口用于修改密码
     * @param json
     * @param token
     * @return
     */
    @RequestMapping(name = "该接口用于修改密码",value = "/changePassword",method = {RequestMethod.GET,RequestMethod.POST})
    @AddLog(module = "内部用户",value = "该接口用于修改密码")
    public Map<String, Object> changeUserPassword(@RequestBody(required = false) String json ,@InnerUserToken String token) {
        try {
            InnerUserChangePasswordBO innerUserChangePasswordBO = gson.fromJson(json,InnerUserChangePasswordBO.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(innerUserChangePasswordBO, new HibernateSupportedValidator<InnerUserChangePasswordBO>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate()
                    .result(toComplex());
            if(ret.isSuccess()){
                if(StringUtils.isBlank(innerUserChangePasswordBO.getToken())){
                    innerUserChangePasswordBO.setToken(token);
                }
                return innerUserService.changeInnerUserPassword(innerUserChangePasswordBO);
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("内部用户修改密码 {}",e.getMessage());
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 该接口用于不同系统切换间交换token
     * 需求中不予支持切换系统
     * 代码予以保留，以供以后有需要支持可能
     * @return json格式的数据 ，包含返回跳转的url
     */
    @RequestMapping(name = "该接口用于不同系统切换间交换token",value = "/switchSys", method = {RequestMethod.GET,RequestMethod.POST})
    public Map<String, Object> switchSys(@RequestBody(required = false) String json,@InnerUserToken String token) {
        try{
            UserSwitchSysBO userSwitchSysBO = gson.fromJson(json,UserSwitchSysBO.class);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(userSwitchSysBO, new HibernateSupportedValidator<UserSwitchSysBO>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate()
                    .result(toComplex());
            if(ret.isSuccess()){
                if(StringUtils.isNotBlank(userSwitchSysBO.getOldToken())){
                    userSwitchSysBO.setOldToken(token);
                }
                return innerUserService.swap(userSwitchSysBO);
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("切换系统异常：{}",e.getMessage());
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 将一次性Token转换为固定Token
     * 由于Oncetoken传递数据格式由内部统一用户服务传递
     * @return json格式的数据 ，包含返回跳转的url
     */
    @RequestMapping(name = "将一次性Token转换为固定Token", value = "/handleOnceToken", method = RequestMethod.POST)
    public Map<String, Object> getToken(@RequestBody(required = false) String json, HttpServletResponse response) {
        try {
            UserTokenBO userTokenBO = gson.fromJson(json,UserTokenBO.class);
            if (StringUtils.isNotBlank(userTokenBO.getToken())) {
                String token  = innerUserService.exchangeToken(userTokenBO.getToken());
                if(StringUtils.isNotBlank(token)){
                    UserExchangeTokenVO userExchangeTokenVO = new UserExchangeTokenVO(token);
                    if(StringUtils.isNotBlank(userExchangeTokenVO.getToken())){
                        if(innerUserService.setUserCookie(userExchangeTokenVO.getToken(),response)){
                            UserInfoVO userInfoVO = innerUserService.getInnerUserInfoFromRedis(userExchangeTokenVO.getToken());
                            return MsgTemplate.successMsg(userInfoVO);
                        }
                    }
                }
                return MsgTemplate.failureMsg(InnerUserMsgEnum.TOEKN_NULL);
            }
        }catch (Exception e){
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 用户登出系统
     * @param token the token
     * @return map
     */
    @RequestMapping(name = "用户登出系统", value = "/logout")
    public Map<String, Object> logout(@InnerUserToken String token, HttpServletResponse response,@InnerUserToken UserInfoVO userInfoVO) {
        Boolean isSuccess = innerUserService.logout(token);
        //无论是否成功退出内部统一登录系统，本系统内直接可以退出
        if(isSuccess) {
            CookiesUtil.setCookie(response,ParamsConfig.INNER_USER_COOKIE_NAME,"",0);
            UserLogoutVO userLogoutVO = new UserLogoutVO(ParamsConfig.INNER_USER_LOGIN_URL);
            permissionService.delUserRedisPermission(userInfoVO.getId());
            return MsgTemplate.successMsg(userLogoutVO);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

}
