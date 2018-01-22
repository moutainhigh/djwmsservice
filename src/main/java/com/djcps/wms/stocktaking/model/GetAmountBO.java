package com.djcps.wms.stocktaking.model;

public class GetAmountBO {

    private String warehouseAreaId;

    private String warehouseLocId;

    private String orderId;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "GetAmountBO{" +
                "warehouseAreaId='" + warehouseAreaId + '\'' +
                ", warehouseLocId='" + warehouseLocId + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
