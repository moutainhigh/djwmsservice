package com.djcps.wms.warehouse.model.area;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;

/**
 * @title:仓库库区修改对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月29日
 */
public class UpdateAreaBO extends BaseUpdateAndDeleteBO implements Serializable{

	private static final long serialVersionUID = 2523650660051089827L;

	/**
	 * 唯一标识
	 */
	@NotBlank
	private String id;
	
	/**
	 * 库区名称,10个字
	 */
	@NotBlank
	private String name;
	
	/**
	 * 省名称
	 */
	private String provinceName;
	
	/**
	 * 省编码
	 */
	private String provinceCode;
	
	/**
	 * 市名称
	 */
	private String cityName;
	
	/**
	 * 市编码
	 */
	private String cityCode;
	
	private List<UpdateAreaDetailBO> countyList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public List<UpdateAreaDetailBO> getCountyList() {
		return countyList;
	}

	public void setCountyList(List<UpdateAreaDetailBO> countyList) {
		this.countyList = countyList;
	}

	@Override
	public String toString() {
		return "UpdateAreaBO [id=" + id + ", name=" + name + ", provinceName=" + provinceName + ", provinceCode="
				+ provinceCode + ", cityName=" + cityName + ", cityCode=" + cityCode + ", countyList=" + countyList
				+ "]";
	}
	
}
