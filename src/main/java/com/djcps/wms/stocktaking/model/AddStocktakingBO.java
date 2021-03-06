package com.djcps.wms.stocktaking.model;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.order.model.WarehouseAreaBO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 新增盘点任务参数类
 * @author  wzy
 * @create  2018/1/9 19:30
 **/
public class AddStocktakingBO extends BaseAddBO implements Serializable{

    private static final long serialVersionUID = -4893342633580586508L;

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


    private String  warehouseName;

    /**
     * 盘点数量
     * @author  wzy
     * @create  2018/1/10 10:54
     **/
    private Integer ftakestockamount;

    /**
     * 作业单号
     */
    private String jobId;

    /**
     * 库区列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 9:55
     **/
    private List<WarehouseAreaBO> areaList;

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Integer getFtype() {
        return ftype;
    }

    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    public String getWarehouseID() {
        return warehouseId;
    }

    public void setWarehouseID(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getFtakestockamount() {
        return ftakestockamount;
    }

    public void setFtakestockamount(int ftakestockamount) {
        this.ftakestockamount = ftakestockamount;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public List<WarehouseAreaBO> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<WarehouseAreaBO> areaList) {
		this.areaList = areaList;
	}

	public void setFtakestockamount(Integer ftakestockamount) {
		this.ftakestockamount = ftakestockamount;
	}

	@Override
	public String toString() {
		return "AddStocktakingBO [ftype=" + ftype + ", warehouseId=" + warehouseId + ", warehouseName=" + warehouseName
				+ ", ftakestockamount=" + ftakestockamount + ", jobId=" + jobId + ", areaList=" + areaList + "]";
	}
    
}
