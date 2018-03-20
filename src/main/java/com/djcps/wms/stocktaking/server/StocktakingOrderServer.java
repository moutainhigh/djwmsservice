package com.djcps.wms.stocktaking.server;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.stocktaking.request.StocktakingOrderHttpRequest;
import com.djcps.wms.stocktaking.request.WmsForStocktakingOrderHttpRequest;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rpc.plugin.http.HTTPResponse;

import java.util.List;
import java.util.Map;

/**
 * @title:盘点订单调用http请求
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/9
 **/
@Component
public class StocktakingOrderServer {
    private static final Logger logger = LoggerFactory.getLogger(StocktakingOrderServer.class);

    private Gson gson = new Gson();

    @Autowired
    private WmsForStocktakingOrderHttpRequest wmsStocktakingOrderHttpRequest;

    @Autowired
   private StocktakingOrderHttpRequest stocktakingOrderHttpRequest;

    /**
     * 批量获取订单详情
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/10 9:29
     **/
    public HttpResult getInfoByChildIds(Map<String,List<String>> map){
        String json = gson.toJson(map);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //HTTPResponse http =wmsStocktakingOrderHttpRequest.getOrderDetailsList(rb) ;
        HTTPResponse http=stocktakingOrderHttpRequest.getOrderDetailsList(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    /**
     *校验
     * @author  wzy
     * @param http
     * @return
     * @create  2018/1/9 10:53
     **/
    private HttpResult verifyHttpResult(HTTPResponse http){
        HttpResult result = null;
        //校验请求是否成功
        if(http.isSuccessful()){
            result = gson.fromJson(http.getBodyString(), HttpResult.class);
        }
        if(result == null){
            System.err.println("Http请求出错,HttpResult结果为null");
            logger.error("Http请求出错,HttpResult结果为null");
        }
        return result;
    }
}
