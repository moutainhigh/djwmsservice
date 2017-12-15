package com.djcps.wms.warehouse.model.area;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseAddBo;


/**
 * 省市对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月8日
 */
public class ProvinceCityBo implements Serializable{
	
	private static final long serialVersionUID = 4149549572390899075L;
	
	/**
	 * 唯一标识
	 */
	private String id;
	
	/**
	 * 仓库编号
	 */
	private String warehouseId;
	
	/**
	 * 仓库名称
	 */
	private String warehouseName;
	
	/**
	 * 库区编号
	 */
	private String warehouseAreaId;
	
	/**
	 * 库区名称,10个字
	 */
	@NotBlank
	private String name;
	
	/**
	 * 省名称
	 */
	@NotBlank
	private String provinceName;
	
	/**
	 * 省编码
	 */
	@NotBlank
	private String provinceCode;
	
	/**
	 * 市名称
	 */
	@NotBlank
	private String cityName;
	
	/**
	 * 市编码
	 */
	@NotBlank
	private String cityCode;
	
	/**
	 * 多个区
	 */
	@NotEmpty
	private List countyList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getWarehouseAreaId() {
		return warehouseAreaId;
	}

	public void setWarehouseAreaId(String warehouseAreaId) {
		this.warehouseAreaId = warehouseAreaId;
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

	public List getCountyList() {
		return countyList;
	}

	public void setCountyList(List countyList) {
		this.countyList = countyList;
	}

	@Override
	public String toString() {
		return "ProvinceCityBo [id=" + id + ", warehouseId=" + warehouseId + ", warehouseName=" + warehouseName
				+ ", warehouseAreaId=" + warehouseAreaId + ", name=" + name + ", provinceName=" + provinceName
				+ ", provinceCode=" + provinceCode + ", cityName=" + cityName + ", cityCode=" + cityCode
				+ ", countyList=" + countyList + "]";
	}

	
}
