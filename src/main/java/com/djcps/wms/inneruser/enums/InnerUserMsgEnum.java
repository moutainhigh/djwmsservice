package com.djcps.wms.inneruser.enums;

import com.djcps.wms.commons.msg.MsgInterface;

/**
 * 内部用户 消息返回类
 * @author Chengw
 * @since 2017/12/4 17:01.
 */
public enum InnerUserMsgEnum implements MsgInterface{

    /**
     * 错误代码
     */

    TOEKN_NULL(810001,"token 未发现");

    private int code;

    private String msg;

    InnerUserMsgEnum(int code,String msg){
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
