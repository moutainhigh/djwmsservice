package com.djcps.wms.warehouse.model.warehouse;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBo;

/**
 * @title:仓库新增对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月29日
 */
public class AddWarehouseBO extends BaseAddBo implements Serializable{

	private static final long serialVersionUID = -3191296476292183363L;
	
	/**
	 * 仓库编码
	 */
	@NotBlank
	private String warehouseId;
	
	/**
	 * 仓库名称,最多10个字
	 */
	@NotBlank
	private String name;
	
	/**
	 * 仓库类型
	 */
	@NotBlank
	private String type;
	
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
	
	/**
	 * 编码类型
	 */
	private String codeType ;

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	@Override
	public String toString() {
		return "AddWarehouseBO [warehouseId=" + warehouseId + ", name=" + name + ", type=" + type + ", contacts="
				+ contacts + ", tel=" + tel + ", phone=" + phone + ", remark=" + remark + ", codeType=" + codeType
				+ "]";
	}
	
}
