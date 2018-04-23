package com.djcps.wms.permission.model.po;

import java.io.Serializable;

public class GetWmsPerPO implements Serializable{

	private static final long serialVersionUID = -5570401834676092111L;
	
	private String id;
	private String ptitle;
	private String polayer;
	private String pfather;
	private String pfirst;
	private String isParent;
	private String pmark;
	private String icon;
	private String pinterface;
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
	public String getPolayer() {
		return polayer;
	}
	public void setPolayer(String polayer) {
		this.polayer = polayer;
	}
	public String getPfather() {
		return pfather;
	}
	public void setPfather(String pfather) {
		this.pfather = pfather;
	}
	public String getPfirst() {
		return pfirst;
	}
	public void setPfirst(String pfirst) {
		this.pfirst = pfirst;
	}
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	public String getPmark() {
		return pmark;
	}
	public void setPmark(String pmark) {
		this.pmark = pmark;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPinterface() {
		return pinterface;
	}
	public void setPinterface(String pinterface) {
		this.pinterface = pinterface;
	}
	@Override
	public String toString() {
		return "GetWmsPerPO [id=" + id + ", ptitle=" + ptitle + ", polayer=" + polayer + ", pfather=" + pfather
				+ ", pfirst=" + pfirst + ", isParent=" + isParent + ", pmark=" + pmark + ", icon=" + icon
				+ ", pinterface=" + pinterface + "]";
	}

}
