package com.djcps.wms.loadingtask.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.abnormal.model.OrderIdListBO;
import com.djcps.wms.abnormal.server.AbnormalServer;
import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtask.constant.LoadingTaskConstant;
import com.djcps.wms.loadingtask.enums.LoadingTaskEnum;
import com.djcps.wms.loadingtask.model.AddOrderApplicationListBO;
import com.djcps.wms.loadingtask.model.AdditionalOrderBO;
import com.djcps.wms.loadingtask.model.ConfirmBO;
import com.djcps.wms.loadingtask.model.CustomerBO;
import com.djcps.wms.loadingtask.model.FinishLoadingBO;
import com.djcps.wms.loadingtask.model.GetOrderByWayBillIdPO;
import com.djcps.wms.loadingtask.model.LoadingBO;
import com.djcps.wms.loadingtask.model.LoadingPersonBO;
import com.djcps.wms.loadingtask.model.LoadingPersonIdBO;
import com.djcps.wms.loadingtask.model.OrderInfoBO;
import com.djcps.wms.loadingtask.model.OutOrderInfoBO;
import com.djcps.wms.loadingtask.model.RejectRequestBO;
import com.djcps.wms.loadingtask.model.RemoveLoadingPersonBO;
import com.djcps.wms.loadingtask.model.result.AbnormalOrderPO;
import com.djcps.wms.loadingtask.model.result.ConfirmPO;
import com.djcps.wms.loadingtask.model.result.FinishLoadingPO;
import com.djcps.wms.loadingtask.model.result.OrderIdAndLoadingAmountPO;
import com.djcps.wms.loadingtask.model.result.OrderInfoPO;
import com.djcps.wms.loadingtask.model.result.OrderRedundantPO;
import com.djcps.wms.loadingtask.server.LoadingTaskServer;
import com.djcps.wms.loadingtask.service.LoadingTaskService;
import com.djcps.wms.order.model.ChildOrderBO;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderIdListBO;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.push.mq.producer.AppProducer;
import com.google.gson.JsonParser;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * 装车实现类
 *
 * @author  wyb
 * @since 2018/3/19
 */
@Service
public class LoadingTaskServiceImpl implements LoadingTaskService {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(LoadingTaskService.class);

    @Autowired
    private LoadingTaskServer loadingTaskServer;

    @Autowired
    private OrderServer orderServer;

    @Autowired
    private AbnormalServer abnormalServer;
    @Resource
    private AppProducer appProducer;

