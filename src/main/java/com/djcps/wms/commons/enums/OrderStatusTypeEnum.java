package com.djcps.wms.commons.enums;

/**
 * 订单状态类型
 * @author Chengw
 * @since 2018/1/26 12:17.
 */
public enum OrderStatusTypeEnum {
	
	
	/**
	 * 全部的订单状态
	 */
	All_STATUS("-3"),
	
	/**
	 *待入库 
	 */
	NO_STOCK("3"),
	
	/**
     * 部分入库
     */
    LESS_ADD_STOCK("21"),
    /**
     * 已入库
     */
    ALL_ADD_STOCK("22"),
	
	/**
	 * 已配货
	 */
	ORDER_ALREADY_ALLOCATION("23"),
	
	/**
	 * 已提货
	 */
	ORDER_ALREADY_DELIVERY("24"),
	
	/**
	 * 已装车
	 */
	ORDER_ALREADY_LOADING("25"),
	
	 /**
     * 已发车
     */
	ORDER_ALREADY_GOTO("26");

    private String value;

    OrderStatusTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "OrderStatusTypeEnum{" +
                "value='" + value + '\'' +
                '}';
    }
}
