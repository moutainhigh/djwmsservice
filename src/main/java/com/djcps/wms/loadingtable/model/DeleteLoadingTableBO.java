package com.djcps.wms.loadingtable.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import com.djcps.wms.commons.base.BaseUpdateAndDeleteBo;

/**
 * @title:删除装车台对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月27日
 */
public class DeleteLoadingTableBO extends BaseUpdateAndDeleteBo implements Serializable{
	
	private static final long serialVersionUID = 6963154004442103258L;

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
		return "DeleteLoadingTableBO [id=" + id + "]";
	}
	
}
