package com.djcps.wms.permission.model;

import java.io.Serializable;

public class UserInfoPO implements Serializable{

	private static final long serialVersionUID = -1663272155169476522L;

	private String uname;
	
	private String id;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserInfoPO [uname=" + uname + ", id=" + id + "]";
	}
}
