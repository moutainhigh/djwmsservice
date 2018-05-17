package com.djcps.wms.commons.aop.inneruser.annotation;

import java.lang.annotation.*;

/**
 * 获取系统操作用户信息
 * @author Chengw
 * @since 2018/4/17 10:16.
 */
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperatorAnnotation {
}
