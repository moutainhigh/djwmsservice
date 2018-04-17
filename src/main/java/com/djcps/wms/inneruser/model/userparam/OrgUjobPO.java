package com.djcps.wms.inneruser.model.userparam;

/**
 * 从org获取公司职务信息
 * @author:wzy
 * @date:2018/4/16
 **/
public class OrgUjobPO {

    /**
     * 职务名称
     */
    private String ujob_name;

    /**
     * 职务id
     */
    private String id;

    public String getUjob_name() {
        return ujob_name;
    }

    public void setUjob_name(String ujob_name) {
        this.ujob_name = ujob_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrgUjobPO{" +
                "ujob_name='" + ujob_name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}


