package com.djcps.wms.commons.server;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.model.param.PhoneCodePo;
import com.djcps.wms.commons.request.MsgHttpRequest;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
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
     * @param phoneCodePo
     * @return
     */
    public Boolean sendPhoneCode(PhoneCodePo phoneCodePo){
        HTTPResponse httpResponse = msgHttpRequest.sendCode(phoneCodePo.getPhone(),
                phoneCodePo.getCode(),
                phoneCodePo.getAppSystem());
        if(httpResponse.isSuccessful()){
            return true;
        }
        return false;
    }

}
