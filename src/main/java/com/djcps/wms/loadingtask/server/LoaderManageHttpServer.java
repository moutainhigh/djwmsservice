package com.djcps.wms.loadingtask.server;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.loadingtask.model.DelLoaderBO;
import com.djcps.wms.loadingtask.model.GetLoadingPersonInfoBO;
import com.djcps.wms.loadingtask.model.SaveLoaderBO;
import com.djcps.wms.loadingtask.model.UpdataLoaderBO;
import com.djcps.wms.loadingtask.request.LoaderManageHttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rpc.plugin.http.HTTPResponse;

import static com.djcps.wms.commons.utils.HttpResultUtils.*;

/**
 * @author  wyb
 * @since 2018/3/19
 */
@Component
public class LoaderManageHttpServer {
    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(LoaderManageHttpServer.class);
    @Autowired
    private LoaderManageHttpRequest loaderManageHttpRequest;
    
    private Gson gson = GsonUtils.gson;
    /**
     * 修改装车员
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/20
     **/
    public HttpResult updataLoader(UpdataLoaderBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = loaderManageHttpRequest.updataLoader(requestBody);
        return returnResult(httpResponse);

    }

    /**
     * 删除装车员
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/20
     **/
    public HttpResult delLoader(DelLoaderBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = loaderManageHttpRequest.delLoader(requestBody);
        return returnResult(httpResponse);

    }

    /**
     * 新增装车员
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/20
     **/
    public HttpResult saveLoader(SaveLoaderBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = loaderManageHttpRequest.saveLoader(requestBody);
        return returnResult(httpResponse);

    }

    /**
     * 获取装车员列表
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/20
     **/
    public HttpResult loadingPersonList(GetLoadingPersonInfoBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = loaderManageHttpRequest.loaderList(requestBody);
        return returnResult(httpResponse);

    }

}
