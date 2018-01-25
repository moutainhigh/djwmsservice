package com.djcps.wms.stocktaking.model;

/**
 * 库位信息
 * @author  wzy
 * @param
 * @return
 * @create  2018/1/23 18:10
 **/
public class LocationInfo {
    private String warehouseId;
    private String warehouseAreaId;
    private String warehouseLocId;

    private String id;
    private String name;
    private String type;
    private String length;
    private String width;
    private String heigth;
    private String volume;
    private String bearing;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeigth() {
        return heigth;
    }

    public void setHeigth(String heigth) {
        this.heigth = heigth;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getBearing() {
        return bearing;
    }

    public void setBearing(String bearing) {
        this.bearing = bearing;
    }

    @Override
    public String toString() {
        return "LocationInfo{" +
                "warehouseId='" + warehouseId + '\'' +
                ", warehouseAreaId='" + warehouseAreaId + '\'' +
                ", warehouseLocId='" + warehouseLocId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", heigth='" + heigth + '\'' +
                ", volume='" + volume + '\'' +
                ", bearing='" + bearing + '\'' +
                '}';
    }
}
