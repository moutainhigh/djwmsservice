package com.djcps.wms.role.model.request;

/**
 * org返回权限信息参数
 * 
 * @author:wyb
 * @date:2018/4/13
 **/
public class OrgPerssionsInfoPO {
    /**
     * 权限包名称
     */
    private String ptitle;
    /**
     * 权限id
     */
    private String pid;

    public String getPtitle() {
        return ptitle;
    }

    public String getPid() {
        return pid;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "OrgPerssionsInfoPO [ptitle=" + ptitle + ", pid=" + pid + "]";
    }

}
