package com.djcps.wms.commons.aop.inneruser.aspect;

import com.djcps.wms.commons.aop.inneruser.annotation.InnerUser;
import com.djcps.wms.commons.config.ParamsConfig;
import com.djcps.wms.inneruser.model.result.UserInfoVo;
import com.djcps.wms.inneruser.service.InnerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *  内部用户信息
 *  @author Chengw
 *  @since 2017/12/5 13:19.
 */
public class InnerUserResolver implements HandlerMethodArgumentResolver{

    @Autowired
    private InnerUserService innerUserService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //返回设置注解
        return methodParameter.hasParameterAnnotation(InnerUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        if(methodParameter.getParameterType().equals(UserInfoVo.class)) {
            Cookie[] cookies = nativeWebRequest.getNativeRequest(HttpServletRequest.class).getCookies();
            if(!ObjectUtils.isEmpty(cookies)){
                for (Cookie cookie : cookies){
                    if(ParamsConfig.INNER_USER_COOKIE_NAME.equals(cookie.getName())){
                        return innerUserService.getInnerUserInfoFromRedis(cookie.getValue());
                    }
                }
            }
        }
        return null;
    }
}
