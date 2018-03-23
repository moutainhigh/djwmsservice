package com.djcps.wms.loadingTask.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.base.BaseListBO;

/**
 * 获取装车员信息包含查询
 * 
 * @company:djwms
 * @author:wyb
 * @date:2018年3月22日
 */
public class GetLoadingPersonInfoBO extends BaseListBO implements Serializable{

    private static final long serialVersionUID = 1522403205165183703L;
    /**
     * 姓名
     */
    private String name;

    /**
     * 合作方id
     */
    @NotBlank
    private String partnerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return "GetLoadingPersonInfoBO [name=" + name + ", partnerId=" + partnerId + "]";
    }

}
