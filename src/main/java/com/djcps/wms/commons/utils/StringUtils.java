package com.djcps.wms.commons.utils;

/**
 * 字符串操作类
 * @author Chengw
 * @since 2018/2/1 16:05.
 */
public class StringUtils {

    /**
     * 空值特殊处理
     * @param obj
     * @return
     */
    public static String toString(Object obj){
        return (obj == null) ? null : obj.toString();
    }
}
