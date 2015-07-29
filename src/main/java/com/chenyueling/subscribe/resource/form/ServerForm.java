package com.chenyueling.subscribe.resource.form;

import javax.ws.rs.QueryParam;

/**
 * Created by chenyueling on 2015/4/24.
 */
public class ServerForm extends BaseForm{

    public ServerForm() {
    }

    private String id;
    private String name;
    private String tip;
    private String status;

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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
