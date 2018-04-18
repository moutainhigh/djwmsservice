package com.djcps.wms.loadingtask.model;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 获取装车员信息参数
 * 
 * @author WYB
 * @since 2018/3/20
 */
public class LoadingPersonBO extends BaseBO {

    /**
     * 
     */
    private static final long serialVersionUID = -959879472225997395L;
    /**
     * 合作方号
     */
    private String partnerId;
    /**
     * 装车台id
     */
    private String loadingTableId;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getLoadingTableId() {
        return loadingTableId;
    }

    public void setLoadingTableId(String loadingTableId) {
        this.loadingTableId = loadingTableId;
    }

    @Override
    public String toString() {
        return "LoadingPersonBO [partnerId=" + partnerId + ", loadingTableId=" + loadingTableId + "]";
    }

}
