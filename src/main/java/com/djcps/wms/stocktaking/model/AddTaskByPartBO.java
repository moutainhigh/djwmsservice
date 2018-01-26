package com.djcps.wms.stocktaking.model;

/**
 * 新增部分盘点实体类
 * @author:wzy
 * @company:djwms
 * @create:2018/1/26
 **/
public class AddTaskByPartBO {
    /**
     * 仓库编号
     */
    private String warehouseId;
    /**
     * 合作方编码
     */
    private String partnerId;
    /**
     * 库区编号
     */
    private String warehouseAreaId;
    /**
     * 库位编号
     */
    private String warehouseLocId;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

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

    @Override
    public String toString() {
        return "AddTaskByPartBO{" +
                "warehouseId='" + warehouseId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", warehouseAreaId='" + warehouseAreaId + '\'' +
                ", warehouseLocId='" + warehouseLocId + '\'' +
                '}';
    }
}
