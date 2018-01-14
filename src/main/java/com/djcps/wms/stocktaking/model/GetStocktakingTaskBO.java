package com.djcps.wms.stocktaking.model;

import org.hibernate.validator.constraints.NotBlank;

/**获取盘点任务列表条件对象
 * @title:
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/12
 **/
public class GetStocktakingTaskBO {
    /**
     * 仓库编号
     */
    @NotBlank
    private String warehouseId;
    /**
     * 作业状态
     */
    private String status;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 作业单号
     */
    private String jobId;
    /**
     * 开始时间
     */
    private String beginTime;
    /**
     * 结束时间
     */
    private String endTime;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "GetStocktakingTaskBO{" +
                "warehouseId='" + warehouseId + '\'' +
                ", status='" + status + '\'' +
                ", creator='" + creator + '\'' +
                ", jobId='" + jobId + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
