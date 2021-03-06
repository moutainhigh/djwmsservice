package com.djcps.wms.commons.interceptor;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.config.ParamsConfig;
import com.djcps.wms.commons.constant.RedisPrefixConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.redis.RedisClient;
import com.djcps.wms.commons.utils.CookiesUtil;
import com.djcps.wms.inneruser.model.result.UserInfoVO;
import com.djcps.wms.inneruser.service.InnerUserService;
import com.djcps.wms.permission.service.PermissionService;
import com.djcps.wms.sysurl.model.SysUrlPO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static com.djcps.wms.commons.utils.GsonUtils.gson;


/**
 * @title:wms权限控制拦截器
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月13日
 */
public class WmsInterceptor extends HandlerInterceptorAdapter{

	private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(WmsInterceptor.class);

	@Autowired
	@Qualifier("redisClientCluster")
	RedisClient redisClient;

	@Autowired
	@Qualifier("redisClientSingle")
	RedisClient userRedisClient;

	@Autowired
	private InnerUserService innerUserService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
							 Object handler) throws Exception {
		//url状态,0:失效,1:生效(默认)
		int effect = 0;
		//0:需要用户登录(默认),1:不需要登录
		int loginType = 1;
		//路径的用户类型,0:未知(默认,代表都可以访问),1:本部才可以使用的路径,2:合作方使用的路径
		int userType = 0;
		String url = request.getRequestURI();
        LOGGER.info("---访问URL:"+url+",请求方式:"+request.getMethod());
		String contextPath = request.getContextPath();
		String appLogin = request.getHeader("loginType");
		//删除下文根路径获取RequestMappingUrl
		url = url.substring(contextPath.length());
		//字符串分割去除.do
		url = url.substring(0, url.indexOf("."));
		String json = redisClient.get(RedisPrefixConstant.REDIS_SYSTEM_URL_PREFIX+url);
		SysUrlPO sysUrl = gson.fromJson(json,SysUrlPO.class);
		//取不到url
		if(sysUrl==null){
			responseMsg(SysMsgEnum.SYSURL_NULL, response,appLogin);
			return false;
		}
		//url失效
		if(sysUrl.getEffect() == effect){
			responseMsg(SysMsgEnum.URL_EXPIRE, response,appLogin);
			return false;
		}
		//不需要登录
		if(sysUrl.getLoginType() == loginType){
			return true;
		}

		//获取token认证
		String token = CookiesUtil.getCookieByName(request, "token");
		// 用户token 是否过期 或者不存在
		if (ObjectUtils.isEmpty(token)) {
			responseMsg(SysMsgEnum.NOT_LOGIN, response,appLogin);
			return false;
		}
		UserInfoVO userInfo = innerUserService.getInnerUserInfoFromRedis(token);
		if(userInfo!=null){
			//token是否过期,未过期重新设置过期时间
			if(userRedisClient.ttl(token)>=0){
				userRedisClient.expire(RedisPrefixConstant.DJCPS_DJAUTH_TOKEN + token, ParamsConfig.COOKIE_TIMEOUT);
			}
			//设置cook时间
			CookiesUtil.setCookie(response, ParamsConfig.INNER_USER_COOKIE_NAME, token, ParamsConfig.COOKIE_TIMEOUT);
			//给合作方属性赋值
			PartnerInfoBO partner = new PartnerInfoBO();
			partner.setOperator(userInfo.getUname());
			partner.setOperatorId(userInfo.getId());
			partner.setPartnerId(userInfo.getUcompany());
			partner.setPartnerName(userInfo.getOname());
			partner.setPartnerArea(userInfo.getOcode());
			//将组织好的合作方信息存到request中方便后面请求的使用
			request.setAttribute("partnerInfo", partner);
		}else{
			//用户不存在也代表未登入
			responseMsg(SysMsgEnum.NOT_LOGIN, response,appLogin);
			return false;
		}

		return true;
	}

	/**
	 * @title:输出错误信息
	 * @description:
	 * @param msg
	 * @param response
	 * @author:zdx
	 * @date:2017年11月13日
	 */
	private void responseMsg(SysMsgEnum msg, HttpServletResponse response,String appLogin) {
        LOGGER.info(msg.getMsg());
		PrintWriter printWriter = null;
		if (!response.isCommitted()) {
			Map<String, Object> result;
			if(!StringUtils.isEmpty(appLogin)){
				result= (MsgTemplate.failureMsg(msg));
			}else {
				result= (MsgTemplate.failureMsg(msg,ParamsConfig.INNER_USER_LOGIN_URL));
			}

			ObjectMapper mapper = new ObjectMapper();
			try {
				String json = mapper.writeValueAsString(result);
				if (!response.isCommitted()){
					response.setContentType("application/json; charset=utf-8");
					response.setCharacterEncoding("utf-8");
					printWriter = response.getWriter();
					printWriter.print(json);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(printWriter!=null){
					printWriter.close();
				}
			}
		}
	}
}
