package com.djcps.wms.warehouse.model.warehouse;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @title 获取仓库编码
 * @author  wzy
 * @create  2017/12/20 13:05
 **/
public class GetWarehouseCodeBO implements Serializable{

    private static final long serialVersionUID = -1833285868264967486L;
    /**
     * 编码类型 1 仓库编码 2 库区编码 3 库位编码
     */
    @NotBlank
    private String codeType;

    /**
     * 合作方id
     */
    @NotBlank
    private String partnerId;

    /**
     * 版本号
     */
    @NotBlank
    private String version;


    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "GetWarehouseCodeBO{" +
                "codeType=" + codeType +
                ", partnerId='" + partnerId + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
