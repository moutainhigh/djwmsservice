package com.djcps.wms.permission.model.vo;

import java.io.Serializable;
/**
 * @author zhq
 * 将得到的WMS权限字段转化
 * 2018年4月19日
 */
public class ChangeWmsPerVO implements Serializable{
	
	private String id;
	
	private String title;
	/**
	 * 层级，供前端构造树状结构
	 */
	private String layer;
	/**
	 * 父节点id
	 */
	private String fatherId;
	/**
	 * 顶层节点
	 */
	private String firstId;
	/**
	 * 是否是父节点
	 */
	private String isParent;
	
	private String mark;
	
	private String icon;
	/**
	 * 接口信息
	 */
	private String interfaceInfo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLayer() {
		return layer;
	}
	public void setLayer(String layer) {
		this.layer = layer;
	}
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	public String getFirstId() {
		return firstId;
	}
	public void setFirstId(String firstId) {
		this.firstId = firstId;
	}
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getInterfaceInfo() {
		return interfaceInfo;
	}
	public void setInterfaceInfo(String interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}
	@Override
	public String toString() {
		return "ChangeWmsPerPO [id=" + id + ", title=" + title + ", layer=" + layer + ", fatherId=" + fatherId
				+ ", firstId=" + firstId + ", isParent=" + isParent + ", mark=" + mark + ", icon=" + icon
				+ ", interfaceInfo=" + interfaceInfo + "]";
	}
}
