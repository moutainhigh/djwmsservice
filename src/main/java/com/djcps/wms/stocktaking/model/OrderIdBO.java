package com.djcps.wms.stocktaking.model;

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
