package com.djcps.wms.commons.request;

import com.djcps.wms.commons.config.ParamsConfig;
import com.sun.org.apache.regexp.internal.RE;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * @title 仓库库区库位编码http请求
 * @author  wzy
 * @create  2017/12/20 17:18
 **/
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface GetCodeRequest {
    @Headers("content-type:application/json")
    @POST("code/getCode.do")
    public HTTPResponse getCode(@Body RequestBody rb);

}
