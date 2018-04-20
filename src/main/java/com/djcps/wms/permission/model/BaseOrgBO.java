package com.djcps.wms.permission.model;

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
	private String operator;
	/**
	 * ip
	 */
	private String ip;
	/**
	 * 业务模块
	 */
	private String bussion;
	
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
	public String getBussion() {
		return bussion;
	}
	public void setBussion(String bussion) {
		this.bussion = bussion;
	}
	@Override
	public String toString() {
		return "OrgBaseBO [operator=" + operator + ", ip=" + ip + ", bussion=" + bussion + "]";
	}
	
}
