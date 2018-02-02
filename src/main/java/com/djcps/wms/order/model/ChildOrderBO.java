package com.djcps.wms.order.model;

/**
 * 订单服务 子订单 返回实体类
 * @author Chengw
 * @since 2018/1/31 16:04.
 */
public class ChildOrderBO {

    /**
     * 订单号
     */
    private String forderId;

    /**
     * 子订单号
     */
    private String fchildorderid;

    /**
     * 母账户名称
     */
    private String fpusername;

    /**
     * 子账户名称
     */
    private String fcusername;

    /**
     * 团购商品id
     */
    private String fgroupgoodid;

    /**
     * 团购商品
     */
    private String fgroupgoodname;

    /**
     * 产品单价
     */
    private Double funitprice;
    /**
     * 真实价格（最终价格）
     */
    private Double fsmallprice;
    /**
     * 金额
     */
    private Double famountprice;

    /**
     * 订单状态
     */
    private Integer fstatus;

    /**
     * 供应商ID
     */
    private String fmanufacturer;

    /**
     * 材料ID
     */
    private String fmateriafid;

    /**
     * 运单ID
     */
    private String ftransportsheet;

    /**
     * 楞型
     */
    private String fflutetype;

    /**
     * 箱型
     */
    private String fboxmodel;

    /**
     * 规格长
     */
    private Double fboxlength;

    /**
     * 规格宽
     */
    private Double fboxwidth;

    /**
     * 规格高
     */
    private Double fboxheight;

    /**
     * 料规格长加系数
     */
    private Double fmateriallength;

    /**
     * 下料规格宽加系数
     */
    private Double fmaterialwidth;

    /**
     * 材料名称
     */
    private String fmaterialname;

    /**
     * 压线方式
     */
    private String fstavetype;

    /**
     * 成型方式
     */
    private String fseries;

    /**
     * 横向压线
     */
    private String fhline;

    /**
     * 纵向压线
     */
    private String fvline;

    /**
     * 横向压线公式
     */
    private String fhlineformula;

    /**
     * 纵向压线公式
     */
    private String fvlineformula;

    /**
     * 只数
     */
    private Integer famount;
    /**
     * 片数
     */
    private Integer famountpiece;

    /**
     * 层数
     */
    private Integer flayer;

    private String fhformula;

    private String fvformula;

    /**
     * 平方米面积
     */
    private Double fproductarea;

    /**
     * 营销方案
     */
    private String fmarktingplanid;

    /**
     * 营销方案（改）
     */
    private String fmktplanchangeid;

    /**
     * 订单类型
     */
    private Integer fordertype;

    /**
     * 接收人
     */
    private String fconsignee;

    /**
     * 接收人联系方式
     */
    private String fcontactway;

    /**
     * 省市区
     */
    private String fcodeprovince;

    /**
     * 交期
     */
    private String fdelivery;

    /**
     * 交期 格式化
     */
    private String fdeliveryString;

    /**
     * 分发时间
     */
    private String fdistime;

    /**
     * 签收时间
     */
    private String fsignintime;

    /**
     * 坐标
     */
    private String flnglat;

    /**
     * 详细地址
     */
    private String faddressdetail;

    /**
     * 地址备注
     */
    private String faddressremark;

    /**
     * 操作时间
     */
    private String foperatetime;

    /**
     * 操作人
     */
    private String foperator;

    /**
     * 区域拆分键
     */
    private String fkeyarea;

    /**
     * 急单
     */
    private Integer furgencyorder;

    /**
     * 赠送积分
     */
    private Integer fgiveintegral;

    /**
     * 删除状态
     */
    private String fdeletestatus;

    /**
     * 支付类型
     */
    private Integer fpaytype;

    /**
     * 是否分发
     */
    private Integer fdbflage;

    public String getForderId() {
        return forderId;
    }

