package com.djcps.wms.stocktaking.service.impl;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.stocktaking.constant.StocktakingTaskConstant;
import com.djcps.wms.stocktaking.model.*;
import com.djcps.wms.stocktaking.orderresult.InnerDate;
import com.djcps.wms.stocktaking.orderresult.OrderResult;
import com.djcps.wms.stocktaking.server.StocktakingOrderServer;
import com.djcps.wms.stocktaking.server.StocktakingTaskServer;
import com.djcps.wms.stocktaking.service.StocktakingTaskService;
import com.google.gson.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**盘点任务实现类
 * @title:
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/10
 **/
@Service
public class StocktakingTaskServiceimpl implements StocktakingTaskService {

    @Autowired
    private StocktakingTaskServer stocktakingTaskServer;

    @Autowired
    private StocktakingOrderServer stocktakingOrderServer;

    Gson gson=new Gson();
    /**
     * 获取全盘仓库关联订单
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/10 9:55
     **/
    @Override
    public Map<String, Object> getAllStocktakingInfo(AddStocktakingBO addStocktakingBO) {
        //获取库位关联订单信息
        HttpResult result=stocktakingTaskServer.getAllStocktakingInfo(addStocktakingBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        // data数据为空将值赋值为null,这里取到的是空数组
        if(ObjectUtils.isEmpty(result.getData())){
            return MsgTemplate.successMsg(null);
        }
        JsonArray asJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
        //获取所有订单id
        List<String> orderidlist=new ArrayList<String>();
        Map<String,List<String>> map=new HashMap<String,List<String>>();
        for (JsonElement jsonElement : asJsonArray){
            String orderId = new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("orderId").getAsString();
            orderidlist.add(orderId);
        }
        map.put("childOrderIds",orderidlist);

        //获取批量订单信息列表
        HttpResult orderResult=stocktakingOrderServer.getInfoByChildIds(map);
        JsonArray orderJsonArray = new JsonParser().parse(gson.toJson(orderResult.getData())).getAsJsonArray();

        //筛选fdblflag为0的订单信息
        JsonArray orderJsonArray0=new JsonArray();
        for (JsonElement jsonElement : orderJsonArray){
            int fdbflag =new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fdblflag").getAsInt();
            if(fdbflag==0){
                orderJsonArray0.add(jsonElement);
            }
        }
        //组装参数
        List<StocktakingTaskBO> stocktakingTaskBOList=getStocktakingOrderDetail(asJsonArray,orderJsonArray0);
        return MsgTemplate.successMsg(stocktakingTaskBOList);
    }

    /**
     * 获取部分盘点关联订单
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 9:59
     **/
    @Override
    public Map<String, Object> getPartStocktakingInfo(AddStocktakingBO addStocktakingBO) {
        //获取库位关联订单信息
        HttpResult result=stocktakingTaskServer.getPartStocktakingInfo(addStocktakingBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        // data数据为空将值赋值为null,这里取到的是空数组
        if(ObjectUtils.isEmpty(result.getData())){
            return MsgTemplate.successMsg(null);
        }
        JsonArray asJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
        //获取所有订单id
        List<String> orderidlist=new ArrayList<String>();
        Map<String,List<String>> map=new HashMap<String,List<String>>();
        for (JsonElement jsonElement : asJsonArray){
            String orderId = new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("orderId").getAsString();
            orderidlist.add(orderId);
        }
        map.put("childOrderIds",orderidlist);

        //获取批量订单信息列表
        HttpResult orderResult=stocktakingOrderServer.getInfoByChildIds(map);
        JsonArray orderJsonArray = new JsonParser().parse(gson.toJson(orderResult.getData())).getAsJsonArray();

        //筛选fdblflag为0的订单信息
        JsonArray orderJsonArray0=new JsonArray();
        for (JsonElement jsonElement : orderJsonArray){
            int fdbflag =new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fdblflag").getAsInt();
            if(fdbflag==0){
                orderJsonArray0.add(jsonElement);
            }
        }
        //组装参数
        List<StocktakingTaskBO> stocktakingTaskBOList=getPartStocktakingOrderDetail(asJsonArray,orderJsonArray0);
        return MsgTemplate.successMsg(stocktakingTaskBOList);
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
     * 获取可用盘点人员列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 15:07
     **/
    @Override
    public Map<String, Object> getInventoryclerk() {
        //HttpResult result=stocktakingTaskServer.getInventoryclerk();
        //HttpResult result1=new HttpResult();
       List<InventoryClerkBO> list=new ArrayList<InventoryClerkBO>();
       InventoryClerkBO inventoryClerkBO=new InventoryClerkBO();
        inventoryClerkBO.setInventoryClerk("盘点员01");
        inventoryClerkBO.setInventoryClerkId("10000101");
        list.add(inventoryClerkBO);
        //result1.setData(list);
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
     * 组装盘点订单
     * @author  wzy
     * @param asJsonArray 关联订单id信息的库位信息
     * @param orderJsonArray0 筛选过后的订单详情列表
     * @return
     * @create  2018/1/11 12:58
     **/
    public List<StocktakingTaskBO> getStocktakingOrderDetail(JsonArray asJsonArray,JsonArray orderJsonArray0){
        List<StocktakingTaskBO> stocktakingTaskBOList=new ArrayList<StocktakingTaskBO>();
        //遍历库区库位
        for (JsonElement warehouseElement: asJsonArray){
            String orderId = new JsonParser().parse(gson.toJson(warehouseElement)).getAsJsonObject().get("orderId").getAsString();
            //组装盘点任务仓库id
            String warehouseId=new JsonParser().parse(gson.toJson(warehouseElement)).getAsJsonObject().get("warehouseId").getAsString();
            String warehouseName=new JsonParser().parse(gson.toJson(warehouseElement)).getAsJsonObject().get("warehouseName").getAsString();

            if(warehouseElement.getAsJsonObject().get("warehouseAreaInfo")!=null){
                JsonArray warehouseAreaInfo=warehouseElement.getAsJsonObject().get("warehouseAreaInfo").getAsJsonArray();
                String warehouseAreaId=null;
                String warehouseAreaName=null;
                for (JsonElement warehouseAreaElement:warehouseAreaInfo){
                    //组装盘点任务库区id
                    warehouseAreaId=new JsonParser().parse(gson.toJson(warehouseAreaElement)).getAsJsonObject().get("warehouseAreaId").getAsString();
                    warehouseAreaName=new JsonParser().parse(gson.toJson(warehouseAreaElement)).getAsJsonObject().get("warehouseAreaName").getAsString();

                    if(warehouseAreaElement.getAsJsonObject().get("locationList")!=null){
                        JsonArray warehouseLocInfo=warehouseAreaElement.getAsJsonObject().get("locationList").getAsJsonArray();
                        for(JsonElement warehouseLocElement:warehouseLocInfo){
                            //组装盘点任务库位id

                            String warehouseLocId=new JsonParser().parse(gson.toJson(warehouseLocElement)).getAsJsonObject().get("warehouseLocId").getAsString();
                            String warehouseLocName=new JsonParser().parse(gson.toJson(warehouseLocElement)).getAsJsonObject().get("warehouseLocName").getAsString();
                            Integer trueAmount=new JsonParser().parse(gson.toJson(warehouseLocElement)).getAsJsonObject().get("trueAmount").getAsInt();
                            StocktakingTaskBO stocktakingTaskBO=new StocktakingTaskBO();
                            stocktakingTaskBO.setWarehouseId(warehouseId);
                            stocktakingTaskBO.setWarehouseName(warehouseName);
                            stocktakingTaskBO.setWarehouseAreaId(warehouseAreaId);
                            stocktakingTaskBO.setWarehouseAreaName(warehouseAreaName);
                            stocktakingTaskBO.setWarehouseLocId(warehouseLocId);
                            stocktakingTaskBO.setWarehouseLocName(warehouseLocName);
                            //组装盘点任务订单详情
                            for (JsonElement orderElement:orderJsonArray0){
                                String fchildorderid=new JsonParser().parse(gson.toJson(orderElement)).getAsJsonObject().get("fchildorderid").getAsString();
                                if (orderId.equals(fchildorderid)){
                                    String fmaterialid=new JsonParser().parse(gson.toJson(orderElement)).getAsJsonObject().get("fmateriafid").getAsString();
                                    String fmaterialname=new JsonParser().parse(gson.toJson(orderElement)).getAsJsonObject().get("fmaterialname").getAsString();
                                    WarehouseOrderDetailPO orderDetailPO=gson.fromJson(orderElement,WarehouseOrderDetailPO.class);
                                    //订单参数拼接
                                    orderDetailPO.setMaterialId(fmaterialid);
                                    orderDetailPO.setFmaterialname(fmaterialname);
                                    if(trueAmount!=null){
                                        orderDetailPO.setInstockAmount(trueAmount);
                                    }
                                    getOrderDetail(orderDetailPO,orderDetailPO);
                                    stocktakingTaskBO.setOrderDetail(orderDetailPO);
                                    stocktakingTaskBOList.add(stocktakingTaskBO);
                                }
                            }
                        }
                    }
                }
            }
        }
        return stocktakingTaskBOList;
    }

    /**
     * 组装部分部分盘点订单
     * @author  wzy
     * @param asJsonArray 关联订单id信息的库位信息
     * @param orderJsonArray0 筛选过后的订单详情列表
     * @return
     * @create  2018/1/14 15:21
     **/
    public List<StocktakingTaskBO> getPartStocktakingOrderDetail(JsonArray asJsonArray,JsonArray orderJsonArray0){
        List<StocktakingTaskBO> stocktakingTaskBOList=new ArrayList<StocktakingTaskBO>();
        for (JsonElement warehouseElement: asJsonArray){
            String orderId = new JsonParser().parse(gson.toJson(warehouseElement)).getAsJsonObject().get("orderId").getAsString();
            //组装盘点任务仓库id
            String warehouseId=new JsonParser().parse(gson.toJson(warehouseElement)).getAsJsonObject().get("warehouseId").getAsString();
            String warehouseName=new JsonParser().parse(gson.toJson(warehouseElement)).getAsJsonObject().get("warehouseName").getAsString();

            if(warehouseElement.getAsJsonObject().get("orderAreaAndLocDetailInfo")!=null){
            JsonObject warehouseAreaInfo=warehouseElement.getAsJsonObject().get("orderAreaAndLocDetailInfo").getAsJsonObject();
                //组装盘点任务库区id
                String warehouseAreaId=warehouseAreaInfo.get("warehouseAreaId").getAsString();
                String warehouseAreaName=warehouseAreaInfo.get("warehouseAreaName").getAsString();
               //组装盘点任务库位id
                String warehouseLocId=warehouseAreaInfo.get("warehouseLocId").getAsString();
                String warehouseLocName=warehouseAreaInfo.get("warehouseLocName").getAsString();
                Integer trueAmount=warehouseAreaInfo.get("trueAmount").getAsInt();
                StocktakingTaskBO stocktakingTaskBO=new StocktakingTaskBO();
                stocktakingTaskBO.setWarehouseId(warehouseId);
                stocktakingTaskBO.setWarehouseName(warehouseName);
                stocktakingTaskBO.setWarehouseAreaId(warehouseAreaId);
                stocktakingTaskBO.setWarehouseAreaName(warehouseAreaName);
                stocktakingTaskBO.setWarehouseLocId(warehouseLocId);
                stocktakingTaskBO.setWarehouseLocName(warehouseLocName);
                if(trueAmount!=null){
                    stocktakingTaskBO.setTrueAmount(trueAmount);
                }
                //组装盘点任务订单详情
                for (JsonElement orderElement:orderJsonArray0){
                    String fchildorderid=new JsonParser().parse(gson.toJson(orderElement)).getAsJsonObject().get("fchildorderid").getAsString();
                    if (orderId.equals(fchildorderid)){
                        String fmaterialid=new JsonParser().parse(gson.toJson(orderElement)).getAsJsonObject().get("fmateriafid").getAsString();
                        String fmaterialname=new JsonParser().parse(gson.toJson(orderElement)).getAsJsonObject().get("fmaterialname").getAsString();
                        WarehouseOrderDetailPO orderDetailPO=gson.fromJson(orderElement,WarehouseOrderDetailPO.class);
                        //订单参数拼接
                        orderDetailPO.setMaterialId(fmaterialid);
                        orderDetailPO.setFmaterialname(fmaterialname);
                        getOrderDetail(orderDetailPO,orderDetailPO);
                        stocktakingTaskBO.setOrderDetail(orderDetailPO);
                        stocktakingTaskBOList.add(stocktakingTaskBO);
                    }
                }
            }
        }
        return stocktakingTaskBOList;
    }

    /**
     * 规格参数拼接
     * @param source
     * @param target
     * @return
     * @author:zdx
     * @date:2018年1月8日
     */
    private WarehouseOrderDetailPO getOrderDetail(WarehouseOrderDetailPO source, WarehouseOrderDetailPO target){
        //规格长宽高都不为null,才进行拼接
        if(!ObjectUtils.isEmpty(target.getFboxlength()) && !ObjectUtils.isEmpty(target.getFboxwidth()) &&
                !ObjectUtils.isEmpty(target.getFboxheight())){
            //拼接字符串,拼接成产品规格和下料规格
            source.setFproductRule(new StringBuffer().append(target.getFboxlength()).append("*")
                    .append(target.getFboxwidth()).append("*").append(target.getFboxheight()).toString());
        }
        source.setFmaterialRule(new StringBuffer().append(target.getFmateriallength()).append("*")
                .append(target.getFmaterialwidth()).toString());
        //组织参数
        source.setFmaterialRule(new StringBuffer().append(target.getFmateriallength()).append("*")
                .append(target.getFmaterialwidth()).toString());
        source.setFordertime(target.getFordertime());
        source.setFdelivery(target.getFdelivery());
        source.setFgroupgoodname(target.getFgroupgoodname());
        source.setFflutetype(target.getFflutetype());
        source.setFstatus(target.getFstatus());
        source.setFmaterialname(target.getFmaterialname());
        source.setFlnglat(target.getFlnglat());
        source.setFpaymenttime(target.getFpaymenttime());
        source.setFaddressdetail(target.getFaddressdetail());
        source.setFcodeprovince(target.getFcodeprovince());
        source.setFconsignee(target.getFconsignee());
        source.setFcontactway(target.getFcontactway());
        source.setFpusername(target.getFpusername());
        source.setOrderId(source.getFchildorderid());
        source.setAmount(source.getFamount());
        source.setFamount(source.getAmount());
        return null;
    }

    /**
     * 不带f的订单详细信息
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/15 15:24
     **/
    private WarehouseOrderDetailPO getOrderDetail(SaveStocktakingOrderInfoBO source, SaveStocktakingOrderInfoBO target){
        //规格长宽高都不为null,才进行拼接
        if(!ObjectUtils.isEmpty(target.getBoxLength()) && !ObjectUtils.isEmpty(target.getBoxWidth()) &&
                !ObjectUtils.isEmpty(target.getBoxHeight())){
            //拼接字符串,拼接成产品规格和下料规格
            source.setProductRule(new StringBuffer().append(target.getBoxLength()).append("*")
                    .append(target.getBoxWidth()).append("*").append(target.getBoxHeight()).toString());
        }
        source.setMaterialRule(new StringBuffer().append(target.getMaterialLength()).append("*")
                .append(target.getMaterialWidth()).toString());
        //组织参数
        source.setOrdertime(target.getOrdertime());
        source.setDelivery(target.getDelivery());
        source.setGroupgoodname(target.getGroupgoodname());
        source.setFluteType(target.getFluteType());
        source.setStatus(target.getStatus());
        source.setMaterialname(target.getMaterialname());
        source.setLnglat(target.getLnglat());
        source.setPaymenttime(target.getPaymenttime());
        source.setAddressdetail(target.getAddressdetail());
        source.setCodeprovince(target.getCodeprovince());
        source.setConsignee(target.getConsignee());
        source.setContactway(target.getContactway());
        source.setPusername(target.getPusername());
//        source.setOrderId(source.getChildorderid());
//        source.setOrderId(source.getOrderId());
       // source.setAmount(source.getAmount());
        source.setAmount(source.getAmount());
        return null;
    }

    /**
     * 保存新增的盘点任务
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 22:18
     **/
    @Override
    public Map<String, Object> saveSoctakingTask(SaveStocktakingTaskBO saveStocktakingTaskBO) {
        String partnerId=saveStocktakingTaskBO.getPartnerId();
        String relativeId=saveStocktakingTaskBO.getJobId();
        List<SaveStocktakingOrderInfoBO> list=saveStocktakingTaskBO.getStocktakingOrderInfo();
        for (int i=0;i<list.size();i++){
            list.get(i).setPartnerId(partnerId);
            list.get(i).setRelativeId(relativeId);
            list.get(i).setOperatorId(saveStocktakingTaskBO.getCreatorId());
            list.get(i).setOperator(saveStocktakingTaskBO.getCreator());
            list.get(i).setIsInventoryProfit(2);
            GetAmountBO getAmountBO=new GetAmountBO();
            getAmountBO.setOrderId(list.get(i).getOrderId());
            getAmountBO.setWarehouseAreaId(list.get(i).getWarehouseAreaId());
            getAmountBO.setWarehouseLocId(list.get(i).getWarehouseLocId());
            //获取在库数量
            HttpResult amountResult=stocktakingTaskServer.getAmount(getAmountBO);
            if(!ObjectUtils.isEmpty(amountResult.getData())){
                int trueAmount = Integer.parseInt(new java.text.DecimalFormat("0").format(amountResult.getData()));
                list.get(i).setInstockAmount(trueAmount);
            }
        }
        saveStocktakingTaskBO.setStocktakingOrderInfo(list);
        HttpResult result=stocktakingTaskServer.saveSoctakingTask(saveStocktakingTaskBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        int saveStocktakingType=saveStocktakingTaskBO.getSaveStocktakingType();
        if(saveStocktakingType==StocktakingTaskConstant.CONFIRM_PUSH){
            //请求推送服务
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
            //List<StocktakingTaskBO> list=new ArrayList<StocktakingTaskBO>();
            List<SaveStocktakingOrderInfoBO> list=new ArrayList<SaveStocktakingOrderInfoBO>();
            JsonObject resultObject = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonObject();
            JsonArray OrderInfoJsonArray=resultObject.get("result").getAsJsonArray();
            //JsonArray OrderInfoJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
            for (JsonElement jsonElement : OrderInfoJsonArray){
                StocktakingTaskBO stocktakingTaskBO=gson.fromJson(jsonElement,StocktakingTaskBO.class);
                //WarehouseOrderDetailPO warehouseOrderDetailPO=gson.fromJson(jsonElement,WarehouseOrderDetailPO.class);
                SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO=gson.fromJson(jsonElement,SaveStocktakingOrderInfoBO.class);
                saveStocktakingOrderInfoBO.setUnits("片");
                getOrderDetail(saveStocktakingOrderInfoBO,saveStocktakingOrderInfoBO);
                //stocktakingTaskBO.setOrderDetail(saveStocktakingOrderInfoBO);
                list.add(saveStocktakingOrderInfoBO);
            }
            return  MsgTemplate.successMsg(list);
    }

    /**
     * 获取盘点任务订单信息，校验是否需要盘盈
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 11:26
     **/
    @Override
    public Map<String, Object> inventorySurplus(StocktakingTaskBO stocktakingTaskBO) {
            Map<String,List<String>> map=new HashMap<String,List<String>>();
            OrderIdBO orderIdBO=new OrderIdBO();
           orderIdBO.setOrderId(stocktakingTaskBO.getOrderId());
            List<OrderIdBO> listorderid=new ArrayList<OrderIdBO>();
            listorderid.add(orderIdBO);
            //获取订单详情
            OrderWarehouseLocInfoBO orderWarehouseLocInfoBO=new OrderWarehouseLocInfoBO();
            orderWarehouseLocInfoBO.setOrderIds(listorderid);
            //HttpResult orderWarehouseResult=stocktakingTaskServer.orderWarehouseLocInfo(orderWarehouseLocInfoBO);


            String orderId=stocktakingTaskBO.getOrderId();
            List<String> list=new ArrayList<String>();
            list.add(orderId);
            map.put("childOrderIds",list);
            //请求订单详细信息
            HttpResult orderResult=stocktakingOrderServer.getInfoByChildIds(map);
        if(ObjectUtils.isEmpty(orderResult.getData()) ){
            return MsgTemplate.failureMsg(SysMsgEnum.ORDER_WRONG);
        }
            JsonArray orderJsonArray = new JsonParser().parse(gson.toJson(orderResult.getData())).getAsJsonArray();
            //筛选fdblflag为0的订单信息
            JsonArray orderJsonArray0=new JsonArray();
            for (JsonElement jsonElement : orderJsonArray){
                int fdbflag =new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fdblflag").getAsInt();
                if(fdbflag==0){
                    orderJsonArray0.add(jsonElement);
                }
            }
            //组装盘点任务订单详情
            for (JsonElement orderElement:orderJsonArray0){
                    WarehouseOrderDetailPO orderDetailPO=gson.fromJson(orderElement,WarehouseOrderDetailPO.class);
                    //订单参数拼接
                    getOrderDetail(orderDetailPO,orderDetailPO);
                    orderDetailPO.setUnits("片");
                    stocktakingTaskBO.setOrderDetail(orderDetailPO);
            }

//        if(!ObjectUtils.isEmpty(orderWarehouseResult.getData())){
//            JsonArray asJsonArray = new JsonParser().parse(gson.toJson(orderWarehouseResult.getData())).getAsJsonArray();
//            for (JsonElement jsonElement:asJsonArray){
//                JsonObject amount=new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject();
//                JsonArray warehouseAreaInfo = new JsonParser().parse(gson.toJson(amount)).getAsJsonArray();
//                for (JsonElement areajsonelement:warehouseAreaInfo){
//                    JsonObject LocInfo=new JsonParser().parse(gson.toJson(areajsonelement)).getAsJsonObject();
//                    JsonArray locinfo= new JsonParser().parse(gson.toJson(LocInfo)).getAsJsonArray();
//                    for (JsonElement locjsonelement:locinfo){
//                        int tureAmount =new JsonParser().parse(gson.toJson(locjsonelement)).getAsJsonObject().get("trueAmount").getAsInt();
//                        stocktakingTaskBO.setTrueAmount(tureAmount);
//                    }
//                }
//            }
//        }
            return MsgTemplate.successMsg(stocktakingTaskBO);
    }

    /**
     * PDA/Web保存盘盈录入信息
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
     * PDA发起盘盈
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 19:44
     **/
    @Override
    public Map<String, Object> pdaInventorySurplus(StocktakingTaskBO stocktakingTaskBO) {
//        HttpResult result=stocktakingTaskServer.stocktakingOrderInfoByOrderId(stocktakingTaskBO);
//        if(!result.isSuccess()){
//            HttpResult otherResult = new HttpResult();
//            BeanUtils.copyProperties(result, otherResult);
//            return MsgTemplate.customMsg(otherResult);
//        }
        // data数据为空,表示需要盘盈,请求订单详细信息
//        if(ObjectUtils.isEmpty(result.getData())){

            //获取批量订单信息列表,判断是不是作业单号写错了
            Map<String,List<String>> map=new HashMap<String,List<String>>();
            String orderId=stocktakingTaskBO.getOrderId();
            List<String> list=new ArrayList<String>();
            list.add(orderId);
            map.put("childOrderIds",list);
            //请求订单详细信息
            HttpResult orderResult=stocktakingOrderServer.getInfoByChildIds(map);
            if(ObjectUtils.isEmpty(orderResult.getData()) ){
                return MsgTemplate.failureMsg(SysMsgEnum.ORDER_WRONG);
            }
            JsonArray orderJsonArray = new JsonParser().parse(gson.toJson(orderResult.getData())).getAsJsonArray();
            //筛选fdblflag为0的订单信息
            JsonArray orderJsonArray0=new JsonArray();
            for (JsonElement jsonElement : orderJsonArray){
                int fdbflag =new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fdblflag").getAsInt();
                if(fdbflag==0){
                    orderJsonArray0.add(jsonElement);
                }
            }
            //组装盘点任务订单详情
            for (JsonElement orderElement:orderJsonArray0){
                WarehouseOrderDetailPO orderDetailPO=gson.fromJson(orderElement,WarehouseOrderDetailPO.class);
                //订单参数拼接
                getOrderDetail(orderDetailPO,orderDetailPO);
                stocktakingTaskBO.setOrderDetail(orderDetailPO);
            }
            //获取相关订单库区库位信息,并拼接入返回信息中
            HttpResult warehouseAreaAndLocResult=stocktakingTaskServer.pdaWarehouseAreaAndLocInfo(stocktakingTaskBO);
//            JsonObject warehouseJsonObject = new JsonParser().parse(gson.toJson(warehouseAreaAndLocResult.getData())).getAsJsonObject();
//            StocktakingTaskBO warehouseStocktakingBo=gson.fromJson(warehouseJsonObject,StocktakingTaskBO.class);
//            BeanUtils.copyProperties(warehouseStocktakingBo,stocktakingTaskBO);
            return MsgTemplate.successMsg(stocktakingTaskBO);
       // }
        //订单存在,盘盈失败
        //return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
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
        Map<String,List<String>> map=new HashMap<String,List<String>>();
        SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO=null;
        //获取所有订单列表
        for(int i=0;i<saveStocktakingOrderInfoList.getSaveStocktaking().size();i++){
            saveStocktakingOrderInfoBO=saveStocktakingOrderInfoList.getSaveStocktaking().get(i);
            orderidlist.add(saveStocktakingOrderInfoBO.getOrderId());
        }
        map.put("childOrderIds",orderidlist);
        //获取批量订单信息列表
        HttpResult orderResult=stocktakingOrderServer.getInfoByChildIds(map);
        JsonArray orderJsonArray = new JsonParser().parse(gson.toJson(orderResult.getData())).getAsJsonArray();
        //筛选fdblflag为0的订单信息
        JsonArray orderJsonArray0=new JsonArray();
        for (JsonElement jsonElement : orderJsonArray){
            int fdbflag =new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fdblflag").getAsInt();
            if(fdbflag==0){
                orderJsonArray0.add(jsonElement);
            }
        }
        //拼接盘点任务订单+详细信息
//        saveStocktakingOrderInfoList.setOperator(partnerInfoBO.getOperator());
//        saveStocktakingOrderInfoList.setOperatorId(partnerInfoBO.getOperatorId());
//        saveStocktakingOrderInfoList.setPartnerId(partnerInfoBO.getPartnerId());
        for (JsonElement jsonElement : orderJsonArray0){
            String orderId = new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fchildorderid").getAsString();
            for(int i=0;i<saveStocktakingOrderInfoList.getSaveStocktaking().size();i++){
                //比对订单号
                if(orderId.equals(saveStocktakingOrderInfoList.getSaveStocktaking().get(i).getOrderId())){
                    FOrderInfoBO fOrderInfoBO=gson.fromJson(jsonElement,FOrderInfoBO.class);
                    saveStocktakingOrderInfoList.getSaveStocktaking().get(i).setOperatorId(partnerInfoBO.getOperatorId());
                    saveStocktakingOrderInfoList.getSaveStocktaking().get(i).setOperator(partnerInfoBO.getOperator());
                    saveStocktakingOrderInfoList.getSaveStocktaking().get(i).setRelativeId(saveStocktakingOrderInfoList.getJobId());
                    BeanUtils.copyProperties(partnerInfoBO,saveStocktakingOrderInfoList.getSaveStocktaking().get(i));
                    saveStocktakingOrderInfoList.getSaveStocktaking().get(i).setMaterialLength(fOrderInfoBO.getFmateriallength());
                    saveStocktakingOrderInfoList.getSaveStocktaking().get(i).setMaterialWidth(fOrderInfoBO.getFmaterialwidth());
                    saveStocktakingOrderInfoList.getSaveStocktaking().get(i).setBoxLength(fOrderInfoBO.getFboxlength());
                    saveStocktakingOrderInfoList.getSaveStocktaking().get(i).setBoxWidth(fOrderInfoBO.getFboxwidth());
                    saveStocktakingOrderInfoList.getSaveStocktaking().get(i).setBoxHeight(fOrderInfoBO.getFboxheight());
                    saveStocktakingOrderInfoList.getSaveStocktaking().get(i).setProductName(fOrderInfoBO.getFgroupgoodname());
                    saveStocktakingOrderInfoList.getSaveStocktaking().get(i).setMaterialName(fOrderInfoBO.getFmaterialname());
                    saveStocktakingOrderInfoList.getSaveStocktaking().get(i).setMaterialId(fOrderInfoBO.getFmateriafid());
                    //计算差异量
                    GetAmountBO getAmountBO=new GetAmountBO();
                    getAmountBO.setOrderId(saveStocktakingOrderInfoList.getSaveStocktaking().get(i).getOrderId());
                    getAmountBO.setWarehouseAreaId(saveStocktakingOrderInfoList.getSaveStocktaking().get(i).getWarehouseAreaId());
                    getAmountBO.setWarehouseLocId(saveStocktakingOrderInfoList.getSaveStocktaking().get(i).getWarehouseLocId());
                    //获取在库数量
                    HttpResult amountResult=stocktakingTaskServer.getAmount(getAmountBO);
                    if(!ObjectUtils.isEmpty(amountResult.getData())){
                        int trueAmount = Integer.parseInt(new java.text.DecimalFormat("0").format(amountResult.getData()));
                        saveStocktakingOrderInfoList.getSaveStocktaking().get(i).setInstockAmount(trueAmount);
                        saveStocktakingOrderInfoList.getSaveStocktaking().get(i).setDifferenceValue(saveStocktakingOrderInfoBO.getTakeStockAmount()-trueAmount);
                    }
                }
            }
        }
        HttpResult result= stocktakingTaskServer.saveStocktakingResult(saveStocktakingOrderInfoList);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 保存盘点结果,获取库存量，计算差异量
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 15:22
     **/
    @Override
    public Map<String, Object> completeStocktakingTask(SaveStocktakingOrderInfoList saveStocktakingOrderInfoBOList,PartnerInfoBO partnerInfoBO) {
        List<String> orderidlist=new ArrayList<String>();
        Map<String,List<String>> map=new HashMap<String,List<String>>();
        SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO=null;
        String jobId=saveStocktakingOrderInfoBOList.getJobId();
        String WarehouseId=null;
        //获取所有订单列表
        for(int i=0;i<saveStocktakingOrderInfoBOList.getSaveStocktaking().size();i++){
            saveStocktakingOrderInfoBO=saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i);
            orderidlist.add(saveStocktakingOrderInfoBO.getOrderId());
            //jobId=saveStocktakingOrderInfoBO.getRelativeId();
            WarehouseId=saveStocktakingOrderInfoBO.getWarehouseId();
        }
        //校验是否盘点完毕
        PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO=new PdaGetStocktakingOrderBO();
        pdaGetStocktakingOrderBO.setJobId(jobId);
        pdaGetStocktakingOrderBO.setPartnerId(partnerInfoBO.getPartnerId());
        pdaGetStocktakingOrderBO.setWarehouseId(WarehouseId);
        HttpResult result=stocktakingTaskServer.getOrderAmount(pdaGetStocktakingOrderBO);
        //JsonArray asJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
        int unfinishedStocktakingAmount = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonObject().get("unfinishedStocktakingAmount").getAsInt();
        //未盘点数量为0，全部盘点完了
        //if(unfinishedStocktakingAmount==0){
            map.put("childOrderIds",orderidlist);
            //获取批量订单信息列表
            HttpResult orderResult=stocktakingOrderServer.getInfoByChildIds(map);
            JsonArray orderJsonArray = new JsonParser().parse(gson.toJson(orderResult.getData())).getAsJsonArray();

            //筛选fdblflag为0的订单信息
            JsonArray orderJsonArray0=new JsonArray();
            for (JsonElement jsonElement : orderJsonArray){
                int fdbflag =new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fdblflag").getAsInt();
                if(fdbflag==0){
                    orderJsonArray0.add(jsonElement);
                }
            }
            //拼接盘点任务订单+详细信息
            saveStocktakingOrderInfoBOList.setOperator(partnerInfoBO.getOperator());
            saveStocktakingOrderInfoBOList.setOperatorId(partnerInfoBO.getOperatorId());
            saveStocktakingOrderInfoBOList.setPartnerId(partnerInfoBO.getPartnerId());
            for (JsonElement jsonElement : orderJsonArray0){
                String orderId = new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fchildorderid").getAsString();
                for(int i=0;i<saveStocktakingOrderInfoBOList.getSaveStocktaking().size();i++){
                    if(orderId.equals(saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).getOrderId())){
                        FOrderInfoBO fOrderInfoBO=gson.fromJson(jsonElement,FOrderInfoBO.class);
                        saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).setOperatorId(partnerInfoBO.getOperatorId());
                        saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).setOperator(partnerInfoBO.getOperator());
                        saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).setRelativeId(saveStocktakingOrderInfoBOList.getJobId());
                        BeanUtils.copyProperties(partnerInfoBO,saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i));
                        saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).setMaterialLength(fOrderInfoBO.getFmateriallength());
                        saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).setMaterialWidth(fOrderInfoBO.getFmaterialwidth());
                        saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).setBoxLength(fOrderInfoBO.getFboxlength());
                        saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).setBoxWidth(fOrderInfoBO.getFboxwidth());
                        saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).setBoxHeight(fOrderInfoBO.getFboxheight());
                        saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).setProductName(fOrderInfoBO.getFgroupgoodname());
                        saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).setMaterialName(fOrderInfoBO.getFmaterialname());
                        saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).setMaterialId(fOrderInfoBO.getFmateriafid());
                        //计算差异量
                        GetAmountBO getAmountBO=new GetAmountBO();
                        getAmountBO.setOrderId(saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).getOrderId());
                        getAmountBO.setWarehouseAreaId(saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).getWarehouseAreaId());
                        getAmountBO.setWarehouseLocId(saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).getWarehouseLocId());
                        //获取在库数量
                        HttpResult amountResult=stocktakingTaskServer.getAmount(getAmountBO);
                        if(!ObjectUtils.isEmpty(amountResult.getData())){
                            int trueAmount = Integer.parseInt(new java.text.DecimalFormat("0").format(amountResult.getData()));
                            saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).setInstockAmount(trueAmount);
                            saveStocktakingOrderInfoBOList.getSaveStocktaking().get(i).setDifferenceValue(saveStocktakingOrderInfoBO.getTakeStockAmount()-trueAmount);
                        }
                    }
                }
            }
            HttpResult saveresult= stocktakingTaskServer.completeStocktakingTask(saveStocktakingOrderInfoBOList);
            return MsgTemplate.customMsg(saveresult);
       // }
        //有未盘点的订单
