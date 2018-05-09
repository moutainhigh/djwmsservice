package com.djcps.wms.allocation.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 装车顺序对象
 * 
 * @company:djwms
 * @author:zdx
 * @date:2018年2月4日
 */
public class SequenceBO extends BaseAddBO implements Serializable {

    private static final long serialVersionUID = 910056481885333624L;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 装车顺序
     */
    private Integer sequence;

    /**
     * 提货单号
     */
    private String deliveryId;
    /**
     * 仓库编号
     */
    private String warehouseId;
    /**
     * 库区编号
     */
    private String warehouseAreaId;
    /**
     * 库位编号
     */
    private String warehouseLocId;

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

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public String getWarehouseAreaId() {
        return warehouseAreaId;
    }

    public String getWarehouseLocId() {
        return warehouseLocId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setWarehouseAreaId(String warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
    }

    public void setWarehouseLocId(String warehouseLocId) {
        this.warehouseLocId = warehouseLocId;
    }

    @Override
    public String toString() {
        return "SequenceBO [orderId=" + orderId + ", sequence=" + sequence + ", deliveryId=" + deliveryId
                + ", warehouseId=" + warehouseId + ", warehouseAreaId=" + warehouseAreaId + ", warehouseLocId="
                + warehouseLocId + "]";
    }

}
