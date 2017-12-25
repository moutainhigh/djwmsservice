package com.djcps.wms.commons.server;

import com.djcps.wms.commons.model.param.PhoneCodeBO;
import com.djcps.wms.commons.request.MsgHttpRequest;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rpc.plugin.http.HTTPResponse;

/**
 * 短信消息发送 服务类
 * @author Chengw
 * @since 2017/10/31 16:34.
 */
@Repository
public class MsgServer {

    Gson gson = new Gson();

    @Autowired
    private MsgHttpRequest msgHttpRequest;

    /**
     * 发送手机短信验证码
     * @param phoneCodeBO
     * @return
     */
    public Boolean sendPhoneCode(PhoneCodeBO phoneCodeBO){
        HTTPResponse httpResponse = msgHttpRequest.sendCode(phoneCodeBO.getPhone(),
                phoneCodeBO.getCode(),
                phoneCodeBO.getAppSystem());
        if(httpResponse.isSuccessful()){
            return true;
        }
        return false;
    }

}
