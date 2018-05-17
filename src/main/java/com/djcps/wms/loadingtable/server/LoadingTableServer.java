package com.djcps.wms.loadingtable.server;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.loadingtable.model.*;
import com.djcps.wms.loadingtable.request.NumberServerHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.base.BaseListPartnerIdBO;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.loadingtable.request.WmsForLoadingTableHttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rpc.plugin.http.HTTPResponse;

import java.util.List;
import java.util.Map;

import static com.djcps.wms.commons.utils.HttpResultUtils.*;

/**
 * @title:装车台服务
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月23日
 */
@Component
public class LoadingTableServer {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(LoadingTableServer.class);

    private Gson gson = GsonUtils.gson;

    @Autowired
    private WmsForLoadingTableHttpRequest loadingTableHttpRequest;

    @Autowired
    private NumberServerHttpRequest numberServerHttp;


    public HttpResult add(AddLoadingTableBO loadingTable) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(loadingTable);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = loadingTableHttpRequest.add(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    public HttpResult modify(UpdateLoadingTableBO loadingTable) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(loadingTable);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = loadingTableHttpRequest.modify(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    public HttpResult delete(DeleteLoadingTableBO loadingTable) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(loadingTable);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = loadingTableHttpRequest.delete(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    public HttpResult getAllList(BaseListPartnerIdBO baseListParam) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(baseListParam);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = loadingTableHttpRequest.getAllList(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    public HttpResult getLoadingTableByAttribute(SelectLoadingTableByAttributeBO loadingTable) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(loadingTable);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = loadingTableHttpRequest.getLoadingTableByAttribute(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    public HttpResult getLoadingTableById(SelectLoadingTableByIdBO loadingTable) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(loadingTable);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = loadingTableHttpRequest.getLoadingTableById(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    public HttpResult enable(IsUseLoadingTableBO loadingTable) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(loadingTable);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = loadingTableHttpRequest.enable(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    public HttpResult disable(IsUseLoadingTableBO loadingTable) {
        //将请求参数转化为requestbody格式
        String json = gson.toJson(loadingTable);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = loadingTableHttpRequest.disable(rb);
        //校验请求是否成功
        return returnResult(http);
    }

	public HttpResult deleteBindingUserId(UpdateLoadingTableBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = loadingTableHttpRequest.deleteBindingUserId(rb);
        //校验请求是否成功
        return returnResult(http);
	}
    
    /**
     * @title 获取随机编号
     * @author wzy
     * @create 2017/12/21 10:50
     **/
    public HttpResult getNumber(int count) {
        GetNumberBO getNumberBO = new GetNumberBO();
        getNumberBO.setCount(count);
        String json = gson.toJson(getNumberBO);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = numberServerHttp.getnumber(rb);
        //校验请求是否成功
        HttpResult result = returnResult(http);
        //更换data为字符串"ZTC+numbers"
        String backjson = gson.toJson(result.getData());
        System.out.println("");
        Map<String, List<String>> map = gson.fromJson(backjson, Map.class);
        String number = "";
        //返回的编号键名
        String key = "numbers";
        if ((map.get(key)).size() > 0) {
            number = AppConstant.ZCT + (map.get("numbers")).get(0);
        }
        result.setData(number);
        return result;
    }

}
