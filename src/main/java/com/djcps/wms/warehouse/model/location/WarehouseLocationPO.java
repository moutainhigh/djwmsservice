package com.djcps.wms.warehouse.model.location;


import java.io.Serializable;


/**
 * 该类为库位实体类
 * 
 * @author zdx
 * @version 1.0
 * @since 2017/12/7
 */
public class WarehouseLocationPO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4505351110264495524L;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 库区名称
     */
    private String warehouseAreaName;
    /**
     * 库位唯一标识
     */
    private String id;
    /**
     * 仓库编号
     */
    private String warehouseId;
    /**
     * 库区编号
     */
    private String warehouseAreaId;
    /**
     * 库位编号
     */
    private String warehouseLocId;
    /**
     * 库位名称
     */
    private String name;
    /**
     * 库位类型
     */
    private Integer type;
    /**
     * 库位长
     */
    private Double length;
    /**
     * 库位宽
     */
    private Double width;
    /**
     * 库位高
     */
    private Double height;
    /**
     * 库位容积
     */
    private Double volume;
    /**
     * 库位承重
     */
    private Double bearing;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getBearing() {
        return bearing;
    }

    public void setBearing(Double bearing) {
        this.bearing = bearing;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseAreaName() {
        return warehouseAreaName;
    }

    public void setWarehouseAreaName(String warehouseAreaName) {
        this.warehouseAreaName = warehouseAreaName;
    }

    @Override
    public String toString() {
        return "WarehouseLocationPO [warehouseName=" + warehouseName + ", warehouseAreaName=" + warehouseAreaName
                + ", id=" + id + ", warehouseId=" + warehouseId + ", warehouseAreaId=" + warehouseAreaId
                + ", warehouseLocId=" + warehouseLocId + ", name=" + name + ", type=" + type + ", length=" + length
                + ", width=" + width + ", height=" + height + ", volume=" + volume + ", bearing=" + bearing + "]";
    }

}
