package com.djcps.wms.record.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.djcps.wms.commons.base.BaseBO;


/**
 * 任务操作记录实体类
 * 
 * @author ztw
 * @version 1.0
 * @since 2018-4-14
 */
public class TaskOperationRecordPO extends BaseBO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8510151872447609673L;
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
     * 仓库ID
     */
    @NotBlank
    private String warehouseId;
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
     * 创建时间搜索条件 start
     */
    private String createTimeStart;
    
    /**
     * 创建时间搜索条件 end
     */
    private String createTimeEnd;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
    
    /**
     * 更新时间搜索条件 start
     */
    private String updateTimeStart;
    
    /**
     * 更新时间搜索条件 end
     */
    private String updateTimeEnd;
    
    public TaskOperationRecordPO() {
        super();
    }
    public TaskOperationRecordPO(String partnerId, String partnerArea, String relativeId, String operatorId,
            String operator, String operationType, String warehouseId, String event) {
        super();
        this.partnerId = partnerId;
        this.partnerArea = partnerArea;
        this.relativeId = relativeId;
        this.operatorId = operatorId;
        this.operator = operator;
        this.operationType = operationType;
        this.warehouseId = warehouseId;
        this.event = event;
    }
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
    public String getEvent() {
        return event;
    }
    public void setEvent(String event) {
        this.event = event;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
    @Override
    public String toString() {
        return "TaskOperationRecordPO [id=" + id + ", partnerId=" + partnerId + ", partnerArea=" + partnerArea
                + ", relativeId=" + relativeId + ", operatorId=" + operatorId + ", operator=" + operator
                + ", operationType=" + operationType + ", warehouseId=" + warehouseId + ", event=" + event
                + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
    }

}
