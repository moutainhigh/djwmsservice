package com.djcps.wms.warehouse.model.area;

import java.util.List;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 确认街道是否使用对象
 * @company:djwms
 * @author:zdx
 * @date:2018年1月2日
 */
public class IsUseStreetBO extends BaseAddBO{
	/**
	 * 仓库编码
	 */
	private String warehouseId;
	/**
	 * 街道StreetBO list
	 */
	private List streetList;
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public List getStreetList() {
		return streetList;
	}
	public void setStreetList(List streetList) {
		this.streetList = streetList;
	}
	@Override
	public String toString() {
		return "IsUseStreetBO [warehouseId=" + warehouseId + ", streetList=" + streetList + "]";
	}
	
}
