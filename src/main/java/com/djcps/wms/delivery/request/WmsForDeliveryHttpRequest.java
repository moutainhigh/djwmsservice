package com.djcps.wms.delivery.request;

import com.djcps.wms.commons.config.ParamsConfig;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 提货相关的HTTP请求接口
 * @author Chengw
 * @since 2018/1/31 08:40.
 */
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsForDeliveryHttpRequest {

    /**
     * 完成提货操作
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("delivery/completeTheDeliveryOrder.do")
    HTTPResponse completeTheDeliveryOrder(@Body RequestBody json);

    /**
     * 获取提货订单列表 
     * @autuor Chengw
     * @since 2018/1/31  09:29
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("delivery/getDeliveryOrderList.do")
    HTTPResponse getDeliveryOrderList(@Body RequestBody json);


    /**
     * 获取提货单列表
     * @autuor Chengw
     * @since 2018/1/31  09:32
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("delivery/getDeliveryList.do")
    HTTPResponse getDeliveryList(@Body RequestBody json);

    /**
     * 增加提货单打印次数 
     * @autuor Chengw
     * @since 2018/1/31  09:32
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("delivery/addPrintCount.do")
    HTTPResponse addPrintCount(@Body RequestBody json);

    /**
     * PDA获取提货任务订单列表 
     * @autuor Chengw
     * @since 2018/2/1  11:15
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("delivery/getDeliveryOrderListForPDA.do")
    HTTPResponse getDeliveryOrderListForPDA(@Body RequestBody json);

    /**
     * PDA获取提货任务订单详情 
     * @autuor Chengw
     * @since 2018/2/1  10:17
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("delivery/getDeliveryOrderDetail.do")
    HTTPResponse getDeliveryOrderDetail(@Body RequestBody json);


    /**
     * PDA获取提货任务信息 
     * @autuor Chengw
     * @since 2018/2/1  10:17
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("delivery/getDeliveryForPDA.do")
    HTTPResponse getDeliveryForPDA(@Body RequestBody json);
    /**
     * 删除提货订单信息 
     * @autuor wyb
     * @since 2018/3/13 
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("delivery/updateDeliveryEffect.do")
    HTTPResponse updateDeliveryEffect(@Body RequestBody json);

}
