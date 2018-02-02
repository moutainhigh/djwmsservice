package com.djcps.wms.delivery.model;

/**
 * 订单提货信息
 * @author Chengw
 * @since 2018/2/1 14:55.
 */
public class OrderDeliveryPO {

    /**
     * 库区ID
     */
    private String warehouseAreaId;

    /**
     * 库区名称
     */
    private String warehouseAreaName;

    /**
     * 库位ID
     */
    private String warehouseLocId;

    /**
     * 库位名称
     */
    private String warehouseLocName;

    /**
     * 提货数量
     */
    private Integer deliveryAmount;

    /**
     * 实际提货数量
     */
    private Integer realDeliveryAmount;
    /**
     * 状态
     */
    private Integer status;

    public String getWarehouseAreaId() {
        return warehouseAreaId;
    }

    public void setWarehouseAreaId(String warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
    }

    public String getWarehouseAreaName() {
        return warehouseAreaName;
    }

    public void setWarehouseAreaName(String warehouseAreaName) {
        this.warehouseAreaName = warehouseAreaName;
    }

    public String getWarehouseLocId() {
        return warehouseLocId;
    }

    public void setWarehouseLocId(String warehouseLocId) {
        this.warehouseLocId = warehouseLocId;
    }

    public String getWarehouseLocName() {
        return warehouseLocName;
    }

    public void setWarehouseLocName(String warehouseLocName) {
        this.warehouseLocName = warehouseLocName;
    }

    public Integer getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(Integer deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public Integer getRealDeliveryAmount() {
        return realDeliveryAmount;
    }

    public void setRealDeliveryAmount(Integer realDeliveryAmount) {
        this.realDeliveryAmount = realDeliveryAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDeliveryPO{" +
                "warehouseAreaId='" + warehouseAreaId + '\'' +
                ", warehouseAreaName='" + warehouseAreaName + '\'' +
                ", warehouseLocId='" + warehouseLocId + '\'' +
                ", warehouseLocName='" + warehouseLocName + '\'' +
                ", deliveryAmount=" + deliveryAmount +
                ", realDeliveryAmount=" + realDeliveryAmount +
                ", status=" + status +
                '}';
    }
}
