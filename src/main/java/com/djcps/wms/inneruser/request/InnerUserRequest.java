package com.djcps.wms.inneruser.request;

import com.djcps.wms.commons.config.ParamsConfig;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 内部用户 登录部门
 * @author Chengw
 * @since 2017/12/4 11:20.
 */
@RPCClientFields(urlfield = "INNER_USER_SERVER", urlbean = ParamsConfig.class)
public interface InnerUserRequest {

    /**
     * APP登录接口
     * @param username
     * @param password
     * @param appname
     * @return
     */
    @FormUrlEncoded
    @POST("applogin.do")
    HTTPResponse getApplogin(@Field("username") String username,
                             @Field("password") String password,
                             @Field("appname") String appname);

    /**
     * APP手机验证码登录
     * @param phone
     * @param phoneCode
     * @param appname
     * @return
     */
    @FormUrlEncoded
    @POST("appLoginByPhone.do")
    HTTPResponse appLoginByPhone(@Field("phone") String phone,
                                 @Field("phoneCode") Integer phoneCode,
                                 @Field("appname") String appname);

    /**
     * 置换固定Token
     * @param onceToken
     * @return
     */
    @FormUrlEncoded
    @POST("exchangetoken.do")
    HTTPResponse getExchangetoken(@Field("onceToken") String onceToken);

    /**
     * 退出登录
     * @param  token
     * @return
     */
    @FormUrlEncoded
    @POST("logout.do")
    HTTPResponse getLogout(@Field("token") String token);

    /**
     * 系统登录切换
     * @param  token
     * @param appname
     * @return
     */
    @FormUrlEncoded
    @POST("tokenlogin.do")
    HTTPResponse getTokenLogin(@Field("token") String token, @Field("appname") String appname);

    /**
     * 修改密码
     * @param userid
     * @param oldpassword
     * @param newpassword
     * @return
     */
    @FormUrlEncoded
    @POST("modifypassword.do")
    HTTPResponse getModifyPassword(@Field("userid") String userid,
                                   @Field("oldpassword") String oldpassword,
                                   @Field("newpassword") String newpassword);

    /**
     * 获取用户code
     * @param username
     * @return
     */
    @FormUrlEncoded
    @POST("getcode.do")
    HTTPResponse getCode(@Field("username") String username);

    /**
     * 发送登录手机验证码
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("sendLoginCode.do")
    HTTPResponse sendLoginCode(@Field("phone") String phone);
}
