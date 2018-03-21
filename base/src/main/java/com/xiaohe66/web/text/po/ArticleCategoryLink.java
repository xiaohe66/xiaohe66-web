package com.xiaohe66.web.text.po;

import com.xiaohe66.web.common.base.BasePo;

/**
 * @author xh
 * @date 18-03-17 017
 */
public class ArticleCategoryLink extends BasePo{
    private Long articleId;
    private Long categoryId;

    public ArticleCategoryLink() {
    }

    public ArticleCategoryLink(Long articleId) {
        this.articleId = articleId;
    }

    public ArticleCategoryLink(Long articleId, Long categoryId) {
        this.articleId = articleId;
        this.categoryId = categoryId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
