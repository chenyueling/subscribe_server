package com.chenyueling.subscribe.resource;

import com.chenyueling.subscribe.common.JsonUtils;
import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.form.*;
import com.chenyueling.subscribe.resource.vo.*;
import com.chenyueling.subscribe.server.entity.Article;
import com.chenyueling.subscribe.server.service.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by chenyueling on 2015/5/6.
 */
@Path("/admins")
public class AdminResource extends ResourceConfig {
    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    ServerService serverService;

    @Autowired
    ArticleService articleService;

    @Autowired
    DeviceService deviceService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response info(@PathParam("id")String id){
        AdminVo adminvo = adminService.info(id);
        String json = JsonUtils.format(adminvo);
        return  Response.ok().entity(json).build();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(AdminForm dataForm){
   //public Response login(@QueryParam("username") String username,@QueryParam("password") String password){
        String json = null;
        try {
            System.out.println(dataForm.getUsername() +dataForm.getPassword());
            Result result = adminService.login(dataForm.getUsername(),dataForm.getPassword());
            json = JsonUtils.format(result);
        } catch (Exception e) {
            e.printStackTrace();
            json =  JsonUtils.format(Result.SystemError());
        }finally {
            return  Response.ok().entity(json).build();
        }
    }


    @PUT
     @Path("/update_password")
     @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
     public Response updatePassword(AdminForm dataFrom){
        String json = null;
        try {
            Result result = adminService.updatePassword(dataFrom);
            json = JsonUtils.format(result);
        } catch (Exception e) {
            //e.printStackTrace();
            json =  JsonUtils.format(Result.SystemError());
        }finally {
            return  Response.ok().entity(json).build();
        }
    }


    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(AdminForm dataFrom){
        String json = null;
        try {
            Result result = adminService.update(dataFrom);
            json = JsonUtils.format(result);
        } catch (Exception e) {
            //e.printStackTrace();
            json =  JsonUtils.format(Result.SystemError());
        }finally {
            return  Response.ok().entity(json).build();
        }
    }


    @POST
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
   // @Consumes(MediaType.TEXT_PLAIN)
    public Response getUsers(@FormParam("sort") String sort,@FormParam("order") String order,@FormParam("page") int page,@FormParam("rows") int rows){
        String json = null;
        UserForm dataFrom = new UserForm();
        dataFrom.setSort(sort);
        dataFrom.setOrder(order);
        dataFrom.setP(page);
        dataFrom.setR(rows);

        try {
            JsonEasyUI<UserVo> result = userService.getData(dataFrom);
            json = JsonUtils.format(result);
        } catch (Exception e) {
            //e.printStackTrace();
            json =  JsonUtils.format(Result.SystemError());
        }finally {
            return  Response.ok().entity(json).build();
        }
    }


    @POST
    @Path("/servers/not-audit")
    @Produces(MediaType.APPLICATION_JSON)
    // @Consumes(MediaType.TEXT_PLAIN)
    public Response getNotAuditServers(@FormParam("sort") String sort,@FormParam("order") String order,@FormParam("page") int page,@FormParam("rows") int rows){
        String json = null;
        ServerForm dataFrom = new ServerForm();
        dataFrom.setSort(sort);
        dataFrom.setOrder(order);
        dataFrom.setP(page);
        dataFrom.setR(rows);

        try {
            JsonEasyUI<ServerVo> result = serverService.getNotAuditData(dataFrom);
            json = JsonUtils.format(result);
        } catch (Exception e) {
            //e.printStackTrace();
            json =  JsonUtils.format(Result.SystemError());
        }finally {
            return  Response.ok().entity(json).build();
        }
    }

    @POST
    @Path("/servers/audit")
    @Produces(MediaType.APPLICATION_JSON)
    // @Consumes(MediaType.TEXT_PLAIN)
    public Response getAuditServers(@FormParam("sort") String sort,@FormParam("order") String order,@FormParam("page") int page,@FormParam("rows") int rows){
        String json = null;
        ServerForm dataFrom = new ServerForm();
        dataFrom.setSort(sort);
        dataFrom.setOrder(order);
        dataFrom.setP(page);
        dataFrom.setR(rows);

        try {
            JsonEasyUI<ServerVo> result = serverService.getAuditData(dataFrom);
            json = JsonUtils.format(result);
        } catch (Exception e) {
            //e.printStackTrace();
            json =  JsonUtils.format(Result.SystemError());
        }finally {
            return  Response.ok().entity(json).build();
        }
    }

    @POST
    @Path("/servers")
    @Produces(MediaType.APPLICATION_JSON)
    // @Consumes(MediaType.TEXT_PLAIN)
    public Response getServers(@FormParam("sort") String sort,@FormParam("order") String order,@FormParam("page") int page,@FormParam("rows") int rows){
        String json = null;
        ServerForm dataFrom = new ServerForm();
        dataFrom.setSort(sort);
        dataFrom.setOrder(order);
        dataFrom.setP(page);
        dataFrom.setR(rows);

        try {
            JsonEasyUI<ServerVo> result = serverService.getData(dataFrom);
            json = JsonUtils.format(result);
        } catch (Exception e) {
            //e.printStackTrace();
            json =  JsonUtils.format(Result.SystemError());
        }finally {
            return  Response.ok().entity(json).build();
        }
    }


    @POST
    @Path("/articles")
    @Produces(MediaType.APPLICATION_JSON)
    // @Consumes(MediaType.TEXT_PLAIN)
    public Response getArticles(@FormParam("sort") String sort,@FormParam("order") String order,@FormParam("page") int page,@FormParam("rows") int rows){
        String json = null;
        ArticleForm dataFrom = new ArticleForm();
        dataFrom.setSort(sort);
        dataFrom.setOrder(order);
        dataFrom.setP(page);
        dataFrom.setR(rows);

        try {
            JsonEasyUI<ArticleVo> result = articleService.getData(dataFrom);
            json = JsonUtils.format(result);
        } catch (Exception e) {
            e.printStackTrace();
            json =  JsonUtils.format(Result.SystemError());
        }finally {
            return  Response.ok().entity(json).build();
        }
    }

    @POST
    @Path("/devices")
    @Produces(MediaType.APPLICATION_JSON)
    // @Consumes(MediaType.TEXT_PLAIN)
    public Response getDevices(@FormParam("sort") String sort,@FormParam("order") String order,@FormParam("page") int page,@FormParam("rows") int rows){
        String json = null;
        DeviceForm dataFrom = new DeviceForm();
        dataFrom.setSort(sort);
        dataFrom.setOrder(order);
        dataFrom.setP(page);
        dataFrom.setR(rows);

        try {
            JsonEasyUI<DeviceVo> result = deviceService.getData(dataFrom);
            json = JsonUtils.format(result);
        } catch (Exception e) {
            e.printStackTrace();
            json =  JsonUtils.format(Result.SystemError());
        }finally {
            return  Response.ok().entity(json).build();
        }
    }

    @POST
    @Path("/servers/{id}/pass")
    @Produces(MediaType.APPLICATION_JSON)
    public Result pass(@PathParam("id")String id){
        return serverService.pass(id);
    }


    @POST
    @Path("/servers/{id}/un_pass")
    @Produces(MediaType.APPLICATION_JSON)
    public Result unPass(@PathParam("id")String id){
        return serverService.unPass(id);
    }



}
