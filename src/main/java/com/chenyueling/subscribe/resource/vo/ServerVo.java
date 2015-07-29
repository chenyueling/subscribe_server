package com.chenyueling.subscribe.resource.vo;

import com.chenyueling.subscribe.common.DateUtis;
import com.chenyueling.subscribe.server.entity.Server;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by chenyueling on 2015/4/29.
 */
public class ServerVo implements Comparable{
    private String id ;
    private String name;
    private String createTime;
    private String token;
    //判断这个服务是共有还是私有的.
    private String status;
    //备注
    private String tip;
    private String userName;
    private String audit;
    //用户是否关注
    private boolean subscribeStatus = false;

    public ServerVo() {
    }

    public ServerVo(Server server) {
        this.id = server.getId();
        this.name = server.getName();

        this.createTime = DateUtis.formatDate(server.getCreateTime());
        this.token = server.getToken();
        this.status = server.isStatus() ? "public":"private";
        this.audit = server.isAudit() ? "pass":"not through";
        this.tip = server.getTip();
        this.userName = server.getUser().getName();
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isSubscribeStatus() {
        return subscribeStatus;
    }

    public void setSubscribeStatus(boolean subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(obj instanceof ServerVo){
            ServerVo serverVo =(ServerVo)obj;
//          if(user.id = this.id) return true; // 只比较id
            // 比较id和username 一致时才返回true 之后再去比较 hashCode
            if(serverVo.getId().equals(this.getId()))
                return true;
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        String createTime = ((ServerVo)o).getCreateTime();
        Date date = null;
        Date date2 = null;
        try {
             date = DateUtis.sdf.parse(createTime);
             date2 = DateUtis.sdf.parse(this.getCreateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat dateFormat = new SimpleDateFormat("");
        return (int)((date != null ? date.getTime() : 0) - (date2 != null ? date2.getTime() : 0));
    }

    public static void main(String[] args) {
        String time = "2015-04-24 04:19:21";
        try {
            Date date = DateUtis.sdf.parse(time);
            System.out.println(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
