package com.djcps.wms.stocktaking.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 更新打印次数
 * @author:wzy
 * @company:djwms
 * @create:2018/1/25
 **/
public class PrintCountBO {
    @NotBlank
    private String partnerId;

    @NotBlank
    private String jobId;

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
        return "PrintCountBO{" +
                "partnerId='" + partnerId + '\'' +
                ", jobId='" + jobId + '\'' +
                '}';
    }
}
