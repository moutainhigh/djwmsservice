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
     * 获取入库移库操作记录
     * @param rb
     * @return
     * @author:py
     * @date:2018/3/6
     */
    @Headers("content-type:application/json")
    @POST("workRecord/entryList.do")
    HTTPResponse workRecordList(@Body RequestBody rb);
}
