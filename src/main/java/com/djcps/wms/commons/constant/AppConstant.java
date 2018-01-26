package com.djcps.wms.commons.constant;

/**
 *  应用参数
 * @author Chengw
 * @since 2017/10/31 16:24
 */
public class AppConstant {

    public static final String DEFAULT_VERSION = "1.0";

    /**
     * WMS业务系统
     */
    public static final String WMS = "WMS";

    /**
     * 内部用户登录标识码
     */
    public static final String LOGIN_TYPE = "DJOA";
    
    /**
     * 高德地图Web服务key,目前用的是个人的
     */
    public static final String MAP_API_KEY = "acc123608b5ce05371ff359f4d3f4c18";

    /**
     * 装车台编号前缀
     */
    public static final String ZCT="ZCT";

    /**
     * 盘点任务编号前缀
     */
    public static final String TS="TS";
    
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
     * 团购服务获取订单,double双写值
     */
    public static final String GROUP_ORDER_DOUBLE ="0";
    
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
     * 提货任务状态 1:提货完成
     */
    public static final String DELIVERY_ORDER_SUCCESS ="1";
    
    /**
     * 提货任务状态 0:未完成
     */
    public static final String DELIVERY_ORDER_UNSUCCESS ="0";
    
}
