package com.djcps.wms.permission.model.bo;

import java.io.Serializable;

/**
 * @author zhq
 * 根据组合权限id和公司id，获取获取组合权限集合实体类
 * 2018年4月17日
 */
public class GetPermissionChooseBO extends BaseOrgParamBO implements Serializable{

	private static final long serialVersionUID = 3875308314077375040L;
	/**
	 * id集合
	 */
	private String ids;
	/**
	 * 公司id
	 */
	private String companyID;
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	@Override
	public String toString() {
		return "GetPermissionChooseBO [ids=" + ids + ", companyID=" + companyID + "]";
	}

}
