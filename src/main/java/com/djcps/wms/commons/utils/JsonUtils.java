package com.djcps.wms.commons.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * json操作类
 * @author Chengw
 * @create 2018/4/27 11:06.
 * @since 1.0.0
 */
public class JsonUtils {

    /**
     * json参数设置到map中
     * @param json
     * @param map
     * @return
     */
    public static Map<String,String[]> jsonToMap(String json, Map<String,String[]> map){
        if(StringUtils.isNotEmpty(json)) {
            Map<String, Object> jsonMap = JSONObject.parseObject(json, HashMap.class);
            Set<String> keySet = jsonMap.keySet();
            keySet.forEach(key -> {
                String obj = String.valueOf(jsonMap.get(key));
                String[] params = map.get(key);
                if(ObjectUtils.isEmpty(params)){
                    map.put(key,new String[]{obj});
                }else{
                    int loc = params.length;
                    params = Arrays.copyOf(params, loc + 1);
                    params[loc] = obj;
                    map.replace(key, params);
                }
            });
        }
        return map;
    }

}
