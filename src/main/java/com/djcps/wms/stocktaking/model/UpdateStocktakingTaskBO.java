package com.djcps.wms.stocktaking.model;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @title:更新盘点状态参数类
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/11
 **/
public class UpdateStocktakingTaskBO implements Serializable{

    private static final long serialVersionUID = 3284717543875349228L;
    /**
     * 作业状态 1,2,3,4(待作业，作业中，已完成，已关闭)
     */
    private Integer status;

    /**
     * 作业单号
     */
    @NotBlank
    private String jobId;

    private String warehouseId;

    private String partnerId;

    /**
     * pda作业状态
     */
    private Integer pdaStatus;

    public Integer getPdaStatus() {
        return pdaStatus;
    }

    public void setPdaStatus(Integer pdaStatus) {
        this.pdaStatus = pdaStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return "UpdateStocktakingTaskBO{" +
                "status=" + status +
                ", jobId='" + jobId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", pdaStatus='" + pdaStatus + '\'' +
                '}';
    }
}
