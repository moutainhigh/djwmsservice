package com.djcps.wms.commons.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;

/**
 * @author
 */
public interface RedisClient {

    /**
     * set
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);

    /**
     * get
     * @param key
     * @return
     */
    String get(String key);

    /**
     * del
     * @param key
     * @return
     */
    Long del(String key);

    /**
     * hset
     * @param key
     * @param field
     * @param value
     * @return
     */
    Long hset(String key, String field, String value);

    /**
     * hget
     * @param key
     * @param field
     * @return
     */
    String hget(String key, String field);

    /**
     * hdel
     * @param key
     * @param fields
     * @return
     */
    Long hdel(String key, String... fields);

    /**
     * setnx
     * @param key
     * @param value
     * @return
     */
    Long setnx(String key, String value);

    /**
     * hsetnx
     * @param key
     * @param field
     * @param value
     * @return
     */
    Long hsetnx(String key, String field, String value);

    /**
     * expire
     * @param key
     * @param seconds
     * @return
     */
    Long expire(String key, int seconds);

    /**
     * incr
     * @param key
     * @return
     */
    Long incr(String key);

    /**
     * decr
     * @param key
     * @return
     */
    Long decr(String key);

    /**
     * exists
     * @param key
     * @return
     */
    Boolean exists(String key);

    /**
     * persist
     * @param key
     * @return
     */
    Long persist(String key);

    /**
     * ttl
     * @param key
     * @return
     */
    Long ttl(String key);

    /**
     * lpush
     * @param key
     * @param strings
     * @return
     */
    Long lpush(String key, String... strings);

    /**
     * rpop
     * @param key
     * @return
     */
    String rpop(String key);

    /**
     * sadd
     * @param key
     * @param strings
     * @return
     */
    Long sadd(String key, String... strings);

    /**
     * smembers
     * @param key
     * @return
     */
    Set<String> smembers(String key);

    /**
     * lrange
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<String> lrange(String key, long start, long end);

    /**
     * hgetAll
     * @param key
     * @return
     */
    Map<String, String> hgetAll(String key);

    /**
     * geoadd
     * @param key
     * @param longitude
     * @param latitude
     * @param member
     * @return
     */
    Long geoadd(String key, double longitude, double latitude, String member);

    /**
     * geopos
     * @param key
     * @param members
     * @return
     */
    List<GeoCoordinate> geopos(String key, String... members);

    /**
     * geodist
     * @param key
     * @param member1
     * @param member2
     * @return
     */
    Double geodist(String key, String member1, String member2);

    /**
     * georadiusByMember
     * @param key
     * @param member
     * @param radius
     * @param unit
     * @return
     */
    List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit);
}
