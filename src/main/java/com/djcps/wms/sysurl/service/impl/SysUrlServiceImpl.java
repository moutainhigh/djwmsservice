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
	public int batchInsertSysUrlDao(List<SysUrlPo> sysUrlList) {
		return sysUrlDao.batchInsertSysUrlDao(sysUrlList);
	}

	@Override
	public int updateSysUrlDao(SysUrlPo sysUrlList) {
		return sysUrlDao.updateSysUrlDao(sysUrlList);
	}

	@Override
	public List<SysUrlPo> getALLSysUrl() {
		return sysUrlDao.getALLSysUrl();
	}

	@Override
	public int batchReplaceSysUrlDao(List<SysUrlPo> sysUrlList) {
		return sysUrlDao.batchReplaceSysUrlDao(sysUrlList);
	}

	@Override
	public int batchUpdateSysUrlDao(List<SysUrlPo> sysUrlList) {
		return sysUrlDao.batchUpdateSysUrlDao(sysUrlList);
	}


}
