package com.djcps.wms.delivery.model;

import com.djcps.wms.commons.base.BaseListBO;

/**
 * 提货单订单列表 参数类
 * @author Chengw
 * @since 2018/1/31 07:57.
 */
public class ListDeliveryOrderBO extends BaseListBO {


    /**
     * 合作方号
     */
    private String partnerId;

    /**
     * 提货单号
     */
    private String deliveryId;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    @Override
    public String toString() {
        return "ListDeliveryOrderBO{" +
                "partnerId='" + partnerId + '\'' +
                ", deliveryId='" + deliveryId + '\'' +
                '}';
    }
}
