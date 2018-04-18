package com.djcps.wms.delivery.enums;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.msg.MsgInterface;

/**
 * @author Chengw
 * @since 2018/2/1 13:48.
 */
public enum DeliveryMsgEnum implements MsgInterface {
    /**
     * 提货错误代码
     */
    DELIVERY_NOT_EXIT(1,"获取提货信息失败"),
    ORDER_NOT_EXIT(2,"获取提货订单信息失败");

    private int code;

    private String msg;

    DeliveryMsgEnum(int code, String msg) {
        this.code = AppConstant.WMS_MSG_ENUM_PREFIX + AppConstant.WMS_DELIVERY_ENUM_PREFIX + code;
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
