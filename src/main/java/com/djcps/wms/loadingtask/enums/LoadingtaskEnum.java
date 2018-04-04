package com.djcps.wms.loadingtask.enums;

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
	WAYBILLID_ERROR(880026,"运单号错误"),
	/**
	 * 生成出库单失败
	 */
	OUTORDER_FAIL(880025,"生成出库单失败")
	;
	
	 private int code;

    private String msg;

    LoadingtaskEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
	@Override
	public int getCode() {
		return 0;
	}

	@Override
	public String getMsg() {
		return null;
	}

}
