package com.djcps.wms.inneruser.model.result;

import java.io.Serializable;

/**
 * 用户token 置换类
 * @author Chengw
 * @since 2017/12/4 15:27.
 */
public class UserExchangeTokenVO implements Serializable{

    private String token;

    public UserExchangeTokenVO(){}

    public UserExchangeTokenVO(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserExchangeTokenVO{" +
                "token='" + token + '\'' +
                '}';
    }
}
