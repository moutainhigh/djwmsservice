package com.djcps.wms.stocktaking.model;

import java.util.Date;

/**
 * @title:从订单服务拿过来带f的参数
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/22
 **/
public class ForderInfoBO {

    private String forderid;

    private String fchildorderid;

    /**
     * 联系方式
     */
    private String fcontactway;
    /**
     * 联系人
     */
    private String fconsignee;

    /**
     * 客户名称
     */
    private String fpusername;

    private String fdblflag;

    /**
     * 材料id
     */
    private String fmateriafid;

    /**
     * 楞型
     */
    private String fflutetype;

    /**
     * 下单时间
     */
    private Date fordertime;

    /**
     * 提货单号
     */
    private String fdeliveryorder;

    private String fboxmodel;

    private Double fboxwidth;

    private Double fboxlength;

    private Double fboxheight;

    private Double fmateriallength;

    private Double fmaterialwidth;

    /**
     * 材料名
     */
    private String fmaterialname;

    /**
     * 产品名
     */
    private String fgroupgoodname;

    /**
     * 下料规格
     */
    private String fmaterialRule;

    /**
     * 产品规格
     */
    private String fproductRule;

    /**
     * 订单数量
     */
    private Integer amount;

    /**
     * 订单数量
     */
    private Integer famount;

    /**
     * 订单状态(
     *	已付款:2
     *	已分发:3
     * 	部分入库：21
     已入库：22
     已配货：23
     已提货：24
     已装车：25
     已发车：26
     )
     */
    private String fstatus;

    /**
     * 经纬度
     */
    private String flnglat;

    /**
     * 支付时间
     */
    private Date fpaymenttime;

    /**
     * 地址街道
     */
    private String faddressdetail;

    /**
     * 省市区
     */
    private String fcodeprovince;


    public String getFlnglat() {
        return flnglat;
    }

    public void setFlnglat(String flnglat) {
        this.flnglat = flnglat;
    }

    public String getFstatus() {
        return fstatus;
    }

    public void setFstatus(String fstatus) {
        this.fstatus = fstatus;
    }

    public String getFdeliveryorder() {
        return fdeliveryorder;
    }

    public void setFdeliveryorder(String fdeliveryorder) {
        this.fdeliveryorder = fdeliveryorder;
    }

    public String getFcontactway() {
        return fcontactway;
    }

    public void setFcontactway(String fcontactway) {
        this.fcontactway = fcontactway;
    }


    public String getFflutetype() {
        return fflutetype;
    }

    public void setFflutetype(String fflutetype) {
        this.fflutetype = fflutetype;
    }

    public Date getFordertime() {
        return fordertime;
    }

    public void setFordertime(Date fordertime) {
        this.fordertime = fordertime;
    }

    public String getFmaterialRule() {
        return fmaterialRule;
    }

    public void setFmaterialRule(String fmaterialRule) {
        this.fmaterialRule = fmaterialRule;
    }

    public String getFproductRule() {
        return fproductRule;
    }

