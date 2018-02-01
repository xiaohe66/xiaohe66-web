package com.xiaohe66.web.category.dao;

import com.xiaohe66.web.common.base.BaseDao;
import com.xiaohe66.web.category.po.Category;

import java.util.List;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
public interface CategoryDao extends BaseDao<Category> {
    /**
     * 根据pid查询
     * @param pid pid
     * @return List<Category>
     */
    List<Category> searchByPid(Long pid);
}
