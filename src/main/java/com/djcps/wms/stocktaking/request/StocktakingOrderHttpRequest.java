package com.djcps.wms.stocktaking.request;

import com.djcps.wms.commons.config.ParamsConfig;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 零时查询订单服务
 * @author  wzy
 * @param
 * @return
 * @create  2018/1/10 18:00
 **/
@RPCClientFields(urlfield = "UPDATE_ORDER_SERVER", urlbean = ParamsConfig.class)
public interface StocktakingOrderHttpRequest {
    /**
     * 批量获取订单详情
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/10 9:29
     **/
    @Headers("content-type:application/json")
    @POST("order/getInfoByChildIds.do")
    HTTPResponse getOrderDetailsList(@Body RequestBody json);
}
