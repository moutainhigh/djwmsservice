package com.djcps.wms.stocktaking.model;

import java.util.List;

public class ListAreaAndLocInfoByPartPO {

   private List<AreaAndLocInfoByPartPO> data;

    public List<AreaAndLocInfoByPartPO> getData() {
        return data;
    }

    public void setData(List<AreaAndLocInfoByPartPO> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ListAreaAndLocInfoByPartPO{" +
                "data=" + data +
                '}';
    }
}
