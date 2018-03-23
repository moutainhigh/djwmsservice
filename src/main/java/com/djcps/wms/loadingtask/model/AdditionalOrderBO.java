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
    private String waybillId;
    /**
     * 装车台编号
     */
    private String loadingTableId;
    /**
     * 处理人编号
     */
    private String handlerid;
    /**
     * 处理人
     */
    private String handler;

    public String getHandlerid() {
        return handlerid;
    }

    public void setHandlerid(String handlerid) {
        this.handlerid = handlerid;
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

    public String getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(String waybillId) {
        this.waybillId = waybillId;
    }

    public String getLoadingTableId() {
        return loadingTableId;
    }

    public void setLoadingTableId(String loadingTableId) {
        this.loadingTableId = loadingTableId;
    }

    @Override
    public String toString() {
        return "AdditionalOrderBO [addSquare=" + addSquare + ", proposerId=" + proposerId + ", proposer=" + proposer
                + ", partnerId=" + partnerId + ", waybillId=" + waybillId + ", loadingTableId=" + loadingTableId
                + ", handlerid=" + handlerid + ", handler=" + handler + "]";
    }

}
