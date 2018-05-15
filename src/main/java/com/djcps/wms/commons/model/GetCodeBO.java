package com.djcps.wms.commons.model;

import com.djcps.wms.commons.base.BaseAddBO;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 获取编码
 * @author  wzy
 * @since  2017/12/20 16:46
 *
 **/
public class GetCodeBO extends BaseAddBO implements Serializable {
    private static final long serialVersionUID = -5482024471175235674L;
    /**
     * 编码类型 1 仓库编码 2 库区编码 3 库位编码
     */
    @NotBlank
    private String codeType;

    /**
     * 仓库编码
     */
    private String warehouseId;

    /**
     * 库区编码
     */
    private String warehouseAreaId;

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }


    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseAreaId() {
        return warehouseAreaId;
    }

    public void setWarehouseAreaId(String warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
    }

    @Override
    public String toString() {
        return "GetCodeBO{" +
                "codeType='" + codeType + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseAreaId='" + warehouseAreaId + '\'' +
                '}';
    }
}
