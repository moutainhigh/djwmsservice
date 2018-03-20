package com.djcps.wms.cancelstock.model;

import java.io.Serializable;

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
	private String fflutetype;
	
	/**
	 * 下料规格
	 */
	private String fmaterialSize;
	
	/**
	 * 产品规格
	 */
	private String fproductSize;
	
	/**
	 * 材料名称
	 */
	private String fmaterialname;
	
	/**
	 * 产品名称
	 */
	private String productName;
	
	private Integer status;
	
	private String warehouseId;
	
	private String warehouseName;
	
	/**
	 * 经纬度
	 */
	private String location;
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getFflutetype() {
		return fflutetype;
	}

	public void setFflutetype(String fflutetype) {
		this.fflutetype = fflutetype;
	}

	public String getFmaterialSize() {
		return fmaterialSize;
	}

	public void setFmaterialSize(String fmaterialSize) {
		this.fmaterialSize = fmaterialSize;
	}

	public String getFproductSize() {
		return fproductSize;
	}

	public void setFproductSize(String fproductSize) {
		this.fproductSize = fproductSize;
	}

	public String getFmaterialname() {
		return fmaterialname;
	}

	public void setFmaterialname(String fmaterialname) {
		this.fmaterialname = fmaterialname;
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

	@Override
	public String toString() {
		return "CancalOrderAttributePO [orderId=" + orderId + ", id=" + id + ", cancelAmount=" + cancelAmount
				+ ", fflutetype=" + fflutetype + ", fmaterialSize=" + fmaterialSize + ", fproductSize=" + fproductSize
				+ ", fmaterialname=" + fmaterialname + ", productName=" + productName + ", status=" + status
				+ ", warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", location=" + location + "]";
	}

}
