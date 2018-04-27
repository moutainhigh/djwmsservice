package com.djcps.wms.record.model.param;

import com.djcps.wms.commons.base.BaseBO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 保存操作记录
 * 
 * @author wyb
 * @version 1.0
 * @since 2018/3/6
 */
public class SaveOperationRecordBO extends BaseBO {
    /**
     * 
     */
    private static final long serialVersionUID = -1126976367147881251L;
    /**
     * 唯一标识
     */
    private String id;
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
     * 数量
     */
    private Integer amount;
    /**
     * 事件
     */
    @NotBlank
    private String event;
    /**
     * 操作时间
     */
    private String operatortime;
    /**
     * 合作方编号
     */
    private String partnerId;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getOperatortime() {
        return operatortime;
    }

    public void setOperatortime(String operatortime) {
        this.operatortime = operatortime;
    }

    @Override
    public String toString() {
        return "SaveOperationRecordBO [id=" + id + ", relativeId=" + relativeId + ", operatorId=" + operatorId
                + ", operator=" + operator + ", operationType=" + operationType + ", amount=" + amount + ", event="
                + event + ", operatortime=" + operatortime + ", partnerId=" + partnerId + "]";
    }

  

}