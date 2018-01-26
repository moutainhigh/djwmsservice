package com.djcps.wms.allocation.model;


import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseListBO;

/**
 * 装车优化确认追加订单
 * @company:djwms
 * @author:zdx
 * @date:2018年1月23日
 */
public class AgainVerifyAddOrderBO extends BaseListBO implements Serializable{

	private static final long serialVersionUID = -4319180656618117518L;
	//=======提货单========
	/**
	 * 运单号
	 */
	@NotBlank
	private String waybillId;
	/**
	 * 提货单号
	 */
	@NotBlank
	private String deliveryId;
	/**
	 * 装车台id
	 */
	@NotBlank
	private String loadingtableId;
	/**
	 * 装车台名称
	 */
	@NotBlank
	private String loadingtableName;
	/**
	 * 打印次数
	 */
	@NotBlank
	private Integer printCount;
	/**
	 * 提货员id
	 */
	@NotBlank
	private String pickerId;
	/**
	 * 提货员名称
	 */
	@NotBlank
	private String pickerName;
	/**
	 * 车牌号
	 */
	@NotBlank
	private String plateNumber;
	/**
	 * 装车员id
	 */
	@NotBlank
	private String loadingPersonId;
	/**
	 * 装车员名称
	 */
	@NotBlank
	private String loadingPersonName;
	
	//==========订单======
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
	private String groupGoodName;
	/**
	 * 材料名称
	 */
	@NotBlank
	private String materialName;
	/**
	 * 商品规格
	 */
	@NotBlank
	private String productSize;
	/**
	 * 客户名称(母账户名称或者子账户名称)
	 */
	@NotBlank
	private String clientName;
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
	 * 收货地址
	 */
	@NotBlank
	private String address;
	/**
	 * 单位
	 */
	@NotBlank
	private String unit;
	
	public String getWaybillId() {
		return waybillId;
	}
	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
	}
	public String getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
	public String getLoadingtableId() {
		return loadingtableId;
	}
	public void setLoadingtableId(String loadingtableId) {
		this.loadingtableId = loadingtableId;
	}
	public String getLoadingtableName() {
		return loadingtableName;
	}
	public void setLoadingtableName(String loadingtableName) {
		this.loadingtableName = loadingtableName;
	}
	public Integer getPrintCount() {
		return printCount;
	}
	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
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
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
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
	public String getGroupGoodName() {
		return groupGoodName;
	}
	public void setGroupGoodName(String groupGoodName) {
		this.groupGoodName = groupGoodName;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getProductSize() {
		return productSize;
	}
	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
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
	@Override
	public String toString() {
		return "AgainVerifyAddOrderBO [waybillId=" + waybillId + ", deliveryId=" + deliveryId + ", loadingtableId="
				+ loadingtableId + ", loadingtableName=" + loadingtableName + ", printCount=" + printCount
				+ ", pickerId=" + pickerId + ", pickerName=" + pickerName + ", plateNumber=" + plateNumber
				+ ", loadingPersonId=" + loadingPersonId + ", loadingPersonName=" + loadingPersonName + ", orderId="
				+ orderId + ", allocationId=" + allocationId + ", warehouseId=" + warehouseId + ", warehouseName="
				+ warehouseName + ", warehouseAreaId=" + warehouseAreaId + ", warehouseAreaName=" + warehouseAreaName
				+ ", warehouseLocId=" + warehouseLocId + ", warehouseLocName=" + warehouseLocName + ", groupGoodName="
				+ groupGoodName + ", materialName=" + materialName + ", productSize=" + productSize + ", clientName="
				+ clientName + ", contacts=" + contacts + ", deliveryTime=" + deliveryTime + ", sequence=" + sequence
				+ ", orderAmount=" + orderAmount + ", address=" + address + ", unit=" + unit + "]";
	}
	
}
	