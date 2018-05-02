package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 配货订单映射对象
 * @company:djwms
 * @author:zdx
 * @date:2018年1月24日
 */
public class OrderPO implements Serializable{

	private static final long serialVersionUID = 4564577119500469319L;
	
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 智能配货id
	 */
	private String allocationId;
	/**
	 * 提货单号
	 */
	private String deliveryId;
	/**
	 * 提货单确认状态
	 */
	private Integer deliveryIdEffect;
	
	/**
	 * 仓库编码
	 */
	private String warehouseId;
	/**
	 * 仓库名称
	 */
	private String warehouseName;
	
	/**
	 * 库区编码
	 */
	private String warehouseAreaId;
	
	/**
	 * 库区名称
	 */
	private String warehouseAreaName;
	
	/**
	 * 库位编码
	 */
	private String warehouseLocId;
	/**
	 * 库位名称
	 */
	private String warehouseLocName;
	
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 材料名称
	 */
	private String materialName;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 联系人
	 */
	private String contacts;
	/**
	 * 交期时间
	 */
	private String deliveryTime;
	/**
	 * 装车顺序
	 */
	private Integer sequence;
	/**
	 * 当前提货任务状态 1:完成  默认0:未完成'
	 */
	private Integer pickUpStatus;
	/**
	 * 订单数量
	 */
	private Integer orderAmount;
	/**
	 * 提货数量
	 */
	private Integer deliveryAmount;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 单位
	 */
	private String unit;
	
	/**
	 * 订单状态
	 */
	private Integer orderStatus;
	/**
	 * 下料规格长
	 */
	private String materialLength;
	/**
	 * 下料规格宽
	 */
	private String materialWidth;
	/**
	 * 纸箱规格长
	 */
	private String boxLength;
	/**
	 * 纸箱规格宽
	 */
	private String boxWidth;
	/**
	 * 纸箱规格高
	 */
	private String boxHeight;
	
	/**
	 * 产品规格
	 */
	private String productSize;
	
	/**
	 * 装车台id
	 */
	private String loadingTableId;
	/**
	 * 装车台名称
	 */
	private String loadingTableName;
	/**
	 * 提货员id
	 */
	private String pickerId;
	/**
	 * 提货员名称
	 */
	private String pickerName;
	/**
	 * 提货员联系方式
	 */
	private String pickerPhone;
	/**
	 * 装车员id
	 */
	private String loadingPersonId;
	/**
	 * 装车员名称
	 */
	private String loadingPersonName;
	
	/**
	 * 装车员联系方式
	 */
	private String loadingPersonPhone;
	
	/**
	 * 仓库信息
	 */
	private WarehousePO  warehouse;
	
	public WarehousePO getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehousePO warehouse) {
		this.warehouse = warehouse;
	}

	public String getLoadingTableId() {
		return loadingTableId;
	}

	public void setLoadingTableId(String loadingTableId) {
		this.loadingTableId = loadingTableId;
	}

	public String getLoadingTableName() {
		return loadingTableName;
	}

	public void setLoadingTableName(String loadingTableName) {
		this.loadingTableName = loadingTableName;
	}

	public String getPickerId() {
		return pickerId;
	}

	public void setPickerId(String pickerId) {
		this.pickerId = pickerId;
	}

	public String getPickerName() {
		return pickerName;
	}

	public void setPickerName(String pickerName) {
		this.pickerName = pickerName;
	}

	public String getPickerPhone() {
		return pickerPhone;
	}

	public void setPickerPhone(String pickerPhone) {
		this.pickerPhone = pickerPhone;
	}

	public String getLoadingPersonId() {
		return loadingPersonId;
	}

	public void setLoadingPersonId(String loadingPersonId) {
		this.loadingPersonId = loadingPersonId;
	}

	public String getLoadingPersonName() {
		return loadingPersonName;
	}

	public void setLoadingPersonName(String loadingPersonName) {
		this.loadingPersonName = loadingPersonName;
	}

	public String getLoadingPersonPhone() {
		return loadingPersonPhone;
	}

	public void setLoadingPersonPhone(String loadingPersonPhone) {
		this.loadingPersonPhone = loadingPersonPhone;
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

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public Integer getDeliveryIdEffect() {
		return deliveryIdEffect;
	}

	public void setDeliveryIdEffect(Integer deliveryIdEffect) {
		this.deliveryIdEffect = deliveryIdEffect;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getPickUpStatus() {
		return pickUpStatus;
	}

	public void setPickUpStatus(Integer pickUpStatus) {
		this.pickUpStatus = pickUpStatus;
	}

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getDeliveryAmount() {
		return deliveryAmount;
	}

	public void setDeliveryAmount(Integer deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
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

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getMaterialLength() {
		return materialLength;
	}

	public void setMaterialLength(String materialLength) {
		this.materialLength = materialLength;
	}

	public String getMaterialWidth() {
		return materialWidth;
	}

	public void setMaterialWidth(String materialWidth) {
		this.materialWidth = materialWidth;
	}

	public String getBoxLength() {
		return boxLength;
	}

	public void setBoxLength(String boxLength) {
		this.boxLength = boxLength;
	}

	public String getBoxWidth() {
		return boxWidth;
	}

	public void setBoxWidth(String boxWidth) {
		this.boxWidth = boxWidth;
	}

	public String getBoxHeight() {
		return boxHeight;
	}

	public void setBoxHeight(String boxHeight) {
		this.boxHeight = boxHeight;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	@Override
	public String toString() {
		return "OrderPO [orderId=" + orderId + ", allocationId=" + allocationId + ", deliveryId=" + deliveryId
				+ ", deliveryIdEffect=" + deliveryIdEffect + ", warehouseId=" + warehouseId + ", warehouseName="
				+ warehouseName + ", warehouseAreaId=" + warehouseAreaId + ", warehouseAreaName=" + warehouseAreaName
				+ ", warehouseLocId=" + warehouseLocId + ", warehouseLocName=" + warehouseLocName + ", productName="
				+ productName + ", materialName=" + materialName + ", customerName=" + customerName + ", contacts="
				+ contacts + ", deliveryTime=" + deliveryTime + ", sequence=" + sequence + ", pickUpStatus="
				+ pickUpStatus + ", orderAmount=" + orderAmount + ", deliveryAmount=" + deliveryAmount + ", address="
				+ address + ", unit=" + unit + ", orderStatus=" + orderStatus + ", materialLength=" + materialLength
				+ ", materialWidth=" + materialWidth + ", boxLength=" + boxLength + ", boxWidth=" + boxWidth
				+ ", boxHeight=" + boxHeight + ", productSize=" + productSize + ", loadingTableId=" + loadingTableId
				+ ", loadingTableName=" + loadingTableName + ", pickerId=" + pickerId + ", pickerName=" + pickerName
				+ ", pickerPhone=" + pickerPhone + ", loadingPersonId=" + loadingPersonId + ", loadingPersonName="
				+ loadingPersonName + ", loadingPersonPhone=" + loadingPersonPhone + ", warehouse=" + warehouse + "]";
	}

	
}
