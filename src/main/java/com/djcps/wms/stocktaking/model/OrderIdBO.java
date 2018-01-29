package com.djcps.wms.stocktaking.model;

/**
 * orderid实体类
 * @author:wzy
 * @company:djwms
 * @create:2018/1/26
 **/
public class OrderIdBO {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderIdBO{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
