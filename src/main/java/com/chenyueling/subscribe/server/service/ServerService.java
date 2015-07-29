package com.chenyueling.subscribe.server.service;

import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.vo.JsonEasyUI;
import com.chenyueling.subscribe.resource.vo.ServerVo;
import com.chenyueling.subscribe.server.entity.Server;
import com.chenyueling.subscribe.resource.form.ServerForm;

import java.util.List;

/**
 * Created by chenyueling on 2015/4/24.
 */
public interface ServerService {
    public Result save(ServerForm dataForm);
    public JsonEasyUI<ServerVo> getData(ServerForm dataForm);
    public JsonEasyUI<ServerVo> getDataByUser(ServerForm dataForm);
    public JsonEasyUI<ServerVo> getNotAuditData(ServerForm dataForm);
    public Result delete(ServerForm dataForm);
    public ServerVo find(String id);
    public Result resetToken(String id);

    public List<ServerVo> publicServers(ServerForm dataForm);
    public List<ServerVo> privateServers(ServerForm dataForm);
    public List<ServerVo> selfServers(ServerForm dataForm);

    public JsonEasyUI<ServerVo> getAuditData(ServerForm dataFrom);

    public Result pass(String id);

    public Result unPass(String id);
}
