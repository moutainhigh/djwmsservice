package com.djcps.wms.stock.model;


import java.io.Serializable;

/**
 * 该类为可建仓库类型枚举实体类
 * 
 * @author zdx
 * @version 1.0
 * @since 2017/12/1
 */
public class BulitTypePO implements Serializable {

    private static final long serialVersionUID = 618850335397705983L;
    /**
     * 仓库类型
     */
    private String type;
    /**
     * 仓库唯一标识
     */
    private String id;
    /**
     * 仓库名称
     */
    private String name;
    /**
     * 仓库编号
     */
    private String warehouseId;
   
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

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BulitTypePO [type=" + type + ", id=" + id + ", name=" + name + ", warehouseId=" + warehouseId + "]";
    }

}
