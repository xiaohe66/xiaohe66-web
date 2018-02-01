package com.xiaohe66.web.category.service;

import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.data.CheckUtils;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.category.dao.CategoryDao;
import com.xiaohe66.web.category.po.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
@Service
public class CategoryService extends AbstractService<Category> {
    private CategoryDao categoryDao;

    public CategoryService(){}

    @Autowired
    public CategoryService(CategoryDao categoryDao){
        super(categoryDao);
        this.categoryDao = categoryDao;
    }

    public List<Category> findByPid(Long pid){
        if(CheckUtils.isNull(pid)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"pid is null");
        }
        return categoryDao.searchByPid(pid);
    }
}
