package com.djcps.wms.stock.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.fluentvalidator.ValidateNullInteger;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtable.enums.LoadingTableMsgEnum;
import com.djcps.wms.stock.model.AddStockBO;
import com.djcps.wms.stock.model.MoveStockBO;
import com.djcps.wms.stock.model.OperationRecordBO;
import com.djcps.wms.stock.model.RecommendLocaBO;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.djcps.wms.stock.model.SelectSavedStockAmountBO;
import com.djcps.wms.stock.service.StockService;
import com.google.gson.Gson;
import com.google.gson.JsonParser;


/**
 *  入库移库控制层
 * @company:djwms
 * @author:zdx
 * @date:2017年12月20日
 */
@RestController
@RequestMapping(value = "/stock")
public class StockController {
	
	private static final Logger logger = LoggerFactory.getLogger(StockController.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	StockService stockService;
	
	/**
	 * 推荐库区接口
	 * @description:
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年12月18日
	 */
	@RequestMapping(name="推荐库区",value = "/getRecommendLoca",method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getRecommendLoca(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			RecommendLocaBO param = gson.fromJson(json, RecommendLocaBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<RecommendLocaBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return stockService.getRecommendLoca(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	@RequestMapping(name="获取操作记录接口",value = "/getOperationRecord",method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getOperationRecord(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			String string = new JsonParser().parse(json).getAsJsonObject().get("orderId").toString();
			if(ObjectUtils.isEmpty(string)){
				return MsgTemplate.failureMsg(SysMsgEnum.PARAM_ERROR);
			}
			return stockService.getOperationRecord(json);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	@RequestMapping(name="入库",value = "/addStock",method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> addStock(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			AddStockBO param = gson.fromJson(json, AddStockBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<AddStockBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					//备注50个字
					.on(param.getRemark(),new ValidateNullInteger(LoadingTableMsgEnum.LENGTH_BEYOND,50))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return stockService.addStock(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	@RequestMapping(name="移库",value = "/moveStock",method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> moveStock(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			MoveStockBO param = gson.fromJson(json, MoveStockBO.class);
			OperationRecordBO operationRecord = param.getOperationRecord();
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			BeanUtils.copyProperties(partnerInfoBean,operationRecord);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<MoveStockBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return stockService.moveStock(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	@RequestMapping(name="获取订单已入库数量",value = "/getSavedStockAmount",method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getSavedStockAmount(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			SelectSavedStockAmountBO param = gson.fromJson(json, SelectSavedStockAmountBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<SelectSavedStockAmountBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return stockService.getSavedStockAmount(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	@RequestMapping(name="根据订单获取库位信息",value = "/getAreaByOrderId",method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAreaByOrderId(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			logger.debug("json : " + json);
			//参数就需要订单号和版本号,用这个参数也一样
			SelectAreaByOrderIdBO param = gson.fromJson(json, SelectAreaByOrderIdBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<SelectAreaByOrderIdBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return stockService.getAreaByOrderId(param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
}
