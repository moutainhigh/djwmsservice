package com.djcps.wms.warehouse.service;

import java.util.Map;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.warehouse.model.location.AddLocationBO;
import com.djcps.wms.warehouse.model.location.DeleteLocationBO;
import com.djcps.wms.warehouse.model.location.SelectAllLocationList;
import com.djcps.wms.warehouse.model.location.SelectLocationByAttributeBO;
import com.djcps.wms.warehouse.model.location.UpdateLocationBO;
import com.djcps.wms.warehouse.model.warehouse.AddWarehouseBO;
import com.djcps.wms.warehouse.model.warehouse.DeleteWarehouseBO;
import com.djcps.wms.warehouse.model.warehouse.IsUseWarehouseBO;
import com.djcps.wms.warehouse.model.warehouse.SelectWarehouseByAttributeBO;
import com.djcps.wms.warehouse.model.warehouse.SelectWarehouseByIdBO;
import com.djcps.wms.warehouse.model.warehouse.UpdateWarehouseBO;


/**
 * 库位业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
public interface LocationService {
	
	/**
	 * 新增库位
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	Map<String, Object> addLocation(AddLocationBO param);

	/**
	 * 修改库位
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	Map<String, Object> modifyLocation(UpdateLocationBO param);

	/**
	 * 删除库位
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	Map<String, Object> deleteLocation(DeleteLocationBO param);

	/**
	 * 获取所有库位
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	Map<String, Object> getLocationAllList(SelectAllLocationList param);

	/**
	 * 根据库位属性模糊查询
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	Map<String, Object> getLocationByAttribute(SelectLocationByAttributeBO param);
}
