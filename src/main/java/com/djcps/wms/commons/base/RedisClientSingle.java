package com.djcps.wms.commons.base;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * 
 * @ClassName: RedisSingle 
 * @Description: TODO
 * @author 叶千阁
 * @date 2017年12月21日 下午3:23:12
 */
public class RedisClientSingle implements RedisClient {
    
    private JedisPool jedisPool;
    

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hset(key, field, value);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hget(key, field);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hdel(String key, String... fields) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hdel(key, fields);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long setnx(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setnx(key, value);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hsetnx(key, field, value);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.expire(key, seconds);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long decr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(key);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long persist(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.persist(key);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.ttl(key);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long lpush(String key, String... strings) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lpush(key, strings);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public String rpop(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.rpop(key);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long sadd(String key, String... strings) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sadd(key, strings);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> smembers(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.smembers(key);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long geoadd(String key, double longitude, double latitude, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geoadd(key, longitude, latitude, member);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<GeoCoordinate> geopos(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geopos(key, members);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public Double geodist(String key, String member1, String member2) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geodist(key, member1, member2);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.georadiusByMember(key, member, radius, GeoUnit.KM);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> lgetall(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lrange(key, 0, -1);
        } finally{
            if (jedis!=null) {
                jedis.close();
            }
        }
    }

}
