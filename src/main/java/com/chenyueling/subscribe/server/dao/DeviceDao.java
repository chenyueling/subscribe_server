package com.chenyueling.subscribe.server.dao;

import com.chenyueling.subscribe.server.entity.Device;
import com.chenyueling.subscribe.server.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by chenyueling on 2015/4/20.
 */

@Transactional
@Component("deviceDao")
public interface DeviceDao {
    public Device findById(@Param("id")String id);
    public void save(Device device);
    public Device findByIMEI(@Param("imei")String imei);
    public Device findByDeviceCode(@Param("deviceCode")String deviceCode);
    public void update(@Param("device")Device device);

    public List<Device> getScrollDataByUser(@Param("user")User user,@Param("sort") String sort,@Param("order")String order ,@Param("start") int start,@Param("end") int end );
    public Long getScrollDataByUserCount(@Param("user")User user);

    List<Device> getScrollData(@Param("sort") String sort,@Param("order")String order ,@Param("start") int start,@Param("end") int end);
    public Long getScrollDataCount();
}
