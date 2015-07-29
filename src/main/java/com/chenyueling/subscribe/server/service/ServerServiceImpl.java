package com.chenyueling.subscribe.server.service;

import com.chenyueling.subscribe.common.HttpSessionHelper;
import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.form.BaseForm;
import com.chenyueling.subscribe.resource.form.ServerForm;
import com.chenyueling.subscribe.resource.vo.JsonEasyUI;
import com.chenyueling.subscribe.resource.vo.ServerVo;
import com.chenyueling.subscribe.server.dao.ArticleDao;
import com.chenyueling.subscribe.server.dao.DeviceDao;
import com.chenyueling.subscribe.server.dao.DeviceSubscribeServerDao;
import com.chenyueling.subscribe.server.dao.ServerDao;
import com.chenyueling.subscribe.server.dao.UserDao;
import com.chenyueling.subscribe.server.entity.Article;
import com.chenyueling.subscribe.server.entity.Device;
import com.chenyueling.subscribe.server.entity.Server;
import com.chenyueling.subscribe.server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Created by chenyueling on 2015/4/24.
 */
@Transactional
@Service("serverService")
public class ServerServiceImpl implements ServerService {

    @Autowired
    ServerDao serverDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    DeviceDao deviceDao;

    @Autowired
    DeviceSubscribeServerDao deviceSubscribeServerDao;

    @Inject HttpSession httpSession;

    @Override
    public Result save(ServerForm dataForm) {
       // System.out.println(httpSession.getAttribute("xx"));
        Server server = new Server();
        server.setId(UUID.randomUUID().toString());
        server.setName(dataForm.getName());
        server.setCreateTime(new Date());
        server.setAudit(false);

        //user setting this service is open
        if("PUBLIC".equals(dataForm.getStatus())){
            server.setStatus(true);
        }

        if("PRIVATE".equals(dataForm.getStatus())){
            server.setStatus(false);
        }

        server.setToken(UUID.randomUUID().toString());
        server.setTip(dataForm.getTip());

        String uid = HttpSessionHelper.getUserId(httpSession);
        User user = userDao.findById(uid);
        if(user == null){
            return new Result(Result.ERROR_STATUS,"login timeOut , please login!");
        }
        server.setUser(user);

        serverDao.save(server);
        return Result.Success();
    }


    public Result delete(ServerForm dataForm){
        Server server = serverDao.findById(dataForm.getId());

        if(server == null){
            //return Result
            return new Result(Result.ERROR_STATUS,"delete server not exist!");
        }

        if(validateServerOwner(server)){
            //you not power delete this server
            return new Result(Result.ERROR_STATUS,"you not power delete this server!");
        }

        List<Article> articles = articleDao.findByServer(server);
        for (Article article : articles) {
            articleDao.delete(article);
        }
        serverDao.delete(server);

        return Result.Success();
    }

    @Override
    public ServerVo find(String id) {
        Server server = serverDao.findById(id);
        return new ServerVo(server);
    }

    @Override
    public Result resetToken(String id) {
        Server server = serverDao.findById(id);
        if(validateServerOwner(server)){
            //you not power update this server
            return new Result(Result.ERROR_STATUS,"you not power update this server!");
        }
        server.setToken(UUID.randomUUID().toString());
        serverDao.update(server);
        return Result.Success();
    }


    public Result update(ServerForm dataForm){
        String serverId = dataForm.getId();
        Server server = serverDao.findById(serverId);
        if(server == null){
            //delete server not exist
            return new Result(Result.ERROR_STATUS,"update server not exist!");
        }
        if(validateServerOwner(server)){
            //you not power update this server
            return new Result(Result.ERROR_STATUS,"you not power update this server!");
        }

        server.setName(dataForm.getName());
        server.setTip(dataForm.getTip());
        serverDao.update(server);

        return Result.Success();
    }



    //admin
    public JsonEasyUI<ServerVo> getNotAuditData(ServerForm dataForm){

        BaseForm.Limit limit = dataForm.getLimit();
        JsonEasyUI<ServerVo> serverVoJsonEasyUI = new JsonEasyUI<ServerVo>();
        List<ServerVo> serverVos = new ArrayList<ServerVo>();
        List<Server> servers = serverDao.getScrollDataByAudit(false, dataForm.getSort(), dataForm.getOrder(), limit.getStart(), limit.getEnd());
        for (Server server : servers) {
            serverVos.add(new ServerVo(server));
        }

        Long aLong = serverDao.getScrollDataByAuditCount(false);
        serverVoJsonEasyUI.setTotal(aLong);
        serverVoJsonEasyUI.setRows(serverVos);
        return serverVoJsonEasyUI;
    }


    public JsonEasyUI<ServerVo> getAuditData(ServerForm dataForm){

        BaseForm.Limit limit = dataForm.getLimit();
        JsonEasyUI<ServerVo> serverVoJsonEasyUI = new JsonEasyUI<ServerVo>();
        List<ServerVo> serverVos = new ArrayList<ServerVo>();
        List<Server> servers = serverDao.getScrollDataByAudit(true,dataForm.getSort(), dataForm.getOrder(), limit.getStart(), limit.getEnd());
        for (Server server : servers) {
            serverVos.add(new ServerVo(server));
        }

        Long aLong = serverDao.getScrollDataByAuditCount(true);
        serverVoJsonEasyUI.setTotal(aLong);
        serverVoJsonEasyUI.setRows(serverVos);
        return serverVoJsonEasyUI;
    }

