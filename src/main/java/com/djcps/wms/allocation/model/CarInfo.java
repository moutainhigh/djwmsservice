package com.djcps.wms.allocation.model;

import java.io.Serializable;

public class CarInfo implements Serializable{
	
	private String cheliangguige;
	private String chepaihao;
	private String zhuangzailv;
	private String changyunquyu;
	private String chenliangzhuangtai;
	private String sijixingming;
	private String lianxifangshi;
	private String suoshuchedui;
	private String suoshuerjichang;
	private String cheliangleixing;
	private String changkuanggao;
	private String zuidayunshutiji;
	private String zuidayunshuzhongliang;
	private String suoshuhezuofang;
	
	public CarInfo(){
		this.cheliangguige = "6.8米";
		this.chepaihao = "浙C2D66";
		this.zhuangzailv = "100%";
		this.changyunquyu = "苍南";
		this.chenliangzhuangtai="已签到";
		this.sijixingming="郭全凯";
		this.lianxifangshi="15157780633";
		this.suoshuchedui="东经包装";
		this.suoshuerjichang="东林";
		this.cheliangleixing="箱式货车";
		this.changkuanggao="长2米  宽3米  高3米";
		this.zuidayunshutiji="100立方米";
		this.zuidayunshuzhongliang="100吨";
		this.suoshuhezuofang="东林";
	}
	
	public CarInfo(String sijixingming){
		this.cheliangguige = "6.8米";
		this.chepaihao = "浙C2D66";
		this.zhuangzailv = "100%";
		this.changyunquyu = "苍南";
		this.chenliangzhuangtai="已签到";
		this.sijixingming=sijixingming;
		this.lianxifangshi="15157780633";
		this.suoshuchedui="东经包装";
		this.suoshuerjichang="东林";
		this.cheliangleixing="箱式货车";
		this.changkuanggao="长2米  宽3米  高3米";
		this.zuidayunshutiji="100立方米";
		this.zuidayunshuzhongliang="100吨";
		this.suoshuhezuofang="东林";
	}
	
	public String getCheliangguige() {
		return cheliangguige;
	}
	public void setCheliangguige(String cheliangguige) {
		this.cheliangguige = cheliangguige;
	}
	public String getChepaihao() {
		return chepaihao;
	}
	public void setChepaihao(String chepaihao) {
		this.chepaihao = chepaihao;
	}
	public String getZhuangzailv() {
		return zhuangzailv;
	}
	public void setZhuangzailv(String zhuangzailv) {
		this.zhuangzailv = zhuangzailv;
	}
	public String getChangyunquyu() {
		return changyunquyu;
	}
	public void setChangyunquyu(String changyunquyu) {
		this.changyunquyu = changyunquyu;
	}
	public String getChenliangzhuangtai() {
		return chenliangzhuangtai;
	}
	public void setChenliangzhuangtai(String chenliangzhuangtai) {
		this.chenliangzhuangtai = chenliangzhuangtai;
	}
	public String getSijixingming() {
		return sijixingming;
	}
	public void setSijixingming(String sijixingming) {
		this.sijixingming = sijixingming;
	}
	public String getLianxifangshi() {
		return lianxifangshi;
	}
	public void setLianxifangshi(String lianxifangshi) {
		this.lianxifangshi = lianxifangshi;
	}
	public String getSuoshuchedui() {
		return suoshuchedui;
	}
	public void setSuoshuchedui(String suoshuchedui) {
		this.suoshuchedui = suoshuchedui;
	}
	public String getSuoshuerjichang() {
		return suoshuerjichang;
	}
	public void setSuoshuerjichang(String suoshuerjichang) {
		this.suoshuerjichang = suoshuerjichang;
	}
	public String getCheliangleixing() {
		return cheliangleixing;
	}
	public void setCheliangleixing(String cheliangleixing) {
		this.cheliangleixing = cheliangleixing;
	}
	public String getChangkuanggao() {
		return changkuanggao;
	}
	public void setChangkuanggao(String changkuanggao) {
		this.changkuanggao = changkuanggao;
	}
	public String getZuidayunshutiji() {
		return zuidayunshutiji;
	}
	public void setZuidayunshutiji(String zuidayunshutiji) {
		this.zuidayunshutiji = zuidayunshutiji;
	}
	public String getZuidayunshuzhongliang() {
		return zuidayunshuzhongliang;
	}
	public void setZuidayunshuzhongliang(String zuidayunshuzhongliang) {
		this.zuidayunshuzhongliang = zuidayunshuzhongliang;
	}
	public String getSuoshuhezuofang() {
		return suoshuhezuofang;
	}
	public void setSuoshuhezuofang(String suoshuhezuofang) {
		this.suoshuhezuofang = suoshuhezuofang;
	}
	@Override
	public String toString() {
		return "CarInfo [cheliangguige=" + cheliangguige + ", chepaihao=" + chepaihao + ", zhuangzailv=" + zhuangzailv
				+ ", changyunquyu=" + changyunquyu + ", chenliangzhuangtai=" + chenliangzhuangtai + ", sijixingming="
				+ sijixingming + ", lianxifangshi=" + lianxifangshi + ", suoshuchedui=" + suoshuchedui
				+ ", suoshuerjichang=" + suoshuerjichang + ", cheliangleixing=" + cheliangleixing + ", changkuanggao="
				+ changkuanggao + ", zuidayunshutiji=" + zuidayunshutiji + ", zuidayunshuzhongliang="
				+ zuidayunshuzhongliang + ", suoshuhezuofang=" + suoshuhezuofang + "]";
	}
	
}
