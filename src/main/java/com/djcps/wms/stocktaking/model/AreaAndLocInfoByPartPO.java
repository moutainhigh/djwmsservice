package com.djcps.wms.stocktaking.model;

import java.util.List;

/**
 * 部分新增时的参数接收类
 * @author:wzy
 * @company:djwms
 * @create:2018/1/26
 **/
public class AreaAndLocInfoByPartPO {

    /**
     * 库位选中状态0未选中，1部分选中，2是全部
     */
    private Integer status;

    private String warehouseAreaId;

    private String warehouseAreaName;

    private List<LocationListPO> locationList;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public List<LocationListPO> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<LocationListPO> locationList) {
        this.locationList = locationList;
    }

    @Override
    public String toString() {
        return "AreaAndLocInfoByPartPO{" +
                "status=" + status +
                ", warehouseAreaId='" + warehouseAreaId + '\'' +
                ", warehouseAreaName='" + warehouseAreaName + '\'' +
                ", locationList=" + locationList +
                '}';
    }
}
