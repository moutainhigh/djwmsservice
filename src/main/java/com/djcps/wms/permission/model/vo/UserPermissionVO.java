package com.djcps.wms.permission.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chengw
 * @create 2018/4/23 17:33.
 * @since 1.0.0
 */
public class UserPermissionVO  implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 父节点
     */
    private String father;

    /**
     * 标题
     */
    private String title;

    /**
     * 层级
     */
    private String layer;

    /**
     * 接口
     */
    private String interfaceName;

    /**
     * 标记
     */
    private String mark;

    /**
     * 图标
     */
    private String ico;

    /**
     * 业务
     */
    private String business;

    /**
     * 控件编号
     */
    private String htmlId;

    /**
     * 权限类型
     * 0-按钮 1-菜单 2-数据权限
     */
    private Integer type;

    /**
     * 子节点
     */
    private List<UserPermissionVO> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
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

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public List<UserPermissionVO> getChildren() {
        return children;
    }

    public void setChildren(List<UserPermissionVO> children) {
        this.children = children;
    }

    public String getHtmlId() {
        return htmlId;
    }

    public void setHtmlId(String htmlId) {
        this.htmlId = htmlId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserPermissionVO{" +
                "id='" + id + '\'' +
                ", father='" + father + '\'' +
                ", title='" + title + '\'' +
                ", layer='" + layer + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", mark='" + mark + '\'' +
                ", ico='" + ico + '\'' +
                ", business='" + business + '\'' +
                ", htmlId='" + htmlId + '\'' +
                ", type=" + type +
                ", children=" + children +
                '}';
    }
}
