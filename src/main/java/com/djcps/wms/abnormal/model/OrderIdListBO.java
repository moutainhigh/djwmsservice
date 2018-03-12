package com.djcps.wms.abnormal.model;



import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * 订单idList参数接收
 * @company:djwms
 * @author:zdx
 * @date:2018年3月7日
 */
public class OrderIdListBO {

    /**
     *合作方id
     */
	@NotBlank
    private String partnerId;

    /**
     * 订单idlist
     */
    @NotEmpty
    List<String> list;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "OrderIdListBO{" +
                "partnerId='" + partnerId + '\'' +
                ", list=" + list +
                '}';
    }
}
