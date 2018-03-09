package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

/** 提货单订单映射对象
 * @company:djwms
 * @author:zdx
 * @date:2018年1月23日
 */
public class DeliveryOrderPO implements Serializable{

	private static final long serialVersionUID = 1663257778600398357L;
	
	/**
	 * 提货单号
	 */
	private String deliveryId;
	
	/**
	 * 装车id
	 */
	private String loadingTableId;
	/**
	 * 装车台名称
	 */
	private String loadingTableName;
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
	 * 订单信息
	 */
	private List<OrderPO> orders;

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

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
	
	public List<OrderPO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderPO> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "DeliveryOrderPO [deliveryId=" + deliveryId + ", loadingTableId=" + loadingTableId
				+ ", loadingTableName=" + loadingTableName + ", loadingPersonId=" + loadingPersonId
				+ ", loadingPersonName=" + loadingPersonName + ", loadingPersonPhone=" + loadingPersonPhone
				+ ", pickerId=" + pickerId + ", pickerName=" + pickerName + ", pickerPhone=" + pickerPhone + ", orders="
				+ orders + "]";
	}
}
