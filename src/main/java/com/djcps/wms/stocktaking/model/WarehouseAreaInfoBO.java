package com.djcps.wms.stocktaking.model;

import java.io.Serializable;
import java.util.List;

/**
 * 库区库位信息实体类
 * @author:wzy
 * @company:djwms
 * @create:2018/1/26
 **/
public class WarehouseAreaInfoBO implements Serializable{

    private static final long serialVersionUID = -440569053456467512L;

    private String warehouseAreaId;

    private String warehouseAreaName;

    private List<LocationListPO> locationList;

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
        return "WarehouseAreaInfoBO{" +
                "warehouseAreaId='" + warehouseAreaId + '\'' +
                ", warehouseAreaName='" + warehouseAreaName + '\'' +
                ", locationList=" + locationList +
                '}';
    }
}
