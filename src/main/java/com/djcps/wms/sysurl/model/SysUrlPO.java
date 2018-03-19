package com.djcps.wms.sysurl.model;

import java.io.Serializable;

/**
 * 系统url对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月13日
 */
public class SysUrlPO implements Serializable{
	
	private static final long serialVersionUID = -6949618255180508493L;

	/**
	 * id唯一字段
	 */
	private String id;
	
	/**
	 * url 名称
	 */
	private String name;
	
	/**
	 * url
	 */
	private String url;
	
	/**
	 * 0:需要用户登录（默认），1：不需要登录
	 */
	private Integer loginType;
	
	/**
	 * url状态-》0：失效，1：生效(默认)
	 */
	private Integer effect;
	
	/**
	 * 路径的用户类型-》0：未知（默认），1：本部使用的路径，2：合作方使用的路径
	 */
	private Integer userType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public Integer getEffect() {
		return effect;
	}

	public void setEffect(Integer effect) {
		this.effect = effect;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
}
