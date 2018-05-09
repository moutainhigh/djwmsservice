package com.djcps.wms.record.model.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.base.BaseListBO;

/**
 * 操作记录关联id
 * @company:djwms
 * @author:zdx
 * @date:2018年3月8日
 */
public class RelativeIdBO extends BaseListBO implements Serializable {
	
	private static final long serialVersionUID = -3809499474310430921L;
	
	/**
	 * 操作记录关联id
	 */
	@NotBlank
	private String relativeId;

	public String getRelativeId() {
		return relativeId;
	}

	public void setRelativeId(String relativeId) {
		this.relativeId = relativeId;
	}

	@Override
	public String toString() {
		return "RelativeIdBO [relativeId=" + relativeId + "]";
	}
	
}
