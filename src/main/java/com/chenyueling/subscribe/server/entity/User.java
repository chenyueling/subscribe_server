package com.chenyueling.subscribe.server.entity;

import java.util.Date;

/**
 * Created by chenyueling on 2015/4/20.
 */

/**
 * sql
 * create table tb_user(id varchar(36) NOT NULL,name varchar(100),password varchar(36),
 * lastLoginTime datetime,email varchar(100))
 */
public class User {

    private String id;
    private String username;
    private String name;
    private String password;
    private Date lastLoginTime;
    private String email;
    private Date createTime;


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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
