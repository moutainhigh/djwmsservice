package com.djcps.wms.permission.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.base.BaseListPO;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.httpclient.OtherHttpResult;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.permission.constants.PermissionConstants;
import com.djcps.wms.permission.model.bo.BaseOrgBO;
import com.djcps.wms.permission.model.bo.DeletePerParamBO;
import com.djcps.wms.permission.model.bo.GetPermissionBO;
import com.djcps.wms.permission.model.bo.GetPermissionChooseBO;
import com.djcps.wms.permission.model.bo.GetUserByPermissionIdBO;
import com.djcps.wms.permission.model.bo.GetWmsPermissionBO;
import com.djcps.wms.permission.model.bo.InsertOrUpdatePermissionBO;
import com.djcps.wms.permission.model.bo.PermissionBO;
import com.djcps.wms.permission.model.bo.PermissionChooseBO;
import com.djcps.wms.permission.model.bo.UpdatePermissionBO;
import com.djcps.wms.permission.model.bo.UserPermissionBO;
import com.djcps.wms.permission.model.bo.WmsPermissionBO;
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

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * @author zhq
 * 权限服务层
 * 2018年4月13日
 */
@Service
public class PermissionServiceImpl implements PermissionService{
	
    private DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(PermissionService.class);
     
	@Autowired
	private PermissionServer permissionServer;
	
	@Autowired
	private PermissionRedisDao permissionRedisDao;
	
	/**
	 * 得到组合权限的数据
	 */
	@Override
	public Map<String, Object> getPermissionList(PermissionBO param) {
			if(ObjectUtils.isEmpty(param.getKeyWord())) {
				param.setKeyWord("");
			}
			GetPermissionBO getPermissonBO=new GetPermissionBO();
			BeanUtils.copyProperties(param, getPermissonBO);
			getPermissonBO.setCompanyID(param.getCompanyId());
			getPermissonBO.setBusiness((param.getBusiness()));
			//得到请求的数据
			OtherHttpResult result =permissionServer.getPermissionList(getPermissonBO);
			String data = JSONObject.toJSONString(result.getData());
			Integer countData = result.getTotal();
			/*Integer	count;
			if(!ObjectUtils.isEmpty(countData)) {
			    count=Integer.parseInt(countData);
			}else {
			    count=0;
			}*/
			ArrayList<GetPermissionPackagePO> listUser = gson.fromJson(data,new TypeToken<List<GetPermissionPackagePO>>() {}.getType());
			//规范返回字段
			if(!ObjectUtils.isEmpty(listUser)) {
				List listUserChange=listUser.stream().map(x->new ChangePerPackageVO() {{
						setBussion(x.getPbussion());
						setDescribe(x.getPdes());
						setId(x.getId());
						setPerList(x.getPerlist());
						setTitle(x.getPtitle());
				}}).collect(Collectors.toList());
				//组织返回数据
				BaseListPO info=new BaseListPO() {{
					setList(listUserChange);
					setTotal(countData);
				}};
				return MsgTemplate.successMsg(info);
			}else {
				return MsgTemplate.successMsg(null);
			}
			
	}
	

