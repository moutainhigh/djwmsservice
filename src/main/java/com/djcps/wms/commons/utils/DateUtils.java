package com.djcps.wms.commons.utils;

import java.time.LocalDate;

/**
 * 时间计算
 * @author Chengw
 * @create 2018/4/25 09:35.
 * @since 1.0.0
 */
public class DateUtils {

    /**
     *
     * @param year
     * @param month
     * @return
     */
    public static String getStartMonth(Integer year,Integer month){
        LocalDate localDate = LocalDate.of(year,month,1);
        return localDate.toString();
    }

    /**
     *
     * @param year
     * @param month
     * @return
     */
    public static String getEndMonth(Integer year,Integer month){
        LocalDate localDate = LocalDate.of(year,month,1);
        localDate = localDate.plusMonths(1);
        return localDate.toString();
    }

    public static void main(String[] args){
        System.out.println(getStartMonth(2018,4));
        System.out.println(getEndMonth(2018,4));
    }
}
