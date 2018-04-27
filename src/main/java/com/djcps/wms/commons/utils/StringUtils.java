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
     * 楞型转换,中文转数字
     * @param fluteType
     * @return
     * @author:zdx
     * @date:2018年4月24日
     */
    public static String switchFluteTypeToNumber(String fluteType){
    	String newFluteType = null; 
    	switch(fluteType){
        case "BC瓦":
        	newFluteType = FluteTypeEnum.BC_NUMBER.getValue();break;
        case "BE瓦":
        	newFluteType = FluteTypeEnum.BE_NUMBER.getValue();break;
        case "单C瓦":
        	newFluteType = FluteTypeEnum.C_NUMBER.getValue();break;
        case "单B瓦":
        	newFluteType = FluteTypeEnum.B_NUMBER.getValue();break;
        case "单E瓦":
        	newFluteType = FluteTypeEnum.E_NUMBER.getValue();break;
        case "EBC瓦":
        	newFluteType = FluteTypeEnum.EBC_NUMBER.getValue();break;
        default:
        	newFluteType = FluteTypeEnum.EE_NUMBER.getValue();break;
        }
    	return newFluteType;
    }
    
    /**
     * 楞型转换,数字转中文
     * @param fluteType
     * @return
     * @author:zdx
     * @date:2018年4月24日
     */
    public static String switchFluteTypeToString(String fluteType){
    	String newFluteType = null; 
    	switch(fluteType){
        case "1":
        	newFluteType = FluteTypeEnum.BC_STRING.getValue();break;
        case "2":
        	newFluteType = FluteTypeEnum.BE_STRING.getValue();break;
        case "3":
        	newFluteType = FluteTypeEnum.C_STRING.getValue();break;
        case "4":
        	newFluteType = FluteTypeEnum.B_STRING.getValue();break;
        case "5":
        	newFluteType = FluteTypeEnum.E_STRING.getValue();break;
        case "6":
        	newFluteType = FluteTypeEnum.EBC_STRING.getValue();break;
        default:
        	newFluteType = FluteTypeEnum.EE_STRING.getValue();break;
        }
    	return newFluteType;
    }
}
