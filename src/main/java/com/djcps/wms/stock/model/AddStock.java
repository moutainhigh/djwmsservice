package com.djcps.wms.stock.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseAddBo;

/**
 * 入库对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月22日
 */
public class AddStock extends BaseAddBo implements Serializable{
	
	private static final long serialVersionUID = 5650542988348663815L;
	
	/**
	 * 订单编号
	 */
	@NotBlank
	private String orderId;
	/**
	 * 订单数量
	 */
	@NotBlank
	private String amount;
	/**
	 * 准备入库数量
	 */
	@NotBlank
	private String amountSave;
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
	 * 操作记录信息集合
	 */
	@NotNull
	private OperationRecordBo operationRecord;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAmountSave() {
		return amountSave;
	}
	public void setAmountSave(String amountSave) {
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
	public OperationRecordBo getOperationRecord() {
		return operationRecord;
	}
	public void setOperationRecord(OperationRecordBo operationRecord) {
		this.operationRecord = operationRecord;
	}
	@Override
	public String toString() {
		return "AddStock [orderId=" + orderId + ", amount=" + amount + ", amountSave=" + amountSave + ", warehouseId="
				+ warehouseId + ", warehouseName=" + warehouseName + ", remark=" + remark + ", warehouseAreaId="
				+ warehouseAreaId + ", warehouseAreaName=" + warehouseAreaName + ", warehouseLocId=" + warehouseLocId
				+ ", warehouseLocName=" + warehouseLocName + ", operationRecord=" + operationRecord + "]";
	}
	
}
