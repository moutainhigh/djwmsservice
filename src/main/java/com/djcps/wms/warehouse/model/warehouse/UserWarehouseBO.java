package com.djcps.wms.warehouse.model.warehouse;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 用户所属仓库 参数类型
 * @author Chengw
 * @version 1.0.0
 * @since 2018/5/18 13:53.
 */
public class UserWarehouseBO implements Serializable {

    @NotBlank
    private String partnerId;

    private String userId;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserWarehouseBO{" +
                "partnerId='" + partnerId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
