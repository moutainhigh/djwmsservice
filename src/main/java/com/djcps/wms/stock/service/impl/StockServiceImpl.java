package com.djcps.wms.stock.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.abnormal.constant.AbnormalConstant;
import com.djcps.wms.abnormal.enums.AbnormalMsgEnum;
import com.djcps.wms.abnormal.model.AbnormalOrderPO;
import com.djcps.wms.abnormal.model.AddAbnormal;
import com.djcps.wms.abnormal.model.OrderIdListBO;
import com.djcps.wms.abnormal.model.UpdateAbnormalBO;
import com.djcps.wms.abnormal.server.AbnormalServer;
import com.djcps.wms.allocation.constant.AllocationConstant;
import com.djcps.wms.allocation.enums.AllocationMsgEnum;
import com.djcps.wms.allocation.model.UpdateOrderRedundantBO;
import com.djcps.wms.allocation.server.AllocationServer;
import com.djcps.wms.cancelstock.enums.CancelStockMsgEnum;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.FluteTypeEnum1;
import com.djcps.wms.commons.enums.OrderStatusTypeEnum;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.loadingtask.constant.LoadingTaskConstant;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderIdListBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateOrderBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitOrderBO;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.record.server.OperationRecordServer;
import com.djcps.wms.stock.enums.StockMsgEnum;
import com.djcps.wms.stock.model.AddOrderRedundantBO;
import com.djcps.wms.stock.model.AddStockBO;
import com.djcps.wms.stock.model.BulitTypePO;
import com.djcps.wms.stock.model.MapLocationPO;
import com.djcps.wms.stock.model.MoveStockBO;
import com.djcps.wms.stock.model.RecommendLocaBO;
import com.djcps.wms.stock.model.RecommendLocaParamBO;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.djcps.wms.stock.model.SelectSavedStockAmountBO;
import com.djcps.wms.stock.server.StockServer;
import com.djcps.wms.stock.service.StockService;
import com.djcps.wms.warehouse.server.WarehouseServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * 入库移库业务层
 * 
 * @company:djwms
 * @author:zdx
 * @date:2017年12月20日
 */
@Service
public class StockServiceImpl implements StockService {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private StockServer stockServer;

    @Autowired
    private OrderServer orderServer;

    @Autowired
    private AllocationServer allocationServer;

    @Autowired
    private AbnormalServer abnormalServer;

    @Autowired
    private WarehouseServer warehouseServer;

    @Autowired
    private OperationRecordServer operationRecordServer;

    private JsonParser jsonParser = new JsonParser();

    private Gson gson = GsonUtils.gson;

    private Gson dataFormatGson = GsonUtils.gson;

    @Override
    public Map<String, Object> getRecommendLoca(RecommendLocaBO param) {
        StringBuilder bulider1 = new StringBuilder();
        List<RecommendLocaParamBO> addList = new ArrayList<RecommendLocaParamBO>();
        String location = param.getLocation();
        // location要求小数点显示后六位
        String newLocation = "";
        String[] split = location.split(",");
        for (int i = 0; i <= split.length - 1; i++) {
            String str = split[i];
            int indexOf = str.indexOf(".");
            String substring = str.substring(indexOf + 1);
            if (substring.length() > 6) {
                String str1 = str.substring(0, indexOf);
                String str2 = str.substring(indexOf + 1, indexOf + 7);
                String str3 = new StringBuilder().append(str1).append(".").append(str2).toString();
                if (i == 0) {
                    bulider1.append(str3);
                } else {
                    bulider1.append(",").append(str3);
                }
            } else {
                if (i == 0) {
                    bulider1.append(split[i]);
                } else {
                    bulider1.append(",").append(split[i]);
                }
            }
        }
        newLocation = bulider1.toString();
        // key表示高德地图api的需要的key,location表示经纬度,output输出格式
        String key = AppConstant.MAP_API_KEY;
        String output = "JSON";
        MapLocationPO mapLocationPo = stockServer.getStreetCode(key, newLocation, output);
        if (mapLocationPo != null) {
            RecommendLocaParamBO rl = new RecommendLocaParamBO();
            rl.setPartnerId(param.getPartnerId());
            rl.setStreetCode(mapLocationPo.getStreetCode());
            rl.setWarehouseId(param.getWarehouseId());
            addList.add(rl);
            param.setParam(addList);
            HttpResult result = stockServer.getRecommendLoca(param);
            if (!ObjectUtils.isEmpty(result.getData())) {
                ArrayList data = (ArrayList) result.getData();
                result.setData(data.get(0));
            }
            return MsgTemplate.customMsg(result);
        } else {
            return MsgTemplate.successMsg();
        }
    }

