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

	public static String getMessageServer() {
		return MESSAGE_SERVER;
	}

	public static void setMessageServer(String messageServer) {
		MESSAGE_SERVER = messageServer;
	}

	public static String getWmsServer() {
		return WMS_SERVER;
	}

	public static void setWmsServer(String wmsServer) {
		WMS_SERVER = wmsServer;
	}

}
