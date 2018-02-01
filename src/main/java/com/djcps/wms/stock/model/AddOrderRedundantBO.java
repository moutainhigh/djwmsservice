package com.djcps.wms.stock.model;


import java.io.Serializable;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 冗余表对象
 * @company:djwms
 * @author:zdx
 * @date:2018年1月26日
 */
public class AddOrderRedundantBO extends BaseBO implements Serializable{
	private static final long serialVersionUID = -6933472703581523046L;
	/**
	 * 合作方id
	 */
	private String partnerId;
	/**
	 * 合作方姓名
	 */
	private String partnerName;
	/**
	 *合作方区域 
	 */
	private String partnerArea;
	/**
	 * 订单状态
	 */
	private Integer status;
	/**
	 * 是否拆分
	 */
	private Integer isSplit;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 材料名称
	 */
	private String materialName;
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
	 * 下单时间
	 */
	private String orderTime;
	/**
	 * 交期时间
	 */
	private String deliveryTime;
	/**
	 * 支付时间
	 */
	private String paymentTime;
	
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerArea() {
		return partnerArea;
	}
	public void setPartnerArea(String partnerArea) {
		this.partnerArea = partnerArea;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsSplit() {
		return isSplit;
	}
	public void setIsSplit(Integer isSplit) {
		this.isSplit = isSplit;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Override
	public String toString() {
		return "AddOrderRedundantBO [partnerId=" + partnerId + ", partnerName=" + partnerName + ", partnerArea="
				+ partnerArea + ", status=" + status + ", isSplit=" + isSplit + ", orderId=" + orderId
				+ ", customerName=" + customerName + ", productName=" + productName + ", materialName=" + materialName
				+ ", materialLength=" + materialLength + ", materialWidth=" + materialWidth + ", boxLength=" + boxLength
				+ ", boxWidth=" + boxWidth + ", boxHeight=" + boxHeight + ", orderTime=" + orderTime + ", deliveryTime="
				+ deliveryTime + ", paymentTime=" + paymentTime + "]";
	}
}