//        else {
//            return MsgTemplate.customMsg(stocktakingTaskServer.getOrderAmount(pdaGetStocktakingOrderBO));
//        }
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
        //HttpResult result=stocktakingTaskServer.stocktakingTaskList(getStocktakingTaskBO);
        if(ObjectUtils.isEmpty(orderResult.getData())){
            return MsgTemplate.successMsg(null);
        }
        //Map<String, Object> resultMap=gson.fromJson(orderResult,Map.class)

        //因为这里返回的参数比较特殊所以需要重新自己组织对象,不调用方法
        Map<String, Object> resultMap = new HashMap<String, Object>();
        InnerDate innerDate=new InnerDate();
        innerDate.setTotal(orderResult.getData().getTotal());
        innerDate.setResult(orderResult.getData().getResult());
        resultMap.put("success", true);
        resultMap.put("code", 100000);
        resultMap.put("msg", "");
        resultMap.put("data",innerDate);
        return resultMap;
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
        Map<String, Object> resultMap = new HashMap<String, Object>();
        InnerDate innerDate=new InnerDate();
        innerDate.setTotal(orderResult.getData().getTotal());
        innerDate.setResult(orderResult.getData().getResult());
        resultMap.put("success", true);
        resultMap.put("code", 100000);
        resultMap.put("msg", "");
        resultMap.put("data",innerDate);
        return resultMap;
        //return MsgTemplate.customMsg(result);
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
            return MsgTemplate.successMsg(null);
        }
        JsonArray asJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
        List<PdaStocktakingOrderBO> pdaorderlist=new ArrayList<PdaStocktakingOrderBO>();
        //订单参数拼接
        for (JsonElement jsonElement:asJsonArray){
            PdaStocktakingOrderBO pdaStocktakingOrderBO=gson.fromJson(jsonElement,PdaStocktakingOrderBO.class);
            pdaStocktakingOrderBO.setMaterialRule(new StringBuffer().append(pdaStocktakingOrderBO.getMaterialLength()).append("*")
                    .append(pdaStocktakingOrderBO.getMaterialWidth()).toString());
            pdaorderlist.add(pdaStocktakingOrderBO);
        }
        List<PdaStocktakingOrderBO> pdaorderlist2=new ArrayList<PdaStocktakingOrderBO>();
        for(PdaStocktakingOrderBO pdaStocktakingOrderBO:pdaorderlist){
            if(ObjectUtils.isEmpty(pdaStocktakingOrderBO.getTakeStockAmount())){
                pdaorderlist2.add(pdaStocktakingOrderBO);
            }
        }
        for(PdaStocktakingOrderBO pdaStocktakingOrderBO:pdaorderlist) {
            if (pdaStocktakingOrderBO.getIsInventoryProfit().equals("1.0")) {
                pdaorderlist2.add(pdaStocktakingOrderBO);
            }
        }
        for(PdaStocktakingOrderBO pdaStocktakingOrderBO:pdaorderlist){
            if (pdaStocktakingOrderBO.getIsInventoryProfit().equals("2.0") && !ObjectUtils.isEmpty(pdaStocktakingOrderBO.getTakeStockAmount())) {
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
        Map<String,List<String>> checkmap=new HashMap<String,List<String>>();
        orderidlist.add(pdaStocktakingOrderInfo.getOrderId());
        checkmap.put("childOrderIds",orderidlist);
        //获取批量订单信息列表,判断是不是作业单号写错了
        HttpResult CheckorderResult=stocktakingOrderServer.getInfoByChildIds(checkmap);
        if(ObjectUtils.isEmpty(CheckorderResult.getData()) ){
            return MsgTemplate.failureMsg(SysMsgEnum.ORDER_WRONG);
        }
        //请求详细信息
        HttpResult result=stocktakingTaskServer.pdaStocktakingOrderInfo(pdaStocktakingOrderInfo);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        if(ObjectUtils.isEmpty(result.getData())){
            return MsgTemplate.successMsg(null);
        }
        JsonObject JsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonObject();
        PdaOderInfoBO pdaOderInfoBO=gson.fromJson(JsonArray,PdaOderInfoBO.class);
        pdaOderInfoBO.setProductRule(new StringBuffer().append(pdaOderInfoBO.getBoxLength()).append("*")
                .append(pdaOderInfoBO.getBoxWidth()).append("*").append(pdaOderInfoBO.getBoxHeight()).toString());
        pdaOderInfoBO.setMaterialRule(new StringBuffer().append(pdaOderInfoBO.getMaterialLength()).append("*")
                .append(pdaOderInfoBO.getMaterialWidth()).toString());
        return MsgTemplate.successMsg(pdaOderInfoBO);
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
        String materialName=null;
        String materialId=null;
        List<String> orderidlist=new ArrayList<String>();
        Map<String,List<String>> map=new HashMap<String,List<String>>();
        //获取所有订单列表
        orderidlist.add(saveStocktakingOrderInfoBO.getOrderId());
        map.put("childOrderIds",orderidlist);
        //获取批量订单信息列表
        HttpResult orderResult=stocktakingOrderServer.getInfoByChildIds(map);
        JsonArray orderJsonArray = new JsonParser().parse(gson.toJson(orderResult.getData())).getAsJsonArray();
        //筛选fdblflag为0的订单信息
        JsonArray orderJsonArray0=new JsonArray();
        for (JsonElement jsonElement : orderJsonArray){
            int fdbflag =new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fdblflag").getAsInt();
            if(fdbflag==0){
                orderJsonArray0.add(jsonElement);
            }
        }
        for (JsonElement jsonElement : orderJsonArray0){
            FOrderInfoBO fOrderInfoBO=gson.fromJson(jsonElement,FOrderInfoBO.class);
            materialName=fOrderInfoBO.getFmaterialname();
            materialId=fOrderInfoBO.getFmateriafid();
        }
        saveStocktakingOrderInfoBO.setMaterialName(materialName);
        saveStocktakingOrderInfoBO.setMaterialId(materialId);
        saveStocktakingOrderInfoBO.setRelativeId(saveStocktakingOrderInfoBO.getJobId());
        //是正常新增时做校验
        if (saveStocktakingOrderInfoBO.getIsAdd().equals("1")){
            //判断新增订单的库区库位是否已在盘点任务列表中
            PdaStocktakingOrderInfo pdaStocktakingOrderInfo=new PdaStocktakingOrderInfo();
            pdaStocktakingOrderInfo.setJobId(saveStocktakingOrderInfoBO.getJobId());
            pdaStocktakingOrderInfo.setOrderId(saveStocktakingOrderInfoBO.getOrderId());
            pdaStocktakingOrderInfo.setPartnerId(saveStocktakingOrderInfoBO.getPartnerId());
            HttpResult pdaStocktakingOrder=stocktakingTaskServer.pdaStocktakingOrderInfo(pdaStocktakingOrderInfo);
            //若该订单已存在
            if(!ObjectUtils.isEmpty(pdaStocktakingOrder.getData())){
                JsonObject OrderJsonArray = new JsonParser().parse(gson.toJson(pdaStocktakingOrder.getData())).getAsJsonObject();
                PdaOderInfoBO pdaOderInfoBO=gson.fromJson(OrderJsonArray,PdaOderInfoBO.class);
                for (WarehouseAreaAndLocBO warehouseAreaAndLocBO:pdaOderInfoBO.getOrderAreaAndLocList()){
                    if (saveStocktakingOrderInfoBO.getWarehouseAreaId().equals(warehouseAreaAndLocBO.getWarehouseAreaId()) && saveStocktakingOrderInfoBO.getWarehouseLocId().equals(warehouseAreaAndLocBO.getWarehouseLocId())){
                        //该新增订单已存在
                        return MsgTemplate.failureMsg(SysMsgEnum.ORDER_EXIST);
                    }
                }
            }

            HttpResult result=stocktakingTaskServer.savePdaStocktakingResult(saveStocktakingOrderInfoBO);
            if(!result.isSuccess()){
                HttpResult otherResult = new HttpResult();
                BeanUtils.copyProperties(result, otherResult);
                return MsgTemplate.customMsg(otherResult);
            }
            return MsgTemplate.customMsg(result);
        }

        //正常保存和盘盈时
        //saveStocktakingOrderInfoBO.setRelativeId(saveStocktakingOrderInfoBO.getJobId());
        GetAmountBO getAmountBO=new GetAmountBO();
        getAmountBO.setOrderId(saveStocktakingOrderInfoBO.getOrderId());
        getAmountBO.setWarehouseAreaId(saveStocktakingOrderInfoBO.getWarehouseAreaId());
        getAmountBO.setWarehouseLocId(saveStocktakingOrderInfoBO.getWarehouseLocId());
        //获取在库数量,计算差异量
        HttpResult amountResult=stocktakingTaskServer.getAmount(getAmountBO);
        if(!ObjectUtils.isEmpty(amountResult.getData())){
            int trueAmount = Integer.parseInt(new java.text.DecimalFormat("0").format(amountResult.getData()));
            saveStocktakingOrderInfoBO.setInstockAmount(trueAmount);
            saveStocktakingOrderInfoBO.setDifferenceValue(saveStocktakingOrderInfoBO.getTakeStockAmount()-trueAmount);
        }
        //保存盘点结果
        HttpResult result=stocktakingTaskServer.savePdaStocktakingResult(saveStocktakingOrderInfoBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * PDA完成盘点请求，未完成返回盘点数
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
        //JsonArray asJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
        //for(JsonElement jsonElement:asJsonArray){
            //int unfinishedStocktakingAmount = new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("unfinishedStocktakingAmount").getAsInt();
//            if(unfinishedStocktakingAmount==0){
              // return MsgTemplate.successMsg(pdaCompleteStocktaking(pdaGetStocktakingOrderBO));
            //}
        //}
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
            return MsgTemplate.successMsg(null);
        }
        //因为这里返回的参数比较特殊所以需要重新自己组织对象,不调用方法
        Map<String, Object> resultMap = new HashMap<String, Object>();
        InnerDate innerDate=new InnerDate();
        innerDate.setTotal(orderResult.getData().getTotal());
        innerDate.setResult(orderResult.getData().getResult());
        resultMap.put("success", true);
        resultMap.put("code", 100000);
        resultMap.put("msg", "");
        resultMap.put("data",innerDate);
        return resultMap;
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
        int Status=1;
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
                    Status=2;
                }
                stocktakingCompleteStatusPO.setStatus(Status);
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
}
