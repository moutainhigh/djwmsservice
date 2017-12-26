package com.djcps.wms.commons.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.djcps.wms.commons.base.RedisClient;
import com.djcps.wms.commons.config.ParamsConfig;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.constant.RedisPrefixContant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.CookiesUtil;
import com.djcps.wms.inneruser.model.result.UserInfoVo;
import com.djcps.wms.inneruser.service.InnerUserService;
import com.djcps.wms.sysurl.model.SysUrlPO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


/**
 * @title:wms权限控制拦截器
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月13日
 */
public class WmsInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(WmsInterceptor.class);
	
	@Autowired
	@Qualifier("redisClientCluster")
	RedisClient redisClient;
	
	@Autowired
    private InnerUserService innerUserService;
	
	private Gson gson = new Gson();
	
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
		System.err.println("---访问URL:"+url+",请求方式:"+request.getMethod());
		String contextPath = request.getContextPath();
		//删除下文根路径获取RequestMappingUrl
		url = url.substring(contextPath.length());
		//字符串分割去除.do
		url = url.substring(0, url.indexOf("."));
		String json = redisClient.get(RedisPrefixContant.REDIS_SYSTEM_URL_PREFIX+url);
		SysUrlPO sysUrl = gson.fromJson(json,SysUrlPO.class);
		//取不到url
		if(sysUrl==null){
			responseMsg(SysMsgEnum.SYSURL_NULL, response);
			return false;
		}
		//url失效
		if(sysUrl.getEffect() == effect){
			responseMsg(SysMsgEnum.URL_EXPIRE, response);
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
			responseMsg(SysMsgEnum.NOT_LOGIN, response);
			return false;
		}
		UserInfoVo userInfo = innerUserService.getInnerUserInfoFromRedis(token);
		if(userInfo!=null){
			//toke是否过期,过期重新设置过期时间
			if(redisClient.ttl(token)>=0){
				redisClient.expire(token, AppConstant.TOKEN_EFFECTIVE_TIME);
			}
			//设置cook时间
			CookiesUtil.setCookie(response,"token",token, AppConstant.TOKEN_EFFECTIVE_TIME);
			//给合作方属性赋值
			PartnerInfoBO partner = new PartnerInfoBO();
			partner.setOperator(userInfo.getUname());
			partner.setOperatorId(userInfo.getUids());
			partner.setPartnerId(userInfo.getUcompany());
			partner.setPartnerName(userInfo.getOname());
			partner.setPartnerArea(userInfo.getOcode());
			//将组织好的合作方信息存到request中方便后面请求的使用
			request.setAttribute("partnerInfo", partner);
		}else{
			//用户不存在也代表未登入
			responseMsg(SysMsgEnum.NOT_LOGIN, response);
			return false;
		}
		
//		//web端和app端请求用户控制
//		String version = request.getParameter("version");
//		if (version == null){
//			responseMsg(InterceptorEnums.PARAMS_ERROR,response);
//			return false;
//		}
			
//		if ("web".equals(version)) {
//			String vtoken = request.getParameter("vtoken");
//			if (vtoken == null)
//				return this.poClient(response, "107");
//			if (!vtoken.equals(MD5Util.getMD5String((token + "oa")))) {
//				return this.poClient(response, "109");
//			}
//		}
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
	private void responseMsg(SysMsgEnum msg, HttpServletResponse response) {
        logger.info(msg.getMsg());
        PrintWriter printWriter = null;
        if (!response.isCommitted()) {
            Map<String, Object> result = (MsgTemplate.failureMsg(msg,ParamsConfig.INNER_USER_LOGIN_URL));
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
