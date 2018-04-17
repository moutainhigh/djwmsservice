package com.djcps.wms.role.model;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.base.BaseOrgBO;

/**
 * 新增角色信息参数
 * 
 * @author WYB
 * @since 2018/4/12
 */
public class SaveBO extends BaseOrgBO {

    /**
     * 
     */
    private static final long serialVersionUID = 696280916702489605L;
    /**
     * 角色id
     */
    private String id;
    /**
     * 角色类型
     */
    private String rtype;
    /**
     * 角色名称
     */
    private String rname;
    /**
     * 合作方id
     */
    @NotBlank
    private String partnerId;
    /**
     * 角色描述
     */
    private String rdesc;
    /**
     * 权限id
     */
    private String pid;

    public String getRdesc() {
        return rdesc;
    }

    public String getPid() {
        return pid;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public String getRtype() {
        return rtype;
    }

    public String getRname() {
        return rname;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return "SaveBO [id=" + id + ", rtype=" + rtype + ", rname=" + rname + ", partnerId=" + partnerId + ", rdesc="
                + rdesc + ", pid=" + pid + "]";
    }

}
