package com.chenyueling.subscribe.server.entity;

import java.util.Date;

/**
 * Created by chenyueling on 2015/4/20.
 */
public class Device {

    private String id;
    private String imei;
    private String code;
    private Date lastLoginTime;
    private int push_setting;
    private Date createTime;
    private User user;
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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getPush_setting() {
        return push_setting;
    }

    public void setPush_setting(int push_setting) {
        this.push_setting = push_setting;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
