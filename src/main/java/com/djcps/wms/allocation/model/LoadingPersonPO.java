package com.djcps.wms.allocation.model;

import java.io.Serializable;

public class LoadingPersonPO implements Serializable{
	
	/**
	 * 装车员id
	 */
	private String loadingPersonId;
	/**
	 * 装车员名称
	 */
	private String loadingPersonName;
	/**
	 * 装车员联系方式
	 */
	private String loadingPersonPhone;
	/**
	 * 装车员状态
	 */
	private String loadingPersonStatus;
	
	public LoadingPersonPO(String loadingPersonId, String loadingPersonName, String loadingPersonPhone,
			String loadingPersonStatus) {
		super();
		this.loadingPersonId = loadingPersonId;
		this.loadingPersonName = loadingPersonName;
		this.loadingPersonPhone = loadingPersonPhone;
		this.loadingPersonStatus = loadingPersonStatus;
	}
	public String getLoadingPersonStatus() {
		return loadingPersonStatus;
	}
	public void setLoadingPersonStatus(String loadingPersonStatus) {
		this.loadingPersonStatus = loadingPersonStatus;
	}
	public String getLoadingPersonId() {
		return loadingPersonId;
	}
	public void setLoadingPersonId(String loadingPersonId) {
		this.loadingPersonId = loadingPersonId;
	}
	public String getLoadingPersonName() {
		return loadingPersonName;
	}
	public void setLoadingPersonName(String loadingPersonName) {
		this.loadingPersonName = loadingPersonName;
	}
	public String getLoadingPersonPhone() {
		return loadingPersonPhone;
	}
	public void setLoadingPersonPhone(String loadingPersonPhone) {
		this.loadingPersonPhone = loadingPersonPhone;
	}
	@Override
	public String toString() {
		return "LoadingPersonPO [loadingPersonId=" + loadingPersonId + ", loadingPersonName=" + loadingPersonName
				+ ", loadingPersonPhone=" + loadingPersonPhone + ", loadingPersonStatus=" + loadingPersonStatus + "]";
	}
}
