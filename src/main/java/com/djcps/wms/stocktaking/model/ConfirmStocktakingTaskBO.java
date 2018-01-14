package com.djcps.wms.stocktaking.model;


import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @title:确认盘点任务参数类
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/11
 **/
public class ConfirmStocktakingTaskBO {

    /**
     * 保存盘点类型1，2，3(只保存，保存并打印，保存并发起推送)
     */
    @NotNull
    private Integer saveStocktakingType;

    /**
     * 作业单号
     */
    private String jobId;

    /**
     * 盘点类型
     */
    @NotNull
    private Integer type;
    /**
     * 作业状态
     */
    @NotNull
    private Integer status;
    /**
     * pda作业状态
     */
    private Integer pdaStatus;
    /**
     * 创建人
     */
    @NotBlank
    private String creator;
    /**
     * 创建人编号
     */
    @NotBlank
    private String creatorId;
    /**
     * 盘点员
     */
    @NotBlank
    private String inventoryClerk;
    /**
     * 盘点员编号
     */
    @NotBlank
    private String inventoryClerkId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 合作方编号
     */
    @NotBlank
    private String partnerId;
    /**
     * 合作方姓名
     */
    @NotBlank
    private String partnerName;
    /**
     * 合作方区域
     */
    @NotBlank
    private String partnerArea;
    /**
     * 仓库编号
     */
    @NotBlank
    private String warehouseId;
    /**
     * 仓库名称
     */
    @NotBlank
    private String warehouseName;
    /**
     * 盘点订单信息集合
     */
    private List<WarehouseOrderDetailPO> stocktakingOrderInfo;

    public Integer getSaveStocktakingType() {
        return saveStocktakingType;
    }

    public void setSaveStocktakingType(Integer saveStocktakingType) {
        this.saveStocktakingType = saveStocktakingType;
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

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerArea() {
        return partnerArea;
    }

    public void setPartnerArea(String partnerArea) {
        this.partnerArea = partnerArea;
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

    public List<WarehouseOrderDetailPO> getStocktakingOrderInfo() {
        return stocktakingOrderInfo;
    }

    public void setStocktakingOrderInfo(List<WarehouseOrderDetailPO> stocktakingOrderInfo) {
        this.stocktakingOrderInfo = stocktakingOrderInfo;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "ConfirmStocktakingTaskBO{" +
                "saveStocktakingType=" + saveStocktakingType +
                ", jobId='" + jobId + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", pdaStatus=" + pdaStatus +
                ", creator='" + creator + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", inventoryClerk='" + inventoryClerk + '\'' +
                ", inventoryClerkId='" + inventoryClerkId + '\'' +
                ", remark='" + remark + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", partnerArea='" + partnerArea + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", stocktakingOrderInfo=" + stocktakingOrderInfo +
                '}';
    }
}
