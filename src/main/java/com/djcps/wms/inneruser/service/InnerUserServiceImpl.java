package com.djcps.wms.inneruser.service;

import com.djcps.wms.commons.config.ParamsConfig;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.CookiesUtil;
import com.djcps.wms.inneruser.model.param.InnerUserChangePasswordPo;
import com.djcps.wms.inneruser.model.param.InnerUserLoginPo;
import com.djcps.wms.inneruser.model.param.UserSwitchSysPo;
import com.djcps.wms.inneruser.model.result.UserInfoVo;
import com.djcps.wms.inneruser.model.result.UserLogoutVo;
import com.djcps.wms.inneruser.redis.InnerUserRedisDao;
import com.djcps.wms.inneruser.server.InnerUserServer;
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

    private static Logger logger = LoggerFactory.getLogger(InnerUserServiceImpl.class);

    @Autowired
    private InnerUserServer innerUserServer;

    @Autowired
    private InnerUserRedisDao innerUserRedisDao;

    /**
     * app方式登录
     * @autuor Chengw
     * @since 2017/12/4  16:40
     * @param innerUserLoginPo
     * @return
     */
    @Override
    public Map<String, Object> loginTokenWithApp(InnerUserLoginPo innerUserLoginPo) {
        HttpResult baseResult = innerUserServer.loginTokenWithApp(innerUserLoginPo);
        return MsgTemplate.customMsg(baseResult);
    }

    /**
     * 不同系统之间交换token
     * @autuor Chengw
     * @since 2017/12/4  16:42
     * @param userSwitchSysPo
     * @return
     */
    @Override
    public Map<String, Object> swap(UserSwitchSysPo userSwitchSysPo){
        UserLogoutVo userLogoutVo = innerUserServer.swap(userSwitchSysPo);
        if(StringUtils.isNotBlank(userLogoutVo.getUrl())){
           return MsgTemplate.successMsg(userLogoutVo);
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
        if(StringUtils.isNotBlank(token)){
            return onceToken;
        }
        return token;
    }

    /**
     * 更改用户密码
     * @autuor Chengw
     * @since 2017/12/4  16:42
     * @param innerUserChangePasswordPo
     * @return
     */
    @Override
    public Map<String, Object> changeInnerUserPassword(InnerUserChangePasswordPo innerUserChangePasswordPo) {
        try{
            UserInfoVo userInfoVo = innerUserRedisDao.getInnerUserInfo(innerUserChangePasswordPo.getToken());
            String userCode = innerUserServer.getUserCode(userInfoVo.getUids());
            if(!ObjectUtils.isEmpty(userInfoVo) && StringUtils.isNotBlank(userCode)){
                String oldPassword = DigestUtils.md5Hex(innerUserChangePasswordPo.getOldPassword()+userCode);
                String newPassword = DigestUtils.md5Hex(innerUserChangePasswordPo.getNewPassword()+userCode);
                Boolean status = innerUserServer.changeUserPassword(String.valueOf(userInfoVo.getId()),oldPassword,newPassword);
                if(status){
                    return  MsgTemplate.successMsg();
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    @Override
    public UserInfoVo getInnerUserInfoFromRedis(String token) {
        UserInfoVo userInfoVo = null;
        try {
            userInfoVo = innerUserRedisDao.getInnerUserInfo(token);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("内部用户信息 {}",e.getMessage());
        }
        return userInfoVo;
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
            CookiesUtil.setCookie(response, ParamsConfig.INNER_USER_COOKIE_NAME,value,ParamsConfig.COOKIE_TIMEOUT);
            return true;
        }
        return false;
    }
}
