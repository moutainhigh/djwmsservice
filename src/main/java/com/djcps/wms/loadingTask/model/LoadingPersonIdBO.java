package com.djcps.wms.loadingTask.model;

import com.djcps.wms.commons.base.BaseBO;

public class LoadingPersonIdBO extends BaseBO {

    /**
     * 
     */
    private static final long serialVersionUID = 47147837247657226L;
    /**
     * 装车员编号
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoadingPersonIdBO [id=" + id + "]";
    }

}
