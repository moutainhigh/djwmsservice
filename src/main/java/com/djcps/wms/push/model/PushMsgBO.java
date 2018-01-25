package com.djcps.wms.push.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 消息推送 消息类
 * @author Chengw
 * @since 2018/1/23 16:09.
 */
public class PushMsgBO implements Serializable{

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
     * APP系统
     */
    private String appSystem;

    /**
     * 通知栏提示文字
     */
    private String ticker;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知文字描述
     */
    private String text;

    /**
     * 自定义内容
     */
    private String custom;

    /**
     * 消息类型
     */
    private String type;

    /**
     * 自定义参数
     */
    private Object extraField;

    /**
     * 业务id
     */
    @NotNull
    private String mid;

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

    public String getAppSystem() {
        return appSystem;
    }

    public void setAppSystem(String appSystem) {
        this.appSystem = appSystem;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getExtraField() {
        return extraField;
    }

    public void setExtraField(Object extraField) {
        this.extraField = extraField;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "PushMsgBO{" +
                "userid='" + userid + '\'' +
                ", msg='" + msg + '\'' +
                ", appSystem='" + appSystem + '\'' +
                ", ticker='" + ticker + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", custom='" + custom + '\'' +
                ", type='" + type + '\'' +
                ", extraField='" + extraField + '\'' +
                ", mid='" + mid + '\'' +
                '}';
    }
}
