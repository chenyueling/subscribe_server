package com.chenyueling.subscribe.server.dao;

import com.chenyueling.subscribe.server.entity.Device;
import com.chenyueling.subscribe.server.entity.DeviceSubscribeServer;
import com.chenyueling.subscribe.server.entity.Server;
import com.chenyueling.subscribe.server.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by chenyueling on 2015/5/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration( defaultRollback = false)
public class DeviceSubscribeDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    DeviceSubscribeServerDao deviceSubscribeServerDao;

    @Autowired
    ServerDao serverDao;

    @Autowired
    DeviceDao deviceDao;

    @Autowired
    UserDao userDao;



    @Test
    public void subscribeTest(){
        String serverId = "1";
        String deviceId = "1cc";

        Server server = serverDao.findById(serverId);
        Device device = deviceDao.findById(deviceId);
        DeviceSubscribeServer subscribe = new DeviceSubscribeServer();
        subscribe.setId(UUID.randomUUID().toString());
        subscribe.setServer(server);
        subscribe.setDevice(device);
        subscribe.setCreateTime(new Date());
        deviceSubscribeServerDao.save(subscribe);
        System.out.println(deviceSubscribeServerDao.findById(subscribe.getId()).getServer().getName());
        System.out.println("runing success!");
    }


    @Test
    public void findByIdTest(){
        DeviceSubscribeServer deviceSubscribeServer = deviceSubscribeServerDao.findById("dc4877f0-fdda-495b-bb6f-9a0b5bb94199");
        System.out.println(deviceSubscribeServer.getId());
        System.out.println(deviceSubscribeServer.getCreateTime());
        Server server = deviceSubscribeServer.getServer();
        Device device = deviceSubscribeServer.getDevice();
        System.out.println(server.getName());
        System.out.println(server.getUser().getId());
        System.out.println(device.getImei());
        System.out.println(device.getUser().getName());

    }



    @Test
    public void findByDeviceServerTest(){
        String serverId = "1";
        String deviceId = "1cc";
        Server server = serverDao.findById(serverId);
        Device device = deviceDao.findById(deviceId);
        DeviceSubscribeServer deviceSubscribeServer = deviceSubscribeServerDao.findByDeviceServer(device.getId(), server.getId());
        System.out.println(deviceSubscribeServer.getId());
    }

    @Test
    public void subscribeServersTest(){
        String deviceId = "1cc";
        Device device = deviceDao.findById(deviceId);
        List list = deviceSubscribeServerDao.subscribeServers(device);
        for (Object o : list) {
            System.out.println(((Server)o).getName());
        }
    }



}