    /**
     * 获取装车员列表
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/20
     */
    @Override
    public Map<String, Object> loadingPersonList(LoadingPersonBO param) {
        HttpResult result = loadingTaskServer.loadingPersonList(param);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 移除装车员
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/20
     */
    @Override
    public Map<String, Object> removeLoadingPerson(RemoveLoadingPersonBO param) {
        param.setStatus(LoadingTaskConstant.LOADINGPERSON_TYPE_0);
        HttpResult result = loadingTaskServer.removeLoadingPerson(param);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 更新装车员状态并获取装车任务列表
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/20
     */
    @Override
    public Map<String, Object> confirm(ConfirmBO param) {

        List<LoadingPersonIdBO> list = param.getList();
        if (!ObjectUtils.isEmpty(list)) {
            for (LoadingPersonIdBO info : list) {
                if (!ObjectUtils.isEmpty(param.getLoadingTableId())) {
                    info.setLoadingTableId(param.getLoadingTableId());
                }
            }
        }
        // 获取订单列表和运单信息
        HttpResult result = loadingTaskServer.getOrderList(param);
        if (ObjectUtils.isEmpty(result.getData())) {
            return MsgTemplate.failureMsg(LoadingTaskEnum.NOT_TASK);
        }
        if (result.isSuccess()) {
            // 更新装车员状态
            loadingTaskServer.updateLoadPersonStatus(param);
            String data = JSONObject.toJSONString(result.getData());
            ConfirmPO confirmPO = gson.fromJson(data, ConfirmPO.class);
            // 获取订单编号及装车数量
            List<OrderIdAndLoadingAmountPO> orderPOList = confirmPO.getOrderPOList();
            List<String> childOrderIds = new ArrayList<String>();
            OrderIdsBO orderIdsBO = new OrderIdsBO();
            Map<String, String> map = new HashMap<>(16);
            if (!ObjectUtils.isEmpty(orderPOList)) {
                String orderId = null;
                for (OrderIdAndLoadingAmountPO orderInfo : orderPOList) {
                    if (!ObjectUtils.isEmpty(orderInfo.getOrderId())) {
	                    map.put(orderInfo.getOrderId(), orderInfo.getOrderId());
	                    childOrderIds.add(orderInfo.getOrderId());
                    }
                    orderIdsBO.setChildOrderIds(childOrderIds);
                    orderIdsBO.setPartnerArea(param.getPartnerArea());
                }
            }
            // 获取异常订单异常数目
            List<AbnormalOrderPO> abnormalInfo = abnormalOrderInfo(childOrderIds, param);
           
            List<OrderInfoPO> orderInfo = new ArrayList<>();
            List<OrderInfoPO> otherOrderInfo = new ArrayList<>();
            // 从订单服务获取订单信息
            BatchOrderDetailListPO batchOrderDetailList = orderServer.getOrderOrSplitOrder(orderIdsBO);
            List<WarehouseOrderDetailPO> orderList = batchOrderDetailList.getOrderList();
            if(!ObjectUtils.isEmpty(orderList)){
            	List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
            	for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
            		OrderInfoPO orderInfoPO = new OrderInfoPO();
            		BeanUtils.copyProperties(warehouseOrderDetailPO, orderInfoPO);
            		orderInfo.add(orderInfoPO);
				}
            }
            
            List<WarehouseOrderDetailPO> splitOrderList = batchOrderDetailList.getSplitOrderList();
            if(!ObjectUtils.isEmpty(splitOrderList)){
            	List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(splitOrderList);
            	for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
            		OrderInfoPO orderInfoPO = new OrderInfoPO();
            		BeanUtils.copyProperties(warehouseOrderDetailPO, orderInfoPO);
            		otherOrderInfo.add(orderInfoPO);
				}
            }
            List<OrderInfoPO> newOrderInfo = new ArrayList<>();
            if(!ObjectUtils.isEmpty(orderInfo)){
            	newOrderInfo.addAll(orderInfo);
            }
            
            if(!ObjectUtils.isEmpty(otherOrderInfo)){
            	newOrderInfo.addAll(otherOrderInfo);
            }
            if (!ObjectUtils.isEmpty(newOrderInfo)) {
                // 组合数据将对应订单的异常数量set进入对应订单信息里
                for (OrderInfoPO orderInformation : orderInfo) {
                    for (AbnormalOrderPO abnormalOrderPO : abnormalInfo) {
                        if (orderInformation.getOrderId().equals(abnormalOrderPO.getOrderId())) {
                            if (!abnormalOrderPO.getAbnomalAmount().equals(0)) {
                                orderInformation.setAbnomalAmount(abnormalOrderPO.getAbnomalAmount());
                            }
                        }
                    }
                }
                // 匹配对应订单将对应装车数量set进入对应订单信息
                orderInfo.stream().forEach(info -> {
                    List<OrderIdAndLoadingAmountPO> loadingAmount = orderPOList.stream()
                            .filter(amount -> info.getOrderId().equals(map.get(amount.getOrderId())))
                            .collect(Collectors.toList());
                    info.setLoadingAmount(loadingAmount.get(0).getLoadingAmount());
                });
                confirmPO.setOrderInfo(orderInfo);
            }
            return MsgTemplate.successMsg(confirmPO);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);

    }

    /**
     * 获取异常订单信息
     * 
     * @param childOrderIds
     * @param param
     * @return
     */
    public List<AbnormalOrderPO> abnormalOrderInfo(List<String> childOrderIds, ConfirmBO param) {
        OrderIdListBO orderIdListBO = new OrderIdListBO();
        orderIdListBO.setList(childOrderIds);
        orderIdListBO.setPartnerId(param.getPartnerId());
        HttpResult abnormalInfo = abnormalServer.getOrderByOrderIdList(orderIdListBO);
        List<AbnormalOrderPO> abnormalOrderInfoList = new ArrayList<>();
        if (abnormalInfo.isSuccess()) {
            String abnormalData = gson.toJson(abnormalInfo.getData());
            abnormalOrderInfoList = JSONArray.parseArray(abnormalData, AbnormalOrderPO.class);
        }
        return abnormalOrderInfoList;
    }

