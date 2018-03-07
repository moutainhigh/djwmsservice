package com.djcps.wms.record.util;

/**
 * 订单相关操作工具类
 * @author ztw
 * @since 2018/2/23
 *
 */
public class OrderOperationRecordUtil {

    public static String getEntryEvent(String warehouseName, String warehouseAreaName,
                                       String warehouseLocName, int amount){
        
        return new StringBuilder().append("在仓库").append(warehouseName)
                   .append("-").append("库区").append(warehouseAreaName)
                   .append("-").append("库位").append(warehouseLocName)
                   .append("入库").append("" + amount).toString();
    }
    
    public static String getRemoveEvent(String oldWarehouseAreaName, String oldWarehouseLocName,
                                        String newWarehouseAreaName, String newWarehouseLocName){
        
        return new StringBuilder().append("将此笔订单从库区").append(oldWarehouseAreaName)
                   .append("-").append("库位").append(oldWarehouseLocName)
                   .append("移到").append("库区").append(newWarehouseAreaName)
                   .append("库位").append(newWarehouseLocName).toString();
    }
    
    public static String getReturnEvent(String warehouseName, String warehouseAreaName,
                                        String warehouseLocName){
        
        return new StringBuilder().append("将此笔订单从装车台退库到仓库").append(warehouseName)
                   .append("-").append("库区").append(warehouseAreaName)
                   .append("-").append("库位").append(warehouseLocName)
                   .toString();
    }
    
    public static String getRecommendEvent(String warehouseAreaName,
                                           String warehouseLocName){
        
        return new StringBuilder().append("为您推荐库区库位:库区")
                   .append(warehouseAreaName).append("库位").append(warehouseLocName)
                   .toString();
    }
    
    public static String getAllocationEvent(String plateNumber, String driverName){
        
        return new StringBuilder().append("对此笔订单进行了配货操作.车牌:")
                   .append(plateNumber).append("司机:").append(driverName)
                   .toString();
    }
    
    public static String getCAllocationEvent(){
        
        return "对此笔订单进行了取消配货操作.";
    }
    
    public static String getDeliveryEvent(String warehouseName, String warehouseAreaName,
                                          String warehouseLocName, int amount){
        
        return new StringBuilder().append("在仓库").append(warehouseName)
                .append("-").append("库区").append(warehouseAreaName)
                .append("-").append("库位").append(warehouseLocName)
                .append("提货").append("" + amount).toString();
    }
    
    public static String getLoadingEvent(){
        //TODO
        return "";
    }
    
    public static String getTakeStockEvent(int amount){
        
        return new StringBuilder().append("对此笔订单进行盘点，盘点数量:")
                   .append(amount).toString();
    }
    
    public static String getStartAbnomalEvent(int amount){
        
        return new StringBuilder().append("对此笔订单发起异常，异常数量:")
                   .append(amount).toString();
    }
    
    public static String getHandleAbnomalEvent(String handleType){
        
        return new StringBuilder().append("对此笔订单进行异常处理，处理结果:")
                   .append(handleType).toString();
    }
    
    public static String getSplitOrderEvent(int amount){
        
        return new StringBuilder().append("拆分了此笔订单，拆分数量:")
                .append(amount).toString();
    }
    
}
