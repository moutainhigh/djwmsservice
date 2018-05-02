package com.djcps.wms.abnormal.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 *	新增异常订单
 * @company:djwms
 * @author:zdx
 * @date:2018年3月7日
 */
public class AddAbnormal extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = 3388158413759565152L;
	
   /**
     * 仓库id
     */
    @NotBlank
    private String warehouseId;

    /**
     * 仓库名称
     */
    @NotBlank
    private String warehouseName;

    /**
     * 订单编号
     */
    @NotBlank
    private String orderId;

    /**
     * 产品名称
     */
    @NotBlank
    private String productName;

    /**
     *订单数量
     */
    @NotNull
    private Integer amount;

    /**
     *客户名称
     */
    @NotBlank
    private String customerName;

    /**
     *是否拆分 0:正常订单(默认)  1:被拆分订单 2:拆后订单
     */
    @NotNull
    private Integer isSplit;

    /**
     *异常环节 1：入库 2：盘点 3：提货
     */
    @NotBlank
    private String link;

    /**
     *异常原因
     */
    @NotBlank
    private String reason;

    /**
     *异常数量
     */
    @NotNull
    private Integer abnomalAmount;

    /**
     *异常处理结果
     */
    private String result;

    /**
     *备注
     */
    private String remark;

    private String loadingTableId;
    
    private String loadingTableName;
    
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "AddAbnormal [warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", orderId=" + orderId
				+ ", productName=" + productName + ", amount=" + amount + ", customerName=" + customerName
				+ ", isSplit=" + isSplit + ", link=" + link + ", reason=" + reason + ", abnomalAmount=" + abnomalAmount
				+ ", result=" + result + ", remark=" + remark + ", loadingTableId=" + loadingTableId
				+ ", loadingTableName=" + loadingTableName + "]";
	}
    
}
