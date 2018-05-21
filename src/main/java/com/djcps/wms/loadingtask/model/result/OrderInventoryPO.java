package com.djcps.wms.loadingtask.model.result;

/**
 * 插入t_cborder_inventory
 * @author:wzy
 * @date:2018/3/26
 **/
public class OrderInventoryPO {
    private String partnerId;
    private String partnerName;
    private String partnerArea;
    private String orderId;
    private String splitorderId;
    private Integer issplit;
    private Integer amount;
    private Integer amountSaved;
    private Integer amountInStock;
    private Integer amountOutStock;
    private String warehouseId;
    private String warehouseName;
    private String remark;
    private String operatorId;
    private String operator;

    /**
     * 新订单id-1
     */
    private String onceOrderid;

    /**
     * 新订单id-2
     */
    private String twiceOrderid;

    public String getOnceOrderid() {
        return onceOrderid;
    }

    public void setOnceOrderid(String onceOrderid) {
        this.onceOrderid = onceOrderid;
    }

    public String getTwiceOrderid() {
        return twiceOrderid;
    }

    public void setTwiceOrderid(String twiceOrderid) {
        this.twiceOrderid = twiceOrderid;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSplitorderId() {
        return splitorderId;
    }

    public void setSplitorderId(String splitorderId) {
        this.splitorderId = splitorderId;
    }

    public Integer getIssplit() {
        return issplit;
    }

    public void setIssplit(Integer issplit) {
        this.issplit = issplit;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmountSaved() {
        return amountSaved;
    }

    public void setAmountSaved(Integer amountSaved) {
        this.amountSaved = amountSaved;
    }

    public Integer getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(Integer amountInStock) {
        this.amountInStock = amountInStock;
    }

    public Integer getAmountOutStock() {
        return amountOutStock;
    }

    public void setAmountOutStock(Integer amountOutStock) {
        this.amountOutStock = amountOutStock;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "OrderInventoryBO{" +
                "partnerId='" + partnerId + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", partnerArea='" + partnerArea + '\'' +
                ", orderId='" + orderId + '\'' +
                ", splitorderId='" + splitorderId + '\'' +
                ", issplit=" + issplit +
                ", amount='" + amount + '\'' +
                ", amountSaved=" + amountSaved +
                ", amountInStock=" + amountInStock +
                ", amountOutStock='" + amountOutStock + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", remark='" + remark + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", operator='" + operator + '\'' +
                ", onceOrderid='" + onceOrderid + '\'' +
                ", twiceOrderid='" + twiceOrderid + '\'' +
                '}';
    }
}
