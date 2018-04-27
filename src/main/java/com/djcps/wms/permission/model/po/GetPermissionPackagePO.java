package com.djcps.wms.permission.model.po;

import java.io.Serializable;

/**
 * @author zhq
 * 获得权限包实体类
 * 2018年4月18日
 */
public class GetPermissionPackagePO implements Serializable{

	private static final long serialVersionUID = -2487908610836462651L;
    
    private String id;
    
	private String ptitle;
	
	private String pdes;
	
	private String perlist;
	
	private String pbussion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getPerlist() {
		return perlist;
	}

	public void setPerlist(String perlist) {
		this.perlist = perlist;
	}

	public String getPbussion() {
		return pbussion;
	}

	public void setPbussion(String pbussion) {
		this.pbussion = pbussion;
	}

	@Override
	public String toString() {
		return "GetPermissionPackagePO [id=" + id + ", ptitle=" + ptitle + ", pdes=" + pdes + ", perlist=" + perlist
				+ ", pbussion=" + pbussion + "]";
	}
	
}
