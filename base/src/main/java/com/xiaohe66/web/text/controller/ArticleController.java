package com.xiaohe66.web.text.controller;

import com.xiaohe66.web.comm.dto.CategoryDto;
import com.xiaohe66.web.comm.po.Category;
import com.xiaohe66.web.comm.service.CategoryService;
import com.xiaohe66.web.common.annotation.*;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.XhData;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.ClassUtils;
import com.xiaohe66.web.org.dto.UsrDto;
import com.xiaohe66.web.org.service.UsrService;
import com.xiaohe66.web.sys.controller.PageController;
import com.xiaohe66.web.sys.dto.CurrentUsr;
import com.xiaohe66.web.sys.dto.Result;
import com.xiaohe66.web.text.dto.DtoArticle;
import com.xiaohe66.web.text.dto.TextCategoryDto;
import com.xiaohe66.web.text.po.Article;
import com.xiaohe66.web.text.po.TextCategory;
import com.xiaohe66.web.text.service.ArticleService;
import com.xiaohe66.web.text.service.TextCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author xiaohe
 * @time 17-11-08 008
 */
@XhController("/text/article")
public class ArticleController {

    private static final String ARTICLE_EDITOR_PAGE_URL = "text/article_editor";

    private static final String ARTICLE_DETAIL_PAGE_URL = "text/article_detail";

    private static final String ARTICLE_LIST_PAGE_URL = "text/article_list";

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TextCategoryService textCategoryService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UsrService usrService;

    @Page("/add")
    public String index(CurrentUsr currentUsr,Model model){
        List<TextCategory> textCategoryList = textCategoryService.findByUsrId(currentUsr.getId());
        model.addAttribute("perCategoryList",ClassUtils.convertList(TextCategoryDto.class,textCategoryList));
        model.addAttribute("sysCategoryList",ClassUtils.convertList(CategoryDto.class,categoryService.findTextSysCategory()));
        return ARTICLE_EDITOR_PAGE_URL;
    }

    @Page("/editor")
    public String editor(Model model,CurrentUsr currentUsr,Long id){

        Article article = articleService.findById(id);
        if(article == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"this article is not exist");
        }
        if(!currentUsr.getId().equals(article.getCreateId())){
            throw new XhException(CodeEnum.NOT_PERMISSION,"this article not is current usr article");
        }
        model.addAttribute("article",articleService.toDto(article));

        List<TextCategory> textCategoryList = textCategoryService.findByUsrId(currentUsr.getId());
        model.addAttribute("perCategoryList",ClassUtils.convertList(TextCategoryDto.class,textCategoryList));
        model.addAttribute("sysCategoryList",ClassUtils.convertList(CategoryDto.class,categoryService.findTextSysCategory()));
        return ARTICLE_EDITOR_PAGE_URL;
    }

    @Page("/detail/{id}")
    public String detail(Model model,@PathVariable("id") Long id){
        DtoArticle dtoArticle = articleService.findDtoById(id);
        model.addAttribute("article",dtoArticle);
        model.addAttribute("title",dtoArticle.getTitle());
        model.addAttribute("page",ARTICLE_DETAIL_PAGE_URL);
        model.addAttribute("usrDto",usrService.lookAtUsr(dtoArticle.getCreateId()));
        return PageController.RIGHT_PAGE_URL;
    }

    @Page("/list")
    public String list(Model model){
        return list(model,null);
    }

    @Page("/list/{usrId}")
    public String list(Model model,@PathVariable("usrId") Long usrId){
        UsrDto usrDto = usrService.lookAtUsr(usrId);
        List<DtoArticle> dtoArticleList = articleService.findDtoByUsrId(usrDto.getId());
        model.addAttribute("list",dtoArticleList);
        model.addAttribute("size",dtoArticleList.size());
        model.addAttribute("usrDto",usrDto);
        model.addAttribute("title",usrDto.getUsrName()+"的文章");
        model.addAttribute("page",ARTICLE_LIST_PAGE_URL);
        return PageController.RIGHT_PAGE_URL;
    }

    @Post
    public Result add(CurrentUsr currentUsr,Article article,@RequestParam("perCategoryIds[]") Long[] perCategoryIds){
        if (article.getSecretLevel() == null) {
            article.setSecretLevel(XhData.SECRET_LEVEL_ALL);
        }
        articleService.add(article,currentUsr.getUsr().getId(),perCategoryIds);
        return Result.ok(article.getId());
    }

    @Get("/{id}")
    public Result findById(@PathVariable("id") Long id){
        Article article = articleService.findById(id);
        return Result.ok(ClassUtils.convert(DtoArticle.class,article));
    }

    @Get
    @Paging
    public Result list(Long lookUsrId){
        return Result.ok(articleService.findDtoByUsrId(lookUsrId));
    }

    @Put
    public Result update(CurrentUsr currentUsr,Article article,@RequestParam("perCategoryIds[]") Long[] perCategoryIds){
        articleService.updateById(article,currentUsr.getId(),perCategoryIds);
        return Result.ok(article.getId());
    }

    @Del("/{id}")
    public Result delete(CurrentUsr currentUsr,@PathVariable("id") Long id){
        articleService.delById(id,currentUsr.getId());
        return Result.ok();
    }
}
