package com.djcps.wms.delivery.model;

import com.djcps.wms.commons.base.BaseBO;

import java.io.Serializable;
import java.util.List;

/**
 * 订单号list<String>对象
 * @company:djwms
 * @author:zdx
 * @date:2018年3月19日
 */
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
