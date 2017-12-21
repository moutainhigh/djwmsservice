package com.djcps.wms.warehouse.service.impl;

import java.util.Map;

import com.djcps.wms.commons.model.GetCodeBO;
import com.djcps.wms.commons.model.PartnerInfoBo;
import com.djcps.wms.warehouse.model.warehouse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.warehouse.server.WarehouseServer;
import com.djcps.wms.warehouse.service.WarehouseService;
import com.google.gson.Gson;

/**
 * @title:仓库管理业务层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月22日
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {
	
	private Gson gson = new Gson();
	
	@Autowired
	private WarehouseServer warehouseServer;



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
		return MsgTemplate.customMsg(result);
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
	public Map<String, Object> getAllList(BaseListParam baseListParam){
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
	public Map<String, Object> getAllWarehouseName(String partnerId) {
		HttpResult result = warehouseServer.getAllWarehouseName(partnerId);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getWarehouseCode(PartnerInfoBo partnerInfoBo) {
		HttpResult result=warehouseServer.getWarehouseCode(partnerInfoBo);
		return MsgTemplate.customMsg(result);
	}
}
