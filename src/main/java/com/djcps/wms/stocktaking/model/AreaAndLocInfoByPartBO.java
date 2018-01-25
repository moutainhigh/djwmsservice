package com.djcps.wms.stocktaking.model;

/**
 * 获取新增盘点时的库区库位
 * @author:wzy
 * @company:djwms
 * @create:2018/1/23
 **/
public class AreaAndLocInfoByPartBO {

    private String warehouseId;

    private String warehouseName;

    private String jobId;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "AreaAndLocInfoByPartBO{" +
                "warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", jobId='" + jobId + '\'' +
                '}';
    }
}
