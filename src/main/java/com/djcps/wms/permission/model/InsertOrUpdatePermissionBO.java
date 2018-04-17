package com.djcps.wms.permission.model;

import java.io.Serializable;

/**
 * @author zhq
 * 新增/修改权限实体类
 * 2018年4月12日
 */
public class InsertOrUpdatePermissionBO extends BaseOrgBO implements Serializable{

	private static final long serialVersionUID = 337658715645866921L;
	
	/**
	 * id
	 */
	private String id;
	/**
	 * 用户id
	 */
	private String userid;
	/**
	 * 公司id
	 */
	private String companyID;
	/**
	 * 权限组合
	 */
	private String permissions;
	/**
	 * 组合权限名称
	 */
	private String ptitle;
	/**
	 * 组合权限描述
	 */
	private String pdes;
	/**
	 * 所属业务ID
	 */
	private String pbussion;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPdes() {
		return pdes;
	}
	public void setPdes(String pdes) {
		this.pdes = pdes;
	}
	public String getPbussion() {
		return pbussion;
	}
	public void setPbussion(String pbussion) {
		this.pbussion = pbussion;
	}
	@Override
	public String toString() {
		return "InsertOrUpdatePermissionBO [id=" + id + ", userid=" + userid + ", companyID=" + companyID
				+ ", permissions=" + permissions + ", ptitle=" + ptitle + ", pdes=" + pdes + ", pbussion=" + pbussion
				+ "]";
	}
	
	
}
