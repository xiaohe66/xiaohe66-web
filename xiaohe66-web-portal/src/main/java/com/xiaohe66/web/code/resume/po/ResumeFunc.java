package com.xiaohe66.web.code.resume.po;

import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * 简历-功能开发
 *
 * @author xh
 * @date 18-10-12 012
 */
public class ResumeFunc extends BasePoDetailed{

    private Integer projectId;
    private String funcName;
    private Integer articleId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

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

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"createTime\":" + createTime
                + ",\"projectId\":\"" + projectId + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"funcName\":\"" + funcName + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"articleId\":\"" + articleId + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
