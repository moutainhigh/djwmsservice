package com.djcps.wms.warehouse.model.location;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @title 获取库位对象
 * @author  wzy
 * @create  2017/12/21 17:01
 **/
public class LocationBo implements Serializable {

    private static final long serialVersionUID = 4080342799779890661L;

    /**
     * 仓库编码
     */
    @NotBlank
    private  String warehouseId;

    /**
     * 库区编码
     */
    @NotBlank
    private String warehouseAreaId;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseAreaId() {
        return warehouseAreaId;
    }

    public void setWarehouseAreaId(String warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
    }

    @Override
    public String toString() {
        return "LocationBo{" +
                "warehouseId='" + warehouseId + '\'' +
                ", warehouseAreaId='" + warehouseAreaId + '\'' +
                '}';
    }
}
