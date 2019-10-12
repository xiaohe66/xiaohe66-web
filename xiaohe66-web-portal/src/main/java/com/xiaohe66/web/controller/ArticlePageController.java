package com.xiaohe66.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.common.dto.CategoryDto;
import com.xiaohe66.web.code.common.service.CategoryService;
import com.xiaohe66.web.code.file.service.UsrFileService;
import com.xiaohe66.web.code.org.dto.UsrDto;
import com.xiaohe66.web.code.org.helper.UsrHelper;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.text.dto.ArticleDto;
import com.xiaohe66.web.code.text.dto.TextCategoryDto;
import com.xiaohe66.web.code.text.po.TextCategory;
import com.xiaohe66.web.code.text.service.ArticleService;
import com.xiaohe66.web.code.text.service.TextCategoryService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xh
 * @version 1.0
 * @time 2018-08-20 16:09
 */
@XhController("/text/article")
public class ArticlePageController {

    private static final String ARTICLE_EDITOR_PAGE_URL = "text/article_editor";

    private static final String ARTICLE_DETAIL_PAGE_URL = "text/article_detail";

    private static final String ARTICLE_LIST_PAGE_URL = "text/article_list";

    private static final String ARTICLE_ALL_PAGE_URL = "text/article_all";

    private static final String ARTICLE_ADMIN_PAGE_URL = "text/article_admin";

    @Resource
    private ArticleService articleService;

    @Resource
    private TextCategoryService textCategoryService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private UserService userService;

    @Resource
    private UsrFileService usrFileService;

    @Page("/add")
    public String index(Model model){
        List<TextCategory> textCategoryList = textCategoryService.findByUsrId(UsrHelper.getCurrentUsrId());
        model.addAttribute("perCategoryList", ClassUtils.convertList(TextCategoryDto.class,textCategoryList));
        model.addAttribute("perCategorySize",textCategoryList.size());
        model.addAttribute("sysCategoryList",ClassUtils.convertList(CategoryDto.class,categoryService.findTextSysCategory()));
        model.addAttribute("title","添加一篇文章");
        model.addAttribute("page",ARTICLE_EDITOR_PAGE_URL);
        return OtherPageController.USR_ZONE_PAGE_URL;
    }

    @Page("/editor/{id}")
    public String editor(Model model,@PathVariable("id") Integer id){

        Integer currentUsrId = UsrHelper.getCurrentUsrId();
        ArticleDto articleDto = articleService.findDtoById(id);
        if(articleDto == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"this article is not exist");
        }
        if(!currentUsrId.equals(articleDto.getCreateId())){
            throw new XhException(CodeEnum.NOT_PERMISSION,"this article not is current user article");
        }
        model.addAttribute("article",articleDto);

        List<TextCategory> textCategoryList = textCategoryService.findByUsrId(currentUsrId);
        model.addAttribute("title","文章编辑");
        model.addAttribute("perCategoryList",ClassUtils.convertList(TextCategoryDto.class,textCategoryList));
        model.addAttribute("perCategorySize",textCategoryList.size());
        model.addAttribute("sysCategoryList",ClassUtils.convertList(CategoryDto.class,categoryService.findTextSysCategory()));
        model.addAttribute("page",ARTICLE_EDITOR_PAGE_URL);
        return OtherPageController.USR_ZONE_PAGE_URL;
    }

    @Page("/detail/{id}")
    public String detail(Model model,@PathVariable("id") Integer id){
        ArticleDto articleDto = articleService.findDtoById(id);
        model.addAttribute("article",articleDto);
        model.addAttribute("title",articleDto.getTitle());
        model.addAttribute("page",ARTICLE_DETAIL_PAGE_URL);
        model.addAttribute("usrDto",userService.lookAtUsr(articleDto.getCreateId()));
        model.addAttribute("fileList",usrFileService.findDtoHotTop5(articleDto.getCreateId()));
        model.addAttribute("hotArticle",articleService.findDtoHotTop5(articleDto.getCreateId()));
        return OtherPageController.RIGHT_PAGE_URL;
    }

    @Page("/list")
    public String list(Model model){
        return list(model,null);
    }

    @Page("/list/{usrId}")
    public String list(Model model,@PathVariable("usrId") Integer usrId){
        UsrDto usrDto = userService.lookAtUsr(usrId);

        PageHelper.startPage(1,10);
        List<ArticleDto> dtoArticleList = articleService.findDtoByUsrId(usrDto.getId(),Final.Article.SECRET_LEVEL_PUBLIC);

        model.addAttribute("pageInfo",new PageInfo<>(dtoArticleList));
        model.addAttribute("usrDto",usrDto);
        model.addAttribute("title",usrDto.getUsrName()+"的文章");
        model.addAttribute("fileList",usrFileService.findDtoHotTop5(usrId));
        model.addAttribute("hotArticle",articleService.findDtoHotTop5(usrId));
        model.addAttribute("page",ARTICLE_LIST_PAGE_URL);
        return OtherPageController.RIGHT_PAGE_URL;
    }

    @Page("/admin/index")
    public String admin(Model model){
        PageHelper.startPage(1,10);
        List<ArticleDto> list = articleService.findDtoByUsrId(UsrHelper.getCurrentUsrId(),null);
        model.addAttribute("pageInfo",new PageInfo<>(list));
        model.addAttribute("title","文章管理");
        model.addAttribute("size",list.size());
        model.addAttribute("page",ARTICLE_ADMIN_PAGE_URL);

        return OtherPageController.USR_ZONE_PAGE_URL;
    }

    @Page("/all")
    public String all(Model model){
        UsrDto usrDto = userService.lookAtUsr(null);

        PageHelper.startPage(1,10);
        model.addAttribute("pageInfo",new PageInfo<>(articleService.findDtoAll(null,false)));
        model.addAttribute("usrDto",usrDto);
        model.addAttribute("title","文章列表");
        model.addAttribute("usrDivTitle","站长");
        model.addAttribute("fileList",usrFileService.findDtoHotTop5(null));
        model.addAttribute("hotArticle",articleService.findDtoHotTop5(null));
        model.addAttribute("page",ARTICLE_ALL_PAGE_URL);
        return OtherPageController.RIGHT_PAGE_URL;
    }

}
