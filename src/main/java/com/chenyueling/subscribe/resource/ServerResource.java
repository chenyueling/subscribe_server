package com.chenyueling.subscribe.resource;

import com.chenyueling.subscribe.common.JsonUtils;
import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.vo.JsonEasyUI;
import com.chenyueling.subscribe.resource.vo.ServerVo;
import com.chenyueling.subscribe.server.entity.Server;
import com.chenyueling.subscribe.resource.form.ServerForm;
import com.chenyueling.subscribe.server.service.ServerService;
import com.google.gson.Gson;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by chenyueling on 2015/4/24.
 */
@Path("/api")
public class ServerResource extends ResourceConfig {

    @Autowired
    ServerService serverService;

    @Inject
    HttpSession httpSession;


    @POST
    @Path("/servers")
    @Consumes(MediaType.APPLICATION_JSON)
    public void save(ServerForm beanParam){

        System.out.println(beanParam.getId());
        System.out.println(beanParam.getName());
        serverService.save(beanParam);

    }

    @GET
    @Path("/servers")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("sort") String sort,@QueryParam("order") String order,@QueryParam("p") int p,@QueryParam("r") int row){
        ServerForm dataForm = new ServerForm();
        dataForm.setP(p);
        dataForm.setR(row);
        dataForm.setSort(sort);
        dataForm.setOrder(order);
        JsonEasyUI<ServerVo> list = serverService.getData(dataForm);
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return Response.ok().entity(json).build();
    }


    @PUT
    @Path("/servers")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id") String id){
        ServerForm dataForm = new ServerForm();
        dataForm.setId(id);
        JsonEasyUI<ServerVo> list = serverService.getData(dataForm);
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return Response.ok().entity(json).build();
    }

    @PUT
    @Path("/servers/{id}/token")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Result resetToken(@PathParam("id")String id){
       serverService.resetToken(id);
        return Result.Success();
    }


    @GET
    @Path("/servers/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") String id){
        ServerVo serverVo = serverService.find(id);
        String json = JsonUtils.format(serverVo);
        return Response.ok().entity(json).build();
    }





}
