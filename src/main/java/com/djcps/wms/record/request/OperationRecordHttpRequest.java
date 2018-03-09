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
     * 获取操作记录
     * @param rb
     * @return
     * @author:zyb
     * @date:2018/3/6
     */
    @Headers("content-type:application/json")
    @POST("operationRecord/entryList.do")
    HTTPResponse entryRecordList(@Body RequestBody rb);
    /**
     * 保存操作记录
     * @param rb
     * @return
     * @author:zyb
     * @date:2018/3/6
     */
    @Headers("content-type:application/json")
    @POST("operationRecord/saveOperationRecord.do")
    HTTPResponse saveOperationRecord(@Body RequestBody json);
    
}
