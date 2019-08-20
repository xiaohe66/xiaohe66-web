package com.xiaohe66.web.text.controller;

import com.xiaohe66.web.base.annotation.Del;
import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.org.helper.UsrHelper;
import com.xiaohe66.web.text.dto.TextCategoryDto;
import com.xiaohe66.web.text.po.TextCategory;
import com.xiaohe66.web.text.service.TextCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author xh
 * @date 18-03-07 007
 */
@XhController("/text/category")
public class TextCategoryController {

    @Autowired
    private TextCategoryService categoryService;

    @Get("/{id}")
    public TextCategoryDto get(@PathVariable("id") Long id){
        TextCategory textCategory = categoryService.findById(id);
        return ClassUtils.convert(TextCategoryDto.class, textCategory);
    }

    @Post
    public TextCategoryDto add(TextCategory textCategory){
        categoryService.add(textCategory, UsrHelper.getCurrentUsrId());
        return ClassUtils.convert(TextCategoryDto.class,textCategory);
    }

    @Put
    public void update(TextCategory textCategory){
        categoryService.updateById(textCategory,UsrHelper.getCurrentUsrId());
    }

    @Del("/{id}")
    public void del(@PathVariable("id")Long id){
        categoryService.delById(id,UsrHelper.getCurrentUsrId());
    }

    @Get("/p/{pid}")
    public List<TextCategoryDto> getByPid(@PathVariable("pid")Long pid){
        List<TextCategory> textCategoryList = categoryService.findByPid(pid);
        return ClassUtils.convertList(TextCategoryDto.class, textCategoryList);
    }
}
