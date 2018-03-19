package com.djcps.wms.delivery.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 完成单条订单提货任务
 * 
 * @author Chengw
 * @since 2018/1/31 07:52.
 */
public class SaveDeliveryBO implements Serializable {

    /**
     * 合作方号
     */
    @NotNull
    private String partnerId;

    /**
     * 提货单号
     */
    @NotNull
    private String deliveryId;

    /**
     * 订单号
     */
    @NotNull
    private String orderId;

    /**
     * 仓库ID
     */
    @NotNull
    private String warehouseId;

    /**
     * 库区ID
     */
    @NotNull
    private String warehouseAreaId;

    /**
     * 库位ID
     */
    @NotNull
    private String warehouseLocId;

    /**
     * 实际提货数量
     */
    @NotNull
    private Integer realDeliveryAmount;

    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作人ID
     */
    private String operatorId;
    /**
     * 运单编号
     */
    @NotNull
    private String wayBillId;

    public String getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(String wayBillId) {
        this.wayBillId = wayBillId;
    }

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseAreaId() {
        return warehouseAreaId;
    }

    public void setWarehouseAreaId(String warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
    }

    public String getWarehouseLocId() {
        return warehouseLocId;
    }

    public void setWarehouseLocId(String warehouseLocId) {
        this.warehouseLocId = warehouseLocId;
    }

    public Integer getRealDeliveryAmount() {
        return realDeliveryAmount;
    }

    public void setRealDeliveryAmount(Integer realDeliveryAmount) {
        this.realDeliveryAmount = realDeliveryAmount;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    @Override
    public String toString() {
        return "SaveDeliveryBO [partnerId=" + partnerId + ", deliveryId=" + deliveryId + ", orderId=" + orderId
                + ", warehouseId=" + warehouseId + ", warehouseAreaId=" + warehouseAreaId + ", warehouseLocId="
                + warehouseLocId + ", realDeliveryAmount=" + realDeliveryAmount + ", operator=" + operator
                + ", operatorId=" + operatorId + ", wayBillId=" + wayBillId + "]";
    }

}
