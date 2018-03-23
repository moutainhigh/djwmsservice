package com.djcps.wms.loadingTask.request;

import com.djcps.wms.commons.config.ParamsConfig;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 查询订单服务
 * @author  wyb
 * @create  2018/3/19
 **/
@RPCClientFields(urlfield = "UPDATE_ORDER_SERVER", urlbean = ParamsConfig.class)
public interface LoadingTaskOrderHttpRequest {
    /**
     * 批量获取订单详情
     * @author  wyb
     * @param json
     * @return http
     * @create  2018/3/19
     **/
    @Headers("content-type:application/json")
    @POST("order/getInfoByChildIds.do")
    HTTPResponse getOrderDetailsList(@Body RequestBody json);
}
