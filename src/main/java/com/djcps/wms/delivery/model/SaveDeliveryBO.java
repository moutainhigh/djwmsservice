package com.djcps.wms.delivery.model;

import javax.validation.constraints.NotNull;

import com.djcps.wms.record.model.OrderOperationRecordPO;
import com.djcps.wms.record.model.TaskOperationRecordPO;

import java.io.Serializable;
import java.util.List;

/**
 * 完成单条订单提货任务
 * 
 * @author Chengw
 * @since 2018/1/31 07:52.
 */
public class SaveDeliveryBO implements Serializable {

    /**
     * 合作方号
     */
    @NotNull
    private String partnerId;

    /**
     * 合作方区域,就是区域拆分键
     */
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

    /**
     * 仓库ID
     */
    @NotNull
    private String warehouseId;

    /**
     * 库区ID
     */
    @NotNull
    private String warehouseAreaId;

    /**
     * 库位ID
     */
    @NotNull
    private String warehouseLocId;

    /**
     * 实际提货数量
     */
    @NotNull
    private Integer realDeliveryAmount;

    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作人ID
     */
    private String operatorId;
    /**
     * 运单编号
     */
    @NotNull
    private String wayBillId;
    /**
     * 操作记录信息
     */
    private List<OrderOperationRecordPO> list;
    /**
     * 操作记录信息
     */
    private TaskOperationRecordPO taskOperationRecordPO;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 库区名称
     */
    private String warehouseAreaName;
    /**
     * 库位名称
     */
    private String warehouseLocName;

    public String getWarehouseName() {
        return warehouseName;
    }

    public String getWarehouseAreaName() {
        return warehouseAreaName;
    }

    public String getWarehouseLocName() {
        return warehouseLocName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public void setWarehouseAreaName(String warehouseAreaName) {
        this.warehouseAreaName = warehouseAreaName;
    }

    public void setWarehouseLocName(String warehouseLocName) {
        this.warehouseLocName = warehouseLocName;
    }

    public TaskOperationRecordPO getTaskOperationRecordPO() {
        return taskOperationRecordPO;
    }

    public void setTaskOperationRecordPO(TaskOperationRecordPO taskOperationRecordPO) {
        this.taskOperationRecordPO = taskOperationRecordPO;
    }

    public List<OrderOperationRecordPO> getList() {
        return list;
    }

    public void setList(List<OrderOperationRecordPO> list) {
        this.list = list;
    }

    public String getPartnerArea() {
        return partnerArea;
    }

    public void setPartnerArea(String partnerArea) {
        this.partnerArea = partnerArea;
    }

    public String getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(String wayBillId) {
        this.wayBillId = wayBillId;
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

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseAreaId() {
        return warehouseAreaId;
    }

    public void setWarehouseAreaId(String warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
    }

    public String getWarehouseLocId() {
        return warehouseLocId;
    }

    public void setWarehouseLocId(String warehouseLocId) {
        this.warehouseLocId = warehouseLocId;
    }

    public Integer getRealDeliveryAmount() {
        return realDeliveryAmount;
    }

    public void setRealDeliveryAmount(Integer realDeliveryAmount) {
        this.realDeliveryAmount = realDeliveryAmount;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    @Override
    public String toString() {
        return "SaveDeliveryBO [partnerId=" + partnerId + ", partnerArea=" + partnerArea + ", deliveryId=" + deliveryId
                + ", orderId=" + orderId + ", warehouseId=" + warehouseId + ", warehouseAreaId=" + warehouseAreaId
                + ", warehouseLocId=" + warehouseLocId + ", realDeliveryAmount=" + realDeliveryAmount + ", operator="
                + operator + ", operatorId=" + operatorId + ", wayBillId=" + wayBillId + ", list=" + list
                + ", taskOperationRecordPO=" + taskOperationRecordPO + ", warehouseName=" + warehouseName
                + ", warehouseAreaName=" + warehouseAreaName + ", warehouseLocName=" + warehouseLocName + "]";
    }

}
