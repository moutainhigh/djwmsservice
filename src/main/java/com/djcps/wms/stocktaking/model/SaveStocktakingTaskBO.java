package com.djcps.wms.stocktaking.model;

import java.util.List;

/**
 * @title:保存盘点任务
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/14
 **/
public class SaveStocktakingTaskBO {

    /**
     * 保存盘点类型1，2，3(只保存，保存并打印，保存并发起推送)
     */
    private Integer saveStocktakingType;

    /** 作业单号
    */
    private String jobId;

    /**
     * 合作方编号
     */
    private String partnerId;

    /**
     * 合作方名称
     */
    private String partnerName;

    /**
     * 合作方区域
     */
    private String partnerArea;

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

    public String getPartnerArea() {
        return partnerArea;
    }

    public void setPartnerArea(String partnerArea) {
        this.partnerArea = partnerArea;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPdaStatus() {
        return pdaStatus;
    }

    public void setPdaStatus(Integer pdaStatus) {
        this.pdaStatus = pdaStatus;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getInventoryClerk() {
        return inventoryClerk;
    }

    public void setInventoryClerk(String inventoryClerk) {
        this.inventoryClerk = inventoryClerk;
    }

    public String getInventoryClerkId() {
        return inventoryClerkId;
    }

    public void setInventoryClerkId(String inventoryClerkId) {
        this.inventoryClerkId = inventoryClerkId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SaveStocktakingOrderInfoBO> getStocktakingOrderInfo() {
        return stocktakingOrderInfo;
    }

    public void setStocktakingOrderInfo(List<SaveStocktakingOrderInfoBO> stocktakingOrderInfo) {
        this.stocktakingOrderInfo = stocktakingOrderInfo;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Integer getSaveStocktakingType() {
        return saveStocktakingType;
    }

    public void setSaveStocktakingType(Integer saveStocktakingType) {
        this.saveStocktakingType = saveStocktakingType;
    }

    @Override
    public String toString() {
        return "SaveStocktakingTaskBO{" +
                "saveStocktakingType=" + saveStocktakingType +
                ", jobId='" + jobId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", partnerArea='" + partnerArea + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", pdaStatus=" + pdaStatus +
                ", creator='" + creator + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", inventoryClerk='" + inventoryClerk + '\'' +
                ", inventoryClerkId='" + inventoryClerkId + '\'' +
                ", remark='" + remark + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", stocktakingOrderInfo=" + stocktakingOrderInfo +
                '}';
    }
}
