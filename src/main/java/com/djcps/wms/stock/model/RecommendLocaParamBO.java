package com.djcps.wms.stock.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 获取库区推荐对象
 * 
 * @company:djwms
 * @author:zdx
 * @date:2017年12月20日
 */
public class RecommendLocaParamBO implements Serializable {

    private static final long serialVersionUID = -7237160364347242371L;

    /**
     * 合作方id
     */
    @NotBlank
    private String partnerId;

    /**
     * 街道code
     */
    private String streetCode;
    /**
     * 仓库编号
     */
    private String warehouseId;

    public String getPartnerId() {
        return partnerId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    @Override
    public String toString() {
        return "RecommendLocaParamBO [partnerId=" + partnerId + ", streetCode=" + streetCode + ", warehouseId="
                + warehouseId + "]";
    }

}
