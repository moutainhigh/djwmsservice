package com.djcps.wms.loadingtable.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBo;
import com.djcps.wms.commons.base.BaseParam;

/**
 * @title:新增装车台对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月27日
 */
public class AddLoadingTableBO extends BaseAddBo implements Serializable{
	
	private static final long serialVersionUID = 8245001644412433835L;

	/**
	 * 装车台编号
	 */
	@NotBlank
	private String loadingTableId;
	
	/**
	 * 装车台名称
	 */
	@NotBlank
	private String name;
	
	/**
	 * 装车台可用该车辆规格数组
	 */
	@NotBlank
	private String specs;

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

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	@Override
	public String toString() {
		return "AddLoadingTableBO [loadingTableId=" + loadingTableId + ", name=" + name + ", specs=" + specs + "]";
	}
	
}
