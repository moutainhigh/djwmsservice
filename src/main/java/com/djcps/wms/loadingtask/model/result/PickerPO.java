package com.djcps.wms.loadingtask.model.result;

/**
 *提货员对象
 * @author:wzy
 * @date:2018/3/22
 **/
public class PickerPO {

    /**
     * 提货员id
     */
    private String pickerId;

    /**
     * 提货员名称
     */
    private String pickerName;

    /**
     * 提货员联系方式
     */
    private String pickerPhone;

    public String getPickerId() {
        return pickerId;
    }

    public void setPickerId(String pickerId) {
        this.pickerId = pickerId;
    }

    public String getPickerName() {
        return pickerName;
    }

    public void setPickerName(String pickerName) {
        this.pickerName = pickerName;
    }

    public String getPickerPhone() {
        return pickerPhone;
    }

    public void setPickerPhone(String pickerPhone) {
        this.pickerPhone = pickerPhone;
    }

    @Override
    public String toString() {
        return "PickerPO{" +
                "pickerId='" + pickerId + '\'' +
                ", pickerName='" + pickerName + '\'' +
                ", pickerPhone='" + pickerPhone + '\'' +
                '}';
    }
}
