package com.djcps.wms.role.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.httpclient.HttpResult;

import com.djcps.wms.inneruser.model.userparam.GetRoleTypePO;
import com.djcps.wms.inneruser.model.userparam.RoleTypeBO;
import com.djcps.wms.role.model.DeleteBO;
import com.djcps.wms.role.model.RoleListBO;
import com.djcps.wms.role.model.SaveRoleBO;
import com.djcps.wms.role.model.UpdateRoleInfoBO;
import com.djcps.wms.role.model.request.GetUserStatusPO;
import com.djcps.wms.role.model.request.RoleInfoResultVO;
import com.djcps.wms.role.model.request.WmsRoleInfoPO;
import com.djcps.wms.role.request.RoleHttpRequest;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import rpc.plugin.http.HTTPResponse;

import java.util.ArrayList;
import java.util.List;

import static com.djcps.wms.commons.utils.HttpResultUtils.*;

/**
 * @title:调用WMS_http服务
 * @description:
 * @author:wyb
 * @company:djwms
 * @create:2018/4/12
 **/
@Component
public class RoleHttpServer {

    private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(RoleHttpServer.class);

    private Gson gson = new Gson();

    @Autowired
    private RoleHttpRequest roleHttpRequest;

    /**
     * 获取角色列表
     *
     * @param roleListBO
     * @return
     * @author wyb
     * @create 2018/4/12
     **/
    public RoleInfoResultVO pageRole(RoleListBO roleListBO) {
        String json = gson.toJson(roleListBO);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        HTTPResponse http = roleHttpRequest.list(rb);
        RoleInfoResultVO roleInfoResultVO = null;
        HttpResult result = null;
        //校验请求是否成功
        if (http.isSuccessful()) {
            result = gson.fromJson(http.getBodyString(), HttpResult.class);
            String data = gson.toJson(result.getData());
            roleInfoResultVO = gson.fromJson(data, RoleInfoResultVO.class);
        }
        if (result == null) {
            LOGGER.error("Http请求出错,HttpResult结果为null");
        }
        return roleInfoResultVO;
    }

    /**
     * 根据角色类型编码获取角色列表
     *
     * @param
     * @return
     * @author wzy
     * @date 2018/4/16 15:54
     **/
    public List<WmsRoleInfoPO> listRole(RoleTypeBO getRoleTypeBO) {
        String json = JSONObject.toJSONString(getRoleTypeBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        HTTPResponse httpResponse = roleHttpRequest.listRole(requestBody);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                if (!ObjectUtils.isEmpty(data)) {
                    List<WmsRoleInfoPO> wmsRoleInfoPOList = JSONObject.parseArray(data,WmsRoleInfoPO.class);
                    return wmsRoleInfoPOList;
                }
            }
        }
        return null;
    }

    /**
     * 更新角色关联信息
     *
     * @param param
     * @return
     */
    public HttpResult update(UpdateRoleInfoBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse http = roleHttpRequest.update(requestBody);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 删除角色关联信息
     *
     * @param param
     * @return
     */
    public HttpResult delete(DeleteBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse http = roleHttpRequest.delete(requestBody);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 保存角色关联信息
     *
     * @param param
     * @return
     */
    public HttpResult save(SaveRoleBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse http = roleHttpRequest.save(requestBody);
        //校验请求是否成功
        return returnResult(http);
    }

    /**
     * 根据角色类型批量获取用户状态等信息
     *
     * @param param
     * @return
     */
    public List<GetUserStatusPO> getUserStatusList(List<DeleteBO> param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse http = roleHttpRequest.getUserStatusList(requestBody);
        HttpResult result = null;
        List<GetUserStatusPO> list = null;
        //校验请求是否成功
        if (http.isSuccessful()) {
            result = gson.fromJson(http.getBodyString(), HttpResult.class);
            String data = gson.toJson(result.getData());
            list = new ArrayList<>();
            list = JSONArray.parseArray(data, GetUserStatusPO.class);
        }
        if (result == null) {
            LOGGER.error("Http请求出错,HttpResult结果为null");
        }
        return list;
    }

    /**
     * 获取角色类型信息
     *
     * @return
     */
    public HttpResult getRoleType(BaseBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse http = roleHttpRequest.roleType(requestBody);
        //校验请求是否成功
        return returnResult(http);
    }

}
