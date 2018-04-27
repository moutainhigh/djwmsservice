package com.djcps.wms.role.server;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSONArray;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.role.model.DeleteBO;
import com.djcps.wms.role.model.OrgRoleInfoBO;
import com.djcps.wms.role.model.UpdateRoleInfoBO;
import com.djcps.wms.role.model.request.OrgRoleListPO;
import com.djcps.wms.role.model.request.OrgSavePO;
import com.djcps.wms.role.request.OrgRoleHttpRequest;
import com.google.gson.Gson;

import rpc.plugin.http.HTTPResponse;

/**
 * @title:调用ORG_http服务
 * @description:
 * @author:wyb
 * @company:djwms
 * @create:2018/4/12
 **/
@Component
public class OrgRoleHttpServer {
    private static DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(OrgRoleHttpServer.class);

    private Gson gson = new Gson();
    @Autowired
    private OrgRoleHttpRequest orgRoleHttpRequest;
    /**
     * 获取角色列表
     * @author  wyb
     * @param roleListBO
     * @return 
     * @create  2018/4/12
     **/
    public List<OrgRoleListPO> getRoleFromId(OrgRoleInfoBO orgRoleInfoBO){
        String json = gson.toJson(orgRoleInfoBO);
        Map<String,Object> map=gson.fromJson(json,Map.class);
        HTTPResponse http =orgRoleHttpRequest.getRolePerInfo(map);
      //校验请求是否成功
        HttpResult result = null;
        List<OrgRoleListPO> list= null;
        if(http.isSuccessful()){
            result = gson.fromJson(http.getBodyString(), HttpResult.class);
            String data = gson.toJson(result.getData());
            list = new ArrayList<>();
            list  = JSONArray.parseArray(data, OrgRoleListPO.class);
        }
        if(ObjectUtils.isEmpty(list)){
            System.err.println("Http请求出错,HttpResult结果为null");
            LOGGER.error("Http请求出错,HttpResult结果为null");
        }
        return list;
    }
    /**
     * 更新角色信息
     * @param param
     * @return
     */
    public HttpResult updatePostRoleManage(OrgRoleInfoBO param){
        String json = gson.toJson(param);
        Map<String,Object> map=gson.fromJson(json,Map.class);
        HTTPResponse http =orgRoleHttpRequest.updatePostRoleManage(map);
      //校验请求是否成功
        return returnResult(http);
    }
    /**
     * 新增角色信息
     * @param param
     * @return
     */
    public OrgSavePO addPostRoleManage(OrgRoleInfoBO param){
        String json = gson.toJson(param);
        Map<String,Object> map=gson.fromJson(json,Map.class);
        HTTPResponse httpResponse =orgRoleHttpRequest.addPostRoleManage(map);
        HttpResult httpResult = returnResult(httpResponse);
        OrgSavePO result = null;
        if(httpResponse.isSuccessful()){
            String http = gson.toJson(httpResult.getData());
            result = gson.fromJson(http, OrgSavePO.class);
        }
        if(result == null){
            LOGGER.error("Http请求出错,HttpResult结果为null");
        }
        return result;
    }
    /**
     * 删除角色信息
     * @param param
     * @return
     */
    public HttpResult delRoleManage(OrgRoleInfoBO param){
        String json = gson.toJson(param);
        Map<String,Object> map=gson.fromJson(json,Map.class);
        HTTPResponse http =orgRoleHttpRequest.delRoleManage(map);
      //校验请求是否成功
        return returnResult(http);
    }
    /**
     * 公共返回
     *
     * @param httpResponse
     * @return
     */
    private HttpResult returnResult(HTTPResponse httpResponse) {
        if (httpResponse.isSuccessful()) {
            try {
                String body = httpResponse.getBodyString();
                if (StringUtils.isNotBlank(body)) {
                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    return baseResult;
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }
}
