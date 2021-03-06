package com.djcps.wms.inneruser.model.param;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 用户修改密码 参数类
 * @author Chengw
 * @since 2017/12/5 15:11.
 */
public class InnerUserChangePasswordBO  implements Serializable {

    private String token;

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "InnerUserChangePasswordBO{" +
                "token='" + token + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
