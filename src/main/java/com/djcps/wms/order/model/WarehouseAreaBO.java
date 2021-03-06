package com.djcps.wms.order.model;

import java.io.Serializable;
import java.util.List;

/**
 * 库区对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public class WarehouseAreaBO implements Serializable{
	
	private static final long serialVersionUID = 1657011130307398174L;

	/**
	 * 库区编号 
	 */
	private String warehouseAreaId;
	
	/**
	 * 库区名称
	 */
	private String warehouseAreaName;
	
	/**
	 * 库区list
	 */
	private List<WarehouseLocationBO> locationList;


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

	public List<WarehouseLocationBO> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<WarehouseLocationBO> locationList) {
		this.locationList = locationList;
	}

	@Override
	public String toString() {
		return "WarehouseAreaHaveLocationListBO [warehouseAreaId=" + warehouseAreaId + ", warehouseAreaName="
				+ warehouseAreaName + ", locationList=" + locationList + "]";
	}
	
}
