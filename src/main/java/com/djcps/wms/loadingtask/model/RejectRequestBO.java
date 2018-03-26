package com.djcps.wms.loadingtask.model;

import javax.validation.constraints.NotNull;

import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.push.model.PushMsgBO;

/**
 * 驳回申请
 * 
 * @author wyb
 * @since 2018/3/21
 */
public class RejectRequestBO extends BaseBO {
    /**
     * 
     */
    private static final long serialVersionUID = 5492028894181177434L;
    /**
     * 合作方号
     */
    private String partnerId;
    /**
     * 运单编号
     */
    private String wayBillId;
    /**
     * 处理人编号
     */
    private String handlerId;
    /**
     * 处理人
     */
    private String handler;
    /**
     * 处理状态
     */
    private Integer disposeStatus;
    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 操作人名称
     */
    private String operator;
    /**
     * 用户id列表
     */
    @NotNull
    private String userid;

    /**
     * 消息内容
     */
    @NotNull
    private String msg;
    /**
     * 业务id
     */
    @NotNull
    private String mid;
    /**
     * 消息类型
     */
    private String type;
    /**
     * 通知标题
     */
    private String title;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(String wayBillId) {
        this.wayBillId = wayBillId;
    }

    public Integer getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(Integer disposeStatus) {
        this.disposeStatus = disposeStatus;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    @Override
    public String toString() {
        return "RejectRequestBO [partnerId=" + partnerId + ", wayBillId=" + wayBillId + ", handlerId=" + handlerId
                + ", handler=" + handler + ", disposeStatus=" + disposeStatus + ", operatorId=" + operatorId
                + ", operator=" + operator + ", userid=" + userid + ", msg=" + msg + ", mid=" + mid + ", type=" + type
                + ", title=" + title + "]";
    }

}
