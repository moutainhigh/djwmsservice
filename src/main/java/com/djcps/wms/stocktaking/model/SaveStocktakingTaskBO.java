package com.djcps.wms.stocktaking.model;

import java.util.List;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * @title:保存盘点任务
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/14
 **/
public class SaveStocktakingTaskBO extends BaseAddBO {

    /**
     * 保存盘点类型1，2，3(只保存，保存并打印，保存并发起推送)
     */
    private Integer saveStocktakingType;

    /**
     * 作业单号
     */
    private String jobId;
    /**
     * 盘点类型
     */
    private Integer type;
    /**
     * 作业状态
     */
    private Integer status;
    /**
     * pda作业状态
     */
    private Integer pdaStatus;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建人编号
     */
    private String creatorId;
    /**
     * 盘点员
     */
    private String inventoryClerk;
    /**
     * 盘点员编号
     */
    private String inventoryClerkId;
    /**
     * 备注
     */
    private String remark;

    /**
     * 仓库编号
     */
    private String warehouseId;
    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 盘点订单信息集合
     */
    private List<SaveStocktakingOrderInfoBO> stocktakingOrderInfo;

    public Integer getSaveStocktakingType() {
        return saveStocktakingType;
    }

    public String getJobId() {
        return jobId;
    }

    public Integer getType() {
        return type;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getPdaStatus() {
        return pdaStatus;
    }

    public String getCreator() {
        return creator;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getInventoryClerk() {
        return inventoryClerk;
    }

    public String getInventoryClerkId() {
        return inventoryClerkId;
    }

    public String getRemark() {
        return remark;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public List<SaveStocktakingOrderInfoBO> getStocktakingOrderInfo() {
        return stocktakingOrderInfo;
    }

    public void setSaveStocktakingType(Integer saveStocktakingType) {
        this.saveStocktakingType = saveStocktakingType;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPdaStatus(Integer pdaStatus) {
        this.pdaStatus = pdaStatus;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public void setInventoryClerk(String inventoryClerk) {
        this.inventoryClerk = inventoryClerk;
    }

    public void setInventoryClerkId(String inventoryClerkId) {
        this.inventoryClerkId = inventoryClerkId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public void setStocktakingOrderInfo(List<SaveStocktakingOrderInfoBO> stocktakingOrderInfo) {
        this.stocktakingOrderInfo = stocktakingOrderInfo;
    }

    @Override
    public String toString() {
        return "SaveStocktakingTaskBO [saveStocktakingType=" + saveStocktakingType + ", jobId=" + jobId + ", type="
                + type + ", status=" + status + ", pdaStatus=" + pdaStatus + ", creator=" + creator + ", creatorId="
                + creatorId + ", inventoryClerk=" + inventoryClerk + ", inventoryClerkId=" + inventoryClerkId
                + ", remark=" + remark + ", warehouseId=" + warehouseId + ", warehouseName=" + warehouseName
                + ", stocktakingOrderInfo=" + stocktakingOrderInfo + "]";
    }

}
