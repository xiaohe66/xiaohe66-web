package com.xiaohe66.web.code.text.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePo;

/**
 * @author xh
 * @date 18-03-17 017
 */
@TableName("xiaohe66_web_text_article_category_link")
public class ArticleCategoryLink extends BasePo{
    private Integer articleId;
    private Integer categoryId;

    public ArticleCategoryLink() {
    }

    public ArticleCategoryLink(Integer articleId) {
        this.articleId = articleId;
    }

    public ArticleCategoryLink(Integer articleId, Integer categoryId) {
        this.articleId = articleId;
        this.categoryId = categoryId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
