package com.chenyueling.subscribe.server.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by chenyueling on 2015/4/20.
 */

/**
 * sql
 * craete table tb_service (id varchar(36) NOT NULL, name varchar(200),createTime datetime,url varchar(500),
 * token varchar(36) , status boolean,PRIMARY KEY (`id`),user varchar(36) foreign key peferences tb_user(id))
 */
public class Server {
    private String id ;
    private String name;
    private Date createTime;
    private String token;
    //判断这个服务是共有还是私有的.
    private boolean status;
    private boolean audit;
    //备注
    private String tip;
    private User user;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public boolean isAudit() {
        return audit;
    }

    public void setAudit(boolean audit) {
        this.audit = audit;
    }
}