    /**
     * 装车
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/21
     */
    @Override
    public Map<String, Object> loading(LoadingBO param) {
        List<String> childOrderIds = new ArrayList<String>();
        childOrderIds.add(param.getOrderId());
        OrderIdsBO orderIdsBO = new OrderIdsBO();
        orderIdsBO.setChildOrderIds(childOrderIds);
        orderIdsBO.setPartnerArea(param.getPartnerArea());
        
        List<OrderInfoPO> orderInfo = new ArrayList<>();
        // 从订单服务获取订单信息
        BatchOrderDetailListPO batchOrderDetailList = orderServer.getOrderOrSplitOrder(orderIdsBO);
        List<WarehouseOrderDetailPO> orderList = batchOrderDetailList.getOrderList();
        if(!ObjectUtils.isEmpty(orderList)){
        	List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
        	for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
        		OrderInfoPO orderInfoPO = new OrderInfoPO();
        		BeanUtils.copyProperties(warehouseOrderDetailPO, orderInfoPO);
        		orderInfo.add(orderInfoPO);
			}
        }else{
        	List<WarehouseOrderDetailPO> splitOrderList = batchOrderDetailList.getSplitOrderList();
        	List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(splitOrderList);
        	for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
        		OrderInfoPO orderInfoPO = new OrderInfoPO();
        		BeanUtils.copyProperties(warehouseOrderDetailPO, orderInfoPO);
        		orderInfo.add(orderInfoPO);
			}
        }
        
        
        for (OrderInfoPO orderInfoPO : orderInfo) {
            if (!orderInfoPO.getOrderStatus().equals(LoadingTaskConstant.ORDERSTATUS_24)) {
                return MsgTemplate.failureMsg(LoadingTaskEnum.NOTLOADING);
            }
        }

        if (!ObjectUtils.isEmpty(param)) {
            Integer loadingAmount = param.getLoadingAmount();
            Integer orderAmount = param.getOrderAmount();
            if (!orderAmount.equals(loadingAmount)) {
                param.setOnceOrderid(
                        new StringBuffer(param.getOrderId()).append(LoadingTaskConstant.BREAK_UP_ORDER_1).toString());
                param.setTwiceOrderid(
                        new StringBuffer(param.getOrderId()).append(LoadingTaskConstant.BREAK_UP_ORDER_2).toString());
                if (!ObjectUtils.isEmpty(loadingAmount)) {
                    // 全部退库
                    if (loadingAmount == 0) {
                        param.setCancelStockAmount(orderAmount);
                        param.setCancelType(LoadingTaskConstant.CANCEL_TYPE_1);
                        HttpResult result = loadingTaskServer.loading(param);
                        // 更新订单状态
                        if (result.isSuccess()) {
                        	List<String> orderIdList = new ArrayList<>();
                        	orderIdList.add(param.getOrderId());
                        	
                        	List<OrderIdBO> orderIdBOList = new ArrayList<>();
                        	OrderIdBO order = new OrderIdBO();
                            order.setOrderId(param.getOrderId());
                            order.setStatus(LoadingTaskConstant.REDUNDANTSTATUS_24);
                            orderIdBOList.add(order);
                            
                        	HttpResult updateResult = orderServer.updateOrderOrSplitOrder(param.getPartnerArea(),orderIdBOList);
                        	if(!updateResult.isSuccess()){
                                LOGGER.error("装车,全部退库环节,修改订单状态失败!!!");
                                return MsgTemplate.customMsg(updateResult);
                            }
                            Boolean compareOrderStatus = orderServer.compareOrderStatus(orderIdList,  param.getPartnerArea());
                            if(compareOrderStatus==false){
                              return MsgTemplate.failureMsg("------拆单状态比子单状态小,需要修改子单状态,但是修改子订单状态失败!!!------");
                            }
                        } else {
                            return MsgTemplate.customMsg(result);
                        }
                    } else {
                        // 部分退库
                        param.setCancelStockAmount(orderAmount - loadingAmount);
                        param.setCancelType(LoadingTaskConstant.CANCEL_TYPE_2);
                        HttpResult result = loadingTaskServer.loading(param);
                        if (result.isSuccess()) {
                        	List<String> orderIdList = new ArrayList<>();
                        	orderIdList.add(param.getOnceOrderid());
                        	orderIdList.add(param.getTwiceOrderid());
                        	
                        	List<OrderIdBO> orderIdBOList = new ArrayList<>();
                        	//-1更新订单状态
                        	OrderIdBO firstOrder = new OrderIdBO();
                        	firstOrder.setOrderId(param.getOnceOrderid());
                        	firstOrder.setStatus(LoadingTaskConstant.REDUNDANTSTATUS_25);
            				orderIdBOList.add(firstOrder);
                        	//-2更新订单状态
                        	OrderIdBO secondOrder = new OrderIdBO();
                        	secondOrder.setOrderId(param.getTwiceOrderid());
                        	secondOrder.setStatus(LoadingTaskConstant.REDUNDANTSTATUS_24);
            				orderIdBOList.add(secondOrder);
                        	HttpResult updateResult = orderServer.updateOrderOrSplitOrder(param.getPartnerArea(),orderIdBOList);
                        	if(!updateResult.isSuccess()){
                                LOGGER.error("装车,部分退库,修改订单状态失败!!!");
                                return MsgTemplate.customMsg(updateResult);
                            }
                        	Boolean compareOrderStatus = orderServer.compareOrderStatus(orderIdList,  param.getPartnerArea());
                            if(compareOrderStatus==false){
                              return MsgTemplate.failureMsg("------拆单状态比子单状态小,需要修改子单状态,但是修改子订单状态失败!!!------");
                            }
                        } else {
                            return MsgTemplate.customMsg(result);
                        }
                    }
                }
            }
        }
        HttpResult result = loadingTaskServer.loading(param);
        if (result.isSuccess()) {
            List<OrderIdBO> orderIdBOList = new ArrayList<>();
            List<String> orderId = new ArrayList<>();
        	OrderIdBO firstOrder = new OrderIdBO();
        	firstOrder.setOrderId(param.getOrderId());
        	firstOrder.setStatus(LoadingTaskConstant.REDUNDANTSTATUS_25);
			orderIdBOList.add(firstOrder);
			orderId.add(param.getOrderId());
            HttpResult updateResult = orderServer.updateOrderOrSplitOrder(param.getPartnerArea(),orderIdBOList);
        	if(!updateResult.isSuccess()){
                LOGGER.error("装车,部分退库,修改订单状态失败!!!");
                return MsgTemplate.customMsg(updateResult);
            }
        	Boolean compareOrderStatus = orderServer.compareOrderStatus(orderId,param.getPartnerArea());
            if(compareOrderStatus==false){
              return MsgTemplate.failureMsg("------拆单状态比子单状态小,需要修改子单状态,但是修改子订单状态失败!!!------");
            }
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * 追加订单
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/21
     */
    @Override
    public Map<String, Object> additionalOrder(AdditionalOrderBO param) {
        // 传字符串帮助服务端判别更新申请时间为当前时间
        param.setApplicationTime(" ");
        param.setProposer(param.getOperator());
        param.setOperatorId(param.getOperatorId());
        HttpResult result = loadingTaskServer.additionalOrder(param);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 驳回申请
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/21
     */
    @Override
    public Map<String, Object> rejectRequest(RejectRequestBO param) {
        // 推送驳回消息
        pushMsg(param);
        param.setDisposeStatus(LoadingTaskConstant.DISPOSESTATUS_2);
        param.setHandler(param.getOperator());
        param.setHandlerId(param.getOperatorId());
        HttpResult result = loadingTaskServer.rejectRequest(param);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 推送驳回申请消息
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/21
     */
    public void pushMsg(RejectRequestBO param) {
        try {
            String json = gson.toJson(param);
            appProducer.sendPushMsg(json);
        } catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }

    }

    /**
     * 追加订单申请列表web
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/21
     */
    @Override
    public Map<String, Object> addOrderApplicationList(AddOrderApplicationListBO param) {
        HttpResult result = loadingTaskServer.addOrderApplicationList(param);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 完成装车
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/21
     */
    @Override
    public Map<String, Object> finishLoading(FinishLoadingBO param) {
        FinishLoadingPO result = loadingTaskServer.finishLoading(param);
        System.out.println(result.getLoadingTaskPO());
        System.out.println(param);
        if (ObjectUtils.isEmpty(result.getLoadingTaskPO())) {
            return MsgTemplate.failureMsg(LoadingTaskEnum.NOT_DEAL);
        }
        // 该订单数据状态应到订单自取
        List<OrderRedundantPO> orderPOList = result.getOrderPOList();
        if (!ObjectUtils.isEmpty(orderPOList)) {
            for (OrderRedundantPO info : orderPOList) {
                if (!LoadingTaskConstant.ORDERTSTATUS_25.equals(info.getStatus())) {
                    return MsgTemplate.failureMsg(LoadingTaskEnum.NO_DEAL_ORDER);
                }
            }
        }
        param.setStatus(LoadingTaskConstant.WAYBILLID_STATUS_20);
        HttpResult updateResult = loadingTaskServer.updateWayBill(param);

        if (updateResult.isSuccess()) {
            /**
             * 下面是生成出库单
             */
            // 先向出库单服务端根据运单号获取订单id、车辆id、车牌号等信息
            HttpResult outOrderResult = loadingTaskServer.getInfoByWayBillId(param);
            GetOrderByWayBillIdPO getOrderById = null;
            if (!ObjectUtils.isEmpty(outOrderResult.getData())) {
                getOrderById = gson.fromJson(gson.toJson(outOrderResult.getData()), GetOrderByWayBillIdPO.class);
            } else {
                return MsgTemplate.failureMsg(LoadingTaskEnum.WAYBILLID_ERROR);
            }
            // 获取订单id
            List<String> orders = getOrderById.getOrderId();
            OrderIdsBO orderParam = new OrderIdsBO();
            orderParam.setChildOrderIds(orders);
            orderParam.setPartnerArea(param.getPartnerArea());
            // 根据订单id从订单服务获取订单详情
            BatchOrderDetailListPO batchOrderDetailList = orderServer.getOrderOrSplitOrder(orderParam);
            List<WarehouseOrderDetailPO> orderList = batchOrderDetailList.getOrderList();
            List<ChildOrderBO> childOrderList = new ArrayList<>();
            if(!ObjectUtils.isEmpty(orderList)){
            	List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
            	for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
            		ChildOrderBO chlid = new ChildOrderBO();
            		BeanUtils.copyProperties(warehouseOrderDetailPO, chlid);
            		childOrderList.add(chlid);
				}
            }
            
            List<ChildOrderBO> otherChildOrderList = new ArrayList<>();
            List<WarehouseOrderDetailPO> splitOrderList = batchOrderDetailList.getSplitOrderList();
            if(!ObjectUtils.isEmpty(splitOrderList)){
            	List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(splitOrderList);
            	for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
            		ChildOrderBO chlid = new ChildOrderBO();
            		BeanUtils.copyProperties(warehouseOrderDetailPO, chlid);
            		otherChildOrderList.add(chlid);
				}
            }
            
            List<ChildOrderBO> newChildOrderList = new ArrayList<>();
            if(!ObjectUtils.isEmpty(childOrderList)){
            	newChildOrderList.addAll(childOrderList);
            }
            
            if(!ObjectUtils.isEmpty(otherChildOrderList)){
            	newChildOrderList.addAll(otherChildOrderList);
            }
            
            if(!ObjectUtils.isEmpty(newChildOrderList)){
            	 // 获取客户信息
                List<CustomerBO> customers = getCustomers(childOrderList);
                // 获取出库单信息
                List<OutOrderInfoBO> outOrderInfos = getOutOrderInfos(customers, param, getOrderById);
                HttpResult insertResult = loadingTaskServer.insertOutOrder(outOrderInfos);
                if (!insertResult.isSuccess()) {
                    return MsgTemplate.failureMsg(LoadingTaskEnum.OUTORDER_FAIL);
                }
                return MsgTemplate.customMsg(insertResult);
            }else{
            	return MsgTemplate.failureMsg(LoadingTaskEnum.OUTORDER_FAIL);
            }
            
        }
        return MsgTemplate.customMsg(updateResult);
    }

    /**
     * 生成出库单
     * 
     * @param customers
     *            客户详情
     * @param param
     *            运单对象
     * @param getOrderById
     *            获取车辆id，配货时间，订单号对象
     * @return
     */
    public List<OutOrderInfoBO> getOutOrderInfos(List<CustomerBO> customers, FinishLoadingBO param,
            GetOrderByWayBillIdPO getOrderById) {
        // 记录归并的订单id集合
        List<String> orderIds = new ArrayList<>();
        // 记录已经被归并过的订单id集合
        List<String> orderIdsUsed = new ArrayList<>();
        // 出库单数据
        List<OutOrderInfoBO> outOrderInfos = new ArrayList<OutOrderInfoBO>();
        for (int i = 0; i < customers.size(); i++) {
            CustomerBO customerBO = customers.get(i);
            if (orderIdsUsed.contains(customerBO.getOrderIds())) {
                continue;
            }
            orderIds.add(customerBO.getOrderIds());
            orderIdsUsed.add(customerBO.getOrderIds());
            if ((i + 1) != customers.size()) {
                for (int j = i + 1; j < customers.size(); j++) {
                    CustomerBO customerJ = customers.get(j);
                    boolean b1 = customerBO.getCustomerName().equals(customerJ.getCustomerName());
                    boolean b2 = customerBO.getContacts().equals(customerJ.getContacts());
                    boolean b3 = customerBO.getContactWay().equals(customerJ.getContactWay());
                    boolean b4 = customerBO.getAddress().equals(customerJ.getAddress());
                    if (b1 && b2 && b3 && b4) {
                        orderIds.add(customerJ.getOrderIds());
                        orderIdsUsed.add(customerJ.getOrderIds());
                    }
                }
            }
            OutOrderInfoBO outOrder = new OutOrderInfoBO();
            // 客户地址
            // 联系人
            // 联系方式
            // 客户名称
            BeanUtils.copyProperties(customerBO, outOrder);
            List<String> orderIdsAttr = new ArrayList<>();
            orderIdsAttr.addAll(orderIds);
            // 订单数组
            StringBuffer buffer = new StringBuffer();
            for (String str : orderIdsAttr) {
                buffer.append(str).append(",");
            }
            String str = buffer.toString().substring(0, buffer.toString().lastIndexOf(","));
            outOrder.setOrderIds(str);
            HttpResult numResult = loadingTaskServer.getNumber(1);
            // 出库单id
            outOrder.setId(numResult.getData().toString());
            outOrder.setOperatorId(param.getOperatorId());
            outOrder.setOperator(param.getOperator());
            outOrder.setPartnerArea(param.getPartnerArea());
            outOrder.setPartnerId(param.getPartnerId());
            outOrder.setPartnerName(param.getPartnerName());
            // 配货时间
            outOrder.setAllocationTime(getOrderById.getAllocationTime());
            // 先写死，到时候去TMS拿数据
            // 司机id
            outOrder.setDriverId("111122233");
            // 司机名称
            outOrder.setDriverName("刘德煌");
            // 车牌号
            outOrder.setPlateNumber(getOrderById.getCarId());
            outOrderInfos.add(outOrder);
            orderIds.clear();
        }
        return outOrderInfos;
    }

    /**
     * 获取客户信息
     * 
     * @param childOrderList
     * @return
     */
    public List<CustomerBO> getCustomers(List<ChildOrderBO> childOrderList) {
    	List<CustomerBO> customers = new ArrayList<>();
    	for (ChildOrderBO childOrderBO : childOrderList) {
        	CustomerBO customerBO = new CustomerBO();
        	BeanUtils.copyProperties(childOrderBO, customerBO);
        	customerBO.setOrderIds(childOrderBO.getOrderId());
        	customerBO.setContacts(childOrderBO.getConsignee());
        	customerBO.setAddress(childOrderBO.getAddressDetailProvince());
        	customers.add(customerBO);
		}
        return customers;
    }

    @Override
    public Map<String, Object> getLoadingTableIdByUserId(PartnerInfoBO partnerInfoBO) {
        // 伪代码
        HttpResult result = loadingTaskServer.getLoadingTableIdByUserId(partnerInfoBO);
        return MsgTemplate.customMsg(result);
    }

}
