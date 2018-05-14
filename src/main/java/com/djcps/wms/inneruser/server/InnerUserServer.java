package com.djcps.wms.inneruser.server;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.inneruser.model.param.InnerUserLoginBO;
import com.djcps.wms.inneruser.model.param.InnerUserLoginPhoneBO;
import com.djcps.wms.inneruser.model.param.UserSwitchSysBO;
import com.djcps.wms.inneruser.model.result.UserCodeVO;
import com.djcps.wms.inneruser.model.result.UserExchangeTokenVO;
import com.djcps.wms.inneruser.model.result.UserLogoutVO;
import com.djcps.wms.inneruser.model.result.UserTokenVO;
import com.djcps.wms.inneruser.request.InnerUserRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import rpc.plugin.http.HTTPResponse;

import javax.annotation.Resource;

import static com.djcps.wms.commons.utils.GsonUtils.gson;
import static com.djcps.wms.commons.utils.HttpResultUtils.*;

/**
 * 内部用户 server
 *
 * @author Chengw
 * @since 2017/12/4 11:34.
 */
@Repository
public class InnerUserServer {

    private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(InnerUserServer.class);

    @Resource
    private InnerUserRequest innerUserRequest;

    /**
     * 获取用户code
     *
     * @param userName String
     * @return String
     * @author Chengw
     * @since 2017/12/4 14:46
     */
    public String getUserCode(String userName) {
        HTTPResponse httpResponse = innerUserRequest.getCode(userName);
        if (httpResponse.isSuccessful()) {
            try {
                String body = httpResponse.getBodyString();
                if (StringUtils.isNotBlank(body)) {

                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    String json = gson.toJson(baseResult.getData());
                    UserCodeVO userCodeVO = gson.fromJson(json, UserCodeVO.class);
                    return userCodeVO.getCode();
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * App方式登录
     *
     * @param innerUserLoginBO InnerUserLoginBO
     * @return HttpResult
     * @author Chengw
     * @since 2017/12/4 15:11
     */
    public HttpResult loginTokenWithApp(InnerUserLoginBO innerUserLoginBO) {
        HTTPResponse httpResponse = innerUserRequest.getApplogin(innerUserLoginBO.getUserName(),
                innerUserLoginBO.getPassword(), AppConstant.LOGIN_TYPE);
        return returnResult(httpResponse);
    }
    /**
     * 获取用户token
     * @param result HttpResult
     * @return HttpResult
     */
    public String getUserToken(HttpResult result) {
        String json = gson.toJson(result.getData());
        UserTokenVO userTokenVO = gson.fromJson(json, UserTokenVO.class);
        return userTokenVO.getToken();
    }
    /**
     * 手机验证码登录
     *
     * @param innerUserLoginPhoneBO InnerUserLoginPhoneBO
     * @return HttpResult
     * @author Chengw
     * @since 2017/12/20 09:12
     */
    public HttpResult loginTokenWithPhone(InnerUserLoginPhoneBO innerUserLoginPhoneBO) {
        HTTPResponse httpResponse = innerUserRequest.appLoginByPhone(innerUserLoginPhoneBO.getPhone(),
                Integer.valueOf(innerUserLoginPhoneBO.getPhoneCode()), AppConstant.LOGIN_TYPE);
        return returnResult(httpResponse);
    }

    /**
     * 不同系统之间交换token
     *
     * @param userSwitchSysBO UserSwitchSysBO
     * @return UserLogoutVO
     * @author Chengw
     * @since 2017/12/4 15:10
     */
    public UserLogoutVO swap(UserSwitchSysBO userSwitchSysBO) {
        HTTPResponse httpResponse = innerUserRequest.getTokenLogin(userSwitchSysBO.getOldToken(),
                userSwitchSysBO.getSys());
        if (httpResponse.isSuccessful()) {
            try {
                String body = httpResponse.getBodyString();
                if (StringUtils.isNotBlank(body)) {
                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    if (baseResult.isSuccess()) {
                        String json = gson.toJson(baseResult.getData());
                        return gson.fromJson(json, UserLogoutVO.class);
                    }
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 用户登出系统
     *
     * @param token String
     * @return Boolean
     * @author Chengw
     * @since 2017/12/4 15:24
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
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 置换token,、将临时的转换为固定token
     *
     * @param onceToken String
     * @return String
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
                        UserExchangeTokenVO userExchangeTokenVO = gson.fromJson(json, UserExchangeTokenVO.class);
                        return userExchangeTokenVO.getToken();
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 修改内部用户密码
     *
     * @param userId String
     * @param oldPassword String
     * @param newPassword String
     * @return Boolean
     */
    public Boolean changeUserPassword(String userId, String oldPassword, String newPassword) {
        try {
            HTTPResponse httpResponse = innerUserRequest.getModifyPassword(userId, oldPassword, newPassword);
            HttpResult result = returnResult(httpResponse);
            if(!ObjectUtils.isEmpty(result)){
                return result.isSuccess();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 发送登录手机验证码
     *
     * @param phone String
     * @return Boolean
     * @author Chengw
     * @since 2017/12/20 09:16
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
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

}
