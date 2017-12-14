package com.djcps.wms.commons.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.djcps.wms.commons.base.RedisClientCluster;
import com.djcps.wms.commons.contant.RedisPrefixContant;
import com.djcps.wms.sysurl.model.SysUrlPo;
import com.djcps.wms.sysurl.service.SysUrlService;
import com.djcps.wms.warehouse.controller.WarehouseController;
import com.google.gson.Gson;

/**
 * 扫描并存入数据库url监听器
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月14日
 */
@Component
public class RequestMappingListener implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired  
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	@Autowired 
	private SysUrlService sysUrlService;
	
	@Autowired
	RedisClientCluster redisClientCluster;
	
	private static final Logger logger = LoggerFactory.getLogger(RequestMappingListener.class);
	
	private Gson gson = new Gson();
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		String  rootName= "Root WebApplicationContext";
		String displayName = contextRefreshedEvent.getApplicationContext().getParent().getDisplayName();
		logger.error("------我的父容器为------:"+displayName);
		logger.error("------容器初始化开始------");
	    try {
			if(rootName.equals(displayName)){
				List<SysUrlPo> sysUrlList = new ArrayList<SysUrlPo>();
				Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();  
				for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
				    RequestMappingInfo info = m.getKey();
				    String name = info.getName();
				    PatternsRequestCondition p = info.getPatternsCondition();  
				    for (String url : p.getPatterns()) {
				    	SysUrlPo sysUrl = new SysUrlPo();
				    	String string = UUID.randomUUID().toString();
				    	sysUrl.setId(string);
				    	sysUrl.setUrl(url);
				    	if(ObjectUtils.isEmpty(name)){
				    		sysUrl.setName("无接口名");
				    	}else{
				    		sysUrl.setName(name);
				    	}
				    	sysUrlList.add(sysUrl);
				    	
				    }
				}
				sysUrlService.batchReplaceSysUrlDao(sysUrlList);
			}
			List<SysUrlPo> allSysUrl = sysUrlService.getALLSysUrl();
			if(!ObjectUtils.isEmpty(allSysUrl)){
				for (SysUrlPo sysUrlPo : allSysUrl) {
					redisClientCluster.set(RedisPrefixContant.REDIS_SYSTEM_URL_PREFIX+sysUrlPo.getUrl(),gson.toJson(sysUrlPo));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	    logger.error("------容器初始化结束------");
	}

}
