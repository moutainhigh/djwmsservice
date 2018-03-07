package com.djcps.wms.record.util;

/**
 * 提货单相关操作工具类
 * @author ztw
 * @since 2018/2/23
 *
 */
public class DeliveryOperationRecordUtil {

    public static String getCraeteEvent(String picker){
        
        return new StringBuilder().append("新建了提货单，提货人:").append(picker).toString();
    }
    
    public static String getStartEvent(){
        
        return "开始提货";
    }

    public static String getFinishEvent(){
    
        return "完成全部提货操作";
    }   

}
