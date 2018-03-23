package com.djcps.wms.loadingtask.model;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 追加订单参数
 * 
 * @author WYB
 * @since 2018/3/21
 */
public class AdditionalOrderBO extends BaseBO {

    /**
     * 
     */
    private static final long serialVersionUID = -5984700348598254524L;
    /**
     * 追加平方数
     */
    private Integer addSquare;
    /**
     * 申请人编号
     */
    private String proposerId;
    /**
     * 申请人
     */
    private String proposer;
    /**
     * 合作方号
     */
    private String partnerId;
    /**
     * 运单编号
     */
    private String wayBillId;
    /**
     * 处理人编号
     */
    private String handlerId;
    /**
     * 处理人
     */
    private String handler;
    /**
     * 处理状态(0待处理,1已通过,2已驳回)
     */
    private Integer disposeStatus;

    public String getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    public Integer getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(Integer disposeStatus) {
        this.disposeStatus = disposeStatus;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Integer getAddSquare() {
        return addSquare;
    }

    public void setAddSquare(Integer addSquare) {
        this.addSquare = addSquare;
    }

    public String getProposerId() {
        return proposerId;
    }

    public void setProposerId(String proposerId) {
        this.proposerId = proposerId;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(String wayBillId) {
        this.wayBillId = wayBillId;
    }

    @Override
    public String toString() { 
        return "AdditionalOrderBO [addSquare=" + addSquare + ", proposerId=" + proposerId + ", proposer=" + proposer
                + ", partnerId=" + partnerId + ", wayBillId=" + wayBillId + ", handlerId=" + handlerId + ", handler="
                + handler + ", disposeStatus=" + disposeStatus + "]";
    }

}
