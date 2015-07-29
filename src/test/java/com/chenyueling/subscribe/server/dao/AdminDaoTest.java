package com.chenyueling.subscribe.server.dao;

import com.chenyueling.subscribe.common.DesUtil;
import com.chenyueling.subscribe.server.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration( defaultRollback = false)
public class AdminDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    AdminDao adminDao;

    @Test
    public void testFindById() throws Exception {
        String id = "test";
        if(adminDao.findById(id) == null){
            assert false;
        }
    }

    @Test
    public void testLogin() throws Exception {
        String password = DesUtil.encryptDES("123456");
        String username = "chenyueling";
        if(adminDao.login(username,password) == null){
            assert false;
        }

    }

    @Test
    public void testSave() throws Exception {
        Admin admin = new Admin();
        admin.setUserName("chenyueling");
        admin.setName("陈岳凌");
        String passwrod = DesUtil.encryptDES("123456");
        admin.setPassword(passwrod);
        admin.setId("test");
        admin.setCreateTime(new Date());

        adminDao.save(admin);

    }

    @Test
    public void testDelete() throws Exception {
        String id = "test";
        Admin admin = adminDao.findById(id);
        adminDao.delete(admin);
    }

    @Test
    public void testUpdate() throws Exception {
        String id = "test";
        Admin admin = adminDao.findById(id);
        if(admin == null){
            assert false;
        }
        admin.setName("陈岳凌更新");
        //admin.setLastLoginTime(new Date());
        //  adminDao.update(admin);

    }
}