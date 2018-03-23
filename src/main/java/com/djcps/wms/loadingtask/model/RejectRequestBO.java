package com.djcps.wms.loadingtask.model;

import com.djcps.wms.commons.base.BaseBO;

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
        return "RejectRequestBO [partnerId=" + partnerId + ", waybillId=" + waybillId + ", loadingTableId="
                + loadingTableId + ", handlerid=" + handlerid + ", handler=" + handler + "]";
    }

}
