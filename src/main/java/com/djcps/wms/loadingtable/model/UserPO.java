package com.djcps.wms.loadingtable.model;

import java.io.Serializable;

/**
 * 用户
 * @company:djwms
 * @author:zdx
 * @date:2018年3月22日
 */
public class UserPO implements Serializable{

	private static final long serialVersionUID = -8955651059294769300L;
	
	/**
     * 绑定的账户名称
     */
    private String bindingUserName;
    
    /**
     * 绑定的用户表的fid
     */
    private String bindingUserId;

	public String getBindingUserName() {
		return bindingUserName;
	}

	public void setBindingUserName(String bindingUserName) {
		this.bindingUserName = bindingUserName;
	}

	public String getBindingUserId() {
		return bindingUserId;
	}

	public void setBindingUserId(String bindingUserId) {
		this.bindingUserId = bindingUserId;
	}

	@Override
	public String toString() {
		return "UserPO [bindingUserName=" + bindingUserName + ", bindingUserId=" + bindingUserId + "]";
	}

}
