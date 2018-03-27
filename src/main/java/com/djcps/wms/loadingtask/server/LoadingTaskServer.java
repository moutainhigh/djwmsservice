package com.djcps.wms.loadingtask.server;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.loadingtask.model.AddOrderApplicationListBO;
import com.djcps.wms.loadingtask.model.AdditionalOrderBO;
import com.djcps.wms.loadingtask.model.ConfirmBO;
import com.djcps.wms.loadingtask.model.FinishLoadingBO;
import com.djcps.wms.loadingtask.model.LoadingBO;
import com.djcps.wms.loadingtask.model.LoadingPersonBO;
import com.djcps.wms.loadingtask.model.RejectRequestBO;
import com.djcps.wms.loadingtask.model.RemoveLoadingPersonBO;
import com.djcps.wms.loadingtask.model.result.FinishLoadingPO;
import com.djcps.wms.loadingtask.request.WmsForLoadingTaskHttpRequest;
import com.djcps.wms.stocktaking.model.orderresult.OrderResult;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rpc.plugin.http.HTTPResponse;
import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * @author wyb
 * @since 2018/3/19
 */
@Component
public class LoadingTaskServer {
    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(LoadingTaskServer.class);
    @Autowired
    private WmsForLoadingTaskHttpRequest wmsForLoadingTaskHttpRequest;

    /**
     * 获取装车员列表
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/20
     **/
    public HttpResult loadingPersonList(LoadingPersonBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.loadingPersonList(requestBody);
        return returnResult(httpResponse);

    }

    /**
     * 移除装车员
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/20
     **/
    public HttpResult removeLoadingPerson(RemoveLoadingPersonBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.removeLoadingPerson(requestBody);
        return returnResult(httpResponse);
    }

    /**
     * (装车员界面确认)获取订单列表和运单信息
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/20
     */
    public HttpResult getOrderList(ConfirmBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.getOrderList(requestBody);
        return returnResult(httpResponse);

    }
    /**
     * (装车员界面确认)修改装车员状态，批量接口
     * @param param
     * @return
     */
    public HttpResult updateLoadPersonStatus(ConfirmBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.updateLoadPersonStatus(requestBody);
        return returnResult(httpResponse);
    }
    /**
     * 装车
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/21
     */
    public HttpResult loading(LoadingBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.loading(requestBody);
        return returnResult(httpResponse);
    }

    /**
     * 追加订单
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/21
     */
    public HttpResult additionalOrder(AdditionalOrderBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.additionalOrder(requestBody);
        return returnResult(httpResponse);
    }
    /**
     * 驳回申请
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/21
     */
    public HttpResult rejectRequest(RejectRequestBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.rejectRequest(requestBody);
        return returnResult(httpResponse);
    }
    /**
     * 追加订单申请列表web
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/21
     */
    public HttpResult addOrderApplicationList(AddOrderApplicationListBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.addOrderApplicationList(requestBody);
        return returnResult(httpResponse);
    }
    /**
     * 修改运单状态
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/21
     */
    public HttpResult updateWayBill(FinishLoadingBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.updateWayBill(requestBody);
        return returnResult(httpResponse);
    }
    /**
     * 完成装车
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/21
     */
    public FinishLoadingPO finishLoading(FinishLoadingBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.finishLoading(requestBody);
        FinishLoadingPO result = null;
        if(httpResponse.isSuccessful()){
            result = gson.fromJson(httpResponse.getBodyString(), FinishLoadingPO.class);
        }
        if(result == null){
            LOGGER.error("Http请求出错,HttpResult结果为null");
        }
        return result;
    }
    /**
     * 公共返回
     *
     * @param httpResponse
     * @return
     */
    private HttpResult returnResult(HTTPResponse httpResponse) {
        if (httpResponse.isSuccessful()) {
            try {
                String body = httpResponse.getBodyString();
                if (StringUtils.isNotBlank(body)) {
                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    return baseResult;
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }
}
