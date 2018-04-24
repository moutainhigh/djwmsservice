package com.djcps.wms.role.model.request;

import java.io.Serializable;

/**
 * 获取角色类型信息实体类
 * 
 * @author WYB
 * @since 2018/4/12
 */
public class RoleTypeInfoPO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7500031984603609145L;
    /**
     * 角色类型code
     */
    private String rtypeCode;
    /**
     * 角色名称
     */
    private String rtype;

    public String getRtypeCode() {
        return rtypeCode;
    }

    public String getRtype() {
        return rtype;
    }

    public void setRtypeCode(String rtypeCode) {
        this.rtypeCode = rtypeCode;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    @Override
    public String toString() {
        return "RoleTypeInfoPO [rtypeCode=" + rtypeCode + ", rtype=" + rtype + "]";
    }

}
