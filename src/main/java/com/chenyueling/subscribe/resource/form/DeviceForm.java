package com.chenyueling.subscribe.resource.form;

/**
 * Created by chenyueling on 2015/5/13.
 */
public class DeviceForm extends BaseForm{
    public String id;
    public String imei;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