    @Override
    public Map<String, Object> getOperationRecord(OrderIdBO fromJson) {
        HttpResult result = stockServer.getOperationRecord(fromJson);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 入库操作记录数据处理
     * 
     * @param param
     */
    public void addStockOperationRecord(AddStockBO param) {
        OrderIdsBO orderIdsBO = new OrderIdsBO();
        List<String> orderIds = new ArrayList<>();
        orderIds.add(param.getOrderId());
        orderIdsBO.setChildOrderIds(orderIds);
        orderIdsBO.setPartnerArea(param.getPartnerArea());
        // 根据订单编号获取订单信息
        BatchOrderDetailListPO orderInfo = orderServer.getOrderOrSplitOrder(orderIdsBO);
        List<WarehouseOrderDetailPO> orderList = new ArrayList<WarehouseOrderDetailPO>();
        if(!ObjectUtils.isEmpty(orderInfo.getOrderList())) {
            orderList.addAll(orderInfo.getOrderList());
        }
        if(!ObjectUtils.isEmpty(orderInfo.getSplitOrderList())) {
            orderList.addAll(orderInfo.getSplitOrderList());
        }
        List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
            for (WarehouseOrderDetailPO info : joinOrderParamInfo) {
                // 处理数据
                param.setFluteType(FluteTypeEnum1.getCode(info.getFluteType()));
                param.setRelativeName(info.getProductName());
                // 计算操作面积
                double area = operationRecordServer.getVolume(Double.parseDouble(info.getMaterialLength()),
                        Double.parseDouble(info.getMaterialWidth()), param.getAmountSave());
                param.setArea(String.valueOf(area));
            }
        }

    @Override
    public Map<String, Object> addStock(AddStockBO param) {
        // 处理操作记录数据
        addStockOperationRecord(param);
        // 先判断入库的仓库是否被启用,禁用直接驳回
        PartnerInfoBO partnerInfoBean = new PartnerInfoBO();
        BeanUtils.copyProperties(param, partnerInfoBean);
        HttpResult warehouseResult = warehouseServer.getAllWarehouseName(partnerInfoBean);
        if (ObjectUtils.isEmpty(warehouseResult.getData())) {
            return MsgTemplate.failureMsg(SysMsgEnum.NO_HAVE_WAREHOUSE);
        }
        List<BulitTypePO> fromJsonDetailList = gson.fromJson(gson.toJson(warehouseResult.getData()),
                new TypeToken<ArrayList<BulitTypePO>>() {
                }.getType());
        Map<String, BulitTypePO> haveMap = new HashMap<>(16);
        for (BulitTypePO bulitTypePO : fromJsonDetailList) {
            haveMap.put(bulitTypePO.getWarehouseId(), bulitTypePO);
        }
        BulitTypePO bulitTypePO = haveMap.get(param.getWarehouseId());
        if (bulitTypePO == null) {
            return MsgTemplate.failureMsg(SysMsgEnum.WAREHOUSE_ERROR);
        }

        if (param.getOrderId().indexOf("-") == -1) {
            // 判断该订单是否是拆单的子单,是拆单的子单不允许入库
            BatchOrderIdListBO batchOrderIdListBO = new BatchOrderIdListBO();
            List<String> orderIdsList = new ArrayList<>();
            orderIdsList.add(param.getOrderId());
            batchOrderIdListBO.setOrderIds(orderIdsList);
            batchOrderIdListBO.setKeyArea(param.getPartnerArea());
            HttpResult orderResult = orderServer.getOrderDeatilByIdList(batchOrderIdListBO);
            if (!ObjectUtils.isEmpty(orderResult.getData())) {
                BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson
                        .fromJson(gson.toJson(orderResult.getData()), BatchOrderDetailListPO.class);
                List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
                Integer splitOrder = orderServer.joinOrderParamInfo(orderList).get(0).getSplitOrder();
                if (splitOrder == 1) {
                    return MsgTemplate.failureMsg(StockMsgEnum.SPLIT_ORDER_NOT_STOC);
                }
            } else {
                return MsgTemplate.failureMsg(AllocationMsgEnum.ORDER_IS_NULL);
            }
        }
        HttpResult result = null;
        // 订单号
        String orderId = param.getOrderId();
        // 入库数量
        Integer saveAmount = param.getAmountSave();
        // 订单数量
        Integer orderAmount = param.getAmount();
        SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
        BeanUtils.copyProperties(param, selectAreaByOrderId);

        List<String> orderIdList = new ArrayList<>();
        orderIdList.add(orderId);

        OrderIdBO orderIdBO = new OrderIdBO();
        UpdateSplitOrderBO splitOrder = new UpdateSplitOrderBO();
        List<OrderIdBO> list = new ArrayList<OrderIdBO>();
        // 正常子单入库
        if (orderId.indexOf(LoadingTaskConstant.SUBSTRING_ORDER) == -1) {
            orderIdBO.setOrderId(orderId);
            list.add(orderIdBO);
            selectAreaByOrderId.setOrderIds(list);
            // 解析在库信息
            List<WarehouseOrderDetailPO> orderDetail = orderServer.getOrderStockInfo(selectAreaByOrderId);
            // 修改订单状态需要的参数
            if (!ObjectUtils.isEmpty(orderDetail)) {
                Integer trueAmount = orderDetail.get(0).getAmountSaved();
                if (trueAmount + saveAmount > orderAmount) {
                    return MsgTemplate.failureMsg(StockMsgEnum.SAVE_AMOUNT_ERROE);
                } else if (trueAmount + saveAmount == orderAmount) {
                    // 相等表示已入库修改订单状态
                    orderIdBO.setStatus(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue());
                } else {
                    // 小于表示部分入库
                    orderIdBO.setStatus(OrderStatusTypeEnum.LESS_ADD_STOCK.getValue());
                    // 多次部分入库的情况下要累计拆单数量字段的值
                    splitOrder.setSubNumber(saveAmount + trueAmount);
                    splitOrder.setInStock(saveAmount + trueAmount);
                }
            } else {
                if (saveAmount > orderAmount) {
                    return MsgTemplate.failureMsg(StockMsgEnum.SAVE_AMOUNT_ERROE);
                } else if (saveAmount.equals(orderAmount)) {
                    // 相等表示已入库修改订单状态
                    orderIdBO.setStatus(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue());
                } else {
                    // 小于表示部分入库
                    orderIdBO.setStatus(OrderStatusTypeEnum.LESS_ADD_STOCK.getValue());
                    // 多次部分入库的情况下要累计拆单数量字段的值
                    splitOrder.setSubNumber(saveAmount);
                    splitOrder.setInStock(saveAmount);
                }
            }
            param.setId(UUID.randomUUID().toString());
        } else {
            // 拆单入库
            orderIdBO = new OrderIdBO();
            orderIdBO.setOrderId(orderId);
            list.add(orderIdBO);
            if (saveAmount > orderAmount) {
                return MsgTemplate.failureMsg(StockMsgEnum.SAVE_AMOUNT_ERROE);
            } else if (saveAmount.equals(orderAmount)) {
                // 相等表示已入库修改订单状态
                orderIdBO.setStatus(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue());
            } else {
                // 小于表示部分入库
                orderIdBO.setStatus(OrderStatusTypeEnum.LESS_ADD_STOCK.getValue());
            }
            result = stockServer.getInventoryFidByOrderId(param);
            param.setId((String) result.getData());
        }
        // 入库
        result = stockServer.addStock(param);
        if (result.isSuccess()) {
            // 只有在正常子单第一次拆单的情况下才往OMS服务他们的拆单表中生成假数据,并且修订单状态
            if (orderId.indexOf(LoadingTaskConstant.SUBSTRING_ORDER) == -1) {
                UpdateOrderBO updateOrder = new UpdateOrderBO();
                updateOrder.setKeyArea(param.getPartnerArea());
                updateOrder.setOrderId(orderId);
                updateOrder.setOrderStatus(list.get(0).getStatus());
                List<String> deleteList = Arrays.asList(orderId);
                updateOrder.setDeleteOrdeIdList(deleteList);

                List<UpdateSplitOrderBO> addSplitOrderList = new ArrayList<>();
                splitOrder.setOrderId(orderId);
                splitOrder.setSubOrderId(orderId);
                splitOrder.setSubStatus(Integer.valueOf(list.get(0).getStatus()));
                splitOrder.setKeyArea(param.getPartnerArea());
                splitOrder.setIsProduce(0);
                splitOrder.setIsStored(0);
                splitOrder.setIsException(0);

                addSplitOrderList.add(splitOrder);
                updateOrder.setSplitOrders(addSplitOrderList);
                result = orderServer.splitOrder(updateOrder);
                if (!result.isSuccess()) {
                    LOGGER.error("入库,OMS生成假的拆单数据失败,并且修改子单状态失败");
                    return MsgTemplate.failureMsg("入库,OMS生成假的拆单数据失败,并且修改子单状态失败");
                }
            } else {
                // 拆单入库,不需要往oms的拆单表里加数据了,修改拆单状态和拆单数量,以及子单状态
                // 根据子单号获取拆单信息
                String sonOrderId = orderId.substring(0, orderId.indexOf(LoadingTaskConstant.SUBSTRING_ORDER));
                List<OrderIdBO> splitOrderList = new ArrayList<>();
                OrderIdBO order = new OrderIdBO();
                order.setOrderId(sonOrderId);
                order.setKeyArea(param.getPartnerArea());
                result = orderServer.getSplitOrderDeatilByIdList(splitOrderList);
                List<WarehouseOrderDetailPO> orderDetail = null;
                Map<String, List<WarehouseOrderDetailPO>> orderMap = dataFormatGson.fromJson(
                        gson.toJson(result.getData()), new TypeToken<Map<String, List<WarehouseOrderDetailPO>>>() {
                        }.getType());
                for (Map.Entry<String, List<WarehouseOrderDetailPO>> entry : orderMap.entrySet()) {
                    orderDetail = entry.getValue();
                }
                UpdateSplitOrderBO updateSplitOrder = new UpdateSplitOrderBO();
                for (WarehouseOrderDetailPO warehouseOrderDetailPO : orderDetail) {
                    if (orderId.equals(warehouseOrderDetailPO.getSubOrderId())) {
                        Integer subNumber = warehouseOrderDetailPO.getSubNumber();
                        updateSplitOrder.setSubNumber(subNumber + saveAmount);
                    }
                }
                // 修改拆单中的拆单数量
                List<UpdateSplitOrderBO> updateSplitOrderList = new ArrayList<>();
                updateSplitOrder.setKeyArea(param.getPartnerArea());
                updateSplitOrder.setSubOrderId(orderId);
                updateSplitOrder.setSubStatus(Integer.valueOf(list.get(0).getStatus()));
                result = orderServer.updateSplitOrderInfo(updateSplitOrderList);
                if (!result.isSuccess()) {
                    LOGGER.error("拆单入库,修改oms拆单状态数量失败!!!");
                    return MsgTemplate.failureMsg("拆单入库,修改oms拆单状态数量失败!!!");
                }
                if (list.get(0).getStatus().equals(OrderStatusTypeEnum.ALL_ADD_STOCK)) {
                    // 子单修改子单状态
                    List<UpdateOrderBO> orderUpdateList = new ArrayList<>();
                    UpdateOrderBO update = new UpdateOrderBO();
                    update.setOrderId(orderId.substring(0, orderId.indexOf(LoadingTaskConstant.SUBSTRING_ORDER)));
                    update.setOrderStatus(list.get(0).getStatus());
                    update.setKeyArea(param.getPartnerArea());
                    orderUpdateList.add(update);
                    result = orderServer.updateOrderInfo(orderUpdateList);
                    if (!result.isSuccess()) {
                        LOGGER.error("拆单入库为已入库状态,修改oms子单状态失败");
                        return MsgTemplate.failureMsg("拆单入库为已入库状态,修改oms子单状态失败");
                    }
                }
            }
            if (result.isSuccess()) {
                // 插入冗余表数据
                BatchOrderIdListBO batchOrderIdListBO = new BatchOrderIdListBO();
                List<String> orderIdsList = new ArrayList<>();
                orderIdsList.add(param.getOrderId());
                batchOrderIdListBO.setOrderIds(orderIdsList);
                batchOrderIdListBO.setKeyArea(param.getPartnerArea());
                result = orderServer.getOrderDeatilByIdList(batchOrderIdListBO);
                if (!ObjectUtils.isEmpty(result.getData())) {
                    BatchOrderDetailListPO batchOrderDetailListPO = gson.fromJson(gson.toJson(result.getData()),
                            BatchOrderDetailListPO.class);
                    List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
                    List<WarehouseOrderDetailPO> splitOrderList = batchOrderDetailListPO.getSplitOrderList();
                    List<WarehouseOrderDetailPO> detail = !ObjectUtils.isEmpty(orderList) ? orderList : splitOrderList;
                    WarehouseOrderDetailPO onlinePaperboardPO = orderServer.joinOrderParamInfo(detail).get(0);

                    AddOrderRedundantBO orderRedundant = new AddOrderRedundantBO();
                    BeanUtils.copyProperties(onlinePaperboardPO, orderRedundant);
                    BeanUtils.copyProperties(param, orderRedundant);
                    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (!ObjectUtils.isEmpty(onlinePaperboardPO.getDeliveryTime())) {
                        orderRedundant.setDeliveryTime(sd.format(onlinePaperboardPO.getDeliveryTime()));
                    }
                    if (!ObjectUtils.isEmpty(onlinePaperboardPO.getOrderTime())) {
                        orderRedundant.setOrderTime(sd.format(onlinePaperboardPO.getOrderTime()));
                    }
                    if (!ObjectUtils.isEmpty(onlinePaperboardPO.getPayTime())) {
                        orderRedundant.setPaymentTime(sd.format(onlinePaperboardPO.getPayTime()));
                    }
                    orderRedundant.setStatus(onlinePaperboardPO.getOrderStatus());
                    orderRedundant.setIsSplit(
                            onlinePaperboardPO.getSplitOrder() == null ? 0 : onlinePaperboardPO.getSplitOrder());
                    // 插入冗余数据订单数据
                    result = allocationServer.batchAddOrderRedundant(orderRedundant);
                    if (!result.isSuccess()) {
                        return MsgTemplate.failureMsg(StockMsgEnum.REDUNDANT_FAIL);
                    }

                    // 判断该订单是否是已入库状态,是的话修改冗余表修改为已入库
                    if (OrderStatusTypeEnum.ALL_ADD_STOCK.getValue().equals(orderIdBO.getStatus())) {
                        // 修改冗余表订单状态为已入库
                        List<UpdateOrderRedundantBO> updateList = new ArrayList<>();
                        UpdateOrderRedundantBO update = new UpdateOrderRedundantBO();
                        update.setStatus(Integer.valueOf(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue()));
                        update.setOrderId(param.getOrderId());
                        update.setPartnerId(param.getPartnerId());
                        updateList.add(update);
                        allocationServer.batchUpdateOrderRedun(updateList);
                    }
                    // 异常订单逻辑
                    OrderIdListBO orderIdListBO = new OrderIdListBO();
                    BeanUtils.copyProperties(param, orderIdListBO);
                    orderIdListBO.setList(new ArrayList<>());
                    orderIdListBO.getList().add(param.getOrderId());
                    result = abnormalServer.getOrderByOrderIdList(orderIdListBO);
                    if (ObjectUtils.isEmpty(result.getData())) {
                        // 未生产异常订单
                        if (OrderStatusTypeEnum.LESS_ADD_STOCK.getValue().equals(orderIdBO.getStatus())) {
                            // 剩余的异常订单数量
                            Integer surplusOrderAmount = orderAmount - saveAmount;
                            // 直接插入异常订单数据
                            WarehouseOrderDetailPO orderDeatil = orderServer.joinOrderParamInfo(detail).get(0);
                            AddAbnormal addAbnormal = new AddAbnormal();
                            BeanUtils.copyProperties(param, addAbnormal);
                            addAbnormal.setLink(AbnormalConstant.ABNORMAL_LINK_ADD_STOCK);
                            addAbnormal.setReason(new StringBuffer().append(AbnormalConstant.ABNORMAL_ERROR_REASON)
                                    .append(surplusOrderAmount).toString());
                            addAbnormal.setAbnomalAmount(surplusOrderAmount);
                            addAbnormal.setCustomerName(orderDeatil.getCustomerName());
                            addAbnormal.setProductName(orderDeatil.getProductName());
                            addAbnormal.setIsSplit(orderDeatil.getSplitOrder());
                            result = abnormalServer.addAbnormal(addAbnormal);
                            if (result.isSuccess()) {
                                // oms修改异常标记逻辑
                                List<String> strOrderIds = Arrays.asList(param.getOrderId());
                                result = abnormalServer.updateExecptionFlag(1, strOrderIds, param.getPartnerArea());
                                if (!result.isSuccess()) {
                                    return MsgTemplate
                                            .failureMsg(AbnormalMsgEnum.STOCK_UPDATE_SPLIT_ORDER_STATUS_ERROR);
                                }
                            }
                        } else {
                            // 已入库oms修改异常标记逻辑,修改为正常
                            List<String> strOrderIds = Arrays.asList(param.getOrderId());
                            result = abnormalServer.updateExecptionFlag(0, strOrderIds, param.getPartnerArea());
                            if (!result.isSuccess()) {
                                return MsgTemplate.failureMsg(AbnormalMsgEnum.STOCK_UPDATE_SPLIT_ORDER_STATUS_ERROR);
                            }
                        }
                    } else {
                        // 已生产异常订单
                        UpdateAbnormalBO updateOrderBO = new UpdateAbnormalBO();
                        BeanUtils.copyProperties(param, updateOrderBO);
                        if (OrderStatusTypeEnum.LESS_ADD_STOCK.getValue().equals(orderIdBO.getStatus())) {
                            AbnormalOrderPO abnormalFromJson = gson.fromJson(
                                    gson.toJson(
                                            jsonParser.parse(gson.toJson(result.getData())).getAsJsonArray().get(0)),
                                    AbnormalOrderPO.class);
                            // 异常数量
                            Integer abnomalAmount = abnormalFromJson.getAbnomalAmount();
                            // 剩余的异常订单数量
                            Integer surplusOrderAmount = abnomalAmount - saveAmount;
                            // 仍然为异常订单,修改异常数量即可
                            updateOrderBO.setAbnomalAmount(String.valueOf(surplusOrderAmount));
                            updateOrderBO.setReason(new StringBuffer().append(AbnormalConstant.ABNORMAL_ERROR_REASON)
                                    .append(surplusOrderAmount).toString());
                            updateOrderBO.setSubmiter(param.getOperator());
                            result = abnormalServer.updateAbnormal(updateOrderBO);
                            if (result.isSuccess()) {
                                // oms修改异常标记逻辑
                                List<String> strOrderIds = Arrays.asList(param.getOrderId());
                                result = abnormalServer.updateExecptionFlag(1, strOrderIds, param.getPartnerArea());
                                if (!result.isSuccess()) {
                                    return MsgTemplate
                                            .failureMsg(AbnormalMsgEnum.STOCK_UPDATE_SPLIT_ORDER_STATUS_ERROR);
                                }
                            }
                        } else {
                            updateOrderBO.setStatus(Integer.valueOf(AbnormalConstant.ABNORMAL_STATUS));
                            updateOrderBO.setAbnomalAmount("0");
                            updateOrderBO.setReason(AbnormalConstant.ABNORMAL_REASON_NULL);
                            updateOrderBO.setSubmiter(param.getOperator());
                            result = abnormalServer.updateAbnormal(updateOrderBO);
                            if (result.isSuccess()) {
                                // oms修改异常标记逻辑
                                List<String> strOrderIds = Arrays.asList(param.getOrderId());
                                result = abnormalServer.updateExecptionFlag(0, strOrderIds, param.getPartnerArea());
                                if (!result.isSuccess()) {
                                    return MsgTemplate
                                            .failureMsg(AbnormalMsgEnum.STOCK_UPDATE_SPLIT_ORDER_STATUS_ERROR);
                                }
                            }
                        }
                    }
                } else {
                    return MsgTemplate.failureMsg(AllocationMsgEnum.ORDER_IS_NULL);
                }
            }
        }
        return MsgTemplate.customMsg(result);
    }

    @Override
    public Map<String, Object> moveStock(MoveStockBO param) {
        OrderIdsBO orderIdsBO = new OrderIdsBO();
        List<String> orderIds = new ArrayList<>();
        orderIds.add(param.getOrderId());
        orderIdsBO.setChildOrderIds(orderIds);
        orderIdsBO.setPartnerArea(param.getPartnerArea());
        // 根据订单编号获取订单信息
        BatchOrderDetailListPO orderInfo = orderServer.getOrderOrSplitOrder(orderIdsBO);
        List<WarehouseOrderDetailPO> orderList = new ArrayList<WarehouseOrderDetailPO>();
        if(!ObjectUtils.isEmpty(orderInfo.getOrderList())) {
            orderList.addAll(orderInfo.getOrderList());
        }
        if(!ObjectUtils.isEmpty(orderInfo.getSplitOrderList())) {
            orderList.addAll(orderInfo.getSplitOrderList());
        }
        List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
        if (!ObjectUtils.isEmpty(joinOrderParamInfo)) {
            for (WarehouseOrderDetailPO info : joinOrderParamInfo) {
                // 处理数据
                param.setFluteType(FluteTypeEnum1.getCode(info.getFluteType()));
                param.setRelativeName(info.getProductName());
                // 计算操作面积
                double area = operationRecordServer.getVolume(Double.parseDouble(info.getMaterialLength()),
                        Double.parseDouble(info.getMaterialWidth()), Integer.parseInt(param.getAmountSave()));
                param.setArea(String.valueOf(area));
            }
        }
        HttpResult result = stockServer.moveStock(param);
        return MsgTemplate.customMsg(result);
    }

    @Override
    public Map<String, Object> getSavedStockAmount(SelectSavedStockAmountBO param) {
        HttpResult result = stockServer.getSavedStockAmount(param);
        return MsgTemplate.customMsg(result);
    }

    @Override
    public Map<String, Object> getAreaByOrderId(SelectAreaByOrderIdBO param) {
        HttpResult result = stockServer.getAreaByOrderId(param);
        return MsgTemplate.customMsg(result);
    }

}
