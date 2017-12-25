package com.djcps.wms.warehouse.service;

import java.util.Map;

import com.djcps.wms.commons.model.PartnerInfoBo;
import com.djcps.wms.warehouse.model.area.AddAreaBO;
import com.djcps.wms.warehouse.model.area.AreaCodeBO;
import com.djcps.wms.warehouse.model.area.DeleteAreaBO;
import com.djcps.wms.warehouse.model.area.SelectAllAreaListBO;
import com.djcps.wms.warehouse.model.area.UpdateAreaBO;
import com.djcps.wms.warehouse.model.warehouse.SelectWarehouseByIdBO;


/**
 * 仓库库区业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
public interface AreaService {
	
	/**
	 * 新增库区
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	Map<String, Object> addArea(AddAreaBO param);

	/**
	 * 修改库区
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	Map<String, Object> modifyArea(UpdateAreaBO param);

	/**
	 * 删除库区
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	Map<String, Object> deleteArea(DeleteAreaBO param);

	/**
	 * 获取所有库区
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	Map<String, Object> getAreaAllList(SelectAllAreaListBO param);

	/**
	 * 根据id获取库区
	 * @description:
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年12月7日
	 */
	Map<String, Object> getAreaById(SelectWarehouseByIdBO param);
	
	/**
	 * 推荐库位
	 * @param location
	 * @return
	 * @author:zdx
	 * @date:2017年12月18日
	 */
	Map<String, Object> getRecommendLoca(String location);

	/**
	 * 获取库区编码
	 * @author  wzy
	 * @param partnerInfoBo
	 * @param areaCode
	 * @return
	 * @create  2017/12/20 12:59
	 **/
	Map<String,Object> getAreaCode(PartnerInfoBo partnerInfoBo,AreaCodeBO areaCode);
}
