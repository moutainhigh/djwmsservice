package com.djcps.wms.inneruser.model.result;

import java.io.Serializable;

/**
 * 用户code返回类
 * @author Chengw
 * @since 2017/12/4 14:26.
 */
public class UserCodeVO implements Serializable {

    /**
     * 用户code
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UserCodeVO{" +
                "code='" + code + '\'' +
                '}';
    }
}
