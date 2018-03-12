package com.xiaohe66.web.text.dao;

import com.xiaohe66.web.common.base.BaseDao;
import com.xiaohe66.web.text.po.TextCategory;

import java.util.List;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
public interface TextCategoryDao extends BaseDao<TextCategory> {
    /**
     * 根据pid查询
     * @param pid pid
     * @return List<TextCategory>
     */
    List<TextCategory> findByPid(Long pid);
}
