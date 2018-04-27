package com.djcps.wms.inneruser.model.result;

/**
 * 内部用户token
 * 
 * @author wyb
 * @since 2018/4/26 16:09.
 */
public class UserTokenVO {
    /**
     * 用户token
     */
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserTokenVO [token=" + token + "]";
    }

}
