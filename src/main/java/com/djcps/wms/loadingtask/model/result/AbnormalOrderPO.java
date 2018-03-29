package com.djcps.wms.loadingtask.model.result;


import java.util.Date;
import java.util.List;

/**
 * 异常订单实体类
 * @author:wzy
 * @date:2018/3/6
 **/
public class AbnormalOrderPO {
    /**
     * 合作方id
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
     * 仓库id
     */
    private String warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     *订单数量
     */
    private Integer amount;

    /**
     *客户名称
     */
    private String customerName;

    /**
     *是否拆分 0:正常订单(默认)  1:被拆分订单 2:拆后订单
     */
    private Integer isSplit;

    /**
     *异常环节 1：入库 2：盘点 3：提货
     */
    private Integer link;

    /**
     *异常原因
     */
    private String reason;

    /**
     *异常数量
     */
    private Integer abnomalAmount;

    /**
     *处理状态 1:已处理 0:未处理
     */
    private Integer status;

    /**
     *异常处理结果
     */
        private String result;

    /**
     *装车台id
     */
    private String loadingTableId;

    /**
     *装车台名称
     */
    private String loadingTableName;

    /**
     *备注
     */
    private String remark;

    /**
     *提报人
     */
    private String submiter;

    /**
     *提报时间
     */
    private Date submitTime;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     * 订单状态
     */
    private String orderStatus;


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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getIsSplit() {
        return isSplit;
    }

    public void setIsSplit(Integer isSplit) {
        this.isSplit = isSplit;
    }

    public Integer getLink() {
        return link;
    }

    public void setLink(Integer link) {
        this.link = link;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getAbnomalAmount() {
        return abnomalAmount;
    }

    public void setAbnomalAmount(Integer abnomalAmount) {
        this.abnomalAmount = abnomalAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLoadingTableId() {
        return loadingTableId;
    }

    public void setLoadingTableId(String loadingTableId) {
        this.loadingTableId = loadingTableId;
    }

    public String getLoadingTableName() {
        return loadingTableName;
    }

    public void setLoadingTableName(String loadingTableName) {
        this.loadingTableName = loadingTableName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSubmiter() {
        return submiter;
    }

    public void setSubmiter(String submiter) {
        this.submiter = submiter;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "AbnormalOrderPO{" +
                "partnerId='" + partnerId + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", partnerArea='" + partnerArea + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", productName='" + productName + '\'' +
                ", amount=" + amount +
                ", customerName='" + customerName + '\'' +
                ", isSplit=" + isSplit +
                ", link=" + link +
                ", reason='" + reason + '\'' +
                ", abnomalAmount=" + abnomalAmount +
                ", status=" + status +
                ", result='" + result + '\'' +
                ", loadingTableId='" + loadingTableId + '\'' +
                ", loadingTableName='" + loadingTableName + '\'' +
                ", remark='" + remark + '\'' +
                ", submiter='" + submiter + '\'' +
                ", submitTime='" + submitTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", cStatus='" + orderStatus + '\'' +
                '}';
    }
}
