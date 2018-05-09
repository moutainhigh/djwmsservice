package com.djcps.wms.role.model;

/**
 * @author Chengw
 * @create 2018/5/8 15:16.
 * @since 1.0.0
 */
public class OrgUserRolePO {


    /**
     * 角色类型
     */
    private String rtype;

    /**
     * 角色名称
     */
    private String rname;
    /**
     * id
     */
    private String id;


    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
