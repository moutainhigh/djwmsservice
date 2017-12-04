package com.djcps.wms.provider.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseParam;

public class ProvinceCityAreaCodeBo extends BaseParam implements Serializable{
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ProvinceCityAreaCodeBo [code=" + code + "]";
	}

}
