package com.djcps.wms.delivery.model;

import com.djcps.wms.commons.base.BaseBO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author Chengw
 * @since 2018/2/1 13:14.
 */
public class DeliveryOrderDetailBO extends BaseBO {

    /**
     * 合作方号
     */
    @NotNull
    private String partnerId;

    @NotEmpty
    private String partnerArea;

    /**
     * 提货单号
     */
    @NotNull
    private String deliveryId;

    /**
     * 订单号
     */
    @NotNull
    private String orderId;

    public String getPartnerArea() {
		return partnerArea;
	}

	public void setPartnerArea(String partnerArea) {
		this.partnerArea = partnerArea;
	}

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

	@Override
	public String toString() {
		return "DeliveryOrderDetailBO [partnerId=" + partnerId + ", partnerArea=" + partnerArea + ", deliveryId="
				+ deliveryId + ", orderId=" + orderId + "]";
	}

}
