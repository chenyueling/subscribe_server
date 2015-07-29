package com.chenyueling.subscribe.server.service;

import com.chenyueling.subscribe.common.HttpSessionFactory;
import com.chenyueling.subscribe.common.HttpSessionHelper;
import com.chenyueling.subscribe.common.JsonUtils;
import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.form.ArticleForm;
import com.chenyueling.subscribe.resource.vo.ArticleVo;
import com.chenyueling.subscribe.resource.vo.JsonEasyUI;
import com.chenyueling.subscribe.server.dao.ArticleDao;
import com.chenyueling.subscribe.server.dao.DeviceDao;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by chenyueling on 2015/5/2.
 */
@Transactional
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    

    @Autowired
    ServerDao serverDao;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    DeviceDao deviceDao;

    @Autowired
    UserDao userDao;

    @Inject
    HttpSession httpSession;

    @Override
    public Result save(ArticleForm dataForm) {
        String token = dataForm.getToken();
        Server server = serverDao.findByToken(token);
        System.out.println(server);
        if(server == null){
            return new Result(Result.ERROR_STATUS,"no such server!");
        }

        Article article =  new Article();
        article.setId(UUID.randomUUID().toString());
        article.setServer(server);
        article.setTitle(dataForm.getTitle());
        article.setUrl(dataForm.getUrl());
        article.setCreateTime(new Date());

        articleDao.save(article);

        return Result.Success();
    }

    @Override
    public JsonEasyUI<ArticleVo> getDataByUser(ArticleForm dataForm) {
        String id = HttpSessionHelper.getUserId(httpSession);
        User user = userDao.findById(id);
        JsonEasyUI<ArticleVo> articleVoJsonEasyUI = new JsonEasyUI<ArticleVo>();
        List<ArticleVo> articleVos = new ArrayList<ArticleVo>();
        List<Article> articles = articleDao.getScrollDataByUser(user, dataForm.getSort(), dataForm.getOrder(), dataForm.getLimit().getStart(), dataForm.getLimit().getEnd());
        for (Article article : articles) {
            articleVos.add(new ArticleVo(article));
        }
        articleVoJsonEasyUI.setRows(articleVos);
        Long aLong = articleDao.getScrollDataByUserCount(user);
        articleVoJsonEasyUI.setTotal(aLong);
        return articleVoJsonEasyUI;
    }

    @Override
    public JsonEasyUI<ArticleVo> getData(ArticleForm dataForm) {
        JsonEasyUI<ArticleVo> articleVoJsonEasyUI = new JsonEasyUI<ArticleVo>();
        List<ArticleVo> articleVos = new ArrayList<ArticleVo>();
        List<Article> articles = articleDao.getScrollData(dataForm.getSort(), dataForm.getOrder(), dataForm.getLimit().getStart(), dataForm.getLimit().getEnd());
        for (Article article : articles) {
            articleVos.add(new ArticleVo(article));
        }
        articleVoJsonEasyUI.setRows(articleVos);
        Long aLong = articleDao.getScrollDataCount();
        articleVoJsonEasyUI.setTotal(aLong);
        return articleVoJsonEasyUI;
    }

    @Override
    public List<ArticleVo> subscribeArticles(ArticleForm dataForm) {
        List<ArticleVo> articleVos = new ArrayList<ArticleVo>();
        String deviceCode = dataForm.getDeviceCode();
        if(deviceCode == null || "".equals(deviceCode)){
            return articleVos;
        }
        Device device = deviceDao.findByDeviceCode(deviceCode);

        if(deviceDao == null){
            return articleVos;
        }
        List<Article> articles = articleDao.findByDevice(device,dataForm.getSort(),dataForm.getOrder(),dataForm.getLimit().getStart(),dataForm.getLimit().getEnd());
        System.out.println(JsonUtils.format(articles));
        for (Article article : articles) {
            articleVos.add(new ArticleVo(article));
        }
        return articleVos;
    }


}
