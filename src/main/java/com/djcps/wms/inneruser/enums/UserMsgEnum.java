package com.djcps.wms.inneruser.enums;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.msg.MsgInterface;

public enum UserMsgEnum implements MsgInterface{
    USER_NOT_EXIET(1," 用户不存在"),

    USER_BUSY(2," 用户忙碌,无法删除"),

    DEL_USER_FAIL(3," 用户忙碌,无法删除"),

    SAVE_USER_FAIL(4," 保存用户信息失败"),
    ;

    private int code;

    private String msg;

    UserMsgEnum(Integer code,String msg) {
        this.code = AppConstant.WMS_MSG_ENUM_PREFIX+AppConstant.WMS_USER_ENUM_PREFIX+code;
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

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
