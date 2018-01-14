package com.djcps.wms.stocktaking.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @title:PDA获取盘点任务订单信息
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/13
 **/
public class PdaGetStocktakingOrderBO {

    /**
     * 作业单号
     */
    @NotBlank
    private String jobId;

    /**
     * 合作方编号
     */
    @NotBlank
    private String partnerId;

    /**
     * 仓库编号
     */
    @NotBlank
    private String warehouseId;

    /**
     * 页面显示数量
     */
    private String pageSize;

    /**
     *当前页
     **/
    private String pageNo;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "PdaGetStocktakingOrderBO{" +
                "jobId='" + jobId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", pageNo='" + pageNo + '\'' +
                '}';
    }
}
