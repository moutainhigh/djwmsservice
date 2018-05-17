package com.djcps.wms.inneruser.service;

import com.djcps.wms.inneruser.model.param.InnerUserChangePasswordBO;
import com.djcps.wms.inneruser.model.param.InnerUserLoginBO;
import com.djcps.wms.inneruser.model.param.InnerUserLoginPhoneBO;
import com.djcps.wms.inneruser.model.param.UserSwitchSysBO;
import com.djcps.wms.inneruser.model.result.UserInfoVO;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 内部用户 service
 * @author Chengw
 * @since 2017/12/4 11:34.
 */
public interface InnerUserService {

    /**
     * app方式登录
     * @author Chengw
     * @since 2017/12/4  16:40
     * @param innerUserLoginBO InnerUserLoginBO
     * @return Map<String,Object>
     */
    Map<String,Object> loginTokenWithApp(InnerUserLoginBO innerUserLoginBO);

    /**
     * app手机验证码登录
     * @param innerUserLoginPhoneBO InnerUserLoginPhoneBO
     * @return Map<String,Object>
     */
    Map<String,Object> loginTokenWithPhone(InnerUserLoginPhoneBO innerUserLoginPhoneBO);

    /**
     * 发送手机验证码
     * @param innerUserLoginPhoneBO InnerUserLoginPhoneBO
     * @return
     */
    Map<String,Object> sendLoginCode(InnerUserLoginPhoneBO innerUserLoginPhoneBO);


    /**
     * 不同系统之间交换token 
     * @author Chengw
     * @since 2017/12/4  16:42
     * @param userSwitchSysBO UserSwitchSysBO
     * @return Map<String,Object>
     */
    Map<String,Object> swap(UserSwitchSysBO userSwitchSysBO);

    /**
     * 用户登出系统 
     * @author Chengw
     * @since 2017/12/4  16:42
     * @param token String
     * @return Boolean
     */
    Boolean logout(String token);

    /**
     *  置换token,将临时的转换固定token 
     * @author Chengw
     * @since 2017/12/4  16:42
     * @param onceToken String
     * @return String
     */
    String exchangeToken(final String onceToken);

    /**
     * 更改用户密码
     * @author Chengw
     * @
     * @since 2017/12/4  16:42
     * @param innerUserChangePasswordBO InnerUserChangePasswordBO
     * @return  Map<String,Object>
     */
    Map<String,Object> changeInnerUserPassword(InnerUserChangePasswordBO innerUserChangePasswordBO);

    /**
     * 获取内部用户信息
     * 从redis中拿去
     * @author Chengw
     * @since 2017/12/4  16:43
     * @param token String
     * @return UserInfoVO
     */
    UserInfoVO getInnerUserInfoFromRedis(String token);

    /**
     * 设置用户信息到Cookie
     * @author Chengw
     * @since 2017/12/4  16:44
     * @param value String
     * @param response HttpServletResponse
     * @return Boolean
     */
    Boolean setUserCookie(String value, HttpServletResponse response);
}
