package com.djcps.wms.loadingtask.model.result;

public class LoadInfoPO {
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
        return "LoadInfoPO [orderId=" + orderId + ", loadingAmount=" + loadingAmount + "]";
    }

}
