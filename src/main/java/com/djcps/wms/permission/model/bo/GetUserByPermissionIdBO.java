package com.djcps.wms.permission.model.bo;

import java.io.Serializable;

/**
 * @author zhq
 * 根据权限id获取用户参数类
 * 2018年4月17日
 */
public class GetUserByPermissionIdBO extends BaseOrgParamBO implements Serializable{

	private static final long serialVersionUID = -8287143395016871423L;
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GetUserByPermissionId [id=" + id + "]";
	}
	
}
