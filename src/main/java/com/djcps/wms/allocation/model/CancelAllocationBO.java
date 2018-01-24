package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.order.model.OrderIdBO;

/**
 * 取消订单
 * @company:djwms
 * @author:zdx
 * @date:2018年1月23日
 */
public class CancelAllocationBO extends BaseAddBO implements Serializable{
	
	private static final long serialVersionUID = 7010231338345551359L;
	/**
	 * 所有的订单号
	 */
	@NotEmpty
	private List<OrderIdBO> orderIds;
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
	 * 智能配货id
	 */
	@NotBlank
	private String allocationId;
	
	public List<OrderIdBO> getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(List<OrderIdBO> orderIds) {
		this.orderIds = orderIds;
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
	
	public String getAllocationId() {
		return allocationId;
	}
	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}
	@Override
	public String toString() {
		return "CancelAllocationBO [orderIds=" + orderIds + ", waybillId=" + waybillId + ", deliveryId=" + deliveryId
				+ ", allocationId=" + allocationId + "]";
	}
	
}
