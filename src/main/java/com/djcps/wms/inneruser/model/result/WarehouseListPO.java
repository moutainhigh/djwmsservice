package com.djcps.wms.inneruser.model.result;

import java.io.Serializable;

/**
 * 仓库对应类
 * @author wzy
 * @date 2018/4/20
 **/
public class WarehouseListPO implements Serializable{

    /**
     * 仓库id
     */
    private String warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 合作方id
     */
    private String partnerId;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

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
        return "WarehouseListPO{" +
                "warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", userId='" + userId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                '}';
    }
}
