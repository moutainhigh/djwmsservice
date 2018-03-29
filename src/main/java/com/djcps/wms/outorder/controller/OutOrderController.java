package com.djcps.wms.outorder.controller;

import java.util.Map;

import javax.validation.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.outorder.model.OutOrderBO;
import com.djcps.wms.outorder.model.SelectOutOrderBO;
import com.djcps.wms.outorder.service.OutOrderService;
import com.google.gson.Gson;
/**
 * 
 * @author ldh
 *
 */
@RestController
@RequestMapping("/outOrder")
public class OutOrderController {
	@Autowired
	private OutOrderService outOrderService;
	
	private Gson gson = new Gson();
	
	private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(OutOrderController.class);
	
	@RequestMapping(name="获取订单明细列表",value="/gerOrderDetail",method = RequestMethod.POST)
	public Map<String,Object> getOrderDetailByOrderId(@RequestBody String json){
		try{
			LOGGER.debug("json:"+json);
			OutOrderBO out = gson.fromJson(json, OutOrderBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(out,new HibernateSupportedValidator<OutOrderBO>()
					.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return outOrderService.getOrderDetail(out);
		}catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
		
	}
	
	@RequestMapping(name="获取所有出库单列表",value="/listOutOrder",method=RequestMethod.POST)
	public Map<String, Object> getAllOutOrder(@RequestBody String json){
		try{
			LOGGER.debug("json:"+json);
			SelectOutOrderBO param = gson.fromJson(json, SelectOutOrderBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,new HibernateSupportedValidator<SelectOutOrderBO>()
					.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return outOrderService.getAllOutOrder(param);
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	@RequestMapping(name="根据出库单编号获取一条出库单信息",value="/getOutOrderById",method=RequestMethod.POST)
	public Map<String, Object> getOutOrderByOutOrderId(@RequestBody String json){
		try{
			LOGGER.debug("json:"+json);
			OutOrderBO param = gson.fromJson(json, OutOrderBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,new HibernateSupportedValidator<OutOrderBO>()
					.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return outOrderService.getOutOrderByOutOrderId(param);
		}catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	@RequestMapping(name="根据出库单编号更新出库单状态和打印次数",value="/updateOutOrderById",method=RequestMethod.POST)
	public Map<String, Object> updateOutOrderById(@RequestBody String json){
		try{
			LOGGER.debug("json:"+json);
			OutOrderBO param = gson.fromJson(json, OutOrderBO.class);
			ComplexResult ret = FluentValidator.checkAll().failFast().on(param,new HibernateSupportedValidator<OutOrderBO>()
					.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if(!ret.isSuccess()){
				return MsgTemplate.failureMsg(ret);
			}
			return outOrderService.updateOutOrderByOutOrderId(param);
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
}
