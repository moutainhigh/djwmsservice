package com.djcps.wms.commons.msg;

import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.djcps.wms.commons.httpclient.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装http请求返回的json数据
 * @author Chengw
 * @since 2017-03-07.
 */
public class MsgTemplate {

    /**
     * 成功输出
     * @return
     */
    public static Map<String, Object> successMsg() {
        return successMsg(null);
    }

    /**
     * 成功输出
     * @param data 输出数据
     * @return
     */
    public static Map<String, Object> successMsg(Object data) {
        return customMsg(true, 100000, "", data);
    }

    /**
     * 成功输出
     * @param message  MsgInterface接口类型
     * @param data     输出数据
     * @return
     */
    public static Map<String, Object> successMsg(MsgInterface message, Object data){
        return customMsg(true, message.getCode(), message.getMsg(), data);
    }
    
    /**
     * 错误输出
     * @param message  MsgInterface接口类型
     * @param data     输出数据
     * @return
     */
    public static Map<String, Object> failureMsg(MsgInterface message, Object data){
        return customMsg(false, message.getCode(), message.getMsg(), data);
    }
    
    /**
     * 错误输出
     * @param message MsgInterface接口类型
     * @return
     */
    public static Map<String, Object> failureMsg(MsgInterface message) {
        return customMsg(false, message.getCode(), message.getMsg(), null);
    }

    /**
     * 错误输出,不建议使用，违反统一输出规范
     * @param error 错误输出
     * @return
     */
    @Deprecated
    public static Map<String, Object> failureMsg(String error) {
        return customMsg(false, 310001, error, null);
    }

    /**
     * 错误输出
     * @param ret 错误输出
     * @return
     */
    public static Map<String, Object> failureMsg(ComplexResult ret) {
        if(ret.getErrors().size() > 0){

            return customMsg(false,
                    ret.getErrors().get(0).getErrorCode() > 0 ?  ret.getErrors().get(0).getErrorCode() : 310015,
                    ret.getErrors().get(0).getErrorMsg() + "，错误字段："+ret.getErrors().get(0).getField(), null);
        }
        return customMsg(false, 310015, null, null);
    }

    /**
     * 网络请求 返回map对象
     * @param result
     * @return
     */
    public static Map<String, Object> customMsg(HttpResult result) {
        return customMsg(result.isSuccess(), result.getCode(), result.getMsg(), result.getData());
    }

    /**
     * Custom map.
     *
     * @param success the success
     * @param msgCode the enums code
     * @param message the message
     * @param data    the data
     * @return the map
     */
    private static Map<String, Object> customMsg(boolean success, int msgCode, String message, Object data) {
        Map<String, Object> result = new HashMap<String, Object>(4);
        result.put("success", success);
        result.put("code", msgCode);
        result.put("msg", message);
        result.put("data", data);
        try {
            logger.info("返回输出：", JSONObject.toJSONString(result));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private static final Logger logger = LoggerFactory.getLogger(MsgTemplate.class);


}