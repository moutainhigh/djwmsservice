package com.djcps.wms.stocktaking.model;

import java.util.List;

public class StocktakingCompleteStatusPO {
    private String warehouseAreaId;

    private String warehouseAreaName;

   private Integer  status;

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
        return "StocktakingCompleteStatusPO{" +
                "warehouseAreaId='" + warehouseAreaId + '\'' +
                ", warehouseAreaName='" + warehouseAreaName + '\'' +
                ", status='" + status + '\'' +
                ", locationList=" + locationList +
                '}';
    }
}
