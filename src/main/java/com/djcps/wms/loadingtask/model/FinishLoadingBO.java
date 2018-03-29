package com.djcps.wms.loadingtask.model;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.commons.base.BaseBO;

/**
 * 完成装车参数
 * 
 * @author WYB
 * @since 2018/3/21
 */
public class FinishLoadingBO extends BaseAddBO {

    /**
     * 
     */
    private static final long serialVersionUID = 2698964389972601167L;
    /**
     * 运单号
     */
    private String wayBillId;
    /**
     * 运单状态 1待提货,5部分提货,10提货完成,15部分装车,20装车完成
     */
    private Integer status;

    public String getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(String wayBillId) {
        this.wayBillId = wayBillId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FinishLoadingBO [wayBillId=" + wayBillId + ", status=" + status + "]";
    }

}
