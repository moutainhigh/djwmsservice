package com.djcps.wms.workrecords.server;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.workrecords.model.*;
import com.djcps.wms.workrecords.request.WorkRecordHttpRequest;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rpc.plugin.http.HTTPResponse;

import static com.djcps.wms.commons.utils.GsonUtils.gson;
import static com.djcps.wms.commons.utils.HttpResultUtils.*;

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
        return returnResult(http);
    }

    /**
     * 获取工作记录详细信息
     * @param param
     * @return
     */
    public HttpResult getWorkRecordsDetail(WorkRecordsDetailBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.getWorkRecordsDetail(rb);
        //校验请求是否成功
        return returnResult(http);
    }


    /**
     * 获取提货工作记录信息
     * @param param
     * @return
     */
    public HttpResult listDeliveryRecord(WorkRecordsBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.getDeliveryRecordList(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 获取提货工作记录详细信息
     * @param param
     * @return
     */
    public HttpResult getDeliveryWorkRecordsDetail(WorkRecordsDetailBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.getDeliveryWorkRecordsDetail(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 获取pda工作记录
     * @param param
     * @return
     */
    public HttpResult listPdaInfo(WorkRecordsListBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.listPdaWorkRecordsInfo(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 获取pda 装车的工作记录
     * @param param
     * @return
     */
    public HttpResult listPdaLoadingTask(WorkRecordsListBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.listPdaLoadingTaskWorkRecords(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 获取pda 提货的工作记录
     * @param param
     * @return
     */
    public HttpResult listPdaDelivery(WorkRecordsListBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.listPdaDeliveryWorkRecords(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 获取pda 盘点的工作记录
     * @param param
     * @return
     */
    public HttpResult listPdaStockTaking(WorkRecordsListBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.listPdaStockTakingWorkRecords(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 获取pda 运单明细
     * @param param
     * @return
     */
    public HttpResult listPdaLoadingTaskInfo(WorkRecordsTaskBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.listPdaLoadingTaskWorkRecordsInfo(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     *  获取pda 提货单明细
     * @param param
     * @return
     */
    public HttpResult listPdaDeliveryInfo(WorkRecordsTaskBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.listPdaDeliveryWorkRecordsInfo(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 根据操作记录id获取仓库id、仓库名称、操作记录
     * @param param
     * @return
     */
    public HttpResult getBasicDetail(WorkRecordsOrderBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.getBasicDetail(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 获取pda 盘点单明细
     * @param param
     * @return
     */
    public HttpResult listPdaStockTakingInfo(WorkRecordsTaskBO param) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.listPdaStockTakingWorkRecordsInfo(rb);
        //校验请求是否成功
        return returnResult(http);
    }

}
