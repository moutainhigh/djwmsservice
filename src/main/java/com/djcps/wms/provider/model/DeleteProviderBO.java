package com.djcps.wms.provider.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;

/**
 * @title:供应商删除对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月29日
 */
public class DeleteProviderBO extends BaseUpdateAndDeleteBO implements Serializable{

	private static final long serialVersionUID = 3019764457554816231L;
	
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
		return "DeleteProviderBO [id=" + id + "]";
	}
	
}
