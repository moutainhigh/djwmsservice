package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 确认配货
 * @company:djwms
 * @author:zdx
 * @date:2018年1月22日
 */
public class VerifyAllocationBO extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = -5147017176184795518L;
	
	/**
	 * 所有的订单号
	 */
	private List orderIds;
	
	/**
	 * 智能配货id
	 */
	private String allocationId;
	/**
	 * 确认配货状态
	 */
	private String effect;
	/**
	 * 确认配货时间
	 */
	private String feffecttime;
	
	/**
	 * ======================提货单=================================
	 */
	
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
	 * 提货单的确认状态
	 */
	private String feffect;
	
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
	public String getAllocationId() {
		return allocationId;
	}
	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public String getFeffecttime() {
		return feffecttime;
	}
	public void setFeffecttime(String feffecttime) {
		this.feffecttime = feffecttime;
	}
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
	public String getFeffect() {
		return feffect;
	}
	public void setFeffect(String feffect) {
		this.feffect = feffect;
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
	public List getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(List orderIds) {
		this.orderIds = orderIds;
	}
	@Override
	public String toString() {
		return "VerifyAllocationBO [orderIds=" + orderIds + ", allocationId=" + allocationId + ", effect=" + effect
				+ ", feffecttime=" + feffecttime + ", waybillId=" + waybillId + ", deliveryId=" + deliveryId
				+ ", loadingtableId=" + loadingtableId + ", loadingtableName=" + loadingtableName + ", pickerId="
				+ pickerId + ", pickerName=" + pickerName + ", plateNumber=" + plateNumber + ", feffect=" + feffect
				+ ", loadingPersonId=" + loadingPersonId + ", loadingPersonName=" + loadingPersonName + "]";
	}
	
}
