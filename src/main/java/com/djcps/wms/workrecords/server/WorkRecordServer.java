package com.djcps.wms.workrecords.server;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.workrecords.model.WorkRecordsDetailBO;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.workrecords.model.WorkRecordsBO;
import com.djcps.wms.workrecords.request.WorkRecordHttpRequest;

import rpc.plugin.http.HTTPResponse;
import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * @author panyang
 * @version 创建时间：2018年4月17日 下午6:50:09
 * 类说明
 */
@Component
public class WorkRecordServer {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(WorkRecordServer.class);

    @Autowired
    private WorkRecordHttpRequest workRecordHttpRequest;

    /**
     * 获取工作记录信息
     *
     * @param param
     * @return
     */

    public HttpResult getAllRecordListByOperationType(WorkRecordsBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.getAllRecordListByOperationType(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    public HttpResult getWorkRecordsDetail(WorkRecordsDetailBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.getWorkRecordsDetail(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }


    public HttpResult listDeliveryRecord(WorkRecordsBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.getDeliveryRecordList(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }

    public HttpResult getDeliveryWorkRecordsDetail(WorkRecordsDetailBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.getDeliveryWorkRecordsDetail(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }


    private HttpResult verifyHttpResult(HTTPResponse http) {
        HttpResult result = null;
        //校验请求是否成功
        if (http.isSuccessful()) {
            result = gson.fromJson(http.getBodyString(), HttpResult.class);
        }
        if (ObjectUtils.isEmpty(result)) {
            LOGGER.error("Http请求出错,HttpResult结果为null");
        }
        return result;
    }


}
