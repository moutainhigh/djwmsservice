package com.djcps.wms.loadingtask.model;
/**
 * 客户信息
 * @author ldh
 *
 */
public class CustomerBO {
	
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
	private String contactWay;
	/**
	 * 联系人
	 */
	private String contacts;
	/**
	 * 地址
	 */
	private String address;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	@Override
	public String toString() {
		return "CustomerBO [orderIds=" + orderIds + ", customerName=" + customerName + ", contactWay=" + contactWay
				+ ", contacts=" + contacts + ", address=" + address + "]";
	}
}
