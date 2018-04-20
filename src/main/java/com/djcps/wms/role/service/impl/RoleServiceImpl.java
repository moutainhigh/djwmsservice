package com.djcps.wms.role.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.role.constant.RoleConstant;
import com.djcps.wms.role.enums.RoleEnum;
import com.djcps.wms.role.model.DeleteBO;
import com.djcps.wms.role.model.OrgRoleInfoBO;
import com.djcps.wms.role.model.RoleListBO;
import com.djcps.wms.role.model.SaveBO;
import com.djcps.wms.role.model.UpdateRoleInfoBO;
import com.djcps.wms.role.model.request.GetUserStatusPO;
import com.djcps.wms.role.model.request.OrgRoleListPO;
import com.djcps.wms.role.model.request.OrgSavePO;
import com.djcps.wms.role.model.request.RoleInfoResultPO;
import com.djcps.wms.role.model.request.WmsRoleInfoPO;
import com.djcps.wms.role.server.OrgRoleHttpServer;
import com.djcps.wms.role.server.RoleHttpServer;
import com.djcps.wms.role.service.RoleService;

/**
 * 角色实现类
 * 
 * @title:
 * @description:
 * @author:wyb
 * @company:djwms
 * @create:2018/4/12
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleHttpServer roleHttpServer;

    @Autowired
    private OrgRoleHttpServer orgRoleHttpServer;

    /**
     * 获取角色列表
     * 
     * @author wyb
     * @param roleListBO
     * @return
     * @create 2018/4/13
     **/
    @Override
    public Map<String, Object> roleList(RoleListBO roleListBO) {
        roleListBO.setBussion("WMS");
        roleListBO.setIp("192.168.10.71");
        OrgRoleInfoBO orgRoleInfoBO = new OrgRoleInfoBO();
        // 从wms服务获取角色关联信息
        RoleInfoResultPO wmsRoleInfo = roleHttpServer.roleList(roleListBO);
        if (!ObjectUtils.isEmpty(wmsRoleInfo.getResult())) {
            BeanUtils.copyProperties(roleListBO, orgRoleInfoBO);
            for (WmsRoleInfoPO roleId : wmsRoleInfo.getResult()) {
                orgRoleInfoBO.setRid(roleId.getRoleId());
                // 通过角色id从org获取角色信息
                List<OrgRoleListPO> orgRoleInfo = orgRoleHttpServer.getRoleFromId(orgRoleInfoBO);
                if (!ObjectUtils.isEmpty(orgRoleInfo)) {
                    // 进行数据组合
                    if (roleId.getRoleId().equals(orgRoleInfo.get(0).getId())) {
                        roleId.setRoleDesc(orgRoleInfo.get(0).getRdesc());
                        if (!ObjectUtils.isEmpty(orgRoleInfo.get(0).getPerssions())) {
                            roleId.setPerssions(orgRoleInfo.get(0).getPerssions());
                        }
                    }
                }

            }
        }

        return MsgTemplate.successMsg(wmsRoleInfo);
    }

    /**
     * 更新角色信息
     * 
     * @param UpdateRoleInfoBO
     * @return
     * @create 2018/4/12
     */
    @Override
    public Map<String, Object> update(UpdateRoleInfoBO updateRoleInfoBO) {
        updateRoleInfoBO.setBussion("WMS");
        updateRoleInfoBO.setIp("192.168.10.71");
        OrgRoleInfoBO orgRoleInfoBO = new OrgRoleInfoBO();
        // 更新wms角色关联信息
        HttpResult baseResult = roleHttpServer.update(updateRoleInfoBO);
        HttpResult result = null;
        if (baseResult.isSuccess()) {
            BeanUtils.copyProperties(updateRoleInfoBO, orgRoleInfoBO);
            // 实体类匹配字段数值请求org服务
            if (!ObjectUtils.isEmpty(updateRoleInfoBO.getRoleTypeCode())) {
                orgRoleInfoBO.setRtype(updateRoleInfoBO.getRoleTypeCode());
            }
            if (!ObjectUtils.isEmpty(updateRoleInfoBO.getRoleName())) {
                orgRoleInfoBO.setRname(updateRoleInfoBO.getRoleName());
            }
            if (!ObjectUtils.isEmpty(updateRoleInfoBO.getRoleDesc())) {
                orgRoleInfoBO.setRdesc(updateRoleInfoBO.getRoleDesc());
            }
            if (!ObjectUtils.isEmpty(updateRoleInfoBO.getPerId())) {
                orgRoleInfoBO.setPid(updateRoleInfoBO.getPerId());
            }
            orgRoleInfoBO.setRid(updateRoleInfoBO.getRoleId());

            // 更新org角色关联信息
            result = orgRoleHttpServer.updatePostRoleManage(updateRoleInfoBO);
        } else {
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * 删除角色信息信息
     * 
     * @param deleteBO
     * @return
     * @create 2018/4/12
     */
    @Override
    public Map<String, Object> delete(DeleteBO deleteBO) {
        deleteBO.setBussion("WMS");
        deleteBO.setIp("192.168.10.71");
        List<DeleteBO> list = new ArrayList<DeleteBO>();
        HttpResult result = null;
        if (!ObjectUtils.isEmpty(deleteBO)) {
            list.add(deleteBO);
            // 获取该角色类型的所有用户状态
            List<GetUserStatusPO> userStatusList = roleHttpServer.getUserStatusList(list);
            if (!ObjectUtils.isEmpty(userStatusList)) {
                for (GetUserStatusPO getUserStatusPO : userStatusList) {
                    // 判断用户状态是否为忙碌状态
                    if (RoleConstant.BUSY.equals(getUserStatusPO.getWorkStatus())) {
                        return MsgTemplate.failureMsg(RoleEnum.USER_BUSY);
                    }
                }
            }
            // 删除角色wms关联信息
            HttpResult delWmsRoleInfo = roleHttpServer.delete(deleteBO);
            OrgRoleInfoBO orgRoleInfoBO = new OrgRoleInfoBO();
            BeanUtils.copyProperties(deleteBO, orgRoleInfoBO);
            orgRoleInfoBO.setRid(deleteBO.getRoleId());
            if (delWmsRoleInfo.isSuccess()) {
                // 删除org角色信息
                result = orgRoleHttpServer.delRoleManage(deleteBO);
            }
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * 保存角色信息信息
     * 
     * @param saveBO
     * @return
     * @create 2018/4/12
     */
    @Override
    public Map<String, Object> save(SaveBO saveBO) {
        saveBO.setBussion("WMS");
        saveBO.setIp("192.168.10.71");
        OrgRoleInfoBO orgRoleInfoBO = new OrgRoleInfoBO();
        BeanUtils.copyProperties(saveBO, orgRoleInfoBO);
        orgRoleInfoBO.setPid(saveBO.getPerId());
        orgRoleInfoBO.setRdesc(saveBO.getRoleDesc());
        orgRoleInfoBO.setRname(saveBO.getRoleName());
        orgRoleInfoBO.setRtype(saveBO.getRoleTypeCode());
        // 新增org角色关联信息
        OrgSavePO orgSavePO = orgRoleHttpServer.addPostRoleManage(orgRoleInfoBO);
        HttpResult save = null;
        if (!ObjectUtils.isEmpty(orgSavePO)) {
            saveBO.setRoleId(orgSavePO.getInsertID());
            save = roleHttpServer.save(saveBO);
        }
        return MsgTemplate.customMsg(save);
    }

    /**
     * 获取角色以及权限信息
     * 
     * @return
     * @create 2018/4/12
     */
    @Override
    public Map<String, Object> getRoleType(BaseBO param) {
        HttpResult roleTypeList = roleHttpServer.getRoleType(param);
        // @TODO权限包获取
        return MsgTemplate.customMsg(roleTypeList);
    }

}
