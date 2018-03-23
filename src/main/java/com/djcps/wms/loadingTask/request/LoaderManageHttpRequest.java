package com.djcps.wms.loadingTask.request;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * 查询订单服务
 * @author  wyb
 * @create  2018/3/19
 **/
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface LoaderManageHttpRequest {
        /**
         * 修改装车员
         * @author  wyb
         * @param json
         * @return http
         * @create  2018/3/22
         **/
        @Headers("content-type:application/json")
        @POST("loadingPerson/updateLoadingPerson.do")
        HTTPResponse updataLoader(@Body RequestBody json);
        /**
         * 删除装车员
         * @author  wyb
         * @param json
         * @return http
         * @create  2018/3/22
         **/
        @Headers("content-type:application/json")
        @POST("loadingPerson/deleteLoadingPerson.do")
        HTTPResponse delLoader(@Body RequestBody json);
        /**
         * 新增装车员
         * @author  wyb
         * @param json
         * @return http
         * @create  2018/3/22
         **/
        @Headers("content-type:application/json")
        @POST("loadingPerson/addLoadingPerson.do")
        HTTPResponse saveLoader(@Body RequestBody json);
        /**
         * 获取装车员列表
         * @author  wyb
         * @param json
         * @return http
         * @create  2018/3/22
         **/
        @Headers("content-type:application/json")
        @POST("loadingPerson/getLoadingPersonInfo.do")
        HTTPResponse loaderList(@Body RequestBody json);
}
