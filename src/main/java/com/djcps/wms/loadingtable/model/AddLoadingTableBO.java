package com.djcps.wms.loadingtable.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import com.djcps.wms.commons.base.BaseParam;

/**
 * @title:新增装车台对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月27日
 */
public class AddLoadingTableBO extends BaseParam implements Serializable{
	
	private static final long serialVersionUID = 8245001644412433835L;

	/**
	 *合作方id
	 */
	@NotBlank
	private String partnerId;
	
	/**
	 * 合作方名称
	 */
	@NotBlank
	private String partnerName;
	
	/**
	 * 合作方所在区域
	 */
	@NotBlank
	private String partnerArea;
	
	/**
	 * 装车台编号
	 */
	@NotBlank
	private String loadingTableId;
	
	/**
	 * 装车台名称
	 */
	@NotBlank
	private String name;
	
	/**
	 * 装车台状态(1.使用中 2.空闲中)
	 */
	@NotBlank
	private String state; 
	
	/**
	 * 装车台可用该车辆规格数组
	 */
	@NotBlank
	private String specs;
	
	/**
	 * 装车台禁用状态
	 */
	private String effect;
	
	/**
	 * 删除状态
	 */
	private String delete;
	
	/**
	 * 操作人id
	 */
	@NotBlank
	private String operatorId; 
	
	/**
	 * 操作人
	 */
	@NotBlank
	private String operator;

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

	public String getLoadingTableId() {
		return loadingTableId;
	}

	public void setLoadingTableId(String loadingTableId) {
		this.loadingTableId = loadingTableId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
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
		return "AddBean [partnerId=" + partnerId + ", partnerName=" + partnerName + ", partnerArea=" + partnerArea
				+ ", loadingTableId=" + loadingTableId + ", name=" + name + ", state=" + state + ", specs=" + specs
				+ ", effect=" + effect + ", delete=" + delete + ", operatorId=" + operatorId + ", operator=" + operator
				+ "]";
	}
	
}
