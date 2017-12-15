package com.djcps.wms.warehouse.model.warehouse;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseParam;
import com.djcps.wms.commons.base.BaseUpdateAndDeleteBo;

/**
 * @title:仓库修改对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月29日
 */
public class UpdateWarehouseBO extends BaseUpdateAndDeleteBo implements Serializable{

	private static final long serialVersionUID = 8459493360763354343L;
	
	/**
	 * 唯一标识
	 */
	@NotBlank
	private String id;
	
	/**
	 * 仓库名称,最多10个字
	 */
	@NotBlank
	private String name;
	
	/**
	 * 仓库状态 1.使用中  2.已暂停
	 */
	private String state;
	
	/**
	 * 联系人,最多10个字
	 */
	private String contacts;
	
	/**
	 * 座机号码,最多15个字
	 */
	private String tel;
	
	/**
	 * 手机号码,以1开头的11位数字
	 */
	private String phone;
	
	/**
	 * 备注,最多50个字
	 */
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "UpdateWarehouseBO [id=" + id + ", name=" + name + ", state=" + state + ", contacts=" + contacts
				+ ", tel=" + tel + ", phone=" + phone + ", remark=" + remark + "]";
	}


}
