package com.djcps.wms.stocktaking.model;

/**
 * 获取已盘点数信息
 * @author:wzy
 * @company:djwms
 * @create:2018/1/26
 **/
public class GetAmountBO {

    private String partnerId;

    private String warehouseAreaId;

    private String warehouseLocId;

    private String orderId;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "GetAmountBO{" +
                "partnerId='" + partnerId + '\'' +
                ", warehouseAreaId='" + warehouseAreaId + '\'' +
                ", warehouseLocId='" + warehouseLocId + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
