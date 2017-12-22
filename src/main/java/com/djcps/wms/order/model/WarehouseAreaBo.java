package com.djcps.wms.order.model;

import java.util.List;

/**
 * 库区对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public class WarehouseAreaBo {
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
	private List<WarehouseLocationBo> locationList;

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

	public List<WarehouseLocationBo> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<WarehouseLocationBo> locationList) {
		this.locationList = locationList;
	}
	
}
