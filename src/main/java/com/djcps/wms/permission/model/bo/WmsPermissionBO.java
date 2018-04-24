package com.djcps.wms.permission.model.bo;

/**
 * @author zhq
 * 获取WMS权限接口传递的参数实体类
 * 2018年4月23日
 */
public class WmsPermissionBO extends BaseOrgParamBO{
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
		return "WmsPermissionBO [firstnode=" + firstnode + "]";
	}
}
