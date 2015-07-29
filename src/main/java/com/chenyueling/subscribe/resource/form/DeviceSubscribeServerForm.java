package com.chenyueling.subscribe.resource.form;

import javax.ws.rs.QueryParam;

/**
 * Created by chenyueling on 2015/5/11.
 */
public class DeviceSubscribeServerForm extends BaseForm {
    private String id;
    @QueryParam("serverId")
    private String serverId;
    @QueryParam("deviceCode")
    private String deviceCode;

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerId() {
        return serverId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }
}
