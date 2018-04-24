package com.djcps.wms.permission.model.bo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author zhq
 * 传递参数的基础类
 * 2018年4月23日
 */
public class BaseOrgParamBO {
	/**
	 * 操作人
	 */
	@NotBlank
	private String operator;
	/**
	 * ip
	 */
	@NotBlank
	private String ip;
	/**
	 * 业务模块
	 */
	@NotBlank
	private String business;
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	@Override
	public String toString() {
		return "BaseOrgParamBO [operator=" + operator + ", ip=" + ip + ", business=" + business + "]";
	}
	
}
