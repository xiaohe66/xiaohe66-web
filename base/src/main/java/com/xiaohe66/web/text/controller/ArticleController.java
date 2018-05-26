package com.xiaohe66.web.text.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaohe66.web.comm.dto.CategoryDto;
import com.xiaohe66.web.comm.service.CategoryService;
import com.xiaohe66.web.common.annotation.Del;
import com.xiaohe66.web.common.annotation.Get;
import com.xiaohe66.web.common.annotation.Page;
import com.xiaohe66.web.common.annotation.Paging;
import com.xiaohe66.web.common.annotation.Post;
import com.xiaohe66.web.common.annotation.Put;
import com.xiaohe66.web.common.annotation.XhController;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.XhData;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.ClassUtils;
import com.xiaohe66.web.org.dto.UsrDto;
import com.xiaohe66.web.org.service.UsrFileService;
import com.xiaohe66.web.org.service.UsrService;
import com.xiaohe66.web.sys.controller.PageController;
import com.xiaohe66.web.sys.dto.CurrentUsr;
import com.xiaohe66.web.sys.dto.Result;
import com.xiaohe66.web.text.dto.ArticleDto;
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

    private static final String ARTICLE_ALL_PAGE_URL = "text/article_all";

    private static final String ARTICLE_ADMIN_PAGE_URL = "text/article_admin";

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TextCategoryService textCategoryService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UsrService usrService;

    @Autowired
    private UsrFileService usrFileService;

    @Page("/add")
    public String index(CurrentUsr currentUsr,Model model){
        List<TextCategory> textCategoryList = textCategoryService.findByUsrId(currentUsr.getId());
        model.addAttribute("perCategoryList",ClassUtils.convertList(TextCategoryDto.class,textCategoryList));
        model.addAttribute("perCategorySize",textCategoryList.size());
        model.addAttribute("sysCategoryList",ClassUtils.convertList(CategoryDto.class,categoryService.findTextSysCategory()));
        return ARTICLE_EDITOR_PAGE_URL;
    }

    @Page("/editor/{id}")
    public String editor(Model model,CurrentUsr currentUsr,@PathVariable("id") Long id){

        ArticleDto articleDto = articleService.findDtoById(id,currentUsr.getId());
        if(articleDto == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"this article is not exist");
        }
        if(!currentUsr.getId().equals(articleDto.getCreateId())){
            throw new XhException(CodeEnum.NOT_PERMISSION,"this article not is current usr article");
        }
        model.addAttribute("article",articleDto);

        List<TextCategory> textCategoryList = textCategoryService.findByUsrId(currentUsr.getId());
        model.addAttribute("perCategoryList",ClassUtils.convertList(TextCategoryDto.class,textCategoryList));
        model.addAttribute("perCategorySize",textCategoryList.size());
        model.addAttribute("sysCategoryList",ClassUtils.convertList(CategoryDto.class,categoryService.findTextSysCategory()));
        return ARTICLE_EDITOR_PAGE_URL;
    }

    @Page("/detail/{id}")
    public String detail(Model model,CurrentUsr currentUsr,@PathVariable("id") Long id){
        ArticleDto articleDto = articleService.findDtoById(id,currentUsr.getId());
        model.addAttribute("article",articleDto);
        model.addAttribute("title",articleDto.getTitle());
        model.addAttribute("page",ARTICLE_DETAIL_PAGE_URL);
        model.addAttribute("usrDto",usrService.lookAtUsr(articleDto.getCreateId()));
        model.addAttribute("fileList",usrFileService.findDtoHotTop5(articleDto.getCreateId()));
        model.addAttribute("hotArticle",articleService.findDtoHotTop5(articleDto.getCreateId()));
        return PageController.RIGHT_PAGE_URL;
    }

    @Page("/list")
    public String list(Model model){
        return list(model,null);
    }

    @Page("/list/{usrId}")
    public String list(Model model,@PathVariable("usrId") Long usrId){
        UsrDto usrDto = usrService.lookAtUsr(usrId);

        PageHelper.startPage(1,10);
        List<ArticleDto> dtoArticleList = articleService.findDtoByUsrId(usrDto.getId());

        model.addAttribute("pageInfo",new PageInfo<>(dtoArticleList));
        model.addAttribute("usrDto",usrDto);
        model.addAttribute("title",usrDto.getUsrName()+"的文章");
        model.addAttribute("fileList",usrFileService.findDtoHotTop5(usrId));
        model.addAttribute("hotArticle",articleService.findDtoHotTop5(usrId));
        model.addAttribute("page",ARTICLE_LIST_PAGE_URL);
        return PageController.RIGHT_PAGE_URL;
    }

    @Page("/admin")
    public String admin(Model model,CurrentUsr currentUsr){
        PageHelper.startPage(1,10);
        List<ArticleDto> list = articleService.findDtoByUsrId(currentUsr.getId());
        model.addAttribute("pageInfo",new PageInfo<>(list));
        model.addAttribute("size",list.size());
        model.addAttribute("page",ARTICLE_ADMIN_PAGE_URL);

        return PageController.USR_ZONE_PAGE_URL;
    }

    @Page("/all")
    public String all(Model model){
        UsrDto usrDto = usrService.lookAtUsr(null);

        PageHelper.startPage(1,10);
        model.addAttribute("pageInfo",new PageInfo<>(articleService.findDtoAll(null,false)));
        model.addAttribute("usrDto",usrDto);
        model.addAttribute("title","文章列表");
        model.addAttribute("usrDivTitle","站长");
        model.addAttribute("fileList",usrFileService.findDtoHotTop5(null));
        model.addAttribute("hotArticle",articleService.findDtoHotTop5(null));
        model.addAttribute("page",ARTICLE_ALL_PAGE_URL);
        return PageController.RIGHT_PAGE_URL;
    }

    @Post
    public Result add(CurrentUsr currentUsr,Article article,
                      @RequestParam(value = "perCategoryIds[]",required=false) Long[] perCategoryIds){
        if (article.getSecretLevel() == null) {
            article.setSecretLevel(XhData.SECRET_LEVEL_ALL);
        }
        articleService.add(article,currentUsr.getUsr().getId(),perCategoryIds);
        return Result.ok(article.getId());
    }

    @Get("/{id}")
    public Result findById(@PathVariable("id") Long id){
        Article article = articleService.findById(id);
        return Result.ok(ClassUtils.convert(ArticleDto.class,article));
    }

    @Paging
    @Get("/all/{onlyWebmaster}")
    public Result all(@PathVariable("onlyWebmaster") boolean onlyWebmaster){
        return Result.ok(articleService.findDtoAll(null,onlyWebmaster));
    }

    @Paging
    @Get("/all/{onlyWebmaster}/{search}")
    public Result all2(@PathVariable("onlyWebmaster") boolean onlyWebmaster,@PathVariable("search") String search){
        return Result.ok(articleService.findDtoAll(search,onlyWebmaster));
    }

    @Paging
    @Get("/usr")
    public Result list(){
        return list2(null);
    }

    @Paging
    @Get("/usr/{lookUsrId}")
    public Result list2(@PathVariable("lookUsrId") Long lookUsrId){
        return Result.ok(articleService.findDtoByUsrId(lookUsrId));
    }

    @Put
    public Result update(CurrentUsr currentUsr,Article article,
                         @RequestParam(value = "perCategoryIds[]",required=false) Long[] perCategoryIds){
        articleService.updateById(article,currentUsr.getId(),perCategoryIds);
        return Result.ok(article.getId());
    }

    @Del("/{id}")
    public Result delete(CurrentUsr currentUsr,@PathVariable("id") Long id){
        articleService.delById(id,currentUsr.getId());
        return Result.ok();
    }
}
