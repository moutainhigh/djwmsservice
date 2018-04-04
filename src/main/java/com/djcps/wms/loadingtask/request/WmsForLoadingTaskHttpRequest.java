package com.djcps.wms.loadingtask.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * @title:装车任务http请求
 * @author:wyb
 * @company:djwms
 * @create:2018/3/19
 **/
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface WmsForLoadingTaskHttpRequest {
    
    /**
     * 装车员列表
     * 
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("loadingtask/getLoadPerson.do")
    HTTPResponse loadingPersonList(@Body RequestBody json);

    /**
     * 获取订单列表和运单信息
     * 
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("loadingtask/getOrderList.do")
    HTTPResponse getOrderList(@Body RequestBody json);
    
    /**
     * 修改装车员状态，批量接口
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("loadingtask/confimLoadPerson.do")
    HTTPResponse updateLoadPersonStatus(@Body RequestBody json);
    /**
     * 移除装车员
     * 
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("loadingtask/updateLoadPerson.do")
    HTTPResponse removeLoadingPerson(@Body RequestBody json);

    /**
     * 装车
     * 
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("loadingtask/loadingAndUpdate.do")
    HTTPResponse loading(@Body RequestBody json);

    /**
     * 完成装车
     * 
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("loadingtask/getWayBillAndOrder.do")
    HTTPResponse finishLoading(@Body RequestBody json);

    /**
     * 追加订单
     * 
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("loadingtask/updateLoadingTask.do")
    HTTPResponse additionalOrder(@Body RequestBody json);
    /**
     * 驳回申请
     * 
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("loadingtask/updateLoadingTask.do")
    HTTPResponse rejectRequest(@Body RequestBody json);
    /**
     * 追加订单申请列表web
     * 
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("loadingtask/getAddSquare.do")
    HTTPResponse addOrderApplicationList(@Body RequestBody json);
    /**
     * 修改运单状态
     * 
     * @param json
     * @return
     */
    @Headers("content-type:application/json")
    @POST("loadingtask/updateWayBill.do")
    HTTPResponse updateWayBill(@Body RequestBody json);
    
    /**
     * 根据用户id获取装车台id
     * @param rb
     * @return
     * @author:zdx
     * @date:2018年4月4日
     */
    @Headers("content-type:application/json")
    @POST("loadingtask/getLoadingTableIdByUserId.do")
	HTTPResponse getLoadingTableIdByUserId(@Body RequestBody rb);
}
