package com.djcps.wms.outorder.enums;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.msg.MsgInterface;
/**
 * 
 * @author ldh
 *
 */
public enum OutOrderEnums implements MsgInterface{
	/**
	 * 获取订单数组失败
	 */
	GET_ORDER_ID_FAIL(1,"根据出库单编号获取订单编号数组失败"),
	/**
	 * 获取订单详情失败
	 */
	GET_ORDERDETAIL_FAIL(2,"向订单服务获取订单详情失败"),
	;
	
	 private int code;

	 private String msg;
	
	OutOrderEnums(int code,String msg) {
		 this.code = AppConstant.WMS_MSG_ENUM_PREFIX+AppConstant.WMS_MODULE_OUTORDER_ENUM_PREFIX+code;
	     this.msg = msg;
	}
	
	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}
	
}
