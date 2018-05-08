package com.djcps.wms.record.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 订单操作记录实体类
 * 
 * @author ztw
 * @version 1.0
 * @since 2018-4-14
 */
public class OrderOperationRecordPO extends BaseBO implements Serializable {

    /**
     * 唯一标识
     */
    private String id;

    /**
     * 合作方Id
     */
    @NotBlank
    private String partnerId;

    /**
     * 合作方区域
     */
    @NotBlank
    private String partnerArea;
    /**
     * 关联id
     */
    @NotBlank
    private String relativeId;

    /**
     * 业务关联名称
     */
    private String relativeName;

    public String getRelativeName() {
        return relativeName;
    }

    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }

    /**
     * 操作人id
     */
    @NotBlank
    private String operatorId;
    /**
     * 操作人
     */
    @NotBlank
    private String operator;
    /**
     * 操作类型
     */
    @NotBlank
    private String operationType;

    /**
     * 订单类型
     */
    @NotBlank
    private String orderType;

    /**
     * 楞型
     */
    @NotBlank
    private Integer fluteType;

    /**
     * 仓库id
     */
    @NotBlank
    private String warehouseId;

    /**
     * 库区Id
     */
    private String warehouseAreaId;

    /**
     * 库位Id
     */
    private String warehouseLocId;
    /**
     * 操作面积
     */
    @NotBlank
    private String area;

    /**
     * 数量
     */
    @NotBlank
    private String amount;
    /**
     * 事件
     */
    @NotBlank
    private String event;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

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

    public String getPartnerId() {
        return partnerId;
    }

    public String getPartnerArea() {
        return partnerArea;
    }

    public String getRelativeId() {
        return relativeId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getOperator() {
        return operator;
    }

    public String getOperationType() {
        return operationType;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public String getWarehouseAreaId() {
        return warehouseAreaId;
    }

    public String getWarehouseLocId() {
        return warehouseLocId;
    }

    public String getArea() {
        return area;
    }

    public String getAmount() {
        return amount;
    }

    public String getEvent() {
        return event;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public String getUpdateTimeStart() {
        return updateTimeStart;
    }

    public String getUpdateTimeEnd() {
        return updateTimeEnd;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public void setPartnerArea(String partnerArea) {
        this.partnerArea = partnerArea;
    }

    public void setRelativeId(String relativeId) {
        this.relativeId = relativeId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setWarehouseAreaId(String warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
    }

    public void setWarehouseLocId(String warehouseLocId) {
        this.warehouseLocId = warehouseLocId;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public void setUpdateTimeStart(String updateTimeStart) {
        this.updateTimeStart = updateTimeStart;
    }

    public void setUpdateTimeEnd(String updateTimeEnd) {
        this.updateTimeEnd = updateTimeEnd;
    }

    public Integer getFluteType() {
        return fluteType;
    }

    public void setFluteType(Integer fluteType) {
        this.fluteType = fluteType;
    }

    @Override
    public String toString() {
        return "OrderOperationRecordPO [id=" + id + ", partnerId=" + partnerId + ", partnerArea=" + partnerArea
                + ", relativeId=" + relativeId + ", relativeName=" + relativeName + ", operatorId=" + operatorId
                + ", operator=" + operator + ", operationType=" + operationType + ", orderType=" + orderType
                + ", fluteType=" + fluteType + ", warehouseId=" + warehouseId + ", warehouseAreaId=" + warehouseAreaId
                + ", warehouseLocId=" + warehouseLocId + ", area=" + area + ", amount=" + amount + ", event=" + event
                + ", createTime=" + createTime + ", updateTime=" + updateTime + ", createTimeStart=" + createTimeStart
                + ", createTimeEnd=" + createTimeEnd + ", updateTimeStart=" + updateTimeStart + ", updateTimeEnd="
                + updateTimeEnd + "]";
    }

}
