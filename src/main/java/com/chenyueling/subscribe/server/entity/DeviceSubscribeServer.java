package com.chenyueling.subscribe.server.entity;

import java.util.Date;

/**
 * Created by chenyueling on 2015/5/2.
 */
public class DeviceSubscribeServer {
    private String id;
    private Server server;
    private Device device;
    private Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
