package com.djcps.wms.warehouse.service;

import java.util.Map;

import com.djcps.wms.commons.model.PartnerInfoBo;
import com.djcps.wms.warehouse.model.location.*;


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

	/**
	 * 获取库位编码
	 * @param getLocationCode
	 * @return
	 */
	Map<String,Object> getLocationCode(PartnerInfoBo partnerInfoBo,LocationBo locationBo);
}
