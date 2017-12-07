package com.djcps.wms.commons.aop.inneruser.annotation;

import java.lang.annotation.*;

/**
 * @author Chengw
 * @since 2017/12/5 13:17.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InnerUserToken {
}
