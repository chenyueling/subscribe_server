package com.chenyueling.subscribe.server.service;

import com.chenyueling.subscribe.common.HttpSessionHelper;
import com.chenyueling.subscribe.common.RedisHelper;
import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.common.StringUtil;
import com.chenyueling.subscribe.resource.form.BaseForm;
import com.chenyueling.subscribe.resource.form.DeviceForm;
import com.chenyueling.subscribe.resource.vo.DeviceVo;
import com.chenyueling.subscribe.resource.vo.JsonEasyUI;
import com.chenyueling.subscribe.resource.vo.ServerVo;
import com.chenyueling.subscribe.server.dao.DeviceDao;
import com.chenyueling.subscribe.server.dao.DeviceSubscribeServerDao;
import com.chenyueling.subscribe.server.dao.ServerDao;
import com.chenyueling.subscribe.server.dao.UserDao;
import com.chenyueling.subscribe.server.entity.Device;
import com.chenyueling.subscribe.server.entity.DeviceSubscribeServer;
import com.chenyueling.subscribe.server.entity.Server;
import com.chenyueling.subscribe.server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by chenyueling on 2015/5/1.
 */
@Transactional
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService{

    @Autowired
    DeviceDao deviceDao;

    @Autowired
    DeviceSubscribeServerDao deviceSubscribeServerDao;

    @Autowired
    ServerDao serverDao;

    @Inject
    HttpSession httpSession;

    @Autowired
    private UserDao userDao;


    @Override
    public Result generateCode(String imei) {
        Device device = deviceDao.findByIMEI(imei);
        String deviceCode;
        if(device == null){
            device =  new Device();
            device.setId(UUID.randomUUID().toString());
            deviceCode = uniqueDeviceCode(imei);
            device.setCode(deviceCode);
            device.setImei(imei);
            device.setPush_setting(1);
            device.setCreateTime(new Date());
            deviceDao.save(device);
        }
        deviceCode = device.getCode();
        return new Result(Result.SUCCESS_STATUS,deviceCode);
    }

    @Override
    public Result setServerPushNum(String deviceCode,int num) {
        Device device = deviceDao.findByDeviceCode(deviceCode);
        device.setPush_setting(num);
        deviceDao.update(device);
        return Result.Success();
    }

    @Override
    public DeviceVo getInfo(String deviceCode) {
        Device device = deviceDao.findByDeviceCode(deviceCode);
        DeviceVo deviceVo = new DeviceVo(device);
        return deviceVo;
    }





    @Override
    public List<ServerVo> subscribeServerList(String deviceCode, BaseForm baseForm) {
        Device device = deviceDao.findByDeviceCode(deviceCode);
        if(device == null){
            return null;
        }
        List<Server> servers = deviceSubscribeServerDao.subscribeServers(device);
        return null;
    }


    @Override
    public JsonEasyUI<DeviceVo> getData(DeviceForm dataForm) {
        JsonEasyUI<DeviceVo> deviceVoJsonEasyUI = new JsonEasyUI<DeviceVo>();
        List<DeviceVo> deviceVos = new ArrayList<DeviceVo>();
        List<Device> devices = deviceDao.getScrollData(dataForm.getSort(),dataForm.getOrder(),dataForm.getLimit().getStart(),dataForm.getLimit().getEnd());
        for (Device device : devices) {
            deviceVos.add(new DeviceVo(device));
        };

        Long aLong = deviceDao.getScrollDataCount();

        deviceVoJsonEasyUI.setTotal(aLong);
        deviceVoJsonEasyUI.setRows(deviceVos);
        return deviceVoJsonEasyUI;
    }

    @Override
    public JsonEasyUI<DeviceVo> getDataByUser(DeviceForm dataForm) {
        String id = HttpSessionHelper.getUserId(httpSession);
        User user = userDao.findById(id);
        JsonEasyUI<DeviceVo> deviceVoJsonEasyUI = new JsonEasyUI<DeviceVo>();
        List<DeviceVo> deviceVos = new ArrayList<DeviceVo>();
        List<Device> devices = deviceDao.getScrollDataByUser(user,dataForm.getSort(),dataForm.getOrder(),dataForm.getLimit().getStart(),dataForm.getLimit().getEnd());
        for (Device device : devices) {
            deviceVos.add(new DeviceVo(device));
        };

        Long aLong = deviceDao.getScrollDataByUserCount(user);

        deviceVoJsonEasyUI.setTotal(aLong);
        deviceVoJsonEasyUI.setRows(deviceVos);
        return deviceVoJsonEasyUI;
    }

    /**
     * create unique device Code
     * @param imei
     * @return
     */
    private String uniqueDeviceCode(String imei){
        Jedis jedis = RedisHelper.getRedis();
        String deviceCode = jedis.get(imei);
        if(deviceCode == null || "".equals(deviceCode)){
            while(true){
                deviceCode = StringUtil.getRandomNum(6);
                if(jedis.get(deviceCode) == null){
                    jedis.set(imei,deviceCode);
                    jedis.set(deviceCode,imei);
                    break;
                }
            }
        }
        return deviceCode;
    }

    @Override
    public Result removeUser(String deviceId) {
        String id = HttpSessionHelper.getUserId(httpSession);
        User  user = userDao.findById(id);
        Device device = deviceDao.findById(deviceId);

        if(user == null){
            return new Result(Result.ERROR_STATUS,"login time out!");
        }

        if(device == null){
            return new Result(Result.ERROR_STATUS,"no such device!");
        }

        if(device.getUser()== null ){
            return new Result(Result.ERROR_STATUS,"this server not user!");
        }


        if(!device.getUser().getId().equals(user.getId())){
            return new Result(Result.ERROR_STATUS,"not power opera!");
        }
        device.setUser(null);
        deviceDao.update(device);
        return Result.Success();
    }


    @Override
    public Result addUser(String deviceCode) {

        String id = HttpSessionHelper.getUserId(httpSession);
        User  user = userDao.findById(id);


        Device device = deviceDao.findByDeviceCode(deviceCode);

        if(device == null){
            return new Result(Result.ERROR_STATUS,"no such device!");
        }


        if(device.getUser()!= null ){
            return new Result(Result.ERROR_STATUS,"this server already have user!");
        }

        device.setUser(user);

        deviceDao.update(device);

        return Result.Success();
    }
}
