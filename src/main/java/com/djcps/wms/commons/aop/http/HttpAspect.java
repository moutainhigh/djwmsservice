package com.djcps.wms.commons.aop.http;

import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import okhttp3.logging.HttpLoggingInterceptor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import rpc.plugin.http.log.HttpLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Chengw
 * @create 2018/3/26 09:32.
 * @since 1.0.0
 */
@Aspect
@Component
public class HttpAspect {

    private DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(HttpAspect.class);

    private LocalTime startTime;

    @Before("execution(* com.djcps.wms.*.controller.*.*(..))")
    public void before(){
        startTime = LocalTime.now();
    }

    /**
     * AOP执行的方法
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.djcps.wms.*.controller.*.*(..))")
    public Object logPrint(ProceedingJoinPoint pjp) throws Throwable {
        Object proceed = pjp.proceed();
        Object[] params = pjp.getArgs();

        String json = "";
        if(params.length>0){
            json = JSONObject.toJSONString(Arrays.asList(params).stream().filter(
                    a -> !(a instanceof HttpServletRequest || a instanceof HttpServletResponse)
            ).collect(Collectors.toList()));
        }
        LOGGER.debug("==> Http_Preparing: {} " , pjp.getSignature());
        LOGGER.debug("==> Http_Parameters: {} " , json);
        LOGGER.debug("<== Http_Result: - {} - 耗时 {}ms" , proceed,Duration.between(startTime,LocalTime.now()).toMillis());
        return proceed;
    }



}
