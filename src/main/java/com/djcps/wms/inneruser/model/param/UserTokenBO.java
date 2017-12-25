package com.djcps.wms.inneruser.model.param;

import com.djcps.wms.commons.base.BaseBO;

import java.io.Serializable;

/**
 * 用户token
 * @author Chengw
 * @since 2017/12/4 17:18.
 */
public class UserTokenBO extends BaseBO implements Serializable{

    /**
     * 用户token
     */
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
