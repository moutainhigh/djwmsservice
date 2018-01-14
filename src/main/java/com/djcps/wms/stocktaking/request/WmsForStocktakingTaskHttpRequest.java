package com.djcps.wms.stocktaking.request;

import com.djcps.wms.commons.config.ParamsConfig;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * @title:盘点任务http请求
 * @author:wzy
 * @company:djwms
 * @create:2018/1/9
 **/
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsForStocktakingTaskHttpRequest {

    /**
     *获取全盘订单关联信息
     * @author  wzy
     * @param json
     * @return
     * @create  2018/1/10 8:47
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/increaseStoctakingTask.do")
    public HTTPResponse getAllStocktakingInfo(@Body RequestBody json);

    /**
     *获取部分盘点订单关联信息
     * @author  wzy
     * @param json
     * @return
     * @create  2018/1/10 8:47
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/stoctakingByAreaAndLoc.do")
    public HTTPResponse getPartStocktakingInfo(@Body RequestBody json);

    /**
     *更新盘点状态
     * @author  wzy
     * @param json
     * @return
     * @create  2018/1/10 8:47
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/updateTaskStatus.do")
    public HTTPResponse updateTaskState(@Body RequestBody json);

    /**
     *获取可用盘点人员列表
     * @author  wzy
     * @param json
     * @return
     * @create  2018/1/10 8:47
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/xx.do")
    public HTTPResponse getInventoryclerk(@Body RequestBody json);

    /**
     *确认订单
     * @author  wzy
     * @param json
     * @return
     * @create  2018/1/10 8:47
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/savestoctakingTask.do")
    public HTTPResponse saveSoctakingTask(@Body RequestBody json);

    /**
     * 请求盘点任务信息多条
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 9:36
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/stocktakingOrderInfoList.do")
    public HTTPResponse stocktakingOrderInfoList(@Body RequestBody json);

    /**
     * 请求盘点任务订单信息一条
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 11:05
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/stocktakingOrderInfoByOrderId.do")
    public HTTPResponse stocktakingOrderInfoByOrderId(@Body RequestBody json);

    /**
     * 暂存盘点结果
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 15:01
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/saveStocktakingResult.do")
    public HTTPResponse saveStocktakingResult(@Body RequestBody json);

    /**
     * 保存盘点结果
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 15:02
     **/
    @Headers("content-type:application/json")
    @POST("order/completeStocktakingTask.do")
    public HTTPResponse completeStocktakingTask(@Body RequestBody json);

    /**
     * 获取全部盘点任务列表
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/12 16:18
     **/
    @Headers("content-type:application/json")
    @POST("order/stocktakingTaskList.do")
    public HTTPResponse stocktakingTaskList(@Body RequestBody json);

    /**
     * 条件获取盘点任务列表
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/12 16:20
     **/
    @Headers("content-type:application/json")
    @POST("order/searchTaskList.do")
    public HTTPResponse searchTaskList(@Body RequestBody json);

    /**
     * PDA请求盘点任务列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 13:50
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/pdaStocktakingTaskList.do")
    public HTTPResponse pdaStocktakingTaskList(@Body RequestBody json);

    /**
     * PDA获取盘点任务订单列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 14:43
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/pdaStocktakingOrderList.do")
    public HTTPResponse pdaStocktakingOrderList(@Body RequestBody json);

    /**
     * PDA盘点订单详情接口
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/13 15:39
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/pdaStocktakingOrderInfo.do")
    public HTTPResponse pdaStocktakingOrderInfo(@Body RequestBody json);

    /**
     * PDA保存盘点结果
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/13 16:12
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/savePdaStocktakingResult.do")
    public HTTPResponse savePdaStocktakingResult(@Body RequestBody json);


    /**
     * PDA完成盘点更新状态
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 15:33
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/completeStocktaking.do")
    public HTTPResponse pdaCompleteStocktaking(@Body RequestBody json);

    /**
     * PDA获取盘点订单各个状态数量
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/13 16:41
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/getOrderAmount.do")
    public HTTPResponse getOrderAmount(@Body RequestBody json);

    /**
     * 盘盈时获取相关订单库区库位信息
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/13 19:52
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/warehouseAreaAndLocInfo.do")
    public HTTPResponse warehouseAreaAndLocInfo(@Body RequestBody json);
    
    /**
     * 查看盘点结果列表
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/14 13:22
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/stocktakingResultList.do")
    public HTTPResponse stocktakingResultList(@Body RequestBody json);

    /**
     * 保存盘盈录入信息
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/14 22:33
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/saveInventoryProfitInfo.do")
    public HTTPResponse saveInventoryProfitInfo(@Body RequestBody json);
}
