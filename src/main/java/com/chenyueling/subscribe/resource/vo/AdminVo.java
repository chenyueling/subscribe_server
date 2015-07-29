package com.chenyueling.subscribe.resource.vo;

import com.chenyueling.subscribe.common.DateUtis;
import com.chenyueling.subscribe.common.StringUtil;
import com.chenyueling.subscribe.server.entity.Admin;

import java.util.Date;

/**
 * Created by chenyueling on 2015/5/6.
 */
public class AdminVo {
    private String id;
    private String username;
    private String password;
    private String lastLoginTime;
    private String createTime;
    private String name;

    public AdminVo() {
    }

    public AdminVo(Admin admin) {
        this.id = admin.getId();
        this.username = admin.getUserName();
        this.password = admin.getPassword();
        Date date = admin.getLastLoginTime();
        this.lastLoginTime = date != null? DateUtis.formatDate(date):"first login";
        date = admin.getCreateTime();
        this.createTime = date != null? DateUtis.formatDate(date):"";
        this.name = admin.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
