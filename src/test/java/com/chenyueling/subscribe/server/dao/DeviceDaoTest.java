package com.chenyueling.subscribe.server.dao;

import com.chenyueling.subscribe.server.entity.Device;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration( defaultRollback = false)
public class DeviceDaoTest {

    @Autowired
    DeviceDao deviceDao;

    @Test
    public void testFindById() throws Exception {

    }

    @Test
    public void testSave() throws Exception {
        Device device = new Device();
        device.setId(UUID.randomUUID().toString());
        device.setCode("123456");
        device.setImei("xxx");
        deviceDao.save(device);
    }

    @Test
    public void testFindByIMEI() throws Exception {
        Device device = deviceDao.findByIMEI("123456");
        System.out.println(device);
    }

    @Test
    public void testFindByDeviceCode() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }
}