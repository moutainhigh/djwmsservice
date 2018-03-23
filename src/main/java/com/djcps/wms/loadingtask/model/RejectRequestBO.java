package com.djcps.wms.loadingtask.model;

import com.djcps.wms.commons.base.BaseBO;
/**
 * 驳回申请
 * @author wyb
 * @since 2018/3/21
 */
public class RejectRequestBO extends BaseBO {
    /**
     * 
     */
    private static final long serialVersionUID = 5492028894181177434L;
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

    public String getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(String wayBillId) {
        this.wayBillId = wayBillId;
    }

    public Integer getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(Integer disposeStatus) {
        this.disposeStatus = disposeStatus;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
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

    @Override
    public String toString() {
        return "RejectRequestBO [partnerId=" + partnerId + ", wayBillId=" + wayBillId + ", handlerid=" + handlerid
                + ", handler=" + handler + ", disposeStatus=" + disposeStatus + "]";
    }

}
