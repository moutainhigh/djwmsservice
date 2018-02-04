package com.djcps.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;

public interface RedisClient {

    String set(String key, String value);

    String get(String key);

    Long del(String key);

    Long hset(String key, String field, String value);

    String hget(String key, String field);

    Long hdel(String key, String... fields);

    Long setnx(String key, String value);

    Long hsetnx(String key, String field, String value);

    Long expire(String key, int seconds);

    Long incr(String key);

    Long decr(String key);

    Boolean exists(String key);

    Long persist(String key);

    Long ttl(String key);

    Long lpush(String key, String... strings);

    String rpop(String key);

    Long sadd(String key, String... strings);

    Set<String> smembers(String key);

    List<String> lrange(String key, long start, long end);

    Map<String, String> hgetAll(String key);

    Long geoadd(String key, double longitude, double latitude, String member);

    List<GeoCoordinate> geopos(String key, String... members);

    Double geodist(String key, String member1, String member2);

    List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit);
}
