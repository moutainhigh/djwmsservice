package com.djcps.wms.abnormal.enums;


import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.msg.MsgInterface;

/**
 * @title:系统参数枚举
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
public enum AbnormalMsgEnum implements MsgInterface {
	
    /**
     * OMS处修改订单异常状态标志失败!!!
     */
    STOCK_UPDATE_SPLIT_ORDER_STATUS_ERROR(1,"OMS处修改订单异常状态标志失败!!!");

    private int code;
    
    private String msg;

    AbnormalMsgEnum(Integer code, String msg) {
        this.code = AppConstant.WMS_MSG_ENUM_PREFIX+AppConstant.WMS_ABNORMAL_ENUM_PREFIX+code;
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
