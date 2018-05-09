package com.djcps.wms.commons.utils;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import org.apache.commons.lang3.StringUtils;
import rpc.plugin.http.HTTPResponse;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * HTTP 请求 统一返回类
 * @author Chengw
 * @create 2018/5/9 07:54.
 * @since 1.0.0
 */
public class HttpResultUtils {


    private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(HttpResultUtils.class);

    /**
     * 公共返回
     *
     * @param httpResponse
     * @return
     */
    public static HttpResult returnResult(HTTPResponse httpResponse) {
        if (httpResponse.isSuccessful()) {
            try {
                String body = httpResponse.getBodyString();
                if (StringUtils.isNotBlank(body)) {
                    HttpResult result = gson.fromJson(body, HttpResult.class);
                    return result;
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }
}
