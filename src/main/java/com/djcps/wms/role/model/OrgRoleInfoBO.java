package com.djcps.wms.role.model;

import com.djcps.wms.commons.base.BaseOrgBO;

/**
 * ORG获取角色列表信息参数
 * 
 * @author WYB
 * @since 2018/4/12
 */
public class OrgRoleInfoBO extends BaseOrgBO {
    /**
     * 
     */
    private static final long serialVersionUID = -8152015187824278326L;
    /**
     * 角色id
     */
    private String rid;
    /**
     * 角色类型
     */
    private String rtype;
    /**
     * 角色名称
     */
    private String rname;
    /**
     * 角色描述
     */
    private String rdesc;
    /**
     * 权限id
     */
    private String pid;

    public String getRid() {
        return rid;
    }

    public String getRtype() {
        return rtype;
    }

    public String getRname() {
        return rname;
    }

    public String getRdesc() {
        return rdesc;
    }

    public String getPid() {
        return pid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "OrgRoleInfoBO [rid=" + rid + ", rtype=" + rtype + ", rname=" + rname + ", rdesc=" + rdesc + ", pid="
                + pid + "]";
    }

}
