package com.djcps.wms.delivery.model;

import java.io.Serializable;
import java.util.List;

import com.djcps.wms.commons.base.BaseBO;

public class OrderInfoBO extends BaseBO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3817138678724901168L;
    /**
     * 订单编号集合
     */
    private List<String> orderIds;

    public List<String> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<String> orderIds) {
        this.orderIds = orderIds;
    }

    @Override
    public String toString() {
        return "OrderInfoBO [orderIds=" + orderIds + "]";
    }

}
