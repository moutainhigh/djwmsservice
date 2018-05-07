package com.djcps.wms.permission.model.bo;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author zhq
 * ORG服务的基础类
 * 2018年4月12日
 */
public class BaseOrgBO implements Serializable{

	private static final long serialVersionUID = 945515681218849379L;
	
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
		return "BaseOrgBO{" +
				"operator='" + operator + '\'' +
				", ip='" + ip + '\'' +
				", business='" + business + '\'' +
				'}';
	}
}
