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
    private String materialname;

    /**
     * 下料规格长
     */
    private String materiallength;

    /**
     * 下料规格宽
     */
    private String materialwidth;

    /**
     * 下料规格
     */
    private String materialRule;

    /**
     * 是否盘盈
     */
    private String isInventoryProfit;


    /**
     * 盘点总数
     */
    private Integer total;

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

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    public String getMateriallength() {
        return materiallength;
    }

    public void setMateriallength(String materiallength) {
        this.materiallength = materiallength;
    }

    public String getMaterialwidth() {
        return materialwidth;
    }

    public void setMaterialwidth(String materialwidth) {
        this.materialwidth = materialwidth;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PdaStocktakingOrderBO{" +
                "orderId='" + orderId + '\'' +
                ", productName='" + productName + '\'' +
                ", materialname='" + materialname + '\'' +
                ", materiallength='" + materiallength + '\'' +
                ", materialwidth='" + materialwidth + '\'' +
                ", materialRule='" + materialRule + '\'' +
                ", isInventoryProfit='" + isInventoryProfit + '\'' +
                ", total=" + total +
                '}';
    }
}
