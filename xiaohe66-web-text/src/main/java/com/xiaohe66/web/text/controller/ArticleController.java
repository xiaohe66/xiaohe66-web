package com.xiaohe66.web.text.controller;

import com.xiaohe66.web.base.annotation.Del;
import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Paging;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.org.helper.UsrHelper;
import com.xiaohe66.web.text.dto.ArticleDto;
import com.xiaohe66.web.text.po.Article;
import com.xiaohe66.web.text.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author xiaohe
 * @time 17-11-08 008
 */
@XhController("/text/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Post
    public Long add(Article article,@RequestParam(value = "perCategoryIds[]",required=false) Long[] perCategoryIds){
        articleService.add(article,perCategoryIds);
        return article.getId();
    }

    @Get("/{id}")
    public ArticleDto findById(@PathVariable("id") Long id){
        Article article = articleService.findById(id);
        return ClassUtils.convert(ArticleDto.class,article);
    }

    @Paging
    @Get("/all/{onlyWebmaster}")
    public List<ArticleDto> all(@PathVariable("onlyWebmaster") boolean onlyWebmaster){
        return articleService.findDtoAll(null,onlyWebmaster);
    }

    @Paging
    @Get("/all/{onlyWebmaster}/{search}")
    public List<ArticleDto> all2(@PathVariable("onlyWebmaster") boolean onlyWebmaster,@PathVariable("search") String search){
        return articleService.findDtoAll(search,onlyWebmaster);
    }

    @Paging
    @Get("/usr")
    public List<ArticleDto> list(){
        return list2(null);
    }

    @Paging
    @Get("/usr/{lookUsrId}")
    public List<ArticleDto> list2(@PathVariable("lookUsrId") Long lookUsrId){
        return articleService.findDtoByUsrId(lookUsrId);
    }

    @Put
    public Long update(Article article,@RequestParam(value = "perCategoryIds[]",required=false) Long[] perCategoryIds){
        articleService.updateById(article,perCategoryIds);
        return article.getId();
    }

    @Del("/{id}")
    public void delete(@PathVariable("id") Long id){
        articleService.delById(id, UsrHelper.getCurrentUsrId());
    }
}
