package com.djcps.wms.warehouse.service.impl;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.base.BaseListPartnerIdBO;
import com.djcps.wms.commons.base.BaseVO;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.model.GetCodeBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.warehouse.enums.WareHouseTypeEnum;
import com.djcps.wms.warehouse.enums.WarehouseMsgEnum;
import com.djcps.wms.warehouse.model.area.DeleteAreaBO;
import com.djcps.wms.warehouse.model.location.*;
import com.djcps.wms.warehouse.model.warehouse.DeleteWarehouseBO;
import com.djcps.wms.warehouse.model.warehouse.WarehousePO;
import com.djcps.wms.warehouse.server.LocationServer;
import com.djcps.wms.warehouse.service.LocationService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @title:库位业务层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月22日
 */
@Service
public class LocationServiceImpl implements LocationService {
	
	private static final DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(LocationServiceImpl.class);
	
	@Autowired
	private LocationServer locationServer;

	private Gson gson = GsonUtils.gson;

	@Override
	public Map<String, Object> addLocation(AddLocationBO param) {
		HttpResult result = locationServer.addLocation(param);
		//新增成功
		if(result.isSuccess()){
			//编码确认
			HttpResult verifyCode = locationServer.verifyCode(param);
			//编码确认失败打印错误，将标志改成false
			if(!verifyCode.isSuccess()){
				LOGGER.error("----wms基础服务编码确认失败----");
				SelectAllLocationListBO selectAllLocationListBO = new SelectAllLocationListBO();
				BeanUtils.copyProperties(param, selectAllLocationListBO);
				HttpResult locationResult = locationServer.getLocationAllList(selectAllLocationListBO);
				BaseVO baseVO = gson.fromJson(gson.toJson(locationResult.getData()), BaseVO.class);
				List<WarehouseLocationPO> locationList = gson.fromJson(gson.toJson(baseVO.getResult()), new TypeToken<ArrayList<WarehouseLocationPO>>(){}.getType());
				for (WarehouseLocationPO locationPO : locationList) {
					if(locationPO.getName().equals(param.getName())){
						DeleteLocationBO deleteBean = new DeleteLocationBO();
						BeanUtils.copyProperties(param, deleteBean);
						deleteBean.setId(locationPO.getId());
						HttpResult deleteResult = locationServer.deleteLocation(deleteBean);
						if(!deleteResult.isSuccess()){
							LOGGER.error("----wms基础服务编码确认失败,并且删除先存入的库位也失败----");
							return MsgTemplate.failureMsg(WarehouseMsgEnum.DELETE_LOCATION_CODE_ERROE);
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

	@Override
	public Map<String, Object> modifyLocation(UpdateLocationBO param) {
		HttpResult result = locationServer.modifyLocation(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> deleteLocation(DeleteLocationBO param) {
		HttpResult result = locationServer.deleteLocation(param);
		//删除编码
		if(result.isSuccess()){
			HttpResult deleteCode = locationServer.deleteCode(param);
			if(!deleteCode.isSuccess()){
				LOGGER.error("----wms基础服务编码删除失败,但库区实际已删除!!!!!!----");
			}
			return MsgTemplate.customMsg(deleteCode);
		}
		return MsgTemplate.customMsg(result);

	}

	@Override
	public Map<String, Object> getLocationAllList(SelectAllLocationListBO param) {
		HttpResult result = locationServer.getLocationAllList(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getLocationByAttribute(SelectLocationByAttributeBO param) {
		HttpResult result = locationServer.getLocationByAttribute(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getLocationCode(GetCodeBO getCodeBO) {
		HttpResult result = locationServer.getLocationCode(getCodeBO);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getLocationByCode(SelectLocationByAttributeBO param) {
	    
        HttpResult result = locationServer.getLocationByAttribute(param);
        Map map = (Map) result.getData();
        ArrayList list = (ArrayList) map.get("result");
        if(!ObjectUtils.isEmpty(list)){
        	Object object = list.get(0);
            result.setData(object);
            return MsgTemplate.customMsg(result);
        }else{
        	result.setData(null);
        	return MsgTemplate.failureMsg(WarehouseMsgEnum.NOT_INFO);
        }
    }
}
