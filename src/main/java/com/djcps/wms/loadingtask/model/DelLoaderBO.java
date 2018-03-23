package com.djcps.wms.loadingtask.model;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 删除装车员信息参数
 * 
 * @author WYB
 * @since 2018/3/20
 */
public class DelLoaderBO extends BaseBO {

    /**
     * 
     */
    private static final long serialVersionUID = -866710709472790072L;
    /**
     * 唯一标识
     */
    @NotBlank
    private String id;
    /**
     * 操作人编号
     */
    private String operatorId;
    /**
     * 合作方编号
     */
    @NotBlank
    private String partnerId;
    /**
     * 操作人姓名
     */
    private String operator;

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DelLoaderBO [id=" + id + ", operatorId=" + operatorId + ", partnerId=" + partnerId + ", operator="
                + operator + "]";
    }

}
