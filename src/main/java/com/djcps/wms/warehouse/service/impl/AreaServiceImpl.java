package com.djcps.wms.warehouse.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.djcps.wms.commons.model.GetCodeBO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.address.model.ProvinceCityAreaCodeBO;
import com.djcps.wms.address.server.AddressServer;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.warehouse.controller.AreaController;
import com.djcps.wms.warehouse.enums.WareHouseTypeEnum;
import com.djcps.wms.warehouse.enums.WarehouseMsgEnum;
import com.djcps.wms.warehouse.model.area.AddAreaBO;
import com.djcps.wms.warehouse.model.area.CountyBO;
import com.djcps.wms.warehouse.model.area.DeleteAreaBO;
import com.djcps.wms.warehouse.model.area.IsUseStreetBO;
import com.djcps.wms.warehouse.model.area.ProvinceCityBO;
import com.djcps.wms.warehouse.model.area.SelectAllAreaListBO;
import com.djcps.wms.warehouse.model.area.StreetBO;
import com.djcps.wms.warehouse.model.area.UpdateAreaBO;
import com.djcps.wms.warehouse.model.area.WarehouseAreaPO;
import com.djcps.wms.warehouse.model.warehouse.SelectWarehouseByIdBO;
import com.djcps.wms.warehouse.model.warehouse.WarehousePO;
import com.djcps.wms.warehouse.server.AreaServer;
import com.djcps.wms.warehouse.service.AreaService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @title:仓库管理业务层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月22日
 */
@Service
public class AreaServiceImpl implements AreaService {
	
	private static final DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(AreaServiceImpl.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	private AreaServer wareAreaServer;
	
	@Autowired
	private AddressServer addressServer;
	
	/**
	 * @title:新增库区
	 * @description:
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	@Override
	public Map<String, Object> addArea(AddAreaBO param){
		//先判断街道是否被使用
		IsUseStreetBO isUseStreetBO = new IsUseStreetBO();
		BeanUtils.copyProperties(param, isUseStreetBO);
		isUseStreetBO.setStreetList(param.getCountyList());
		HttpResult isUseResult = wareAreaServer.isUsedStreet(isUseStreetBO);
		if(!isUseResult.isSuccess()){
			return MsgTemplate.customMsg(isUseResult);
		}
		
		HttpResult result = wareAreaServer.addArea(param);
		//新增成功
		if(result.isSuccess()){
			//编码确认
			HttpResult verifyCode = wareAreaServer.verifyCode(param);
			//编码确认失败打印错误，将标志改成false
			if(!verifyCode.isSuccess()){
				LOGGER.error("----wms基础服务编码确认失败----");
				SelectAllAreaListBO selectAllAreaListBO = new SelectAllAreaListBO();
				BeanUtils.copyProperties(param, selectAllAreaListBO);
				HttpResult areaResult = wareAreaServer.getAreaAllList(selectAllAreaListBO);
				List<WarehouseAreaPO> areaList = gson.fromJson(gson.toJson(areaResult.getData()), new TypeToken<ArrayList<WarehouseAreaPO>>(){}.getType());
				for (WarehouseAreaPO warehouseAreaPO : areaList) {
					if(warehouseAreaPO.getName().equals(param.getName())){
						DeleteAreaBO delete = new DeleteAreaBO();
						BeanUtils.copyProperties(param, delete);
						delete.setId(warehouseAreaPO.getId());
						delete.setCodeType(WareHouseTypeEnum.WAREHOUSE_AREA_CODE.getValue());
						HttpResult deleteResult = wareAreaServer.deleteArea(delete);
						if(!deleteResult.isSuccess()){
							LOGGER.error("----wms基础服务编码确认失败,并且删除先存入的库区也失败----");
							return MsgTemplate.failureMsg(WarehouseMsgEnum.DELETE_AREA_CODE_ERROE);
						}
						return MsgTemplate.customMsg(deleteResult); 
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
	 * @title:修改库区
	 * @description:
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	@Override
	public Map<String, Object> modifyArea(UpdateAreaBO param){
		HttpResult result = wareAreaServer.modifyArea(param);
		return MsgTemplate.customMsg(result);
	}

	/**
	 * @title:删除库区
	 * @description:
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	@Override
	public Map<String, Object> deleteArea(DeleteAreaBO param){
		HttpResult result = wareAreaServer.deleteArea(param);
		//删除编码
		if(result.isSuccess()){
			HttpResult deleteCode = wareAreaServer.deleteCode(param);
			if(!deleteCode.isSuccess()){
				LOGGER.error("----wms基础服务编码删除失败,但库区实际已删除!!!!!!----");
			}
			return MsgTemplate.customMsg(deleteCode);
		}
		return MsgTemplate.customMsg(result);
	}

	/**
	 * @title:获取所有库区
	 * @description:
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	@Override
	public Map<String, Object> getAreaAllList(SelectAllAreaListBO param){
		HttpResult result = wareAreaServer.getAreaAllList(param);
		return MsgTemplate.customMsg(result);
	}

	/**
	 * @title:根据id获取库区
	 * @description:
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	@Override
	public Map<String, Object> getAreaById(SelectWarehouseByIdBO param){
		HttpResult result = wareAreaServer.getAreaById(param);
		ProvinceCityBO provinceCity = gson.fromJson(gson.toJson(result.getData()), ProvinceCityBO.class);
		List<CountyBO> countyList = gson.fromJson(gson.toJson(provinceCity.getCountyList()),new TypeToken<List<CountyBO>>(){}.getType());
		List list = provinceCity.getCountyList();
		if(list.size()!= 0){
			for (CountyBO countyBo : countyList) {
				//数据库查出来的街道
				List<StreetBO> sqlStreetList = gson.fromJson(gson.toJson(countyBo.getStreetList()),new TypeToken<List<StreetBO>>(){}.getType());
				//以下是从地址服务获取的街道
				String countyCode = countyBo.getCountyCode();
				ProvinceCityAreaCodeBO code = new ProvinceCityAreaCodeBO();
				code.setCode(countyCode);
				HttpResult streeListByArea = addressServer.getStreeListByArea(code);
				List<StreetBO> streetList = gson.fromJson(gson.toJson(streeListByArea.getData()),new TypeToken<List<StreetBO>>(){}.getType());
				if(sqlStreetList.size() == streetList.size()){
					countyBo.setCountyStatus("2");
				}else if(sqlStreetList.size() != streetList.size()){
					countyBo.setCountyStatus("1");
				}
			}
		}else{
			for (CountyBO countyBo : countyList) {
				countyBo.setCountyStatus("0");
			}
		}
		provinceCity.setCountyList(countyList);
		result.setData(provinceCity);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAreaCode(GetCodeBO getCodeBO) {
		HttpResult httpResult=wareAreaServer.getAreaCode(getCodeBO);
		return MsgTemplate.customMsg(httpResult);
	}

}