    @Override
    public Result pass(String id) {
        Server server = serverDao.findById(id);
        if(server == null){
            return new Result(Result.ERROR_STATUS,"invalidation server!");
        }
        if(server.isAudit()){
            return new Result(Result.ERROR_STATUS,"server already pass!");
        }

        server.setAudit(true);

        serverDao.update(server);

        return Result.Success();
    }

    @Override
    public Result unPass(String id) {
        Server server = serverDao.findById(id);
        if(server == null){
            return new Result(Result.ERROR_STATUS,"invalidation server!");
        }
        if(!server.isAudit()){
            return new Result(Result.ERROR_STATUS,"server already is not audit!");
        }

        server.setAudit(false);

        serverDao.update(server);

        return Result.Success();
    }


    public JsonEasyUI<ServerVo> getData(ServerForm dataForm){

        BaseForm.Limit limit = dataForm.getLimit();
        JsonEasyUI<ServerVo> serverVoJsonEasyUI = new JsonEasyUI<ServerVo>();
        List<ServerVo> serverVos = new ArrayList<ServerVo>();
        List<Server> servers = serverDao.getScrollData(dataForm.getSort(), dataForm.getOrder(), limit.getStart(), limit.getEnd());
        for (Server server : servers) {
            serverVos.add(new ServerVo(server));
        }
        serverVoJsonEasyUI.setRows(serverVos);
        Long aLong = serverDao.getScrollDataCount();
        serverVoJsonEasyUI.setTotal(aLong);
        return serverVoJsonEasyUI;
    }

    @Override
    public JsonEasyUI<ServerVo> getDataByUser(ServerForm dataForm) {
        String userId = HttpSessionHelper.getUserId(httpSession);
        User user = userDao.findById(userId);
        JsonEasyUI<ServerVo> serverVoJsonEasyUI = new JsonEasyUI<ServerVo>();
        List<ServerVo> serverVos = new ArrayList<ServerVo>();
        List<Server> servers = serverDao.getScrollDataByUser(user,dataForm.getSort(),dataForm.getOrder(),dataForm.getLimit().getStart(),dataForm.getLimit().getEnd());
        for (Server server : servers) {
            serverVos.add(new ServerVo(server));
        }
        serverVoJsonEasyUI.setRows(serverVos);
        Long along = serverDao.getScrollDataByUserCount(user);
        serverVoJsonEasyUI.setTotal(along);
        return serverVoJsonEasyUI;
    }

    @Override
    public List<ServerVo> publicServers(ServerForm dataForm) {
        String deviceCode = dataForm.getDeviceCode();

        List<Server> servers = serverDao.getScrollDataByStatus(true, dataForm.getSort(),dataForm.getOrder(),dataForm.getLimit().getStart(),dataForm.getLimit().getEnd());

        Device device = deviceDao.findByDeviceCode(deviceCode);

        List<Server> subscribeServers = deviceSubscribeServerDao.subscribeServers(device);
        HashSet<ServerVo> hashSets = new HashSet<ServerVo>();
        ServerVo serverVo;

        for (Server subscribeServer : subscribeServers) {
            serverVo = new ServerVo(subscribeServer);
            serverVo.setSubscribeStatus(true);
            hashSets.add(serverVo);
        }

        for (Server server : servers) {
            serverVo = new ServerVo(server);
            hashSets.add(serverVo);
        }

        ArrayList<ServerVo> serverVos = new ArrayList<ServerVo>(hashSets);
        Collections.sort(serverVos);
        return serverVos;
    }



    @Override
    public List<ServerVo> privateServers(ServerForm dataForm) {
        String deviceCode = dataForm.getDeviceCode();
        List<ServerVo> serverVos = new ArrayList<ServerVo>();

        if(deviceCode == null || "".equals(deviceCode)){
            return serverVos;
        }

        Device device = deviceDao.findByDeviceCode(deviceCode);
        User user = device.getUser();
        if(user == null){
            return serverVos;
        }



        List<Server> servers = serverDao.getScrollDataByUserAndStatus(user,false, dataForm.getSort(),dataForm.getOrder(),dataForm.getLimit().getStart(),dataForm.getLimit().getEnd());
        for (Server server : servers) {
            serverVos.add(new ServerVo(server));
        }
        return serverVos;
    }


    @Override
    public List<ServerVo> selfServers(ServerForm dataForm) {
        String deviceCode = dataForm.getDeviceCode();
        List<ServerVo> serverVos = new ArrayList<ServerVo>();

        if(deviceCode == null || "".equals(deviceCode)){
            return serverVos;
        }

        Device device = deviceDao.findByDeviceCode(deviceCode);
        User user = device.getUser();
        if(user == null){
            return serverVos;
        }



        List<Server> servers = serverDao.getScrollDataByUser(user,dataForm.getSort(),dataForm.getOrder(),dataForm.getLimit().getStart(),dataForm.getLimit().getEnd());
        for (Server server : servers) {
            serverVos.add(new ServerVo(server));
        }
        return serverVos;
    }


    /**
     * validate current user operate power
     */
    private boolean validateServerOwner(Server server){
        User serverOwner = server.getUser();
        String uid = HttpSessionHelper.getUserId(httpSession);
        return  !serverOwner.getId().equals(uid);
    }

}
