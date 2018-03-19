package com.djcps.wms.warehouse.model.location;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;

/**
 * @title:仓库修改对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月29日
 */
public class UpdateLocationBO extends BaseUpdateAndDeleteBO implements Serializable{

	private static final long serialVersionUID = 3242861166727997124L;

	/**
	 * 唯一标识
	 */
	@NotBlank
	private String id;
	
	/**
	 * 库位类型
	 */
	@NotBlank
	private String type;
	
	/**
	 * 库位名称
	 */
	@NotBlank
	private String name;
	
	/**
	 * 库位长
	 */
	private String length;
	
	/**
	 * 库位宽
	 */
	private String width;
	
	/**
	 * 库位高
	 */
	private String height;
	
	/**
	 * 库位容积  m3
	 */
	private String volume;
	
	/**
	 * 库位承重 kg
	 */
	private String bearing;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getBearing() {
		return bearing;
	}

	public void setBearing(String bearing) {
		this.bearing = bearing;
	}

	@Override
	public String toString() {
		return "UpdateLocationBO [id=" + id + ", type=" + type + ", name=" + name + ", length=" + length + ", width="
				+ width + ", height=" + height + ", volume=" + volume + ", bearing=" + bearing + "]";
	}

	
}
