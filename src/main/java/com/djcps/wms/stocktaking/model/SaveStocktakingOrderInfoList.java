package com.djcps.wms.stocktaking.model;

import java.util.List;

/**
 * @title:列表保存盘点结果
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/16
 **/
public class SaveStocktakingOrderInfoList {

    private String jobId;

    private String partnerId;

    private String operator;

    private String operatorId;

    List<SaveStocktakingOrderInfoBO> saveStocktaking;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }



    public List<SaveStocktakingOrderInfoBO> getSaveStocktaking() {
        return saveStocktaking;
    }

    public void setSaveStocktaking(List<SaveStocktakingOrderInfoBO> saveStocktaking) {
        this.saveStocktaking = saveStocktaking;
    }

}
