package com.djcps.wms.allocation.model;


import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.commons.base.BaseListBO;

/**
 * 装车优化确认追加订单
 * @company:djwms
 * @author:zdx
 * @date:2018年1月23日
 */
public class AgainVerifyAddOrderBO extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = -4319180656618117518L;
	/**
	 * =======提货单========
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
	private String loadingTableId;
	/**
	 * 装车台名称
	 */
	@NotBlank
	private String loadingTableName;
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
	 * 提货员联系方式
	 */
	@NotBlank
	private String pickerPhone;
	
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
	/**
	 * 装车员联系方式
	 */
	@NotBlank
	private String loadingPersonPhone;
	
	/**
	 * 提货单确认状态
	 */
	private String deliveryIdEffect;
	/**
	 * ==========订单======
	 */
	@NotEmpty
	List<AddAllocationOrderBO> ordersList;
	
	public String getPickerPhone() {
		return pickerPhone;
	}
	public void setPickerPhone(String pickerPhone) {
		this.pickerPhone = pickerPhone;
	}
	public String getLoadingPersonPhone() {
		return loadingPersonPhone;
	}
	public void setLoadingPersonPhone(String loadingPersonPhone) {
		this.loadingPersonPhone = loadingPersonPhone;
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
	public List<AddAllocationOrderBO> getOrdersList() {
		return ordersList;
	}
	public void setOrdersList(List<AddAllocationOrderBO> ordersList) {
		this.ordersList = ordersList;
	}
	public String getDeliveryIdEffect() {
		return deliveryIdEffect;
	}
	public void setDeliveryIdEffect(String deliveryIdEffect) {
		this.deliveryIdEffect = deliveryIdEffect;
	}
	@Override
	public String toString() {
		return "AgainVerifyAddOrderBO [waybillId=" + waybillId + ", deliveryId=" + deliveryId + ", loadingTableId="
				+ loadingTableId + ", loadingTableName=" + loadingTableName + ", pickerId=" + pickerId + ", pickerName="
				+ pickerName + ", pickerPhone=" + pickerPhone + ", plateNumber=" + plateNumber + ", loadingPersonId="
				+ loadingPersonId + ", loadingPersonName=" + loadingPersonName + ", loadingPersonPhone="
				+ loadingPersonPhone + ", deliveryIdEffect=" + deliveryIdEffect + ", ordersList=" + ordersList + "]";
	}
	
}
	