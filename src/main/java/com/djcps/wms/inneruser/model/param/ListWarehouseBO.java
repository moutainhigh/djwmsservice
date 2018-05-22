package com.djcps.wms.inneruser.model.param;

import java.io.Serializable;
import java.util.List;

/**
 * 获取所属仓库 参数类
 * @author wzy
 * @date 2018/4/23
 **/
public class ListWarehouseBO implements Serializable {

    /**
     * 用户id集合
     */
    private List<String> userIds;

    /**
     * 合作方id
     */
    private String partnerId;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return "ListWarehouseBO{" +
                "userIds=" + userIds +
                ", partnerId='" + partnerId + '\'' +
                '}';
    }
}
