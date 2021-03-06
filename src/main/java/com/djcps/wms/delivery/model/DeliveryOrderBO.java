package com.djcps.wms.delivery.model;

import com.djcps.wms.commons.base.BaseBO;

/**
 * @author Chengw
 * @since 2018/2/1 11:18.
 */
public class DeliveryOrderBO extends BaseBO{

    /**
     * 合作方号
     */
    private String partnerId;
    
    /**
     * 合作方区域
     */
    private String partnerArea;

    /**
     * 提货员id
     */
    private String pickerId;

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

    public String getPickerId() {
        return pickerId;
    }

    public void setPickerId(String pickerId) {
        this.pickerId = pickerId;
    }

	@Override
	public String toString() {
		return "DeliveryOrderBO [partnerId=" + partnerId + ", partnerArea=" + partnerArea + ", pickerId=" + pickerId
				+ "]";
	}

}
