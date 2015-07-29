package com.chenyueling.subscribe.server.service;

import com.chenyueling.subscribe.common.Result;
import com.chenyueling.subscribe.resource.form.ArticleForm;
import com.chenyueling.subscribe.resource.vo.ArticleVo;
import com.chenyueling.subscribe.resource.vo.JsonEasyUI;
import com.chenyueling.subscribe.server.entity.Article;

import java.util.List;

/**
 * Created by chenyueling on 2015/5/2.
 */
public interface ArticleService {

    public Result save(ArticleForm dataForm);
    public JsonEasyUI<ArticleVo>  getData(ArticleForm dataForm);

    public JsonEasyUI<ArticleVo>  getDataByUser(ArticleForm dataForm);
    public List<ArticleVo> subscribeArticles(ArticleForm dataForm);


}
