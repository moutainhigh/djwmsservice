package com.djcps.wms.loadingtable.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseUpdateAndDeleteBo;

/**
 * @title:修改装车台对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月27日
 */
public class UpdateLoadingTableBO extends BaseUpdateAndDeleteBo implements Serializable{
	
	private static final long serialVersionUID = 8863360524886335013L;
	
	/**
	 * 唯一标识字段
	 */
	private String id;
	/**
	 * 装车台名称
	 */
	private String name;
	
	/**
	 * 装车台状态(1.使用中 2.空闲中)
	 */
	private String state; 
	
	/**
	 * 装车台可用该车辆规格数组
	 */
	private String specs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UpdateLoadingTableBO [id=" + id + ", name=" + name + ", state=" + state + ", specs=" + specs + "]";
	}

}
