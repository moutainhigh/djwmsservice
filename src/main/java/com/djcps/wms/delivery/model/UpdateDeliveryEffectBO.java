package com.djcps.wms.delivery.model;

import java.io.Serializable;
import java.util.List;

import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

/**
 * 删除订单信息 参数类
 * 
 * @author wyb
 * @since 2018/3/13
 *
 */
public class UpdateDeliveryEffectBO extends PartnerInfoBO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6029712845147235071L;
    /**
     * 提货单号
     */
    private String deliveryId;
    /**
     * 订单编号集合
     */
    private List<String> orderIds;
    /**
     * 运单编号
     */
    private String wayBillId;
    
    public String getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(String wayBillId) {
        this.wayBillId = wayBillId;
    }

    public List<String> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<String> orderIds) {
        this.orderIds = orderIds;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

	@Override
	public String toString() {
		return "UpdateDeliveryEffectBO [deliveryId=" + deliveryId + ", orderIds=" + orderIds + ", wayBillId="
				+ wayBillId + "]";
	}

}
