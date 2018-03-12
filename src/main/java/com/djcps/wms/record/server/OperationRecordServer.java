package com.djcps.wms.record.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.record.model.EntryRecordListBO;
import com.djcps.wms.record.model.SaveOperationRecordBO;
import com.djcps.wms.record.model.StocktakingRecordListBO;
import com.djcps.wms.record.model.OperationRecordResult.OperationRecordResult;
import com.djcps.wms.record.request.OperationRecordHttpRequest;
import com.google.gson.Gson;

import rpc.plugin.http.HTTPResponse;

/**
 * @title:盘点任务调用http服务
 * @description:
 * @author:wyb
 * @company:djwms
 * @create:2018/3/6
 **/
@Component
public class OperationRecordServer {
    private static final Logger logger = LoggerFactory.getLogger(OperationRecordServer.class);

    private Gson gson = new Gson();
    @Autowired
    private OperationRecordHttpRequest operationRecordHttpRequest;
    /**
     * 获取盘点操作记录
     * @author  wyb
     * @param
     * @return 
     * @create  2018/3/6
     **/
    public OperationRecordResult stocktakingRecordList(StocktakingRecordListBO stocktakingRecordListBO){
        String json = gson.toJson(stocktakingRecordListBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =operationRecordHttpRequest.stocktakingRecordList(rb) ;
        //校验请求是否成功
        OperationRecordResult result = null;
        if(http.isSuccessful()){
            result = gson.fromJson(http.getBodyString(), OperationRecordResult.class);
        }
        if(result == null){
            System.err.println("Http请求出错,HttpResult结果为null");
            logger.error("Http请求出错,HttpResult结果为null");
        }
        return result;
    }
    /**
     * 获取入库移库操作记录
     * @author  wyb
     * @param
     * @return 
     * @create  2018/3/6
     **/
    public HttpResult getEntryRecordList(EntryRecordListBO fromJson) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(fromJson);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = operationRecordHttpRequest.entryRecordList(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }
    /**
     * @title:保存操作记录
     * @description:
     * @param http
     * @return
     * @author:wyb
     * @date: 2018/3/6
     */
    public HttpResult saveOperationRecord(SaveOperationRecordBO fromJson) {
        //将请求参数转换为requestbody格式
        String json =gson.toJson(fromJson);
        System.out.println("--http请求参数转化为json格式--:"+json);
        okhttp3.RequestBody rb=okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json;charset=utf-8"), json);
        //调用接口获取信息
        HTTPResponse http = operationRecordHttpRequest.saveOperationRecord(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }
    /**
     * @title:校验HTTPResponse结果是否成功
     * @description:
     * @param http
     * @return
     * @author:wyb
     * @date: 2018/3/6
     */
    private HttpResult verifyHttpResult(HTTPResponse http){
        HttpResult result = null;
        //校验请求是否成功
        if(http.isSuccessful()){
            result = gson.fromJson(http.getBodyString(), HttpResult.class);
        }
        if(ObjectUtils.isEmpty(result)){
            System.err.println("Http请求出错,HttpResult结果为null");
            logger.error("Http请求出错,HttpResult结果为null");
        }
        return result;
    }
}
