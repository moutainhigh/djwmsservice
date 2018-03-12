package com.djcps.wms.record.constant;
/**
 * 操作记录操作类型参数
 * 
 * @author ztw
 * @since 2017/2/23
 */
public class WaybillOperationConstant {
    
    
    //=====================运单操作类型====================
    
    /**
     * 新建
     */
    public static final String CREATE_WAYBILL = "30";
    
    /**
     * 开始提货
     */
    public static final String START_TAKE_DELIVERY = "31";
    
    /**
     * 提货完成
     */
    public static final String FINISH_TAKE_DELIVERY = "32";
    
    /**
     * 开始装车
     */
    public static final String START_TRUCK_LOADING = "33";
    
    /**
     * 装车完成
     */
    public static final String FINISH_TRUCK_LOADING = "34";
    
    
}