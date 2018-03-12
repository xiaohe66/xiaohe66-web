package com.xiaohe66.web.text.controller;

import com.xiaohe66.web.common.annotation.*;
import com.xiaohe66.web.common.util.ClassUtils;
import com.xiaohe66.web.sys.dto.CurrentUsr;
import com.xiaohe66.web.sys.dto.Result;
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
    public Result get(@PathVariable("id") Long id){
        TextCategory textCategory = categoryService.findById(id);
        return Result.ok(ClassUtils.convert(TextCategoryDto.class, textCategory));
    }

    @Post
    public Result add(CurrentUsr currentUsr,TextCategory textCategory){
        categoryService.add(textCategory,currentUsr.getId());
        return Result.ok(textCategory.getId());
    }

    @Put
    public Result update(CurrentUsr currentUsr,TextCategory textCategory){
        categoryService.updateById(textCategory,currentUsr.getId());
        return Result.ok();
    }

    @Del("/{id}")
    public Result del(CurrentUsr currentUsr,@PathVariable("id")Long id){
        categoryService.delById(id,currentUsr.getId());
        return Result.ok();
    }

    @Get("/p/{pid}")
    public Result getByPid(@PathVariable("pid")Long pid){
        List<TextCategory> textCategoryList = categoryService.findByPid(pid);
        return Result.ok(ClassUtils.convertList(TextCategoryDto.class, textCategoryList));
    }
}
