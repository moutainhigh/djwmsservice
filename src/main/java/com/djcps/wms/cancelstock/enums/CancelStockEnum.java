package com.djcps.wms.cancelstock.enums;

/**
 * 退库枚举
 * @company:djwms
 * @author:zdx
 * @date:2018年3月21日
 */
public enum CancelStockEnum {
	/**
	 * 分割符
	 */
	STRING_SEPARATOR("-"),
	
	WAREHOUSEID_ERROR("请退回到原来仓库");
	
	private String value;
	
	CancelStockEnum(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
