package com.djcps.wms.stocktaking.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 获取盘点任务参数实体
 * @author:wzy
 * @company:djwms
 * @create:2018/1/26
 **/
public class JobAndWarehouseBO {

    @NotBlank
    private String warehouseId;

    @NotBlank
    private String jobId;

    private String partnerId;

    private String warehouseAreaId;

    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWarehouseAreaId() {
        return warehouseAreaId;
    }

    public void setWarehouseAreaId(String warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

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

    @Override
    public String toString() {
        return "JobAndWarehouseBO{" +
                "warehouseId='" + warehouseId + '\'' +
                ", jobId='" + jobId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", warehouseAreaId='" + warehouseAreaId + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
