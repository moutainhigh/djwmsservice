package com.djcps.wms.permission.model;

import org.hibernate.validator.constraints.NotBlank;

public class UserIdBO {
    /**
     * 用户id
     */
    @NotBlank
    private String userId;
    /**
     * 合作方id
     */
    @NotBlank
    private String partnerId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	@Override
	public String toString() {
		return "UserIdBO [userId=" + userId + ", partnerId=" + partnerId + "]";
	} 
}
