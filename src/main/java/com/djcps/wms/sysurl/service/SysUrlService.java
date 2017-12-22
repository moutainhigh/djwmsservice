package com.djcps.wms.sysurl.service;

import java.util.List;
import com.djcps.wms.sysurl.model.SysUrlPo;

/**
 * 系统url业务层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月13日
 */
/**
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月14日
 */
public interface SysUrlService {
	
	
	/**
	 * 批量插入
	 * @param sysUrlList
	 * @return
	 * @author:zdx
	 * @date:2017年12月14日
	 */
	int batchInsertSysUrl(List<SysUrlPo> sysUrlList);
	
	
	/**
	 * 单条更新
	 * @description:
	 * @param sysUrlList
	 * @return
	 * @author:zdx
	 * @date:2017年12月19日
	 */
	int updateSysUrl(SysUrlPo sysUrlList);
	
	/**
	 * 批量更新 
	 * @param sysUrlList
	 * @return
	 * @author:zdx
	 * @date:2017年12月14日
	 */
	int batchUpdateSysUrl(List<SysUrlPo> sysUrlList);
	
	/**
	 * 获取所有的url
	 * @return
	 * @author:zdx
	 * @date:2017年12月14日
	 */
	List<SysUrlPo> getALLSysUrl();
	
	/**
	 * 批量替换,有就更新,没有就新增,需要保证表有唯一索引
	 * @param sysUrlList
	 * @return
	 * @author:zdx
	 * @date:2017年12月14日
	 */
	int batchReplaceSysUrl(List<SysUrlPo> sysUrlList);
}
