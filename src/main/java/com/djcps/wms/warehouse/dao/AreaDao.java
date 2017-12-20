package com.djcps.wms.warehouse.dao;


import java.util.List;
import org.springframework.stereotype.Repository;
import com.djcps.wms.warehouse.model.area.MapLocationPo;



/**
 * 数据库maplocationDAO
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月19日
 */
@Repository
public interface AreaDao {
	
	/**
	 * 经纬度插入
	 * @param mapLocationPo
	 * @return
	 * @author:zdx
	 * @date:2017年12月19日
	 */
	int insertLocation(MapLocationPo mapLocationPo);
	
	/**
	 * 获得所有的经纬度
	 * @return
	 * @author:zdx
	 * @date:2017年12月19日
	 */
	List<MapLocationPo> getAllLocation();
	
	/**
	 * 根据经纬度查询
	 * @param location
	 * @return
	 * @author:zdx
	 * @date:2017年12月19日
	 */
	MapLocationPo getLocationByLocation(String location);
}
