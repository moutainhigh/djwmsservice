package com.djcps.wms.inneruser.model.userparam;

/**
 * ORG获取职位信息列表
 * @author:wzy
 * @date:2018/4/17
 **/
public class OrgPositionPO {
    /**
     * 职位id
     */
    private String id;

    /**
     * 职位名称
     */
    private String uposition_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUposition_name() {
        return uposition_name;
    }

    public void setUposition_name(String uposition_name) {
        this.uposition_name = uposition_name;
    }

    @Override
    public String toString() {
        return "OrgPositionPO{" +
                "id='" + id + '\'' +
                ", uposition_name='" + uposition_name + '\'' +
                '}';
    }
}
