package com.chenyueling.subscribe.server.dao;

import com.chenyueling.subscribe.server.entity.Article;
import com.chenyueling.subscribe.server.entity.Device;
import com.chenyueling.subscribe.server.entity.Server;
import com.chenyueling.subscribe.server.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenyueling on 2015/4/20.
 *//*
@Transactional*/
@Component("articleDao")
public interface ArticleDao {

    public void save(Article article);

    public List<Article> findByDeviceCode(@Param("deviceCode")String deviceCode,@Param("sort") String sort,@Param("order")String order ,@Param("start") int start,@Param("end") int end );
    public List<Article> findByDevice(@Param("device")Device device,@Param("sort") String sort,@Param("order")String order ,@Param("start") int start,@Param("end") int end );

    public List<Article> getScrollData(@Param("sort") String sort,@Param("order")String order ,@Param("start") int start,@Param("end") int end );
    public Long getScrollDataCount();

    public List<Article> getScrollDataByUser(@Param("user")User user,@Param("sort") String sort,@Param("order")String order ,@Param("start") int start,@Param("end") int end );
    public Long getScrollDataByUserCount(@Param("user")User use);

    public List<Article> findByServer(@Param("server")Server server);

    public void delete(@Param("article")Article article);
}
