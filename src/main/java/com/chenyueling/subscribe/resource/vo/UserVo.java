package com.chenyueling.subscribe.resource.vo;

import com.chenyueling.subscribe.common.DateUtis;
import com.chenyueling.subscribe.server.entity.User;

import java.util.Date;

/**
 * Created by chenyueling on 2015/5/6.
 */
public class UserVo {
    private String id;
    private String username;
    private String name;
    private String password;
    private String lastLoginTime;
    private String email;
    private String createTime;

    public UserVo() {
    }

    public UserVo(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.password = user.getPassword();
        Date date = user.getLastLoginTime();
        this.lastLoginTime = date != null? DateUtis.formatDate(date):"first login";
        this.email = user.getEmail();
        date = user.getCreateTime();
        this.createTime = date != null? DateUtis.formatDate(date):"";
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
