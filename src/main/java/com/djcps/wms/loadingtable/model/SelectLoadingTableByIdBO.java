package com.djcps.wms.loadingtable.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;

/**
 * @title:根据编号查询装车台对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
public class SelectLoadingTableByIdBO extends BaseBO implements Serializable{

	private static final long serialVersionUID = 631699184714543761L;
	
	/**
	 * 唯一标识
	 */
	@NotBlank
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SelectLoadingTableByIdBO [id=" + id + "]";
	}

}
