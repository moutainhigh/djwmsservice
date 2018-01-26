package com.djcps.wms.allocation.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.base.BaseListBO;

/**
 * 配货管理和配货模糊查询对象
 * @company:djwms
 * @author:zdx
 * @date:2018年1月22日
 */
public class GetAllocationResultBO extends BaseBO implements Serializable{

	private static final long serialVersionUID = -5359074791977719744L;
	
	/**
	 * 运单号
	 */
	private String waybillId;
	/**
	 * 提货单号
	 */
	private String deliveryId;
	/**
	 * 订单号
	 */
	private String fchildorderid;
	/**
	 * 商品名称
	 */
	private String fgroupgoodname;
	/**
	 * 客户名称
	 */
	private String custName;
	/**
	 * 订单状态
	 */
	private String status;
	/**
	 * 车牌号
	 */
	private String plateNumber;
	/**
	 * 交期开始时间
	 */
	private String deliveryStartTime;
	/**
	 * 交期结束时间
	 */
	private String deliveryEndTime;
	
	/**
	 * 下单开始时间
	 */
	private String orderStartTime;
	/**
	 * 下单结束时间
	 */
	private String orderEndTime;
	/**
	 * 配货开始时间
	 */
	private String allocationStartTime;
	/**
	 * 配货结束时间
	 */
	private String allocationEndTime;
	/**
	 * 运单状态,1待提货,5部分提货,10提货完成,15部分装车,20装车完成
	 */
	private String waybillStatus;
	/**
	 * 仓库类型,1,2,3,4,5(纸板，纸箱，积分商城仓库，物料仓库，退货仓库)
	 */
	@NotBlank
	private String warehouseType;
	/**
	 * 仓库编码
	 */
	@NotBlank
	private String warehouseId;
	/**
	 * 页数
	 */
	@NotBlank
	private String pageNo;
	
	/**
	 * 行数
	 */
	@NotBlank
	private String pageSize;

	public String getAllocationStartTime() {
		return allocationStartTime;
	}

	public void setAllocationStartTime(String allocationStartTime) {
		this.allocationStartTime = allocationStartTime;
	}

	public String getAllocationEndTime() {
		return allocationEndTime;
	}

	public void setAllocationEndTime(String allocationEndTime) {
		this.allocationEndTime = allocationEndTime;
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

	public String getFchildorderid() {
		return fchildorderid;
	}

	public void setFchildorderid(String fchildorderid) {
		this.fchildorderid = fchildorderid;
	}

	public String getFgroupgoodname() {
		return fgroupgoodname;
	}

	public void setFgroupgoodname(String fgroupgoodname) {
		this.fgroupgoodname = fgroupgoodname;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(String deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public String getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(String deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	public String getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public String getOrderEndTime() {
		return orderEndTime;
	}

	public void setOrderEndTime(String orderEndTime) {
		this.orderEndTime = orderEndTime;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getWaybillStatus() {
		return waybillStatus;
	}

	public void setWaybillStatus(String waybillStatus) {
		this.waybillStatus = waybillStatus;
	}

	@Override
	public String toString() {
		return "GetAllocationResultBO [waybillId=" + waybillId + ", deliveryId=" + deliveryId + ", fchildorderid="
				+ fchildorderid + ", fgroupgoodname=" + fgroupgoodname + ", custName=" + custName + ", status=" + status
				+ ", plateNumber=" + plateNumber + ", deliveryStartTime=" + deliveryStartTime + ", deliveryEndTime="
				+ deliveryEndTime + ", orderStartTime=" + orderStartTime + ", orderEndTime=" + orderEndTime
				+ ", allocationStartTime=" + allocationStartTime + ", allocationEndTime=" + allocationEndTime
				+ ", waybillStatus=" + waybillStatus + ", warehouseType=" + warehouseType + ", warehouseId="
				+ warehouseId + ", pageNo=" + pageNo + ", pageSize=" + pageSize + "]";
	}
	
}
