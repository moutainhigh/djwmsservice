package com.djcps.wms.record.server;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.order.model.ChildOrderBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.record.model.OrderOperationRecordPO;
import com.djcps.wms.record.model.TaskOperationRecordPO;
import com.djcps.wms.record.model.param.DeleteOrderRecordBO;
import com.djcps.wms.record.model.param.DeleteTaskRecordBO;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.record.model.param.EntryRecordListBO;
import com.djcps.wms.record.model.param.RelativeIdBO;
import com.djcps.wms.record.model.param.SaveOperationRecordBO;
import com.djcps.wms.record.model.param.SelectOrderRecordBO;
import com.djcps.wms.record.model.param.SelectTaskRecordBO;
import com.djcps.wms.record.model.param.StocktakingRecordListBO;
import com.djcps.wms.record.model.result.OperationRecordResult;
import com.djcps.wms.record.request.OperationRecordHttpRequest;
import com.google.gson.Gson;

import rpc.plugin.http.HTTPResponse;

/**
 * @title:调用http服务
 * @description:
 * @author:wyb
 * @company:djwms
 * @create:2018/3/6
 **/
@Component
public class OperationRecordServer {
    private static DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(OperationRecordServer.class);

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
            LOGGER.error("Http请求出错,HttpResult结果为null");
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
     * @title:根据关联id获取操作记录信息
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    public HttpResult getRecordByRrelativeId(RelativeIdBO fromJson) {
        //将请求参数转换为requestbody格式
        String json =gson.toJson(fromJson);
        System.out.println("--http请求参数转化为json格式--:"+json);
        okhttp3.RequestBody rb=okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json;charset=utf-8"), json);
        //调用接口获取信息
        HTTPResponse http = operationRecordHttpRequest.getRecordByRrelativeId(rb);
      //校验请求是否成功
        return verifyHttpResult(http);
        
    }
    /**
     * @title:查询任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    public HttpResult selectTaskRecord(SelectTaskRecordBO fromJson) {
        //将请求参数转换为requestbody格式
        String json =gson.toJson(fromJson);
        System.out.println("--http请求参数转化为json格式--:"+json);
        okhttp3.RequestBody rb=okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json;charset=utf-8"), json);
        //调用接口获取信息
        HTTPResponse http = operationRecordHttpRequest.selectTaskRecord(rb);
      //校验请求是否成功
        return verifyHttpResult(http);
        
    }
    /**
     * @title:保存任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    public HttpResult saveTaskRecord(TaskOperationRecordPO fromJson) {
        //将请求参数转换为requestbody格式
        String json =gson.toJson(fromJson);
        System.out.println("--http请求参数转化为json格式--:"+json);
        okhttp3.RequestBody rb=okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json;charset=utf-8"), json);
        //调用接口获取信息
        HTTPResponse http = operationRecordHttpRequest.saveTaskRecord(rb);
      //校验请求是否成功
        return verifyHttpResult(http);
        
    }
    /**
     * @title:删除任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    public HttpResult deleteTaskRecord(DeleteTaskRecordBO fromJson) {
        //将请求参数转换为requestbody格式
        String json =gson.toJson(fromJson);
        System.out.println("--http请求参数转化为json格式--:"+json);
        okhttp3.RequestBody rb=okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json;charset=utf-8"), json);
        //调用接口获取信息
        HTTPResponse http = operationRecordHttpRequest.deleteTaskRecord(rb);
      //校验请求是否成功
        return verifyHttpResult(http);
        
    }
    /**
     * @title:查询任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    public HttpResult selectOrderRecord(SelectOrderRecordBO fromJson) {
        //将请求参数转换为requestbody格式
        String json =gson.toJson(fromJson);
        System.out.println("--http请求参数转化为json格式--:"+json);
        okhttp3.RequestBody rb=okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json;charset=utf-8"), json);
        //调用接口获取信息
        HTTPResponse http = operationRecordHttpRequest.selectOrderRecord(rb);
      //校验请求是否成功
        return verifyHttpResult(http);
        
    }
    /**
     * @title:保存任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    public HttpResult saveOrderRecord(OrderOperationRecordPO fromJson) {
        //将请求参数转换为requestbody格式
        String json =gson.toJson(fromJson);
        System.out.println("--http请求参数转化为json格式--:"+json);
        okhttp3.RequestBody rb=okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json;charset=utf-8"), json);
        //调用接口获取信息
        HTTPResponse http = operationRecordHttpRequest.saveOrderRecord(rb);
      //校验请求是否成功
        return verifyHttpResult(http);
        
    }
    /**
     * @title:删除任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    public HttpResult deleteOrderRecord(DeleteOrderRecordBO fromJson) {
        //将请求参数转换为requestbody格式
        String json =gson.toJson(fromJson);
        System.out.println("--http请求参数转化为json格式--:"+json);
        okhttp3.RequestBody rb=okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json;charset=utf-8"), json);
        //调用接口获取信息
        HTTPResponse http = operationRecordHttpRequest.deleteOrderRecord(rb);
      //校验请求是否成功
        return verifyHttpResult(http);
        
    }
    /**
     * 纸板面积计算
     * @param length
     * @param wide
     * @param fluteType
     * @param amount
     * @return落料长乘落料宽
     */
    public double getVolume(double length, double wide,int amount){
        BigDecimal lengthBD = new BigDecimal(Double.toString(length));
        BigDecimal wideBD = new BigDecimal(Double.toString(wide));
        BigDecimal amountBD = new BigDecimal(amount);
        return lengthBD.multiply(wideBD).multiply(amountBD).setScale(2, BigDecimal.ROUND_UP).doubleValue();
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
            LOGGER.error("Http请求出错,HttpResult结果为null");
        }
        return result;
    }
}
