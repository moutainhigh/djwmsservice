package com.djcps.wms.inneruser.model.userparam;

import java.util.List;

/**
 * 用户部门职位职务组合返回
 * @author:wzy
 * @date:2018/4/17
 **/
public class UserDepartAndJobBO {

    /**
     * 部门id,udepartment_id
     */
    private String departmentId;

    /**
     * 部门名称ufdepartment
     */
    private String department;

    /**
     * 职位id,cmp_user_position__id
     */
    private String positionId;

    /**
     * 职位名称,uposition_name
     */
    private String positionName;

    /**
     *职务id，无
     */
    private String jobId;

    /**
     * 职务
     */
    private String jobName;

    /**
     * 或有部门列表
     */
    List<DepartmentPO> allDepartmentList;

    /**
     * 所有职务列表
     */
    List<JobPO> allJobList;

    /**
     * 所有职位列表
     */
    List<PositionPO> allPositionList;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public List<DepartmentPO> getAllDepartmentList() {
        return allDepartmentList;
    }

    public void setAllDepartmentList(List<DepartmentPO> allDepartmentList) {
        this.allDepartmentList = allDepartmentList;
    }

    public List<JobPO> getAllJobList() {
        return allJobList;
    }

    public void setAllJobList(List<JobPO> allJobList) {
        this.allJobList = allJobList;
    }

    public List<PositionPO> getAllPositionList() {
        return allPositionList;
    }

    public void setAllPositionList(List<PositionPO> allPositionList) {
        this.allPositionList = allPositionList;
    }

    @Override
    public String toString() {
        return "UserDepartAndJobBO{" +
                "departmentId='" + departmentId + '\'' +
                ", department='" + department + '\'' +
                ", positionId='" + positionId + '\'' +
                ", positionName='" + positionName + '\'' +
                ", jobId='" + jobId + '\'' +
                ", jobName='" + jobName + '\'' +
                ", allDepartmentList=" + allDepartmentList +
                ", allJobList=" + allJobList +
                ", allPositionList=" + allPositionList +
                '}';
    }
}
