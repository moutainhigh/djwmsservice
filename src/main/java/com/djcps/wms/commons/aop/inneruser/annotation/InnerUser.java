package com.djcps.wms.commons.aop.inneruser.annotation;

import java.lang.annotation.*;

/**
 * 自定义参数
 * 
 * @author Chengw
 * @since 2017/12/5 13:16.
 */
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InnerUser {
}
