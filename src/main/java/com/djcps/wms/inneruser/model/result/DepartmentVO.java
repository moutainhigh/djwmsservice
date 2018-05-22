package com.djcps.wms.inneruser.model.result;

import java.io.Serializable;

/**
 * 部门信息实体类
 * @author wzy
 * @date 2018/4/23
 **/
public class DepartmentVO implements Serializable {

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
        return "DepartmentVO{" +
                "departmentName='" + departmentName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }
}
