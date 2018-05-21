package com.djcps.wms.stock.model;

import java.util.List;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 根据条件批量获取订单信息
 * 
 * @author wyb
 *
 */
public class GetorderInfoListBO extends BaseAddBO {

    /**
     * 
     */
    private static final long serialVersionUID = -2997748065752803950L;
    /**
     * 仓库编号
     */
    private String warehouseId;
    /**
     * 订单列表
     */
    private List<String> list;

    public String getWarehouseId() {
        return warehouseId;
    }

    public List<String> getList() {
        return list;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "getorderInfoListBO [warehouseId=" + warehouseId + ", list=" + list + "]";
    }

}
