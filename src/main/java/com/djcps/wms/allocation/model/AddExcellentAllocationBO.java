package com.djcps.wms.allocation.model;



import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBO;

public class AddExcellentAllocationBO extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = 640662852507847681L;
	
	/**
	 * 智能配货id
	 */
	@NotBlank
	private String allocationId;
	/**
	 * 提货单
	 */
	@NotBlank
	private String deliveryId;
	/**
	 * 车辆id
	 */
	@NotBlank
	private String carId;
	
	private String waybillId;
	
	public String getWaybillId() {
		return waybillId;
	}
	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
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
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	@Override
	public String toString() {
		return "AddExcellentAllocationBO [allocationId=" + allocationId + ", deliveryId=" + deliveryId + ", carId="
				+ carId + "]";
	}
	
}
