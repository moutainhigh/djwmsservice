package com.djcps.wms.provider.model;

import java.io.Serializable;


import com.djcps.wms.commons.base.BaseListParam;

/**
 * @title:根据供应商属性模糊查询获取供应商对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月29日
 */
public class SelectProviderByAttributeBO extends BaseListParam implements Serializable{

	private static final long serialVersionUID = 7878892531556006961L;

	/**
	 * 供应商编码
	 */
	private String providerId;
	
	/**
	 * 供应商名称,30个字
	 */
	private String name;
	
	/**
	 * 供应商简称,10个字 
	 */
	private String shortName;

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String toString() {
		return "SelectVagueBean [providerId=" + providerId + ", name=" + name + ", shortName=" + shortName + "]";
	}
		
}
