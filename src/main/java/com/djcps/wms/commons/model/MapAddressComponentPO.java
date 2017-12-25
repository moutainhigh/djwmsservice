package com.djcps.wms.commons.model;

import java.io.Serializable;

/**
 * 高德地图Web服务地理位置逆编码返回对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月19日
 */
public class MapAddressComponentPO implements Serializable{
	
	private static final long serialVersionUID = -4548731253497913620L;
	
	/**
	 * 国家
	 */
	private String country;
	
	
	/**
	 * 省份
	 */
	private String province;
	
	/**
	 * 区县
	 */
	private String district;
	
	/**
	 * 区县编码
	 */
	private String adcode;
	
	/**
	 * 镇,街道
	 */
	private String township;
	
	/**
	 *  镇,街道编码
	 */
	private String towncode;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	public String getTowncode() {
		return towncode;
	}

	public void setTowncode(String towncode) {
		this.towncode = towncode;
	}

	@Override
	public String toString() {
		return "MapAddressComponentBO [country=" + country + ", province=" + province + ", district=" + district
				+ ", adcode=" + adcode + ", township=" + township + ", towncode=" + towncode + "]";
	}
	
}
