package com.chenyueling.subscribe.server.service;

import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.form.BaseForm;
import com.chenyueling.subscribe.resource.form.DeviceForm;
import com.chenyueling.subscribe.resource.vo.DeviceVo;
import com.chenyueling.subscribe.resource.vo.JsonEasyUI;
import com.chenyueling.subscribe.resource.vo.ServerVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenyueling on 2015/5/1.
 */

public interface DeviceService {

    /**
     * Device Setting
     */
    //device IME code create deviceCode
    public Result generateCode(String ime);
    public Result setServerPushNum(String deviceCode ,int num);
    public DeviceVo getInfo(String deviceCode);
    public List<ServerVo> subscribeServerList(String deviceCode, BaseForm baseForm);

    public Result removeUser(String deviceId);
    public Result addUser(String deviceCode);

    public JsonEasyUI<DeviceVo> getData(DeviceForm dataForm);
    public JsonEasyUI<DeviceVo> getDataByUser(DeviceForm dataForm);

}
