package com.djcps.wms.inneruser.model.userparam;

/**
 * 部门信息实体类
 * @author:wzy
 * @date:2018/4/23
 **/
public class DepartmentPO {

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 部门id
     */
    private String departmentId;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "DepartmentPO{" +
                "departmentName='" + departmentName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }
}
