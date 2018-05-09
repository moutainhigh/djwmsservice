package com.djcps.wms.stocktaking.model;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * @title:PDA获取盘点任务订单信息
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/13
 **/
public class PdaGetStocktakingOrderBO extends BaseAddBO {

    /**
     * 作业单号
     */
    @NotBlank
    private String jobId;

    /**
     * 关联id
     */
    private String relativeId;

    /**
     * 仓库编号
     */
    private String warehouseId;

    /**
     * 页面显示数量
     */
    private String pageSize;

    /**
     * 当前页
     **/
    private String pageNo;

    private Integer status;

    private Integer pdaStatus;

    public String getJobId() {
        return jobId;
    }

    public String getRelativeId() {
        return relativeId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public String getPageSize() {
        return pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getPdaStatus() {
        return pdaStatus;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setRelativeId(String relativeId) {
        this.relativeId = relativeId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPdaStatus(Integer pdaStatus) {
        this.pdaStatus = pdaStatus;
    }

    @Override
    public String toString() {
        return "PdaGetStocktakingOrderBO [jobId=" + jobId + ", relativeId=" + relativeId + ", warehouseId="
                + warehouseId + ", pageSize=" + pageSize + ", pageNo=" + pageNo + ", status=" + status + ", pdaStatus="
                + pdaStatus + "]";
    }

}
