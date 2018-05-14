package com.djcps.wms.inneruser.model.userparam;

/**
 * 仓库对应类
 * @author wzy
 * @date 2018/4/20
 **/
public class WarehouseListPO {
    private String  warehouseId;

    private String warehouseName;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    @Override
    public String toString() {
        return "WarehouseListPO{" +
                "warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                '}';
    }
}
