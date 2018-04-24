package com.djcps.wms.delivery.server;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.delivery.model.*;
import com.djcps.wms.delivery.request.WmsForDeliveryHttpRequest;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rpc.plugin.http.HTTPResponse;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * @author Chengw
 * @since 2018/1/31 08:43.
 */
@Component
public class DeliveryServer {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(DeliveryServer.class);

    @Autowired
    private WmsForDeliveryHttpRequest wmsForDeliveryHttpRequest;

    /**
     * 获取提货单列表
     * @autuor Chengw
     * @since 2018/1/31  09:32
     * @param param
     * @return
     */
    public HttpResult list(ListDeliveryBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForDeliveryHttpRequest.getDeliveryList(requestBody);
        return returnResult(httpResponse);
    }
    /**
     * PDA获取提货任务订单列表
     * @autuor Chengw
     * @since 2018/2/1  11:15
     * @param param
     * @return
     */
    public HttpResult listOrderForPDA(DeliveryOrderBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForDeliveryHttpRequest.getDeliveryOrderListForPDA(requestBody);
        return returnResult(httpResponse);
    }

    /**
     * 获取提货订单列表
     * @autuor Chengw
     * @since 2018/1/31  09:29
     * @param param
     * @return
     */
    public HttpResult listOrder(ListDeliveryOrderBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForDeliveryHttpRequest.getDeliveryOrderList(requestBody);
        return returnResult(httpResponse);
    }

    /**
     * 增加提货单打印次数
     *
     * @param param
     * @return
     * @autuor Chengw
     * @since 2018/2/1  11:26
     */
    public HttpResult addPrintCount(PrintDeliveryBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForDeliveryHttpRequest.addPrintCount(requestBody);
        return returnResult(httpResponse);
    }

    /**
     * 完成提货操作
     *
     * @param param
     * @return
     * @autuor Chengw
     * @since 2018/2/1  11:26
     */
    public HttpResult completeOrder(SaveDeliveryBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForDeliveryHttpRequest.completeTheDeliveryOrder(requestBody);
        return returnResult(httpResponse);
    }

    /**
     * PDA获取提货任务订单详情
     * @param param
     * @return
     * @autuor Chengw
     * @since 2018/2/1  11:26
     */
    public HttpResult getOrderDetail(DeliveryOrderDetailBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForDeliveryHttpRequest.getDeliveryOrderDetail(requestBody);
        return returnResult(httpResponse);
    }

    /**
     * PDA获取提货任务信息
     *
     * @param param
     * @return
     * @autuor Chengw
     * @since 2018/2/1  11:26
     */
    public HttpResult getDeliveryForPDA(DeliveryOrderBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForDeliveryHttpRequest.getDeliveryForPDA(requestBody);
        return returnResult(httpResponse);
    }
    /**
     *  设置提货单的确认状态为未确认
     *
     * @param param
     * @return
     * @autuor wyb
     * @since 2018/3/13
     */
    public HttpResult updateDeliveryEffect(UpdateDeliveryEffectBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForDeliveryHttpRequest.updateDeliveryEffect(requestBody);
        return returnResult(httpResponse);

    }
    /**
     * 公共返回
     *
     * @param httpResponse
     * @return
     */
    private HttpResult returnResult(HTTPResponse httpResponse) {
        if(httpResponse.isSuccessful()) {
            try{
                String body = httpResponse.getBodyString();
                if(StringUtils.isNotBlank(body)){
                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    return baseResult;
                }
            }catch(Exception e){
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }


}
