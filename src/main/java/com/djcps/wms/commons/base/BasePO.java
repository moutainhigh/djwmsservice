package com.djcps.wms.commons.base;

import java.io.Serializable;

/**
 * 公共操作返回类
 *
 * @author Chengw
 * @version 1.0.0
 * @since 2018/4/24 16:08.
 */
public class BasePO implements Serializable {

    /**
     * 合作方id
     */
    private String partnerId;

    /**
     * 合作方名称
     */
    private String partnerName;

    /**
     * 合作方所在区域
     */
    private String partnerArea;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 操作人名称
     */
    private String operator;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerArea() {
        return partnerArea;
    }

    public void setPartnerArea(String partnerArea) {
        this.partnerArea = partnerArea;
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
        return "BasePO{" +
                "partnerId='" + partnerId + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", partnerArea='" + partnerArea + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}
