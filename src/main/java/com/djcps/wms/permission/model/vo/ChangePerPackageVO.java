package com.djcps.wms.permission.model.vo;

import java.io.Serializable;
/**
 * @author zhq
 * 将得到的权限包数据字段转化
 * 2018年4月18日
 */
public class ChangePerPackageVO implements Serializable {

	private static final long serialVersionUID = -1614555952685626481L;

	private String id;

	private String title;

	private String describe;

	private String perList;

	private String bussion;

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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getPerList() {
		return perList;
	}

	public void setPerList(String perList) {
		this.perList = perList;
	}

	public String getBussion() {
		return bussion;
	}

	public void setBussion(String bussion) {
		this.bussion = bussion;
	}

	@Override
	public String toString() {
		return "ChangePerPackagePO [id=" + id + ", title=" + title + ", describe=" + describe + ", perList=" + perList
				+ ", bussion=" + bussion + "]";
	}
}
