package com.djcps.wms.delivery.model;
/**
 * 运单编号查询订单信息
 * @author wyb
 *
 */
public class OrderInfoPO {
    /**
     * 订单id
     */
    private String orderId;

    /**
     * 配货id
     */
    private String allocationId;

    /**
     * 提货单id
     */
    private String deliveryId;

    /**
     * 订单任务确认状态 1:确认 0:未确认
     */
    private int deliveryIdEffect;
    /**
     * 订单数量
     */
    private int orderAmount;

    /**
     * 待提货数量
     */
    private int deliveryAmount;

    /**
     * 实际提货数量
     */
    private Integer realDeliveryAmount;

    public String getOrderId() {
        return orderId;
    }

    public String getAllocationId() {
        return allocationId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public int getDeliveryIdEffect() {
        return deliveryIdEffect;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public int getDeliveryAmount() {
        return deliveryAmount;
    }

    public Integer getRealDeliveryAmount() {
        return realDeliveryAmount;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setAllocationId(String allocationId) {
        this.allocationId = allocationId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public void setDeliveryIdEffect(int deliveryIdEffect) {
        this.deliveryIdEffect = deliveryIdEffect;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setDeliveryAmount(int deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public void setRealDeliveryAmount(Integer realDeliveryAmount) {
        this.realDeliveryAmount = realDeliveryAmount;
    }

    @Override
    public String toString() {
        return "OrderInfoPO [orderId=" + orderId + ", allocationId=" + allocationId + ", deliveryId=" + deliveryId
                + ", deliveryIdEffect=" + deliveryIdEffect + ", orderAmount=" + orderAmount + ", deliveryAmount="
                + deliveryAmount + ", realDeliveryAmount=" + realDeliveryAmount + "]";
    }

}
