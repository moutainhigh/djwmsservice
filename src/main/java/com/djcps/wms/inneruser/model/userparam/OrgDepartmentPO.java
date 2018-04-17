package com.djcps.wms.inneruser.model.userparam;

/**
 * 从org获取的公司部门信息
 * @author:wzy
 * @date:2018/4/13
 **/
public class OrgDepartmentPO {

    /**
     * 部门名称
     */
    private String oname;

    /**
     * 部门名称
     */
    private String id;

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrgDepartmentPO{" +
                "oname='" + oname + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
