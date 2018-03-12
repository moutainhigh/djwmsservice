package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import com.djcps.wms.order.model.WarehouseAreaBO;

/**
 * 仓库对象
 * @company:djwms
 * @author:zdx
 * @date:2018年2月1日
 */
public class WarehousePO implements Serializable{

	private static final long serialVersionUID = -5470344424533441713L;
	
	/**
	 * 仓库编码
	 */
	private String warehouseId;
	
	/**
	 * 仓库名称
	 */
	private String warehouseName;
	
	/**
	 * 订单号
	 */
	private String orderId;
	
	/**
	 * 库区信息
	 */
	private List<WarehouseAreaBO> areaList;
	
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<WarehouseAreaBO> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<WarehouseAreaBO> areaList) {
		this.areaList = areaList;
	}

	@Override
	public String toString() {
		return "WarehousePO [warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", orderId=" + orderId
				+ ", areaList=" + areaList + "]";
	}

}
