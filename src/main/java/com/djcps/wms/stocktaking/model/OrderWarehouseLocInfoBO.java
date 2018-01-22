package com.djcps.wms.stocktaking.model;
import com.djcps.wms.commons.constant.AppConstant;

import java.util.List;

public class OrderWarehouseLocInfoBO {
    private String version;

    private List<OrderIdBO> orderIds;

    public String getVersion() {
        return version;
    }

    public void setVersion() {
        this.version = AppConstant.DEFAULT_VERSION;
    }


    public List<OrderIdBO> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<OrderIdBO> orderIds) {
        this.orderIds = orderIds;
    }
}
