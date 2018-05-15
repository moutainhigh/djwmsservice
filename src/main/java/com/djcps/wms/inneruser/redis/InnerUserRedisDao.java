package com.djcps.wms.inneruser.redis;

import com.djcps.wms.commons.constant.RedisPrefixConstant;
import com.djcps.wms.commons.redis.RedisClient;
import com.djcps.wms.inneruser.model.result.UserInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * 内部用户 redis操作 用户单独redis 连接池
 * 
 * @author Chengw
 * @since 2017/12/5 08:36.
 */
@Repository
public class InnerUserRedisDao {
	
	@Resource
	@Qualifier("redisClientSingle")
	private RedisClient redisClient;

	/**
	 * 获取用户
	 * 
	 * @param token String
	 * @return UserInfoVO
	 */
	public UserInfoVO getInnerUserInfo(String token) {
		if (StringUtils.isNotBlank(token)) {
			String userId = redisClient.get(RedisPrefixConstant.DJCPS_DJAUTH_TOKEN + token);
			if (ObjectUtils.isEmpty(userId)) {
				return null;
			}
			String userInfoStr = redisClient.get("userInfo" + userId);
			return gson.fromJson(userInfoStr, UserInfoVO.class);
		}
		return null;
	}
}
