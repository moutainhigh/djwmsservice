package com.djcps.wms.commons.utils;

import com.alibaba.fastjson.JSONObject;
import com.djcps.wms.commons.enums.FluteTypeEnum;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
     * 字符串参数 设置到map集合中
     * @param str
     * @param map
     * @return
     */
    public static Map<String, String[]> strToMap(String str, Map<String, String[]> map) {
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(str)) {
            String[] param = str.split("&").clone();
            for(String p : param){
                String[] p1 = p.split("=").clone();
                if(2 == p1.length){
                    String key = p1[0];
                    String[] params = map.get(key);
                    if(!ObjectUtils.isEmpty(params)) {
                        int loc = params.length;
                        params = Arrays.copyOf(params, loc + 1);
                        params[loc] = p1[1];
                        map.replace(key, params);
                    }else{
                        params = new String[]{p1[1]};
                        map.put(key, params);
                    }
                }
            }
        }

        return map;
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

