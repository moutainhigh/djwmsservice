package com.djcps.wms.inneruser.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.base.BaseVO;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.utils.HttpResultUtils;
import com.djcps.wms.inneruser.model.param.*;
import com.djcps.wms.inneruser.model.result.*;
import com.djcps.wms.inneruser.request.UserRequest;
import com.djcps.wms.inneruser.request.WmsForUserRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import rpc.plugin.http.HTTPResponse;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户管理
 *
 * @author wzy
 * @date 2018/4/12
 **/
@Repository
public class UserServer {

    private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(UserServer.class);

    private Gson gson = new GsonBuilder().create();

    @Resource
    private UserRequest userRequest;

    @Resource
    private WmsForUserRequest wmsForUserRequest;

    /**
     * 从org批量根据用户id获取用户基本信息
     *
     * @param orgGetUserInfo BatchOrgUserInfoBO
     * @return List<OrgUserInfoPO>
     * @author wzy
     * @date 2018/4/12 14:06
     **/
    public List<UserInfoPO> getUserListByOrg(BatchOrgUserInfoBO orgGetUserInfo) {
        String json = JSONObject.toJSONString(orgGetUserInfo);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.getUserInfoList(map);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                List<OrgUserInfoPO> list = JSONArray.parseArray(data, OrgUserInfoPO.class);
                return toUserInfo(list);
            }
        }
        return null;
    }

    /**
     * 从org获取该用户的全部信息
     *
     * @param orgGetUserInfo GetOrgUserInfoBO
     * @return OrgUserInfoPO
     * @author wzy
     * @date 2018/4/12 14:39
     **/
    public UserInfoPO getUserByOrg(GetOrgUserInfoBO orgGetUserInfo) {
        String json = JSONObject.toJSONString(orgGetUserInfo);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.getUserInfo(map);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                List<OrgUserInfoPO> list = JSONArray.parseArray(data, OrgUserInfoPO.class);
                if (list.size() > 0) {
                    List<UserInfoPO> userInfoList = toUserInfo(list);
                    return userInfoList.get(0);
                }
            }
        }
        return null;
    }

    /**
     * 转化字段参数
     *
     * @param list
     * @return
     */
    private List<UserInfoPO> toUserInfo(List<OrgUserInfoPO> list) {
        return list.stream().map(orgUserInfoPO -> new UserInfoPO() {{
            setId(orgUserInfoPO.getId());
            setCompanyId(orgUserInfoPO.getUcompany_id());
            setUserName(orgUserInfoPO.getUname());
            setIdCard(orgUserInfoPO.getUid_card());
            setBirthday(orgUserInfoPO.getUbirthday());
            setDepartmentId(orgUserInfoPO.getUfdepartment_id());
            setDepartmentName(orgUserInfoPO.getUfdepartment());
            setEducation(orgUserInfoPO.getUeducation());
            setEmail(orgUserInfoPO.getUemail());
            setGraduateSchool(orgUserInfoPO.getUgraduate_school());
            setHomeAddress(orgUserInfoPO.getUhome_address());
            setInduction(orgUserInfoPO.getUinduction());
            setJob(orgUserInfoPO.getUjob());
            setMaritalStatus(orgUserInfoPO.getUmarital_status());
            setNation(orgUserInfoPO.getUnation());
            setPhone(orgUserInfoPO.getUphone());
            setPassword(orgUserInfoPO.getUpassword());
            setPlaceOrigin(orgUserInfoPO.getUplace_origin());
            setPoliticalOutlook(orgUserInfoPO.getUpolitical_outlook());
            setSex(orgUserInfoPO.getUsex());
            setShortPhone(orgUserInfoPO.getUshort_phone());
            setPositionId(orgUserInfoPO.getCmp_user_position__id());
            setPositionName(orgUserInfoPO.getUposition_name());
            setUserStatus(orgUserInfoPO.getUserstatus());
            setRegisteredResidence(orgUserInfoPO.getUregistered_residence());
        }}).collect(Collectors.toList());
    }

    /**
     * org开启/禁用用户
     *
     * @param orgDeleteUserBO OrgDeleteUserBO
     * @return Boolean
     * @author wzy
     * @date 2018/4/13 13:36
     **/
    public Boolean updateCloseOpenUser(OrgDeleteUserBO orgDeleteUserBO) {
        String json = JSONObject.toJSONString(orgDeleteUserBO);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.updateCloseOpenUser(map);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (!ObjectUtils.isEmpty(result)) {
                return result.isSuccess();
            }
        }
        return false;
    }

    /**
     * wms获取单条用户关联信息
     *
     * @param userBO UserBO
     * @return UserRelevanceBO
     * @author wzy
     * @date 2018/4/13 10:32
     **/
    public UserRelevancePO getUserRelevance(UserBO userBO) {
        String paramJson = JSONObject.toJSONString(userBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.getUserRelevance(requestBody);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                return gson.fromJson(data, UserRelevancePO.class);
            }
        }
        return null;
    }

    /**
     * wms修改用户工作状态等信息
     *
     * @param updateUserBO UpdateUserBO
     * @return HTTPResponse
     * @author wzy
     * @date 2018/4/13 9:31
     **/
    public HttpResult updateUserRelevance(UpdateUserBO updateUserBO) {
        String paramJson = JSONObject.toJSONString(updateUserBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.updateUserRelevance(requestBody);
        return HttpResultUtils.returnResult(httpResponse);
    }

    /**
     * wms删除用户关联信息
     *
     * @param userBO UserBO
     * @return result
     * @author wzy
     * @date 2018/4/13 11:18
     **/
    public HttpResult deleteUserRelevance(UserBO userBO) {
        String paramJson = JSONObject.toJSONString(userBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.deleteUserRelevance(requestBody);
        return HttpResultUtils.returnResult(httpResponse);
    }

    /**
     * wms新增用户关联信息
     *
     * @param userRelevanceBO UserRelevanceBO
     * @return http
     * @author wzy
     * @date 2018/4/16 10:15
     **/
    public HttpResult saveUserRelevance(UserRelevanceBO userRelevanceBO) {
        String paramJson = JSONObject.toJSONString(userRelevanceBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.saveUserRelevance(requestBody);
        return HttpResultUtils.returnResult(httpResponse);
    }

    /**
     * WMS条件获取用户列表
     *
     * @param pageUserInfoBO PageUserInfoBO
     * @return BaseVO
     * @author wzy
     * @date 2018/4/13 15:17
     **/
    public BaseVO pageUserRelevance(PageUserInfoBO pageUserInfoBO) {
        String paramJson = JSONObject.toJSONString(pageUserInfoBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.pageUserRelevance(requestBody);
        if (httpResponse.isSuccessful()) {
            HttpResult httpResult = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (httpResult.isSuccess()) {
                String data = JSONObject.toJSONString(httpResult.getData());
                BaseVO baseVO = gson.fromJson(data,BaseVO.class);
                return baseVO;
            }
        }
        return null;
    }

    /**
     * 根据角色类型 获取用户信息
     *
     * @param userInfoBO
     * @return
     */
    public List<UserRelevancePO> listUserByRoleCode(UserInfoBO userInfoBO) {
        String json = JSONObject.toJSONString(userInfoBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        HTTPResponse httpResponse = wmsForUserRequest.listUserByRoleCode(requestBody);
        if (httpResponse.isSuccessful()) {
            HttpResult result = HttpResultUtils.returnResult(httpResponse);
            if (!ObjectUtils.isEmpty(result)) {
                if (result.isSuccess()) {
                    String data = JSONObject.toJSONString(result.getData());
                    List<UserRelevancePO> userList = JSONObject.parseArray(data, UserRelevancePO.class);
                    return userList;
                }
            }
        }
        return null;
    }

    /**
     * 从org获取该公司的所有部门信息
     *
     * @param orgGetDepartmentBO OrgGetDepartmentBO
     * @return OrgDepartmentPO
     * @author wzy
     * @date 2018/4/12 14:39
     **/
    public List<DepartmentVO> getDepartment(OrgGetDepartmentBO orgGetDepartmentBO) {
        String json = JSONObject.toJSONString(orgGetDepartmentBO);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.getDepartList(map);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                List<OrgDepartmentPO> orgDepartmentPOList = JSONArray.parseArray(data, OrgDepartmentPO.class);
                return orgDepartmentPOList.stream().map(x -> new DepartmentVO() {{
                    setDepartmentId(x.getId());
                    setDepartmentName(x.getOname());
                }}).collect(Collectors.toList());
            }
        }
        return null;
    }

    /**
     * 从org获取该公司的所有职务信息
     *
     * @param orgGetDepartmentBO OrgGetDepartmentBO
     * @return List<OrgUserJobPO>
     * @author wzy
     * @date 2018/4/17 9:25
     **/
    public List<JobVO> getUserJob(OrgGetDepartmentBO orgGetDepartmentBO) {
        String json = JSONObject.toJSONString(orgGetDepartmentBO);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.getUjob(map);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                List<OrgUserJobPO> orgUserJobPOList = JSONArray.parseArray(data, OrgUserJobPO.class);
                return orgUserJobPOList.stream().map(x -> new JobVO() {{
                    setJobId(x.getId());
                    setJobName(x.getUjob_name());
                }}).collect(Collectors.toList());
            }
        }
        return null;
    }

    public List<PositionVO> getPosition(OrgPositionBO orgPositionBO) {
        String json = JSONObject.toJSONString(orgPositionBO);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.queryPosition(map);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                List<OrgPositionPO> orgPositionPOList = JSONArray.parseArray(data, OrgPositionPO.class);
                return orgPositionPOList.stream().map(x -> new PositionVO() {{
                    setPositionId(x.getId());
                    setPositionName(x.getUposition_name());
                }}).collect(Collectors.toList());

            }
        }
        return null;
    }


    /**
     * org修改保存用户信息
     *
     * @param saveUserBO SaveUserBO
     * @return Boolean
     * @author wzy
     * @date 2018/4/16 9:50
     **/
    public Boolean updateUserManage(SaveUserBO saveUserBO) {
        String json = JSONObject.toJSONString(saveUserBO);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.updateUserManage(map);
        if (httpResponse.isSuccessful()) {
            String body = httpResponse.getBodyString();
            if (StringUtils.isNotBlank(body)) {
                HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                if (baseResult.isSuccess()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * org新增用户并返回用户信息
     *
     * @param saveUserBO SaveUserBO
     * @return saveUserBO
     * @author wzy
     * @date 2018/4/16 9:58
     **/
    public HttpResult addPostUserInfo(SaveUserBO saveUserBO) {
        String json = JSONObject.toJSONString(saveUserBO);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.addPostUserInfo(map);
        return HttpResultUtils.returnResult(httpResponse);
    }

    /**
     * 批量获取用户关联仓库信息
     *
     * @param listWarehouseBOS ListWarehouseBO
     * @return List<WarehouseListPO>
     * @author wzy
     * @date 2018/4/17 11:08
     **/
    public List<WarehouseListPO> listUserWarehouse(ListWarehouseBO listWarehouseBOS) {
        String paramJson = JSONObject.toJSONString(listWarehouseBOS);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.listUserWarehouse(requestBody);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String json = gson.toJson(result.getData());
                return JSONArray.parseArray(json, WarehouseListPO.class);
            }
        }
        return null;
    }

    /**
     * @param orgUserRoleBO
     * @return
     */
    public List<OrgUserInfoVO> listUserByRoleId(OrgUserRoleBO orgUserRoleBO) {
        String json = JSONObject.toJSONString(orgUserRoleBO);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.listUserByRoleId(map);
        HttpResult httpResult = HttpResultUtils.returnResult(httpResponse);
        if (!ObjectUtils.isEmpty(httpResult)) {
            if (httpResult.isSuccess()) {
                String data = gson.toJson(httpResult.getData());
                List<OrgUserInfoByRoleIdPO> orgUserInfoPOList = JSONArray.parseArray(data, OrgUserInfoByRoleIdPO.class);
                return orgUserInfoPOList.stream().map(x -> new OrgUserInfoVO() {{
                    setId(x.getId());
                    setUserName(x.getUname());
                    setDepartment(x.getUfdepartment());
                    setUserImage(x.getUimage());
                    setUserPhone(x.getUphone());
                    setDepartmentName(x.getUfdepartment());
                    setWholeSpell(x.getUwholespell());
                    setJob(x.getUjob());
                    setFirstSpell(x.getUfirstspell());
                    setPosition(x.getUposition());
                }}).collect(Collectors.toList());
            }
        }
        return null;
    }

}
