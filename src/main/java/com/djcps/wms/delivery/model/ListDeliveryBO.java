package com.djcps.wms.delivery.model;


import com.djcps.wms.commons.base.BaseListBO;

/**
 * 提货单列表 参数类
 * @author Chengw
 * @since 2018/1/31 07:49.
 */
public class ListDeliveryBO extends BaseListBO {

    /**
     * 合作方号
     */
    private String partnerId;

    /**
     * 提货单号
     */
    private String deliveryId;

    /**
     * 运单编号
     */
    private String waybillId;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 提货状态
     */
    private Integer status;

    /**
     * 配货时间（提货单创建时间）起始
     */
    private String allocationTimeStart;

    /**
     * 配货时间（提货单创建时间）结束
     */
    private String allocationTimeEnd;

    /**
     * 提货完成时间起始
     */
    private String finishTimeStart;

    /**
     * 交期时间起始
     */
    private String deliveryTimeStart;

    /**
     * 交期时间结束
     */
    private String deliveryTimeEnd;

    /**
     * 提货完成时间结束
     */
    private String finishTimeEnd;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 提货人员
     */
    private String pickerId;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(String waybillId) {
        this.waybillId = waybillId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAllocationTimeStart() {
        return allocationTimeStart;
    }

    public void setAllocationTimeStart(String allocationTimeStart) {
        this.allocationTimeStart = allocationTimeStart;
    }

    public String getAllocationTimeEnd() {
        return allocationTimeEnd;
    }

    public void setAllocationTimeEnd(String allocationTimeEnd) {
        this.allocationTimeEnd = allocationTimeEnd;
    }

    public String getFinishTimeStart() {
        return finishTimeStart;
    }

    public void setFinishTimeStart(String finishTimeStart) {
        this.finishTimeStart = finishTimeStart;
    }

    public String getDeliveryTimeStart() {
        return deliveryTimeStart;
    }

    public void setDeliveryTimeStart(String deliveryTimeStart) {
        this.deliveryTimeStart = deliveryTimeStart;
    }

    public String getDeliveryTimeEnd() {
        return deliveryTimeEnd;
    }

    public void setDeliveryTimeEnd(String deliveryTimeEnd) {
        this.deliveryTimeEnd = deliveryTimeEnd;
    }

    public String getFinishTimeEnd() {
        return finishTimeEnd;
    }

    public void setFinishTimeEnd(String finishTimeEnd) {
        this.finishTimeEnd = finishTimeEnd;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPickerId() {
        return pickerId;
    }

    public void setPickerId(String pickerId) {
        this.pickerId = pickerId;
    }

    @Override
    public String toString() {
        return "ListDeliveryBO{" +
                "partnerId='" + partnerId + '\'' +
                ", deliveryId='" + deliveryId + '\'' +
                ", waybillId='" + waybillId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", status=" + status +
                ", allocationTimeStart='" + allocationTimeStart + '\'' +
                ", allocationTimeEnd='" + allocationTimeEnd + '\'' +
                ", finishTimeStart='" + finishTimeStart + '\'' +
                ", deliveryTimeStart='" + deliveryTimeStart + '\'' +
                ", deliveryTimeEnd='" + deliveryTimeEnd + '\'' +
                ", finishTimeEnd='" + finishTimeEnd + '\'' +
                ", productName='" + productName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", pickerId='" + pickerId + '\'' +
                '}';
    }
}
