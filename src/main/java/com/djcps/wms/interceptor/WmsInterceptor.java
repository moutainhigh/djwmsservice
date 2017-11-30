package com.djcps.wms.interceptor;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.djcps.wms.commons.base.RedisClient;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.CookiesUtil;
import com.djcps.wms.interceptor.enums.InterceptorEnums;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	private RedisClient jedis;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
//		String url = request.getRequestURI();
//		System.out.println("---------------------访问URL：" + url + "　请求方式：" + request.getMethod()
//		+ "---------------");
//		//用户登录地址过滤,login表示登录，common表示公共url,不需要登录即可请求
//		if (url.matches(".*login.wms") || url.matches(".*Common.wms")) {
//			return true;
//		}
//		
//		//获取token认证
//		String token = CookiesUtil.getCookieByName(request, "token");
//		
//		// 用户token 是否过期 或者不存在
//		if (token == null) {
//			responseMsg(InterceptorEnums.NOT_LOGIN,response);
//			return false;
//		}
//		
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
	private void responseMsg(InterceptorEnums msg, HttpServletResponse response) {
        logger.info(msg.getMsg());
        if (!response.isCommitted()) {
            Map<String, Object> result = (MsgTemplate.failureMsg(msg));
            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writeValueAsString(result);
                if (!response.isCommitted()){
                	response.setContentType("application/json; charset=utf-8");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().print(json);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
