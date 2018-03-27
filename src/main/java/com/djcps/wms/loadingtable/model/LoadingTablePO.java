package com.djcps.wms.loadingtable.model;

import java.io.Serializable;

/**
 * 该类为装车台实体类
 * 
 * @author wyb
 * @version 1.0
 * @since 2017/11/23
 */
public class LoadingTablePO implements Serializable {

    private static final long serialVersionUID = -4453443522314318715L;
    /**
     * 合作方id
     */
    private String partnerId;
    /**
     * 合作方名称
     */
    private String partnerName;
    /**
     * 合作方所在区域
     */
    private String partnerArea;
    /**
     * 操作人id
     */
    private String operatorId;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作时间
     */
    private String operatorTime;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 装车台唯一标识
     */
    private String id;
    /**
     * 装车台编号
     */
    private String loadingTableId;
    /**
     * 装车台名称
     */
    private String name;
    /**
     * 装车台状态
     */
    private Integer status;
    /**
     * 装车台规格组数
     */
    private String specs;
    /**
     * 装车台禁用启用状态
     */
    private Integer effect;
    /**
     * 删除状态
     */
    private Integer delete;
    
    /**
     * 绑定的账户名称
     */
    private String bindingUserName;
    
    /**
     * 绑定的用户表的fid
     */
    private String bindingUserId;
    
    public String getBindingUserId() {
		return bindingUserId;
	}

	public void setBindingUserId(String bindingUserId) {
		this.bindingUserId = bindingUserId;
	}

	public String getBindingUserName() {
		return bindingUserName;
	}

	public void setBindingUserName(String bindingUserName) {
		this.bindingUserName = bindingUserName;
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

    public String getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(String operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public Integer getEffect() {
        return effect;
    }

    public void setEffect(Integer effect) {
        this.effect = effect;
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

	@Override
	public String toString() {
		return "LoadingTablePO [partnerId=" + partnerId + ", partnerName=" + partnerName + ", partnerArea="
				+ partnerArea + ", operatorId=" + operatorId + ", operator=" + operator + ", operatorTime="
				+ operatorTime + ", createTime=" + createTime + ", updateTime=" + updateTime + ", id=" + id
				+ ", loadingTableId=" + loadingTableId + ", name=" + name + ", status=" + status + ", specs=" + specs
				+ ", effect=" + effect + ", delete=" + delete + ", bindingUserName=" + bindingUserName
				+ ", bindingUserId=" + bindingUserId + "]";
	}
}
