package com.chenyueling.subscribe.server.service;

import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.form.BaseForm;
import com.chenyueling.subscribe.resource.form.DeviceSubscribeServerForm;
import com.chenyueling.subscribe.resource.vo.ServerVo;
import com.chenyueling.subscribe.server.dao.DeviceDao;
import com.chenyueling.subscribe.server.dao.DeviceSubscribeServerDao;
import com.chenyueling.subscribe.server.dao.ServerDao;
import com.chenyueling.subscribe.server.entity.Device;
import com.chenyueling.subscribe.server.entity.DeviceSubscribeServer;
import com.chenyueling.subscribe.server.entity.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by chenyueling on 2015/5/11.
 */
@Transactional
@Service("deviceSubscribeService")
public class DeviceSubscribeServerServiceImpl implements DeviceSubscribeServerService {
    @Autowired
    DeviceDao deviceDao;
    @Autowired
    ServerDao serverDao;
    @Autowired
    DeviceSubscribeServerDao deviceSubscribeServerDao;

    @Override
    public Result subscribeServer(String serverId, String deviceCode) {
        if(deviceCode == null || "".equals(deviceCode)){
            return new Result(Result.ERROR_STATUS,"deviceCode not null!");
        }

        Device device = deviceDao.findByDeviceCode(deviceCode);

        if(device == null){
            return new Result(Result.ERROR_STATUS,"no power operate!");
        }

        if(serverId == null || "".equals(serverId)){
            return new Result(Result.ERROR_STATUS,"serverId not null!");
        }

        Server server = serverDao.findById(serverId);

        if(server == null){
            return new Result(Result.ERROR_STATUS,"no such server!");
        }

        DeviceSubscribeServer deviceSubscribeServer = deviceSubscribeServerDao.findByDeviceServer(device.getId(), server.getId());

        if(deviceSubscribeServer != null){
            return new Result(Result.ERROR_STATUS,"you already have a subscription this server!");
        }

        deviceSubscribeServer = new DeviceSubscribeServer();
        deviceSubscribeServer.setId(UUID.randomUUID().toString());
        deviceSubscribeServer.setServer(server);
        deviceSubscribeServer.setDevice(device);
        deviceSubscribeServer.setCreateTime(new Date());

        deviceSubscribeServerDao.save(deviceSubscribeServer);

        return Result.Success();
    }

    @Override
    public Result cancelSubscribeServer(String serverId, String deviceCode) {
        if(deviceCode == null || "".equals(deviceCode)){
            return new Result(Result.ERROR_STATUS,"deviceCode not null!");
        }

        Device device = deviceDao.findByDeviceCode(deviceCode);

        if(device == null){
            return new Result(Result.ERROR_STATUS,"no power operate!");
        }

        if(serverId == null || "".equals(serverId)){
            return new Result(Result.ERROR_STATUS,"serverId not null!");
        }

        Server server = serverDao.findById(serverId);

        if(server == null){
            return new Result(Result.ERROR_STATUS,"no such server!");
        }

        DeviceSubscribeServer deviceSubscribeServer = deviceSubscribeServerDao.findByDeviceServer(device.getId(), server.getId());

        if(deviceSubscribeServer == null){
            return new Result(Result.ERROR_STATUS,"You not subscribe this serves");
        }

        deviceSubscribeServerDao.delete(deviceSubscribeServer);

        return Result.Success();
    }

    @Override
    public List<ServerVo> subscribeServerList(String deviceCode) {

        List<ServerVo> serverVos = new ArrayList<ServerVo>();
        if(deviceCode == null || "".equals(deviceCode)){
            return serverVos;
        }
        Device device = deviceDao.findByDeviceCode(deviceCode);

        if(device == null){
            return serverVos;
        }

        List<Server> servers = deviceSubscribeServerDao.subscribeServers(device);

        for (Server server : servers) {
            serverVos.add(new ServerVo(server));
            System.out.println(server.getName());
        }
        return serverVos;
    }
}
