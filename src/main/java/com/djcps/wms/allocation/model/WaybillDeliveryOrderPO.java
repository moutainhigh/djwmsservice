package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

/**
 * 运单,提货单,订单映射对象
 * @company:djwms
 * @author:zdx
 * @date:2018年1月24日
 */
public class WaybillDeliveryOrderPO implements Serializable{
	
	private static final long serialVersionUID = -1258282299661719419L;

	/**
	 * 运单号
	 */
	private String waybillId;
	
	/**
	 *  智能配货id
	 */
	private String allocationId;
	
	/**
	 * 提货单对象
	 */
	private List<DeliveryOrderPO> deliveryOrder;

	public String getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}

	public String getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
	}

	public List<DeliveryOrderPO> getDeliveryOrder() {
		return deliveryOrder;
	}

	public void setDeliveryOrder(List<DeliveryOrderPO> deliveryOrder) {
		this.deliveryOrder = deliveryOrder;
	}

	@Override
	public String toString() {
		return "WaybillDeliveryOrderPO [waybillId=" + waybillId + ", allocationId=" + allocationId + ", deliveryOrder="
				+ deliveryOrder + "]";
	}

}
