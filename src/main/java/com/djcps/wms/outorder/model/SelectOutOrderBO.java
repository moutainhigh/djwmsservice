package com.djcps.wms.outorder.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseListBO;

/**
 * 查询所有出库单所需要的条件类
 * @author ldh
 *
 */
public class SelectOutOrderBO extends BaseListBO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2691916317471128384L;
	/**
	 * 出库单编号
	 */
	private String id;
	/**
	 * 订单编号
	 */
	private String orderId;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
     * 出库单状态
     */
	private Integer status;
	/**
     * 客户名称
     */
	private String customerName;
	/**
     * 开始时间
     */
	private String startTime;
	/**
     * 结束时间
     */
	private String endTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "SelectOutOrderBO [id=" + id + ", orderId=" + orderId + ", productName=" + productName + ", status="
				+ status + ", customerName=" + customerName + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
    
}
