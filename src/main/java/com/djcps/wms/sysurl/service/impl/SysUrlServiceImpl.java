package com.djcps.wms.sysurl.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.sysurl.dao.SysUrlDao;
import com.djcps.wms.sysurl.model.SysUrlPo;
import com.djcps.wms.sysurl.service.SysUrlService;
import com.google.gson.Gson;


/**
 * 系统url业务层实现类
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月13日
 */
@Service
public class SysUrlServiceImpl implements SysUrlService {
	
	private static final Logger logger = LoggerFactory.getLogger(SysUrlServiceImpl.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private SysUrlDao sysUrlDao;
	
	@Override
	public int batchInsertSysUrl(List<SysUrlPo> sysUrlList) {
		return sysUrlDao.batchInsertSysUrl(sysUrlList);
	}

	@Override
	public int updateSysUrl(SysUrlPo sysUrlList) {
		return sysUrlDao.updateSysUrl(sysUrlList);
	}

	@Override
	public List<SysUrlPo> getALLSysUrl() {
		return sysUrlDao.getALLSysUrl();
	}

	@Override
	public int batchReplaceSysUrl(List<SysUrlPo> sysUrlList) {
		return sysUrlDao.batchReplaceSysUrl(sysUrlList);
	}

	@Override
	public int batchUpdateSysUrl(List<SysUrlPo> sysUrlList) {
		return sysUrlDao.batchUpdateSysUrl(sysUrlList);
	}


}
