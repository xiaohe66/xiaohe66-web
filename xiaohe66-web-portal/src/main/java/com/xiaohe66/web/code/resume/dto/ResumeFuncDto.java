package com.xiaohe66.web.code.resume.dto;

import com.xiaohe66.web.base.base.BaseDtoDetailed;

/**
 * 简历-功能开发
 *
 * @author xh
 * @date 18-10-12 012
 */
public class ResumeFuncDto extends BaseDtoDetailed {

    private String funcName;
    private Integer articleId;

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
