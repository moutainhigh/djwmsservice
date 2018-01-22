package com.djcps.wms.stocktaking.model;

/**
 * @title:从订单服务拿过来带f的参数
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/22
 **/
public class FOrderInfoBO {
    private String forderid;

    private String fchildorderid;

    private String fpusername;

    private String fmateriafid;

    private String fboxmodel;

    private Double fboxwidth;

    private Double fboxlength;

    private Double fboxheight;

    private Double fmateriallength;

    private Double fmaterialwidth;

    private String fmaterialname;

    private String fgroupgoodname;

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

    @Override
    public String toString() {
        return "FOrderInfoBO{" +
                "forderid='" + forderid + '\'' +
                ", fchildorderid='" + fchildorderid + '\'' +
                ", fpusername='" + fpusername + '\'' +
                ", fmateriafid='" + fmateriafid + '\'' +
                ", fboxmodel='" + fboxmodel + '\'' +
                ", fboxwidth=" + fboxwidth +
                ", fboxlength=" + fboxlength +
                ", fboxheight=" + fboxheight +
                ", fmateriallength=" + fmateriallength +
                ", fmaterialwidth=" + fmaterialwidth +
                ", fmaterialname='" + fmaterialname + '\'' +
                ", fgroupgoodname='" + fgroupgoodname + '\'' +
                '}';
    }
}
