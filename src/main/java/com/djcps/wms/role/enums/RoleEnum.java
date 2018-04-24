package com.djcps.wms.role.enums;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.msg.MsgInterface;
/**
 * 
 * @author wyb
 *
 */
public enum RoleEnum implements MsgInterface{
    /**
     * 错误代码
     */
    USER_BUSY(1," 该角色的用户还在工作中,不可以进行删除操作"),
    ;

    private int code;

    private String msg;

    RoleEnum(int code,String msg){
        this.code = AppConstant.WMS_MSG_ENUM_PREFIX + AppConstant.WMS_ROLE_ENUM_PREFIX + code;
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
