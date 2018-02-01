package com.xiaohe66.web.text.controller;

import com.xiaohe66.web.common.annotation.*;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.sys.dto.CurrentUsr;
import com.xiaohe66.web.sys.dto.Result;
import com.xiaohe66.web.text.po.Article;
import com.xiaohe66.web.text.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xiaohe
 * @time 17-11-08 008
 */
@XhController("/text/article")
public class ArticleController {

    private static final String ARTICLE_EDITOR_PAGE_URL = "text/article/article_editor";

    private static final String ARTICLE_DETAIL_PAGE_URL = "text/article/article_detail";

    @Autowired
    private ArticleService articleService;

    @Page("/add")
    public String index(Model model){
//        List<Category> categoryList = articleService.getCategoryList();
//        model.addAttribute("categoryList",categoryList);
        return ARTICLE_EDITOR_PAGE_URL;
    }

    @Page("/editor")
    public String editor(Model model,CurrentUsr usr,Long id){
        Article article = articleService.searchById(id);
        if(article != null && !usr.getId().equals(article.getCreateId())){
            throw new XhException(CodeEnum.NOT_PERMISSION,"this article not is current usr article");
        }
//        List<Category> categoryList = articleService.getCategoryList();
//        model.addAttribute("categoryList",categoryList);
        model.addAttribute("article",article);
        return ARTICLE_EDITOR_PAGE_URL;
    }

    @Page("/detail")
    public String detail(Model model,Long id){
        Article article = articleService.searchById(id);
        model.addAttribute("article",article);
        return ARTICLE_DETAIL_PAGE_URL;
    }

    @Post
    public Result add(CurrentUsr currentUsr,Article article){
        articleService.add(article,currentUsr.getUsr().getId());
        return Result.ok(article.getId());
    }

    @Get("/{id}")
    public Result findById(@PathVariable("id") Long id){
        return Result.ok(articleService.searchById(id));
    }

    @Get
    @Paging
    public Result list(Article article){
        article.setPublish(true);
        return Result.ok(articleService.list(article));
    }

    @Put
    public Result update(CurrentUsr currentUsr,Article article){
        articleService.update(article,currentUsr.getId());
        return Result.ok(article.getId());
    }
}
