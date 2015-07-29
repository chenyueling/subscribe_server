package com.chenyueling.subscribe.server.service;

import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.form.AdminForm;
import com.chenyueling.subscribe.resource.vo.AdminVo;

/**
 * Created by chenyueling on 2015/5/6.
 */
public interface AdminService {
    public Result login(String username, String password) throws Exception;
    public Result updatePassword(AdminForm dataFrom) throws Exception;
    AdminVo info(String id);

    public Result update(AdminForm dataFrom);
}
