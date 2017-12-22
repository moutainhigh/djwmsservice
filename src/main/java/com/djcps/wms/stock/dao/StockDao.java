package com.djcps.wms.stock.dao;


import java.util.List;
import org.springframework.stereotype.Repository;

import com.djcps.wms.stock.model.MapLocationPo;



/**
 * 入库移库dao
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月19日
 */
@Repository
public interface StockDao {
	
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
