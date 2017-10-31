package com.djcps.wms.commons.request;

import com.djcps.wms.commons.config.ParamsConfig;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 消息服务接口
 * @author 褚长慧
 * @date 2017年8月19日下午2:11:56
 */
@RPCClientFields(urlfield = "MESSAGE_SERVER", urlbean = ParamsConfig.class)
public interface MsgHttpRequest {
   /**
    * 发送验证码
    * @param phone
    * @param code
    * @param appSystem
    * @return
    * @author 褚长慧
    * @date 2017年7月27日 下午5:55:08
    */
    @FormUrlEncoded
    @POST("sendmsgphone.do")
    HTTPResponse sendCode(@Field("phone") String phone,
                          @Field("msg") String code,
                          @Field("appSystem") String appSystem);
    
}

