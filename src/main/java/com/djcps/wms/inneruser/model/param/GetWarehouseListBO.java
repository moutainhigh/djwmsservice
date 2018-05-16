package com.djcps.wms.inneruser.model.param;

import java.util.List;

/**
 * 获取所属部门列表参数类
 * @author wzy
 * @date 2018/4/23
 **/
public class GetWarehouseListBO {
    private List<String> list;

    private String partnerId;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return "GetWarehouseListBO{" +
                "list=" + list +
                ", partnerId='" + partnerId + '\'' +
                '}';
    }
}
