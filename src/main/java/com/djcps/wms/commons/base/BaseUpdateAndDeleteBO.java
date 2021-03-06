package com.djcps.wms.commons.base;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @title:删除基础对象
 * @description:需要删除的对象,都需要继承此对象
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
public class BaseUpdateAndDeleteBO extends BaseBO implements Serializable {

    private static final long serialVersionUID = 4726558779577505996L;

    /**
     * 合作方id
     */
    @NotBlank
    private String partnerId;

    /**
     * 操作人id
     */
    @NotBlank
    private String operatorId;

    /**
     * 操作人名称
     */
    @NotBlank
    private String operator;
    /**
     * 合作方所在区域
     */
    private String partnerArea;

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

    @Override
    public String toString() {
        return "BaseUpdateAndDeleteBO [partnerId=" + partnerId + ", operatorId=" + operatorId + ", operator=" + operator
                + ", partnerArea=" + partnerArea + "]";
    }

}
