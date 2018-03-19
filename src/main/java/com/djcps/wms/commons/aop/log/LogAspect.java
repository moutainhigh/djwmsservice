package com.djcps.wms.commons.aop.log;

import com.alibaba.fastjson.JSON;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.config.ParamsConfig;
import com.djcps.wms.inneruser.model.result.UserInfoVO;
import com.djcps.wms.inneruser.service.InnerUserService;
import com.google.gson.Gson;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author
 */
@Aspect
@Component
public class LogAspect {

    private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private InnerUserService innerUserService;

    /**
     *  AOP执行的方法
     */
    @Around("@annotation(addLog)")
    public Object validIdentityAndSecure(ProceedingJoinPoint pjp, AddLog addLog) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        LOGGER.info("addLog 执行了");
        long end = System.currentTimeMillis();
        LOGGER.info("方法运行时间：" + (end - start));
        try {
            getParameter(pjp, addLog,proceed);
        }catch (Exception e){
            e.printStackTrace();
        }
        return proceed;
    }

    public HttpServletRequest getRequest(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        HttpServletRequest request = null;
        if (args != null) {
            for (Object object : args) {
                if (object instanceof HttpServletRequest) {
                    request = (HttpServletRequest) object;
                    return request;
                }
            }
        }
        try {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            request = sra.getRequest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return request;
    }

    public void getParameter(ProceedingJoinPoint pjp, AddLog addLog,Object proceed) throws InterruptedException, IOException {
        HttpServletRequest request = getRequest(pjp);
        Map<String, String> linkMap = new LinkedHashMap<>(15);
        Gson gson = new Gson();
        if (request != null) {
            // ip
            String ip = request.getHeader("X-real-IP");
            linkMap.put("ip", ip);
            // 参数
            Map<String, String[]> requestParam = request.getParameterMap();
            String jsonString = JSON.toJSONString(requestParam);
            linkMap.put("requestParam", jsonString);
            Object[] args = pjp.getArgs();
            if (ArrayUtils.isNotEmpty(args)) {
                for (Object object : args) {
                    if (object instanceof String) {
                        linkMap.put("requestBody", (String) object);
                        break;
                    }
                }
            }
            if (!(proceed instanceof String)) {
                linkMap.put("responseBody", gson.toJson(proceed));
            }
            String token = getToken(request);
            if (StringUtils.isNotEmpty(token)) {
                UserInfoVO userInfoVO = innerUserService.getInnerUserInfoFromRedis(token);
                if (userInfoVO !=null) {
                    linkMap.put("userid", String.valueOf(userInfoVO.getId()));
                    linkMap.put("username", userInfoVO.getUname());
                }
            }
            String os = request.getHeader("User-Agent");
            linkMap.put("OS", os);
        }
        // 操作时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        linkMap.put("operatingTime", simpleDateFormat.format(date));
        // 最小功能
        String signature = pjp.getSignature().toString();
        linkMap.put("signature", signature);
        // 操作内容
        String value = addLog.value();
        linkMap.put("operatingValue", value);
        // 系统模块
        String module = addLog.module();
        linkMap.put("systemModule", module);
        LOGGER.info(JSON.toJSONString(linkMap));
    }

    /**
     * 获取cookie里的token
     */
    public String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (ParamsConfig.INNER_USER_COOKIE_NAME.equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }

}
