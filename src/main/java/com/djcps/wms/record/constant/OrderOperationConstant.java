package com.djcps.wms.record.constant;
/**
 * 操作记录操作类型参数
 * 
 * @author ztw
 * @since 2017/2/23
 */
public class OrderOperationConstant {
    
    //=====================订单操作类型====================
    
    /**
     * 入库
     */
    public static final String ENTRY_WAREHOUSE = "1";
    
    /**
     * 推荐库区
     */
    public static final String RECOMMEND_WAREHOUSEAREA = "2";
    
    /**
     * 移库
     */
    public static final String REMOVE_WAREHOUSE = "3";
    
    /**
     * 配货
     */
    public static final String ALLOCATION_OF_CARGO = "4";
    
    /**
     * 取消配货
     */
    public static final String CANCLE_ALLOCATION_OF_CARGO = "5";
    
    /**
     * 提货
     */
    public static final String TAKE_DELIVERY = "6";
    
    /**
     * 退库
     */
    public static final String RETURN_WAREHOUSE = "7";
    
    /**
     * 装车
     */
    public static final String TRUCK_LOADING = "8";
    
    /**
     * 盘点
     */
    public static final String STOCK_TAKING = "9";
    
    /**
     * 发起异常
     */
    public static final String START_ABNORMAL = "10";
    
    /**
     * 异常处理
     */
    public static final String HANDLE_ABNORMAL = "11";
    
    /**
     * 拆分订单
     */
    public static final String SPLIT_ORDER = "12";
    
    
}