package com.djcps.wms.sysurl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.djcps.wms.sysurl.model.SysUrlPo;


/**
 * 系统url dao
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月13日
 */
@Repository
public interface SysUrlDao {
	/**
	 * 批量插入
	 * @param sysUrlList
	 * @return
	 * @author:zdx
	 * @date:2017年12月14日
	 */
	int batchInsertSysUrl(List<SysUrlPo> sysUrlList);
	
	/**
	 * 批量更新
	 * @param sysUrlList
	 * @return
	 * @author:zdx
	 * @date:2017年12月14日
	 */
	int batchUpdateSysUrl(List<SysUrlPo> sysUrlList);
	
	/**
	 * 单条更新 
	 * @param sysUrl
	 * @return
	 * @author:zdx
	 * @date:2017年12月19日
	 */
	int updateSysUrl(SysUrlPo sysUrl);
	
	/**
	 * 批量替换,有就更新,没有就新增,需要保证表有唯一索引
	 * @param sysUrlList
	 * @return
	 * @author:zdx
	 * @date:2017年12月14日
	 */
	int batchReplaceSysUrl(List<SysUrlPo> sysUrlList);
	
	/**
	 * 获取所有的url
	 * @description:
	 * @return
	 * @author:zdx
	 * @date:2017年12月14日
	 */
	List<SysUrlPo> getALLSysUrl();
}
