package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.record.model.OrderOperationRecordPO;

/**
 * 取消订单
 * 
 * @company:djwms
 * @author:zdx
 * @date:2018年1月23日
 */
public class CancelAllocationBO extends BaseAddBO implements Serializable {

    private static final long serialVersionUID = 7010231338345551359L;
    /**
     * 所有的订单号
     */
    @NotEmpty
    private List<String> orderIds;
    /**
     * 运单号
     */
    @NotBlank
    private String waybillId;
    /**
     * 提货单号
     */
    @NotEmpty
    private List<String> deliveryId;

    /**
     * 提货单的确认状态
     */
    private String deliveryIdEffect;

    /**
     * 智能配货id
     */
    @NotBlank
    private String allocationId;

    /**
     * 智能配货确认状态
     */
    private String allocationIdEffect;

    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 操作记录信息
     */
    private List<OrderOperationRecordPO> list;

    public List<OrderOperationRecordPO> getList() {
        return list;
    }

    public void setList(List<OrderOperationRecordPO> list) {
        this.list = list;
    }

    public String getDeliveryIdEffect() {
        return deliveryIdEffect;
    }

    public void setDeliveryIdEffect(String deliveryIdEffect) {
        this.deliveryIdEffect = deliveryIdEffect;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAllocationIdEffect() {
        return allocationIdEffect;
    }

    public void setAllocationIdEffect(String allocationIdEffect) {
        this.allocationIdEffect = allocationIdEffect;
    }

    public List<String> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<String> orderIds) {
        this.orderIds = orderIds;
    }

    public String getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(String waybillId) {
        this.waybillId = waybillId;
    }

    public List<String> getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(List<String> deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(String allocationId) {
        this.allocationId = allocationId;
    }

    @Override
    public String toString() {
        return "CancelAllocationBO [orderIds=" + orderIds + ", waybillId=" + waybillId + ", deliveryId=" + deliveryId
                + ", deliveryIdEffect=" + deliveryIdEffect + ", allocationId=" + allocationId + ", allocationIdEffect="
                + allocationIdEffect + ", orderStatus=" + orderStatus + ", list=" + list + "]";
    }

}
