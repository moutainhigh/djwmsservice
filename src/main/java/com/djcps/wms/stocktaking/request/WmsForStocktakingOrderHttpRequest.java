package com.djcps.wms.stocktaking.request;

import com.djcps.wms.commons.config.ParamsConfig;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * @title:盘点订单http请求
 * @author:wzy
 * @company:djwms
 * @create:2018/1/9
 **/
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsForStocktakingOrderHttpRequest {
    /**
     * 获取订单库位信息接口
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/18 17:58
     **/
    @Headers("content-type:application/json")
    @POST("entry/orderWarehouseLocInfo.do")
    HTTPResponse orderWarehouseLocInfo(@Body RequestBody json);

    /**
     * 获取库区库位订单的库存数量
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/19 10:51
     **/
    @Headers("content-type:application/json")
    @POST("entry/getAmount.do")
    HTTPResponse getAmount(@Body RequestBody json);

}
