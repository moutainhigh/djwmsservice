package com.djcps.wms.stocktaking.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 新增盘点任务对象
 * @author:wzy
 * @company:djwms
 * @create:2018/1/26
 **/
public class AddTaskBO implements Serializable{

    private static final long serialVersionUID = -5923264610557525728L;
    /**
     * 盘点类型，1，2(全盘，部分盘点)
     * @author  wzy
     **/
    @NotNull
    private Integer ftype;

    /**
     * 仓库编码
     * @author  wzy
     **/
    @NotBlank
    private String warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     *合作方id
     */
    private String partnerId;
    
    private String partnerArea;

    
    public String getPartnerArea() {
		return partnerArea;
	}

	public void setPartnerArea(String partnerArea) {
		this.partnerArea = partnerArea;
	}

	public void setFtype(Integer ftype) {
		this.ftype = ftype;
	}

	public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public int getFtype() {
        return ftype;
    }

    public void setFtype(int ftype) {
        this.ftype = ftype;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

	@Override
	public String toString() {
		return "AddTaskBO [ftype=" + ftype + ", warehouseId=" + warehouseId + ", warehouseName=" + warehouseName
				+ ", partnerId=" + partnerId + ", partnerArea=" + partnerArea + "]";
	}

}
