package com.chenyueling.subscribe.resource.form;

import javax.ws.rs.QueryParam;

/**
 * Created by chenyueling on 2015/5/9.
 */
public class AdminForm extends BaseForm{
    private String id;
    @QueryParam("username")
    private String username;
    @QueryParam("password")
    private String password;
    private String repassword;
    private String name;
    private String newPassword;

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

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
