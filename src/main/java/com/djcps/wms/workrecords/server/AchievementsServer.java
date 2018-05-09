package com.djcps.wms.workrecords.server;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.workrecords.model.AchievementsBO;
import com.djcps.wms.workrecords.model.AchievementsInfoBO;
import com.djcps.wms.workrecords.request.WorkRecordHttpRequest;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rpc.plugin.http.HTTPResponse;

import static com.djcps.wms.commons.utils.GsonUtils.gson;
import static com.djcps.wms.commons.utils.HttpResultUtils.*;

/**
 * @author Chengw
 * @create 2018/4/24 18:53.
 * @since 1.0.0
 */
@Component
public class AchievementsServer {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(AchievementsServer.class);

    @Autowired
    private WorkRecordHttpRequest workRecordHttpRequest;

    /**
     * 获取提货绩效
     * @param param
     * @return
     */
    public HttpResult listAllAchievements(AchievementsBO param) {
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.listAllAchievements(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 入库绩效信息
     * @param param
     * @return
     */
    public HttpResult listPdaAchievementsInfo(AchievementsInfoBO param) {
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.listPdaAchievementsInfo(rb);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 提货绩效信息
     * @param param
     * @return
     */
    public HttpResult listPdaDeliveryAchievementsInfo(AchievementsInfoBO param) {
        String json = gson.toJson(param);
        RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        //调用借口获取信息
        HTTPResponse http = workRecordHttpRequest.listPdaDeliveryAchievementsInfo(rb);
        //校验请求是否成功
        return returnResult(http);
    }

}
