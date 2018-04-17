package com.djcps.wms.warehouse.service.impl;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.allocation.model.AddAllocationOrderBO;
import com.djcps.wms.commons.base.BaseListBO;
import com.djcps.wms.commons.base.BaseListPartnerIdBO;
import com.djcps.wms.commons.base.BaseVO;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.model.GetCodeBO;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.warehouse.enums.WareHouseTypeEnum;
import com.djcps.wms.warehouse.enums.WarehouseMsgEnum;
import com.djcps.wms.warehouse.model.location.DeleteLocationBO;
import com.djcps.wms.warehouse.model.warehouse.*;
import com.djcps.wms.warehouse.server.WarehouseServer;
import com.djcps.wms.warehouse.service.WarehouseService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @title:仓库管理业务层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月22日
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {
	
	private static final DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(WarehouseServiceImpl.class);
	
	@Autowired
	private WarehouseServer warehouseServer;

	Gson gson = new Gson();

	/**
	 * @title:新增仓库
	 * @description:
	 * @param addBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Override
	public Map<String, Object> add(AddWarehouseBO addBean){
		HttpResult result = warehouseServer.add(addBean);
		//新增成功
		if(result.isSuccess()){
			//编码确认
			HttpResult verifyCode = warehouseServer.verifyCode(addBean);
			//编码确认失败打印错误，将标志改成false
			if(!verifyCode.isSuccess()){
				LOGGER.error("----wms基础服务编码确认失败----");
				BaseListPartnerIdBO baseListParam = new BaseListPartnerIdBO();
				BeanUtils.copyProperties(addBean, baseListParam);
				HttpResult warehouseResult = warehouseServer.getAllList(baseListParam);
				BaseVO baseVO = gson.fromJson(gson.toJson(warehouseResult.getData()), BaseVO.class);
				List<WarehousePO> warehouseList = gson.fromJson(gson.toJson(baseVO.getResult()), new TypeToken<ArrayList<WarehousePO>>(){}.getType());
				for (WarehousePO warehousePO : warehouseList) {
					if(warehousePO.getName().equals(addBean.getName())){
						IsUseWarehouseBO isUseBean = new IsUseWarehouseBO();
						BeanUtils.copyProperties(addBean, isUseBean);
						isUseBean.setId(warehousePO.getId());
						HttpResult isUseResult = warehouseServer.disable(isUseBean);
						if(isUseResult.isSuccess()){
							DeleteWarehouseBO deleteBean = new DeleteWarehouseBO();
							BeanUtils.copyProperties(addBean, deleteBean);
							deleteBean.setId(warehousePO.getId());
							HttpResult deleteResult = warehouseServer.delete(deleteBean);
							if(!deleteResult.isSuccess()){
								LOGGER.error("----wms基础服务编码确认失败,并且删除先存入的仓库也失败----");
								return MsgTemplate.failureMsg(WarehouseMsgEnum.DELETE_WAREHOUSE_CODE_ERROE);
							}
							return MsgTemplate.customMsg(deleteResult); 
						}else{
							LOGGER.error("----wms基础服务编码确认失败,并且删除先存入的仓库前,禁用该仓库失败----");
							return MsgTemplate.customMsg(isUseResult); 
						}
					}
				}
			}
			return MsgTemplate.customMsg(verifyCode);
		}else{
			//新增失败直接返回错误信息
			return MsgTemplate.customMsg(result);
		}
	}

	/**
	 * @title:修改仓库
	 * @description:
	 * @param updateBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Override
	public Map<String, Object> modify(UpdateWarehouseBO updateBean){
		HttpResult result = warehouseServer.modify(updateBean);
		return MsgTemplate.customMsg(result);
	}

	/**
	 * @title:删除仓库
	 * @description:
	 * @param deleteBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Override
	public Map<String, Object> delete(DeleteWarehouseBO deleteBean){
		HttpResult result = warehouseServer.delete(deleteBean);
		//删除编码
		if(result.isSuccess()){
			HttpResult deleteCode = warehouseServer.deleteCode(deleteBean);
			if(!deleteCode.isSuccess()){
				LOGGER.error("----wms基础服务编码删除失败,但仓库实际已删除!!!!!!----");
			}
			return MsgTemplate.customMsg(deleteCode);
		}
		return MsgTemplate.customMsg(result);
	}

	/**
	 * @title:获取所有仓库
	 * @description:
	 * @param baseListParam
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Override
	public Map<String, Object> getAllList(BaseListPartnerIdBO baseListParam){
		HttpResult result = warehouseServer.getAllList(baseListParam);
		return MsgTemplate.customMsg(result);
	}

	/**
	 * @title:根据仓库唯一标识id获取仓库
	 * @description:
	 * @param selectByIdBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Override
	public Map<String, Object> getWarehouseById(SelectWarehouseByIdBO selectByIdBean){
		HttpResult result = warehouseServer.getWarehouseById(selectByIdBean);
		return MsgTemplate.customMsg(result);
	}

	/**
	 * @title:根据仓库属性模糊查询获取仓库
	 * @description:
	 * @param selectVagueBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Override
	public Map<String, Object> getWarehouseByAttribute(SelectWarehouseByAttributeBO selectVagueBean){
		HttpResult result = warehouseServer.getWarehouseByAttribute(selectVagueBean);
		return MsgTemplate.customMsg(result);
	}

	/**
	 * @title:启用仓库
	 * @description:
	 * @param isUseBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Override
	public Map<String, Object> enable(IsUseWarehouseBO isUseBean){
		HttpResult result = warehouseServer.enable(isUseBean);
		return MsgTemplate.customMsg(result);
	}

	/**
	 * @title:禁用仓库
	 * @description:
	 * @param isUseBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月29日
	 */
	@Override
	public Map<String, Object> disable(IsUseWarehouseBO isUseBean){
		HttpResult result = warehouseServer.disable(isUseBean);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getWarehouseType(String partnerId) {
		HttpResult result = warehouseServer.getWarehouseType(partnerId);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAllWarehouseName(PartnerInfoBO partnerInfoBean) {
		HttpResult result = warehouseServer.getAllWarehouseName(partnerInfoBean);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getWarehouseCode(GetCodeBO getCodeBO) {
		HttpResult result=warehouseServer.getWarehouseCode(getCodeBO);
		return MsgTemplate.customMsg(result);
	}
}
