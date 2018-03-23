package com.djcps.wms.loadingtask.model;

import com.djcps.wms.commons.base.BaseListBO;
/**
 * 追加订单申请列表
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
     * 处理人编号
     */
    private String handlerid;
    /**
     * 处理人
     */
    private String handler;
    /**
     * 处理状态
     */
    private Integer disposeStatus;
    /**
     * 开始时间
     */
    private String beginTime;
    /**
     * 结束时间
     */
    private String endTime;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public Integer getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(Integer disposeStatus) {
        this.disposeStatus = disposeStatus;
    }

    @Override
    public String toString() {
        return "AddOrderApplicationListBO [partnerId=" + partnerId + ", wayBillId=" + wayBillId + ", handlerid="
                + handlerid + ", handler=" + handler + ", disposeStatus=" + disposeStatus + ", beginTime=" + beginTime
                + ", endTime=" + endTime + "]";
    }

}
