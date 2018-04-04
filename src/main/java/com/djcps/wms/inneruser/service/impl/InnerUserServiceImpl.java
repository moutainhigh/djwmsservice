package com.djcps.wms.inneruser.service.impl;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.config.ParamsConfig;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.CookiesUtil;
import com.djcps.wms.inneruser.model.param.InnerUserChangePasswordBO;
import com.djcps.wms.inneruser.model.param.InnerUserLoginPhoneBO;
import com.djcps.wms.inneruser.model.param.InnerUserLoginBO;
import com.djcps.wms.inneruser.model.param.UserSwitchSysBO;
import com.djcps.wms.inneruser.model.result.UserInfoVO;
import com.djcps.wms.inneruser.model.result.UserLogoutVO;
import com.djcps.wms.inneruser.redis.InnerUserRedisDao;
import com.djcps.wms.inneruser.server.InnerUserServer;
import com.djcps.wms.inneruser.service.InnerUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Chengw
 * @since 2017/12/4 11:35.
 */
@Service
public class InnerUserServiceImpl implements InnerUserService {

    private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(InnerUserServiceImpl.class);

    @Autowired
    private InnerUserServer innerUserServer;

    @Autowired
    private InnerUserRedisDao innerUserRedisDao;

    /**
     * app方式登录
     * @autuor Chengw
     * @since 2017/12/4  16:40
     * @param innerUserLoginBO
     * @return
     */
    @Override
    public Map<String, Object> loginTokenWithApp(InnerUserLoginBO innerUserLoginBO) {
        HttpResult baseResult = innerUserServer.loginTokenWithApp(innerUserLoginBO);
        return MsgTemplate.customMsg(baseResult);
    }

    /**
     * 手机验证码登录
     * @autuor Chengw
     * @since 2017/12/20  09:15
     * @param innerUserLoginPhoneBO
     * @return
     */
    @Override
    public Map<String, Object> loginTokenWithPhone(InnerUserLoginPhoneBO innerUserLoginPhoneBO) {
        HttpResult baseResult = innerUserServer.loginTokenWithPhone(innerUserLoginPhoneBO);
        return MsgTemplate.customMsg(baseResult);
    }

    /**
     * 发送手机验证码 
     * @autuor Chengw
     * @since 2017/12/20  09:19
     * @param innerUserLoginPhoneBO
     * @return
     */
    @Override
    public Map<String, Object> sendLoginCode(InnerUserLoginPhoneBO innerUserLoginPhoneBO) {
        Boolean result = innerUserServer.sendLoginCode(innerUserLoginPhoneBO.getPhone());
        if(result){
            return MsgTemplate.successMsg();
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 不同系统之间交换token
     * @autuor Chengw
     * @since 2017/12/4  16:42
     * @param userSwitchSysBO
     * @return
     */
    @Override
    public Map<String, Object> swap(UserSwitchSysBO userSwitchSysBO){
        UserLogoutVO userLogoutVO = innerUserServer.swap(userSwitchSysBO);
        if(StringUtils.isNotBlank(userLogoutVO.getUrl())){
           return MsgTemplate.successMsg(userLogoutVO);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 用户登出系统
     * @autuor Chengw
     * @since 2017/12/4  16:42
     * @param token
     * @return
     */
    @Override
    public Boolean logout(String token) {
        return innerUserServer.logout(token);
    }

    /**
     *  置换token,将临时的转换固定token
     * @autuor Chengw
     * @since 2017/12/4  16:42
     * @param onceToken
     * @return
     */
    @Override
    public String exchangeToken(String onceToken) {
        String token = innerUserServer.exchangeToken(onceToken);
        if(StringUtils.isBlank(token)){
            return onceToken;
        }
        return token;
    }

    /**
     * 更改用户密码
     * @autuor Chengw
     * @since 2017/12/4  16:42
     * @param innerUserChangePasswordBO
     * @return
     */
    @Override
    public Map<String, Object> changeInnerUserPassword(InnerUserChangePasswordBO innerUserChangePasswordBO) {
        try{
            UserInfoVO userInfoVO = innerUserRedisDao.getInnerUserInfo(innerUserChangePasswordBO.getToken());
            String userCode = innerUserServer.getUserCode(userInfoVO.getId());
            if(!ObjectUtils.isEmpty(userInfoVO) && StringUtils.isNotBlank(userCode)){
                String oldPassword = DigestUtils.md5Hex(innerUserChangePasswordBO.getOldPassword()+userCode);
                String newPassword = DigestUtils.md5Hex(innerUserChangePasswordBO.getNewPassword()+userCode);
                Boolean status = innerUserServer.changeUserPassword(String.valueOf(userInfoVO.getId()),oldPassword,newPassword);
                if(status){
                    return  MsgTemplate.successMsg();
                }
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    @Override
    public UserInfoVO getInnerUserInfoFromRedis(String token) {
        UserInfoVO userInfoVO = null;
        try {
            userInfoVO = innerUserRedisDao.getInnerUserInfo(token);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("内部用户信息 {}",e.getMessage());
        }
        return userInfoVO;
    }

    /**
     * 设置用户信息到Cookie
     * @param value
     * @param response
     * @return
     */
    @Override
    public Boolean setUserCookie(String value, HttpServletResponse response){
        if(StringUtils.isNotBlank(value)){
            CookiesUtil.setCookie(response, ParamsConfig.INNER_USER_COOKIE_NAME,value, ParamsConfig.COOKIE_TIMEOUT);
            return true;
        }
        return false;
    }
}
