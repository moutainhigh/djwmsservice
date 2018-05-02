package com.djcps.wms.workrecords.model;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author py
 */

import com.djcps.wms.commons.base.BaseListBO;

public class WorkRecordsBO extends BaseListBO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7869214018357478001L;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 楞型
	 */
	private Integer fluteType;

	/**
	 * 操作类型
	 * 1 入库
	 * 2 推荐库区
	 * 3 移库
	 * 4 配货
	 * 5 取消配货
	 * 6 提货
	 * 7 退库
	 * 8 装车
	 * 9 盘点
	 * 10 发起异常
	 * 11 异常处理
	 * 12 拆单处理
	 */
	@NotNull
	private Integer operationType;
	
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Integer getFluteType() {
		return fluteType;
	}
	public void setFluteType(Integer fluteType) {
		this.fluteType = fluteType;
	}
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	@Override
	public String toString() {
		return "WorkRecordsBO{" +
				"startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", operator='" + operator + '\'' +
				", fluteType=" + fluteType +
				", operationType=" + operationType +
				'}';
	}


}
