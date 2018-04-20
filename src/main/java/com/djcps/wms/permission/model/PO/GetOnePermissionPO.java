package com.djcps.wms.permission.model.PO;

import java.io.Serializable;

public class GetOnePermissionPO implements Serializable{

	private static final long serialVersionUID = 3216139664002767218L;
	
	private String ptitle;
	private String pdes;
	private String pcompany;
	private String puserid;
	private String pperlist;
	private String pbussion;
	private String id;
	private String isdel;
	private String isdel_per;
	private String create_time;
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
