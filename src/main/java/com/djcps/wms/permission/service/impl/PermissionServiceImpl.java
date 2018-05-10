package com.djcps.wms.permission.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.base.BaseListPO;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.httpclient.OtherHttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.permission.constants.PermissionConstants;
import com.djcps.wms.permission.model.bo.*;
import com.djcps.wms.permission.model.po.GetOnePermissionPO;
import com.djcps.wms.permission.model.po.GetPermissionPackagePO;
import com.djcps.wms.permission.model.po.GetWmsPerPO;
import com.djcps.wms.permission.model.vo.ChangeOnePerVO;
import com.djcps.wms.permission.model.vo.ChangePerPackageVO;
import com.djcps.wms.permission.model.vo.ChangeWmsPerVO;
import com.djcps.wms.permission.model.vo.UserPermissionVO;
import com.djcps.wms.permission.redis.PermissionRedisDao;
import com.djcps.wms.permission.server.PermissionServer;
import com.djcps.wms.permission.service.PermissionService;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * @author zhq
 * 权限服务层
 * 2018年4月13日
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(PermissionService.class);

    @Autowired
    private PermissionServer permissionServer;

    @Autowired
    private PermissionRedisDao permissionRedisDao;

    /**
     * 得到组合权限的数据
     */
    @Override
    public Map<String, Object> getPermissionList(PermissionBO param) {
        if (ObjectUtils.isEmpty(param.getKeyword())) {
            param.setKeyword("");
        }
        GetPermissionBO getPermissionBO = new GetPermissionBO();
        BeanUtils.copyProperties(param, getPermissionBO);
        getPermissionBO.setCompanyID(param.getCompanyId());
        getPermissionBO.setBusiness((param.getBusiness()));
        //得到请求的数据
        OtherHttpResult result = permissionServer.getPermissionList(getPermissionBO);
        String data = JSONObject.toJSONString(result.getData());
        Integer countData = result.getTotal();
        ArrayList<GetPermissionPackagePO> listUser = gson.fromJson(data, new TypeToken<List<GetPermissionPackagePO>>() {
        }.getType());
        //规范返回字段
        List listUserChange = listUser.stream().map(x -> new ChangePerPackageVO() {{
            setBusiness(x.getPbussion());
            setDescribe(x.getPdes());
            setId(x.getId());
            setPerList(x.getPerlist());
            setTitle(x.getPtitle());
        }}).collect(Collectors.toList());
        //组织返回数据
        BaseListPO info = new BaseListPO() {{
            setList(listUserChange);
            setTotal(countData);
        }};
        return MsgTemplate.successMsg(info);
    }


    /**
     * @author zhq
     * 得到WMS权限
     */
    @Override
    public Map<String, Object> getWmsPermission(GetWmsPermissionBO param) {
        WmsPermissionBO wmsPermissionBO = new WmsPermissionBO();
        BeanUtils.copyProperties(param, wmsPermissionBO);
        wmsPermissionBO.setBusiness(param.getBusiness());
        //得到wms权限数据
        List<ChangeWmsPerVO> result = permissionRedisDao.getBasicPermission();
        if(ObjectUtils.isEmpty(result)){
            result = permissionServer.getWmsPermission(wmsPermissionBO);
            permissionRedisDao.setBasicPermission(result);
        }
        //规范返回字段
        if (!ObjectUtils.isEmpty(result)){
            return MsgTemplate.successMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }

    /**
     * 新增权限
     *
     * @author zhq
     */
    @Override
    public Map<String, Object> insertPermission(UpdatePermissionBO param) {
        if (ObjectUtils.isEmpty(param.getId())) {
            param.setId("");
        }
        InsertOrUpdatePermissionBO insertOrUpdate = new InsertOrUpdatePermissionBO();
        BeanUtils.copyProperties(param, insertOrUpdate);
        insertOrUpdate.setBusiness(param.getBusiness());
        insertOrUpdate.setCompanyID(param.getCompanyId());
        insertOrUpdate.setPdes(param.getDescribe());
        insertOrUpdate.setPtitle(param.getTitle());
        insertOrUpdate.setUserid(param.getUserId());
        insertOrUpdate.setPbussion(param.getBusinessId());
        HttpResult result = permissionServer.insertPermission(insertOrUpdate);
        String data = JSONObject.toJSONString(result.getData());
        return MsgTemplate.successMsg(data);
    }

    /**
     * @author zhq
     * 删除权限包
     * 如果有用户关联权限包，则不能删除
     */
    @Override
    public Map<String, Object> deletePermission(DeletePerParamBO param) {
        //组织参数,获取关联了该权限的用户
        GetUserByPermissionIdBO getUser = new GetUserByPermissionIdBO();
        BeanUtils.copyProperties(param, getUser);
        HttpResult resultUser = permissionServer.getUserByPermissionId(getUser);
        //如果有用户关联则删除失败，否则删除
        if (!ObjectUtils.isEmpty(resultUser.getData())) {
            return MsgTemplate.failureMsg("有用户存在关联，删除失败");
        }
        HttpResult result = permissionServer.deletePermission(param);
        String data = JSONObject.toJSONString(result.getData());
        return MsgTemplate.successMsg(data);
    }

    /**
     * @author zhq
     * 根据组合权限id和公司id，获取获取组合权限信息
     */
    @Override
    public Map<String, Object> getPerChoose(PermissionChooseBO param) {
        GetPermissionChooseBO getPerChoose = new GetPermissionChooseBO();
        BeanUtils.copyProperties(param, getPerChoose);
        getPerChoose.setCompanyID(param.getCompanyId());
        getPerChoose.setBusiness(param.getBusiness());
        //得到查询权限信息
        List<ChangeOnePerVO> changeList = permissionServer.getPerChoose(getPerChoose);
        return MsgTemplate.successMsg(changeList);
    }

    /**
     * @author zhq
     * 修改权限包
     */
    @Override
    public Map<String, Object> updatePermission(UpdatePermissionBO param) {
        InsertOrUpdatePermissionBO insertOrUpdate = new InsertOrUpdatePermissionBO();
        BeanUtils.copyProperties(param, insertOrUpdate);
        insertOrUpdate.setCompanyID(param.getCompanyId());
        insertOrUpdate.setBusiness(param.getBusiness());
        insertOrUpdate.setPdes(param.getDescribe());
        insertOrUpdate.setPtitle(param.getTitle());
        insertOrUpdate.setUserid(param.getUserId());
        insertOrUpdate.setPbussion(param.getBusinessId());
        HttpResult result = permissionServer.updatePermission(insertOrUpdate);
        String data = JSONObject.toJSONString(result.getData());
        return MsgTemplate.successMsg(data);
    }

    /**
     * 获取用户拥有权限
     * 进行redis 缓存
     *
     * @param param
     * @return
     * @autuor Chengw
     * @since 2018/4/23  15:11
     */
    @Override
    public Map<String, Object> getUserPermission(UserPermissionBO param) {
        try {
            List<UserPermissionVO> userPermissionVOList = permissionRedisDao.getPermission(param.getId());
            if (ObjectUtils.isEmpty(userPermissionVOList)) {
                userPermissionVOList = permissionServer.getUserPermission(param);
                // 设置权限缓存
				permissionRedisDao.setPermission(param.getId(),userPermissionVOList);
            }
            return MsgTemplate.successMsg(userPermissionVOList);
        } catch (Exception e) {
            LOGGER.error("获取权限 {}", e.getMessage());
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }


    @Override
    public Boolean notExistSystemPermission(String userId, String url) {
        try {
            BaseOrgBO param = new BaseOrgBO();
            param.setOperator(userId);
            param.setIp("");
            param.setBusiness(AppConstant.WMS);
            List<UserPermissionVO> basicPermissionList = permissionServer.listBasicPermission(param);
            Optional optional = basicPermissionList.stream().filter(x ->
                    x.getInterfaceName().split(AppConstant.W).clone()[0].equals(url)
            ).findFirst();
            if (optional.isPresent()) {
                return false;
            }
        }catch (Exception e){
            LOGGER.error("permission : {}",e.getMessage());
        }
        return true;
    }

    @Override
    public Boolean checkPermission(String userId, String url, Map<String, String[]> params) {
        try {
            UserPermissionBO param = new UserPermissionBO();
            param.setIp("");
            param.setpBusiness(PermissionConstants.BUSINESS_ID);
            param.setId(userId);
            param.setBusiness(AppConstant.WMS);
            param.setOperator(userId);
            List<UserPermissionVO> list = listUserPermission(param);
            Optional optional = list.stream().filter(x -> {
                String[] str = x.getInterfaceName().split(AppConstant.W).clone();
                if (str[0].equals(url)) {
                    LOGGER.info("permission: {} : {}", str[0], str[0].equals(url));
                }
                if (str[0].equals(url)) {
                    if (str.length > 1) {
                        if (!params.isEmpty()) {
                            String[] paramStr = str[1].split("=").clone();
                            int result = Arrays.binarySearch(params.get(paramStr[0]), paramStr[1]);
                            if (result >= 0) {
                                return true;
                            }
                        }
                        return false;
                    }
                    return true;
                }
                return false;
            }).findFirst();
            if (optional.isPresent()) {
                return true;
            }
        }catch (Exception e){
            LOGGER.error("permission : {}",e.getMessage());
        }
        return false;
    }

    /**
     * 获取用户拥有权限
     * 进行redis 缓存
     *
     * @param param
     * @return
     * @autuor Chengw
     * @since 2018/4/23  15:11
     */
    @Override
    public List<UserPermissionVO> listUserPermission(UserPermissionBO param) {
        try {
            List<UserPermissionVO> userPermissionVOList = permissionRedisDao.getPermission(param.getId());
            if (ObjectUtils.isEmpty(userPermissionVOList)) {
                userPermissionVOList = permissionServer.listUserPermission(param);
            }
            return userPermissionVOList;
        } catch (Exception e) {
            LOGGER.error("获取权限 {}", e.getMessage());
        }
        return null;
    }

    @Override
    public void delUserRedisPermission(String userId) {
        permissionRedisDao.delPermission(userId);
    }


}
