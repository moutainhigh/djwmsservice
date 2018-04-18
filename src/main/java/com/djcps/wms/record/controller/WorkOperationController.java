package com.djcps.wms.record.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.record.model.WorfRecordListBO;
import com.google.gson.Gson;

/**
* @author panyang
* @version 创建时间：2018年4月17日 上午11:01:32
* 类说明
*/

@RestController
@RequestMapping("/workOperationRecord")
public class WorkOperationController {
	
	private static final Logger LOGGER=org.slf4j.LoggerFactory.getLogger(WorkOperationController.class);
	
	private Gson gson=new Gson();
	
	public Map<String, Object>  workRecordList(@RequestBody(required=false) String json ,HttpServletRequest request){
		
        PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
        WorfRecordListBO worfRecordListBO=gson.fromJson(json,WorfRecordListBO.class );
        BeanUtils.copyProperties(partnerInfoBo, worfRecordListBO);
     
        
		
		
	}
	
	

}
