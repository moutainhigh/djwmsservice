package com.djcps.wms.role.model.request;

import java.util.List;

/**
 * 角色列表信息实体类
 * 
 * @author WYB
 * @since 2018/4/12
 */
public class OrgRoleListPO {
    /**
     * 角色id
     */
    private String id;
    /**
     * 合作方id
     */
    private String partnerId;
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
     * 权限信息
     */
    private List<OrgPerssionsInfoPO> perssions;

    public String getId() {
        return id;
    }

    public List<OrgPerssionsInfoPO> getPerssions() {
        return perssions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPerssions(List<OrgPerssionsInfoPO> perssions) {
        this.perssions = perssions;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public String getRtype() {
        return rtype;
    }

    public String getRname() {
        return rname;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @Override
    public String toString() {
        return "OrgRoleListPO [id=" + id + ", partnerId=" + partnerId + ", rtype=" + rtype + ", rname=" + rname
                + ", rdesc=" + rdesc + ", perssions=" + perssions + "]";
    }

}
