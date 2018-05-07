package com.djcps.wms.allocation.model;


import java.io.Serializable;

/**
 * 运单号映射对象
 * @company:djwms
 * @author:zdx
 * @date:2018年1月29日
 */
public class WaybillPO implements Serializable{
	
	private static final long serialVersionUID = 6336333174498306508L;
	/**
	 * 运单号
	 */
	private String waybillId;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 智能配货id
	 */
	private String allocationId;
	/**
	 * 运单状态,1待提货,5部分提货,10提货完成,15部分装车,20装车完成
	 */
	private Integer status;
	/**
	 * 车牌号
	 */
	private String plateNumber;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	@Override
	public String toString() {
		return "WaybillPO [waybillId=" + waybillId + ", orderId=" + orderId + ", allocationId=" + allocationId
				+ ", status=" + status + ", plateNumber=" + plateNumber + "]";
	}
	
}
