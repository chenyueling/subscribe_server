package com.chenyueling.subscribe.server.service;

import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.form.BaseForm;
import com.chenyueling.subscribe.resource.form.DeviceSubscribeServerForm;
import com.chenyueling.subscribe.resource.vo.ServerVo;

import java.util.List;

/**
 * Created by chenyueling on 2015/5/11.
 */
public interface DeviceSubscribeServerService {
    /**
     * Service Manage
     */
    public Result subscribeServer(String serverId,String deviceCode);

    public Result cancelSubscribeServer(String serverId, String deviceCode);

    public List<ServerVo> subscribeServerList(String deviceCode);
}
