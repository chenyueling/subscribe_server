package com.chenyueling.subscribe.resource;

import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.form.ArticleForm;
import com.chenyueling.subscribe.resource.form.DeviceForm;
import com.chenyueling.subscribe.resource.form.DeviceSubscribeServerForm;
import com.chenyueling.subscribe.resource.form.ServerForm;
import com.chenyueling.subscribe.resource.vo.AndroidListVo;
import com.chenyueling.subscribe.resource.vo.ArticleVo;
import com.chenyueling.subscribe.resource.vo.ServerVo;
import com.chenyueling.subscribe.server.dao.ArticleDao;
import com.chenyueling.subscribe.server.dao.ServerDao;
import com.chenyueling.subscribe.server.service.ArticleService;
import com.chenyueling.subscribe.server.service.DeviceService;
import com.chenyueling.subscribe.server.service.DeviceSubscribeServerService;
import com.chenyueling.subscribe.server.service.ServerService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.attribute.standard.MediaSize;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by chenyueling on 2015/5/2.
 *
 * publicServerList
 * privateServerList
 * subscribeServerList
 * subscribeArticle
 * subscribeServer
 * cancelSubscribeServer
 *
 *
 */

@Path("/device")
public class DeviceResource extends ResourceConfig {
    @Autowired
    DeviceSubscribeServerService deviceSubscribeServerService;

    @Autowired
    ServerService serverService;
    @Autowired
    ArticleService articleService;

    @Autowired
    DeviceService deviceService;
    @POST
    @Path("/servers/subscribe")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result subscribeServer(DeviceSubscribeServerForm dataForm){
        return deviceSubscribeServerService.subscribeServer(dataForm.getServerId(),dataForm.getDeviceCode());
    }


    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result register(DeviceForm dataForm){
        return deviceService.generateCode(dataForm.getImei());
    }

     @DELETE
     @Path("/servers/cancel")
     @Consumes(MediaType.TEXT_PLAIN)
     @Produces(MediaType.APPLICATION_JSON)
     public Result cancelSubscribeServer(@BeanParam DeviceSubscribeServerForm dataForm){
        return deviceSubscribeServerService.cancelSubscribeServer(dataForm.getServerId(), dataForm.getDeviceCode());
    }




    @GET
    @Path("/servers/subscribe_list")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ServerVo> subscribeServers(@QueryParam("deviceCode") String deviceCode){
        System.out.println("server");
        return deviceSubscribeServerService.subscribeServerList(deviceCode);
    }


    @GET
    @Path("/servers/public")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ServerVo> publicServers(@BeanParam ServerForm dataForm){
        return serverService.publicServers(dataForm);
    }


    @GET
    @Path("/servers/private")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ServerVo> privateServers(@BeanParam ServerForm dataForm){
        return serverService.privateServers(dataForm);
    }


    @GET
    @Path("/servers/self")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ServerVo> selfServers(@BeanParam ServerForm dataForm){
        return serverService.selfServers(dataForm);
    }



    @GET
    @Path("/articles/subscribe_list")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticleVo> subscribeArticles(@BeanParam ArticleForm dataForm){
        return articleService.subscribeArticles(dataForm);
    }









}
