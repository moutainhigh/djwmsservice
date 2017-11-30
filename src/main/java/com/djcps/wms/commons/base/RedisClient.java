package com.djcps.wms.commons.base;

import org.apache.ibatis.type.Alias;

import java.util.Set;

/**
 * @author
 */
public interface RedisClient {

    /**
     * redis set方法
     * @param key
     * @param value
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    String set(String key, String value);

    /**
     * redis get方法
     * @param key
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    String get(String key);

    /**
     * redis del方法
     * @param key
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    Long del(String key);

    
    /**
     * redis hset方法
     * @param key
     * @param field
     * @param value
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    Long hset(String key, String field, String value);

    /**
     * redis hget方法
     * @param key
     * @param field
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    String hget(String key, String field);

    /**
     * redis hdel方法
     * @param key
     * @param fields
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    Long hdel(String key, String... fields);

    /**
     * redis setnx方法
     * @param key
     * @param value
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    Long setnx(String key, String value);

    /**
     * redis hsetnx方法
     * @param key
     * @param field
     * @param value
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    Long hsetnx(String key, String field, String value);

    /**
     * redis expire方法
     * @param key
     * @param seconds
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    Long expire(String key, int seconds);

    /**
     * redis incr方法
     * @param key
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    Long incr(String key);

    /**
     * redis decr方法
     * @param key
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    Long decr(String key);

    /**
     * redis exists方法
     * @param key
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    Boolean exists(String key);

    /**
     * redis persist方法
     * @param key
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    Long persist(String key);

    /**
     * redis ttl方法
     * @param key
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    Long ttl(String key);

    /**
     * redis lpush方法
     * @param key
     * @param strings
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    Long lpush(String key, String... strings);

    /**
     * redis rpop方法
     * @param key
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    String rpop(String key);

    /**
     * redis sadd方法
     * @param key
     * @param strings
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    Long sadd(String key, String... strings);
    
    /**
     * redis smembers方法
     * @param key
     * @return
     * @author:zdx
     * @date:2017年11月30日
     */
    Set<String> smembers(String key);
}
