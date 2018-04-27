package com.djcps.wms.commons.utils;

import com.djcps.wms.commons.enums.FluteTypeEnum;

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

    /**
     * 楞型转换
     * @param fluteType
     * @return
     * @author:zdx
     * @date:2018年4月24日
     */
    public static String switchFluteType(String fluteType){
        String newFluteType = null;
        switch(fluteType){
            case "BC瓦":
                newFluteType = FluteTypeEnum.BC.getValue();break;
            case "BE瓦":
                newFluteType = FluteTypeEnum.BE.getValue();break;
            case "单C瓦":
                newFluteType = FluteTypeEnum.C.getValue();break;
            case "单B瓦":
                newFluteType = FluteTypeEnum.B.getValue();break;
            case "单E瓦":
                newFluteType = FluteTypeEnum.E.getValue();break;
            case "EBC瓦":
                newFluteType = FluteTypeEnum.EBC.getValue();break;
            default:
                newFluteType = FluteTypeEnum.EE.getValue();break;
        }
        return newFluteType;
    }
}

