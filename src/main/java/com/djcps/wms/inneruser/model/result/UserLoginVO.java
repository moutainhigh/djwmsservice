package com.djcps.wms.inneruser.model.result;

import java.io.Serializable;

/**
 * 内部用户登录返回类
 * @author Chengw
 * @since 2017/12/4 14:58.
 */
public class UserLoginVO implements Serializable{

    /**
     * token
     */
    private String token;

    /**
     * msg
     */
    private String msg;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "UserLoginVO{" +
                "token='" + token + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
