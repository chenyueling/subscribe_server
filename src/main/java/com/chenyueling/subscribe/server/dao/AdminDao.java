package com.chenyueling.subscribe.server.dao;

import com.chenyueling.subscribe.server.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenyueling on 2015/5/6.
 */
/*
@Transactional*/
@Component("adminDao")
public interface AdminDao {
    public Admin findById(String id);
    public Admin login(@Param("username") String username, @Param("password") String password);
    public void save(@Param("admin") Admin admin);
    public void delete(@Param("admin") Admin admin);

    public void update(@Param("admin") Admin admin);
}
