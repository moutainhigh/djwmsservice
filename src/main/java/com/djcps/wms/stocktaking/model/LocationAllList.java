package com.djcps.wms.stocktaking.model;

import java.util.List;

public class LocationAllList {
    private List<LocationInfo>  LocationInfo;

    public List<LocationInfo> getLocationInfo() {
        return LocationInfo;
    }

    public void setLocationInfo(List<LocationInfo> locationInfo) {
        LocationInfo = locationInfo;
    }

    @Override
    public String toString() {
        return "LocationAllList{" +
                "LocationInfo=" + LocationInfo +
                '}';
    }
}
