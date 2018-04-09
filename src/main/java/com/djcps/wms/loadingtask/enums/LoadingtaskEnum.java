package com.djcps.wms.loadingtask.enums;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.msg.MsgInterface;
/**
 * 
 * @author ldh
 *
 */
public enum LoadingtaskEnum implements MsgInterface {
	/**
	 * 运单号错误
	 */
	WAYBILLID_ERROR(6,"运单号错误,获取不到信息"),
	/**
	 * 
	 */
	GET_ORDERDETAIL_FAIL(7,"获取订单详情失败"),
	/**
	 * 生成出库单失败
	 */
	OUTORDER_FAIL(8,"生成出库单失败")
	;
	
	 private int code;

    private String msg;

    LoadingtaskEnum(int code,String msg){
    	this.code = AppConstant.WMS_MSG_ENUM_PREFIX+AppConstant.WMS_MODULE_LOADINGTASK_ENUM_PREFIX+code;
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
