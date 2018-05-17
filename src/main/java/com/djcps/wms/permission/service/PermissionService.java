package com.djcps.wms.permission.service;

import com.djcps.wms.permission.model.bo.*;
import com.djcps.wms.permission.model.vo.UserPermissionVO;

import java.util.List;
import java.util.Map;


/**
 * @author zhq
 * <p>
 * 2018年4月12日
 */
public interface PermissionService {

    /**
     * 获取组合权限列表
     *
     * @param param
     * @return Map
     */
    Map<String, Object> getPermissionList(PermissionBO param);

    /**
     * 获取WMS权限
     *
     * @param param
     * @return Map
     */
    Map<String, Object> getWmsPermission(GetWmsPermissionBO param);

    /**
     * 新增组合权限
     *
     * @param param,@param partner
     * @return Map
     */
    Map<String, Object> insertPermission(UpdatePermissionBO param);

    /**
     * 删除组合权限
     *
     * @param param
     * @return Map
     */
    Map<String, Object> deletePermission(DeletePerParamBO param);

    /**
     * 根据组合权限id和公司id，获取获取组合权限集合
     *
     * @param param
     * @return Map
     */
    Map<String, Object> getPerChoose(PermissionChooseBO param);

    /**
     * 更新组合权限
     *
     * @param param,@param partnerInfoBO
     * @return Map
     */
    Map<String, Object> updatePermission(UpdatePermissionBO param);

    /**
     * 获取用户所有权限
     *
     * @param param
     * @return
     * @author Chengw
     * @since 2018/4/23  20:33
     */
    Map<String, Object> getUserPermission(UserPermissionBO param);

    /**
     * 获取用户所有权限 list
     *
     * @param param
     * @return
     * @author Chengw
     * @since 2018/4/23  20:34
     */
    List<UserPermissionVO> listUserPermission(UserPermissionBO param);

    /**
     * 校验是否不存在系统权限项
     *
     * @param userId
     * @param url
     * @return
     */
    Boolean notExistSystemPermission(String userId, String url);

    /**
     * 校验是否有权限 访问
     *
     * @param userId
     * @param url
     * @param params
     * @return
     */
    Boolean checkPermission(String userId, String url, Map<String, String[]> params);

    /**
     * 删除redis 权限缓存
     *
     * @param userId
     */
    void delUserRedisPermission(String userId);
}
