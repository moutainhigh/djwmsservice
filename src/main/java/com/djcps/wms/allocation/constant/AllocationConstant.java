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
    
    /**
     * redis装车优化追加订单前缀
     */
    public static final String AGAIN_VERIFY_ADDORDER="againVerifyAddOrder";
    
    /**
     * redis装车优化追加订单前缀,此缓存装车优化确认配货需要使用
     */
    public static final String CACHE_AGAIN_VERIFY_ADDORDER="cacheAgainVerifyAddOrder";
    
    /**
     * redis装车优化移除订单订单前缀
     */
    public static final String REMOVE_ORDER="removeOrder";
    
    /**
     * redis,装车优化缓存的提货单号
     */
    public static final String DELIVERYID="deliveryId";
    
    /**
     * redis,缓存智能配货未确认结果
     */
    public static final String INTELLIGENT_ALLOCATION="IntelligentAllocation";
    
    /**
     * redis,智能配货确认配货锁
     */
    public static final String VERIFY_ALLOCATION="verifyAllocation";
    
    /**
     * redis,装车优化确认优化锁
     */
    public static final String AGAIN_VERIFY_ALLOCATION="againVerifyAllocation";
    
    /**
     * redis,智能配货确认配货和装车优化确认优化,公共锁
     */
    public static final String COMMON_ALLOCATION_LOADING="commonAllocationLoading";
    
    /**
     * 装车优化追加订单
     */
    public static final String EXCELLENT_LOADING_CACHE="1";
    /**
     * 智能配货追加订单
     */
    public static final String INTELLIGENT_CACHE="0";
    
    /**
     * 追加订单处理,追加订单
     */
    public static final String DISPOSE_ADD_ORDER_CACHE="2";
    
    /**
     * redis锁过期时间
     */
    public static final Integer REDIS_LOCK_TIME=60;
    
    /**
     * 操作有误
     */
    public static final String OPERATION_ERROR="操作有误,请重新刷新";
    
    /**
     * 1 盘点
     * 2 提货
     * 3 装车
     * 4 入库
     *5异常处理
     */
    public static final String PUSH_DELIVERY_TYPE="2";
    
    /**
     * 装车优化追加订单处理标记
     */
    public static final String FLAG_AGAIN_VERIFY="1";
    
    /**
     * 追加订单处理界面处理标记
     */
    public static final String FLAG_ADD_ORDER_HANDLE="0";
    
    public static final String PUSH_DELIVERY_MSG="你有最新的提货任务,请打开任务系统查看";
    
    public static final String PUSH_DELIVERY_TITLE="您有新的提货任务";
    
    public static final String PUSH_DELIVERY_TEXT="您有新的提货任务,请打开任务系统查看";
    
    public static final String HTTP_SUCCESS="success";
}
