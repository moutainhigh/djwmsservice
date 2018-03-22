package com.djcps.wms.loadingTask.model.result;

/**
 * 获取订单编号及装车数量实体类
 * 
 * @author WYB
 * @since 2018/3/20
 */
public class OrderIdAndLoadingAmountPO {
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 装车台数量
     */
    private Integer loadingAmount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getLoadingAmount() {
        return loadingAmount;
    }

    public void setLoadingAmount(Integer loadingAmount) {
        this.loadingAmount = loadingAmount;
    }

    @Override
    public String toString() {
        return "OrderIdAndLoadingAmountPO [orderId=" + orderId + ", loadingAmount=" + loadingAmount + "]";
    }

}
