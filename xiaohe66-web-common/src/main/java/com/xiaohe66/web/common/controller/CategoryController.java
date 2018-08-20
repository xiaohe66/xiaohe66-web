package com.xiaohe66.web.common.controller;

import com.xiaohe66.web.common.dto.CategoryDto;
import com.xiaohe66.web.common.po.Category;
import com.xiaohe66.web.common.service.CategoryService;
import com.xiaohe66.web.base.annotation.Del;
import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.org.helper.UsrHelper;
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
    public CategoryDto get(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        return ClassUtils.convert(CategoryDto.class,category);
    }

    @Post
    public Long add(Category category){
        categoryService.add(category, UsrHelper.getCurrentUsrId());
        return category.getId();
    }

    @Put
    public void update(Category category){
        categoryService.updateById(category, UsrHelper.getCurrentUsrId());
    }

    @Del("/{id}")
    public void del(@PathVariable("id")Long id){
        categoryService.delById(id, UsrHelper.getCurrentUsrId());
    }

    @Get("/p/{pid}")
    public List<CategoryDto> getByPid(@PathVariable("pid")Long pid){
        List<Category> categoryList = categoryService.findByPid(pid);
        return ClassUtils.convertList(CategoryDto.class,categoryList);
    }
}
