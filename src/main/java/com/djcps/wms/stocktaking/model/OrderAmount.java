package com.djcps.wms.stocktaking.model;

import java.io.Serializable;

/**
 * @title:盘点订单各个状态数量
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/13
 **/
public class OrderAmount implements Serializable{


    private static final long serialVersionUID = 4801582070621922067L;

    /**
     * 已完成订单
     */
    private Integer completeStocktakingAmount;

    /**
     * 已盘点订单
     */
    private Integer unfinishedStocktakingAmount;

    /**
     * 盘盈订单
     */
    private Integer inventoryProfitAmount;

    public Integer getCompleteStocktakingAmount() {
        return completeStocktakingAmount;
    }

    public void setCompleteStocktakingAmount(Integer completeStocktakingAmount) {
        this.completeStocktakingAmount = completeStocktakingAmount;
    }

    public Integer getUnfinishedStocktakingAmount() {
        return unfinishedStocktakingAmount;
    }

    public void setUnfinishedStocktakingAmount(Integer unfinishedStocktakingAmount) {
        this.unfinishedStocktakingAmount = unfinishedStocktakingAmount;
    }

    public Integer getInventoryProfitAmount() {
        return inventoryProfitAmount;
    }

    public void setInventoryProfitAmount(Integer inventoryProfitAmount) {
        this.inventoryProfitAmount = inventoryProfitAmount;
    }

    @Override
    public String toString() {
        return "OrderAmount{" +
                "completeStocktakingAmount=" + completeStocktakingAmount +
                ", unfinishedStocktakingAmount=" + unfinishedStocktakingAmount +
                ", inventoryProfitAmount=" + inventoryProfitAmount +
                '}';
    }
}
