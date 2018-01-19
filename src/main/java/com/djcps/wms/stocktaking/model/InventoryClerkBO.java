package com.djcps.wms.stocktaking.model;

/**
 * @title:盘点员
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/15
 **/
public class InventoryClerkBO {

    /**
     * 盘点员名
     */
   private String inventoryClerk;

    /**
     * 盘点员id
     */
   private String inventoryClerkId;

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

    @Override
    public String toString() {
        return "InventoryClerkBO{" +
                "inventoryClerk='" + inventoryClerk + '\'' +
                ", inventoryClerkId='" + inventoryClerkId + '\'' +
                '}';
    }
}
