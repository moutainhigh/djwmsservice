package com.djcps.wms.commons.aop.inneruser.aspect;

import com.djcps.wms.commons.aop.inneruser.annotation.OperateRecordMq;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by xzzx on 2018/5/22.
 */
@Aspect
@Component
public class RecordAspect {
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * AOP执行的方法 获取注解形参的值
     */
    @Around("@annotation(operateRecordMq)")
    public void getRecord(ProceedingJoinPoint pjp, OperateRecordMq operateRecordMq) throws Throwable {
        String[] values = getStrings(pjp);
        Gson gson = new Gson();
        String jsonDate = gson.toJson(values);
        amqpTemplate.convertAndSend("record", jsonDate);
    }


    private String[] getStrings(ProceedingJoinPoint pjp) {
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String[] values = null;
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                boolean methodHasAnnotation = m.isAnnotationPresent(OperateRecordMq.class);
                if (methodHasAnnotation) {
                    OperateRecordMq methodAnnotation = m.getAnnotation(OperateRecordMq.class);
                    values = methodAnnotation.record();
                }
            }
        }
        return values;
    }
}
