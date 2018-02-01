package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 确认更换车辆
 * @company:djwms
 * @author:zdx
 * @date:2018年1月23日
 */
public class ChangeCarInfoBO extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = -7663047064374288713L;
	
	/**
	 * 智能配货id
	 */
	@NotBlank
	private String allocationId;
	
	/**
	 * 车辆id
	 */
	@NotBlank
	private String carId;
	
	/**
	 * 车牌号
	 */
	@NotBlank
	private String plateNumber;
	
	/**
	 * 运单号
	 */
	@NotBlank
	private String waybillId;

	public String getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
	}

	@Override
	public String toString() {
		return "ChangeCarInfoBO [allocationId=" + allocationId + ", carId=" + carId + ", plateNumber=" + plateNumber
				+ ", waybillId=" + waybillId + "]";
	}
	
}
