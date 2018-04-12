package com.xiaohe66.web.text.service;

import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.util.Check;
import com.xiaohe66.web.text.dao.ArticleCategoryLinkDao;
import com.xiaohe66.web.text.po.ArticleCategoryLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xh
 * @date 18-03-17 017
 */
@Service
public class ArticleCategoryLinkService extends AbstractService<ArticleCategoryLink>{

    private ArticleCategoryLinkDao articleCategoryLinkDao;

    public ArticleCategoryLinkService() {
    }

    @Autowired
    public ArticleCategoryLinkService(ArticleCategoryLinkDao articleCategoryLinkDao) {
        super(articleCategoryLinkDao);
        this.articleCategoryLinkDao = articleCategoryLinkDao;
    }

    public void delByArticleId(Long articleId){
        Check.notEmptyCheck(articleId);
        delByParamOfHard(new ArticleCategoryLink(articleId));
    }
}
