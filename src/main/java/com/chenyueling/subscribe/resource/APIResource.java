package com.chenyueling.subscribe.resource;

import com.chenyueling.subscribe.common.JsonUtils;
import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.form.ArticleForm;
import com.chenyueling.subscribe.server.dao.ArticleDao;
import com.chenyueling.subscribe.server.service.ArticleService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by chenyueling on 2015/5/11.
 */
@Path("/api")
public class APIResource extends ResourceConfig{

    @Autowired
    ArticleService articleService;

    @POST
    @Path("/articles")
    public Result pushArticle(ArticleForm dataForm){
        Result result = articleService.save(dataForm);
        System.out.println(JsonUtils.format(result));
        return result;
    }


}
