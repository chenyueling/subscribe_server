package com.chenyueling.subscribe.resource.vo;

import com.chenyueling.subscribe.common.DateUtis;
import com.chenyueling.subscribe.server.entity.Article;
import com.chenyueling.subscribe.server.entity.Server;

import java.util.Date;

/**
 * Created by chenyueling on 2015/5/12.
 */
public class ArticleVo {
    private String id;
    private String title;
    private String url;
    private String createTime;

    private String serverName;
    private String serverId;

    public ArticleVo() {
    }


    public ArticleVo(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.url = article.getUrl();
        Date date = article.getCreateTime();
        this.createTime = date != null ? DateUtis.formatDate(date):"";
        Server server = article.getServer();
        this.serverName = server.getName();
        this.serverId = server.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
}
