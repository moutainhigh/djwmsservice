package com.djcps.wms.loadingtable.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtable.model.AddLoadingTableBO;
import com.djcps.wms.loadingtable.model.DeleteLoadingTableBO;
import com.djcps.wms.loadingtable.model.IsUseLoadingTableBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByIdBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByAttributeBO;
import com.djcps.wms.loadingtable.model.UpdateLoadingTableBO;
import com.djcps.wms.loadingtable.server.LoadingTableServer;
import com.djcps.wms.provider.service.ProviderServiceImpl;
import com.google.gson.Gson;

import rpc.plugin.http.HTTPResponse;

/**
 * @title:装车台service层实现类
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
@Service
public class LoadingTableServiceImpl implements LoadingTableService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoadingTableServiceImpl.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private LoadingTableServer loadingTableServer;

	@Override
	public Map<String, Object> add(AddLoadingTableBO loadingTable) throws Exception {
		HttpResult result = loadingTableServer.add(loadingTable);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> modify(UpdateLoadingTableBO loadingTable) throws Exception {
		HttpResult result = loadingTableServer.modify(loadingTable);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> delete(DeleteLoadingTableBO loadingTable) throws Exception {
		HttpResult result = loadingTableServer.delete(loadingTable);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAllList(BaseListParam baseListParam) throws Exception {
		HttpResult result = loadingTableServer.getAllList(baseListParam);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getLoadingTableByAttribute(SelectLoadingTableByAttributeBO loadingTable) throws Exception{
		HttpResult result = loadingTableServer.getLoadingTableByAttribute(loadingTable);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getLoadingTableById(SelectLoadingTableByIdBO loadingTable) throws Exception{
		HttpResult result = loadingTableServer.getLoadingTableById(loadingTable);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> enable(IsUseLoadingTableBO loadingTable) throws Exception{
		HttpResult result = loadingTableServer.enable(loadingTable);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> disable(IsUseLoadingTableBO loadingTable) throws Exception{
		HttpResult result = loadingTableServer.disable(loadingTable);
		return MsgTemplate.customMsg(result);
	}
	
	/**
	 * @title:校验HttpResult结果并封装返回前端参数
	 * @description:
	 * @param http
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
//	private Map<String, Object> verifyHttpResult(HttpResult result){
//		Map<String, Object> map = new HashMap<String, Object>();
//		if(ObjectUtils.isEmpty(result)){
//			map.put("msg", SysMsgEnum.OPS_FAILURE.getMsg());
//			map.put("code", SysMsgEnum.OPS_FAILURE.getCode());
//			map.put("success", false);
//		}else{
//			map.put("msg", SysMsgEnum.OPS_SUCCESS.getMsg());
//			map.put("code", SysMsgEnum.OPS_SUCCESS.getCode());
//			map.put("success", true);
//			map.put("data", result);
//		}
//		return map;
//	}
}
