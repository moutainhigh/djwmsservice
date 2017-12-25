package com.djcps.wms.warehouse.model.area;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 街道对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月8日
 */
public class StreetBO implements Serializable{
	
	private static final long serialVersionUID = -5140531461362068945L;
	
	/**
	 * 唯一标识
	 */
	@NotBlank
	private String id;
	
	/**
	 * 街道名称
	 */
	@NotBlank
	private String streetName;
	
	/**
	 * 街道编码
	 */
	@NotBlank
	private String streetCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		return "StreetBo [id=" + id + ", streetName=" + streetName + ", streetCode=" + streetCode + "]";
	}
	
}
