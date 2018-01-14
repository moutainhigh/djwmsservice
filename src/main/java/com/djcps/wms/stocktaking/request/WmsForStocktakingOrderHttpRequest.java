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


}
