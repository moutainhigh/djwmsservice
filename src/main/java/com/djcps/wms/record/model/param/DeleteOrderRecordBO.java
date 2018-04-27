package com.djcps.wms.record.model.param;

import java.io.Serializable;


import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 
 * 
 * @author py
 *
 */

public class DeleteOrderRecordBO extends BaseBO implements Serializable{
    

    /**
     * 唯一标识
     */
    private String id;
    
    /**
     * 合作方Id
     */
    private String partnerId;
    
    /**
     * 合作方区域
     */
    private String partnerArea;
    
    /**
     * 关联id
     */
    private String relativeId;
    /**
     * 操作人id
     */
    private String operatorId;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作类型
     */
    private String operationType;
    
    /**
     * 操作类型
     */
    private String orderType;
    
    /**
     * 操作类型
     */
    private String fluteType;
    /**
     * 仓库ID
     */
    private String warehouseId;
    
    /**
     * 创建时间搜索条件 start
     */
    private String createTimeStart;
    
    /**
     * 创建时间搜索条件 end
     */
    private String createTimeEnd;
    
    /**
     * 更新时间搜索条件 start
     */
    private String updateTimeStart;
    
    /**
     * 更新时间搜索条件 end
     */
    private String updateTimeEnd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerArea() {
        return partnerArea;
    }

    public void setPartnerArea(String partnerArea) {
        this.partnerArea = partnerArea;
    }

    public String getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(String relativeId) {
        this.relativeId = relativeId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getUpdateTimeStart() {
        return updateTimeStart;
    }

    public void setUpdateTimeStart(String updateTimeStart) {
        this.updateTimeStart = updateTimeStart;
    }

    public String getUpdateTimeEnd() {
        return updateTimeEnd;
    }

    public void setUpdateTimeEnd(String updateTimeEnd) {
        this.updateTimeEnd = updateTimeEnd;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getFluteType() {
        return fluteType;
    }

    public void setFluteType(String fluteType) {
        this.fluteType = fluteType;
    }

    @Override
    public String toString() {
        return "DeleteOrderRecordBO [id=" + id + ", partnerId=" + partnerId + ", partnerArea=" + partnerArea
                + ", relativeId=" + relativeId + ", operatorId=" + operatorId + ", operator=" + operator
                + ", operationType=" + operationType + ", orderType=" + orderType + ", fluteType=" + fluteType
                + ", warehouseId=" + warehouseId + ", createTimeStart=" + createTimeStart + ", createTimeEnd="
                + createTimeEnd + ", updateTimeStart=" + updateTimeStart + ", updateTimeEnd=" + updateTimeEnd + "]";
    }
    
}
