package com.djcps.wms.loadingtask.model;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 装车信息参数
 * 
 * @author WYB
 * @since 2018/3/21
 */
public class LoadingBO extends BaseAddBO {

    /**
     * 
     */
    private static final long serialVersionUID = -6623274182421249708L;
    /**
     * 装车数量
     */
    private Integer loadingAmount;
    /**
     * 订单数量
     */
    private Integer orderAmount;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 运单编号
     */
    private String wayBillId;

    public Integer getLoadingAmount() {
        return loadingAmount;
    }

    public void setLoadingAmount(Integer loadingAmount) {
        this.loadingAmount = loadingAmount;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(String wayBillId) {
        this.wayBillId = wayBillId;
    }

    @Override
    public String toString() {
        return "LoadingBO [loadingAmount=" + loadingAmount + ", orderAmount=" + orderAmount + ", orderId=" + orderId
                + ", wayBillId=" + wayBillId + "]";
    }

}
