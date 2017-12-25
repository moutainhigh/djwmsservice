package com.djcps.wms.stock.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseListParam;

/**
 * 操作记录
 * @company:djwms
 * @author:zdx
 * @date:2017年12月22日
 */
public class OperationRecordBO extends BaseListParam implements Serializable{

	private static final long serialVersionUID = 5996668621435889565L;
	
	/**
	 * 合作方id
	 */
	@NotBlank
	private String operatorId;
	
	/**
	 * 合作方
	 */
	@NotBlank
	private String operator;
	
	/**
	 * 关联id(订单id)
	 */
	@NotBlank
	private String relativeId;
	
	/**
	 * 操作类型
	 */
	@NotBlank
	private String operationType;
	/**
	 * 数量
	 */
	@NotBlank
	private String amount;
	/**
	 * 事件
	 */
	@NotBlank
	private String event;
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getRelativeId() {
		return relativeId;
	}
	public void setRelativeId(String relativeId) {
		this.relativeId = relativeId;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	@Override
	public String toString() {
		return "OperationRecordBo [operatorId=" + operatorId + ", operator=" + operator + ", relativeId=" + relativeId
				+ ", operationType=" + operationType + ", amount=" + amount + ", event=" + event + "]";
	}
	
}