    public void setForderId(String forderId) {
        this.forderId = forderId;
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

    public String getFcusername() {
        return fcusername;
    }

    public void setFcusername(String fcusername) {
        this.fcusername = fcusername;
    }

    public String getFgroupgoodid() {
        return fgroupgoodid;
    }

    public void setFgroupgoodid(String fgroupgoodid) {
        this.fgroupgoodid = fgroupgoodid;
    }

    public String getFgroupgoodname() {
        return fgroupgoodname;
    }

    public void setFgroupgoodname(String fgroupgoodname) {
        this.fgroupgoodname = fgroupgoodname;
    }

    public Double getFunitprice() {
        return funitprice;
    }

    public void setFunitprice(Double funitprice) {
        this.funitprice = funitprice;
    }

    public Double getFsmallprice() {
        return fsmallprice;
    }

    public void setFsmallprice(Double fsmallprice) {
        this.fsmallprice = fsmallprice;
    }

    public Double getFamountprice() {
        return famountprice;
    }

    public void setFamountprice(Double famountprice) {
        this.famountprice = famountprice;
    }

    public Integer getFstatus() {
        return fstatus;
    }

    public void setFstatus(Integer fstatus) {
        this.fstatus = fstatus;
    }

    public String getFmanufacturer() {
        return fmanufacturer;
    }

    public void setFmanufacturer(String fmanufacturer) {
        this.fmanufacturer = fmanufacturer;
    }

    public String getFmateriafid() {
        return fmateriafid;
    }

    public void setFmateriafid(String fmateriafid) {
        this.fmateriafid = fmateriafid;
    }

    public String getFtransportsheet() {
        return ftransportsheet;
    }

    public void setFtransportsheet(String ftransportsheet) {
        this.ftransportsheet = ftransportsheet;
    }

    public String getFflutetype() {
        return fflutetype;
    }

    public void setFflutetype(String fflutetype) {
        this.fflutetype = fflutetype;
    }

    public String getFboxmodel() {
        return fboxmodel;
    }

    public void setFboxmodel(String fboxmodel) {
        this.fboxmodel = fboxmodel;
    }

    public Double getFboxlength() {
        return fboxlength;
    }

    public void setFboxlength(Double fboxlength) {
        this.fboxlength = fboxlength;
    }

    public Double getFboxwidth() {
        return fboxwidth;
    }

    public void setFboxwidth(Double fboxwidth) {
        this.fboxwidth = fboxwidth;
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

    public String getFstavetype() {
        return fstavetype;
    }

    public void setFstavetype(String fstavetype) {
        this.fstavetype = fstavetype;
    }

    public String getFseries() {
        return fseries;
    }

    public void setFseries(String fseries) {
        this.fseries = fseries;
    }

    public String getFhline() {
        return fhline;
    }

    public void setFhline(String fhline) {
        this.fhline = fhline;
    }

    public String getFvline() {
        return fvline;
    }

    public void setFvline(String fvline) {
        this.fvline = fvline;
    }

    public String getFhlineformula() {
        return fhlineformula;
    }

    public void setFhlineformula(String fhlineformula) {
        this.fhlineformula = fhlineformula;
    }

    public String getFvlineformula() {
        return fvlineformula;
    }

    public void setFvlineformula(String fvlineformula) {
        this.fvlineformula = fvlineformula;
    }

    public Integer getFamount() {
        return famount;
    }

    public void setFamount(Integer famount) {
        this.famount = famount;
    }

    public Integer getFamountpiece() {
        return famountpiece;
    }

    public void setFamountpiece(Integer famountpiece) {
        this.famountpiece = famountpiece;
    }

    public Integer getFlayer() {
        return flayer;
    }

    public void setFlayer(Integer flayer) {
        this.flayer = flayer;
    }

    public String getFhformula() {
        return fhformula;
    }

    public void setFhformula(String fhformula) {
        this.fhformula = fhformula;
    }

    public String getFvformula() {
        return fvformula;
    }

    public void setFvformula(String fvformula) {
        this.fvformula = fvformula;
    }

    public Double getFproductarea() {
        return fproductarea;
    }

    public void setFproductarea(Double fproductarea) {
        this.fproductarea = fproductarea;
    }

    public String getFmarktingplanid() {
        return fmarktingplanid;
    }

    public void setFmarktingplanid(String fmarktingplanid) {
        this.fmarktingplanid = fmarktingplanid;
    }

    public String getFmktplanchangeid() {
        return fmktplanchangeid;
    }

    public void setFmktplanchangeid(String fmktplanchangeid) {
        this.fmktplanchangeid = fmktplanchangeid;
    }

    public Integer getFordertype() {
        return fordertype;
    }

    public void setFordertype(Integer fordertype) {
        this.fordertype = fordertype;
    }

    public String getFconsignee() {
        return fconsignee;
    }

    public void setFconsignee(String fconsignee) {
        this.fconsignee = fconsignee;
    }

    public String getFcontactway() {
        return fcontactway;
    }

    public void setFcontactway(String fcontactway) {
        this.fcontactway = fcontactway;
    }

    public String getFcodeprovince() {
        return fcodeprovince;
    }

    public void setFcodeprovince(String fcodeprovince) {
        this.fcodeprovince = fcodeprovince;
    }

    public String getFdelivery() {
        return fdelivery;
    }

    public void setFdelivery(String fdelivery) {
        this.fdelivery = fdelivery;
    }

    public String getFdeliveryString() {
        return fdeliveryString;
    }

    public void setFdeliveryString(String fdeliveryString) {
        this.fdeliveryString = fdeliveryString;
    }

    public String getFdistime() {
        return fdistime;
    }

    public void setFdistime(String fdistime) {
        this.fdistime = fdistime;
    }

    public String getFsignintime() {
        return fsignintime;
    }

    public void setFsignintime(String fsignintime) {
        this.fsignintime = fsignintime;
    }

    public String getFlnglat() {
        return flnglat;
    }

    public void setFlnglat(String flnglat) {
        this.flnglat = flnglat;
    }

    public String getFaddressdetail() {
        return faddressdetail;
    }

    public void setFaddressdetail(String faddressdetail) {
        this.faddressdetail = faddressdetail;
    }

    public String getFaddressremark() {
        return faddressremark;
    }

    public void setFaddressremark(String faddressremark) {
        this.faddressremark = faddressremark;
    }

    public String getFoperatetime() {
        return foperatetime;
    }

    public void setFoperatetime(String foperatetime) {
        this.foperatetime = foperatetime;
    }

    public String getFoperator() {
        return foperator;
    }

    public void setFoperator(String foperator) {
        this.foperator = foperator;
    }

    public String getFkeyarea() {
        return fkeyarea;
    }

    public void setFkeyarea(String fkeyarea) {
        this.fkeyarea = fkeyarea;
    }

    public Integer getFurgencyorder() {
        return furgencyorder;
    }

    public void setFurgencyorder(Integer furgencyorder) {
        this.furgencyorder = furgencyorder;
    }

    public Integer getFgiveintegral() {
        return fgiveintegral;
    }

    public void setFgiveintegral(Integer fgiveintegral) {
        this.fgiveintegral = fgiveintegral;
    }

    public String getFdeletestatus() {
        return fdeletestatus;
    }

    public void setFdeletestatus(String fdeletestatus) {
        this.fdeletestatus = fdeletestatus;
    }

    public Integer getFpaytype() {
        return fpaytype;
    }

    public void setFpaytype(Integer fpaytype) {
        this.fpaytype = fpaytype;
    }

    public Integer getFdbflage() {
        return fdbflage;
    }

    public void setFdbflage(Integer fdbflage) {
        this.fdbflage = fdbflage;
    }

    @Override
    public String toString() {
        return "ChildOrderBO{" +
                "forderId='" + forderId + '\'' +
                ", fchildorderid='" + fchildorderid + '\'' +
                ", fpusername='" + fpusername + '\'' +
                ", fcusername='" + fcusername + '\'' +
                ", fgroupgoodid='" + fgroupgoodid + '\'' +
                ", fgroupgoodname='" + fgroupgoodname + '\'' +
                ", funitprice=" + funitprice +
                ", fsmallprice=" + fsmallprice +
                ", famountprice=" + famountprice +
                ", fstatus=" + fstatus +
                ", fmanufacturer='" + fmanufacturer + '\'' +
                ", fmateriafid='" + fmateriafid + '\'' +
                ", ftransportsheet='" + ftransportsheet + '\'' +
                ", fflutetype='" + fflutetype + '\'' +
                ", fboxmodel='" + fboxmodel + '\'' +
                ", fboxlength=" + fboxlength +
                ", fboxwidth=" + fboxwidth +
                ", fboxheight=" + fboxheight +
                ", fmateriallength=" + fmateriallength +
                ", fmaterialwidth=" + fmaterialwidth +
                ", fmaterialname='" + fmaterialname + '\'' +
                ", fstavetype='" + fstavetype + '\'' +
                ", fseries='" + fseries + '\'' +
                ", fhline='" + fhline + '\'' +
                ", fvline='" + fvline + '\'' +
                ", fhlineformula='" + fhlineformula + '\'' +
                ", fvlineformula='" + fvlineformula + '\'' +
                ", famount=" + famount +
                ", famountpiece=" + famountpiece +
                ", flayer=" + flayer +
                ", fhformula='" + fhformula + '\'' +
                ", fvformula='" + fvformula + '\'' +
                ", fproductarea=" + fproductarea +
                ", fmarktingplanid='" + fmarktingplanid + '\'' +
                ", fmktplanchangeid='" + fmktplanchangeid + '\'' +
                ", fordertype=" + fordertype +
                ", fconsignee='" + fconsignee + '\'' +
                ", fcontactway='" + fcontactway + '\'' +
                ", fcodeprovince='" + fcodeprovince + '\'' +
                ", fdelivery='" + fdelivery + '\'' +
                ", fdeliveryString='" + fdeliveryString + '\'' +
                ", fdistime='" + fdistime + '\'' +
                ", fsignintime='" + fsignintime + '\'' +
                ", flnglat='" + flnglat + '\'' +
                ", faddressdetail='" + faddressdetail + '\'' +
                ", faddressremark='" + faddressremark + '\'' +
                ", foperatetime='" + foperatetime + '\'' +
                ", foperator='" + foperator + '\'' +
                ", fkeyarea='" + fkeyarea + '\'' +
                ", furgencyorder=" + furgencyorder +
                ", fgiveintegral=" + fgiveintegral +
                ", fdeletestatus='" + fdeletestatus + '\'' +
                ", fpaytype=" + fpaytype +
                '}';
    }
}
