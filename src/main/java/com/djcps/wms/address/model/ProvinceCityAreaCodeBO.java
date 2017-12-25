package com.djcps.wms.address.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseParam;

/**
 * 获取省市区对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月8日
 */
public class ProvinceCityAreaCodeBO extends BaseParam implements Serializable{
	
	private static final long serialVersionUID = -4587232100549379283L;
	
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
