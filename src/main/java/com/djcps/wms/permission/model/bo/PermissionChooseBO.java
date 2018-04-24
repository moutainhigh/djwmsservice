package com.djcps.wms.permission.model.bo;

public class PermissionChooseBO extends BaseOrgBO{
	/**
	 * id集合
	 */
	private String ids;
	/**
	 * 公司id
	 */
	private String companyId;
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	@Override
	public String toString() {
		return "PermissionChooseBO [ids=" + ids + ", companyId=" + companyId + "]";
	}
	
	
}
