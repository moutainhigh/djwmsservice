package com.djcps.wms.loadingtask.model;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 装车信息参数
 * 
 * @author WYB
 * @since 2018/3/21
 */
public class LoadingBO extends BaseBO {

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
    private String waybillId;
    /**
     * 合作方号
     */
    private String partnerId;

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

    public String getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(String waybillId) {
        this.waybillId = waybillId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return "LoadingBO [loadingAmount=" + loadingAmount + ", orderAmount=" + orderAmount + ", orderId=" + orderId
                + ", waybillId=" + waybillId + ", partnerId=" + partnerId + "]";
    }

}
