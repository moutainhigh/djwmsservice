package com.djcps.wms.role.model.request;

import java.io.Serializable;

/**
 * 返回权限信息参数
 * 
 * @author:wyb
 * @date:2018/4/13
 **/
public class WmsPessionInfoPO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -974586237888622262L;
    /**
     * 权限包名称
     */
    private String title;
    /**
     * 权限id
     */
    private String id;

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WmsPessionInfoPO [title=" + title + ", id=" + id + "]";
    }

}
