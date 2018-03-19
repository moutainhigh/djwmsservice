package com.djcps.wms.loadingtable.model;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotBlank;
import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;

/**
 * @title:启用禁用装车台对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
public class IsUseLoadingTableBO extends BaseUpdateAndDeleteBO implements Serializable{

	private static final long serialVersionUID = 8000121247085978929L;
	
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
		return "IsUseLoadingTableBO [id=" + id + "]";
	}
	
}
