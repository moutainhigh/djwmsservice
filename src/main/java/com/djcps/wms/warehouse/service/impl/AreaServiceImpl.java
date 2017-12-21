package com.djcps.wms.warehouse.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.djcps.wms.commons.model.GetCodeBO;
import com.djcps.wms.commons.model.PartnerInfoBo;
import com.djcps.wms.warehouse.model.area.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.address.model.ProvinceCityAreaCodeBo;
import com.djcps.wms.address.server.AddressServer;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.warehouse.model.area.AddAreaBO;
import com.djcps.wms.warehouse.model.area.CountyBo;
import com.djcps.wms.warehouse.model.area.ProvinceCityBo;
import com.djcps.wms.warehouse.model.area.SelectAllAreaList;
import com.djcps.wms.warehouse.model.area.StreetBo;
import com.djcps.wms.warehouse.model.area.UpdateAreaBO;
import com.djcps.wms.warehouse.model.warehouse.DeleteWarehouseBO;
import com.djcps.wms.warehouse.model.warehouse.SelectWarehouseByIdBO;
import com.djcps.wms.warehouse.server.AreaServer;
import com.djcps.wms.warehouse.service.AreaService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.fabric.xmlrpc.base.Array;
import rpc.plugin.http.HTTPResponse;

/**
 * @title:仓库管理业务层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月22日
 */
@Service
public class AreaServiceImpl implements AreaService {
	
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
		HttpResult result = wareAreaServer.addArea(param);
		return MsgTemplate.customMsg(result);
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
	public Map<String, Object> deleteArea(DeleteWarehouseBO param){
		HttpResult result = wareAreaServer.deleteArea(param);
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
	public Map<String, Object> getAreaAllList(SelectAllAreaList param){
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
		ProvinceCityBo provinceCity = gson.fromJson(gson.toJson(result.getData()), ProvinceCityBo.class);
		List<CountyBo> countyList = gson.fromJson(gson.toJson(provinceCity.getCountyList()),new TypeToken<List<CountyBo>>(){}.getType()); 
		List list = provinceCity.getCountyList();
		if(list.size()!= 0){
			for (CountyBo countyBo : countyList) {
				//数据库查出来的街道
				List<StreetBo> sqlStreetList = gson.fromJson(gson.toJson(countyBo.getStreetList()),new TypeToken<List<StreetBo>>(){}.getType());
				//以下是从地址服务获取的街道
				String countyCode = countyBo.getCountyCode();
				ProvinceCityAreaCodeBo code = new ProvinceCityAreaCodeBo();
				code.setCode(countyCode);
				HttpResult streeListByArea = addressServer.getStreeListByArea(code);
				List<StreetBo> streetList = gson.fromJson(gson.toJson(streeListByArea.getData()),new TypeToken<List<StreetBo>>(){}.getType());
				if(sqlStreetList.size() == streetList.size()){
					countyBo.setCountyStatus("2");
				}else if(sqlStreetList.size() != streetList.size()){
					countyBo.setCountyStatus("1");
				}
			}
		}else{
			for (CountyBo countyBo : countyList) {
				countyBo.setCountyStatus("0");
			}
		}
		provinceCity.setCountyList(countyList);
		result.setData(provinceCity);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAreaCode(PartnerInfoBo partnerInfoBo,AreaCode areaCode) {
		HttpResult httpResult=wareAreaServer.getAreaCode(partnerInfoBo,areaCode);
		return MsgTemplate.customMsg(httpResult);
	}

    @Override
    public Map<String, Object> getRecommendLoca(String location) {
        // TODO Auto-generated method stub
        return null;
    }

}
