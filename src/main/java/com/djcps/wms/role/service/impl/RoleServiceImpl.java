package com.djcps.wms.role.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.role.constant.RoleConstant;
import com.djcps.wms.role.enums.RoleEnum;
import com.djcps.wms.role.model.DeleteBO;
import com.djcps.wms.role.model.RoleListBO;
import com.djcps.wms.role.model.SaveBO;
import com.djcps.wms.role.model.UpdateRoleInfoBO;
import com.djcps.wms.role.model.request.GetUserStatusPO;
import com.djcps.wms.role.model.request.RoleInfoResultPO;
import com.djcps.wms.role.model.request.RoleListPO;
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
     * @author  wyb
     * @param roleListBO
     * @return 
     * @create  2018/4/13
     **/
    @Override
    public Map<String, Object> roleList(RoleListBO roleListBO) {
        //从wms服务获取角色关联信息
        RoleInfoResultPO wmsRoleInfo = roleHttpServer.roleList(roleListBO);
        if (!ObjectUtils.isEmpty(wmsRoleInfo.getResult())) {
            for (RoleListPO roleId : wmsRoleInfo.getResult()) {
                roleListBO.setRid(roleId.getRid());
                //通过角色id从org获取角色信息
                List<RoleListPO> orgRoleInfo = orgRoleHttpServer.getRoleFromId(roleListBO);
                if(!ObjectUtils.isEmpty(orgRoleInfo)) {
                    //进行数据组合
                    if(roleId.getRid().equals(orgRoleInfo.get(0).getRid())) {
                        roleId.setRdesc(orgRoleInfo.get(0).getRdesc());
                        
                        if(!ObjectUtils.isEmpty(orgRoleInfo.get(0).getPid())) {
                        roleId.setPid(orgRoleInfo.get(0).getPid());
                        }
                    }
                }
               
            }
        }

        return MsgTemplate.successMsg(wmsRoleInfo);
    }
    
    /**
     * 更新角色信息
     * @param UpdateRoleInfoBO
     * @return
     * @create  2018/4/12
     */
    @Override
    public Map<String, Object> update(UpdateRoleInfoBO UpdateRoleInfoBO) {
        //更新wms角色关联信息
        HttpResult baseResult = roleHttpServer.update(UpdateRoleInfoBO);
        HttpResult result = null;
        if(baseResult.isSuccess()) {
          //更新org角色关联信息
            result = orgRoleHttpServer.updatePostRoleManage(UpdateRoleInfoBO);
        }else {
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
        return MsgTemplate.customMsg(result);
    }
    /**
     * 获取角色以及权限信息
     * @param roleListBO
     * @return
     * @create  2018/4/12
     */
    @Override
    public Map<String, Object> roleInfo(RoleListBO roleListBO) {
      //从wms服务获取角色关联信息
        RoleInfoResultPO wmsRoleInfo = roleHttpServer.roleList(roleListBO);
        //@TODO权限包获取
        return null;
    }
    /**
     * 删除角色信息信息
     * @param deleteBO
     * @return
     * @create  2018/4/12
     */
    @Override
    public Map<String, Object> delete(DeleteBO deleteBO) {
        List<DeleteBO> list = new ArrayList<DeleteBO>();
        HttpResult result = null;
        if(!ObjectUtils.isEmpty(deleteBO)) {
        list.add(deleteBO);
        //获取该角色类型的所有用户状态
        List<GetUserStatusPO> userStatusList = roleHttpServer.getUserStatusList(list);
        for(GetUserStatusPO getUserStatusPO : userStatusList) {
            //判断用户状态是否为忙碌状态
            if(RoleConstant.BUSY.equals(getUserStatusPO.getWorkStatus())) {
                return MsgTemplate.failureMsg(RoleEnum.USER_BUSY);
            }
        }
        //删除角色wms关联信息
        HttpResult delWmsRoleInfo = roleHttpServer.delete(deleteBO);
        if(delWmsRoleInfo.isSuccess()) {
            //删除org角色信息
            result = orgRoleHttpServer.delRoleManage(deleteBO);
        }
        }
        return MsgTemplate.customMsg(result);
    }
    /**
     * 保存角色信息信息
     * @param saveBO
     * @return
     * @create  2018/4/12
     */
    @Override
    public Map<String, Object> save(SaveBO saveBO) {
        saveBO.setBussion("WMS");
        saveBO.setIp("192.168.10.71");
      //新增org角色关联信息
        HttpResult result = orgRoleHttpServer.savePostRoleManage(saveBO);
        if(result.isSuccess()) {
            String id=UUID.randomUUID().toString();
            saveBO.setId(id);
            HttpResult save = roleHttpServer.save(saveBO);
        }
        return MsgTemplate.customMsg(result);
    }

}
