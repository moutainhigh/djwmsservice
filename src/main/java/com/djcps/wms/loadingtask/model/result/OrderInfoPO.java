package com.djcps.wms.loadingtask.model.result;

/**
 * 订单服务 子订单 返回实体类
 * 
 * @author WYB
 * @since 2018/3/20
 */
public class OrderInfoPO {
    /**
     * 产品名称
     */
    private String fgroupgoodname;
    /**
     * 订单号
     */
    private String fchildorderid;
    /**
     * 楞型
     */
    private String fflutetype;

    /**
     * 产品规格长
     */
    private Double fboxlength;

    /**
     * 产品规格宽
     */
    private Double fboxwidth;

    /**
     * 产品规格高
     */
    private Double fboxheight;

    /**
     * 下料规格长
     */
    private Double fmateriallength;

    /**
     * 下料规格宽
     */
    private Double fmaterialwidth;

    /**
     * 材料名称
     */
    private String fmaterialname;
    /**
     * 订单数量
     */
    private Integer famount;
    /**
     * 下料规格
     */
    private String fmaterialRule;

    /**
     * 产品规格
     */
    private String fproductRule;
    /**
     * 装车台数量
     */
    private Integer loadingAmount;
    /**
     * 获取订单状态
     */
    private Integer orderStatus;
    /**
     * 异常数量
     */
    private Integer abnomalAmount;

    public Integer getAbnomalAmount() {
        return abnomalAmount;
    }

    public void setAbnomalAmount(Integer abnomalAmount) {
        this.abnomalAmount = abnomalAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getLoadingAmount() {
        return loadingAmount;
    }

    public void setLoadingAmount(Integer loadingAmount) {
        this.loadingAmount = loadingAmount;
    }

    public String getFgroupgoodname() {
        return fgroupgoodname;
    }

    public void setFgroupgoodname(String fgroupgoodname) {
        this.fgroupgoodname = fgroupgoodname;
    }

    public String getFchildorderid() {
        return fchildorderid;
    }

    public void setFchildorderid(String fchildorderid) {
        this.fchildorderid = fchildorderid;
    }

    public String getFflutetype() {
        return fflutetype;
    }

    public void setFflutetype(String fflutetype) {
        this.fflutetype = fflutetype;
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

    public Integer getFamount() {
        return famount;
    }

    public void setFamount(Integer famount) {
        this.famount = famount;
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

    @Override
    public String toString() {
        return "OrderInfoPO [fgroupgoodname=" + fgroupgoodname + ", fchildorderid=" + fchildorderid + ", fflutetype="
                + fflutetype + ", fboxlength=" + fboxlength + ", fboxwidth=" + fboxwidth + ", fboxheight=" + fboxheight
                + ", fmateriallength=" + fmateriallength + ", fmaterialwidth=" + fmaterialwidth + ", fmaterialname="
                + fmaterialname + ", famount=" + famount + ", fmaterialRule=" + fmaterialRule + ", fproductRule="
                + fproductRule + ", loadingAmount=" + loadingAmount + ", orderStatus=" + orderStatus
                + ", abnomalAmount=" + abnomalAmount + "]";
    }

}