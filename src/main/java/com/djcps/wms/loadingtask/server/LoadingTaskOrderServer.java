package com.djcps.wms.loadingtask.server;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.loadingtask.model.result.OrderIdAndLoadingAmountPO;
import com.djcps.wms.loadingtask.model.result.OrderInfoPO;
import com.djcps.wms.loadingtask.request.LoadingTaskOrderHttpRequest;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderIdsBO;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rpc.plugin.http.HTTPResponse;

/**
 * @author wyb
 * @since 2018/3/19
 */
@Component
public class LoadingTaskOrderServer {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(LoadingTaskServer.class);

    @Autowired
    private LoadingTaskOrderHttpRequest loadingTaskOrderHttpRequest;

    /**
     * 批量获取订单详情
     * 
     * @author WYB
     * @param
     * @return
     * @create 2018/3/20
     **/
    public HttpResult getInfoByOrderIds(OrderIdsBO childOrderIds) {
        String json = gson.toJson(childOrderIds);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
        HTTPResponse httpResponse = loadingTaskOrderHttpRequest.getOrderDetailsList(requestBody);
        return returnResult(httpResponse);
    }
    /**
     * 批量获取订单详细信息
     * @param param
     * @return
     */
    public List<OrderInfoPO> getChildOrderList(OrderIdsBO childOrderIds){
        List<OrderInfoPO> orderInfoPOList = new ArrayList<>();
        HttpResult httpResult = getInfoByOrderIds(childOrderIds);
        if(httpResult.isSuccess()){
            String data = gson.toJson(httpResult.getData());
            orderInfoPOList = JSONArray.parseArray(data,OrderInfoPO.class);
        }
        return  orderInfoPOList;
    }
    /**
     * 修改订单状态
     * @param param
     * @return
     */
    public HttpResult updateOrderStatus(OrderIdBO param){
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = loadingTaskOrderHttpRequest.updateOrderStatus(rb);
        //校验请求是否成功
        return returnResult(http);
    } 
    /**
     * 公共返回
     *
     * @param httpResponse
     * @return
     */
    private HttpResult returnResult(HTTPResponse httpResponse) {
        if (httpResponse.isSuccessful()) {
            try {
                String body = httpResponse.getBodyString();
                if (StringUtils.isNotBlank(body)) {
                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    return baseResult;
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }
}
