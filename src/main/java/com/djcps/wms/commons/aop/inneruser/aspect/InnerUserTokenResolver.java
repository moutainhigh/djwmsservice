package com.djcps.wms.commons.aop.inneruser.aspect;

import com.djcps.wms.commons.aop.inneruser.annotation.InnerUserToken;
import com.djcps.wms.commons.config.ParamsConfig;
import org.springframework.core.MethodParameter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Chengw
 * @since 2017/12/5 14:02.
 */
public class InnerUserTokenResolver implements HandlerMethodArgumentResolver{


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //返回设置注解
        return methodParameter.hasParameterAnnotation(InnerUserToken.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        if(methodParameter.getParameterType().equals(String.class)){
            Cookie[] cookies = nativeWebRequest.getNativeRequest(HttpServletRequest.class).getCookies();
            if(!ObjectUtils.isEmpty(cookies)){
                for (Cookie cookie : cookies){
                    if(ParamsConfig.INNER_USER_COOKIE_NAME.equals(cookie.getName())){
                        return cookie.getValue();
                    }
                }
            }
        }
        return null;
    }
}