	/**
	 * @author zhq
	 * 得到WMS权限
	 */
	@Override
	public Map<String, Object> getWmsPermission(GetWmsPermissionBO param) {
		WmsPermissionBO wmsPermissionBO=new WmsPermissionBO();
		BeanUtils.copyProperties(param, wmsPermissionBO);
		wmsPermissionBO.setBusiness(param.getBusiness());
		//得到wms权限数据
		HttpResult result =permissionServer.getWmsPermission(wmsPermissionBO);
		String data = JSONObject.toJSONString(result.getData());
		ArrayList<GetWmsPerPO> listUser = gson.fromJson(data,new TypeToken<List<GetWmsPerPO>>() {}.getType());
		//规范返回字段
		if(!ObjectUtils.isEmpty(listUser)) {
			List listUserChange=listUser.stream().map(x->new ChangeWmsPerVO() {{
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
			return MsgTemplate.successMsg(listUserChange);
		}else {
			return MsgTemplate.successMsg(null);
		}
		
	}

	/**
	 * 新增权限
	 * @author zhq
	 */
	@Override
	public Map<String, Object> insertPermission(UpdatePermissionBO param) {
		if(ObjectUtils.isEmpty(param.getId())) {
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
		HttpResult result =permissionServer.insertPermission(insertOrUpdate);
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
		GetUserByPermissionIdBO getUser=new GetUserByPermissionIdBO();
		BeanUtils.copyProperties(param, getUser);
		HttpResult resultUser =permissionServer.getUserByPermissionId(getUser);
		//如果有用户关联则删除失败，否则删除
		if(!ObjectUtils.isEmpty(resultUser.getData())) {
			return MsgTemplate.failureMsg("有用户存在关联，删除失败");
		}
		HttpResult result =permissionServer.deletePermission(param);
		String data = JSONObject.toJSONString(result.getData());
		return MsgTemplate.successMsg(data);
	}

	/**
	 * @author zhq
	 * 根据组合权限id和公司id，获取获取组合权限信息
	 */
	@Override
	public Map<String, Object> getPerChoose(PermissionChooseBO param) {
		GetPermissionChooseBO getPerChoose=new GetPermissionChooseBO();
		BeanUtils.copyProperties(param, getPerChoose);
		getPerChoose.setCompanyID(param.getCompanyId());
		getPerChoose.setBusiness(param.getBusiness());
		//得到查询权限信息
		HttpResult result =permissionServer.getPerChoose(getPerChoose);
		String data = JSONObject.toJSONString(result.getData());
		ArrayList<GetOnePermissionPO> list = gson.fromJson(data,new TypeToken<List<GetOnePermissionPO>>() {}.getType());
		//规范返回字段
		List listChange=list.stream().map(x->new ChangeOnePerVO() {{
				setTitle(x.getPtitle());
				setDescribe(x.getPdes());
				setCompanyId(x.getPcompany());
				setUserId(x.getPuserid());
				setPerList(x.getPperlist());
				setBussion(x.getPbussion());
				setId(x.getId());
				setIsDel(x.getIsdel());
				setDeletePerson(x.getIsdel_per());
				setCreateTime(x.getCreate_time());
				setUpdateTime(x.getUpdate_time());
		}}).collect(Collectors.toList());
		return MsgTemplate.successMsg(listChange);
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
     * @autuor Chengw
     * @since 2018/4/23  15:11
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> getUserPermission(UserPermissionBO param) {
        try {
            List<UserPermissionVO> userPermissionVOList = permissionRedisDao.getPermission(param.getId());
            if(ObjectUtils.isEmpty(userPermissionVOList)){
                userPermissionVOList = permissionServer.getUserPermission(param);
            }
            return MsgTemplate.successMsg(userPermissionVOList);
        }catch (Exception e){
            LOGGER.error("获取权限 {}", e.getStackTrace());
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }


    @Override
    public Boolean notExistSystemPermission(String userId, String url) {
        BaseOrgBO param = new BaseOrgBO();
        param.setOperator("-1");
        param.setIp("");
        param.setBusiness(AppConstant.WMS);
        List<UserPermissionVO> basicPermissionList = permissionServer.listBasicPermission(param);
        Optional optional = basicPermissionList.stream().filter(x ->
                x.getInterfaceName().split(AppConstant.W).clone()[0].equals(url)
        ).findFirst();
        if(optional.isPresent()){
            return false;
        }
        return true;
    }

    /**
     * 获取用户拥有权限
     * 进行redis 缓存
     * @autuor Chengw
     * @since 2018/4/23  15:11
     * @param param
     * @return
     */
    @Override
    public List<UserPermissionVO> listUserPermission(UserPermissionBO param) {
        try {
            List<UserPermissionVO> userPermissionVOList = permissionRedisDao.getPermission(param.getId());
            if(ObjectUtils.isEmpty(userPermissionVOList)){
                userPermissionVOList = permissionServer.listUserPermission(param);
            }
            return userPermissionVOList;
        }catch (Exception e){
            LOGGER.error("获取权限 {}", e.getStackTrace());
        }
        return null;
    }

    @Override
    public void delUserRedisPermission(String userId) {
        permissionRedisDao.delPermission(userId);
    }
	

}
