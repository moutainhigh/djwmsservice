package com.djcps.wms.record.model;

import java.util.List;

import com.djcps.wms.commons.base.BaseListBO;

/**
 * 获取盘点操作记录列表参数接受类
 * 
 * @author wyb
 * @version 1.0
 * @since 2018/3/5
 *
 */
public class StocktakingRecordListBO extends BaseListBO {

    /**
     * 
     */
    private static final long serialVersionUID = -2368768130360205166L;
    /**
     * 作业单号
     */
    private String jobId;
    /**
     * 合作方id
     */
    private String partnerId;
    /**
     * 操作类型集合
     */
    private List<String> list;
    
    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "StocktakingRecordListBO [jobId=" + jobId + ", partnerId=" + partnerId + ", list=" + list + "]";
    }


}
