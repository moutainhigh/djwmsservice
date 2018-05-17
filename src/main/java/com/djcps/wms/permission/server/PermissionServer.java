package com.djcps.wms.permission.server;

import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.httpclient.OtherHttpResult;
import com.djcps.wms.permission.constants.PermissionConstants;
import com.djcps.wms.permission.model.bo.*;
import com.djcps.wms.permission.model.po.GetOnePermissionPO;
import com.djcps.wms.permission.model.po.GetWmsPerPO;
import com.djcps.wms.permission.model.po.UserPermissionPO;
import com.djcps.wms.permission.model.vo.ChangeOnePerVO;
import com.djcps.wms.permission.model.vo.ChangeWmsPerVO;
import com.djcps.wms.permission.model.vo.UserPermissionVO;
import com.djcps.wms.permission.request.DjorForPermissionHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import rpc.plugin.http.HTTPResponse;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * @author zhq
 * 权限服务
 * 2018年4月12日
 */
@Component
public class PermissionServer {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(PermissionServer.class);

    @Resource
    private DjorForPermissionHttpRequest djorForPermissionHttpRequest;


    /**
     * 得到组合权限包
     *
     * @param param GetPermissionBO
     * @return OtherHttpResult
     */
    public OtherHttpResult getPermissionList(GetPermissionBO param) {
        String json = gson.toJson(param);
        LOGGER.debug("---http请求参数转化成json---: {}",json);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse http = djorForPermissionHttpRequest.getPermissionList(map);
        return verifyOtherHttpResult(http);
    }

    /**
     * 得到wms权限
     *
     * @param param WmsPermissionBO
     * @return List<ChangeWmsPerVO>
     */
    public List<ChangeWmsPerVO> getWmsPermission(WmsPermissionBO param) {
        String json = gson.toJson(param);
        LOGGER.debug("---http请求参数转化成json---:" + json);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse http = djorForPermissionHttpRequest.getWmsPermission(map);
        HttpResult result = returnHttpResult(http);
        if (result.isSuccess()) {
            String data = JSONObject.toJSONString(result.getData());
            List<GetWmsPerPO> listUser = JSONObject.parseArray(data, GetWmsPerPO.class);
            //规范返回字段
            if (!ObjectUtils.isEmpty(listUser)) {
                List<ChangeWmsPerVO> listUserChange = listUser.stream().map(x -> new ChangeWmsPerVO() {{
                    setId(x.getId());
                    setTitle(x.getPtitle());
                    setLayer(x.getPolayer());
                    setFatherId(x.getPfather());
                    setFirstId(x.getPfirst());
                    setIsParent(x.getIsParent());
                    setMark(x.getPmark());
                    setIcon(x.getIcon());
                    setInterfaceInfo(x.getPinterface());
                }}).collect(Collectors.toList());
                return getBasicRootPermission(listUserChange);
            }
        }
        return null;
    }

    /**
     * 新增权限
     *
     * @param param InsertOrUpdatePermissionBO
     * @return HttpResult
     */
    public HttpResult insertPermission(InsertOrUpdatePermissionBO param) {
        String json = gson.toJson(param);
        LOGGER.debug("---http请求参数转化成json---:" + json);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse http = djorForPermissionHttpRequest.insertPermission(map);
        return returnHttpResult(http);
    }

    /**
     * 修改权限
     *
     * @param param InsertOrUpdatePermissionBO
     * @return HttpResult
     */
    public HttpResult updatePermission(InsertOrUpdatePermissionBO param) {
        String json = gson.toJson(param);
        LOGGER.debug("---http请求参数转化成json---:" + json);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse http = djorForPermissionHttpRequest.updatePermission(map);
        return returnHttpResult(http);
    }

    /**
     * 删除权限
     *
     * @param param DeletePerParamBO
     * @return HttpResult
     */
    public HttpResult deletePermission(DeletePerParamBO param) {
        String json = gson.toJson(param);
        LOGGER.debug("---http请求参数转化成json---:" + json);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse http = djorForPermissionHttpRequest.deletePermission(map);
        return returnHttpResult(http);
    }

