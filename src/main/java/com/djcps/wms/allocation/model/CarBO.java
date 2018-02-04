package com.djcps.wms.allocation.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseAddBO;

public class CarBO extends BaseAddBO implements Serializable{
	/**
	 * 车辆id
	 */
	private String carId;

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Override
	public String toString() {
		return "CarBO [carId=" + carId + "]";
	}
	
}
