package com.djcps.wms.commons.aop.log;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AddLog {
	/**
	 * 操作内容描述
	 * @return
	 */
	String value();
	/**
	 * 系统模块名
	 * @return
	 */
	String module();
}
