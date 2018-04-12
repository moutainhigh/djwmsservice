package com.djcps.wms.warehouse.model.area;


import java.io.Serializable;
import java.util.List;


/**
 * 该类为库区实体类
 * 
 * @author zdx
 * @version 1.0
 * @since 2017/12/6
 */
public class WarehouseAreaPO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8887985531241631551L;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 库区唯一标识
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
     * 库区名称
     */
    private String name;
    /**
     * 省名称
     */
    private String provinceName;
    /**
     * 省编码
     */
    private String provinceCode;
    /**
     * 市名称
     */
    private String cityName;
    /**
     * 市编码
     */
    private String cityCode;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

	@Override
	public String toString() {
		return "WarehouseAreaPO [warehouseName=" + warehouseName + ", id=" + id + ", warehouseId=" + warehouseId
				+ ", warehouseAreaId=" + warehouseAreaId + ", name=" + name + ", provinceName=" + provinceName
				+ ", provinceCode=" + provinceCode + ", cityName=" + cityName + ", cityCode=" + cityCode + "]";
	}

}
