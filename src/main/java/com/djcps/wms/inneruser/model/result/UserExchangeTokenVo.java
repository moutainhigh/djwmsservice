package com.djcps.wms.inneruser.model.result;

/**
 * 用户token 置换类
 * @author Chengw
 * @since 2017/12/4 15:27.
 */
public class UserExchangeTokenVo{

    private String token;

    public UserExchangeTokenVo(){}

    public UserExchangeTokenVo(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}