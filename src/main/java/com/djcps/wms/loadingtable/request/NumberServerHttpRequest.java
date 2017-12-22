package com.djcps.wms.loadingtable.request;

import com.djcps.wms.commons.config.ParamsConfig;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * @title 获取随机编号
 * @author  wzy
 * @create  2017/12/21 13:54
 **/
@RPCClientFields(urlfield = "NUMBER_SERVER", urlbean = ParamsConfig.class)
public interface NumberServerHttpRequest {

    /**
     * 获取随机编号http接口
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("getnumber.do")
    HTTPResponse getnumber(@Body RequestBody json);
}
