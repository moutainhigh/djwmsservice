package com.djcps.wms.stock.model;

import java.io.Serializable;
import java.util.List;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.commons.base.BaseBO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 获取库区推荐对象
 * 
 * @company:djwms
 * @author:zdx
 * @date:2017年12月20日
 */
public class RecommendLocaBO extends BaseBO implements Serializable {

    private static final long serialVersionUID = -7237160364347242371L;

    /**
     * 经纬度
     */
    @NotBlank
    private String location;

    /**
     * 合作方id
     */
    @NotBlank
    private String partnerId;
    /**
     * 仓库编号
     */
    @NotBlank
    private String warehouseId;

    private List<RecommendLocaParamBO> param;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public List<RecommendLocaParamBO> getParam() {
        return param;
    }

    public void setParam(List<RecommendLocaParamBO> param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "RecommendLocaBO [location=" + location + ", partnerId=" + partnerId + ", warehouseId=" + warehouseId
                + ", param=" + param + "]";
    }

}
