package com.djcps.wms.commons.enums;

import com.djcps.wms.commons.msg.MsgInterface;

/**
 * @title:系统参数枚举
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
public enum SysMsgEnum implements MsgInterface {
    
    /**
     * 操作成功
     */
    OPS_SUCCESS(880001, "操作成功"),
    
    /**
     * 操作失败
     */
    OPS_FAILURE(880002, "操作失败"),
    
    /**
     * 系统异常，服务器报错
     */
    SYS_EXCEPTION(880003, "系统异常,请稍后在试"),
    
    /**
     * token过去或未登入
     */
    NOT_LOGIN(880004, "抱歉，您还未登录，或token已过期，请登录后再试");
    
    private int code;
    
    private String msg;

   SysMsgEnum(int code, String msg) {
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
