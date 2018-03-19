package com.djcps.wms.loadingtable.enums;

import com.djcps.wms.commons.msg.MsgInterface;

/**
 * @title:装车台枚举
 * @description:装车台枚举
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
public enum LoadingTableMsgEnum implements MsgInterface{
	
	/**
	 * 字符长度不符合标准
	 */
	LENGTH_BEYOND(880101, "字符长度不符合标准");

    private int code;
    
    private String msg;

    LoadingTableMsgEnum(int code, String msg) {
        this.code = code;
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
