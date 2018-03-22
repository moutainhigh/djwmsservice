package com.djcps.wms.loadingTask.model;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 移除装车员信息参数
 * 
 * @author WYB
 * @since 2018/3/20
 */
public class RemoveLoadingPersonBO extends BaseBO {

    /**
     * 
     */
    private static final long serialVersionUID = -2876493742638536753L;
    /**
     * 合作方号
     */
    private String partnerId;
    /**
     * 装车员id
     */
    private String id;
    
    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RemoveLoadingPersonBO [partnerId=" + partnerId + ", id=" + id + "]";
    }

}
