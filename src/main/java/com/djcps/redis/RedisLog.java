package com.djcps.redis;

import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 
 * @ClassName: LogAspect
 * @Description: TODO
 * @author 叶千阁
 * @date 2017年12月22日 上午10:58:31
 */
@Aspect
@Component
public class RedisLog {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(RedisLog.class);

    /**
     * AOP执行的方法
     * 
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.djcps.redis.*.*(..))")
    public Object logPrint(ProceedingJoinPoint pjp) throws Throwable {
        Object proceed = pjp.proceed();
        Object[] params = pjp.getArgs();
        String json = JSONObject.toJSONString(Arrays.asList(params));
        LOGGER.debug("==> Redis_Preparing: " + pjp.getSignature());
        LOGGER.debug("==> Redis_Parameters: " + json);
        LOGGER.debug("<== Redis_Result: " + proceed);
        return proceed;
    }

}
