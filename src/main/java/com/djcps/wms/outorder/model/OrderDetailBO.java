package com.djcps.wms.outorder.model;

import java.math.BigDecimal;

/**
 * 返回页面的参数
 * @author xzzx
 *
 */
public class OrderDetailBO {
	/**
	 * 订单编号
	 */
	private String childOrderId;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 下料规格
	 */
	private String materialSize;
	/**
	 * 纸箱规格
	 */
	private String productSize;
	/**
	 * 数量
	 */
	private Integer orderAmount;
	/**
	 * 单价
	 */
	private BigDecimal unitPrice;
	/**
	 * 金额
	 */
	private BigDecimal amountPrice; 
	/**
	 * 单位
	 */
	private String unit;
	
	/**
	 * 片数
	 */
	private Integer amountPiece;
	
	public Integer getAmountPiece() {
		return amountPiece;
	}

	public void setAmountPiece(Integer amountPiece) {
		this.amountPiece = amountPiece;
	}

	public OrderDetailBO(){
		this.unit = "片";
	}

	public String getChildOrderId() {
		return childOrderId;
	}

	public void setChildOrderId(String childOrderId) {
		this.childOrderId = childOrderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getAmountPrice() {
		return amountPrice;
	}

	public void setAmountPrice(BigDecimal amountPrice) {
		this.amountPrice = amountPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "OrderDetailBO [childOrderId=" + childOrderId + ", productName=" + productName + ", materialSize="
				+ materialSize + ", productSize=" + productSize + ", orderAmount=" + orderAmount + ", unitPrice="
				+ unitPrice + ", amountPrice=" + amountPrice + ", unit=" + unit + ", amountPiece=" + amountPiece + "]";
	}
	
}
