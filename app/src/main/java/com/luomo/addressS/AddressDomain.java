package com.luomo.addressS;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author :renpan
 * @version :v1.0
 * @class :AddressDomain
 * @date :2016-08-15 11:00
 * @description:
 */
public class AddressDomain implements Serializable{
    private String id;
    private String name;
    private String mobilePhone;

    /**
     * 0 为被选中
     * 1 是默认选中的
     */
    private String defaultFlag = "0";

    public AddressDomain() {
    }

    public AddressDomain(String id, String name, String mobilePhone, String defaultFlag) {
        this.id = id;
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.defaultFlag = defaultFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    @Override
    public String toString() {
        return "AddressDomain{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", defaultFlag='" + defaultFlag + '\'' +
                '}';
    }
}
