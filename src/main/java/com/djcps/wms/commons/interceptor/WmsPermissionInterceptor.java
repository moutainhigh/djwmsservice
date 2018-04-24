package com.djcps.wms.commons.interceptor;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.config.ParamsConfig;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.CookiesUtil;
import com.djcps.wms.inneruser.model.result.UserInfoVO;
import com.djcps.wms.inneruser.service.InnerUserService;
import com.djcps.wms.permission.constants.PermissionConstants;
import com.djcps.wms.permission.model.bo.UserPermissionBO;
import com.djcps.wms.permission.model.vo.UserPermissionVO;
import com.djcps.wms.permission.service.PermissionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 权限拦截器
 *
 * @author Chengw
 * @create 2018/4/23 20:38.
 * @since 1.0.0
 */
public class WmsPermissionInterceptor extends HandlerInterceptorAdapter {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(WmsPermissionInterceptor.class);

    @Autowired
    private InnerUserService innerUserService;

    @Autowired
    private PermissionService permissionService;


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
        LOGGER.info("---访问URL:" + url + ",请求方式:" + request.getMethod());
        String contextPath = request.getContextPath();
        String appLogin = request.getHeader("loginType");
        //删除下文根路径获取RequestMappingUrl
        url = url.substring(contextPath.length());
        //字符串分割去除.do
        url = url.substring(0, url.indexOf("."));

        //获取token认证
        String token = CookiesUtil.getCookieByName(request, "token");
        // 用户token 是否过期 或者不存在
        if (ObjectUtils.isEmpty(token)) {
            UserInfoVO userInfo = innerUserService.getInnerUserInfoFromRedis(token);
            if (userInfo != null) {
                Map<String, String[]> params = request.getParameterMap();
                Boolean result = isExist(userInfo.getId(), url, params);
                if (!result) {
                    responseMsg(SysMsgEnum.NOT_PERMISSION, response, appLogin);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 校验是否拥有权限
     *
     * @param userId
     * @param url
     * @param params
     * @return
     */
    private Boolean isExist(String userId, String url, Map<String, String[]> params) {
        Boolean notInterface = permissionService.notExistSystemPermission(userId, url);
        if (notInterface) {
            return true;
        }
        Boolean result = checkPermission(userId, url, params);
        return result;
    }

    /**
     * 校验是否有权限 访问
     *
     * @param userId
     * @param url
     * @return
     */
    private Boolean checkPermission(String userId, String url, Map<String, String[]> params) {
        UserPermissionBO param = new UserPermissionBO();
        param.setIp("");
        param.setpBusiness(PermissionConstants.BUSINESS_ID);
        param.setId(userId);
        param.setBusiness(AppConstant.WMS);
        param.setOperator(userId);
        List<UserPermissionVO> list = permissionService.listUserPermission(param);
        Optional optional = list.stream().filter(x -> {
            String[] str = x.getInterfaceName().split(AppConstant.W).clone();
            if (str[0].equals(url)) {
                if (str.length > 1) {
                    String[] paramStr = str[1].split("=").clone();
                    int result = Arrays.binarySearch(params.get(paramStr[0]), paramStr[1]);
                    if (result >= 0) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        }).findFirst();
        if (optional.isPresent()) {
            return true;
        }
        return false;
    }

    /**
     * @param msg
     * @param response
     * @title:输出错误信息
     * @description:
     * @author:zdx
     * @date:2017年11月13日
     */
    private void responseMsg(SysMsgEnum msg, HttpServletResponse response, String appLogin) {
        LOGGER.info(msg.getMsg());
        PrintWriter printWriter = null;
        if (!response.isCommitted()) {
            Map<String, Object> result;
            if (!StringUtils.isEmpty(appLogin)) {
                result = (MsgTemplate.failureMsg(msg));
            } else {
                result = (MsgTemplate.failureMsg(msg, ParamsConfig.INNER_USER_LOGIN_URL));
            }

            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writeValueAsString(result);
                if (!response.isCommitted()) {
                    response.setContentType("application/json; charset=utf-8");
                    response.setCharacterEncoding("utf-8");
                    printWriter = response.getWriter();
                    printWriter.print(json);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (printWriter != null) {
                    printWriter.close();
                }
            }
        }
    }
}
