package com.chenyueling.subscribe.server.dao;

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
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@TransactionConfiguration( defaultRollback = false)
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    UserDao userDao;

    @Test
    public void testSave() throws Exception {
        User u =  new User();
        u.setCreateTime(new Date());
        u.setId(UUID.randomUUID().toString());
        u.setPassword("******");
        u.setEmail("email");
        u.setName("name");
        u.setLastLoginTime(new Date());
        u.setUsername("username");
        userDao.save(u);
    }

    @Test
    public void testFindById() throws Exception {
        String userId = "19bbcb52-07e2-4658-9526-0c784b5d5d11";
        System.out.println(userDao.findById(userId));
    }

    @Test
    public void testWhereUser() throws Exception {
        System.out.println(userDao.whereUser("username", "username"));
        System.out.println(userDao.whereUser("email", "chen_yueling@163.com"));


    }

    @Test
    public void testVerifyExist() throws Exception {
        System.out.println(userDao.verifyExist("email","chen_yueling@163.com"));
        System.out.println(userDao.verifyExist("name","xxx"));
    }

    @Test
    public void testLogin() throws Exception {
        System.out.println(userDao.login("123456", "xxxxx"));
    }
}