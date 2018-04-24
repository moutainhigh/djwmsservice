package com.djcps.wms.workrecords.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
* @author panyang
* @version 创建时间：2018年4月17日 下午6:50:09
* 类说明
*/
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WorkRecordHttpRequest {

	
	
	 /**
     * 根据操作类型获取工作记录
     * @param rb
     * @return
     * @author:py
     */
    @Headers("content-type:application/json")
    @POST("workRecords/listWorkRecordsByOperationType.do")
    HTTPResponse getAllRecordListByOperationType(@Body RequestBody rb);
    
    
    /**
     * 根据操作人id、楞型、和操作类型获取工作记录明细
     * @param rb
     * @return
     * @author:py
     */
    @Headers("content-type:application/json")
    @POST("workRecords/listWorkRecordsInfoByParam.do")
    HTTPResponse getWorkRecordsDetail(@Body RequestBody rb);
    
    /**
     * 获取提货工作记录记录
     * @param rb
     * @return
     * @author:py
     */
    
    @Headers("content-type:application/json")
    @POST("workRecords/listDeliveryWorkRecordsByOperationType.do")
    HTTPResponse getDeliveryRecordList(@Body RequestBody rb);
   
    /**
     * 获取提货工作记录记录详情
     * @param rb
     * @return
     * @author:py
     */
    
    @Headers("content-type:application/json")
    @POST("workRecords/listDeliveryWorkRecordsInfoByParam.do")
    HTTPResponse  getDeliveryWorkRecordsDetail(@Body RequestBody rb);
}
