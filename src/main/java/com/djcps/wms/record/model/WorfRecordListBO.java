package com.djcps.wms.record.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseBO;

/**
* @author panyang
* @version 创建时间：2018年4月17日 上午10:57:12
* 类说明
*/
public class WorfRecordListBO extends BaseBO implements Serializable {
	
	
//	private static final long  seriaVersionUI
  private int rowid;
  
  private String fid;

public int getRowid() {
	return rowid;
}


public void setRowid(int rowid) {
	this.rowid = rowid;
}

public String getFid() {
	return fid;
}

public void setFid(String fid) {
	this.fid = fid;
}
	

@Override
public String toString() {
	return "WorfRecordListBO [rowid=" + rowid + ", fid=" + fid + "]";
}
	
	
	
	
	
	
}
