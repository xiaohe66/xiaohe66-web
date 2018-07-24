package com.xiaohe66.web.text.controller;

import com.xiaohe66.web.common.annotation.Del;
import com.xiaohe66.web.common.annotation.Get;
import com.xiaohe66.web.common.annotation.Page;
import com.xiaohe66.web.common.annotation.Post;
import com.xiaohe66.web.common.annotation.Put;
import com.xiaohe66.web.common.annotation.XhController;
import com.xiaohe66.web.common.util.ClassUtils;
import com.xiaohe66.web.sys.controller.PageController;
import com.xiaohe66.web.sys.dto.CurrentUsr;
import com.xiaohe66.web.text.dto.TextCategoryDto;
import com.xiaohe66.web.text.po.TextCategory;
import com.xiaohe66.web.text.service.TextCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author xh
 * @date 18-03-07 007
 */
@XhController("/text/category")
public class TextCategoryController {

    private static final String CATEGORY_MANAGEMENT_PAGE_URL = "org/category_management";

    @Autowired
    private TextCategoryService categoryService;

    @Page("/index")
    public String index(Model model,CurrentUsr currentUsr){
        List list = ClassUtils.convertList(TextCategoryDto.class,categoryService.findByUsrId(currentUsr.getId()));
        model.addAttribute("list",list);
        model.addAttribute("size",list.size());
        model.addAttribute("page",CATEGORY_MANAGEMENT_PAGE_URL);
        model.addAttribute("title","分类管理");

        return PageController.USR_ZONE_PAGE_URL;
    }

    @Get("/{id}")
    public TextCategoryDto get(@PathVariable("id") Long id){
        TextCategory textCategory = categoryService.findById(id);
        return ClassUtils.convert(TextCategoryDto.class, textCategory);
    }

    @Post
    public TextCategoryDto add(CurrentUsr currentUsr,TextCategory textCategory){
        categoryService.add(textCategory,currentUsr.getId());
        return ClassUtils.convert(TextCategoryDto.class,textCategory);
    }

    @Put
    public void update(CurrentUsr currentUsr,TextCategory textCategory){
        categoryService.updateById(textCategory,currentUsr.getId());
    }

    @Del("/{id}")
    public void del(CurrentUsr currentUsr,@PathVariable("id")Long id){
        categoryService.delById(id,currentUsr.getId());
    }

    @Get("/p/{pid}")
    public List<TextCategoryDto> getByPid(@PathVariable("pid")Long pid){
        List<TextCategory> textCategoryList = categoryService.findByPid(pid);
        return ClassUtils.convertList(TextCategoryDto.class, textCategoryList);
    }
}
