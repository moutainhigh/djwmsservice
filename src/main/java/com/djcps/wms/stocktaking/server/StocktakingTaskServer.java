package com.djcps.wms.stocktaking.server;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.loadingtable.model.GetNumberBO;
import com.djcps.wms.loadingtable.request.NumberServerHttpRequest;
import com.djcps.wms.stocktaking.model.*;
import com.djcps.wms.stocktaking.request.WmsForStocktakingTaskHttpRequest;
import com.djcps.wms.warehouse.server.AreaServer;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rpc.plugin.http.HTTPResponse;

import java.util.List;

/**
 * @title:盘点任务调用http服务
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/9
 **/
@Component
public class StocktakingTaskServer {
    private static final Logger logger = LoggerFactory.getLogger(AreaServer.class);

    private Gson gson = new Gson();

    @Autowired
    private NumberServerHttpRequest numberServerHttp;

    @Autowired
    private WmsForStocktakingTaskHttpRequest stocktakingTaskHttpRequest;

    public HttpResult getAllStocktakingInfo(AddStocktakingBO addStocktakingBO){
        String json = gson.toJson(addStocktakingBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.getAllStocktakingInfo(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    public HttpResult getPartStocktakingInfo(AddStocktakingBO addStocktakingBO){
        String json = gson.toJson(addStocktakingBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.getPartStocktakingInfo(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    public HttpResult updateTaskState(UpdateStocktakingTaskBO updateStocktakingTaskBO){
        String json = gson.toJson(updateStocktakingTaskBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.updateTaskState(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    public HttpResult getInventoryclerk(){
        String json = "";
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.getInventoryclerk(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    /**
     * @title 获取一个作业单号随机编号
     * @author  wzy
     * @create  2017/12/21 10:50
     **/
    public HttpResult getNumber(){
        GetNumberBO getNumberBO=new GetNumberBO();
        getNumberBO.setCount(1);
        String json=gson.toJson(getNumberBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = numberServerHttp.getnumber(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    public HttpResult saveSoctakingTask(SaveStocktakingTaskBO saveStocktakingTaskBO){
        String json = gson.toJson(saveStocktakingTaskBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.saveSoctakingTask(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    public HttpResult stocktakingOrderInfoList(String jobId){
        String json = gson.toJson(jobId);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.stocktakingOrderInfoList(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    public HttpResult stocktakingOrderInfoByOrderId(StocktakingTaskBO stocktakingTaskBO){
        String json = gson.toJson(stocktakingTaskBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.stocktakingOrderInfoByOrderId(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    public HttpResult saveStocktakingResult(List<SaveStocktakingOrderInfoBO> saveStocktakingOrderInfoBOList){
        String json = gson.toJson(saveStocktakingOrderInfoBOList);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.saveStocktakingResult(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    public HttpResult completeStocktakingTask(List<SaveStocktakingOrderInfoBO> saveStocktakingOrderInfoBOList){
        String json = gson.toJson(saveStocktakingOrderInfoBOList);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.completeStocktakingTask(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    public HttpResult stocktakingTaskList(GetStocktakingTaskBO getStocktakingTaskBO){
        String json = gson.toJson(getStocktakingTaskBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.stocktakingTaskList(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    public HttpResult searchTaskList(GetStocktakingTaskBO getStocktakingTaskBO){
        String json = gson.toJson(getStocktakingTaskBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.searchTaskList(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    /**
     * PDA请求盘点任务列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 13:51
     **/
    public HttpResult pdaStocktakingTaskList(PdaStocktakingTaskBO pdaStocktakingTaskBO){
        String json = gson.toJson(pdaStocktakingTaskBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.pdaStocktakingTaskList(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    /**
     * PDA请求盘点任务订单列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 14:46
     **/
    public HttpResult pdaStocktakingOrderList(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO){
        String json = gson.toJson(pdaGetStocktakingOrderBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.pdaStocktakingOrderList(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    /**
     * PDA获取订单详情
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 15:48
     **/
    public HttpResult pdaStocktakingOrderInfo(PdaStocktakingOrderInfo pdaStocktakingOrderInfo){
        String json = gson.toJson(pdaStocktakingOrderInfo);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.pdaStocktakingOrderInfo(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    /**
     * PDA保存盘点结果
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 16:16
     **/
    public HttpResult savePdaStocktakingResult(SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO){
        String json = gson.toJson(saveStocktakingOrderInfoBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.savePdaStocktakingResult(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    /**
     * 获取盘点订单各个状态数量
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/13 16:42
     **/
    public HttpResult getOrderAmount(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO){
        String json = gson.toJson(pdaGetStocktakingOrderBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.getOrderAmount(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }


    /**
     * PDA完成盘点更新状态
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 15:34
     **/
    public HttpResult pdaCompleteStocktaking(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO){
        String json = gson.toJson(pdaGetStocktakingOrderBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.pdaCompleteStocktaking(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    /**
     * 盘盈时获取相关订单库区库位信息
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/13 19:55
     **/
    public HttpResult pdaWarehouseAreaAndLocInfo(StocktakingTaskBO stocktakingTaskBO){
        String json = gson.toJson(stocktakingTaskBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.warehouseAreaAndLocInfo(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    /**
     * 查看盘点结果列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/14 13:37
     **/
    public HttpResult stocktakingResultList(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO){
        String json = gson.toJson(pdaGetStocktakingOrderBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.stocktakingResultList(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    /**
     * 保存盘盈录入信息
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/14 22:34
     **/
    public HttpResult saveInventoryProfitInfo(SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO){
        String json = gson.toJson(saveStocktakingOrderInfoBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =stocktakingTaskHttpRequest.saveInventoryProfitInfo(rb) ;
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    /**
     *验证
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
