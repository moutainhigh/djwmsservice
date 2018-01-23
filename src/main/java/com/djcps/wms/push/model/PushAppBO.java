package com.djcps.wms.push.model;

import java.io.Serializable;

/**
 * 消息推送 设备类
 * 接口对应全部小写
 * @author Chengw
 * @since 2018/1/23 16:10.
 */
public class PushAppBO implements Serializable{

    /**
     * 用户id
     */
    private String userid;

    /**
     * 设备别名
     */
    private String devicetoken;

    /**
     * 操作系统
     */
    private String fOS;

    /**
     * APP系统
     */
    private String appsystem;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }

    public String getfOS() {
        return fOS;
    }

    public void setfOS(String fOS) {
        this.fOS = fOS;
    }

    public String getAppsystem() {
        return appsystem;
    }

    public void setAppsystem(String appsystem) {
        this.appsystem = appsystem;
    }

    @Override
    public String toString() {
        return "PushAppBO{" +
                "userid='" + userid + '\'' +
                ", devicetoken='" + devicetoken + '\'' +
                ", fOS='" + fOS + '\'' +
                ", appsystem='" + appsystem + '\'' +
                '}';
    }
}
