package com.djcps.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis客户端-单机版
 * 
 *
 */
public class RedisClientSingle implements RedisClient {

    @Autowired
    private JedisPool jedisPool;

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.set(key, value);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.get(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.del(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.hset(key, field, value);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.hget(key, field);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hdel(String key, String... fields) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.hdel(key, fields);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long setnx(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.setnx(key, value);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.hsetnx(key, field, value);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.expire(key, seconds);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.incr(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long decr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.decr(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Boolean result = jedis.exists(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long persist(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.persist(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.ttl(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long lpush(String key, String... strings) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.lpush(key, strings);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String rpop(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.rpop(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long sadd(String key, String... strings) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.sadd(key, strings);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> smembers(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Set<String> result = jedis.smembers(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<String> result = jedis.lrange(key, start, end);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Map<String, String> result = jedis.hgetAll(key);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Long geoadd(String key, double longitude, double latitude, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long result = jedis.geoadd(key, longitude, latitude, member);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<GeoCoordinate> geopos(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<GeoCoordinate> result = jedis.geopos(key, members);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Double geodist(String key, String member1, String member2) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Double result = jedis.geodist(key, member1, member2);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<GeoRadiusResponse> result = jedis.georadiusByMember(key, member, radius, unit);
            return result;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
