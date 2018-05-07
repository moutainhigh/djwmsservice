package com.djcps.wms.commons.base;

import com.djcps.wms.commons.constant.AppConstant;

import java.io.Serializable;

/**
 * 该类为 基础版本号 参数类
 * @author ztw
 * @version 1.0
 * @since 2017/6/25
 */
public class BaseBO implements Serializable{

    private static final long serialVersionUID = 7853196708030763026L;

    /**
     * 版本号，默认为1.0
     */
    private String version;

    public BaseBO(){
    	this.version = AppConstant.DEFAULT_VERSION;
    }
    
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "BaseBO [version=" + version + "]";
    }
    
    
}
