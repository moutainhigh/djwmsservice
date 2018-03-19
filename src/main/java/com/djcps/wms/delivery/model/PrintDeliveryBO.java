package com.djcps.wms.delivery.model;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 提货单 打印次数 参数类
 * @author Chengw
 * @since 2018/1/31 08:13.
 */
public class PrintDeliveryBO extends BaseBO {

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
        return "PrintDeliveryBO{" +
                "partnerId='" + partnerId + '\'' +
                ", deliveryId='" + deliveryId + '\'' +
                '}';
    }
}
