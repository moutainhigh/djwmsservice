package com.djcps.wms.stock.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.stock.model.AddStockBO;
import com.djcps.wms.stock.model.MapLocationPO;
import com.djcps.wms.stock.model.MoveStockBO;
import com.djcps.wms.stock.model.RecommendLocaBO;
import com.djcps.wms.stock.model.RecommendLocaParamBO;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.djcps.wms.stock.model.SelectSavedStockAmountBO;
import com.djcps.wms.stock.server.StockServer;
import com.djcps.wms.stock.service.StockService;
import com.google.gson.Gson;

/**
 * 入库移库业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年12月20日
 */
@Service
public class StockServiceImpl implements StockService{

	@Autowired
	private StockServer stockServer;
	
	private Gson gson = new Gson();
	
	@Override
	public Map<String, Object> getRecommendLoca(RecommendLocaBO param) {
		StringBuilder bulider1 = new StringBuilder();
		List<RecommendLocaParamBO> addList = new ArrayList<RecommendLocaParamBO>();
		String location = param.getLocation();
		//location要求小数点显示后六位
		String newLocation = "";
		String[] split = location.split(",");
		for(int i =0;i<=split.length-1;i++){
			String str = split[i];
			int indexOf = str.indexOf(".");
			String substring = str.substring(indexOf+1);
			if(substring.length()>6){
				String str1 = str.substring(0,indexOf);
				String str2 = str.substring(indexOf+1,indexOf+7);
				String str3  = new StringBuilder().append(str1).append(".").append(str2).toString();
				if(i == 0){
					bulider1.append(str3);
				}else{
					bulider1.append(",").append(str3);
				}
			}else{
				if(i == 0){
					bulider1.append(split[i]);
				}else{
					bulider1.append(",").append(split[i]);
				}
			}
		}
		newLocation = bulider1.toString();
		//key表示高德地图api的需要的key,location表示经纬度,output输出格式
		String key=AppConstant.MAP_API_KEY;
		String output="JSON";
		MapLocationPO mapLocationPo = stockServer.getStreetCode(key,newLocation,output);
		RecommendLocaParamBO rl = new RecommendLocaParamBO();
		rl.setPartnerId(param.getPartnerId());
		rl.setStreetCode(mapLocationPo.getStreetCode());
		addList.add(rl);
		param.setParam(addList);
		HttpResult result = stockServer.getRecommendLoca(param);
		ArrayList data = (ArrayList) result.getData();
		result.setData(data.get(0));
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getOperationRecord(String string) {
		HttpResult result = stockServer.getOperationRecord(string);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> addStock(AddStockBO param) {
		HttpResult result = stockServer.addStock(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> moveStock(MoveStockBO param) {
		HttpResult result = stockServer.moveStock(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getSavedStockAmount(SelectSavedStockAmountBO param) {
		HttpResult result = stockServer.getSavedStockAmount(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAreaByOrderId(SelectAreaByOrderIdBO param) {
		HttpResult result = stockServer.getAreaByOrderId(param);
		return MsgTemplate.customMsg(result);
	}

}