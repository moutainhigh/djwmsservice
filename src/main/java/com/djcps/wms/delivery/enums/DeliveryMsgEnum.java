package com.djcps.wms.delivery.enums;

import com.djcps.wms.commons.msg.MsgInterface;

/**
 * @author Chengw
 * @since 2018/2/1 13:48.
 */
public enum DeliveryMsgEnum implements MsgInterface {
    /**
     * 提货错误代码
     */
    DELIVERY_NOT_EXIT(870001,"获取提货信息失败"),
    ORDER_NOT_EXIT(870002,"获取提货订单信息失败");

    private int code;

    private String msg;

    DeliveryMsgEnum(int code, String msg) {
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
