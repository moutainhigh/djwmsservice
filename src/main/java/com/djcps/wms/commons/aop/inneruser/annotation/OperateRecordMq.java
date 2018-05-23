package com.djcps.wms.commons.aop.inneruser.annotation;

import java.lang.annotation.*;

/**
 * Created by xzzx on 2018/5/22.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateRecordMq {
    /**
     * 操作记录信息
     *
     * @return
     */
    String[] record() default "";
}
