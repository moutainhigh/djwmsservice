package com.djcps.wms.record.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * @title:操作记录http请求
 * @author:wyb
 * @company:djwms
 * @create:2018/3/6
 **/
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface OperationRecordHttpRequest {
    /**
     * 获取盘点操作记录接口
     *
     * @param json
     * @return
     * @author wyb
     * @create 2018/3/6
     **/
    @Headers("content-type:application/json")
    @POST("operationRecord/stocktakingList.do")
    HTTPResponse stocktakingRecordList(@Body RequestBody json);
    /**
     * 获取入库移库操作记录
     * @param rb
     * @return
     * @author:wyb
     * @date:2018/3/6
     */
    @Headers("content-type:application/json")
    @POST("operationRecord/entryList.do")
    HTTPResponse entryRecordList(@Body RequestBody rb);
    /**
     * 保存操作记录
     * @param json
     * @return
     * @author:wyb
     * @date:2018/3/6
     */
    @Headers("content-type:application/json")
    @POST("operationRecord/saveOperationRecord.do")
    HTTPResponse saveOperationRecord(@Body RequestBody json);
    /**
     * 根据关联id获取操作记录信息
     * @param json
     * @return
     * @author:wyb
     * @date:2018/5/3
     */
    @Headers("content-type:application/json")
    @POST("operationRecord/getRecordByRrelativeId.do")
    HTTPResponse getRecordByRrelativeId(@Body RequestBody json);
    /**
     * 查询任务 操作记录
     * @param json
     * @return
     * @author:wyb
     * @date:2018/5/3
     */
    @Headers("content-type:application/json")
    @POST("operationRecord/selectTaskRecord.do")
    HTTPResponse selectTaskRecord(@Body RequestBody json);
    /**
     * 保存任务 操作记录
     * @param json
     * @return
     * @author:wyb
     * @date:2018/5/3
     */
    @Headers("content-type:application/json")
    @POST("operationRecord/saveTaskRecord.do")
    HTTPResponse saveTaskRecord(@Body RequestBody json);
    /**
     * 删除任务 操作记录
     * @param json
     * @return
     * @author:wyb
     * @date:2018/5/3
     */
    @Headers("content-type:application/json")
    @POST("operationRecord/deleteTaskRecord.do")
    HTTPResponse deleteTaskRecord(@Body RequestBody json);
    /**
     * 查询任务 操作记录
     * @param json
     * @return
     * @author:wyb
     * @date:2018/5/3
     */
    @Headers("content-type:application/json")
    @POST("operationRecord/selectOrderRecord.do")
    HTTPResponse selectOrderRecord(@Body RequestBody json);
    /**
     * 保存任务 操作记录
     * @param json
     * @return
     * @author:wyb
     * @date:2018/5/3
     */
    @Headers("content-type:application/json")
    @POST("operationRecord/saveOrderRecord.do")
    HTTPResponse saveOrderRecord(@Body RequestBody json);
    /**
     * 删除任务 操作记录
     * @param json
     * @return
     * @author:wyb
     * @date:2018/5/3
     */
    @Headers("content-type:application/json")
    @POST("operationRecord/deleteOrderRecord.do")
    HTTPResponse deleteOrderRecord(@Body RequestBody json);
}
