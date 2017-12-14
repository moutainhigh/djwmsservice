package com.djcps.wms.warehouse.model.area;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;


/**
 * 区(县)对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月8日
 */
public class CountyBo implements Serializable{
	
	private static final long serialVersionUID = 1138136777264335726L;

	/**
	 * 区名称
	 */
	@NotBlank
	private String countyName;
	
	/**
	 * 区编码
	 */
	@NotBlank
	private String countyCode;
	
	/**
	 * 库区状态,0是不选,1是全选,2是半选
	 */
	private String countyStatus;
	
	/**
	 * 多个街道
	 */
	@NotBlank
	private List streetList;
	
	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public List getStreetList() {
		return streetList;
	}

	public void setStreetList(List streetList) {
		this.streetList = streetList;
	}

	public String getCountyStatus() {
		return countyStatus;
	}

	public void setCountyStatus(String countyStatus) {
		this.countyStatus = countyStatus;
	}

	@Override
	public String toString() {
		return "CountyBo [countyName=" + countyName + ", countyCode=" + countyCode + ", streetList=" + streetList
				+ ", countyStatus=" + countyStatus + "]";
	}

}
