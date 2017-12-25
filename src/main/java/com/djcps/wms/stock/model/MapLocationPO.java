package com.djcps.wms.stock.model;

import java.io.Serializable;

/**
 * 数据库maplocation表映射对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月19日
 */
public class MapLocationPO implements Serializable{
	
	private static final long serialVersionUID = -1284356650186378345L;
	/**
	 * 唯一标识
	 */
	private String id;
	/**
	 * 经纬度
	 */
	private String location;
	/**
	 * 区县名称
	 */
	private String countyName;
	/**
	 * 区县编码
	 */
	private String countyCode;
	/**
	 * 街道名称
	 */
	private String streetName;
	/**
	 * 街道编码
	 */
	private String streetCode;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
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
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getStreetCode() {
		return streetCode;
	}
	public void setStreetCode(String streetCode) {
		this.streetCode = streetCode;
	}
	@Override
	public String toString() {
		return "MapLocationPo [id=" + id + ", location=" + location + ", countyName=" + countyName + ", countyCode="
				+ countyCode + ", streetName=" + streetName + ", streetCode=" + streetCode + "]";
	}
	
}
