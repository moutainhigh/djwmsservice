package com.djcps.wms.stocktaking.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.djcps.wms.abnormal.constant.AbnormalConstant;
import com.djcps.wms.abnormal.enums.AbnormalMsgEnum;
import com.djcps.wms.abnormal.model.AbnormalOrderPO;
import com.djcps.wms.abnormal.model.AddAbnormal;
import com.djcps.wms.abnormal.model.OrderIdListBO;
import com.djcps.wms.abnormal.model.UpdateAbnormalBO;
import com.djcps.wms.abnormal.server.AbnormalServer;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.FluteTypeEnum;
import com.djcps.wms.commons.enums.FluteTypeEnum1;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.commons.utils.StringUtils;
import com.djcps.wms.delivery.model.SaveDeliveryBO;
import com.djcps.wms.order.model.*;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderIdListBO;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.record.constant.StockTakingOperationConstant;
import com.djcps.wms.record.model.OrderOperationRecordPO;
import com.djcps.wms.record.model.TaskOperationRecordPO;
import com.djcps.wms.record.model.param.SaveOperationRecordBO;
import com.djcps.wms.record.model.param.StocktakingRecordListBO;
import com.djcps.wms.record.server.OperationRecordServer;
import com.djcps.wms.record.util.StockTakingOperationRecordUtil;
import com.djcps.wms.stocktaking.constant.StocktakingTaskConstant;
import com.djcps.wms.stocktaking.enums.StocktakingMsgEnum;
import com.djcps.wms.stocktaking.model.*;
import com.djcps.wms.stocktaking.model.orderresult.InnerDate;
import com.djcps.wms.stocktaking.model.orderresult.OrderInfoListResult;
import com.djcps.wms.stocktaking.model.orderresult.OrderResult;
import com.djcps.wms.stocktaking.server.StocktakingOrderServer;
import com.djcps.wms.stocktaking.server.StocktakingTaskServer;
import com.djcps.wms.stocktaking.service.StocktakingTaskService;
import com.google.gson.*;
import com.mysql.fabric.xmlrpc.base.Array;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**盘点任务实现类
 * @title:
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/10
 **/
@Service
public class StocktakingTaskServiceImpl implements StocktakingTaskService {

    @Autowired
    private StocktakingTaskServer stocktakingTaskServer;

    @Autowired
    private OrderServer orderServer;
    @Autowired
    private OperationRecordServer operationRecordServer;

    @Autowired
    private AbnormalServer abnormalServer;

    private Gson gson = GsonUtils.gson;

    /**
     * 新增全盘
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/23 11:07
     **/
    @Override
    public Map<String, Object> addTaskByAll(AddTaskBO addTaskBO){
        String warehouseid=addTaskBO.getWarehouseId();
        String warehousename=addTaskBO.getWarehouseName();
        //Http获取库位关联订单信息
        HttpResult result=stocktakingTaskServer.increaseTask(addTaskBO);
        if(ObjectUtils.isEmpty(result.getData())){
            return MsgTemplate.successMsg();
        }
        //获取订单库位信息list
        String data = gson.toJson(result.getData());
        List<LocationOrderInfoBO> locationOrderInfoBOList = JSONArray.parseArray(data,LocationOrderInfoBO.class);
        
        locationOrderInfoBOList.stream().forEach(locationOrderInfoBO -> {
            locationOrderInfoBO.setWarehouseId(warehouseid);
            locationOrderInfoBO.setWarehouseName(warehousename);
        });

        //组装orderidlist
        List<String> orderidlist= new ArrayList<String>();
        for (LocationOrderInfoBO locationOrderInfoBO:locationOrderInfoBOList){
            orderidlist.add(locationOrderInfoBO.getOrderId());
        }
        OrderIdsBO orderIdsBO = new OrderIdsBO();
        orderIdsBO.setChildOrderIds(orderidlist);
        orderIdsBO.setPartnerArea(addTaskBO.getPartnerArea());
        //Http获取订单详细信息
        List<ChildOrderBO> childOrderList = orderServer.getChildOrderList(orderIdsBO);
        if(childOrderList.isEmpty()){
            return MsgTemplate.successMsg();
        }
        return MsgTemplate.successMsg(getStocktakingOrderDetail(childOrderList,locationOrderInfoBOList));
    }

    /**
     * 新增部分盘点
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/24 10:48
     **/
    @Override
    public Map<String,Object> addTaskByPart(AddStocktakingBO addStocktakingBO){
        List<AddTaskByPartBO> listAddTaskByPartBO=new ArrayList<AddTaskByPartBO>();
        AddTaskByPartBO addTaskByPartBO=null;
        String warehouseId=addStocktakingBO.getWarehouseID();
        String warehouseName=addStocktakingBO.getWarehouseName();
        String partnerId=addStocktakingBO.getPartnerId();
        String warehouseAreaId="";
        String warehouseLocId="";
        for (WarehouseAreaBO warehouseAreaBO:addStocktakingBO.getAreaList()){
            warehouseAreaId=warehouseAreaBO.getWarehouseAreaId();
            for (WarehouseLocationBO warehouseLocationBO:warehouseAreaBO.getLocationList()){
                addTaskByPartBO=new AddTaskByPartBO();
                warehouseLocId=warehouseLocationBO.getWarehouseLocId();
                addTaskByPartBO.setPartnerId(partnerId);
                addTaskByPartBO.setWarehouseId(warehouseId);
                addTaskByPartBO.setWarehouseAreaId(warehouseAreaId);
                addTaskByPartBO.setWarehouseLocId(warehouseLocId);
                listAddTaskByPartBO.add(addTaskByPartBO);
            }
        }
        ListAddTaskByPartBO listAddTaskByPartBO1=new ListAddTaskByPartBO();
        listAddTaskByPartBO1.setTaskList(listAddTaskByPartBO);
        //Http获取所有订单库位信息
        HttpResult result=stocktakingTaskServer.addTaskByPart(listAddTaskByPartBO1);
        if (ObjectUtils.isEmpty(result.getData())){
            return MsgTemplate.successMsg();
        }
        //获取订单库位信息list
        List<LocationOrderInfoBO> locationOrderInfoBOList=new ArrayList<LocationOrderInfoBO>();
        JsonArray locListjsonArray=new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
        for (JsonElement jsonElement:locListjsonArray){
            JsonArray locjsonArray=jsonElement.getAsJsonArray();
            for (JsonElement jsonElement1:locjsonArray){
                JsonObject locJsonObject=jsonElement1.getAsJsonObject();
                LocationOrderInfoBO locationOrderInfoBO=gson.fromJson(locJsonObject,LocationOrderInfoBO.class);
                locationOrderInfoBO.setWarehouseName(warehouseName);
                locationOrderInfoBO.setWarehouseId(warehouseId);
                locationOrderInfoBOList.add(locationOrderInfoBO);
            }
        }
        //组装orderidlist
        List<String> orderidlist= new ArrayList<String>();
        for (LocationOrderInfoBO locationOrderInfoBO:locationOrderInfoBOList){
            orderidlist.add(locationOrderInfoBO.getOrderId());
        }
        OrderIdsBO orderIdsBO = new OrderIdsBO();
        orderIdsBO.setChildOrderIds(orderidlist);
        orderIdsBO.setPartnerArea(addStocktakingBO.getPartnerArea());
        //Http获取订单详细信息
        List<ChildOrderBO> childOrderList = orderServer.getChildOrderList(orderIdsBO);
        if(ObjectUtils.isEmpty(childOrderList)){
            return MsgTemplate.successMsg();
        }
        return MsgTemplate.successMsg(getStocktakingOrderDetail(childOrderList,locationOrderInfoBOList));
    }

