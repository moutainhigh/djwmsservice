package com.djcps.wms.inneruser.model.userparam;

/**
 * org获取的职务对应PO
 * @author:wzy
 * @date:2018/4/20
 **/
public class JobPO {

    /**
     * 职务名称
     */
    private String jobName;

    /**
     * 职务id
     */
    private String jobId;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "JobPO{" +
                "jobName='" + jobName + '\'' +
                ", jobId='" + jobId + '\'' +
                '}';
    }
}
