package com.djcps.wms.cancelstock.model.param;


import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;
import com.djcps.wms.commons.model.PartnerInfoBO;

/**
 * 提货员就是退库远
 * @company:djwms
 * @author:zdx
 * @date:2018年3月19日
 */
public class PickerIdBO extends PartnerInfoBO implements Serializable{
	
	private static final long serialVersionUID = -243263474101809339L;
	
	/**
	 * 提货员id
	 */
	@NotBlank
	private String pickerId;

	public String getPickerId() {
		return pickerId;
	}

	public void setPickerId(String pickerId) {
		this.pickerId = pickerId;
	}

	@Override
	public String toString() {
		return "PickerId [pickerId=" + pickerId + "]";
	}
	
}
