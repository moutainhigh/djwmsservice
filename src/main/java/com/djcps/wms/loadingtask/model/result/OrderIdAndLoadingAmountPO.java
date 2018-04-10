package com.djcps.wms.loadingtask.model.result;

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
    /**
     * 获取订单状态
     */
    private Integer orderStatus;
    /**
     * 异常数量
     */
    private Integer abnomalAmount;
    /**
     * 实际提货数量
     */
    private Integer realDeliveryAmount;

    public Integer getRealDeliveryAmount() {
        return realDeliveryAmount;
    }

    public void setRealDeliveryAmount(Integer realDeliveryAmount) {
        this.realDeliveryAmount = realDeliveryAmount;
    }

    public Integer getAbnomalAmount() {
        return abnomalAmount;
    }

    public void setAbnomalAmount(Integer abnomalAmount) {
        this.abnomalAmount = abnomalAmount;
    }

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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderIdAndLoadingAmountPO [orderId=" + orderId + ", loadingAmount=" + loadingAmount + ", orderStatus="
                + orderStatus + ", abnomalAmount=" + abnomalAmount + ", realDeliveryAmount=" + realDeliveryAmount + "]";
    }

}
