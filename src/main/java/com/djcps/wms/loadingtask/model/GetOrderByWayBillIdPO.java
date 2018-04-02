package com.djcps.wms.loadingtask.model;

import java.util.List;
/**
 * 根据运单id获取车牌号、订单号、配货时间和车辆id
 * @author ldh
 *
 */
public class GetOrderByWayBillIdPO {
	/**
	 * 车牌号
	 */
	private String plateNumber;
	/**
	 * 订单号
	 */
	private List<String> orderId;
	/**
	 * 配货时间
	 */
	private String allocationTime;
	/**
	 * 车辆id
	 */
	private String carId;
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public List<String> getOrderId() {
		return orderId;
	}
	public void setOrderId(List<String> orderId) {
		this.orderId = orderId;
	}
	public String getAllocationTime() {
		return allocationTime;
	}
	public void setAllocationTime(String allocationTime) {
		this.allocationTime = allocationTime;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	@Override
	public String toString() {
		return "GetOrderByWayBillIdPO [plateNumber=" + plateNumber + ", allocationTime=" + allocationTime + ", carId="
				+ carId + "]";
	}
	
	
}
