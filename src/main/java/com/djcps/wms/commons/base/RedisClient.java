package com.djcps.wms.commons.base;



import java.util.List;
import java.util.Set;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;

/**
 * 
 * @ClassName: RedisClient 
 * @Description: TODO
 * @author 叶千阁
 * @date 2017年12月21日 下午3:06:58
 */
public interface RedisClient {
    /**
     * 以key-value的形式存储 
     * @param key 作为存储数据的标识
     * @param value 需要存储的String
     * @return 
     */
    String set(String key, String value);

    /**
     * 得到指定key对应的value
     * @param key 要取得value的Key
     * @return 
     */
    String get(String key);

    /**
     * 删除指定Key对应的value
     * @param key 需要删除value的key
     * @return 
     */
    Long del(String key);

    /**
     * 通过指定key和field存储value值 （map存储）
	 * key相当于map的名字，
     * @param key 数据存储的标识key
     * @param field 数据存储的标识field
     * @param value 需要存储的值
     * @return 
     */
    Long hset(String key, String field, String value);

    /**
     * 通过指定key和field取得对应的值·（map取值）
     * @param key 要取得值的key
     * @param field 要取得值的field
     * @return 
     */
    String hget(String key, String field);

    /**
	 * 通过指定key和fields删除对应的（map删除）
	 * fields是个可变字符数组
     * @param key 需要删除token的Key
     * @param fields 需要删除token的fields
     * @return 
     */
    Long hdel(String key, String... fields);

    /**
     * 当指定key对应的token不存在时以key-value形式存储一个value（常用分布式锁）
     * @param key 
     * @param value
     * @return 
     */
    Long setnx(String key, String value);

    /**
     * 当指定key和field对应的token不存在时存储一个value（常用分布式锁）
     * @param key
     * @param field
     * @param value
     * @return 
     */
    Long hsetnx(String key, String field, String value);

    /**
     * 设置key对应的过期时间
     * @param key 
     * @param seconds 时间
     * @return 
     */
    Long expire(String key, int seconds);

    /**
     * 对指定的key对应的token值加1
     * @param key
     * @return 返回设置后的值，如果key对应的token值不为integer报错
     */
    Long incr(String key);

    /**
     * 递减1并返回递减后的结果
     * @param key
     * @return
     */
    Long decr(String key);

    /**
     * 判断指定key是否存在
     * @param key
     * @return 存在返回true，不存在返回false
     */
    Boolean exists(String key);

    /**
     * 删除键所指定的过期时间
     * @param key 
     * @return 
     */
    Long persist(String key);

    /**
     * 查询指定key对应的有效时间
     * @param key
     * @return 
     */
    Long ttl(String key);

    /**
     * 在队列中从左插入（list）
     * @param key
     * @param strings
     * @return
     */
    Long lpush(String key, String... strings);

    /**
     * list中从右取出一个key
     * @param key
     * @return 
     */
    String rpop(String key);

    /**
     * set里面添加方法
     * @param key
     * @param strings
     * @return
     */
    Long sadd(String key, String... strings);

    /**
     * 返回set里面的所有对象
     * @param key
     * @return 
     */
    Set<String> smembers(String key);
    
    /**
     * 地理位置添加
     * @param key 
     * @param longitude 经度
     * @param latitude 纬度
     * @param member 名称
     * @return
     */
    Long geoadd(final String key, final double longitude, final double latitude, final String member);
    
    /**
     * 获取地址
     * @param key
     * @param members
     * @return
     */
    List<GeoCoordinate> geopos(final String key, final String... members) ;
    /**
     * 查询两个给定位置之间的距离
     * @param key
     * @param member1
     * @param member2
     * @return
     */
    Double geodist(final String key, final String member1, final String member2);
    /**
     * 查询范围内的点
     * @param key
     * @param member
     * @param radius
     * @param unit
     * @return
     */
    List<GeoRadiusResponse> georadiusByMember(final String key, final String member,
            final double radius);
    /**
     * 获取list所有内容
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<String> lgetall(final String key);
}
