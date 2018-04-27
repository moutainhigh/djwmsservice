package com.djcps.wms.permission.model.po;

import java.io.Serializable;

/**
 * @author zhq
 * 从ORG接受批量权限数据实体类
 * 2018年4月23日
 */
public class GetOnePermissionPO implements Serializable {

	private static final long serialVersionUID = 3216139664002767218L;
	/**
	 * 标题
	 */
	private String ptitle;
	/**
	 * 描述
	 */
	private String pdes;
	/**
	 * 公司
	 */
	private String pcompany;
	/**
	 * 用户id
	 */
	private String puserid;
	/**
	 * 权限集合
	 */
	private String pperlist;
	/**
	 * 业务
	 */
	private String pbussion;
	/**
	 * id
	 */
	private String id;
	/**
	 * 是否删除
	 */
	private String isdel;
	/**
	 * 删除人
	 */
	private String isdel_per;
	/**
	 * 创建时间
	 */
	private String create_time;
	/**
	 * 更新时间
	 */
	private String update_time;

	public String getPtitle() {
		return ptitle;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public String getPdes() {
		return pdes;
	}

	public void setPdes(String pdes) {
		this.pdes = pdes;
	}

	public String getPcompany() {
		return pcompany;
	}

	public void setPcompany(String pcompany) {
		this.pcompany = pcompany;
	}

	public String getPuserid() {
		return puserid;
	}

	public void setPuserid(String puserid) {
		this.puserid = puserid;
	}

	public String getPperlist() {
		return pperlist;
	}

	public void setPperlist(String pperlist) {
		this.pperlist = pperlist;
	}

	public String getPbussion() {
		return pbussion;
	}

	public void setPbussion(String pbussion) {
		this.pbussion = pbussion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public String getIsdel_per() {
		return isdel_per;
	}

	public void setIsdel_per(String isdel_per) {
		this.isdel_per = isdel_per;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	@Override
	public String toString() {
		return "GetOnePermissionPO [ptitle=" + ptitle + ", pdes=" + pdes + ", pcompany=" + pcompany + ", puserid="
				+ puserid + ", pperlist=" + pperlist + ", pbussion=" + pbussion + ", id=" + id + ", isdel=" + isdel
				+ ", isdel_per=" + isdel_per + ", create_time=" + create_time + ", update_time=" + update_time + "]";
	}
}
