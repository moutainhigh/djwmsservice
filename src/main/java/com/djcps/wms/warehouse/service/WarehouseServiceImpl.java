package com.djcps.wms.warehouse.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.warehouse.model.AddWarehouseBO;
import com.djcps.wms.warehouse.model.DeleteWarehouseBO;
import com.djcps.wms.warehouse.model.IsUseWarehouseBO;
import com.djcps.wms.warehouse.model.SelectWarehouseByIdBO;
import com.djcps.wms.warehouse.model.SelectWarehouseByAttributeBO;
import com.djcps.wms.warehouse.model.UpdateWarehouseBO;
import com.djcps.wms.warehouse.server.WarehouseServer;
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
	public Map<String, Object> add(AddWarehouseBO addBean) throws Exception {
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
	public Map<String, Object> modify(UpdateWarehouseBO updateBean) throws Exception {
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
	public Map<String, Object> delete(DeleteWarehouseBO deleteBean) throws Exception {
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
	public Map<String, Object> getAllList(BaseListParam baseListParam) throws Exception {
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
	public Map<String, Object> getWarehouseById(SelectWarehouseByIdBO selectByIdBean) throws Exception {
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
	public Map<String, Object> getWarehouseByAttribute(SelectWarehouseByAttributeBO selectVagueBean) throws Exception {
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
	public Map<String, Object> enable(IsUseWarehouseBO isUseBean) throws Exception {
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
	public Map<String, Object> disable(IsUseWarehouseBO isUseBean) throws Exception {
		HttpResult result = warehouseServer.disable(isUseBean);
		return MsgTemplate.customMsg(result);
	}

}
