package com.djcps.wms.inneruser.model.param;

import com.djcps.wms.commons.base.BaseBO;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 内部用户登录 参数类
 * @author Chengw
 * @since 2017/12/4 16:56.
 */
public class InnerUserLoginBO extends BaseBO implements Serializable{

    /***
     * 用户名称
     */
    @NotBlank
    private String userName;

    /**
     * 密码
     */
    @NotBlank
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
