package com.djcps.wms.commons.constant;

/**
 * redis前缀常量(所有的redis前缀放这里面)
 * @company:djwms
 * @author:zdx
 * @date:2017年12月14日
 */
public class RedisPrefixConstant {
	
	/**
	 * SystemUrl的前缀
	 */
	public static final String REDIS_SYSTEM_URL_PREFIX = "djwms:sysUrl:permission:";
	
	/**
	 * 配货和配货管理的前缀
	 */
	public static final String REDIS_ALLOCATION_ORDER_PREFIX = "djwms:allocation:order:";
	
	/**
	 * 用户权限模块的前缀
	 */
	public static final String PERMISSION_TREE_REDIS_CACHE = "djwms:cache:tree:user:permission:";

	/**
	 * 用户权限模块的前缀
	 */
	public static final String PERMISSION_LIST_REDIS_CACHE = "djwms:cache:list:user:permission:";

	/**
	 * 用户权限模块的前缀
	 */
	public static final String PERMISSION_REDIS_BASIC_CACHE = "djwms:cache:basic:permission:";

		/**
	 * 用户登陆状态token前缀
	 */
	public static final String DJCPS_DJAUTH_TOKEN = "djcps:djauth:token:";
}
