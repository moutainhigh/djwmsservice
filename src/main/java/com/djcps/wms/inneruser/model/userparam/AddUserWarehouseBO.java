package com.djcps.wms.inneruser.model.userparam;

/**
 * 新增用户仓库信息
 * @author  wzy
 * @param
 * @return
 * @date  2018/4/16 10:40
 **/
public class AddUserWarehouseBO {
    /**
     * 用户id
     */
    private String userId;

    private String partnerId;

    private String warehouseId;

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

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public String toString() {
        return "AddUserWarehouseBO{" +
                "userId='" + userId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                '}';
    }
}
