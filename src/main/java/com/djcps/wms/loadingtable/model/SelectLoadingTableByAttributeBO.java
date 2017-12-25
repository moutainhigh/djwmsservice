package com.djcps.wms.loadingtable.model;

import java.io.Serializable;


import com.djcps.wms.commons.base.BaseListParam;

/**
 * @title:模糊查询装车台对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月27日
 */
public class SelectLoadingTableByAttributeBO extends BaseListParam implements Serializable{
	
	private static final long serialVersionUID = -4024468423420020138L;
	
	/**
	 * 装车台编号
	 */
	private String loadingTableId;
	/**
	 * 装车台名称
	 */
	private String name;
	/**
	 * 装车台状态
	 */
	private Integer effect;
	
	public String getLoadingTableId() {
		return loadingTableId;
	}
	public void setLoadingTableId(String loadingTableId) {
		this.loadingTableId = loadingTableId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getEffect() {
		return effect;
	}
	public void setEffect(Integer effect) {
		this.effect = effect;
	}
	
	@Override
	public String toString() {
		return "SelectLoadingTableByAttributeBO [loadingTableId=" + loadingTableId + ", name=" + name + ", effect="
				+ effect + "]";
	}
	
}
