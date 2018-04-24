package com.djcps.wms.permission.model.bo;

/**
 * @author zhq
 * 删除权限参数实体类
 * 2018年4月23日
 */
public class DeletePerParamBO extends BaseOrgParamBO{
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
		return "DeletePerParamBO [userid=" + userid + ", id=" + id + "]";
	}
	
}
