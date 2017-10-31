package com.djcps.wms.commons.base;

import java.io.Serializable;

/**
 * 该类为 基础版本号 参数类
 * @author ztw
 * @version 1.0
 * @since 2017/6/25
 */
public class BaseParam implements Serializable{

    private static final long serialVersionUID = 7853196708030763026L;

    /**
     * 版本号，默认为1.0
     */
    private String version = "1.0";

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "BaseParam [version=" + version + "]";
    }
    
    
}
