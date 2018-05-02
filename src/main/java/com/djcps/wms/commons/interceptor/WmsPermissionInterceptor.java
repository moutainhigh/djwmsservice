package com.djcps.wms.commons.interceptor;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.config.ParamsConfig;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.CookiesUtil;
import com.djcps.wms.inneruser.model.result.UserInfoVO;
import com.djcps.wms.inneruser.service.InnerUserService;
import com.djcps.wms.permission.service.PermissionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限拦截器
 *
 * @author Chengw
 * @create 2018/4/23 20:38.
 * @since 1.0.0
 */
public class WmsPermissionInterceptor extends HandlerInterceptorAdapter {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(WmsPermissionInterceptor.class);

    @Autowired
    private InnerUserService innerUserService;

    @Autowired
    private PermissionService permissionService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        ContentCachingRequestWrapper requestWrapper =
                new ContentCachingRequestWrapper((HttpServletRequest) request);
        String url = request.getRequestURI();
        LOGGER.info("---访问URL:" + url + ",请求方式:" + request.getMethod());
        String contextPath = request.getContextPath();
        String appLogin = request.getHeader("loginType");
        //删除下文根路径获取RequestMappingUrl
        url = url.substring(contextPath.length());
        int loc = url.indexOf(AppConstant.W);
        if (loc > 0) {
            url = url.substring(0, loc);
        }

        //获取token认证
        String token = CookiesUtil.getCookieByName(request, "token");
        // 用户token 是否过期 或者不存在
        if (!ObjectUtils.isEmpty(token)) {
            UserInfoVO userInfo = innerUserService.getInnerUserInfoFromRedis(token);
            if (userInfo != null) {
                Map<String, String[]> params = getParams(requestWrapper, url);
                Boolean result = isExist(userInfo.getId(), url, params);
                if (!result) {
                    responseMsg(SysMsgEnum.NOT_PERMISSION, response, appLogin);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 获取参数集
     *
     * @param request
     * @return
     */
    private Map<String, String[]> getParams(HttpServletRequest request, String url) {
        Map<String, String[]> params = new HashMap<>(request.getParameterMap());
        String method = request.getMethod();
        if (Method.POST.getValue().equals(method)) {
            /**
             * request steam only is not null once
             * @requestBody use steam when
             */
            params = request.getParameterMap();
        }
        if (Method.GET.getValue().equals(method)) {
            String[] urls = url.split(AppConstant.W);
            int length = 2;
            if (length == urls.length) {
                params = com.djcps.wms.commons.utils.StringUtils.strToMap(urls[1], params);
            }
        }
        return params;
    }


    /**
     * 校验是否拥有权限
     *
     * @param userId
     * @param url
     * @param params
     * @return
     */
    private Boolean isExist(String userId, String url, Map<String, String[]> params) {
        Boolean notInterface = permissionService.notExistSystemPermission(userId, url);
        // 未找到对应的权限项则返回true
        if (notInterface) {
            return true;
        }
        Boolean result = permissionService.checkPermission(userId, url, params);
        return result;
    }

    /**
     * @param msg
     * @param response
     * @title:输出错误信息
     * @description:
     * @author:zdx
     * @date:2017年11月13日
     */
    private void responseMsg(SysMsgEnum msg, HttpServletResponse response, String appLogin) {
        LOGGER.info(msg.getMsg());
        PrintWriter printWriter = null;
        if (!response.isCommitted()) {
            Map<String, Object> result;
            if (!StringUtils.isEmpty(appLogin)) {
                result = (MsgTemplate.failureMsg(msg));
            } else {
                result = (MsgTemplate.failureMsg(msg, ParamsConfig.INNER_USER_LOGIN_URL));
            }

            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writeValueAsString(result);
                if (!response.isCommitted()) {
                    response.setContentType("application/json; charset=utf-8");
                    response.setCharacterEncoding("utf-8");
                    printWriter = response.getWriter();
                    printWriter.print(json);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (printWriter != null) {
                    printWriter.close();
                }
            }
        }
    }

    /**
     * 传参枚举
     */
    enum Method {
        GET("GET"),
        POST("POST");

        private String value;

        public String getValue() {
            return value;
        }

        Method(String value) {
            this.value = value;
        }
    }
} 
