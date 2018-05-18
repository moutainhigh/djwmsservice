package com.djcps.wms.inneruser.model.param;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 删除用户关联信息参数类
 * @author wzy
 * @date 2018/4/13
 **/
public class UserBO implements Serializable{

    /**
     * 用户id
     */
    @NotBlank
    private String userId;

    private String partnerId;



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }


    @Override
    public String toString() {
        return "UserBO{" +
                "userId='" + userId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                '}';
    }
}
