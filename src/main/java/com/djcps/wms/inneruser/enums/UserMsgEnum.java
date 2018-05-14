package com.djcps.wms.inneruser.enums;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.msg.MsgInterface;

/**
 * 用户模块错误返回枚举
 * @author wzy
 * @date 2018/4/20
 **/
public enum UserMsgEnum implements MsgInterface{
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(1," 用户不存在"),

    USER_BUSY(2," 用户忙碌,无法删除"),

    DEL_USER_FAIL(3," 删除用户失败"),

    SAVE_USER_FAIL(4," 保存用户信息失败"),

    GET_USER_FAIL(5," 获取用户关联信息失败"),

    NULL_RESULT(6," 请求结果为空"),

    ROLE_TYPE_NULL(7," 无用户类型"),
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
