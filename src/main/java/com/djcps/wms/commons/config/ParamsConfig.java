package com.djcps.wms.commons.config;

/**
 * 定义在配置文件里的常量
 * @author Chengw
 */
public class ParamsConfig {

	/**
	 * 内部用户token
	 */
	public static String INNER_USER_COOKIE_NAME = "";

	/**
	 * Cookie超时
	 */
	public static int COOKIE_TIMEOUT;

	/**
	 * 内部用户登出
	 */
	public static String INNER_USER_LOGIN_URL = "";

    /**
     * 统一消息推送服务url
     */
    public static String MESSAGE_SERVER = "";
    
    /**
     * 仓库管理服务
     */
    public static String WMS_SERVER = "";
    
    /**
     * 地址服务
     */
    public static String ADDRESS_SERVER = "";

	/**
	 * 内部用户服务
	 */
	public static String INNER_USER_SERVER = "";


	/**
	 * 随机编号服务
	 */
	public static String NUMBER_SERVER="";

	/**
	 * 高德地图Web服务
	 */
	public static String MAP_SERVER = "";
	
	/**
	 * 订单服务
	 */
	public static String ORDER_SERVER = "";
	
	/**
	 * 临时天伟订单修改服务
	 */
	public static String UPDATE_ORDER_SERVER = "";
	
	/**
	 * OMS服务
	 */
	public static String ONLINE_PAPERBOARD_SERVER = "";
	
	/**
	 * ORG服务
	 */
	public static String ORG_SERVER = "";

	public static void setOnlinePaperboardServer(String onlinePaperboardServer) {
		ONLINE_PAPERBOARD_SERVER = onlinePaperboardServer;
	}

	public static void setUpdateOrderServer(String updateOrderServer) {
		UPDATE_ORDER_SERVER = updateOrderServer;
	}

	public static void setOrderServer(String orderServer) {
		ORDER_SERVER = orderServer;
	}

	public static void setMessageServer(String messageServer) {
		MESSAGE_SERVER = messageServer;
	}

	public static void setWmsServer(String wmsServer) {
		WMS_SERVER = wmsServer;
	}

	public static void setAddressServer(String addressServer) {
		ADDRESS_SERVER = addressServer;
	}

	public static void setInnerUserCookieName(String innerUserCookieName) {
		INNER_USER_COOKIE_NAME = innerUserCookieName;
	}

	public static void setCookieTimeout(int cookieTimeout) {
		COOKIE_TIMEOUT = cookieTimeout;
	}

	public static void setInnerUserLoginUrl(String innerUserLoginUrl) {
		INNER_USER_LOGIN_URL = innerUserLoginUrl;
	}

	public static void setInnerUserServer(String innerUserServer) {
		INNER_USER_SERVER = innerUserServer;
	}

	public static void setMapServer(String mapServer) {
		MAP_SERVER = mapServer;
	}

	public static void setNumberServer(String numberServer) {
		NUMBER_SERVER = numberServer;
	}
	
	public static void setOrgServer(String orgServer) {
		ORG_SERVER = orgServer;
	}
}
