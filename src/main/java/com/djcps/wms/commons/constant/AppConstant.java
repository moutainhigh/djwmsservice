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
     * 出库任务编号前缀
     */
    public static final String CK="CK";

    /**
     * 团购服务获取订单,double双写值
     */
    public static final String GROUP_ORDER_DOUBLE ="0.0";
    
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
     * 盘点处理消息推送,openType
     */
    public static final Integer PUSH_OPEN_TYPE_STOCKTAKING =1;
    
    /**
     * 追加订单处理消息推送,openType
     */
    public static final Integer PUSH_OPEN_TYPE_ADD_ORDER_HANDLE =2;
    
    /**
     * 提货消息推送,openType
     */
    public static final Integer PUSH_OPEN_TYPE_DELIVERY =3;
    
    /**
     * wms项目msg枚举前缀
     */
    public static final Integer WMS_MSG_ENUM_PREFIX = 880000;
    
    /**
     * 系统msg枚举前缀
     */
    public static final Integer WMS_MODULE_SYS_ENUM_PREFIX = 100;
    
    /**
     * 配货msg枚举前缀
     */
    public static final Integer WMS_MODULE_ALLOCATION_ENUM_PREFIX = 200;
    
    /**
     * 退库msg枚举前缀
     */
    public static final Integer WMS_MODULE_CANCELSTOCK_ENUM_PREFIX = 300;
    
    /**
     * 仓库msg枚举前缀
     */
    public static final Integer WMS_MODULE_WAREHOUSE_ENUM_PREFIX = 400;

    /**
     * 盘点msg枚举前缀
     */
    public static final Integer WMS_MODULE_STOCKTAKING_ENUM_PREFIX = 700;

    /**
     * 装车msg枚举前缀
     */
    public static final Integer WMS_LOADING_TABLE_ENUM_PREFIX = 800;
    /**
     * 入库msg枚举前缀
     */
    public static final Integer WMS_ENTRY_ENUM_PREFIX = 900;
}
