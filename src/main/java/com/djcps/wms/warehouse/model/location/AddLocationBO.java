package com.djcps.wms.warehouse.model.location;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBo;
import com.djcps.wms.commons.base.BaseParam;

/**
 * @title:库位新增对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月29日
 */
public class AddLocationBO extends BaseAddBo implements Serializable{

	private static final long serialVersionUID = 4254884515508797752L;

	/**
	 * 仓库编号
	 */
	@NotBlank
	private String warehouseId;
	
	/**
	 * 仓库名称
	 */
	@NotBlank
	private String warehouseName;
	
	/**
	 * 库区编号
	 */
	@NotBlank
	private String warehouseAreaId;
	
	/**
	 * 库区名称
	 */
	@NotBlank
	private String warehouseAreaName;
	
	/**
	 * 库位编号
	 */
	@NotBlank
	private String warehouseLocId;
	
	/**
	 * 库位名称
	 */
	@NotBlank
	private String name;
	
	/**
	 * 库位类型
	 */
	@NotBlank
	private String type;
	
	/**
	 * 库位长
	 */
	private String length;
	
	/**
	 * 库位宽
	 */
	private String width;
	
	/**
	 * 库位高
	 */
	private String height;
	
	/**
	 * 库位容积  m3
	 */
	private String volume;
	
	/**
	 * 库位承重 kg
	 */
	private String bearing;

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

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
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
		return "AddLocationBO [warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", warehouseAreaId="
				+ warehouseAreaId + ", warehouseAreaName=" + warehouseAreaName + ", warehouseLocId=" + warehouseLocId
				+ ", name=" + name + ", type=" + type + ", length=" + length + ", width=" + width + ", height=" + height
				+ ", volume=" + volume + ", bearing=" + bearing + "]";
	}


	
}
