package com.djcps.wms.permission.model.bo;

/**
 * @author zhq
 * 修改权限参数实体类
 * 2018年4月23日
 */
public class UpdatePermissionBO extends BaseOrgBO{
	/**
	 * id
	 */
	private String id;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 公司id
	 */
	private String companyId;
	/**
	 * 权限组合
	 */
	private String permissions;
	/**
	 * 组合权限名称
	 */
	private String title;
	/**
	 * 组合权限描述
	 */
	private String describe;
	/**
	 * 所属业务ID
	 */
	private String businessId;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
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

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	@Override
	public String toString() {
		return "UpdatePermissionBO{" +
				"id='" + id + '\'' +
				", userId='" + userId + '\'' +
				", companyId='" + companyId + '\'' +
				", permissions='" + permissions + '\'' +
				", title='" + title + '\'' +
				", describe='" + describe + '\'' +
				", businessId='" + businessId + '\'' +
				'}';
	}
}

