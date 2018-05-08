package com.djcps.wms.workrecords.request;

import com.djcps.wms.commons.config.ParamsConfig;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 工作记录
* @author panyang
* @version 创建时间：2018年4月17日 下午6:50:09
* 类说明
*/
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WorkRecordHttpRequest {

	 /**
     * 根据操作类型获取工作记录
     * @param json
     * @return
     * @author:py
     */
    @Headers("content-type:application/json")
    @POST("workRecords/listWorkRecordsByOperationType.do")
    HTTPResponse getAllRecordListByOperationType(@Body RequestBody json);

    /**
     * 根据操作人id、楞型、和操作类型获取工作记录明细
     * @param json
     * @return
     * @author:py
     */
    @Headers("content-type:application/json")
    @POST("workRecords/listWorkRecordsInfoByParam.do")
    HTTPResponse getWorkRecordsDetail(@Body RequestBody json);
    
    /**
     * 获取提货工作记录记录
     * @param json
     * @return
     * @author:py
     */
    
    @Headers("content-type:application/json")
    @POST("workRecords/listDeliveryWorkRecordsByOperationType.do")
    HTTPResponse getDeliveryRecordList(@Body RequestBody json);
   
    /**
     * 获取提货工作记录记录详情
     * @param json
     * @return
     * @author:py
     */
    
    @Headers("content-type:application/json")
    @POST("workRecords/listDeliveryWorkRecordsInfoByParam.do")
    HTTPResponse  getDeliveryWorkRecordsDetail(@Body RequestBody json);

    /**
     * 获取绩效的总面积数
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("workRecords/listAllAchievements.do")
    HTTPResponse listAllAchievements(@Body RequestBody json);

    /**
     * 获取提货绩效的详情
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("workRecords/listPdaDeliveryAchievementsInfo.do")
    HTTPResponse listPdaDeliveryAchievementsInfo(@Body RequestBody json);

    /**
     * 获取入库绩效的详细信息
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("workRecords/listPdaEntryAchievementsInfo.do")
    HTTPResponse listPdaAchievementsInfo(@Body RequestBody json);

    /**
     * 获取pda端装车工作记录任务列表
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("workRecords/listPdaLoadingTaskWorkRecords.do")
    HTTPResponse listPdaLoadingTaskWorkRecords(@Body RequestBody json);

    /**
     * 获取pda端提货工作记录任务列表
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("workRecords/listPdaDeliveryWorkRecords.do")
    HTTPResponse listPdaDeliveryWorkRecords(@Body RequestBody json);


    /**
     * 获取pda端盘点工作记录任务列表
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("workRecords/listPdaStockTakingWorkRecords.do")
    HTTPResponse listPdaStockTakingWorkRecords(@Body RequestBody json);

    /**
     * 获取pda端装车工作记录详细列表
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("workRecords/listPdaLoadingTaskWorkRecordsInfo.do")
    HTTPResponse listPdaLoadingTaskWorkRecordsInfo(@Body RequestBody json);

    /**
     * 获取pda端提货工作记录详细列表
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("workRecords/listPdaDeliveryWorkRecordsInfo.do")
    HTTPResponse listPdaDeliveryWorkRecordsInfo(@Body RequestBody json);

    /**
     * 获取pda端盘点工作记录详细信息列表
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("workRecords/listPdaStockTakingWorkRecordsInfo.do")
    HTTPResponse listPdaStockTakingWorkRecordsInfo(@Body RequestBody json);

    /**
     * 获取pda端工作记录(入库、退库、移库)
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("workRecords/listPdaWorkRecordsInfo.do")
    HTTPResponse listPdaWorkRecordsInfo(@Body RequestBody json);

    /**
     * 根据操作记录id获取仓库id、仓库名称、操作记录
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("workRecords/getWorkRecordsDetail.do")
    HTTPResponse getBasicDetail(@Body RequestBody json);


}
