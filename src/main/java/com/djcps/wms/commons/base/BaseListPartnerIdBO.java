package com.djcps.wms.commons.base;

import java.io.Serializable;

/**
 * 继承BaseList带partnerId的分页
 * @company:djwms
 * @author:zdx
 * @date:2018年2月4日
 */
public class BaseListPartnerIdBO extends BaseListBO implements Serializable{

	private static final long serialVersionUID = 6528337687362155932L;
    
    /**
     * 合作方id
     */
    private String partnerId;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	@Override
	public String toString() {
		return "BaseListPartnerIdBO [partnerId=" + partnerId + "]";
	}
    
}
