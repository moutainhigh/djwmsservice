package com.djcps.wms.loadingtask.model.result;
import java.util.Date;

/**
 * 装车任务信息实体类
 * @author:wyb
 * @date:2018/3/22
 **/
public class LoadingTaskPO{

    /**
     * 运单号
     */
    private String wayBillId;

    /**
     * 装车台id
     */
    private String loadingtTableId;

    /**
     * 追加平方数
     */
    private Integer addSquare;

    /**
     * 申请时间
     */
    private Date applicationTime;

    /**
     * 处理状态(0待处理,1已通过,2已驳回)
     */
    private Integer disposeStatus;

    /**
     * 申请人id
     */
    private String proposerId;

    /**
     * 申请人
     */
    private String proposer;

    /**
     * 处理人id
     */
    private String handlerId;

    /**
     * 处理人
     */
    private String handler;

    private Date createTime;

    private Date updatetime;

    /**
     *合作方id
     */
    private String partnerId;

    /**
     * 合作方名称
     */
    private String partnerName;

    /**
     * 合作方所在区域
     */
    private Integer partnerArea;

    public String getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(String wayBillId) {
        this.wayBillId = wayBillId;
    }

    public String getLoadingtTableId() {
        return loadingtTableId;
    }

    public void setLoadingtTableId(String loadingtTableId) {
        this.loadingtTableId = loadingtTableId;
    }

    public Integer getAddSquare() {
        return addSquare;
    }

    public void setAddSquare(Integer addSquare) {
        this.addSquare = addSquare;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public Integer getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(Integer disposeStatus) {
        this.disposeStatus = disposeStatus;
    }

    public String getProposerId() {
        return proposerId;
    }

    public void setProposerId(String proposerId) {
        this.proposerId = proposerId;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Integer getPartnerArea() {
        return partnerArea;
    }

    public void setPartnerArea(Integer partnerArea) {
        this.partnerArea = partnerArea;
    }

    @Override
    public String toString() {
        return "LoadingTaskPO{" +
                "wayBillId='" + wayBillId + '\'' +
                ", loadingtTableId='" + loadingtTableId + '\'' +
                ", addSquare=" + addSquare +
                ", applicationTime=" + applicationTime +
                ", disposeStatus=" + disposeStatus +
                ", proposerId='" + proposerId + '\'' +
                ", proposer='" + proposer + '\'' +
                ", handlerId='" + handlerId + '\'' +
                ", handler='" + handler + '\'' +
                ", createTime=" + createTime +
                ", updatetime=" + updatetime +
                ", partnerId='" + partnerId + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", partnerArea=" + partnerArea +
                '}';
    }
}
