package com.djcps.wms.commons.base;

import java.io.Serializable;

/**
 * 该类为 分页 参数类
 * @author chengw
 * @version 1.0
 * @since 2017/6/25
 */
public class BaseListBO extends BaseBO implements Serializable{

    private static final long serialVersionUID = 1;
    
    private Integer pageSize;
    
    private Integer pageNo;

	public Integer getPageNum(){
        return getPageNo() * getPageSize();
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "BaseListBO{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                '}';
    }
}
