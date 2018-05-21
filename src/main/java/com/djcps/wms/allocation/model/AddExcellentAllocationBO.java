package com.djcps.wms.allocation.model;



import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;

/**
 * 新增智能配货
 * @company:djwms
 * @author:zdx
 * @date:2018年2月4日
 */
public class AddExcellentAllocationBO extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = 640662852507847681L;
	
	/**
	 * 智能配货id
	 */
	private String allocationId;
	/**
	 * 提货单
	 */
	private String deliveryId;
	/**
	 * 车辆id
	 */
	@NotBlank
	private String carId;
	
	private String waybillId;
	
	/**
	 * 订单详情信息
	 */
	private List<WarehouseOrderDetailPO> orderList;
	
	public List<WarehouseOrderDetailPO> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<WarehouseOrderDetailPO> orderList) {
		this.orderList = orderList;
	}
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
				+ carId + ", waybillId=" + waybillId + ", orderList=" + orderList + "]";
	}
	
}
