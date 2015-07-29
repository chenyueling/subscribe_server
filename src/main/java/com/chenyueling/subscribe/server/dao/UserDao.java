package com.chenyueling.subscribe.server.dao;

import com.chenyueling.subscribe.server.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by chenyueling on 2015/4/20.
 */
/*@Transactional*/
@Component("userDao")
public interface UserDao {

    public void save(User user);


    //mybatis just pass one parameter
    public User findById(String id);

    public List<User> whereUser(@Param("where")String where,@Param("whereParam")String whereParam);

    // mybatis query must be return Java type or user custom define ResultMap
    public Long verifyExist(@Param("where")String where,@Param("whereParam")String whereParam);

    public User login(@Param("userName")String userName,@Param("password")String passowrd);

    public void update(@Param("user") User user);

    public List<User> getScrollData(@Param("sort") String sort,@Param("order")String order ,@Param("start") int start,@Param("end") int end );
    public Long getScrollDataCount();

}
