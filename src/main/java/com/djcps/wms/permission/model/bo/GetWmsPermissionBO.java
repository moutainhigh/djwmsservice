package com.djcps.wms.permission.model.bo;

public class GetWmsPermissionBO extends BaseOrgBO{
	
	private String firstnode;

	public String getFirstnode() {
		return firstnode;
	}

	public void setFirstnode(String firstnode) {
		this.firstnode = firstnode;
	}

	@Override
	public String toString() {
		return "GetWmsPermissionBO [firstnode=" + firstnode + "]";
	}
		
}
