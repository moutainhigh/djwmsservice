package com.djcps.wms.allocation.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 修改提货单数量
 * @company:djwms
 * @author:zdx
 * @date:2018年5月18日
 */
public class UpdateDeliveryAmoutBO extends BaseAddBO implements Serializable{
	
	private static final long serialVersionUID = -4148760817680711997L;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 仓库编码
	 */
	private String warehouseId;
	/**
	 * 库区编码
	 */
	private String warehouseAreaId;
	/**
	 * 库位编码
	 */
	private String warehouseLocId;
	/**
	 * 提货数量
	 */
	private Integer deliveryAmount;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public Integer getDeliveryAmount() {
		return deliveryAmount;
	}
	public void setDeliveryAmount(Integer deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}
	@Override
	public String toString() {
		return "UpdateDeliveryAmoutBO [orderId=" + orderId + ", warehouseId=" + warehouseId + ", warehouseAreaId="
				+ warehouseAreaId + ", warehouseLocId=" + warehouseLocId + ", deliveryAmount=" + deliveryAmount + "]";
	}
	
	
}
