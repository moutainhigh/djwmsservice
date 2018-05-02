package com.djcps.wms.cancelstock.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 退库任务映射对象
 * @company:djwms
 * @author:zdx
 * @date:2018年3月20日
 */
public class CancalOrderAttributePO implements Serializable{
	

	private static final long serialVersionUID = -2886050146248295821L;
	
	/**
	 * 订单号
	 */
	private String orderId;
	
	/**
	 * 唯一标志
	 */
	private String id;
	
	/**
	 * 退库数量
	 */
	private Integer cancelAmount;
	
	/**
	 * 楞型
	 */
	private String fluteType;
	
	/**
	 * 下料规格
	 */
	private String materialSize;
	
	/**
	 * 产品规格
	 */
	private String productSize;
	
	/**
	 * 材料名称
	 */
	private String materialName;
	
	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 订单状态
	 */
	private Integer orderStatus;
	
	private String warehouseId;
	
	private String warehouseName;
	
	/**
	 * 省市区外加地址详情拼接
	 */
	private String addressDetailProvince;
	
	/**
	 * 支付时间
	 */
	private Date payTime;
	/**
	 * 交期
	 */
	private Date deliveryTime;
	
	/**
	 * 材料id
	 */
	private String materiaFid;
	
	/**
	 * 经纬度
	 */
	private String lnglat;
	
	/**
	 * 收货人
	 */
	private String consignee;
	/**
	 * 联系方式
	 */
	private String contactWay;
	
	/**
	 * 订单数量
	 */
	private Integer orderAmount;

	public String getAddressDetailProvince() {
		return addressDetailProvince;
	}

	public void setAddressDetailProvince(String addressDetailProvince) {
		this.addressDetailProvince = addressDetailProvince;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getMateriaFid() {
		return materiaFid;
	}

	public void setMateriaFid(String materiaFid) {
		this.materiaFid = materiaFid;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getLnglat() {
		return lnglat;
	}

	public void setLnglat(String lnglat) {
		this.lnglat = lnglat;
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

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getMaterialSize() {
		return materialSize;
	}

	public void setMaterialSize(String materialSize) {
		this.materialSize = materialSize;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCancelAmount() {
		return cancelAmount;
	}

	public void setCancelAmount(Integer cancelAmount) {
		this.cancelAmount = cancelAmount;
	}

	public String getFluteType() {
		return fluteType;
	}

	public void setFluteType(String fluteType) {
		this.fluteType = fluteType;
	}

	@Override
	public String toString() {
		return "CancalOrderAttributePO [orderId=" + orderId + ", id=" + id + ", cancelAmount=" + cancelAmount
				+ ", fluteType=" + fluteType + ", materialSize=" + materialSize + ", productSize=" + productSize
				+ ", materialName=" + materialName + ", productName=" + productName + ", orderStatus=" + orderStatus
				+ ", warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", addressDetailProvince="
				+ addressDetailProvince + ", payTime=" + payTime + ", deliveryTime=" + deliveryTime + ", materiaFid="
				+ materiaFid + ", lnglat=" + lnglat + ", consignee=" + consignee + ", contactWay=" + contactWay
				+ ", orderAmount=" + orderAmount + "]";
	}

}
