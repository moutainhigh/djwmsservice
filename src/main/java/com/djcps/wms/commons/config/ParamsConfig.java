package com.djcps.wms.commons.config;

/**
 * 定义在配置文件里的常量
 * @author Chengw
 */
public class ParamsConfig {


    /**
     * 统一消息推送服务url
     */
    public static String MESSAGE_SERVER = "";
    
    /**
     * 仓库管理服务
     */
    public static String WMS_SERVER = "";

	public static String getMESSAGE_SERVER() {
		return MESSAGE_SERVER;
	}

	public static void setMESSAGE_SERVER(String mESSAGE_SERVER) {
		MESSAGE_SERVER = mESSAGE_SERVER;
	}

	public static String getWMS_SERVER() {
		return WMS_SERVER;
	}

	public static void setWMS_SERVER(String wMS_SERVER) {
		WMS_SERVER = wMS_SERVER;
	}
    
}
