package com.xiaohe66.web.text.service;

import com.xiaohe66.web.category.po.Category;
import com.xiaohe66.web.category.service.CategoryService;
import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.data.CheckUtils;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.text.dao.ArticleDao;
import com.xiaohe66.web.text.param.ArticleParam;
import com.xiaohe66.web.text.po.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @time 17-11-08 008
 */
@Service
public class ArticleService extends AbstractService<Article>{

    private static final Long ARTICLE_CATEGORY_ROOT_ID = 1L;

    private ArticleDao articleDao;

    @Autowired
    private CategoryService categoryService;

    public ArticleService(){}

    @Autowired
    public ArticleService(ArticleDao articleDao){
        super(articleDao);
        this.articleDao = articleDao;
    }

    /**
     * 查询列表
     * @param article 条件
     * @return 匹配的结果
     */
    public List<Article> list(Article article){
        return this.searchByParam(article);
    }

    public List<Article> indexList(){
        ArticleParam param = new ArticleParam();
        param.setPublish(true);
        param.setIsPrivate(0);
        return searchByParam(param);
    }

    public List<Category> getCategoryList(){
        return categoryService.findByPid(ARTICLE_CATEGORY_ROOT_ID);
    }

    @Override
    public void update(Article entity, Long currentUsrId) {
        if(CheckUtils.isNull(entity) || CheckUtils.isNull(entity.getId())){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"article or id is null");
        }
        Article dbArticle = searchById(entity.getId());
        if(CheckUtils.isNull(dbArticle)){
            throw new XhException(CodeEnum.RESOURCE_NOT_FOUND,"object not found");
        }
        if(!currentUsrId.equals(dbArticle.getCreateId())){
            throw new XhException(CodeEnum.NOT_PERMISSION,"this article not is current usr article");
        }
        super.update(entity, currentUsrId);
    }
}
