package com.djcps.wms.allocation.model;

import java.io.Serializable;

import com.djcps.wms.order.model.WarehouseOrderDetailPO;

/**
 * 智能配货映射结果
 * @company:djwms
 * @author:zdx
 * @date:2018年1月22日
 */
public class IntelligentAllocationPO implements Serializable{

	private static final long serialVersionUID = -3290887202534612683L;
	
	/**
	 * 订单数据
	 */
	private Object date;
	
	/**
	 * 车辆信息
	 */
	private CarInfo carInfo;
	
	/**
	 * 运单号
	 */
	private String waybillId;
	
	/**
	 * 提货单号
	 */
	private String deliveryId;

	public Object getDate() {
		return date;
	}

	public void setDate(Object date) {
		this.date = date;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
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

	@Override
	public String toString() {
		return "IntelligentAllocationPO [date=" + date + ", carInfo=" + carInfo + ", waybillId=" + waybillId
				+ ", deliveryId=" + deliveryId + "]";
	}
}
