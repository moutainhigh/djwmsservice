package com.djcps.wms.inneruser.server;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.inneruser.model.param.InnerUserLoginPhoneBO;
import com.djcps.wms.inneruser.model.param.InnerUserLoginBO;
import com.djcps.wms.inneruser.model.param.UserSwitchSysBO;
import com.djcps.wms.inneruser.model.result.UserCodeVo;
import com.djcps.wms.inneruser.model.result.UserExchangeTokenVo;
import com.djcps.wms.inneruser.model.result.UserLogoutVo;
import com.djcps.wms.inneruser.request.InnerUserRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rpc.plugin.http.HTTPResponse;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * 内部用户 server
 *
 * @author Chengw
 * @since 2017/12/4 11:34.
 */
@Repository
public class InnerUserServer {

    private static Logger logger = LoggerFactory.getLogger(InnerUserServer.class);


    @Autowired
    private InnerUserRequest innerUserRequest;

    /**
     * 获取用户code
     *
     * @param userName
     * @return
     * @autuor Chengw
     * @since 2017/12/4  14:46
     */
    public String getUserCode(String userName) {
        HTTPResponse httpResponse = innerUserRequest.getCode(userName);
        if (httpResponse.isSuccessful()) {
            try {
                String body = httpResponse.getBodyString();
                if (StringUtils.isNotBlank(body)) {

                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    String json = gson.toJson(baseResult.getData());
                    UserCodeVo userCodeVo = gson.fromJson(json, UserCodeVo.class);
                    return userCodeVo.getCode();
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * App方式登录
     *
     * @param innerUserLoginBO
     * @return
     * @autuor Chengw
     * @since 2017/12/4  15:11
     */
    public HttpResult loginTokenWithApp(InnerUserLoginBO innerUserLoginBO) {
        String userCode = getUserCode(innerUserLoginBO.getUserName());
        if (StringUtils.isNotBlank(userCode)) {
            String password = DigestUtils.md5Hex(innerUserLoginBO.getPassword() + userCode);
            HTTPResponse httpResponse = innerUserRequest.getApplogin(innerUserLoginBO.getUserName(), password, AppConstant.LOGIN_TYPE);
            if (httpResponse.isSuccessful()) {
                try {
                    String body = httpResponse.getBodyString();
                    if (StringUtils.isNotBlank(body)) {
                        HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                        return baseResult;
                    }

                } catch (Exception e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 手机验证码登录
     *
     * @param innerUserLoginPhoneBO
     * @return
     * @autuor Chengw
     * @since 2017/12/20  09:12
     */
    public HttpResult loginTokenWithPhone(InnerUserLoginPhoneBO innerUserLoginPhoneBO) {
        HTTPResponse httpResponse = innerUserRequest.appLoginByPhone(innerUserLoginPhoneBO.getPhone(),
                Integer.valueOf(innerUserLoginPhoneBO.getPhoneCode()), AppConstant.LOGIN_TYPE);
        if (httpResponse.isSuccessful()) {
            try {
                String body = httpResponse.getBodyString();
                if (StringUtils.isNotBlank(body)) {
                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    return baseResult;
                }

            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 不同系统之间交换token
     *
     * @param userSwitchSysBO
     * @return
     * @autuor Chengw
     * @since 2017/12/4  15:10
     */
    public UserLogoutVo swap(UserSwitchSysBO userSwitchSysBO) {
        HTTPResponse httpResponse = innerUserRequest.getTokenLogin(userSwitchSysBO.getOldToken(), userSwitchSysBO.getSys());
        if (httpResponse.isSuccessful()) {
            try {
                String body = httpResponse.getBodyString();
                if (StringUtils.isNotBlank(body)) {
                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    if (baseResult.isSuccess()) {
                        String json = gson.toJson(baseResult.getData());
                        UserLogoutVo userLogoutVo = gson.fromJson(json, UserLogoutVo.class);
                        return userLogoutVo;
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 用户登出系统
     *
     * @param token
     * @return
     * @autuor Chengw
     * @since 2017/12/4  15:24
     */
    public Boolean logout(String token) {
        HTTPResponse httpResponse = innerUserRequest.getLogout(token);
        if (httpResponse.isSuccessful()) {
            try {
                String body = httpResponse.getBodyString();
                if (StringUtils.isNotBlank(body)) {
                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    if (baseResult.isSuccess()) {
                        return true;
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 置换token,、将临时的转换为固定token
     *
     * @param onceToken
     * @return
     */
    public String exchangeToken(final String onceToken) {
        HTTPResponse httpResponse = innerUserRequest.getExchangetoken(onceToken);
        if (httpResponse.isSuccessful()) {
            String body = httpResponse.getBodyString();
            if (StringUtils.isNotBlank(body)) {
                try {
                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    if (baseResult.isSuccess()) {
                        String json = gson.toJson(baseResult.getData());
                        UserExchangeTokenVo userExchangeTokenVo = gson.fromJson(json, UserExchangeTokenVo.class);
                        return userExchangeTokenVo.getToken();
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 修改内部用户密码
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public Boolean changeUserPassword(String userId, String oldPassword, String newPassword) {
        try {
            HTTPResponse httpResponse = innerUserRequest.getModifyPassword(userId, oldPassword, newPassword);
            if (httpResponse.isSuccessful()) {
                String body = httpResponse.getBodyString();
                if (StringUtils.isNotBlank(body)) {
                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    return baseResult.isSuccess();
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 发送登录手机验证码
     *
     * @param phone
     * @return
     * @autuor Chengw
     * @since 2017/12/20  09:16
     */
    public Boolean sendLoginCode(String phone) {
        try {
            HTTPResponse httpResponse = innerUserRequest.sendLoginCode(phone);
            if (httpResponse.isSuccessful()) {
                String body = httpResponse.getBodyString();
                if (StringUtils.isNotBlank(body)) {
                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    return baseResult.isSuccess();
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

}
