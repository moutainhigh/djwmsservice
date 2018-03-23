package com.djcps.wms.loadingtask.model;

import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.base.BaseBO;
/**
 * 装车员信息
 * @author wyb
 * @since 2018/3/21
 */
public class LoadingPersonIdBO extends BaseBO {

    /**
     * 
     */
    private static final long serialVersionUID = 47147837247657226L;
    /**
     * 装车员编号
     */
    private String id;
    /**
     * 状态(0空闲,1繁忙)
     */
    private Integer status;
    /**
     * 装车台id
     */
    private String loadingTableId;

    public Integer getStatus() {
        if(ObjectUtils.isEmpty(this.status)) {
            this.status=1;
        }
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLoadingTableId() {
        return loadingTableId;
    }

    public void setLoadingTableId(String loadingTableId) {
        this.loadingTableId = loadingTableId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoadingPersonIdBO [id=" + id + ", status=" + status + ", loadingTableId=" + loadingTableId + "]";
    }

}
