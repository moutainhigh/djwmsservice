package com.djcps.wms.warehouse.model.area;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @title  前台传来仓库id
 * @author  wzy
 * @create  2017/12/20 13:42
 **/
public class AreaCode implements Serializable {

    private static final long serialVersionUID = -4019809043692166800L;

    /**
     * 仓库编码
     */
    @NotBlank
    private  String warehouseId;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public String toString() {
        return "AreaCode{" +
                "warehouseId='" + warehouseId + '\'' +
                '}';
    }
}
