package com.djcps.wms.commons.base;

import java.io.Serializable;

/**
 * 安卓端消息推送
 * @company:djwms
 * @author:zdx
 * @date:2018年4月2日
 */
public class PushExtraFieldBO implements Serializable{

	private static final long serialVersionUID = -8179172554201640519L;
	
	private String userId;
	
	private Integer openType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getOpenType() {
		return openType;
	}

	public void setOpenType(Integer openType) {
		this.openType = openType;
	}

	@Override
	public String toString() {
		return "PushExtraFieldBO [userId=" + userId + ", openType=" + openType + "]";
	}
	
}
