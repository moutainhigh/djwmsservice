package com.djcps.wms.permission.model.po;

import java.io.Serializable;

/**
 * 用户权限实体类
 * @author Chengw
 * @create 2018/4/23 17:27.
 * @since 1.0.0
 */
public class UserPermissionPO implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 父节点
     */
    private String pfather;

    /**
     * 标题
     */
    private String ptitle;

    /**
     * 层级
     */
    private String polayer;

    /**
     * 接口
     */
    private String pinterface;

    /**
     * 标记
     */
    private String pmark;

    /**
     * 图标
     */
    private String pico;

    /**
     * 业务
     */
    private String pbusiness;
    /**
     * 控件编号
     */
    private String phtmlid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPfather() {
        return pfather;
    }

    public void setPfather(String pfather) {
        this.pfather = pfather;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPolayer() {
        return polayer;
    }

    public void setPolayer(String polayer) {
        this.polayer = polayer;
    }

    public String getPinterface() {
        return pinterface;
    }

    public void setPinterface(String pinterface) {
        this.pinterface = pinterface;
    }

    public String getPmark() {
        return pmark;
    }

    public void setPmark(String pmark) {
        this.pmark = pmark;
    }

    public String getPico() {
        return pico;
    }

    public void setPico(String pico) {
        this.pico = pico;
    }

    public String getPbusiness() {
        return pbusiness;
    }

    public void setPbusiness(String pbusiness) {
        this.pbusiness = pbusiness;
    }

    public String getPhtmlid() {
        return phtmlid;
    }

    public void setPhtmlid(String phtmlid) {
        this.phtmlid = phtmlid;
    }

    @Override
    public String toString() {
        return "UserPermissionPO{" +
                "id='" + id + '\'' +
                ", pfather='" + pfather + '\'' +
                ", ptitle='" + ptitle + '\'' +
                ", polayer='" + polayer + '\'' +
                ", pinterface='" + pinterface + '\'' +
                ", pmark='" + pmark + '\'' +
                ", pico='" + pico + '\'' +
                ", pbusiness='" + pbusiness + '\'' +
                ", phtmlid='" + phtmlid + '\'' +
                '}';
    }
}
