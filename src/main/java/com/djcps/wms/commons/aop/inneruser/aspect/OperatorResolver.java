package com.djcps.wms.commons.aop.inneruser.aspect;

import com.djcps.wms.commons.aop.inneruser.annotation.InnerUser;
import com.djcps.wms.commons.aop.inneruser.annotation.OperatorAnnotation;
import com.djcps.wms.commons.config.ParamsConfig;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.model.OperatorInfoBO;
import com.djcps.wms.inneruser.model.result.UserInfoVO;
import com.djcps.wms.inneruser.service.InnerUserService;
import org.apache.commons.lang3.StringUtils;
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
 * 操作人信息
 * @author Chengw
 * @create 2018/4/17 10:32.
 * @since 1.0.0
 */
public class OperatorResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private InnerUserService innerUserService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //返回设置注解
        return methodParameter.hasParameterAnnotation(OperatorAnnotation.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        if(methodParameter.getParameterType().equals(OperatorInfoBO.class)) {
            OperatorInfoBO operatorInfoBO = new OperatorInfoBO();
            HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
            Cookie[] cookies = request.getCookies();
            if(!ObjectUtils.isEmpty(cookies)){
                for (Cookie cookie : cookies){
                    if(ParamsConfig.INNER_USER_COOKIE_NAME.equals(cookie.getName())){
                        UserInfoVO userInfoVO = innerUserService.getInnerUserInfoFromRedis(cookie.getValue());
                        operatorInfoBO.setOperator(userInfoVO.getId());
                        operatorInfoBO.setIp(getRealIp(request));
                        operatorInfoBO.setBussion(AppConstant.WMS);
                        return operatorInfoBO;
                    }
                }
            }

        }
        return null;
    }

    /**
     * 获取真实ip
     * @param request
     * @return
     */
    private String getRealIp(HttpServletRequest request){
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }
}
