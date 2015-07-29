package com.chenyueling.subscribe.server.dao;

import com.chenyueling.subscribe.server.entity.Server;
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
@Component("serverDao")
public interface ServerDao {

    public void save(Server server);

    public Server findById(@Param("id")String id);

    public Server findByToken(@Param("token")String token);

    public void delete(Server server);


    public void update(@Param("server")Server server);




    /**
     * select * from tb_server where xxx=xxx oder by #{sortLine} #{asc/desc}
     *
     * @return
     */
    public List<Server> getScrollData(@Param("sort") String sort,@Param("order")String order ,@Param("start") int start,@Param("end") int end );
    public List<Server> getScrollDataByStatus(@Param("status") boolean status,@Param("sort") String sort,@Param("order")String order ,@Param("start") int start,@Param("end") int end );
    public List<Server> getScrollDataByUser(@Param("user") User user,@Param("sort") String sort,@Param("order")String order ,@Param("start") int start,@Param("end") int end );
    public Long getScrollDataByUserCount(@Param("user") User user);
    public List<Server> getScrollDataByUserAndStatus(@Param("user") User user,@Param("status") boolean status,@Param("sort") String sort,@Param("order")String order ,@Param("start") int start,@Param("end") int end );
    public List<Server> getScrollDataByAudit(@Param("audit") boolean status,@Param("sort") String sort,@Param("order")String order ,@Param("start") int start,@Param("end") int end );

    public Long getScrollDataByAuditCount(@Param("audit") boolean status);

    public Long getScrollDataCount();

}
