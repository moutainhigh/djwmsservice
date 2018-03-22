package com.djcps.wms.loadingTask.model;

import java.util.List;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 装车员页面确认参数
 * 
 * @author WYB
 * @since 2018/3/20
 */
public class ConfirmBO extends BaseBO {

    /**
     * 
     */
    private static final long serialVersionUID = 4090316619383601692L;
    /**
     * 合作方号
     */
    private String partnerId;
    /**
     * 装车台id
     */
    private String loadingTableId;
    /**
     * 装车员编号集合
     */
    private List<LoadingPersonIdBO> list;
    /**
     * 合作方名称
     */
    private String partnerName;
    /**
     * 合作方区域
     */
    private String partnerArea;

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

    public List<LoadingPersonIdBO> getList() {
        return list;
    }

    public void setList(List<LoadingPersonIdBO> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ConfirmBO [partnerId=" + partnerId + ", loadingTableId=" + loadingTableId + ", list=" + list
                + ", partnerName=" + partnerName + ", partnerArea=" + partnerArea + "]";
    }

}
