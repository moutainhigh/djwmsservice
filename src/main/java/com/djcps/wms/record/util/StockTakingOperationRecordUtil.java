package com.djcps.wms.record.util;

/**
 * 盘点任务单相关操作工具类
 * @author ztw
 * @since 2018/2/23
 *
 */
public class StockTakingOperationRecordUtil {

    public static String getCraeteEvent(String checker){
        
        return new StringBuilder().append("新建了盘点任务单，盘点人:").append(checker).toString();
    }
    
    public static String getStartEvent(){
        
        return "开始盘点任务";
    }
                
    public static String getTSEvent(){
        
        return "暂存盘点结果";
    }

    public static String getFinishEvent(){
    
        return "完成全部盘点操作";
    }   
    
}
