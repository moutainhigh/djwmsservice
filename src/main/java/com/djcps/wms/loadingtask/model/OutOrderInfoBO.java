package com.djcps.wms.loadingtask.model;

import java.util.Date;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 向出库单插入的数据
 * @author ldh
 *
 */
public class OutOrderInfoBO extends BaseAddBO{
	/**
	 * 出库单编号
	 */
	private String id;
	/**
	 * 订单编号集合
	 */
	private String orderIds;
	/**
	 * 客户名称
	 */
	private String customerName;
	
	/**
	 * 联系方式
	 */
	private String contactway;
	/**
	 * 联系人
	 */
	private String contacts;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 司机id
	 */
	private String driverId;
	/**
	 * 司机名称
	 */
	private String driverName;
	/**
	 * 车牌号
	 */
	private String plateNumber;
	/**
	 * 配货时间
	 */
	private String allocationTime;
	
	public String getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactway() {
		return contactway;
	}
	public void setContactway(String contactway) {
		this.contactway = contactway;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAllocationTime() {
		return allocationTime;
	}
	public void setAllocationTime(String allocationTime) {
		this.allocationTime = allocationTime;
	}
	@Override
	public String toString() {
		return "OutOrderInfoBO [id=" + id + ", orderIds=" + orderIds + ", customerName=" + customerName
				+ ", contactway=" + contactway + ", contacts=" + contacts + ", address=" + address + ", driverId="
				+ driverId + ", driverName=" + driverName + ", plateNumber=" + plateNumber + ", allocationTime="
				+ allocationTime + "]";
	}
	
	
	
}
