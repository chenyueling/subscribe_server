package com.chenyueling.subscribe.server.service;

import com.chenyueling.subscribe.common.DesUtil;
import com.chenyueling.subscribe.common.HttpSessionHelper;
import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.form.UserForm;
import com.chenyueling.subscribe.resource.vo.JsonEasyUI;
import com.chenyueling.subscribe.resource.vo.UserVo;
import com.chenyueling.subscribe.server.dao.UserDao;
import com.chenyueling.subscribe.server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenyueling on 2015/4/30.
 */
@Component("userService")
public class UserServiceImpl implements UserService {


    @Autowired
    UserDao userDao;

    @Inject
    HttpSession httpSession;

    @Override
    public Result login(String userName, String password) {
        String enryptPassword = null;
        try {
            enryptPassword = DesUtil.encryptDES(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = userDao.login(userName,enryptPassword);
        if(user == null){
            return new Result(Result.ERROR_STATUS,"user not exist or password error!");
        }
        //login success put to session
        HttpSessionHelper.putUser(httpSession, user);
        return Result.Success();
    }

    @Override
    public Result register(UserForm dataForm) {
        User user =  new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(dataForm.getName());
        String email = dataForm.getEmail();
        //email format verify
        if(!isEmail(email)){
            return new Result(Result.ERROR_STATUS,"email format error!");
        }

        long count = userDao.verifyExist("email",email);
        if(count != 0){
            return new Result(Result.ERROR_STATUS,"email already exist!");
        }
        user.setEmail(email);

        String userName = dataForm.getUsername();
        count = userDao.verifyExist("username",userName);
        if(count != 0){
            return new Result(Result.ERROR_STATUS,"username already exist!");
        }
        user.setUsername(userName);
        String password= dataForm.getPassword();
        try {
            password = DesUtil.encryptDES(password);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Result.ERROR_STATUS,"password not valid!");
        }

        user.setPassword(password);
        user.setCreateTime(new Date());
        userDao.save(user);
        return Result.Success();
    }

    @Override
    public Result updatePassword(UserForm dataForm) throws Exception {

        String username = HttpSessionHelper.getUserUserName(httpSession);
        String password = DesUtil.encryptDES(dataForm.getPassword());
        String newpassword = dataForm.getNewPassword();
        String repassword = dataForm.getRepassword();
        if(username == null || "".equals(username)){
            return new Result(Result.ERROR_STATUS,"login time out , please login again!");
        }



        if(password == null || "".equals(password)){
            return new Result(Result.ERROR_STATUS,"new password not null!");
        }

        if(newpassword == null || "".equals(newpassword)){
            return new Result(Result.ERROR_STATUS,"password not null!");
        }
        if(repassword == null || "".equals(repassword)){
            return new Result(Result.ERROR_STATUS,"password not null!");
        }

        if(!newpassword.equals(repassword)){
            return new Result(Result.ERROR_STATUS,"Two input password does not match!");
        }




        User user = userDao.login(username,password);

        if(user == null){
            return new Result(Result.ERROR_STATUS,"password error");
        }

        newpassword = DesUtil.encryptDES(newpassword);

        user.setPassword(newpassword);

        userDao.update(user);

        return Result.Success();
    }

    @Override
    public Result update(UserForm dataForm, String id) {

        String userId = HttpSessionHelper.getUserId(httpSession);
        if(userId == null || !userId.equals(id)){
            return new Result(Result.ERROR_STATUS,"The login timeout, please login again!");
        }
        User user =  userDao.findById(userId);
        user.setId(UUID.randomUUID().toString());
        user.setName(dataForm.getName());
        String email = dataForm.getEmail();
        //email format verify
        if(!isEmail(email)){
            return new Result(Result.ERROR_STATUS,"email format error!");
        }

        long count = userDao.verifyExist("email",email);
        if(count != 0){
            return new Result(Result.ERROR_STATUS,"email already exist!");
        }
        user.setEmail(email);

        String userName = dataForm.getUsername();
        count = userDao.verifyExist("username",userName);
        if(count != 0){
            return new Result(Result.ERROR_STATUS,"username already exist!");
        }
        dataForm.setUsername(userName);
        String password= dataForm.getPassword();
        try {
            password = DesUtil.encryptDES(password);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Result.ERROR_STATUS,"password not valid!");
        }

        user.setPassword(password);
        user.setCreateTime(new Date());
        userDao.update(user);
        return Result.Success();
    }

    public void loginOut(){
        HttpSessionHelper.removeUser(httpSession);
    }

    @Override
    public JsonEasyUI<UserVo> getData(UserForm dataForm) {
        List<User> users = userDao.getScrollData(dataForm.getSort(), dataForm.getOrder(), dataForm.getLimit().getStart(), dataForm.getLimit().getEnd());
        List<UserVo> userVos = new ArrayList<UserVo>();
        for (User user : users) {
            userVos.add(new UserVo(user));
        }
        JsonEasyUI<UserVo> userVoJsonEasyUI = new JsonEasyUI<UserVo>();
        userVoJsonEasyUI.setRows(userVos);
        Long aLong = userDao.getScrollDataCount();
        userVoJsonEasyUI.setTotal(aLong);
        return userVoJsonEasyUI;
    }


    @Override
    public boolean verifyEmail(String code) {
        return false;
    }

    @Override
    public UserVo info(String id) {
        User user = userDao.findById(id);
        UserVo  vo =  new UserVo(user);
        return vo;
    }

    private boolean isEmail(String email){
        if (null==email || "".equals(email)) return false;
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
