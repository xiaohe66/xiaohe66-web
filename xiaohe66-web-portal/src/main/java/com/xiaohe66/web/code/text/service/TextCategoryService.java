package com.xiaohe66.web.code.text.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.code.text.dao.TextCategoryMapper;
import com.xiaohe66.web.code.text.param.TextCategoryParam;
import com.xiaohe66.web.code.text.po.TextCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
@Service
public class TextCategoryService extends AbstractService<TextCategoryMapper, TextCategory> {

    public List<TextCategory> findByPid(Integer pid) {
        Check.notEmpty(pid,"pid");
        return baseMapper.findByPid(pid);
    }

    public List<TextCategory> findByUsrId(Integer userId) {
        Check.notEmpty(userId,"userId");
        TextCategory param = new TextCategory();
        param.setCreateId(userId);

        return list(new QueryWrapper<>(param));
    }

    public String findNamesByArticleId(Integer articleId) {
        Check.notEmpty(articleId,"articleId");
        return baseMapper.findNamesByArticleId(articleId);
    }

    public List<TextCategory> findByArticleId(Integer articleId) {
        Check.notEmpty(articleId,"articleId");
        return baseMapper.findByArticleId(articleId);
    }
}
