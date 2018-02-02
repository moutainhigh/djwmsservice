package com.djcps.wms.allocation.model;

import java.io.Serializable;

/**
 * 配货管理查询映射对象
 * @company:djwms
 * @author:zdx
 * @date:2018年1月23日
 */
public class GetAllocationManageListPO implements Serializable{

	private static final long serialVersionUID = -6078678415214752000L;
	
	/**
	 * 配货时间,也就是提货单创建时间
	 */
	private String deliveryCreateTime;
	/**
	 * 运单号
	 */
	private String waybillId;
	/**
	 * 运单状态
	 */
	private String status;
	/**
	 * 装车台名称
	 */
	private String loadingtableName;
	/**
	 * 车牌号
	 */
	private String plateNumber;
	
	private int total;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getDeliveryCreateTime() {
		return deliveryCreateTime;
	}
	public void setDeliveryCreateTime(String deliveryCreateTime) {
		this.deliveryCreateTime = deliveryCreateTime;
	}
	public String getWaybillId() {
		return waybillId;
	}
	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLoadingtableName() {
		return loadingtableName;
	}
	public void setLoadingtableName(String loadingtableName) {
		this.loadingtableName = loadingtableName;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	@Override
	public String toString() {
		return "GetAllocationManageListPO [deliveryCreateTime=" + deliveryCreateTime + ", waybillId=" + waybillId
				+ ", status=" + status + ", loadingtableName=" + loadingtableName + ", plateNumber=" + plateNumber
				+ ", total=" + total + "]";
	}
}
