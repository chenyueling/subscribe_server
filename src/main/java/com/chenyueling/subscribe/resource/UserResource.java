package com.chenyueling.subscribe.resource;

import com.chenyueling.subscribe.common.JsonUtils;
import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.form.ArticleForm;
import com.chenyueling.subscribe.resource.form.DeviceForm;
import com.chenyueling.subscribe.resource.form.ServerForm;
import com.chenyueling.subscribe.resource.form.UserForm;
import com.chenyueling.subscribe.resource.vo.ArticleVo;
import com.chenyueling.subscribe.resource.vo.DeviceVo;
import com.chenyueling.subscribe.resource.vo.JsonEasyUI;
import com.chenyueling.subscribe.resource.vo.ServerVo;
import com.chenyueling.subscribe.server.dao.ArticleDao;
import com.chenyueling.subscribe.server.service.ArticleService;
import com.chenyueling.subscribe.server.service.DeviceService;
import com.chenyueling.subscribe.server.service.ServerService;
import com.chenyueling.subscribe.server.service.UserService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by chenyueling on 2015/4/30.
 */
@Path("/users")
public class UserResource extends ResourceConfig{

    @Autowired
    UserService userService;

    @Autowired
    ServerService serverService;

    @Autowired
    ArticleService articleService;

    @Autowired
    DeviceService deviceSevice;

   @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result login(UserForm dataForm){
        System.out.println(dataForm.getUsername() + " " + dataForm.getPassword());
        Result result = userService.login(dataForm.getUsername(),dataForm.getPassword());
        return result;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result register(UserForm dataForm){
        Result result = userService.register(dataForm);
        return result;
    }

    @PUT
    @Path("/update_password")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result updatePassword(UserForm dataForm){
        Result result = null;
        try {
            result = userService.updatePassword(dataForm);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.SystemError();
        }
        return result;
    }


    @PUT
    @Path("users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Result update(@BeanParam UserForm dataForm,@PathParam("id") String id){
        return null;
    }


    @GET
    @Path("verify_email/{code}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Result verify_email(@PathParam("code") String code){
        return null;
    }


    @POST
    @Path("/servers")
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
            JsonEasyUI<ServerVo> result = serverService.getDataByUser(dataFrom);
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
            JsonEasyUI<ArticleVo> result = articleService.getDataByUser(dataFrom);
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
        DeviceForm dataForm = new DeviceForm();
        dataForm.setSort(sort);
        dataForm.setOrder(order);
        dataForm.setP(page);
        dataForm.setR(rows);
        System.out.println(dataForm.getSort()+dataForm.getOrder()+dataForm.getP());
        try {
            JsonEasyUI<DeviceVo> result = deviceSevice.getDataByUser(dataForm);
            json = JsonUtils.format(result);
        } catch (Exception e) {
            e.printStackTrace();
            json =  JsonUtils.format(Result.SystemError());
        }finally {
            return  Response.ok().entity(json).build();
        }
    }



    @POST
    @Path("/devices/{id}/remove_user")
    @Produces(MediaType.APPLICATION_JSON)
    public Result removeUser(@PathParam("id")String id){
        return deviceSevice.removeUser(id);
    }


    @POST
    @Path("/devices/add_user")
    @Produces(MediaType.APPLICATION_JSON)
    public Result addUser(@FormParam("deviceCode")String deviceCode){
        return deviceSevice.addUser(deviceCode);
    }

    @POST
    @Path("/servers/{id}/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Result resetToken(@PathParam("id")String id){
        serverService.resetToken(id);
        return Result.Success();
    }


    @POST
    @Path("/servers/apply")
    @Produces(MediaType.APPLICATION_JSON)
    public Result addServer(@FormParam("tip")String tip,@FormParam("status")String status,@FormParam("name")String name){
        ServerForm dataForm = new ServerForm();
        dataForm.setTip(tip);
        dataForm.setStatus(status);
        dataForm.setName(name);
        return serverService.save(dataForm);
    }



}
