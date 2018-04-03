package com.djcps.wms.allocation.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 配货表订单对象
 * @company:djwms
 * @author:zdx
 * @date:2018年1月22日
 */
public class AddAllocationOrderBO extends BaseBO implements Serializable{

	private static final long serialVersionUID = 3846764091660082679L;
	
	/**
	 * 订单号
	 */
	@NotBlank
	private String orderId;
	/**
	 * 智能配货id
	 */
	@NotBlank
	private String allocationId;
	/**
	 * 仓库编码
	 */
	@NotBlank
	private String warehouseId;
	/**
	 * 仓库名称
	 */
	@NotBlank
	private String warehouseName;
	/**
	 * 库区编码
	 */
	@NotBlank
	private String warehouseAreaId;
	/**
	 * 库区名称
	 */
	@NotBlank
	private String warehouseAreaName;
	/**
	 * 库位编码
	 */
	@NotBlank
	private String warehouseLocId;
	/**
	 * 库位名称
	 */
	@NotBlank
	private String warehouseLocName;
	/**
	 * 商品名称
	 */
	@NotBlank
	private String productName;
	/**
	 * 材料名称
	 */
	@NotBlank
	private String materialName;
	/**
	 * 客户名称(母账户名称或者子账户名称)
	 */
	@NotBlank
	private String customerName;
	/**
	 * 联系人
	 */
	@NotBlank
	private String contacts;
	/**
	 * 交期时间
	 */
	@NotBlank
	private String deliveryTime;
	/**
	 * 装车顺序
	 */
	@NotBlank
	private String sequence;
	/**
	 * 订单数量
	 */
	@NotBlank
	private String orderAmount;
	
	/**
	 * 提货数量
	 */
	private String deliveryAmount;
	/**
	 * 收货地址
	 */
	@NotBlank
	private String address;
	/**
	 * 单位
	 */
	@NotBlank
	private String unit;
	
	public String getDeliveryAmount() {
		return deliveryAmount;
	}
	public void setDeliveryAmount(String deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAllocationId() {
		return allocationId;
	}
	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}
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
	public String getWarehouseLocName() {
		return warehouseLocName;
	}
	public void setWarehouseLocName(String warehouseLocName) {
		this.warehouseLocName = warehouseLocName;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Override
	public String toString() {
		return "AddAllocationOrderBO [orderId=" + orderId + ", allocationId=" + allocationId + ", warehouseId="
				+ warehouseId + ", warehouseName=" + warehouseName + ", warehouseAreaId=" + warehouseAreaId
				+ ", warehouseAreaName=" + warehouseAreaName + ", warehouseLocId=" + warehouseLocId
				+ ", warehouseLocName=" + warehouseLocName + ", productName=" + productName + ", materialName="
				+ materialName + ", customerName=" + customerName + ", contacts=" + contacts + ", deliveryTime="
				+ deliveryTime + ", sequence=" + sequence + ", orderAmount=" + orderAmount + ", deliveryAmount="
				+ deliveryAmount + ", address=" + address + ", unit=" + unit + "]";
	}
	
}
