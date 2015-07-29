package com.chenyueling.subscribe.server.service;

import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.form.UserForm;
import com.chenyueling.subscribe.resource.vo.JsonEasyUI;
import com.chenyueling.subscribe.resource.vo.UserVo;
import com.chenyueling.subscribe.server.entity.User;

import java.util.List;

/**
 * Created by chenyueling on 2015/4/30.
 */
public interface UserService {
    public Result login(String userName, String password);
    public Result register(UserForm dataForm);
    public Result updatePassword(UserForm dataForm) throws Exception;
    public Result update(UserForm dataForm,String id);
    public boolean verifyEmail(String code);
    public UserVo info(String id);
    public void loginOut();
    public JsonEasyUI<UserVo> getData(UserForm dataForm);
}