    /**
     * 更新盘点状态
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 9:18
     **/
    @Override
    public Map<String, Object> updateTaskState(UpdateStocktakingTaskBO updateStocktakingTaskBO) {
        HttpResult result=stocktakingTaskServer.updateTaskState(updateStocktakingTaskBO);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 单独更新
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/26 9:49
     **/
    @Override
    public Map<String, Object> updatePdaStatus(UpdateStocktakingTaskBO updateStocktakingTaskBO) {
        updateStocktakingTaskBO.setPdaStatus(StocktakingTaskConstant.PDASTATUS_1);
        HttpResult result=stocktakingTaskServer.updateTaskState(updateStocktakingTaskBO);
        //TODO 调用下发推送
//        PushExtraFieldBO pushExtraFieldBO = new PushExtraFieldBO();
//        pushExtraFieldBO.setUserId(param.getPickerId());
//        pushExtraFieldBO.setOpenType(AppConstant.PUSH_OPEN_TYPE_DELIVERY);
//        PushMsgBO push = new PushMsgBO();
//        push.setUserid(param.getPickerId());
//		  push.setMsg(AllocationConstant.PUSH_DELIVERY_MSG);
//        push.setAppSystem(AppConstant.WMS);
//        push.setMid(param.getDeliveryId());
//        push.setType(AllocationConstant.PUSH_DELIVERY_TYPE);
//        push.setTitle(AllocationConstant.PUSH_DELIVERY_TITLE);
//        push.setText(AllocationConstant.PUSH_DELIVERY_TEXT);
//        push.setExtraField(pushExtraFieldBO);
//        //消息推送
//        Map<String, Object> sendAppMsg = pushService.sendAppMsg(push);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 获取可用盘点人员列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 15:07
     **/
    @Override
    public Map<String, Object> getInventoryclerk() {
       List<InventoryClerkBO> list=new ArrayList<InventoryClerkBO>();
       InventoryClerkBO inventoryClerkBO=new InventoryClerkBO();
        inventoryClerkBO.setInventoryClerk("吴智勇");
        inventoryClerkBO.setInventoryClerkId("1001028");
        InventoryClerkBO inventoryClerkBO2=new InventoryClerkBO();
        inventoryClerkBO2.setInventoryClerk("郑杰");
        inventoryClerkBO2.setInventoryClerkId("977");
        InventoryClerkBO inventoryClerkBO3=new InventoryClerkBO();
        inventoryClerkBO3.setInventoryClerk("超級管理員");
        inventoryClerkBO3.setInventoryClerkId("81");
        InventoryClerkBO inventoryClerkBO4=new InventoryClerkBO();
        inventoryClerkBO4.setInventoryClerk("东城周德星");
        inventoryClerkBO4.setInventoryClerkId("fedafb813d254ed19a4617f1e633773e");
        list.add(inventoryClerkBO);
        list.add(inventoryClerkBO2);
        list.add(inventoryClerkBO3);
        list.add(inventoryClerkBO4);
        return MsgTemplate.successMsg(list);
    }

    /**
     * 获取新增盘点任务作业单号
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 15:44
     **/
    @Override
    public Map<String, Object> getNumber() {
        HttpResult result=stocktakingTaskServer.getNumber();
        //更换data为字符串"TS+日期20180101"
        String backjson= gson.toJson(result.getData());
        Map<String,List<String>> map=gson.fromJson(backjson, Map.class);
        String number= "";
        //返回的编号键名
        String key="numbers";
        if((map.get(key)).size()>0){
            number= AppConstant.TS+ (new SimpleDateFormat("yyyyMMdd")).format(new Date())+(map.get("numbers")).get(0);
        }
        result.setData(number);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 组装订单
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/24 15:30
     **/
//    public List<StocktakingTaskBO> getPartStocktakingOrderDetail(List<ForderInfoBO> forderInfoBOList, List<LocationOrderInfoBO> locationOrderInfoBOList){
//        List<StocktakingTaskBO> stocktakingTaskBOList=new ArrayList<StocktakingTaskBO>();
//        StocktakingTaskBO stocktakingTaskBO=null;
//        for(LocationOrderInfoBO locationOrderInfoBO:locationOrderInfoBOList){
//            for (ForderInfoBO forderInfoBO : forderInfoBOList){
//                if (locationOrderInfoBO.getOrderId().equals(forderInfoBO.getFchildorderid())){
//                    stocktakingTaskBO=new StocktakingTaskBO();
//                    stocktakingTaskBO.setWarehouseId(locationOrderInfoBO.getWarehouseId());
//                    stocktakingTaskBO.setWarehouseName(locationOrderInfoBO.getWarehouseName());
//                    stocktakingTaskBO.setWarehouseAreaId(locationOrderInfoBO.getWarehouseAreaId());
//                    stocktakingTaskBO.setWarehouseAreaName(locationOrderInfoBO.getWarehouseAreaName());
//                    stocktakingTaskBO.setWarehouseLocId(locationOrderInfoBO.getWarehouseLocId());
//                    stocktakingTaskBO.setWarehouseLocName(locationOrderInfoBO.getWarehouseLocName());
//                    if(locationOrderInfoBO.getAmount()!=null){
//                        stocktakingTaskBO.setTrueAmount(locationOrderInfoBO.getAmount());
//                    }
//                    //组装盘点任务订单详情
//                    WarehouseOrderDetailPO orderDetailPO=new WarehouseOrderDetailPO();
//                    if(!ObjectUtils.isEmpty(forderInfoBO.getFboxlength()) && !ObjectUtils.isEmpty(forderInfoBO.getFboxwidth()) &&
//                            !ObjectUtils.isEmpty(forderInfoBO.getFboxheight())){
//                        forderInfoBO.setFproductRule(new StringBuffer().append(forderInfoBO.getFboxlength()).append("*")
//                                .append(forderInfoBO.getFboxwidth()).append("*").append(forderInfoBO.getFboxheight()).toString());
//                    }
//                    forderInfoBO.setFmaterialRule(new StringBuffer().append(forderInfoBO.getFmateriallength()).append("*")
//                            .append(forderInfoBO.getFmaterialwidth()).toString());
//                    
//                    orderDetailPO.setOrderId(forderInfoBO.getFchildorderid());
//                    orderDetailPO.setMaterialId(forderInfoBO.getFmateriafid());
//                    orderDetailPO.setFboxlength(forderInfoBO.getFboxlength().toString());
//                    orderDetailPO.setFboxwidth(forderInfoBO.getFboxwidth().toString());
//                    orderDetailPO.setFboxheight(forderInfoBO.getFboxheight().toString());
//                    orderDetailPO.setFmateriallength(forderInfoBO.getFmateriallength().toString());
//                    orderDetailPO.setFmaterialwidth(StringUtils.toString(forderInfoBO.getFmaterialwidth()));
//                    BeanUtils.copyProperties(forderInfoBO,orderDetailPO);
//                    //组织参数
//                    stocktakingTaskBO.setOrderDetail(orderDetailPO);
//                    stocktakingTaskBOList.add(stocktakingTaskBO);
//                }
//                else{
//
//                }
//            }
//        }
//        return stocktakingTaskBOList;
//    }

    /**
     * 组装订单详情+订单仓库信息
     * @author  wzy
     * @param
     * @return
     * @date  2018/2/2 17:04
     **/
    public List<StocktakingTaskfBO> getStocktakingOrderDetail(List<ChildOrderBO> childOrderBOList, List<LocationOrderInfoBO> locationOrderInfoBOList){
        List<StocktakingTaskfBO> stocktakingTaskBOList=new ArrayList<StocktakingTaskfBO>();
        if (!childOrderBOList.isEmpty()) {
            locationOrderInfoBOList.stream().forEach(locationOrder -> {
                                    Optional optional= childOrderBOList.stream()
                                    .filter(b -> b.getChildOrderId().equals(locationOrder.getOrderId()))
                                    .findFirst();
                if(optional.isPresent()){
                    ChildOrderBO childOrderBO=(ChildOrderBO) optional.get();
                    StocktakingTaskfBO stocktakingTaskBO=new StocktakingTaskfBO();
                    BeanUtils.copyProperties(locationOrder, stocktakingTaskBO);
                    if(locationOrder.getAmount()!=null){
                        stocktakingTaskBO.setTrueAmount(locationOrder.getAmount());
                    }
                    //组装盘点任务订单详情
                    OrderInfoBO orderInfoBO=new OrderInfoBO();
                    BeanUtils.copyProperties(childOrderBO, orderInfoBO);
                    //加入参数
                    stocktakingTaskBO.setOrderDetail(orderInfoBO);
                    stocktakingTaskBOList.add(stocktakingTaskBO);
                }
            });
        }
        return stocktakingTaskBOList;
    }

    /**
     * 修改规格参数拼接
     * @param childOrderBOList
     * @return
     * @author:wzy
     * @date:2018年1月8日
     */
    private OrderInfoBO getOrderDetail(List<ChildOrderBO> childOrderBOList){
        OrderInfoBO orderInfoBO=null;
        //获取第一条
            Optional optional=childOrderBOList.stream().findFirst();
            if(optional.isPresent()){
                ChildOrderBO childOrderBO=(ChildOrderBO) optional.get();
                //组装盘点任务订单详情
                orderInfoBO=new OrderInfoBO();
                BeanUtils.copyProperties(childOrderBO, orderInfoBO);
            }
        return orderInfoBO;
    }

    /**
     * 参数拼接
     * @author  wzy
     * @param
     * @return
     * @date  2018/2/3 10:23
     **/
//    private WarehouseOrderDetailPO getOrderDetail(WarehouseOrderDetailPO source, WarehouseOrderDetailPO target){
//        //规格长宽高都不为null,才进行拼接
//        if(!ObjectUtils.isEmpty(target.getFboxlength()) && !ObjectUtils.isEmpty(target.getFboxwidth()) &&
//                !ObjectUtils.isEmpty(target.getFboxheight())){
//            //拼接字符串,拼接成产品规格和下料规格
//            source.setFproductRule(new StringBuffer().append(target.getFboxlength()).append("*")
//                    .append(target.getFboxwidth()).append("*").append(target.getFboxheight()).toString());
//        }
//        source.setFmaterialRule(new StringBuffer().append(target.getFmateriallength()).append("*")
//                .append(target.getFmaterialwidth()).toString());
//        //组织参数
//        source.setFmaterialRule(new StringBuffer().append(target.getFmateriallength()).append("*")
//                .append(target.getFmaterialwidth()).toString());
//        source.setFordertime(target.getFordertime());
//        source.setFdelivery(target.getFdelivery());
//        source.setFgroupgoodname(target.getFgroupgoodname());
//        source.setFflutetype(target.getFflutetype());
//        source.setFstatus(target.getFstatus());
//        source.setFmaterialname(target.getFmaterialname());
//        source.setFlnglat(target.getFlnglat());
//        source.setFpaymenttime(target.getFpaymenttime());
//        source.setFaddressdetail(target.getFaddressdetail());
//        source.setFcodeprovince(target.getFcodeprovince());
//        source.setFconsignee(target.getFconsignee());
//        source.setFcontactway(target.getFcontactway());
//        source.setFpusername(target.getFpusername());
//        source.setOrderId(source.getFchildorderid());
//        source.setAmount(source.getFamount());
//        source.setFamount(source.getAmount());
//        return null;
//    }
    /**
     * 不带f的订单详细信息参数拼接
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/15 15:24
     **/
//    private WarehouseOrderDetailPO getOrderDetail(SaveStocktakingOrderInfoBO source, SaveStocktakingOrderInfoBO target){
//        //规格长宽高都不为null,才进行拼接
//        if(!ObjectUtils.isEmpty(target.getBoxLength()) && !ObjectUtils.isEmpty(target.getBoxWidth()) &&
//                !ObjectUtils.isEmpty(target.getBoxHeight())){
//            //拼接字符串,拼接成产品规格和下料规格
//            source.setProductRule(new StringBuffer().append(target.getBoxLength()).append("*")
//                    .append(target.getBoxWidth()).append("*").append(target.getBoxHeight()).toString());
//        }
//        source.setMaterialRule(new StringBuffer().append(target.getMaterialLength()).append("*")
//                .append(target.getMaterialWidth()).toString());
//        //组织参数
//        source.setOrdertime(target.getOrdertime());
//        source.setDelivery(target.getDelivery());
//        source.setGroupgoodname(target.getGroupgoodname());
//        source.setFluteType(target.getFluteType());
//        source.setStatus(target.getStatus());
//        source.setMaterialname(target.getMaterialname());
//        source.setLnglat(target.getLnglat());
//        source.setPaymenttime(target.getPaymenttime());
//        source.setAddressdetail(target.getAddressdetail());
//        source.setCodeprovince(target.getCodeprovince());
//        source.setConsignee(target.getConsignee());
//        source.setContactway(target.getContactway());
//        source.setPusername(target.getPusername());
//        source.setAmount(source.getAmount());
//        return null;
//    }

    /**
     * 保存新增的盘点任务
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 22:18
     **/
    @Override
    public Map<String, Object> saveSoctakingTask(SaveStocktakingTaskBO saveStocktakingTaskBO) {
        //判断该作业单号是否存在
        String partnerId=saveStocktakingTaskBO.getPartnerId();
        String relativeId=saveStocktakingTaskBO.getJobId();
        List<SaveStocktakingOrderInfoBO> list=saveStocktakingTaskBO.getStocktakingOrderInfo();
        for (int i=0;i<list.size();i++){
            list.get(i).setPartnerId(partnerId);
            list.get(i).setRelativeId(relativeId);
            list.get(i).setOperatorId(saveStocktakingTaskBO.getCreatorId());
            list.get(i).setOperator(saveStocktakingTaskBO.getCreator());
            list.get(i).setIsInventoryProfit(2);
            list.get(i).setFluteType(StringUtils.switchFluteTypeToNumber(list.get(i).getFluteType()));
            GetAmountBO getAmountBO=new GetAmountBO();
            getAmountBO.setOrderId(list.get(i).getOrderId());
            getAmountBO.setWarehouseAreaId(list.get(i).getWarehouseAreaId());
            getAmountBO.setWarehouseLocId(list.get(i).getWarehouseLocId());
            getAmountBO.setPartnerId(list.get(i).getPartnerId());
            //获取在库数量
            HttpResult amountResult=stocktakingTaskServer.getAmount(getAmountBO);
            if(!ObjectUtils.isEmpty(amountResult.getData())){
                int trueAmount = Integer.parseInt(new java.text.DecimalFormat("0").format(amountResult.getData()));
                list.get(i).setInstockAmount(trueAmount);
            }
            if(ObjectUtils.isEmpty(list.get(i).getTakeStockAmount())){
                list.get(i).setStatus(StocktakingTaskConstant.STATUS_1);
            }
            else{
                list.get(i).setStatus(StocktakingTaskConstant.STATUS_3);
            }
        }
        //判断是否下发，修改pda作业状态
        if(saveStocktakingTaskBO.getSaveStocktakingType().equals(StocktakingTaskConstant.CONFIRM_PUSH)){
            saveStocktakingTaskBO.setPdaStatus(StocktakingTaskConstant.PDASTATUS_1);
        }
        else if (saveStocktakingTaskBO.getSaveStocktakingType().equals(StocktakingTaskConstant.CONFIRM_PRINT)){
            saveStocktakingTaskBO.setPdaStatus(StocktakingTaskConstant.PDASTATUS_0);
        }
        else {
            saveStocktakingTaskBO.setPdaStatus(StocktakingTaskConstant.PDASTATUS_0);
        }
        saveStocktakingTaskBO.setStocktakingOrderInfo(list);
        HttpResult result=stocktakingTaskServer.saveSoctakingTask(saveStocktakingTaskBO);
        if(result.isSuccess()){
            //修改打印次数
            if (saveStocktakingTaskBO.getSaveStocktakingType().equals(StocktakingTaskConstant.CONFIRM_PRINT)){
                PrintCountBO printCountBO=new PrintCountBO();
                printCountBO.setPartnerId(saveStocktakingTaskBO.getPartnerId());
                printCountBO.setJobId(saveStocktakingTaskBO.getJobId());
                Map<String, Object> printCountmap=printCount(printCountBO);
            }
        }
        return  MsgTemplate.customMsg(result);
    }

    /**
     * 请求盘点任务信息
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 9:44
     **/
    @Override
    public Map<String, Object> stocktakingOrderInfoList(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO){
        HttpResult result=stocktakingTaskServer.stocktakingOrderInfoList(pdaGetStocktakingOrderBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        // data数据为空将值赋值为null,这里取到的是空数组
        if(ObjectUtils.isEmpty(result.getData())){
            return MsgTemplate.successMsg(null);
        }
        List<SaveStocktakingOrderInfoBO> list=new ArrayList<SaveStocktakingOrderInfoBO>();
        JsonObject resultObject = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonObject();
        OrderInfoListResult orderInfoListResult=gson.fromJson(resultObject,OrderInfoListResult.class);
        for (SaveStocktakingOrderInfoBO saveStock:orderInfoListResult.getTaskOrderInfo().getResult()){
	    	//规格长宽高都不为null,才进行拼接
			if(!ObjectUtils.isEmpty(saveStock.getBoxLength()) && !ObjectUtils.isEmpty(saveStock.getBoxWidth()) &&
					!ObjectUtils.isEmpty(saveStock.getBoxHeight())){
				saveStock.setProductSize(new StringBuffer().append(saveStock.getBoxLength()).append("*").append(saveStock.getBoxWidth())
						.append("*").append(saveStock.getBoxHeight()).toString());
			}
			if(!ObjectUtils.isEmpty(saveStock.getMaterialLength()) && !ObjectUtils.isEmpty(saveStock.getMaterialWidth())){
				saveStock.setMaterialSize(new StringBuffer().append(saveStock.getMaterialLength()).append("*")
						.append(saveStock.getMaterialWidth()).toString());
			}
			saveStock.setFluteType(StringUtils.switchFluteTypeToString(saveStock.getFluteType()));
            list.add(saveStock);
        }
        orderInfoListResult.getTaskOrderInfo().setResult(list);
        
        
        
        
        return  MsgTemplate.successMsg(orderInfoListResult);
    }

    /**
     * 修改版获取盘点任务订单信息，前端校验是否需要盘盈
     * @author  wzy
     * @param
     * @return
     * @date  2018/2/3 12:25
     **/
    @Override
    public Map<String, Object> inventorySurplus2(StocktakingTaskfBO stocktakingTaskBO) {
        String orderId=stocktakingTaskBO.getOrderId();
        List<String> list=new ArrayList<String>();
        list.add(orderId);
        OrderIdsBO orderIdsBO = new OrderIdsBO();
        orderIdsBO.setChildOrderIds(list);
        orderIdsBO.setPartnerArea(stocktakingTaskBO.getPartnerArea());
        //Http获取订单详细信息
        List<ChildOrderBO> childOrderList = orderServer.getChildOrderList(orderIdsBO);
        if(ObjectUtils.isEmpty(childOrderList) ){
            return MsgTemplate.failureMsg(StocktakingMsgEnum.STOCKTAKING_ORDER_WRONG);
        }
        //组装订单信息
        OrderInfoBO orderInfoBO=getOrderDetail(childOrderList);
        stocktakingTaskBO.setOrderDetail(orderInfoBO);
        return MsgTemplate.successMsg(stocktakingTaskBO);
    }

    /**
     * PDA/Web保存盘盈录入信息 未使用
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/14 22:38
     **/
    @Override
    public Map<String, Object> saveInventoryProfitInfo(SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO) {
       saveStocktakingOrderInfoBO.setRelativeId(saveStocktakingOrderInfoBO.getOrderId());
       //1盘盈
       saveStocktakingOrderInfoBO.setIsInventoryProfit(1);
       saveStocktakingOrderInfoBO.setRelativeId(saveStocktakingOrderInfoBO.getJobId());
        HttpResult result=stocktakingTaskServer.saveInventoryProfitInfo(saveStocktakingOrderInfoBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * PDA发起盘盈， **待修改
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 19:44
     **/
    @Override
    public Map<String, Object> pdaInventorySurplus(StocktakingTaskBO stocktakingTaskBO) {
            String orderId=stocktakingTaskBO.getOrderId();
            List<String> list=new ArrayList<String>();
            list.add(orderId);
            //请求订单详细信息
            BatchOrderIdListBO batch = new BatchOrderIdListBO();
    		batch.setKeyArea(stocktakingTaskBO.getPartnerArea());
    		batch.setOrderIds(list);
    		HttpResult httpResult = orderServer.getOrderDeatilByIdList(batch);
    		if(!ObjectUtils.isEmpty(httpResult.getData())){
    			BatchOrderDetailListPO batchOrderDetailListPO = gson.fromJson(gson.toJson(httpResult.getData()),BatchOrderDetailListPO.class);
    			List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
			    if(ObjectUtils.isEmpty(orderList) ){
	                return MsgTemplate.failureMsg(StocktakingMsgEnum.STOCKTAKING_ORDER_WRONG);
	            }
    	        List<WarehouseOrderDetailPO> joinOrderParamInfo =orderServer.joinOrderParamInfo(orderList);
    	        stocktakingTaskBO.setOrderDetail(joinOrderParamInfo.get(0));
    		}
            //获取相关订单库区库位信息,并拼接入返回信息中
    		//server压根就没有这个接口,此部分是多余代码?
//          HttpResult warehouseAreaAndLocResult=stocktakingTaskServer.pdaWarehouseAreaAndLocInfo(stocktakingTaskBO);
            return MsgTemplate.successMsg(stocktakingTaskBO);
    }

    /**
     * 修改版PDA发起盘盈,等pda拿回来测试无bug在更新
     * @author  wzy
     * @param
     * @return
     * @date  2018/2/3 12:28
     **/
    @Override
    public Map<String, Object> pdaInventorySurplus2(StocktakingTaskfBO stocktakingTaskBO) {
        String orderId=stocktakingTaskBO.getOrderId();
        List<String> list=new ArrayList<String>();
        list.add(orderId);
        OrderIdsBO orderIdsBO = new OrderIdsBO();
        orderIdsBO.setChildOrderIds(list);
        orderIdsBO.setPartnerArea(stocktakingTaskBO.getPartnerArea());
        //Http获取订单详细信息
        List<ChildOrderBO> childOrderList = orderServer.getChildOrderList(orderIdsBO);
        if(ObjectUtils.isEmpty(childOrderList) ){
            return MsgTemplate.failureMsg(StocktakingMsgEnum.STOCKTAKING_ORDER_WRONG);
        }
        //组装订单信息
        OrderInfoBO orderInfoBO=getOrderDetail(childOrderList);
        stocktakingTaskBO.setOrderDetail(orderInfoBO);
        return MsgTemplate.successMsg(stocktakingTaskBO);
    }

    /**
     * 暂存盘点结果,获取库存量，计算差异量
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 15:06
     **/
    @Override
    public Map<String, Object> saveStocktakingResult(SaveStocktakingOrderInfoList saveStocktakingOrderInfoList, PartnerInfoBO partnerInfoBO) {
        List<String> orderidlist=new ArrayList<String>();
        SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO=null;
        //获取所有订单列表
        for(int i=0;i<saveStocktakingOrderInfoList.getSaveStocktaking().size();i++){
            saveStocktakingOrderInfoBO=saveStocktakingOrderInfoList.getSaveStocktaking().get(i);
            orderidlist.add(saveStocktakingOrderInfoBO.getOrderId());
        }
        PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO=new PdaGetStocktakingOrderBO();
        pdaGetStocktakingOrderBO.setPartnerId(saveStocktakingOrderInfoList.getPartnerId());
        pdaGetStocktakingOrderBO.setJobId(saveStocktakingOrderInfoList.getJobId());
        pdaGetStocktakingOrderBO.setWarehouseId(saveStocktakingOrderInfoList.getWarehouseId());
        //Http获取订单列表
        HttpResult result=stocktakingTaskServer.stocktakingResultList(pdaGetStocktakingOrderBO);
        if(ObjectUtils.isEmpty(result.getData())){
            return MsgTemplate.successMsg();
        }
        JsonArray jsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
        List<SaveStocktakingOrderInfoBO> list=new ArrayList<SaveStocktakingOrderInfoBO>();
        for (JsonElement jsonElement : jsonArray){
            SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO1=gson.fromJson(jsonElement,SaveStocktakingOrderInfoBO.class);
            list.add(saveStocktakingOrderInfoBO1);
        }
        //遍历前台传来的
        for (SaveStocktakingOrderInfoBO foresave:saveStocktakingOrderInfoList.getSaveStocktaking()){
            //遍历后台获取的全部订单信息
            for (SaveStocktakingOrderInfoBO backgroundsave:list){
                //匹配同一库位的订单
                if(foresave.getIsInventoryProfit().equals(2)){
                if (foresave.getOrderId().equals(backgroundsave.getOrderId()) && foresave.getWarehouseAreaId().equals(backgroundsave.getWarehouseAreaId()) && foresave.getWarehouseLocId().equals(backgroundsave.getWarehouseLocId())){
                    //保存盘点数量
                    Integer takeStockAmount=null;
                    String remark=foresave.getRemark();
                    if(!ObjectUtils.isEmpty(foresave.getTakeStockAmount())){
                        takeStockAmount=foresave.getTakeStockAmount();
                    }
                    Integer isInventoryProfit=foresave.getIsInventoryProfit();
                    String warehouseId=foresave.getWarehouseId();
                    String warehouseName=foresave.getWarehouseName();
                    String warehouseAreaId=foresave.getWarehouseAreaId();
                    String warehouseAreaName=foresave.getWarehouseAreaName();
                    String warehouseLocId=foresave.getWarehouseLocId();
                    String warehouseLocName=foresave.getWarehouseLocName();
                    BeanUtils.copyProperties(backgroundsave,foresave,new String[] { "takeStockAmount","differenceValue"});
                    if(ObjectUtils.isEmpty(foresave.getTakeStockAmount())){
                        foresave.setStatus(StocktakingTaskConstant.STATUS_1);
                    }
                    else{
                        foresave.setStatus(StocktakingTaskConstant.STATUS_3);
                    }
                    foresave.setIsInventoryProfit(isInventoryProfit);
                    //计算差异量
                    if(!ObjectUtils.isEmpty(takeStockAmount)){
                        foresave.setDifferenceValue(takeStockAmount-backgroundsave.getInstockAmount());
                        foresave.setTakeStockAmount(takeStockAmount);
                    }
                    foresave.setWarehouseId(warehouseId);
                    foresave.setWarehouseName(warehouseName);
                    foresave.setWarehouseAreaId(warehouseAreaId);
                    foresave.setWarehouseAreaName(warehouseAreaName);
                    foresave.setWarehouseLocId(warehouseLocId);
                    foresave.setWarehouseLocName(warehouseLocName);
                    foresave.setPartnerId(partnerInfoBO.getPartnerId());
                    foresave.setOperatorId(partnerInfoBO.getOperatorId());
                    foresave.setOperator(partnerInfoBO.getOperator());
                    foresave.setRelativeId(saveStocktakingOrderInfoList.getJobId());
                    foresave.setRemark(remark);
                }
                }
            }
        }
        //赋值盘盈的订单
        //Http获取订单详细信息
        OrderIdsBO orderIdsBO = new OrderIdsBO();
        orderIdsBO.setChildOrderIds(orderidlist);
        orderIdsBO.setPartnerArea(partnerInfoBO.getPartnerArea());
        List<ChildOrderBO> childOrderList = orderServer.getChildOrderList(orderIdsBO);
        for (SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO3:saveStocktakingOrderInfoList.getSaveStocktaking()){
            if (saveStocktakingOrderInfoBO3.getIsInventoryProfit().equals(1)){
                Optional optional= childOrderList.stream()
                        .filter(b -> b.getChildOrderId().equals(saveStocktakingOrderInfoBO3.getOrderId()))
                        .findFirst();
                if(optional.isPresent()){
                    ChildOrderBO forderInfoBO=(ChildOrderBO) optional.get();
                    saveStocktakingOrderInfoBO3.setPartnerId(partnerInfoBO.getPartnerId());
                    saveStocktakingOrderInfoBO3.setOperatorId(partnerInfoBO.getOperatorId());
                    saveStocktakingOrderInfoBO3.setOperator(partnerInfoBO.getOperator());
                    saveStocktakingOrderInfoBO3.setRelativeId(saveStocktakingOrderInfoList.getJobId());
                    saveStocktakingOrderInfoBO3.setInstockAmount(0);
                    saveStocktakingOrderInfoBO3.setDifferenceValue(saveStocktakingOrderInfoBO3.getTakeStockAmount());
                    BeanUtils.copyProperties(forderInfoBO, saveStocktakingOrderInfoBO3);
                }
            }
        }
        HttpResult saveResult= stocktakingTaskServer.saveStocktakingResult(saveStocktakingOrderInfoList);
        if (saveResult.isSuccess()){
            UpdateStocktakingTaskBO updateStocktakingTaskBO=new UpdateStocktakingTaskBO();
            updateStocktakingTaskBO.setJobId(saveStocktakingOrderInfoList.getJobId());
            updateStocktakingTaskBO.setPartnerId(saveStocktakingOrderInfoList.getPartnerId());
            updateStocktakingTaskBO.setStatus(StocktakingTaskConstant.WORKING);
            Map<String,Object> upmap=updateTaskState(updateStocktakingTaskBO);
        }
        return MsgTemplate.customMsg(saveResult);
    }
    /**
     * 计算盘点完成后盘点任务对应盘点订单的总面积
     * @param saveStocktakingOrderInfoBOList
     * @return
     */
  public double area(SaveStocktakingOrderInfoList param) {
      double areaSum = 0;
      OrderIdsBO orderIdsBO = new OrderIdsBO();
      List<String> orderIds = new ArrayList<>();
      if(!ObjectUtils.isEmpty(param.getSaveStocktaking())) {
      for(SaveStocktakingOrderInfoBO stockIfo : param.getSaveStocktaking()) {
          orderIds.add(stockIfo.getOrderId());
      }
      }
      
      orderIdsBO.setChildOrderIds(orderIds);
      orderIdsBO.setPartnerArea(param.getPartnerArea());
    //根据订单编号获取订单信息
      BatchOrderDetailListPO orderInfo = orderServer.getOrderOrSplitOrder(orderIdsBO);
      if(!ObjectUtils.isEmpty(orderInfo.getSplitOrderList())) {
      orderInfo.getOrderList().addAll(orderInfo.getSplitOrderList());
      }
      //遍历页面传输订单号
      for(SaveStocktakingOrderInfoBO info : param.getSaveStocktaking()) {
          if(!ObjectUtils.isEmpty(orderInfo.getOrderList())) {
              //遍历从订单服务查寻对应订单信息
          for(WarehouseOrderDetailPO orderList : orderInfo.getOrderList()) {
              if(info.getOrderId().equals(orderList.getChildOrderId())) {
                  //计算操作面积
                  double area = operationRecordServer.getVolume(Double.parseDouble(orderList.getMaterialLength()), Double.parseDouble(orderList.getMaterialWidth()), info.getTakeStockAmount());
                  areaSum = areaSum +area;
              }
          }
          }
          
         
      }
      return areaSum;
    
  }

  /**
   * 订单盘点操作记录数据
   * @param param
   */
  public List<OrderOperationRecordPO> orderStocktakingOperationInfo(SaveStocktakingOrderInfoList param,PartnerInfoBO partnerInfoBO) {
      List<OrderOperationRecordPO> list = new ArrayList<>();
     
      OrderIdsBO orderIdsBO = new OrderIdsBO();
      List<String> orderIds = new ArrayList<>();
      if(!ObjectUtils.isEmpty(param.getSaveStocktaking())) {
      for(SaveStocktakingOrderInfoBO stockIfo : param.getSaveStocktaking()) {
          orderIds.add(stockIfo.getOrderId());
      }
      }
      
      orderIdsBO.setChildOrderIds(orderIds);
      orderIdsBO.setPartnerArea(param.getPartnerArea());
    //根据订单编号获取订单信息
      BatchOrderDetailListPO orderInfo = orderServer.getOrderOrSplitOrder(orderIdsBO);
      List<WarehouseOrderDetailPO> OrderList = new ArrayList<WarehouseOrderDetailPO>();
      if(!ObjectUtils.isEmpty(orderInfo.getOrderList())) {
          OrderList.addAll(orderInfo.getOrderList());
      }
      if(!ObjectUtils.isEmpty(orderInfo.getSplitOrderList())) {
          OrderList.addAll(orderInfo.getSplitOrderList());
      }
      //遍历页面传输订单号
      for(SaveStocktakingOrderInfoBO info : param.getSaveStocktaking()) {
          if(!ObjectUtils.isEmpty(OrderList)) {
              //遍历从订单服务查寻对应订单信息
          for(WarehouseOrderDetailPO orderList : OrderList) {
              if(info.getOrderId().equals(orderList.getChildOrderId())) {
                  OrderOperationRecordPO orderOperationRecordPO = new OrderOperationRecordPO();
                  orderOperationRecordPO.setPartnerId(partnerInfoBO.getPartnerId());
                  orderOperationRecordPO.setPartnerArea(partnerInfoBO.getPartnerArea());
                  orderOperationRecordPO.setOperator(partnerInfoBO.getOperator());
                  orderOperationRecordPO.setOperatorId(partnerInfoBO.getOperatorId());
                //订单类型后面判断TODO
                  orderOperationRecordPO.setOrderType("2");
                //处理数据
                  orderOperationRecordPO.setRelativeName(orderList.getProductName());
                  orderOperationRecordPO.setRelativeId(info.getOrderId());
                  orderOperationRecordPO.setAmount(info.getTakeStockAmount().toString());
                  orderOperationRecordPO.setFluteType(FluteTypeEnum1.getCode(orderList.getFluteType()));
                  //计算操作面积
                  double area = operationRecordServer.getVolume(Double.parseDouble(orderList.getMaterialLength()), Double.parseDouble(orderList.getMaterialWidth()), info.getTakeStockAmount());
                  orderOperationRecordPO.setArea(String.valueOf(area));
                  list.add(orderOperationRecordPO);
              }
          }
          }
          
         
      }
      return list;
}
    /**
     * 完成盘点
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/26 11:51
     **/
    @Override
    public Map<String, Object> completeStocktakingTask(SaveStocktakingOrderInfoList saveStocktakingOrderInfoBOList,PartnerInfoBO partnerInfoBO) {
        //盘点订单操作记录数据组合
        List<OrderOperationRecordPO> orderOperationInfo = orderStocktakingOperationInfo(saveStocktakingOrderInfoBOList,partnerInfoBO);
        saveStocktakingOrderInfoBOList.setOrderOperationInfo(orderOperationInfo);
        //盘点任务操作记录数据组合
        TaskOperationRecordPO taskOperationRecordPO = new TaskOperationRecordPO();
        double area = area(saveStocktakingOrderInfoBOList);
        taskOperationRecordPO.setArea(String.valueOf(area));
        saveStocktakingOrderInfoBOList.setTaskOperationRecordPO(taskOperationRecordPO);
        List<String> orderidlist=new ArrayList<String>();
        SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO=null;
        String jobId=saveStocktakingOrderInfoBOList.getJobId();
        //获取所有订单列表
        for(int i=0;i<saveStocktakingOrderInfoBOList.getSaveStocktaking().size();i++){
            saveStocktakingOrderInfoBO=saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i);
            orderidlist.add(saveStocktakingOrderInfoBO.getOrderId());
        }
        //校验是否盘点完毕
        for (SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO1:saveStocktakingOrderInfoBOList.getSaveStocktaking()){
            if (ObjectUtils.isEmpty(saveStocktakingOrderInfoBO1.getTakeStockAmount())){
                return MsgTemplate.successMsg(StocktakingTaskConstant.NOTCOMPLETE);
            }
        }
        OrderIdsBO orderIdsBO = new OrderIdsBO();
        orderIdsBO.setChildOrderIds(orderidlist);
        orderIdsBO.setPartnerArea(partnerInfoBO.getPartnerArea());
        //Http获取订单详细信息
        List<ChildOrderBO> childOrderList = orderServer.getChildOrderList(orderIdsBO);
        PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO1=new PdaGetStocktakingOrderBO();
        pdaGetStocktakingOrderBO1.setPartnerId(saveStocktakingOrderInfoBOList.getPartnerId());
        pdaGetStocktakingOrderBO1.setJobId(saveStocktakingOrderInfoBOList.getJobId());
        pdaGetStocktakingOrderBO1.setWarehouseId(saveStocktakingOrderInfoBOList.getWarehouseId());
        //Http获取订单列表
        HttpResult stocktakingResult=stocktakingTaskServer.stocktakingResultList(pdaGetStocktakingOrderBO1);
        if(ObjectUtils.isEmpty(stocktakingResult.getData())){
            return MsgTemplate.successMsg(null);
        }
        JsonArray jsonArray = new JsonParser().parse(gson.toJson(stocktakingResult.getData())).getAsJsonArray();
        List<SaveStocktakingOrderInfoBO> orderResultlist=new ArrayList<SaveStocktakingOrderInfoBO>();
        for (JsonElement jsonElement : jsonArray){
            SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO2=new SaveStocktakingOrderInfoBO();
            saveStocktakingOrderInfoBO2=gson.fromJson(jsonElement,SaveStocktakingOrderInfoBO.class);
            orderResultlist.add(saveStocktakingOrderInfoBO2);
        }
        //遍历前台传来的
        for (SaveStocktakingOrderInfoBO foresave:saveStocktakingOrderInfoBOList.getSaveStocktaking()){
            //遍历后台获取的全部订单信息
            for (SaveStocktakingOrderInfoBO backgroundsave:orderResultlist) {
                //是盘盈
                if (foresave.getIsInventoryProfit().equals(StocktakingTaskConstant.ISADD_SURPLUS)) {
                    if (foresave.getOrderId().equals(backgroundsave.getOrderId()) && foresave.getWarehouseAreaId().equals(backgroundsave.getWarehouseAreaId()) && foresave.getWarehouseLocId().equals(backgroundsave.getWarehouseLocId())) {
                        //保存盘点数量
                        Integer takeStockAmount = null;
                        String remark = foresave.getRemark();
                        if (!ObjectUtils.isEmpty(foresave.getTakeStockAmount())) {
                            takeStockAmount = foresave.getTakeStockAmount();
                        }
                        Integer isInventoryProfit = foresave.getIsInventoryProfit();
                        String warehouseId = foresave.getWarehouseId();
                        String warehouseName = foresave.getWarehouseName();
                        String warehouseAreaId = foresave.getWarehouseAreaId();
                        String warehouseAreaName = foresave.getWarehouseAreaName();
                        String warehouseLocId = foresave.getWarehouseLocId();
                        String warehouseLocName = foresave.getWarehouseLocName();
                        //拷贝规格参数
                        BeanUtils.copyProperties(backgroundsave, foresave);
                        foresave.setIsInventoryProfit(isInventoryProfit);
                        //计算差异量
                        if (takeStockAmount != null) {
                            foresave.setTakeStockAmount(takeStockAmount);
                            foresave.setDifferenceValue(takeStockAmount - backgroundsave.getInstockAmount());
                        }
                        foresave.setWarehouseId(warehouseId);
                        foresave.setWarehouseName(warehouseName);
                        foresave.setWarehouseAreaId(warehouseAreaId);
                        foresave.setWarehouseAreaName(warehouseAreaName);
                        foresave.setWarehouseLocId(warehouseLocId);
                        foresave.setWarehouseLocName(warehouseLocName);
                        foresave.setPartnerId(partnerInfoBO.getPartnerId());
                        foresave.setOperatorId(partnerInfoBO.getOperatorId());
                        foresave.setOperator(partnerInfoBO.getOperator());
                        foresave.setRelativeId(saveStocktakingOrderInfoBOList.getJobId());
                        foresave.setRemark(remark);
                    }
                }
            }
        }

        //赋值盘盈的订单
        for (SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO3:saveStocktakingOrderInfoBOList.getSaveStocktaking()){
            if (saveStocktakingOrderInfoBO3.getIsInventoryProfit().equals(1)){
                Optional optional= childOrderList.stream()
                        .filter(b -> b.getChildOrderId().equals(saveStocktakingOrderInfoBO3.getOrderId()))
                        .findFirst();
                if(optional.isPresent()){
                    ChildOrderBO forderInfoBO=(ChildOrderBO) optional.get();
                    saveStocktakingOrderInfoBO3.setPartnerId(partnerInfoBO.getPartnerId());
                    saveStocktakingOrderInfoBO3.setOperatorId(partnerInfoBO.getOperatorId());
                    saveStocktakingOrderInfoBO3.setOperator(partnerInfoBO.getOperator());
                    saveStocktakingOrderInfoBO3.setRelativeId(saveStocktakingOrderInfoBOList.getJobId());
                    saveStocktakingOrderInfoBO3.setInstockAmount(0);
                    saveStocktakingOrderInfoBO3.setDifferenceValue(saveStocktakingOrderInfoBO3.getTakeStockAmount());
                    BeanUtils.copyProperties(saveStocktakingOrderInfoBO3, forderInfoBO);
                }
            }
        }

        //添加是否在作业
        for (SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO1:saveStocktakingOrderInfoBOList.getSaveStocktaking()){
            if (ObjectUtils.isEmpty(saveStocktakingOrderInfoBO1.getTakeStockAmount())){
                saveStocktakingOrderInfoBO1.setStatus(StocktakingTaskConstant.STATUS_1);
            }
            else{
                saveStocktakingOrderInfoBO1.setStatus(StocktakingTaskConstant.STATUS_3);
            }
        }
        //异常订单逻辑
        //TODO 获取异常订单collectList
        List<SaveStocktakingOrderInfoBO> collectList = saveStocktakingOrderInfoBOList.getSaveStocktaking().stream()
                .filter(b -> b.getDifferenceValue()!=0)
                .collect(Collectors.toList());
        collectList.stream().forEach(orderInfoBO->{
                    childOrderList.stream().forEach(childOrderBO -> {
                        if(orderInfoBO.getOrderId().equals(childOrderBO.getOrderId())){
                            orderInfoBO.setPartnerName(saveStocktakingOrderInfoBOList.getPartnerName());
                            orderInfoBO.setPartnerArea(saveStocktakingOrderInfoBOList.getPartnerArea());
                            orderInfoBO.setOrderAmount(childOrderBO.getOrderAmount());
                            orderInfoBO.setCustomerName(childOrderBO.getCustomerName());
                        }
                    });
                });
            //存在异常订单
        	List<String> orderIdList = new ArrayList();
            if (!ObjectUtils.isEmpty(collectList)){
                OrderIdListBO orderIdListBO = new OrderIdListBO();
                BeanUtils.copyProperties(saveStocktakingOrderInfoBOList, orderIdListBO);
                List<String> orderidList=new ArrayList<>();
                collectList.stream().forEach(saveSObo -> {
                    orderidList.add(saveSObo.getOrderId());
                });
                orderIdListBO.setList(orderidList);
                //查询是否有异常订单
                HttpResult orderResult= abnormalServer.getOrderByOrderIdList(orderIdListBO);
                //TODO 已存在异常订单执行更新和插入
                if(!ObjectUtils.isEmpty(orderResult.getData())){
                    String data = gson.toJson(orderResult.getData());
                    List<AbnormalOrderPO> abnormalOrderPOList=  JSONArray.parseArray(data, AbnormalOrderPO.class);
                    abnormalOrderPOList.stream().filter(abnormalOrderPO ->
                        !ObjectUtils.isEmpty(abnormalOrderPO)
                    ).forEach(abnormalOrderPO -> {
                        collectList.stream().forEach(allAbnormalOrder->{
                            //更新异常订单
                           if(abnormalOrderPO.getOrderId().equals(allAbnormalOrder.getOrderId())){
                               StringBuffer reson=new StringBuffer();
                               Integer surplusOrderAmount=0;
                               if (allAbnormalOrder.getDifferenceValue()>0){
                                   surplusOrderAmount= allAbnormalOrder.getDifferenceValue();
                                   reson.append(AbnormalConstant.ABNORMAL_ERROR_MORE).append(surplusOrderAmount);
                               }else{
                                   surplusOrderAmount= -allAbnormalOrder.getDifferenceValue();
                                   reson.append(AbnormalConstant.ABNORMAL_ERROR_REASON).append(surplusOrderAmount);
                               }
                               UpdateAbnormalBO updateOrderBO = new UpdateAbnormalBO();
                               BeanUtils.copyProperties(allAbnormalOrder, updateOrderBO,"remark","status");
                               updateOrderBO.setOrderId(allAbnormalOrder.getOrderId());
                               updateOrderBO.setAbnomalAmount(""+surplusOrderAmount+"");
                               updateOrderBO.setReason(reson.toString());
                               updateOrderBO.setSubmiter(allAbnormalOrder.getOperator());
                               updateOrderBO.setResult(null);
                               updateOrderBO.setRemark(null);
                               updateOrderBO.setSubmitTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                               updateOrderBO.setStatus(0);
                               HttpResult abnormalResult = abnormalServer.updateAbnormal(updateOrderBO);
                           }
                           //插入异常订单
                           else{
                               StringBuffer reson=new StringBuffer();
                               Integer surplusOrderAmount=0;
                               if (allAbnormalOrder.getDifferenceValue()>0){
                                   surplusOrderAmount= allAbnormalOrder.getDifferenceValue();
                                   reson.append(AbnormalConstant.ABNORMAL_ERROR_MORE).append(surplusOrderAmount);
                               }else{
                                   surplusOrderAmount= -allAbnormalOrder.getDifferenceValue();
                                   reson.append(AbnormalConstant.ABNORMAL_ERROR_REASON).append(surplusOrderAmount);
                               }
                               //直接插入异常订单数据
                               AddAbnormal addAbnormal = new AddAbnormal();
                               BeanUtils.copyProperties(allAbnormalOrder, addAbnormal,"remark");
                               addAbnormal.setLink(AbnormalConstant.ABNORMAL_LINK_ADD_STOCKTAKING);
                               addAbnormal.setReason(reson.toString());
                               addAbnormal.setAbnomalAmount(surplusOrderAmount);
                               addAbnormal.setCustomerName(allAbnormalOrder.getCustomerName());
                               addAbnormal.setProductName(allAbnormalOrder.getProductName());
                               addAbnormal.setIsSplit(0);
                               addAbnormal.setAmount(allAbnormalOrder.getOrderAmount());
                               HttpResult addResult = abnormalServer.addAbnormal(addAbnormal);
                               //将异常的订单号存入list
                               orderIdList.add(addAbnormal.getOrderId());
                           }
                        });
                    });
                }
                //TODO 不存在异常订单信息全部执行插入
                else{
                    collectList.stream().forEach(s-> {
                        StringBuffer reson=new StringBuffer();
                        Integer surplusOrderAmount=0;
                        if (s.getDifferenceValue()>0){
                             surplusOrderAmount= s.getDifferenceValue();
                            reson.append(AbnormalConstant.ABNORMAL_ERROR_MORE).append(surplusOrderAmount);
                        }else{
                             surplusOrderAmount= -s.getDifferenceValue();
                            reson.append(AbnormalConstant.ABNORMAL_ERROR_REASON).append(surplusOrderAmount);
                        }
                        //直接插入异常订单数据
                        AddAbnormal addAbnormal = new AddAbnormal();
                        BeanUtils.copyProperties(s, addAbnormal,"remark");
                        addAbnormal.setLink(AbnormalConstant.ABNORMAL_LINK_ADD_STOCKTAKING);
                        addAbnormal.setReason(reson.toString());
                        addAbnormal.setAbnomalAmount(surplusOrderAmount);
                        addAbnormal.setCustomerName(s.getCustomerName());
                        addAbnormal.setProductName(s.getProductName());
                        addAbnormal.setIsSplit(0);
                        addAbnormal.setAmount(s.getOrderAmount());
                        HttpResult addResult = abnormalServer.addAbnormal(addAbnormal);
                        //将异常的订单号存入list
                        orderIdList.add(addAbnormal.getOrderId());
                    });
                }
            }
        HttpResult saveresult= stocktakingTaskServer.completeStocktakingTask(saveStocktakingOrderInfoBOList);
        if(saveresult.isSuccess()){
        	//修改oms异常订单的标记
        	HttpResult addAbnormalResult = abnormalServer.updateExecptionFlag(1,orderIdList,partnerInfoBO.getPartnerArea());
            if(!addAbnormalResult.isSuccess()){
         	   return MsgTemplate.failureMsg(AbnormalMsgEnum.STOCK_UPDATE_SPLIT_ORDER_STATUS_ERROR);
            }else{
            	 return MsgTemplate.customMsg(addAbnormalResult);
            }
        }
        return MsgTemplate.customMsg(saveresult);
    }
    /**
     * 获取全部盘点任务列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 16:33
     **/
    @Override
    public Map<String, Object> stocktakingTaskList(GetStocktakingTaskBO getStocktakingTaskBO) {
        OrderResult orderResult=stocktakingTaskServer.stocktakingTaskList(getStocktakingTaskBO);
        if(ObjectUtils.isEmpty(orderResult.getData())){
            return MsgTemplate.successMsg();
        }
        //因为这里返回的参数比较特殊所以需要重新自己组织对象,不调用方法
        InnerDate innerDate=new InnerDate();
        innerDate.setTotal(orderResult.getData().getTotal());
        innerDate.setResult(orderResult.getData().getResult());
        return MsgTemplate.successMsg(innerDate);
    }

    /**
     * 条件获取盘点任务列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 16:35
     **/
    @Override
    public Map<String, Object> searchTaskList(GetStocktakingTaskBO getStocktakingTaskBO) {
        OrderResult orderResult=stocktakingTaskServer.searchTaskList(getStocktakingTaskBO);
        InnerDate innerDate=new InnerDate();
        innerDate.setTotal(orderResult.getData().getTotal());
        innerDate.setResult(orderResult.getData().getResult());
        return MsgTemplate.successMsg(innerDate);
    }

    /**
     * PDA请求盘点任务列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 14:01
     **/
    @Override
    public Map<String, Object> pdaStocktakingTaskList(PdaStocktakingTaskBO pdaStocktakingTaskBO) {
        
        HttpResult result=stocktakingTaskServer.pdaStocktakingTaskList(pdaStocktakingTaskBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        // data数据为空将值赋值为null,这里取到的是空数组
        if(ObjectUtils.isEmpty(result.getData())){
            return MsgTemplate.successMsg(null);
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * PDA请求盘点任务订单列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 14:51
     **/
    @Override
    public Map<String, Object> pdaStocktakingOrderList(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO) {
        HttpResult result=stocktakingTaskServer.pdaStocktakingOrderList(pdaGetStocktakingOrderBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        // data数据为空将值赋值为null,这里取到的是空数组
        if(ObjectUtils.isEmpty(result.getData())){
            return MsgTemplate.successMsg();
        }
        JsonArray asJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
        List<PdaStocktakingOrderBO> pdaorderlist=new ArrayList<PdaStocktakingOrderBO>();
        //订单参数拼接
        for (JsonElement jsonElement:asJsonArray){
            PdaStocktakingOrderBO pdaStocktakingOrderBO=gson.fromJson(jsonElement,PdaStocktakingOrderBO.class);
            pdaStocktakingOrderBO.setMaterialSize(new StringBuffer().append(pdaStocktakingOrderBO.getMaterialLength()).append("*")
                    .append(pdaStocktakingOrderBO.getMaterialWidth()).toString());
            pdaorderlist.add(pdaStocktakingOrderBO);
        }
        List<PdaStocktakingOrderBO> pdaorderlist2=new ArrayList<PdaStocktakingOrderBO>();
        //排序
        for(PdaStocktakingOrderBO pdaStocktakingOrderBO:pdaorderlist){
            if(pdaStocktakingOrderBO.getStatus().equals(StocktakingTaskConstant.STATUS_1)){
                pdaorderlist2.add(pdaStocktakingOrderBO);
            }
        }
        for(PdaStocktakingOrderBO pdaStocktakingOrderBO:pdaorderlist) {
            if (StocktakingTaskConstant.INVENTORY_IS.equals(pdaStocktakingOrderBO.getIsInventoryProfit()) && pdaStocktakingOrderBO.getStatus().equals(StocktakingTaskConstant.STATUS_3)) {
                pdaorderlist2.add(pdaStocktakingOrderBO);
            }
        }
        for(PdaStocktakingOrderBO pdaStocktakingOrderBO:pdaorderlist){
            if (StocktakingTaskConstant.INVENTORY_NORMAL.equals(pdaStocktakingOrderBO.getIsInventoryProfit()) && pdaStocktakingOrderBO.getStatus().equals(StocktakingTaskConstant.STATUS_3)) {
                pdaorderlist2.add(pdaStocktakingOrderBO);
            }
        }
        return MsgTemplate.successMsg(pdaorderlist2);
    }

    /**
     * PDA请求订单详情
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 15:50
     **/
    @Override
    public Map<String, Object> pdaStocktakingOrderInfo(PdaStocktakingOrderInfo pdaStocktakingOrderInfo) {
        //判断是否存在此单号
        List<String> orderidlist=new ArrayList<String>();
        //Map<String,List<String>> checkmap=new HashMap<String,List<String>>();
        orderidlist.add(pdaStocktakingOrderInfo.getOrderId());
        OrderIdsBO orderIdsBO = new OrderIdsBO();
        orderIdsBO.setChildOrderIds(orderidlist);
        orderIdsBO.setPartnerArea(pdaStocktakingOrderInfo.getPartnerArea());
        //获取批量订单信息列表,判断是不是作业单号写错了
        List<ChildOrderBO> childOrderList = orderServer.getChildOrderList(orderIdsBO);
        if(ObjectUtils.isEmpty(childOrderList) ){
            return MsgTemplate.failureMsg(StocktakingMsgEnum.STOCKTAKING_ORDER_WRONG);
        }

        HttpResult result=stocktakingTaskServer.pdaStocktakingOrderInfo(pdaStocktakingOrderInfo);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        if(ObjectUtils.isEmpty(result.getData())){
            return MsgTemplate.successMsg(null);
        }
        JsonObject jsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonObject();
        PdaOderInfoBO pdaOderInfoBO=gson.fromJson(jsonArray,PdaOderInfoBO.class);
        pdaOderInfoBO.setProductSize(new StringBuffer().append(pdaOderInfoBO.getBoxLength()).append("*")
                .append(pdaOderInfoBO.getBoxWidth()).append("*").append(pdaOderInfoBO.getBoxHeight()).toString());
        pdaOderInfoBO.setMaterialSize(new StringBuffer().append(pdaOderInfoBO.getMaterialLength()).append("*")
                .append(pdaOderInfoBO.getMaterialWidth()).toString());
        return MsgTemplate.successMsg(pdaOderInfoBO);
    }
    /**
     * 订单盘点操作记录数据
     * @param param
     */
    public List<OrderOperationRecordPO> pdaOrderStocktakingOperationInfo(SaveStocktakingOrderInfoBO param) {
        List<OrderOperationRecordPO> list = new ArrayList<>();
        OrderOperationRecordPO orderOperationRecordPO = new OrderOperationRecordPO();
        orderOperationRecordPO.setPartnerId(param.getPartnerId());
        orderOperationRecordPO.setPartnerArea(param.getPartnerArea());
        orderOperationRecordPO.setOperator(param.getOperator());
        orderOperationRecordPO.setOperatorId(param.getOperatorId());
      //订单类型后面判断TODO
        orderOperationRecordPO.setOrderType("2");
        //处理数据
        orderOperationRecordPO.setRelativeName(param.getProductName());
        orderOperationRecordPO.setRelativeId(param.getOrderId());
        orderOperationRecordPO.setAmount(param.getTakeStockAmount().toString());
        orderOperationRecordPO.setFluteType(FluteTypeEnum1.getCode(param.getFluteType()));
        //计算操作面积
        double area = operationRecordServer.getVolume(Double.parseDouble(param.getMaterialLength()), Double.parseDouble(param.getMaterialWidth()), param.getTakeStockAmount());
        orderOperationRecordPO.setArea(String.valueOf(area));
        list.add(orderOperationRecordPO);
        return list;
  }
    /**
     * PDA保存盘点结果
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 16:17
     **/
    @Override
    public Map<String, Object> savePdaStocktakingResult(SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO) {
        List<OrderOperationRecordPO> info = pdaOrderStocktakingOperationInfo(saveStocktakingOrderInfoBO);
        saveStocktakingOrderInfoBO.setList(info);
    	saveStocktakingOrderInfoBO.setFluteType(StringUtils.switchFluteTypeToNumber(saveStocktakingOrderInfoBO.getFluteType()));
    	List<String> orderidlist=new ArrayList<String>();
        //获取所有订单列表
        orderidlist.add(saveStocktakingOrderInfoBO.getOrderId());

        OrderIdsBO orderIdsBO = new OrderIdsBO();
        orderIdsBO.setChildOrderIds(orderidlist);
        orderIdsBO.setPartnerArea(saveStocktakingOrderInfoBO.getPartnerArea());
        //TODO 获取订单信息
        List<ChildOrderBO> childOrderList = orderServer.getChildOrderList(orderIdsBO);
        ChildOrderBO childOrderBO=childOrderList.get(0);
        saveStocktakingOrderInfoBO.setMaterialName(childOrderBO.getMaterialName());
        saveStocktakingOrderInfoBO.setMateriaFid(childOrderBO.getMateriaFid());
        saveStocktakingOrderInfoBO.setRelativeId(saveStocktakingOrderInfoBO.getJobId());
        if(ObjectUtils.isEmpty(saveStocktakingOrderInfoBO.getTakeStockAmount())){
            saveStocktakingOrderInfoBO.setStatus(StocktakingTaskConstant.STATUS_1);
        }
        else{
            saveStocktakingOrderInfoBO.setStatus(StocktakingTaskConstant.STATUS_3);
        }
        //TODO 是正常新增时做校验
        if (saveStocktakingOrderInfoBO.getIsAdd().equals(StocktakingTaskConstant.ISADD_NEW)){
            //判断新增订单的库区库位是否已在盘点任务列表中
            PdaStocktakingOrderInfo pdaStocktakingOrderInfo=new PdaStocktakingOrderInfo();
            pdaStocktakingOrderInfo.setJobId(saveStocktakingOrderInfoBO.getJobId());
            pdaStocktakingOrderInfo.setOrderId(saveStocktakingOrderInfoBO.getOrderId());
            pdaStocktakingOrderInfo.setPartnerId(saveStocktakingOrderInfoBO.getPartnerId());
            HttpResult pdaStocktakingOrder=stocktakingTaskServer.pdaStocktakingOrderInfo(pdaStocktakingOrderInfo);
            //若该订单已存在
            if(!ObjectUtils.isEmpty(pdaStocktakingOrder.getData())){
                JsonObject pdaOrderJsonArray = new JsonParser().parse(gson.toJson(pdaStocktakingOrder.getData())).getAsJsonObject();
                PdaOderInfoBO pdaOderInfoBO=gson.fromJson(pdaOrderJsonArray,PdaOderInfoBO.class);
                for (WarehouseAreaAndLocBO warehouseAreaAndLocBO:pdaOderInfoBO.getOrderAreaAndLocList()){
                    if (saveStocktakingOrderInfoBO.getWarehouseAreaId().equals(warehouseAreaAndLocBO.getWarehouseAreaId()) && saveStocktakingOrderInfoBO.getWarehouseLocId().equals(warehouseAreaAndLocBO.getWarehouseLocId())){
                        //该新增订单已存在
                        return MsgTemplate.failureMsg(StocktakingMsgEnum.STOCKTAKING_ORDER_EXIST);
                    }
                }
            }
            //将楞型转换成枚举
            HttpResult result=stocktakingTaskServer.savePdaStocktakingResult(saveStocktakingOrderInfoBO);
            if(!result.isSuccess()){
                HttpResult otherResult = new HttpResult();
                BeanUtils.copyProperties(result, otherResult);
                return MsgTemplate.customMsg(otherResult);
            }
            return MsgTemplate.customMsg(result);
        }

        //TODO 正常保存和盘盈时
        GetAmountBO getAmountBO=new GetAmountBO();
        getAmountBO.setOrderId(saveStocktakingOrderInfoBO.getOrderId());
        getAmountBO.setWarehouseAreaId(saveStocktakingOrderInfoBO.getWarehouseAreaId());
        getAmountBO.setWarehouseLocId(saveStocktakingOrderInfoBO.getWarehouseLocId());
        getAmountBO.setPartnerId(saveStocktakingOrderInfoBO.getPartnerId());
        //获取在库数量,计算差异量
        HttpResult amountResult=stocktakingTaskServer.getAmount(getAmountBO);
        if(!ObjectUtils.isEmpty(amountResult.getData())){
            //数字格式化在库数量
            int trueAmount = Integer.parseInt(new java.text.DecimalFormat("0").format(amountResult.getData()));
            saveStocktakingOrderInfoBO.setInstockAmount(trueAmount);
            saveStocktakingOrderInfoBO.setDifferenceValue(trueAmount-saveStocktakingOrderInfoBO.getTakeStockAmount());
        }
        //保存盘点结果
        HttpResult result=stocktakingTaskServer.savePdaStocktakingResult(saveStocktakingOrderInfoBO);
        if(!result.isSuccess()){
            return MsgTemplate.customMsg(result);
        }
        else{
            UpdateStocktakingTaskBO updateStocktakingTaskBO=new UpdateStocktakingTaskBO();
            updateStocktakingTaskBO.setPartnerId(saveStocktakingOrderInfoBO.getPartnerId());
            updateStocktakingTaskBO.setJobId(saveStocktakingOrderInfoBO.getJobId());
            updateStocktakingTaskBO.setStatus(StocktakingTaskConstant.WORKING);
            updateStocktakingTaskBO.setPdaStatus(StocktakingTaskConstant.PDASTATUS_2);
            updateStocktakingTaskBO.setWarehouseId(saveStocktakingOrderInfoBO.getWarehouseId());
            Map<String,Object> upmap=updateTaskState(updateStocktakingTaskBO);
        }
        saveOperationRecord(saveStocktakingOrderInfoBO);
        return MsgTemplate.customMsg(result);
    }
    /**
     * 判断是否生成盘点开始操作记录
     * @author  wyb
     * @param
     * @return
     * @create  2018/3/7
     **/
    public void saveOperationRecord(SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO){
        List<String> list=new ArrayList<String>();
        list.add(StockTakingOperationConstant.START_INVENTORY_TASK);
        StocktakingRecordListBO stocktakingRecordListBO=new StocktakingRecordListBO();
        stocktakingRecordListBO.setJobId(saveStocktakingOrderInfoBO.getJobId());
        stocktakingRecordListBO.setList(list);
        SaveOperationRecordBO saveOperationRecordBO =new SaveOperationRecordBO();
        saveOperationRecordBO.setRelativeId(saveStocktakingOrderInfoBO.getJobId());
        saveOperationRecordBO.setOperationType(StockTakingOperationConstant.START_INVENTORY_TASK);
        saveOperationRecordBO.setOperator(saveStocktakingOrderInfoBO.getOperator());
        saveOperationRecordBO.setOperatorId(saveStocktakingOrderInfoBO.getOperatorId());
        saveOperationRecordBO.setEvent(StockTakingOperationRecordUtil.getStartEvent());
        if(ObjectUtils.isEmpty(operationRecordServer.stocktakingRecordList(stocktakingRecordListBO))) {
            operationRecordServer.saveOperationRecord(saveOperationRecordBO);
        }
    }
    /**
     * 未完成返回盘点数
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 16:57
     **/
    @Override
    public Map<String, Object> getOrderAmount(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO) {
        //获取在库数量，计算差异量
        HttpResult result=stocktakingTaskServer.getOrderAmount(pdaGetStocktakingOrderBO);
        if(ObjectUtils.isEmpty(result.getData())){
            return MsgTemplate.successMsg(null);
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * PDA完成盘点更新状态
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 15:35
     **/
    @Override
    public Map<String, Object> pdaCompleteStocktaking(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO) {
        pdaGetStocktakingOrderBO.setPdaStatus(StocktakingTaskConstant.PDASTATUS_3);
        pdaGetStocktakingOrderBO.setStatus(StocktakingTaskConstant.WORKING);
        HttpResult result=stocktakingTaskServer.pdaCompleteStocktaking(pdaGetStocktakingOrderBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * 查看盘点结果列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/14 13:44
     **/
    @Override
    public Map<String, Object> stocktakingResultList(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO) {
        HttpResult result=stocktakingTaskServer.stocktakingResultList(pdaGetStocktakingOrderBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        JsonArray jsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();

        List<SaveStocktakingOrderInfoBO> list=new ArrayList<SaveStocktakingOrderInfoBO>();
        for (JsonElement jsonElement : jsonArray){
            SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO=new SaveStocktakingOrderInfoBO();
             saveStocktakingOrderInfoBO=gson.fromJson(jsonElement,SaveStocktakingOrderInfoBO.class);
            saveStocktakingOrderInfoBO.setUnits("片");
            list.add(saveStocktakingOrderInfoBO);
        }
        return MsgTemplate.successMsg(list);
    }

    /**
     * 获取操作记录
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/17 10:34
     **/
    @Override
    public Map<String, Object> operationRecordList(GetStocktakingTaskBO getStocktakingTaskBO) {
        OrderResult orderResult=stocktakingTaskServer.operationRecordList(getStocktakingTaskBO);
        if(ObjectUtils.isEmpty(orderResult.getData())){
            return MsgTemplate.successMsg();
        }
        //因为这里返回的参数比较特殊所以需要重新自己组织对象,不调用方法
        InnerDate innerDate=new InnerDate();
        innerDate.setTotal(orderResult.getData().getTotal());
        innerDate.setResult(orderResult.getData().getResult());
        return MsgTemplate.successMsg(innerDate);
    }

    /**
     * 查看盘点任务进行情况接口
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/17 10:53
     **/
    @Override
    public Map<String, Object> stocktakingCompleteStatus(GetStocktakingTaskBO getStocktakingTaskBO) {
        HttpResult result=stocktakingTaskServer.stocktakingCompleteStatus(getStocktakingTaskBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        JsonArray asJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
        List<StocktakingCompleteStatusPO> stocktakingCompleteStatusPOList=new ArrayList<StocktakingCompleteStatusPO>();
        int status=1;
        //拿到的
       List<StocktakingCompleteStatusPO> completeStatusPOList=gson.fromJson(asJsonArray,List.class);
        for (int i=0;i<completeStatusPOList.size();i++){
            JsonObject complete=new JsonParser().parse(gson.toJson(completeStatusPOList.get(i))).getAsJsonObject();
            StocktakingCompleteStatusPO stocktakingCompleteStatusPO=gson.fromJson(complete,StocktakingCompleteStatusPO.class);
            JsonArray location=new JsonParser().parse(gson.toJson(complete.get("locationList"))).getAsJsonArray();
            for (JsonElement jsonElement:location){
                JsonObject amount=new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject();
                LocationListPO locationListPO=gson.fromJson(amount,LocationListPO.class);
                int allStocktakingTaskAmount=locationListPO.getAllStocktakingTaskAmount();
                int completeStocktakingTaskAmount=locationListPO.getCompleteStocktakingTaskAmount();
                if(allStocktakingTaskAmount!=completeStocktakingTaskAmount){
                    status=2;
                }
                stocktakingCompleteStatusPO.setStatus(status);
            }
            stocktakingCompleteStatusPOList.add(stocktakingCompleteStatusPO);
        }
        return MsgTemplate.successMsg(stocktakingCompleteStatusPOList);
    }

    /**
     * 获取库位订单信息
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/18 18:24
     **/
    @Override
    public Map<String, Object> orderWarehouseLocInfo(OrderWarehouseLocInfoBO orderWarehouseLocInfoBO) {
        HttpResult result=stocktakingTaskServer.orderWarehouseLocInfo(orderWarehouseLocInfoBO);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 盘盈编辑时获取库位
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/23 18:18
     **/
    @Override
    public Map<String, Object> areaAndLocInfo(JobAndWarehouseBO jobAndWarehouseBO) {
        //Http获取盘盈订单的所选库位信息
       HttpResult result=stocktakingTaskServer.areaAndLocInfo(jobAndWarehouseBO);
       JsonArray jsonArray=new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
       List<AreaAndLocInfoByPartPO> list=new ArrayList<AreaAndLocInfoByPartPO>();
       for (JsonElement jsonElement:jsonArray){
           AreaAndLocInfoByPartPO areaAndLocInfoByPartPO=gson.fromJson(jsonElement,AreaAndLocInfoByPartPO.class);
           list.add(areaAndLocInfoByPartPO);
       }
       String warehouseAreaId="";
        jobAndWarehouseBO.setVersion(AppConstant.DEFAULT_VERSION);
       for (AreaAndLocInfoByPartPO areaAndLocInfoByPartPO:list){
            warehouseAreaId=areaAndLocInfoByPartPO.getWarehouseAreaId();
            jobAndWarehouseBO.setWarehouseAreaId(warehouseAreaId);
           //Http获取所有库位信息
          OrderResult locationList=stocktakingTaskServer.getLocationAllList(jobAndWarehouseBO);
           if(!ObjectUtils.isEmpty(locationList.getData())){
               int total=new JsonParser().parse(gson.toJson(locationList.getData().getTotal())).getAsInt();
               if(areaAndLocInfoByPartPO.getLocationList().size()!=total){
                   areaAndLocInfoByPartPO.setStatus(1);
               }
               else{
                   areaAndLocInfoByPartPO.setStatus(2);
               }
           }
       }
        return MsgTemplate.successMsg(list);
    }

    @Override
    public Map<String, Object> getLocationAllList(JobAndWarehouseBO jobAndWarehouseBO) {
        return null;
    }

    /**
     * 更新打印次数接口
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/25 15:45
     **/
    @Override
    public Map<String, Object> printCount(PrintCountBO printCountBO) {
        HttpResult result=stocktakingTaskServer.printCount(printCountBO);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 获取未下发作业订单信息
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/25 15:34
     **/
    @Override
    public Map<String, Object> noSendOrderInfo(PrintCountBO printCountBO) {
        HttpResult result=stocktakingTaskServer.noSendOrderInfo(printCountBO);
        return MsgTemplate.customMsg(result);
    }

}
