package com.djcps.wms.warehouse.model.warehouse;


import java.io.Serializable;


/**
 * 该类为仓库实体类
 * 
 * @author wyb
 * @version 1.0
 * @since 2017/11/23
 */
public class WarehousePO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3978290040306890683L;
    /**
     * 仓库唯一标识
     */
    private String id;
    /**
     * 仓库编号
     */
    private String warehouseId;
    /**
     * 仓库名称
     */
    private String name;
    /**
     * 仓库类型
     */
    private String type;
    /**
     * 仓库状态
     */
    private Integer effect;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 固定电话
     */
    private String tel;
    /**
     * 手机
     */
    private String phone;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除状态
     */
    private Integer delete;

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

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Integer getEffect() {
        return effect;
    }

    public void setEffect(Integer effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "WarehousePO [id=" + id + ", warehouseId=" + warehouseId + ", name=" + name + ", type=" + type
                + ", effect=" + effect + ", contacts=" + contacts + ", tel=" + tel + ", phone=" + phone + ", remark="
                + remark + ", delete=" + delete + "]";
    }

    
}
