package com.djcps.wms.allocation.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.commons.base.BaseBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
import com.djcps.wms.allocation.model.AddAllocationBO;
import com.djcps.wms.allocation.model.AddAllocationOrderBO;
import com.djcps.wms.allocation.model.CancelAllocationBO;
import com.djcps.wms.allocation.model.CarBO;
import com.djcps.wms.allocation.model.ChangeCarInfoBO;
import com.djcps.wms.allocation.model.GetDeliveryByWaybillIdsBO;
import com.djcps.wms.allocation.model.GetExcellentLodingBO;
import com.djcps.wms.allocation.model.GetIntelligentAllocaBO;
import com.djcps.wms.allocation.model.GetRedundantByAttributeBO;
import com.djcps.wms.allocation.model.MergeModelBO;
import com.djcps.wms.allocation.model.MoveOrderPO;
import com.djcps.wms.allocation.model.RelativeIdBO;
import com.djcps.wms.allocation.model.VerifyAllocationBO;
import com.djcps.wms.allocation.service.AllocationService;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateOrderBO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
	
	private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(AllocationController.class);
	
	private Gson gson = GsonUtils.gson;
	
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
			LOGGER.debug("json : " + json);
			BaseBO baseBO = new BaseBO();
			return allocationService.getOrderType(baseBO);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
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
			LOGGER.debug("json : " + json);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			return allocationService.getChooseAllocation(partnerInfoBean);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
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
			LOGGER.debug("json : " + json);
			AddAllocationBO allocation = gson.fromJson(json, AddAllocationBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,allocation);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(allocation,
							new HibernateSupportedValidator<AddAllocationBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.saveAllocation(allocation);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 获取配货结果
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@RequestMapping(name="获取配货结果",value = "/getAllocationResultList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAllocationResultList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			GetRedundantByAttributeBO param = gson.fromJson(json, GetRedundantByAttributeBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<GetRedundantByAttributeBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.getAllocationResultList(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 智能配货结果
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@RequestMapping(name="智能配货结果",value = "/getIntelligentAllocaList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getIntelligentAllocaList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			GetIntelligentAllocaBO param = gson.fromJson(json, GetIntelligentAllocaBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<GetIntelligentAllocaBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.getIntelligentAllocaList(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 假智能配货接口
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping(name="智能配货结果",value = "/addzhinengpeihuo", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> addzhinengpeihuo(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			BaseAddBO param = new BaseAddBO();
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			return allocationService.addzhinengpeihuo(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 确认配货
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@RequestMapping(name="确认配货",value = "/verifyAllocation", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> verifyAllocation(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			VerifyAllocationBO param = gson.fromJson(json, VerifyAllocationBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<VerifyAllocationBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.verifyAllocation(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 移除订单
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@RequestMapping(name="移除订单",value = "/moveOrder", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> moveOrder(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			MoveOrderPO param = gson.fromJson(json, MoveOrderPO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<MoveOrderPO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.moveOrder(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 追加订单
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@RequestMapping(name="追加订单列表",value = "/getAddOrderList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAddOrderList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			GetRedundantByAttributeBO param = gson.fromJson(json, GetRedundantByAttributeBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<GetRedundantByAttributeBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.getAddOrderList(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 智能配货确认追加订单
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@RequestMapping(name="智能配货确认追加订单",value = "/verifyAddOrder", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> verifyAddOrder(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			List<AddAllocationOrderBO> param = gson.fromJson(json, new TypeToken<ArrayList<AddAllocationOrderBO>>(){}.getType());
			long start = System.currentTimeMillis();
			System.err.println(System.currentTimeMillis());
			for (AddAllocationOrderBO addAllocationOrderBO : param) {
				PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
				BeanUtils.copyProperties(partnerInfoBean,param);
				//数据校验
				ComplexResult ret = FluentValidator.checkAll().failFast()
						.on(addAllocationOrderBO,
								new HibernateSupportedValidator<AddAllocationOrderBO>()
								.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
						.doValidate().result(ResultCollectors.toComplex());
				if (!ret.isSuccess()) {
					return MsgTemplate.failureMsg(ret);
				}
			}
			return allocationService.verifyAddOrder(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 装车优化确认追加订单
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping(name="装车优化确认追加订单",value = "/againVerifyAddOrder", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> againVerifyAddOrder(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			List<WarehouseOrderDetailPO> cacheList = gson.fromJson(json, new TypeToken<ArrayList<WarehouseOrderDetailPO>>(){}.getType());
			return allocationService.againVerifyAddOrder(cacheList);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 配货管理查询界面
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	@RequestMapping(name="配货管理查询",value = "/getAllocationManageList", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAllocationManageList(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			GetRedundantByAttributeBO param = gson.fromJson(json, GetRedundantByAttributeBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<GetRedundantByAttributeBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.getAllocationManageList(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 获取运单明细
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@RequestMapping(name="获取运单明细",value = "/getWaybillDetailByWayId", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getWaybillDetailByWayId(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			GetDeliveryByWaybillIdsBO  param = gson.fromJson(json, GetDeliveryByWaybillIdsBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<GetDeliveryByWaybillIdsBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.getWaybillDetailByWayId(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 装车优化
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@RequestMapping(name="装车优化",value = "/getExcellentLoding", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getExcellentLoding(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			GetExcellentLodingBO param = gson.fromJson(json, GetExcellentLodingBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<GetExcellentLodingBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.getExcellentLoding(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 装车优化确认追加订单
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	/*@RequestMapping(name="装车优化确认追加订单",value = "/againVerifyAddOrder", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> againVerifyAddOrder(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			AgainVerifyAddOrderBO param = gson.fromJson(json, AgainVerifyAddOrderBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<AgainVerifyAddOrderBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.againVerifyAddOrder(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
*/
	/**
	 * 装车优化再次确认配货
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	
	@RequestMapping(name="装车优化再次确认配货",value = "/againVerifyAllocation", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> againVerifyAllocation(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			MergeModelBO param = gson.fromJson(json, MergeModelBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<MergeModelBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.againVerifyAllocation(param,partnerInfoBean);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/*@RequestMapping(name="装车优化再次确认配货",value = "/againVerifyAllocation", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> againVerifyAllocation(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			List<AgainVerifyAllocationBO> param = gson.fromJson(json, new TypeToken<ArrayList<AgainVerifyAllocationBO>>(){}.getType());
			for (AgainVerifyAllocationBO againVerifyAllocationBO : param) {
				PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
				BeanUtils.copyProperties(partnerInfoBean,againVerifyAllocationBO);
				ComplexResult ret = FluentValidator.checkAll().failFast()
						.on(againVerifyAllocationBO,
								new HibernateSupportedValidator<AgainVerifyAllocationBO>()
								.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
						.doValidate().result(ResultCollectors.toComplex());
				if (!ret.isSuccess()) {
					return MsgTemplate.failureMsg(ret);
				}
			}
			return allocationService.againVerifyAllocation(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}*/
	
	/**
	 * 获取所有可用车辆的信息
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@RequestMapping(name="获取所有可用车辆的信息",value = "/getAllUserCarInfo", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getAllUserCarInfo(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
//			LOGGER.debug("json : " + json);
//			AgainVerifyAllocationBO param = gson.fromJson(json, AgainVerifyAllocationBO.class);
//			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
//			BeanUtils.copyProperties(partnerInfoBean,param);
//			//数据校验
//			ComplexResult ret = FluentValidator.checkAll().failFast()
//					.on(param,
//							new HibernateSupportedValidator<AgainVerifyAllocationBO>()
//							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
//					.doValidate().result(ResultCollectors.toComplex());
//			if (!ret.isSuccess()) {
//				return MsgTemplate.failureMsg(ret);
//			}
			return allocationService.getAllUserCarInfo();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 根据车辆id获取车辆详情
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@RequestMapping(name="根据车辆id获取车辆详情",value = "/getCarDetailById", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getCarDetailById(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			CarBO param = gson.fromJson(json, CarBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<CarBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.getCarDetailById();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 车辆确认更换
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@RequestMapping(name="车辆确认更换",value = "/changeCarInfo", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> changeCarInfo(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			String id = "";
			ChangeCarInfoBO param = gson.fromJson(json, ChangeCarInfoBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<ChangeCarInfoBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.changeCarInfo(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 取消配货
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@RequestMapping(name="取消配货",value = "/cancelAllocation", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> cancelAllocation(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			CancelAllocationBO param = gson.fromJson(json, CancelAllocationBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<CancelAllocationBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.cancelAllocation(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 智能配货取消配货(清楚缓存)
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping(name="智能配货取消配货(清楚缓存)",value = "/intelligentCancelAllocation", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> intelligentCancelAllocation(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			String parameter = request.getParameter("allocationId");
			if (StringUtils.isEmpty(parameter)) {
				return MsgTemplate.failureMsg(SysMsgEnum.PARAM_ERROR);
			}
			return allocationService.intelligentCancelAllocation(parameter);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 装车优化取界面消配货
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	@RequestMapping(name="装车优化取界面消配货",value = "/againCancelAllocation", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> againCancelAllocation(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			String parameter = request.getParameter("waybillId");
			if(StringUtils.isEmpty(parameter)){
				return MsgTemplate.failureMsg(SysMsgEnum.PARAM_ERROR);
			}
//			CancelAllocationBO param = gson.fromJson(json, CancelAllocationBO.class);
//			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
//			BeanUtils.copyProperties(partnerInfoBean,param);
//			//数据校验
//			ComplexResult ret = FluentValidator.checkAll().failFast()
//					.on(param,
//							new HibernateSupportedValidator<CancelAllocationBO>()
//							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
//					.doValidate().result(ResultCollectors.toComplex());
//			if (!ret.isSuccess()) {
//				return MsgTemplate.failureMsg(ret);
//			}
			return allocationService.againCancelAllocation(parameter);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	@RequestMapping(name="获取提货员信息",value = "/getPicker", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getPicker(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			BaseAddBO param = new BaseAddBO();
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
//			CancelAllocationBO param = gson.fromJson(json, CancelAllocationBO.class);
//			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
//			BeanUtils.copyProperties(partnerInfoBean,param);
//			//数据校验
//			ComplexResult ret = FluentValidator.checkAll().failFast()
//					.on(param,
//							new HibernateSupportedValidator<CancelAllocationBO>()
//							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
//					.doValidate().result(ResultCollectors.toComplex());
//			if (!ret.isSuccess()) {
//				return MsgTemplate.failureMsg(ret);
//			}
			return allocationService.getPicker(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	@RequestMapping(name="获取装车员信息",value = "/getLoadingPerson", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getLoadingPerson(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			return allocationService.getLoadingPerson();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 根据关联id获取操作记录信息
	 * @param json
	 * @param request
	 * @return
	 */
	@RequestMapping(name="根据关联id获取操作记录信息",value = "/getRecordByRrelativeId", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getRecordByRrelativeId(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			RelativeIdBO param = gson.fromJson(json, RelativeIdBO.class);
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			//数据校验
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<RelativeIdBO>()
							.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.getRecordByRrelativeId(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
	 * 拆分订单
	 * @param json
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2018年4月18日
	 */
	@RequestMapping(name="拆分订单接口",value = "/splitOrder", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> splitOrder(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			UpdateOrderBO param = gson.fromJson(json, UpdateOrderBO.class);
			//组织查询需要的参数
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<UpdateOrderBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.splitOrder(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	@RequestMapping(name="根据订单号获取拆单信息",value = "/getSplitOrderByOrderId", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getSplitOrderByOrderId(@RequestBody(required = false) String json, HttpServletRequest request) {
		try {
			LOGGER.debug("json : " + json);
			OrderIdBO param = gson.fromJson(json, OrderIdBO.class);
			//组织查询需要的参数
			PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
			BeanUtils.copyProperties(partnerInfoBean,param);
			ComplexResult ret = FluentValidator.checkAll().failFast()
					.on(param,
							new HibernateSupportedValidator<OrderIdBO>()
									.setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
					.doValidate().result(ResultCollectors.toComplex());
			if (!ret.isSuccess()) {
				return MsgTemplate.failureMsg(ret);
			}
			return allocationService.getSplitOrderByOrderId(param);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
		}
	}
	
	/**
     * 获取车辆排队信息（test 对接TMS）
     * @param json
     * @param request
     * @return
     */
    /*@RequestMapping(name="获取车辆排队信息（test 对接TMS）",value = "/TmsVehicleQueuingList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> TmsVehicleQueuingList(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            return allocationService.TmsVehicleQueuingList();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }*/
}
