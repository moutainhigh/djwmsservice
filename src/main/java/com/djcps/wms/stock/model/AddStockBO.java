package com.djcps.wms.stock.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 入库对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月22日
 */
public class AddStockBO extends BaseAddBO implements Serializable{
	
	private static final long serialVersionUID = 5650542988348663815L;
	
	private String id;
	
	/**
	 * 订单编号
	 */
	@NotBlank
	private String orderId;
	/**
	 * 订单数量
	 */
	@NotNull
	private Integer amount;
	/**
	 * 准备入库数量
	 */
	@NotNull
	private Integer amountSave;
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
	 * 备注
	 */
	private String remark;

	/**
	 * 库区编号
	 */
	@NotBlank
	private String warehouseAreaId;
	/**
	 * 库区名称
	 */
	@NotBlank
	private String warehouseAreaName;
	/**
	 * 库位编码
	 */
	@NotBlank
	private String warehouseLocId;
	/**
	 * 库位名称
	 */
	@NotBlank
	private String warehouseLocName;
	
	/**
	 * 推荐库区编码
	 */
	private String recommendAreaId;
	
	/**
	 * 推荐库区名称
	 */
	private String recommendAreaName;
	
	/**
	 * 操作记录信息集合
	 */
	private OperationRecordBO operationRecord;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getAmountSave() {
		return amountSave;
	}
	public void setAmountSave(Integer amountSave) {
		this.amountSave = amountSave;
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
	public String getWarehouseAreaId() {
		return warehouseAreaId;
	}
	public void setWarehouseAreaId(String warehouseAreaId) {
		this.warehouseAreaId = warehouseAreaId;
	}
	public String getWarehouseAreaName() {
		return warehouseAreaName;
	}
	public void setWarehouseAreaName(String warehouseAreaName) {
		this.warehouseAreaName = warehouseAreaName;
	}
	public String getWarehouseLocId() {
		return warehouseLocId;
	}
	public void setWarehouseLocId(String warehouseLocId) {
		this.warehouseLocId = warehouseLocId;
	}
	public String getWarehouseLocName() {
		return warehouseLocName;
	}
	public void setWarehouseLocName(String warehouseLocName) {
		this.warehouseLocName = warehouseLocName;
	}
	public OperationRecordBO getOperationRecord() {
		return operationRecord;
	}
	public void setOperationRecord(OperationRecordBO operationRecord) {
		this.operationRecord = operationRecord;
	}
	public String getRecommendAreaId() {
		return recommendAreaId;
	}
	public void setRecommendAreaId(String recommendAreaId) {
		this.recommendAreaId = recommendAreaId;
	}
	public String getRecommendAreaName() {
		return recommendAreaName;
	}
	public void setRecommendAreaName(String recommendAreaName) {
		this.recommendAreaName = recommendAreaName;
	}
	@Override
	public String toString() {
		return "AddStockBO [id=" + id + ", orderId=" + orderId + ", amount=" + amount + ", amountSave=" + amountSave
				+ ", warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", remark=" + remark
				+ ", warehouseAreaId=" + warehouseAreaId + ", warehouseAreaName=" + warehouseAreaName
				+ ", warehouseLocId=" + warehouseLocId + ", warehouseLocName=" + warehouseLocName + ", recommendAreaId="
				+ recommendAreaId + ", recommendAreaName=" + recommendAreaName + ", operationRecord=" + operationRecord
				+ "]";
	}
	
}
