package com.djcps.wms.record.util;

/**
 * 运单相关操作工具类
 * @author ztw
 * @since 2018/2/23
 *
 */
public class WaybillOperationRecordUtil {

    public static String getCraeteEvent(String plateNumber, String driverName){
        
        return new StringBuilder().append("新建了运单，车牌:")
                   .append(plateNumber).append("司机:").append(driverName)
                   .toString();
    }
    
    public static String getStartDeliveryEvent(){
        
        return "开始提货";
    }

    public static String getFinishDeliveryEvent(){
    
        return "完成全部提货操作";
    }   
    
    public static String getStartLoadingEvent(){
        
        return "开始装车";
    }

    public static String getFinishLoadingEvent(){
    
        return "完成全部装车操作";
    }  
    
    public static String getFinishLoadingEvent(String... loadingPersons){
        //loadingPersons : 张三,李四,王五
        return "完成全部装车操作。装车人员:" + loadingPersons;
    }  
}
