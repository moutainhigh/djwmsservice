package com.djcps.wms.permission.model.bo;

import java.io.Serializable;

/**
 * @author zhq
 * 删除权限实体类
 * 2018年4月23日
 */
public class DeletePermissionBO extends BaseOrgBO implements Serializable{

	private static final long serialVersionUID = -1524947916009295759L;
	
	/**
	 * 用户id
	 */
	private String userid;
	
	/**
	 * id
	 */
	private String id;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DeletePermissionBO [userid=" + userid + ", id=" + id + "]";
	}
	
	
}