    /**
     * 根据权限获取关联用户
     *
     * @param param
     * @return
     */
    public HttpResult getUserByPermissionId(GetUserByPermissionIdBO param) {
        String json = gson.toJson(param);
        LOGGER.debug("---http请求参数转化成json---:" + json);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse http = djorForPermissionHttpRequest.getUserByPermissionId(map);
        return returnHttpResult(http);
    }

    /**
     * 根据组合权限id和公司id，获取获取组合权限集合
     *
     * @param param
     * @return
     */
    public List<ChangeOnePerVO> getPerChoose(GetPermissionChooseBO param) {
        String json = gson.toJson(param);
        LOGGER.debug("---http请求参数转化成json---:" + json);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse http = djorForPermissionHttpRequest.getPerChoose(map);
        HttpResult result = returnHttpResult(http);
        String data = JSONObject.toJSONString(result.getData());
        List<GetOnePermissionPO> list = JSONObject.parseArray(data, GetOnePermissionPO.class);
        // 规范返回字段
        if (!ObjectUtils.isEmpty(list)) {
            return list.stream().map(x -> new ChangeOnePerVO() {{
                setTitle(x.getPtitle());
                setDescribe(x.getPdes());
                setCompanyId(x.getPcompany());
                setUserId(x.getPuserid());
                setPerList(x.getPperlist());
                setBusiness(x.getPbussion());
                setId(x.getId());
                setIsDel(x.getIsdel());
                setDeletePerson(x.getIsdel_per());
                setCreateTime(x.getCreate_time());
                setUpdateTime(x.getUpdate_time());
            }}).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 得到wms基本权限项
     *
     * @param param BaseOrgBO
     * @return List<UserPermissionVO>
     */
    public List<UserPermissionVO> listBasicPermission(BaseOrgBO param) {
        String json = gson.toJson(param);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse http = djorForPermissionHttpRequest.getWmsPermission(map);
        HttpResult result = returnHttpResult(http);
        String data = JSONObject.toJSONString(result.getData());
        List<UserPermissionPO> userPermissionPOList = JSONObject.parseArray(data, UserPermissionPO.class);
        return toUserPermissionVO(userPermissionPOList);
    }

    /**
     * 获取用户拥有的所有权限
     *
     * @param param UserPermissionBO
     * @return List<UserPermissionVO>
     * @serialData business default: 30
     */
    public List<UserPermissionVO> getUserPermission(UserPermissionBO param) {
        param.setpBusiness(PermissionConstants.BUSINESS_ID);
        String json = gson.toJson(param);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse http = djorForPermissionHttpRequest.getUserPermission(map);
        HttpResult result = returnHttpResult(http);
        String data = JSONObject.toJSONString(result.getData());
        List<UserPermissionPO> userPermissionPOList = JSONObject.parseArray(data, UserPermissionPO.class);
        List<UserPermissionVO> userPermissionVOList = toUserPermissionVO(userPermissionPOList);
        return getUserRootPermission(userPermissionVOList);
    }

    /**
     * 用户权限root权限组合
     *
     * @param param List<UserPermissionVO>
     * @return List<UserPermissionVO>
     */
    private List<UserPermissionVO> getUserRootPermission(List<UserPermissionVO> param) {
        List<UserPermissionVO> rootPermissionList = param.stream().filter(u -> param.stream().noneMatch(
                x -> u.getFather().equals(x.getId()))).collect(Collectors.toList());
        if (!ObjectUtils.isEmpty(rootPermissionList)) {
            rootPermissionList.forEach(u -> u.setChildren(userPermissionToTree(param, u.getId())));
            return rootPermissionList;
        }
        return param;
    }

    /**
     * 获取数据权限项
     *
     * @param param UserPermissionBO
     * @return List<UserPermissionVO>
     * @author Chengw
     * @since 2018/4/23  20:47
     */
    public List<UserPermissionVO> listUserPermission(UserPermissionBO param) {
        param.setpBusiness(PermissionConstants.BUSINESS_ID);
        String json = gson.toJson(param);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse http = djorForPermissionHttpRequest.getUserPermission(map);
        HttpResult result = returnHttpResult(http);
        String data = JSONObject.toJSONString(result.getData());
        List<UserPermissionPO> userPermissionPOList = JSONObject.parseArray(data, UserPermissionPO.class);
        return toUserPermissionVO(userPermissionPOList);
    }

    /**
     * 转换实体类
     *
     * @param userPermissionPOList List<UserPermissionPO>
     * @return List<UserPermissionVO>
     */
    private List<UserPermissionVO> toUserPermissionVO(List<UserPermissionPO> userPermissionPOList) {
        return userPermissionPOList.stream().map(u -> new UserPermissionVO() {{
            setId(u.getId());
            setTitle(u.getPtitle());
            setBusiness(u.getPbusiness());
            setFather(u.getPfather());
            setIco(u.getPico());
            setInterfaceName(u.getPinterface());
            setLayer(u.getPolayer());
            setHtmlId(u.getPhtmlid());
            setType(u.getPtype());
        }}).collect(Collectors.toList());
    }

    /**
     * 将权限项递归生成树
     *
     * @param param  List<UserPermissionVO>
     * @param rootId String
     * @return List<UserPermissionVO>
     */
    private List<UserPermissionVO> userPermissionToTree(List<UserPermissionVO> param, String rootId) {
        List<UserPermissionVO> result = param.stream().filter(u -> rootId.equals(u.getFather())).collect(Collectors.toList());
        result.forEach(u -> u.setChildren(userPermissionToTree(param, u.getId())));
        return result;
    }

    /**
     * 获取基本权限项数据
     *
     * @param param List<ChangeWmsPerVO>
     * @return List<ChangeWmsPerVO>
     */
    private List<ChangeWmsPerVO> getBasicRootPermission(List<ChangeWmsPerVO> param) {
        List<ChangeWmsPerVO> rootPermissionList = param.stream().filter(u -> param.stream().noneMatch(
                x -> u.getFatherId().equals(x.getId()))).collect(Collectors.toList());
        if (!ObjectUtils.isEmpty(rootPermissionList)) {
            rootPermissionList.forEach(u -> u.setChildren(basicPermissionToTree(param, u.getId())));
            return rootPermissionList;
        }
        return param;
    }

    /**
     * 将权限项递归生成树
     *
     * @param param  List<ChangeWmsPerVO>
     * @param rootId String
     * @return List<ChangeWmsPerVO>
     */
    private List<ChangeWmsPerVO> basicPermissionToTree(List<ChangeWmsPerVO> param, String rootId) {
        List<ChangeWmsPerVO> result = param.stream().filter(u -> rootId.equals(u.getFatherId())).collect(Collectors.toList());
        result.forEach(u -> u.setChildren(basicPermissionToTree(param, u.getId())));
        return result;
    }

    private HttpResult returnHttpResult(HTTPResponse http) {
        HttpResult result = null;
        //校验请求是否成功
        if (http.isSuccessful()) {
            result = gson.fromJson(http.getBodyString(), HttpResult.class);
        }
        if (result == null) {
            LOGGER.error("Http请求出错,HttpResult结果为null");
        }
        return result;
    }

    private OtherHttpResult verifyOtherHttpResult(HTTPResponse http) {
        OtherHttpResult result = null;
        //校验请求是否成功
        if (http.isSuccessful()) {
            result = gson.fromJson(http.getBodyString(), OtherHttpResult.class);
        }
        if (result == null) {
            LOGGER.error("Http请求出错,HttpResult结果为null");
        }
        return result;
    }
}
