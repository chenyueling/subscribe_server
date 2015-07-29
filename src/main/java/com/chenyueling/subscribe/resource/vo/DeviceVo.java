package com.chenyueling.subscribe.resource.vo;

import com.chenyueling.subscribe.common.DateUtis;
import com.chenyueling.subscribe.server.entity.Device;
import com.chenyueling.subscribe.server.entity.User;

import java.util.Date;

/**
 * Created by chenyueling on 2015/5/1.
 */
public class DeviceVo {

    private String id;
    private String imei;
    private String code;
    private String lastLoginTime;
    private int push_setting;
    private String username;
    private String createTime;

    public DeviceVo() {
    }

    public DeviceVo(Device device) {
        this.id = device.getId();
        this.imei = device.getImei();
        this.code = device.getCode();
        this.lastLoginTime = device.getLastLoginTime() == null? "first Login": DateUtis.formatDate(device.getLastLoginTime());
        this.push_setting = device.getPush_setting();
        this.username = device.getUser() == null? "Unbound User": device.getUser().getUsername();
        this.createTime = device.getCreateTime() == null? "":DateUtis.formatDate(device.getCreateTime());
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getPush_setting() {
        return push_setting;
    }

    public void setPush_setting(int push_setting) {
        this.push_setting = push_setting;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
