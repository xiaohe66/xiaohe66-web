package com.xiaohe66.web.common.service;

import com.xiaohe66.web.common.dao.CategoryDao;
import com.xiaohe66.web.common.po.Category;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.sys.helper.SysCfgHelper;
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
        if(Check.isOneNull(pid)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"pid is null");
        }
        return categoryDao.findByPid(pid);
    }

    public List<Category> findTextSysCategory(){
        String val = SysCfgHelper.getString(Final.Str.DEFAULT_ARTICLE_PID);
        return findByPid(StrUtils.toLongNotException(val));
    }
}
