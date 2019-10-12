package com.xiaohe66.web.code.text.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.code.text.dao.ArticleCategoryLinkDao;
import com.xiaohe66.web.code.text.po.ArticleCategoryLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xh
 * @date 18-03-17 017
 */
@Service
public class ArticleCategoryLinkService extends AbstractService<ArticleCategoryLinkDao,ArticleCategoryLink>{

    public void delByArticleId(Integer articleId){
        Check.notEmptyCheck(articleId);
        delByParamOfHard(new ArticleCategoryLink(articleId));
    }
}
