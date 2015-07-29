package com.chenyueling.subscribe.server.service;


import com.chenyueling.subscribe.common.DesUtil;
import com.chenyueling.subscribe.common.HttpSessionHelper;
import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.form.AdminForm;
import com.chenyueling.subscribe.resource.vo.AdminVo;
import com.chenyueling.subscribe.server.dao.AdminDao;
import com.chenyueling.subscribe.server.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by chenyueling on 2015/5/6.
 */
@Transactional
@Component("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Inject
    HttpSession httpSession;

    @Override
    public Result login(String username,String password) throws Exception {
        if(username == null || "".equals(username)){
            return new Result(Result.ERROR_STATUS,"username not null!");
        }

        if(password == null || "".equals(password)){
            return new Result(Result.ERROR_STATUS,"password not null!");
        }

        String desPassworcd = DesUtil.encryptDES(password);
        Admin admin = adminDao.login(username,desPassworcd);
        if(admin == null){
            return new Result(Result.ERROR_STATUS,"password error or username not exist !");
        }
        //login success set session
        admin.setLastLoginTime(new Date());
        adminDao.update(admin);

        HttpSessionHelper.putAdmin(httpSession,admin);
        return Result.Success();
    }

    @Override
    public Result updatePassword(AdminForm dataForm) throws Exception {
        String username = HttpSessionHelper.getAdminUsername(httpSession);
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




        Admin admin = adminDao.login(username,password);

        if(admin == null){
            return new Result(Result.ERROR_STATUS,"password error");
        }

        newpassword = DesUtil.encryptDES(newpassword);

        admin.setPassword(newpassword);

        adminDao.update(admin);

        return Result.Success();
    }



    @Override
    public AdminVo info(String id) {
        Admin admin = adminDao.findById(id);
        AdminVo vo = new AdminVo(admin);
        return vo;
    }

    @Override
    public Result update(AdminForm dataFrom) {
        String id = HttpSessionHelper.getAdminId(httpSession);
        if(id == null || "".equals(id)){
            return new Result(Result.ERROR_STATUS,"login time out , please login again!");
        }

        Admin admin = adminDao.findById(id);
        String name = dataFrom.getName();
        if(name != null && !"".equals(name)){
            admin.setName(name);
        }
        adminDao.update(admin);

        return Result.Success();
    }
}
