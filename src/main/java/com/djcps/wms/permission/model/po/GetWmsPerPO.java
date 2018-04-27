package com.djcps.wms.permission.model.po;

import java.io.Serializable;

/**
 * @author zhq
 * 从ORG得到WMS权限参数实体类
 * 2018年4月23日
 */
public class GetWmsPerPO implements Serializable{

	private static final long serialVersionUID = -5570401834676092111L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 标题
	 */
	private String ptitle;
	/**
	 * 层级
	 */
	private String polayer;
	/**
	 * 父节点
	 */
	private String pfather;
	/**
	 * 上层节点
	 */
	private String pfirst;
	/**
	 * 是否是父类
	 */
	private String isParent;
	/**
	 * 标记
	 */
	private String pmark;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 接口
	 */
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
