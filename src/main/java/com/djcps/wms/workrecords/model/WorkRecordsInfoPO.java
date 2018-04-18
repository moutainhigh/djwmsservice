package com.djcps.wms.workrecords.model;

import com.djcps.wms.commons.base.BaseAddBO;

public class WorkRecordsInfoPO extends BaseAddBO{
	/**
	 * 操作记录唯一标识
	 */
	private String id;
	/**
	 * 业务关联id
	 */
	private String relativeId;
	/**
	 * 操作类型
	 */
	private Integer operationType;
	/**
	 * 订单类型
	 */
	private Integer orderType;
	/**
	 * 楞型
	 */
	private Integer fluteType;
	/**
	 * 仓库id
	 */
	private String warehouseId;
	/**
	 * 面积
	 */
	private Double area;
	/**
	 * 操作数量
	 */
	private Integer amount;
	/**
	 * 事件明细
	 */
	private String event;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRelativeId() {
		return relativeId;
	}
	public void setRelativeId(String relativeId) {
		this.relativeId = relativeId;
	}
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getFluteType() {
		return fluteType;
	}
	public void setFluteType(Integer fluteType) {
		this.fluteType = fluteType;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "WorkRecordsInfoPO [id=" + id + ", relativeId=" + relativeId + ", operationType=" + operationType
				+ ", orderType=" + orderType + ", fluteType=" + fluteType + ", warehouseId=" + warehouseId + ", area="
				+ area + ", amount=" + amount + ", event=" + event + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
	
	
	
	
	
}
