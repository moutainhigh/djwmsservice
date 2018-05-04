package com.djcps.wms.abnormal.model;

import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;
import com.djcps.wms.record.model.OrderOperationRecordPO;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * 修改异常订单
 * 
 * @company:djwms
 * @author:zdx
 * @date:2018年3月7日
 */
public class UpdateAbnormalBO extends BaseUpdateAndDeleteBO implements Serializable {

    private static final long serialVersionUID = -1306428961188134546L;

    /**
     * 异常处理结果
     */
    private String result;

    /**
     * 订单编号
     */
    @NotBlank
    private String orderId;

    /**
     * 装车台id
     */
    private String loadingTableId;

    /**
     * 装车台name
     */
    private String loadingTableName;

    /**
     * 备注
     */
    private String remark;
    /**
     * 异常数量
     */
    private String abnomalAmount;
    /**
     * 处理状态
     */
    private String status;

    /**
     * 异常原因
     */
    private String reason;

    /**
     * 提报人
     */
    private String submiter;

    /**
     * 提报时间
     */
    private String submitTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getSubmiter() {
        return submiter;
    }

    public void setSubmiter(String submiter) {
        this.submiter = submiter;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAbnomalAmount() {
        return abnomalAmount;
    }

    public void setAbnomalAmount(String abnomalAmount) {
        this.abnomalAmount = abnomalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoadingTableId() {
        return loadingTableId;
    }

    public void setLoadingTableId(String loadingTableId) {
        this.loadingTableId = loadingTableId;
    }

    public String getLoadingTableName() {
        return loadingTableName;
    }

    public void setLoadingTableName(String loadingTableName) {
        this.loadingTableName = loadingTableName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "UpdateAbnormalBO [result=" + result + ", orderId=" + orderId + ", loadingTableId=" + loadingTableId
                + ", loadingTableName=" + loadingTableName + ", remark=" + remark + ", abnomalAmount=" + abnomalAmount
                + ", status=" + status + ", reason=" + reason + ", submiter=" + submiter + ", submitTime=" + submitTime
                + "]";
    }

}
