package com.djcps.wms.commons.listener;

import com.djcps.wms.commons.constant.RedisPrefixConstant;
import com.djcps.wms.commons.redis.RedisClient;
import com.djcps.wms.sysurl.model.SysUrlPO;
import com.djcps.wms.sysurl.service.SysUrlService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

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
	@Qualifier("redisClientCluster")
	RedisClient redisClient;
	
	private static final Logger logger = LoggerFactory.getLogger(RequestMappingListener.class);
	
	private Gson gson = new Gson();
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		String  rootName= "Root WebApplicationContext";
		String displayName = contextRefreshedEvent.getApplicationContext().getParent().getDisplayName();
		logger.info("------我的父容器为------:"+displayName);
		logger.info("------容器初始化开始------");
	    try {
	    	List<SysUrlPO> sysUrlList = new ArrayList<SysUrlPO>();
	    	List<SysUrlPO> insertList = new ArrayList<SysUrlPO>();
	    	Map<String,SysUrlPO> sysUrlMap = new HashMap<String,SysUrlPO>(16);
	    	
	    	if(rootName.equals(displayName)){
				Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();  
				for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
				    RequestMappingInfo info = m.getKey();
				    String name = info.getName();
				    PatternsRequestCondition p = info.getPatternsCondition();  
				    for (String url : p.getPatterns()) {
				    	//给属性赋值
				    	SysUrlPO sysUrl = new SysUrlPO();
				    	String string = UUID.randomUUID().toString();
				    	sysUrl.setId(string);
				    	sysUrl.setUrl(url);
				    	if(ObjectUtils.isEmpty(name)){
				    		//没有requestMapping("name=")属性,将属性设置为无接口名
				    		sysUrl.setName("无接口名");
				    	}else{
				    		sysUrl.setName(name);
				    	}
				    	sysUrlList.add(sysUrl);
				    }
				}
			}
			List<SysUrlPO> allSysUrl = sysUrlService.getALLSysUrl();
			if(!ObjectUtils.isEmpty(allSysUrl)){
				for (SysUrlPO sysUrlPo : allSysUrl) {
					sysUrlMap.put(sysUrlPo.getUrl(), sysUrlPo);
				}
			}else{
				//为空直接插入
				sysUrlService.batchInsertSysUrl(sysUrlList);
				return;
			}
			for (SysUrlPO sysUrlPo : sysUrlList) {
				SysUrlPO sysUrlPo2 = sysUrlMap.get(sysUrlPo.getUrl());
				if(sysUrlPo2==null){
					//为空表示新增的url
					insertList.add(sysUrlPo);
				}else{
					//不为空表示已存在需要更新
					sysUrlPo.setId(sysUrlPo2.getId());
					sysUrlService.updateSysUrl(sysUrlPo);
				}
			}
			
			if(!ObjectUtils.isEmpty(insertList)){
				sysUrlService.batchInsertSysUrl(insertList);
			}
			
			//再次查询数据库,将查询的数据存到redis中
			List<SysUrlPO> addAllSysUrl = sysUrlService.getALLSysUrl();
			if(!ObjectUtils.isEmpty(addAllSysUrl)){
				for (SysUrlPO sysUrlPo : addAllSysUrl) {
					redisClient.set(RedisPrefixConstant.REDIS_SYSTEM_URL_PREFIX+sysUrlPo.getUrl(),gson.toJson(sysUrlPo));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	    logger.info("------容器初始化结束------");
	}

}
