package com.djcps.wms.permission.model.vo;

import java.io.Serializable;

/**
 * @author zhq
 * 将得到详细的权限字段转化
 * 2018年4月19日
 */
public class ChangeOnePerVO implements Serializable{

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 描述
	 */
	private String describe;

	/**
	 * 公司id
	 */
	private String companyId;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 权限集合
	 */
	private String perList;

	/**
	 * 业务
	 */
	private String business;

	/**
	 * id
	 */
	private String id;

	/**
	 * 是否删除
	 */
	private String isDel;

	/**
	 * 删除人
	 */
	private String deletePerson;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 更新时间
	 */
	private String updateTime;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPerList() {
		return perList;
	}

	public void setPerList(String perList) {
		this.perList = perList;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getDeletePerson() {
		return deletePerson;
	}

	public void setDeletePerson(String deletePerson) {
		this.deletePerson = deletePerson;
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

	@Override
	public String toString() {
		return "ChangeOnePerPO [title=" + title + ", describe=" + describe + ", companyId=" + companyId + ", userId="
				+ userId + ", perList=" + perList + ", business=" + business + ", id=" + id + ", isDel=" + isDel
				+ ", deletePerson=" + deletePerson + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
}