    public void setFproductRule(String fproductRule) {
        this.fproductRule = fproductRule;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getFamount() {
        return famount;
    }

    public void setFamount(Integer famount) {
        this.famount = famount;
    }

    public String getFconsignee() {
        return fconsignee;
    }

    public void setFconsignee(String fconsignee) {
        this.fconsignee = fconsignee;
    }

    public Date getFpaymenttime() {
        return fpaymenttime;
    }

    public void setFpaymenttime(Date fpaymenttime) {
        this.fpaymenttime = fpaymenttime;
    }

    public String getFaddressdetail() {
        return faddressdetail;
    }

    public void setFaddressdetail(String faddressdetail) {
        this.faddressdetail = faddressdetail;
    }

    public String getFcodeprovince() {
        return fcodeprovince;
    }

    public void setFcodeprovince(String fcodeprovince) {
        this.fcodeprovince = fcodeprovince;
    }

    public String getFgroupgoodname() {
        return fgroupgoodname;
    }

    public void setFgroupgoodname(String fgroupgoodname) {
        this.fgroupgoodname = fgroupgoodname;
    }

    public String getForderid() {
        return forderid;
    }

    public void setForderid(String forderid) {
        this.forderid = forderid;
    }

    public String getFchildorderid() {
        return fchildorderid;
    }

    public void setFchildorderid(String fchildorderid) {
        this.fchildorderid = fchildorderid;
    }

    public String getFpusername() {
        return fpusername;
    }

    public void setFpusername(String fpusername) {
        this.fpusername = fpusername;
    }

    public String getFmateriafid() {
        return fmateriafid;
    }

    public void setFmateriafid(String fmateriafid) {
        this.fmateriafid = fmateriafid;
    }

    public String getFboxmodel() {
        return fboxmodel;
    }

    public void setFboxmodel(String fboxmodel) {
        this.fboxmodel = fboxmodel;
    }

    public Double getFboxwidth() {
        return fboxwidth;
    }

    public void setFboxwidth(Double fboxwidth) {
        this.fboxwidth = fboxwidth;
    }

    public Double getFboxlength() {
        return fboxlength;
    }

    public void setFboxlength(Double fboxlength) {
        this.fboxlength = fboxlength;
    }

    public Double getFboxheight() {
        return fboxheight;
    }

    public void setFboxheight(Double fboxheight) {
        this.fboxheight = fboxheight;
    }

    public Double getFmateriallength() {
        return fmateriallength;
    }

    public void setFmateriallength(Double fmateriallength) {
        this.fmateriallength = fmateriallength;
    }

    public Double getFmaterialwidth() {
        return fmaterialwidth;
    }

    public void setFmaterialwidth(Double fmaterialwidth) {
        this.fmaterialwidth = fmaterialwidth;
    }

    public String getFmaterialname() {
        return fmaterialname;
    }

    public void setFmaterialname(String fmaterialname) {
        this.fmaterialname = fmaterialname;
    }

    public String getFdblflag() {
        return fdblflag;
    }

    public void setFdblflag(String fdblflag) {
        this.fdblflag = fdblflag;
    }

    @Override
    public String toString() {
        return "ForderInfoBO{" +
                "forderid='" + forderid + '\'' +
                ", fchildorderid='" + fchildorderid + '\'' +
                ", fcontactway='" + fcontactway + '\'' +
                ", fconsignee='" + fconsignee + '\'' +
                ", fcontactway='" + fcontactway + '\'' +
                ", fpusername='" + fpusername + '\'' +
                ", fdblflag='" + fdblflag + '\'' +
                ", fmateriafid='" + fmateriafid + '\'' +
                ", fflutetype='" + fflutetype + '\'' +
                ", fordertime=" + fordertime +
                ", fdeliveryorder='" + fdeliveryorder + '\'' +
                ", fboxmodel='" + fboxmodel + '\'' +
                ", fboxwidth=" + fboxwidth +
                ", fboxlength=" + fboxlength +
                ", fboxheight=" + fboxheight +
                ", fmateriallength=" + fmateriallength +
                ", fmaterialwidth=" + fmaterialwidth +
                ", fmaterialname='" + fmaterialname + '\'' +
                ", fgroupgoodname='" + fgroupgoodname + '\'' +
                ", fmaterialRule='" + fmaterialRule + '\'' +
                ", fproductRule='" + fproductRule + '\'' +
                ", amount=" + amount +
                ", famount=" + famount +
                ", fstatus='" + fstatus + '\'' +
                ", flnglat='" + flnglat + '\'' +
                ", fpaymenttime=" + fpaymenttime +
                ", faddressdetail='" + faddressdetail + '\'' +
                ", fcodeprovince='" + fcodeprovince + '\'' +
                '}';
    }
}
