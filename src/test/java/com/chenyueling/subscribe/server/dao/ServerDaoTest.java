package com.chenyueling.subscribe.server.dao;

import com.chenyueling.subscribe.server.entity.Server;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration( defaultRollback = false)
public class ServerDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    ServerDao serverDao;

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testFindById() throws Exception {
        Server server = serverDao.findById("1");
       server.setId("2");
        Server server1 = serverDao.findById("1");
        System.out.println(server.equals(server1));
    }

    @Test
    public void testFindByToken() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testGetScrollData() throws Exception {

    }


    @Test
    public void testGetScrollDataByStatus() throws Exception {
        List<Server> servers = serverDao.getScrollDataByStatus(false, "createTime", "asc", 1, 2);
        for (Server server : servers) {
            System.out.println(server.getName());
        }
    }
}