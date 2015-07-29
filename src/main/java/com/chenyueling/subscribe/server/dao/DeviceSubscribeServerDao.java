package com.chenyueling.subscribe.server.dao;

import com.chenyueling.subscribe.server.entity.Device;
import com.chenyueling.subscribe.server.entity.DeviceSubscribeServer;
import com.chenyueling.subscribe.server.entity.Server;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenyueling on 2015/5/2.
 */
@Transactional
@Component("deviceSubscribeServerDao")
public interface DeviceSubscribeServerDao {
    public DeviceSubscribeServer findById(@Param("id") String id);

    public DeviceSubscribeServer findByDeviceServer(@Param("deviceId") String deviceId ,@Param("serverId")String serverId);

    public void save(DeviceSubscribeServer deviceSubscribeServer);
    public List<Server> subscribeServers(Device device);
    public void cancelSubscribe(DeviceSubscribeServer deviceSubscribeServer);

    public void delete(@Param("deviceSubscribeServer")DeviceSubscribeServer deviceSubscribeServer);

}
