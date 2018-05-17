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
     * 获取全盘订单关联信息
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/10 8:47
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/increaseTask.do")
    HTTPResponse getAllStocktakingInfo(@Body RequestBody json);


    /**
     * 获取部分盘点订单关联信息
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/10 8:47
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/increaseTaskByAreaAndLoc.do")
    HTTPResponse getPartStocktakingInfo(@Body RequestBody json);

    /**
     * 更新盘点状态
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/10 8:47
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/updateTaskStatus.do")
    HTTPResponse updateTaskState(@Body RequestBody json);

    /**
     * 获取可用盘点人员列表
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/10 8:47
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/xx.do")
    HTTPResponse getInventoryclerk(@Body RequestBody json);

    /**
     * 保存盘点任务
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/10 8:47
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/saveTask.do")
    HTTPResponse saveSoctakingTask(@Body RequestBody json);

    /**
     * 请求盘点任务信息多条
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/12 9:36
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/orderInfoList.do")
    HTTPResponse stocktakingOrderInfoList(@Body RequestBody json);

    /**
     * 请求盘点任务订单信息一条
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/12 11:05
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/stocktakingOrderInfoByOrderId.do")
    HTTPResponse stocktakingOrderInfoByOrderId(@Body RequestBody json);

    /**
     * 暂存盘点结果
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/12 15:01
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/saveResult.do")
    HTTPResponse saveStocktakingResult(@Body RequestBody json);

    /**
     * 保存盘点结果
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/12 15:02
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/completeTask.do")
    HTTPResponse completeStocktakingTask(@Body RequestBody json);

    /**
     * 获取全部盘点任务列表
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/12 16:18
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/taskList.do")
    HTTPResponse stocktakingTaskList(@Body RequestBody json);

    /**
     * 条件获取盘点任务列表
     *
     * @param json \
     * @return
     * @author wzy
     * @create 2018/1/12 16:20
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/taskList.do")
    HTTPResponse searchTaskList(@Body RequestBody json);

    /**
     * PDA请求盘点任务列表
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/13 13:50
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/pdaTaskList.do")
    HTTPResponse pdaStocktakingTaskList(@Body RequestBody json);

    /**
     * PDA获取盘点任务订单列表
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/13 14:43
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/pdaOrderList.do")
    HTTPResponse pdaStocktakingOrderList(@Body RequestBody json);

    /**
     * PDA盘点订单详情接口
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/13 15:39
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/pdaOrderInfo.do")
    HTTPResponse pdaStocktakingOrderInfo(@Body RequestBody json);

    /**
     * PDA保存盘点结果
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/13 16:12
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/savePdaResult.do")
    HTTPResponse savePdaStocktakingResult(@Body RequestBody json);


    /**
     * PDA完成盘点更新状态
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/13 15:33
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/completePdaTask.do")
    HTTPResponse pdaCompleteStocktaking(@Body RequestBody json);

    /**
     * PDA获取盘点订单各个状态数量
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/13 16:41
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/getOrderAmount.do")
    HTTPResponse getOrderAmount(@Body RequestBody json);

    /**
     * 盘盈时获取相关订单库区库位信息
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/13 19:52
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/warehouseAreaAndLocInfo.do")
    HTTPResponse warehouseAreaAndLocInfo(@Body RequestBody json);

    /**
     * 查看盘点结果列表
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/14 13:22
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/stocktakingResultList.do")
    HTTPResponse stocktakingResultList(@Body RequestBody json);

    /**
     * 保存盘盈录入信息
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/14 22:33
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/saveInventoryProfitInfo.do")
    HTTPResponse saveInventoryProfitInfo(@Body RequestBody json);

    /**
     * 获取操作记录接口
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/17 10:29
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/operationRecordList.do")
    HTTPResponse operationRecordList(@Body RequestBody json);

    /**
     * 查看盘点任务进行情况接口
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/17 10:50
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/stocktakingCompleteStatus.do")
    HTTPResponse stocktakingCompleteStatus(@Body RequestBody json);

    /**
     * web盘盈是获取相关库区库位
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/23 16:53
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/areaAndLocInfo.do")
    HTTPResponse areaAndLocInfo(@Body RequestBody json);

    /**
     * 获取所有库位
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/23 17:42
     **/
    @Headers("content-type:application/json")
    @POST("warehouseLoc/list.do")
    HTTPResponse getLocationAllList(@Body RequestBody json);

    /**
     * 优化新增部分盘点
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/24 14:59
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/increaseTaskByAreaAndLoc.do")
    HTTPResponse test(@Body RequestBody json);

    /**
     * 优化新增全部盘点
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/25 9:28
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/increaseTask.do")
    HTTPResponse increaseTask(@Body RequestBody json);

    /**
     * 更新打印次数接口
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/25 9:30
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/printCount.do")
    HTTPResponse printCount(@Body RequestBody json);

    /**
     * 获取未下发作业订单信息接口
     *
     * @param json
     * @return
     * @author wzy
     * @create 2018/1/25 15:25
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/noSendOrderInfo.do")
    HTTPResponse noSendOrderInfo(@Body RequestBody json);
    
    /**
     * 更新盘点任务状态以及下发时间
     *
     * @param json
     * @return
     * @author wyb
     * @create 2018/5/16
     **/
    @Headers("content-type:application/json")
    @POST("takeStock/updateIssueTimeAndTaskStatus.do")
    HTTPResponse updateIssueTimeAndTaskStatus(@Body RequestBody json);
}
