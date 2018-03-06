package com.djcps.wms.commons.utils;

import com.djcps.wms.commons.redis.RedisClient;

/**
 * redis工具类
 * @company:djwms
 * @author:zdx
 * @date:2018年3月5日
 */
public class RedisUtil {
	
	/**
	 * redis锁
	 * @param redisClient
	 * @param key
	 * @param value
	 * @param expireTime
	 * @return
	 */
	public static Boolean setnx(RedisClient redisClient,String key,String value,int expireTime){
		Long setnx = redisClient.setnx(key, value);
		if(setnx>0){
			if(expireTime!=0){
				redisClient.expire(key,expireTime);
				return true;
			}
		}
		return false;
	}
}
