package com.djcps.wms.permission.redis;

import com.alibaba.fastjson.JSONObject;
import com.djcps.wms.commons.constant.RedisPrefixConstant;
import com.djcps.wms.commons.redis.RedisClientCluster;
import com.djcps.wms.permission.constants.PermissionConstants;
import com.djcps.wms.permission.model.vo.ChangeWmsPerVO;
import com.djcps.wms.permission.model.vo.UserPermissionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Chengw
 * @create 2018/4/23 19:16.
 * @since 1.0.0
 */
@Repository
public class PermissionRedisDao {

    @Autowired
    private RedisClientCluster redisClientCluster;

    /**
     * 获取
     * @param userId
     * @return
     */
    public List<UserPermissionVO> getPermission(String userId){
        String data = redisClientCluster.get(RedisPrefixConstant.PERMISSION_REDIS_CACHE + userId);
        if(StringUtils.isNotBlank(data)){
            return JSONObject.parseArray(data,UserPermissionVO.class);
        }
        return null;
    }

    /**
     * 设置用户权限缓存
     * @param list
     * @param userId
     * @return
     */
    public Boolean setPermission( String userId,List<UserPermissionVO> list){
        long result = redisClientCluster.setnx(RedisPrefixConstant.PERMISSION_REDIS_CACHE + userId,JSONObject.toJSONString(list));
        if(result > 0 ){
            redisClientCluster.expire(RedisPrefixConstant.PERMISSION_REDIS_CACHE + userId,PermissionConstants.PERMISSION_REDIS_CACHE_TIME);
            return true;
        }
        return false;
    }

    /**
     * 删除用户权限数据项
     * @param userId
     * @return
     */
    public Boolean delPermission(String userId){
        long result = redisClientCluster.del(RedisPrefixConstant.PERMISSION_REDIS_CACHE + userId);
        if(result > 0){
            return true;
        }
        return false;
    }

    /**
     * 获取 基础权限数据项
     * @param
     * @return
     */
    public List<ChangeWmsPerVO> getBasicPermission(){
        String data = redisClientCluster.get(RedisPrefixConstant.PERMISSION_REDIS_BASIC_CACHE );
        if(StringUtils.isNotBlank(data)){
            return JSONObject.parseArray(data,ChangeWmsPerVO.class);
        }
        return null;
    }

    /**
     * 设置基本权限权限缓存
     * @param list
     * @return
     */
    public Boolean setBasicPermission(List<ChangeWmsPerVO> list){
        long result = redisClientCluster.setnx(RedisPrefixConstant.PERMISSION_REDIS_BASIC_CACHE,JSONObject.toJSONString(list));
        if(result > 0 ){
            redisClientCluster.expire(RedisPrefixConstant.PERMISSION_REDIS_BASIC_CACHE,PermissionConstants.PERMISSION_REDIS_CACHE_TIME);
            return true;
        }
        return false;
    }
}
