package com.djcps.wms.inneruser.service;

import com.djcps.wms.inneruser.model.param.InnerUserChangePasswordPo;
import com.djcps.wms.inneruser.model.param.InnerUserLoginPo;
import com.djcps.wms.inneruser.model.param.UserSwitchSysPo;
import com.djcps.wms.inneruser.model.result.UserInfoVo;

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
     * @autuor Chengw
     * @since 2017/12/4  16:40
     * @param innerUserLoginPo
     * @return
     */
    Map<String,Object> loginTokenWithApp(InnerUserLoginPo innerUserLoginPo);

    /**
     * 不同系统之间交换token 
     * @autuor Chengw
     * @since 2017/12/4  16:42
     * @param userSwitchSysPo
     * @return
     */
    Map<String,Object> swap(UserSwitchSysPo userSwitchSysPo);

    /**
     * 用户登出系统 
     * @autuor Chengw
     * @since 2017/12/4  16:42
     * @param token
     * @return
     */
    Boolean logout(String token);

    /**
     *  置换token,将临时的转换固定token 
     * @autuor Chengw
     * @since 2017/12/4  16:42
     * @param onceToken
     * @return
     */
    String exchangeToken(final String onceToken);

    /**
     * 更改用户密码
     * @autuor Chengw
     * @since 2017/12/4  16:42
     * @param innerUserChangePasswordPo
     * @return
     */
    Map<String,Object> changeInnerUserPassword(InnerUserChangePasswordPo innerUserChangePasswordPo);

    /**
     * 获取内部用户信息
     * 从redis中拿去
     * @autuor Chengw
     * @since 2017/12/4  16:43
     * @param token
     * @return
     */
    UserInfoVo getInnerUserInfoFromRedis(String token);

    /**
     * 设置用户信息到Cookie
     * @autuor Chengw
     * @since 2017/12/4  16:44
     * @param value
     * @param response
     * @return
     */
    Boolean setUserCookie(String value, HttpServletResponse response);
}
