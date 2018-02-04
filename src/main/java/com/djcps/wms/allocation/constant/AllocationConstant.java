package com.djcps.wms.allocation.constant;


/**
 * 配货,配货管理模块
 * @company:djwms
 * @author:zdx
 * @date:2018年2月4日
 */
public class AllocationConstant {

    public static final String DEFAULT_VERSION = "1.0";

    /**
     * 部分入库
     */
    public static final String LESS_ADD_STOCK ="21";

    /**
     * 已入库
     */
    public static final String ALL_ADD_STOCK ="22";
    /**
     * 已配货
     */
    public static final String ORDER_ALREADY_ALLOCATION ="23";
    
    /**
     * 已提货
     */
    public static final String ORDER_ALREADY_DELIVERY ="24";
    
    /**
     * 已入库
     */
    public static final String ORDER_ALREADY_LOADING ="25";
    /**
     * 已发车
     */
    public static final String ORDER_ALREADY_GOTO ="26";
    
    /**
     * 提货单确认状态,1确认
     */
    public static final String DELIVERY_EFFEFT ="1";
    
    /**
     * 提货单确认状态,2未确认
     */
    public static final String DELIVERY_UNEFFEFT ="2";
    
    /**
     * 配货单确认状态,1确认
     */
    public static final String ALLOCATION_EFFECT ="1";
    
    /**
     * 配货单确认状态,2未确认
     */
    public static final String ALLOCATION_UNEFFECT ="2";
    
    /**
     * 纸板仓库
     */
    public static final String PAPERBOARD_WAREHOUSE ="1";
    /**
     * 纸箱仓库
     */
    public static final String CARTON_WAREHOUSE ="2";
    /**
     * 积分商城仓库
     */
    public static final String INTEGRATED_STORE_WAREHOUSE ="3";
    /**
     * 物料仓库
     */
    public static final String SUPPLIES_WAREHOUSE ="4";
    /**
     * 退货仓库
     */
    public static final String SALES_RETURN_WAREHOUSE ="5";
    
    /**
     * 平台纸板订单
     */
    public static final String PLATFORM_PAPERBOARD_ORDER ="1";
    /**
     * 线下纸板订单
     */
    public static final String OFFLINE_PAPERBOARD_ORDER ="2";
    /**
     * 纸箱订单
     */
    public static final String CARTON_ORDER ="3";
    
    /**
     * 运单前缀
     */
    public static final String WAYBILLID_PREFIX ="YD";
    /**
     * 提货单前缀
     */
    public static final String DELIVERYID_PREFIX ="TH";
    
    /**
     * 有查询条件
     */
    public static final String HAVE_QUERY_CONDITION ="1";
    
    /**
     * 无查询条件
     */
    public static final String UNHAVE_QUERY_CONDITION="0";
    
    /**
     * 配货删除订单
     */
    public static final String ALLOCATION_REMOVE_ORDER="0";
    
    /**
     * 配货管理删除订单
     */
    public static final String ALLOCATION_MANAGEMENT_REMOVE_ORDER="1";
    
    /**
     * 逗号分隔符
     */
    public static final String COMMA_SEPARATOR=",";
}
