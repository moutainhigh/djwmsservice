package com.djcps.wms.stocktaking.model;

import java.io.Serializable;
import java.util.List;

/**
 * 新增全盘的参数接收类
 * @author:wzy
 * @company:djwms
 * @create:2018/1/26
 **/
public class AddTaskByAllPO implements Serializable{

    private static final long serialVersionUID = -410380581666472359L;

    private String orderId;

    private String warehouseId;

    private String warehouseName;

    private List<WarehouseAreaInfoBO> warehouseAreaInfo;

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

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public List<WarehouseAreaInfoBO> getWarehouseAreaInfo() {
        return warehouseAreaInfo;
    }

    public void setWarehouseAreaInfo(List<WarehouseAreaInfoBO> warehouseAreaInfo) {
        this.warehouseAreaInfo = warehouseAreaInfo;
    }

    @Override
    public String toString() {
        return "AddTaskByAllPO{" +
                "orderId='" + orderId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", warehouseAreaInfo=" + warehouseAreaInfo +
                '}';
    }
}
