package com.djcps.wms.loadingtask.model;

import com.djcps.wms.commons.base.BaseListBO;

/**
 * 追加订单申请列表
 * 
 * @author wyb
 * @since 2018/3/21
 */
public class AddOrderApplicationListBO extends BaseListBO {

    /**
     * 
     */
    private static final long serialVersionUID = -7284023483172747561L;
    /**
     * 合作方号
     */
    private String partnerId;
    /**
     * 运单编号
     */
    private String wayBillId;
    /**
     * 处理人
     */
    private String handler;
    /**
     * 处理状态
     */
    private Integer disposeStatus;
    /**
     * 车牌号
     */
    private String plateNumber;
    /**
     * 装车台编号
     */
    private String loadingTableId;
    /**
     * 申请人
     */
    private String proposer;
    /**
     * 申请开始时间
     */
    private String applicationBeginTime;
    /**
     * 申请结束时间
     */
    private String applicationEndTime;
    /**
     * 处理开始时间
     */
    private String handlerBeginTime;
    /**
     * 处理结束时间
     */
    private String handlerEndTime;

    public String getApplicationBeginTime() {
        return applicationBeginTime;
    }

    public void setApplicationBeginTime(String applicationBeginTime) {
        this.applicationBeginTime = applicationBeginTime;
    }

    public String getApplicationEndTime() {
        return applicationEndTime;
    }

    public void setApplicationEndTime(String applicationEndTime) {
        this.applicationEndTime = applicationEndTime;
    }

    public String getHandlerBeginTime() {
        return handlerBeginTime;
    }

    public void setHandlerBeginTime(String handlerBeginTime) {
        this.handlerBeginTime = handlerBeginTime;
    }

    public String getHandlerEndTime() {
        return handlerEndTime;
    }

    public void setHandlerEndTime(String handlerEndTime) {
        this.handlerEndTime = handlerEndTime;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getLoadingTableId() {
        return loadingTableId;
    }

    public void setLoadingTableId(String loadingTableId) {
        this.loadingTableId = loadingTableId;
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

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Integer getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(Integer disposeStatus) {
        this.disposeStatus = disposeStatus;
    }

    @Override
    public String toString() {
        return "AddOrderApplicationListBO [partnerId=" + partnerId + ", wayBillId=" + wayBillId + ", handler=" + handler
                + ", disposeStatus=" + disposeStatus + ", plateNumber=" + plateNumber + ", loadingTableId="
                + loadingTableId + ", proposer=" + proposer + ", applicationBeginTime=" + applicationBeginTime
                + ", applicationEndTime=" + applicationEndTime + ", handlerBeginTime=" + handlerBeginTime
                + ", handlerEndTime=" + handlerEndTime + "]";
    }

}
