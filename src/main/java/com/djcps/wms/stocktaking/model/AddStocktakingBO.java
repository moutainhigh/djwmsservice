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
    private int ftype;

    /**
     * 仓库编码
     * @author  wzy
     **/
    @NotBlank
    private String warehouseId;

    /**
     * 盘点数量
     * @author  wzy
     * @create  2018/1/10 10:54
     **/
    private int ftakestockamount;

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
    private List<WarehouseAreaBO> warehouseAreaInfoList;


    public int getFtype() {
        return ftype;
    }

    public void setFtype(int ftype) {
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

    public List<WarehouseAreaBO> getWarehouseAreaInfoList() {
        return warehouseAreaInfoList;
    }

    public void setWarehouseAreaInfoList(List<WarehouseAreaBO> warehouseAreaInfoList) {
        this.warehouseAreaInfoList = warehouseAreaInfoList;
    }

    @Override
    public String toString() {
        return "AddStocktakingBO{" +
                "ftype=" + ftype +
                ", warehouseId='" + warehouseId + '\'' +
                ", ftakestockamount=" + ftakestockamount +
                ", jobId='" + jobId + '\'' +
                ", warehouseAreaInfoList=" + warehouseAreaInfoList +
                '}';
    }
}
