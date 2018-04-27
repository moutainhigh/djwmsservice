package com.djcps.wms.workrecords.enums;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.msg.MsgInterface;

/**
* @author panyang
* @version 创建时间：2018年4月18日 下午2:06:40
* 类说明
*/
public enum WorkRecordEnums implements  MsgInterface {
	
	/**
	 * 获取工作记录失败
	 */
	GET_WORK_RECORD_FAIL(1,"根据操作类型获取工作记录信息失败"),
	/**
	 * 获取工作记录详情失败
	 */
	GET_WORKDETAIL_FAIL(2,"向工作记录索要工作记录详情失败"),
	;
	
	 private int code;

	 private String msg;
	
	 WorkRecordEnums(int code,String msg) {
		 this.code = AppConstant.WMS_MSG_ENUM_PREFIX+AppConstant.WMS_OUTORDER_ENUM_PREFIX+code;
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



