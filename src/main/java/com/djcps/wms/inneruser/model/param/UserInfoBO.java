package com.djcps.wms.inneruser.model.param;

/**
 *
 * @author Chengw
 * @version 1.0.0
 * @since 2018/5/15 10:03.
 */
public class UserInfoBO {

    /**
     * 类型代码
     */
    private String typeCode;

    /**
     * 公司id
     */
    private String partnerId;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        typeCode = typeCode;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return "UserInfoBO{" +
                "typeCode='" + typeCode + '\'' +
                ", partnerId='" + partnerId + '\'' +
                '}';
    }
}
