package com.djcps.wms.allocation.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.wms.allocation.model.AddAllocation;
import com.djcps.wms.allocation.service.AllocationService;
import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.base.BaseParam;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.fluentvalidator.ValidateNotNullInteger;
import com.djcps.wms.commons.model.PartnerInfoBean;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtable.enums.LoadingTableMsgEnum;
import com.djcps.wms.loadingtable.model.AddLoadingTableBO;
import com.djcps.wms.loadingtable.model.DeleteLoadingTableBO;
import com.djcps.wms.loadingtable.model.IsUseLoadingTableBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByIdBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByAttributeBO;
import com.djcps.wms.loadingtable.model.UpdateLoadingTableBO;
import com.djcps.wms.loadingtable.service.LoadingTableService;
import com.google.gson.Gson;

/**
 * @title:配货台控制层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月8日
 */
@RestController
@RequestMapping(value = "/allocation")
public class AllocationController {
	
	private static final Logger logger = LoggerFactory.getLogger(AllocationController.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	private AllocationService allocationService;
	
	/**
	 * @title:获取所有混合配货列表
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月23日
	 */
	@RequestMapping(name="获取所有混合配货列表",value = "/getOrderType", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getOrderType(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			BaseParam baseParam = new BaseParam();
			return allocationService.getOrderType(baseParam);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 获取已选择的混合配货列表
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	@RequestMapping(name="获取已选择的混合配货列表",value = "/getChooseAllocation", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getChooseAllocation(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			PartnerInfoBean  partnern = new PartnerInfoBean();
			return allocationService.getChooseAllocation(partnern);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 混合配货保存接口(新增和修改)
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年12月13日
	 */
	@RequestMapping(name="混合配货保存接口(新增和修改)",value = "/saveAllocation", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> saveAllocation(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			AddAllocation allocation = gson.fromJson(json, AddAllocation.class);
			PartnerInfoBean partnerInfoBean = new PartnerInfoBean();
			BeanUtils.copyProperties(partnerInfoBean,allocation);
			logger.debug("loadingTable : " + allocation.toString());
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(allocation,
							new HibernateSupportedValidator<AddAllocation>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.saveAllocation(allocation);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
}
