package com.djcps.wms.role.server;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory; 
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.role.model.OrgRoleInfoBO;
import com.djcps.wms.role.model.OrgUserRoleBO;
import com.djcps.wms.role.model.OrgUserRolePO;
import com.djcps.wms.role.model.OrgUserRoleVO;
import com.djcps.wms.role.model.request.OrgRoleListPO;
import com.djcps.wms.role.model.request.OrgSaveRolePO;
import com.djcps.wms.role.request.OrgRoleHttpRequest;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import rpc.plugin.http.HTTPResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.djcps.wms.commons.utils.HttpResultUtils.*;

/**
 * @title:调用ORG_http服务
 * @description:
 * @author:wyb
 * @company:djwms
 * @create:2018/4/12
 **/
@Component
public class OrgRoleHttpServer {

    private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(OrgRoleHttpServer.class);

    private Gson gson = new Gson();

    @Autowired
    private OrgRoleHttpRequest orgRoleHttpRequest;

    /**
     * 获取角色列表
     *
     * @param orgRoleInfoBO
     * @return
     * @author wyb
     * @create 2018/4/12
     **/
    public List<OrgRoleListPO> getRoleFromId(OrgRoleInfoBO orgRoleInfoBO) {
        String json = gson.toJson(orgRoleInfoBO);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse http = orgRoleHttpRequest.getRolePerInfo(map);
        //校验请求是否成功
        if (http.isSuccessful()) {
            HttpResult result = gson.fromJson(http.getBodyString(), HttpResult.class);
            String data = gson.toJson(result.getData());
            List<OrgRoleListPO> list = JSONArray.parseArray(data, OrgRoleListPO.class);
            if (ObjectUtils.isEmpty(list)) {
                LOGGER.error("Http请求出错,HttpResult结果为null");
            }
            return list;
        }
        return null;
    }

    /**
     * 更新角色信息
     *
     * @param param
     * @return
     */
    public HttpResult updatePostRoleManage(OrgRoleInfoBO param) {
        String json = gson.toJson(param);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse http = orgRoleHttpRequest.updatePostRoleManage(map);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 新增角色信息
     *
     * @param param
     * @return
     */
    public OrgSaveRolePO addPostRoleManage(OrgRoleInfoBO param) {
        String json = gson.toJson(param);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = orgRoleHttpRequest.addPostRoleManage(map);
        HttpResult httpResult = returnResult(httpResponse);
        OrgSaveRolePO result = null;
        if (httpResponse.isSuccessful()) {
            String http = gson.toJson(httpResult.getData());
            result = gson.fromJson(http, OrgSaveRolePO.class);
        }
        if (result == null) {
            LOGGER.error("Http请求出错,HttpResult结果为null");
        }
        return result;
    }

    /**
     * 删除角色信息
     *
     * @param param
     * @return
     */
    public HttpResult delRoleManage(OrgRoleInfoBO param) {
        String json = gson.toJson(param);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse http = orgRoleHttpRequest.delRoleManage(map);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 获取用户角色信息
     *
     * @param param
     * @return
     */
    public List<OrgUserRoleVO> getUserRole(OrgUserRoleBO param) {
        String json = JSONObject.toJSONString(param);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse http = orgRoleHttpRequest.getUserRole(map);
        if (http.isSuccessful()) {
            HttpResult result = returnResult(http);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                List<OrgUserRolePO> orgUserROlePOList = JSONObject.parseArray(data, OrgUserRolePO.class);
                if (!ObjectUtils.isEmpty(orgUserROlePOList)) {
                    List<OrgUserRoleVO> orgUserRoleVOList = orgUserROlePOList.stream().map(u -> new OrgUserRoleVO() {
                        {
                            setRoleId(u.getId());
                            setRoleName(u.getRname());
                        }
                    }).collect(Collectors.toList());
                    return orgUserRoleVOList;
                }

            }
        }
         return null;
    }
}
