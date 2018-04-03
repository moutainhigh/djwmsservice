package com.djcps.wms.cancelstock.enums;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.msg.MsgInterface;

/**
 * @title:系统参数枚举
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
public enum CancelStockMsgEnum implements MsgInterface {
	
	ORDER_IS_NULL(1, "查无此订单")
	;

    private int code;
    
    private String msg;

   CancelStockMsgEnum(Integer code, String msg) {
        this.code = AppConstant.WMS_MSG_ENUM_PREFIX+AppConstant.WMS_MODULE_CANCELSTOCK_ENUM_PREFIX+code;
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
