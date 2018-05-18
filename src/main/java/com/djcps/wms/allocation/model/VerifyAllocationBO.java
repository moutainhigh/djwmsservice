package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.record.model.OrderOperationRecordPO;

/**
 * 确认配货
 * 
 * @company:djwms
 * @author:zdx
 * @date:2018年1月22日
 */
public class VerifyAllocationBO extends BaseAddBO implements Serializable {

    private static final long serialVersionUID = -5147017176184795518L;

    /**
     * 所有的装车顺序
     */
    @NotEmpty
    private List<SequenceBO> orderIds;

    /**
     * 智能配货id
     */
    private String allocationId;
    /**
     * 确认配货状态
     */
    private String allocationIdEffect;
    /**
     * 确认配货时间
     */
    private String allocationIdEffectTime;

    /**
     * ======================提货单=================================
     */

    /**
     * 运单号
     */
    @NotBlank
    private String waybillId;

    /**
     * 运单创建时间
     */
    private String waybillIdCreateTime;

    /**
     * 提货单号
     */
    @NotBlank
    private String deliveryId;
    /**
     * 装车台id
     */
    @NotBlank
    private String loadingTableId;
    /**
     * 装车台名称
     */
    @NotBlank
    private String loadingTableName;
    /**
     * 提货员id
     */
    @NotBlank
    private String pickerId;
    /**
     * 提货员名称
     */
    @NotBlank
    private String pickerName;
    /**
     * 提货员联系方式
     */
    private String pickerPhone;
    /**
     * 车牌号
     */
    @NotBlank
    private String plateNumber;

    /**
     * 提货单的确认状态
     */
    private String deliveryIdEffect;

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
     * 提货单创建时间
     */
    private String deliveryCreateTime;

    /**
     * 司机姓名
     */
    @NotBlank
    private String driverName;
    /**
     * 操作记录信息
     */
    private List<OrderOperationRecordPO> list;
    /**
     * 仓库编号
     */
    private String warehouseId;
    
    
    /**
     * 修改提货数量model
     */
    private List<UpdateDeliveryAmoutBO> updateDeliveryAmoutBO;

    
    /**
     * 0,是智能配货,1是手动配货
     */
    private Integer mark;
    
	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public List<UpdateDeliveryAmoutBO> getUpdateDeliveryAmoutBO() {
		return updateDeliveryAmoutBO;
	}

	public void setUpdateDeliveryAmoutBO(List<UpdateDeliveryAmoutBO> updateDeliveryAmoutBO) {
		this.updateDeliveryAmoutBO = updateDeliveryAmoutBO;
	}

	public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public List<OrderOperationRecordPO> getList() {
        return list;
    }

    public void setList(List<OrderOperationRecordPO> list) {
        this.list = list;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPickerPhone() {
        return pickerPhone;
    }

    public void setPickerPhone(String pickerPhone) {
        this.pickerPhone = pickerPhone;
    }

    public String getLoadingPersonPhone() {
        return loadingPersonPhone;
    }

    public void setLoadingPersonPhone(String loadingPersonPhone) {
        this.loadingPersonPhone = loadingPersonPhone;
    }

    public String getWaybillIdCreateTime() {
        return waybillIdCreateTime;
    }

    public String getDeliveryCreateTime() {
        return deliveryCreateTime;
    }

    public void setDeliveryCreateTime(String deliveryCreateTime) {
        this.deliveryCreateTime = deliveryCreateTime;
    }

    public void setWaybillIdCreateTime(String waybillIdCreateTime) {
        this.waybillIdCreateTime = waybillIdCreateTime;
    }

    public List<SequenceBO> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<SequenceBO> orderIds) {
        this.orderIds = orderIds;
    }

    public String getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(String allocationId) {
        this.allocationId = allocationId;
    }

    public String getAllocationIdEffect() {
        return allocationIdEffect;
    }

    public void setAllocationIdEffect(String allocationIdEffect) {
        this.allocationIdEffect = allocationIdEffect;
    }

    public String getAllocationIdEffectTime() {
        return allocationIdEffectTime;
    }

    public void setAllocationIdEffectTime(String allocationIdEffectTime) {
        this.allocationIdEffectTime = allocationIdEffectTime;
    }

    public String getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(String waybillId) {
        this.waybillId = waybillId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDeliveryIdEffect() {
        return deliveryIdEffect;
    }

    public void setDeliveryIdEffect(String deliveryIdEffect) {
        this.deliveryIdEffect = deliveryIdEffect;
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

	@Override
	public String toString() {
		return "VerifyAllocationBO [orderIds=" + orderIds + ", allocationId=" + allocationId + ", allocationIdEffect="
				+ allocationIdEffect + ", allocationIdEffectTime=" + allocationIdEffectTime + ", waybillId=" + waybillId
				+ ", waybillIdCreateTime=" + waybillIdCreateTime + ", deliveryId=" + deliveryId + ", loadingTableId="
				+ loadingTableId + ", loadingTableName=" + loadingTableName + ", pickerId=" + pickerId + ", pickerName="
				+ pickerName + ", pickerPhone=" + pickerPhone + ", plateNumber=" + plateNumber + ", deliveryIdEffect="
				+ deliveryIdEffect + ", loadingPersonId=" + loadingPersonId + ", loadingPersonName=" + loadingPersonName
				+ ", loadingPersonPhone=" + loadingPersonPhone + ", deliveryCreateTime=" + deliveryCreateTime
				+ ", driverName=" + driverName + ", list=" + list + ", warehouseId=" + warehouseId
				+ ", updateDeliveryAmoutBO=" + updateDeliveryAmoutBO + ", mark=" + mark + "]";
	}

}
