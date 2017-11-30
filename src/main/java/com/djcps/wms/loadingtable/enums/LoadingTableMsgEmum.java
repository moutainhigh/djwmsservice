package com.djcps.wms.loadingtable.enums;

import com.djcps.wms.commons.msg.MsgInterface;

public enum LoadingTableMsgEmum implements MsgInterface{
	
	LENGTH_BEYOND(880101, "字符长度不符合标准"),
	
	PARAM_NULL(880102,"参数不允许为空");
    
    private int code;
    
    private String msg;

    LoadingTableMsgEmum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
