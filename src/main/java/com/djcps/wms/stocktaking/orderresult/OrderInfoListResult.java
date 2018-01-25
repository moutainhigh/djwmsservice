package com.djcps.wms.stocktaking.orderresult;

public class OrderInfoListResult {

    private String inventoryClerkId;

    private String inventoryClerk;

    private String remark;

    private TaskOrderInfo taskOrderInfo;

    public String getInventoryClerkId() {
        return inventoryClerkId;
    }

    public void setInventoryClerkId(String inventoryClerkId) {
        this.inventoryClerkId = inventoryClerkId;
    }

    public String getInventoryClerk() {
        return inventoryClerk;
    }

    public void setInventoryClerk(String inventoryClerk) {
        this.inventoryClerk = inventoryClerk;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public TaskOrderInfo getTaskOrderInfo() {
        return taskOrderInfo;
    }

    public void setTaskOrderInfo(TaskOrderInfo taskOrderInfo) {
        this.taskOrderInfo = taskOrderInfo;
    }

    @Override
    public String toString() {
        return "OrderInfoListResult{" +
                "inventoryClerkId='" + inventoryClerkId + '\'' +
                ", inventoryClerk='" + inventoryClerk + '\'' +
                ", remark='" + remark + '\'' +
                ", taskOrderInfo=" + taskOrderInfo +
                '}';
    }
}
