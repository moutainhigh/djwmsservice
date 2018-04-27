package com.djcps.wms.inneruser.model.userparam;

/**
 * 仓库信息列表类
 * @author:wzy
 * @date:2018/4/23
 **/
public class WarehouseAndListBO {
    private String userId;

    private String warehouseName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    @Override
    public String toString() {
        return "WarehouseAndListBO{" +
                "userId='" + userId + '\'' +
                ", WarehouseName='" + warehouseName + '\'' +
                '}';
    }
}
