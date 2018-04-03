package com.djcps.wms.warehouse.enums;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.msg.MsgInterface;

/**
 * @title:系统参数枚举
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
public enum WarehouseMsgEnum implements MsgInterface {

	/**
	 * 编码错误,请重新获取
	 */
	CODE_ERROE(1, "编码失效,请重刷新页面"),

	/**
	 * 编码新增或删除有误,请联系管理员!!!
	 */
	DELETE_CODE_ERROE(2, "编码新增或删除有误,请联系管理员!!!");

	private int code;

	private String msg;

	WarehouseMsgEnum(int code, String msg) {
		this.code = AppConstant.WMS_MODULE_SYS_ENUM_PREFIX + AppConstant.WMS_MODULE_WAREHOUSE_ENUM_PREFIX + code;
		this.msg = msg;
	}

	@Override
	public int getCode() {
		return code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMsg() {
		return msg;
	}

}
