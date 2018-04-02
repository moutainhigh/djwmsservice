package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 装车优化确认配货合并逻辑对象
 * @company:djwms
 * @author:zdx
 * @date:2018年2月4日
 */
public class MergeModelBO extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = 5391909362682990757L;
	
	/**
	 * 装车优化确认配货
	 */
	private List<AgainVerifyAllocationBO> againVerifyAllocation;
	
	/**
	 * 装车优化移除订单
	 */
	private MoveOrderPO moveOrder;
	
	/**
	 * 装车优化再次追加订单
	 */
	private AgainVerifyAddOrderBO againVerifyAddOrder;
	
	/**
	 * 装车顺序
	 */
	private List<SequenceBO> sequenceList;
	
	/**
	 * 智能配货id
	 */
	private String allocationId;
	/**
	 * 运单号
	 */
	@NotBlank
	private String waybillId;
	/**
	 * 提货单号
	 */
	private String newDeliveryId;
	/**
	 * 装车台id
	 */
	private String loadingTableId;
	/**
	 * 装车台名称
	 */
	private String loadingTableName;
	/**
	 * 提货员id
	 */
	private String pickerId;
	/**
	 * 提货员名称
	 */
	private String pickerName;
	/**
	 * 提货员联系方式
	 */
	private String pickerPhone;
	
	/**
	 * 车牌号
	 */
	private String plateNumber;
	/**
	 * 装车员id
	 */
	private String loadingPersonId;
	/**
	 * 装车员名称
	 */
	private String loadingPersonName;
	/**
	 * 装车员联系方式
	 */
	private String loadingPersonPhone;
	
	/**
	 * 区分追加订单处理界面和装车优化界面的标识
	 */
	private String flag;
	
	public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<SequenceBO> getSequenceList() {
		return sequenceList;
	}

	public void setSequenceList(List<SequenceBO> sequenceList) {
		this.sequenceList = sequenceList;
	}
	public String getAllocationId() {
		return allocationId;
	}
	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}
	public String getNewDeliveryId() {
		return newDeliveryId;
	}

	public void setNewDeliveryId(String newDeliveryId) {
		this.newDeliveryId = newDeliveryId;
	}

	public List<AgainVerifyAllocationBO> getAgainVerifyAllocation() {
		return againVerifyAllocation;
	}

	public void setAgainVerifyAllocation(List<AgainVerifyAllocationBO> againVerifyAllocation) {
		this.againVerifyAllocation = againVerifyAllocation;
	}

	public MoveOrderPO getMoveOrder() {
		return moveOrder;
	}

	public void setMoveOrder(MoveOrderPO moveOrder) {
		this.moveOrder = moveOrder;
	}

	public AgainVerifyAddOrderBO getAgainVerifyAddOrder() {
		return againVerifyAddOrder;
	}

	public void setAgainVerifyAddOrder(AgainVerifyAddOrderBO againVerifyAddOrder) {
		this.againVerifyAddOrder = againVerifyAddOrder;
	}

	public String getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
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

	public String getPickerId() {
		return pickerId;
	}

	public void setPickerId(String pickerId) {
		this.pickerId = pickerId;
	}

	public String getPickerName() {
		return pickerName;
	}

	public void setPickerName(String pickerName) {
		this.pickerName = pickerName;
	}

	public String getPickerPhone() {
		return pickerPhone;
	}

	public void setPickerPhone(String pickerPhone) {
		this.pickerPhone = pickerPhone;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getLoadingPersonId() {
		return loadingPersonId;
	}

	public void setLoadingPersonId(String loadingPersonId) {
		this.loadingPersonId = loadingPersonId;
	}

	public String getLoadingPersonName() {
		return loadingPersonName;
	}

	public void setLoadingPersonName(String loadingPersonName) {
		this.loadingPersonName = loadingPersonName;
	}

	public String getLoadingPersonPhone() {
		return loadingPersonPhone;
	}

	public void setLoadingPersonPhone(String loadingPersonPhone) {
		this.loadingPersonPhone = loadingPersonPhone;
	}

    @Override
    public String toString() {
        return "MergeModelBO [againVerifyAllocation=" + againVerifyAllocation + ", moveOrder=" + moveOrder
                + ", againVerifyAddOrder=" + againVerifyAddOrder + ", sequenceList=" + sequenceList + ", allocationId="
                + allocationId + ", waybillId=" + waybillId + ", newDeliveryId=" + newDeliveryId + ", loadingTableId="
                + loadingTableId + ", loadingTableName=" + loadingTableName + ", pickerId=" + pickerId + ", pickerName="
                + pickerName + ", pickerPhone=" + pickerPhone + ", plateNumber=" + plateNumber + ", loadingPersonId="
                + loadingPersonId + ", loadingPersonName=" + loadingPersonName + ", loadingPersonPhone="
                + loadingPersonPhone + ", flag=" + flag + "]";
    }

}
