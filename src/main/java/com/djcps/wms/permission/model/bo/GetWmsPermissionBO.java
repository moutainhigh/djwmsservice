package com.djcps.wms.permission.model.bo;

/**
 * @author zhq
 * 得到WMS基础权限实体类
 * 2018年4月23日
 */
public class GetWmsPermissionBO extends BaseOrgBO{
	/**
	 * 上级节点
	 */
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
