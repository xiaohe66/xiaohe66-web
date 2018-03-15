package com.xiaohe66.web.comm.controller;

import com.xiaohe66.web.comm.dto.CategoryDto;
import com.xiaohe66.web.comm.po.Category;
import com.xiaohe66.web.comm.service.CategoryService;
import com.xiaohe66.web.common.annotation.*;
import com.xiaohe66.web.common.util.ClassUtils;
import com.xiaohe66.web.sys.dto.CurrentUsr;
import com.xiaohe66.web.sys.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author xh
 * @date 18-03-07 007
 */
@XhController("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Get("/{id}")
    public Result get(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        return Result.ok(ClassUtils.convert(CategoryDto.class,category));
    }

    @Post
    public Result add(CurrentUsr currentUsr,Category category){
        categoryService.add(category,currentUsr.getId());
        return Result.ok(category.getId());
    }

    @Put
    public Result update(CurrentUsr currentUsr,Category category){
        categoryService.updateById(category,currentUsr.getId());
        return Result.ok();
    }

    @Del("/{id}")
    public Result del(CurrentUsr currentUsr,@PathVariable("id")Long id){
        categoryService.delById(id,currentUsr.getId());
        return Result.ok();
    }

    @Get("/p/{pid}")
    public Result getByPid(@PathVariable("pid")Long pid){
        List<Category> categoryList = categoryService.findByPid(pid);
        return Result.ok(ClassUtils.convertList(CategoryDto.class,categoryList));
    }
}
