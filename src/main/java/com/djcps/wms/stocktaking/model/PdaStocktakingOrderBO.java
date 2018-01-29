package com.djcps.wms.stocktaking.model;

/**
 * @title:Pda盘点任务订单详细信息
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/13
 **/
public class PdaStocktakingOrderBO {
    /**
     * 订单id
     */
    private String orderId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 材料名称
     */
    private String materialName;

    /**
     * 下料规格长
     */
    private String materialLength;

    /**
     * 下料规格宽
     */
    private String materialWidth;

    /**
     * 下料规格
     */
    private String materialRule;

    /**
     * 是否盘盈 1盘盈，2正常
     */
    private String isInventoryProfit;

    private Integer takeStockAmount;

    /**
     * 盘点总数
     */
    private Integer totalNum;

    /**
     *订单状态 1未完成，3已完成
     */
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTakeStockAmount() {
        return takeStockAmount;
    }

    public void setTakeStockAmount(Integer takeStockAmount) {
        this.takeStockAmount = takeStockAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialLength() {
        return materialLength;
    }

    public void setMaterialLength(String materialLength) {
        this.materialLength = materialLength;
    }

    public String getMaterialWidth() {
        return materialWidth;
    }

    public void setMaterialWidth(String materialWidth) {
        this.materialWidth = materialWidth;
    }

    public String getMaterialRule() {
        return materialRule;
    }

    public void setMaterialRule(String materialRule) {
        this.materialRule = materialRule;
    }

    public String getIsInventoryProfit() {
        return isInventoryProfit;
    }

    public void setIsInventoryProfit(String isInventoryProfit) {
        this.isInventoryProfit = isInventoryProfit;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return "PdaStocktakingOrderBO{" +
                "orderId='" + orderId + '\'' +
                ", productName='" + productName + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialLength='" + materialLength + '\'' +
                ", materialWidth='" + materialWidth + '\'' +
                ", materialRule='" + materialRule + '\'' +
                ", isInventoryProfit='" + isInventoryProfit + '\'' +
                ", takeStockAmount=" + takeStockAmount +
                ", totalNum=" + totalNum +
                ", status=" + status +
                '}';
    }
}
